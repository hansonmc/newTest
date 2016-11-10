package com.jlc.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.jlc.api.cache.memcached.IMemcachedClient;
import com.jlc.api.cache.redis.RedisDataSource;
import com.jlc.api.constants.Message;
import com.jlc.api.dao.SweepstakeMapper;
import com.jlc.api.dto.*;
import com.jlc.api.service.MemberService;
import com.jlc.api.service.SweepstakeService;
import com.jlc.api.type.SmsFromType;
import com.jlc.api.util.noteplatform.HttpClient;
import com.jlc.api.utils.Base64;
import com.jlc.api.utils.ConstantUtil;
import com.jlc.api.utils.MD5;
import com.jlc.api.utils.StringUtil;
import com.jyd.packets.service.write.UsePacketsWriteService;
import com.jyd.packets.vo.operate.UsePacketsVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import redis.clients.jedis.ShardedJedis;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service("sweepstakeService")
@SuppressWarnings("unchecked")
public class SweepstakeServiceImpl implements SweepstakeService{
	protected Logger log = Logger.getLogger(this.getClass());
	
	//锁
	private Lock lock = new ReentrantLock();
	
	@Autowired
    private RedisDataSource redisDataSource;
	
	@Autowired
	private UsePacketsWriteService usePacketsWriteService;
    
	@Autowired
	private SweepstakeMapper sweepstakeMapper;
	
	@Autowired
	private IMemcachedClient memcachedClient;
	
	@Autowired
	private MemberService memberService;

	@Override
	public CJActivite getActiviteById(Long id) throws Exception {
		//查询活动信息
 		String key = "CJ_ACTIVITE_INFO_BYID_"+id;
 		CJActivite activite = (CJActivite)memcachedClient.get(key);
 		if (activite==null) {
 			activite = sweepstakeMapper.getActiviteById(id);
 			if (activite!=null) {
				boolean flag = memcachedClient.set(key, activite,ConstantUtil.INSTANCE.getActiviteCacheTime());
				if (flag) {
					log.info("--------------sweepstake抽奖接口获取活动信息加入缓存("+ConstantUtil.INSTANCE.getActiviteCacheTime()+"s)-------------");
				}
			}
		}
		return activite;
	}

	@Override
	public Integer getCJCountByAid(Long aId) throws Exception {
		// TODO Auto-generated method stub
		return sweepstakeMapper.getCJCountByAid(aId);
	}

	@Override
	public List<CJRecord> getWinRecordList(Long aId) throws Exception {
		String cache_key = "EBANK_APP_CHOUJIANG_RECORD_LIST_AID_" + aId;
		List<CJRecord> rList = (List<CJRecord>) memcachedClient.get(cache_key);
		if (rList == null || rList.size() == 0) {
			rList = sweepstakeMapper.getWinRecordList(aId);
			if (rList.size() > 0) {
				for (CJRecord cord : rList) {
//					cord.setPhone(null);
					
					if (StringUtil.isEmpty(cord.getBoundPhone()) && StringUtil.isNotEmpty(cord.getPhone())) {
						String phone = cord.getPhone().substring(0, cord.getPhone().length() - 8).concat(
								"****").concat(cord.getPhone().substring(
								cord.getPhone().length() - 4, cord.getPhone().length()));
						cord.setBoundPhone(phone);
					} else if (StringUtil.isNotEmpty(cord.getBoundPhone())) {
						String phone = cord.getBoundPhone().substring(0, cord.getBoundPhone().length() - 8).concat(
								"****").concat(cord.getBoundPhone().substring(
								cord.getBoundPhone().length() - 4, cord.getBoundPhone().length()));
						cord.setBoundPhone(phone);
					}
				}
			}
			boolean flag = memcachedClient.set(cache_key, rList, ConstantUtil.INSTANCE.getWinRecordListCacheTime());
			if (flag) {
				log.info("**********获奖记录加入缓存(" + ConstantUtil.INSTANCE.getWinRecordListCacheTime() + "s)************");
			}
		}
		return rList;
	}

	/**
	 * 创建奖池
	 */
	@Transactional
	@Override
	public Map createPond(final Long id) {
		Map jsonMap = new TreeMap();
		try{
			//获取规则详情,并锁定该记录
			CJRegulation regulation =  sweepstakeMapper.getRegulationByIdForUpdate(id);
	 		if (regulation==null || regulation.getStatus()!=0) {
	 			jsonMap.put("code", Message.CODE_ERROR);
	 			jsonMap.put("message","规则不存在或不需要生成奖池");
	 			return jsonMap;
			}
	 		//总奖品数量
	 		Long total = regulation.getTotalAccount();
	 		if (total==null) {
	 			jsonMap.put("code", Message.CODE_ERROR);
	 			jsonMap.put("message","总数量为0，不能生成奖池");
	 			return jsonMap;
			}
	 		long sum = 0;//奖池里奖品数量
	 		//奖池列表
	 		List<CJPond> pondList = new ArrayList<CJPond>();
	 		
	 		//获取规则对应的奖品设置列表
	 		List<CJRegulationPrize> regulationPrizeList = sweepstakeMapper.getRegulationPrizeList(id);
	 		CJRegulationPrize regulationPrize = null;
	 		long count = 0;
	 		long prizeId = 0;
	 		String prizeCode = "";
	 		//生成奖池数据 需对奖品id进行对称加密
	 		for(int i=0,l=regulationPrizeList.size();i<l;i++){
	 			regulationPrize = regulationPrizeList.get(i);
	 			count = regulationPrize.getAccount();
	 			prizeId = regulationPrize.getPrizeId();
	 			prizeCode = Base64.getBASE64(String.valueOf(prizeId)); 
	 			for(long j=0;j<count;j++){
	 				CJPond pond = new CJPond();
	 				pond.setPrizeId(prizeCode);
	 				pond.setRegulationId(id);
	 				pondList.add(pond);
	 			}
	 			sum+=count;
	 		}
	 		//生成未中奖奖品
	 		prizeCode =  Base64.getBASE64("no");
	 		for(long i=0,l=total-sum;i<l;i++){
	 			CJPond pond = new CJPond();
	 			pond.setPrizeId(prizeCode);
	 			pond.setRegulationId(id);
				pondList.add(pond);
	 		}
	 		//打乱奖品次序
	 		Collections.shuffle(pondList);
	 		//放入redis缓存中 以规则id为key
	 		try{
	 			putPondList(id,pondList);
	 		}catch(Exception e){
 				throw new Exception("连接redis服务器出现错误");
 			}	
	 		sweepstakeMapper.updateRegulationStatus(id,2);
	 		new Thread(new Runnable() {
	 			ShardedJedis shardedJedis = null;
				@Override
				public void run() {
					lock.lock();
					shardedJedis= redisDataSource.getRedisClient();
			        String key = "pondList."+id;
			        List<CJPond> list = new ArrayList<CJPond>();
			        try{
			    		String json = "";
						//批量插入。。。。
						//获取redis中缓存的奖池数据
						for(int i=0;i<100000;i++){
							json = shardedJedis.lpop(key);
							CJPond pond = JSON.parseObject(json, CJPond.class); 
							if(pond == null){
								if(!list.isEmpty()){
									//批量添加奖池
									insertPondBatch(list);
									list.clear();
								}	
								//奖池清洗
								clearPonds(id);
								//修改该规则为进行中
								updateRegulationStatus(id,1);
								shardedJedis.del(key);
//								shardedJedis.lpop("pondList");
								break;
							}else{
								list.add(pond);
								if((i+1) % 100 == 0 && !list.isEmpty()){
									insertPondBatch(list);
									list.clear();
								}	
							}
							
						}
			        	
			        }catch(Exception e){
			        	CJPond pond = new CJPond();
			        	for(int i=0,l=list.size();i<l;i++){
			        		pond = list.get(i);
			        		shardedJedis.rpush("pondList."+id, JSON.toJSONString(pond));
			        	}
			        	log.error(e.getMessage());
			        }finally {
						lock.unlock();
						redisDataSource.returnResource(shardedJedis);	
			        }
				
				}
			}).start();
	 		jsonMap.put("code", Message.CODE_SUCCESS);
			jsonMap.put("message",Message.MSG_SUCCESS);
			jsonMap.put("result", "");
		}catch(Exception e){

			jsonMap.put("code", Message.CODE_ERROR);
 			jsonMap.put("message",e.getMessage());
 			return jsonMap;
		}	
		return jsonMap;
	}
	

	/**
	 * 定时任务调用，将奖池加入redis
	 */
	@Override
	@Transactional
	public void addRedisPonds(long regulationId){
		ShardedJedis shardedJedis = null;
		try{
			 shardedJedis = redisDataSource.getRedisClient();
	        if (shardedJedis == null) {
	            return;
	        }
	        
	        //获取奖池数据放到redis缓存中
	        List<CJPond> list  = getPondList(regulationId,10000);
			String key = "pondList-"+regulationId;
			for(int i=0;i<list.size();i++){
				CJPond pond = list.get(i);
				shardedJedis.lpush(key, JSON.toJSONString(pond));
				if(i==4999) key = "pondList+"+regulationId;
			}
			sweepstakeMapper.deletePonds(regulationId,list.size());
        }catch(Exception e){
        	shardedJedis.del("pondList-"+regulationId);
        	shardedJedis.del("pondList+"+regulationId);
        	shardedJedis.del("regulationId" + regulationId);
        	e.printStackTrace();
        }finally {
        	redisDataSource.returnResource(shardedJedis);
        }
	}

	/**
	 * 将奖池放入redis
	 * @param id
	 * @param pondList
	 * @throws Exception
	 */
	public void putPondList(Long id, List<CJPond> pondList) throws Exception{
		ShardedJedis shardedJedis = null;
		String key = "pondList." + id;
		try{
			//throw new Exception("连接redis服务器出现错误");
			shardedJedis= redisDataSource.getRedisClient();
	        if (shardedJedis == null) {
	        	throw new Exception("连接redis服务器出现错误");
	        }
			CJPond pond = new CJPond();
			if(shardedJedis.exists(key)) return;
			for(int i=0,l=pondList.size();i<l;i++){
				System.out.println(i);
				pond = pondList.get(i);
				pond.setSerialNumber(String.valueOf(i));
				
				shardedJedis.rpush(key,JSON.toJSONString(pond));
			}
			shardedJedis.rpush("pondList", id+"");
		}catch(Exception e){
			shardedJedis.del(key);
			throw new Exception(e.getMessage());
		}finally {
			redisDataSource.returnResource(shardedJedis);	
        }
	}


	@Override
	public void updateRegulationStatus(long rid,int status) throws Exception{
		// TODO Auto-generated method stub
		sweepstakeMapper.updateRegulationStatus(rid,status);
	}

	@Override
	public void insertPondBatch(List<CJPond> list) throws Exception{
		// TODO Auto-generated method stub
		sweepstakeMapper.insertPondBatch(list) ;
	}

	@Override
	public Integer getPondCountByRegulationId(Long regulationId)
			throws Exception {
		// TODO Auto-generated method stub
		return sweepstakeMapper.getPondCountByRegulationId(regulationId);
	}

	/**
	 * 发送奖品
	 */
	@Override
	public Map distributePrizes(Map<String, Object> map) throws Exception {
		Map redMap = new HashMap<String, Object>();
		
		CJRecord record = (CJRecord)map.get("record");
		String phone = (String) map.get("phone");
		Long memberId = Long.valueOf(String.valueOf(map.get("memberId")));
		//更新用户可用抽奖机会
//		sweepstakeMapper.updateMemberCJTimeByMemberId(memberId);
		memberService.updateMemberCJTimeByMemberId(memberId,record.getCjAid());
		
		//红包字典code
		String code = getDictionaryCodeByName("红包");
		//抽中的是红包，直接派发红包
		Map rpmap=new HashMap<String, Object>();
 		rpmap.put("rId", record.getRegulationId());
 		rpmap.put("pId", Base64.getFromBASE64(record.getPrizeId()));//奖品id
 		RegulationAndPrize regulationAndPrize = sweepstakeMapper.getRegulationAndPrize(rpmap);
 		//如果奖品是红包
 		if ("0".equals(record.getStatus())&&regulationAndPrize!=null
 				&&code.equals(regulationAndPrize.getTypeCode())
 				&&regulationAndPrize.getCampaignId()!=null) {
 	 		redMap.put("regulationPrizeId", regulationAndPrize.getRegulationPrizeId());
 	 		redMap.put("idNo", record.getId());
 	 		redMap.put("memberId",memberId);
 	 		redMap.put("phone",phone);
 	 		redMap.put("campaignId",regulationAndPrize.getCampaignId());
			//修改红包中奖记录user_id
			sweepstakeMapper.updateCJRecordUserId(map);
	    }else{
	    	//将奖品状态设置为已发送
			sweepstakeMapper.distributePrizes(map);
	    }
 		
 		//-------------------发送短信和站内信-----------------
 		String prizeName = regulationAndPrize.getAmount()==null?regulationAndPrize.getPrizeName():regulationAndPrize.getAmount().toString().concat("元").concat(regulationAndPrize.getPrizeName());
 		CJActivite activite = getActiviteById(record.getCjAid());
 		if (activite!=null) {
	 		// 短信模板 + 奖品名称处理
	 		String messageTemplate = activite.getMessageTemplate() == null ? ""	: activite.getMessageTemplate();
	 		String content = "";
	 		if(messageTemplate!=null){
	 			content = messageTemplate.replace("XX",prizeName);
	 			log.info("短信和站内信内容为 ： content = " + content);
	 		}
			if (activite.getIsZ() != null && activite.getIsZ() == 1) {// 是否发站内信，0不发，1发
				UserMessage message = new UserMessage();
				message.setUseriId(String.valueOf(memberId));
				message.setTitle("系统消息");
				message.setContent(content);
				message.setType("1");
				message.setReadStatus(0);
				sweepstakeMapper.addUserMessage(message);
			}
			if (activite.getIsM() != null&& activite.getIsM() == 1) {// 是否发短信，0不发，1发
				HttpClient.businSms(null, SmsFromType.APPDISTRIBUTE.getCode(), phone, content, null);//发奖短信01006
			}
 		}
 		
 		//更新中奖记录
 		Long activiteId = record.getCjAid();//活动id
 		String cache_key = "EBANK_APP_CHOUJIANG_RECORD_LIST_AID_"+activiteId;
 		List<CJRecord> rList = (List<CJRecord>) memcachedClient.get(cache_key);
		if (rList!=null&&rList.size()>0) {
			if(record!=null&&!"0".equals(regulationAndPrize.getPrizeLevel())) {
				CJRecord cord = new CJRecord();
				cord.setCjDate(new Date());
				cord.setDetail(prizeName);
				if (StringUtil.isNotEmpty(phone)) {
					String phone1 = phone.substring(0, phone.length()-8).concat("****").concat(phone.substring(phone.length()-4,phone.length()));
					cord.setBoundPhone(phone1);
				}
				if (rList.size()==100) {
					rList.remove(rList.size()-1);
				}
				rList.add(0,cord);
				boolean flag = memcachedClient.set(cache_key, rList,ConstantUtil.INSTANCE.getWinRecordListCacheTime());
				if (flag) {
					log.info("**********获奖记录加入缓存("+ConstantUtil.INSTANCE.getWinRecordListCacheTime()+"s)************");
				}
			}
		}
		
		return redMap;
	}
	
	@Override
	public Long addCjRecord(CJRecord cjRecord) throws Exception {
		// TODO Auto-generated method stub
		return sweepstakeMapper.addCjRecord(cjRecord);
	}

	@Override
	public Integer deletePondById(Long pId) throws Exception {
		// TODO Auto-generated method stub
		return sweepstakeMapper.deletePondById(pId);
	}

	@Override
	public CJPrize getPrizeById(Long pId) throws Exception {
		// TODO Auto-generated method stub
		return sweepstakeMapper.getPrizeById(pId);
	}

	@Override
	public CJPond getPondByRid(Long rId) throws Exception {
		CJPond pond = sweepstakeMapper.getPondByRid(rId);
		return pond;
	}

	@Override
	public Integer getMemberWinCount(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sweepstakeMapper.getMemberWinCount(map);
	}

	@Override
	public CJRegulation getRegulationById(Long id) throws Exception {
		//获取规则信息
 		String rkey = "CJ_getRegulationById_"+id;
 		CJRegulation regulation = (CJRegulation)memcachedClient.get(rkey);
 		if (regulation==null) {
 			regulation = sweepstakeMapper.getRegulationById(id);
 			if (regulation!=null) {
				boolean flag = memcachedClient.set(rkey, regulation,ConstantUtil.INSTANCE.getRegulationCacheTime());
				if (flag) {
					log.info("--------------规则信息加入缓存("+ConstantUtil.INSTANCE.getRegulationCacheTime()+"s)-------------");
				}
			}
		}
		return regulation;
	}

	@Override
	public String checkPhone(String phone) throws Exception {
		// TODO Auto-generated method stub
		return sweepstakeMapper.checkPhone(phone);
	}

	@Override
	public Integer distributeRedPacket(Map<String, Object> map){
		Integer code = 0;//0:派发成功  -1：派发失败
		try{
			Long t1= System.currentTimeMillis();
			//获取用户信息，以及红包金额
			usePacketsWriteService = (UsePacketsWriteService) ContextLoader
					.getCurrentWebApplicationContext().getBean("usePacketsWriteService");
			List<UsePacketsVO> usePackets  = new ArrayList<UsePacketsVO>();
			
			
			if(StringUtil.isNotEmpty(map.get("phone").toString())){
				 log.info("红包接口参数phone:"+map.get("phone") +"  campaignId:"+map.get("campaignId"));
				 usePackets = usePacketsWriteService.getPacketsforLuckyDraw(
							new BigDecimal(String.valueOf(map.get("memberId"))),
							map.get("phone").toString(), 
							new BigDecimal(String.valueOf(map.get("campaignId"))));
			}
			else if(StringUtil.isNotEmpty(map.get("userName").toString())){
				 log.info("红包接口参数phone:"+map.get("userName") +"  campaignId:"+map.get("campaignId"));
				 usePackets = usePacketsWriteService.getPacketsforLuckyDraw(
							new BigDecimal(String.valueOf(map.get("memberId"))),
							map.get("userName").toString(), 
							new BigDecimal(String.valueOf(map.get("campaignId"))));
			}
			Long t2= System.currentTimeMillis();
			log.info("--------------------红包系统发送奖品接口耗时（毫秒）:"+(t2-t1));
	        if (usePackets == null || usePackets.size() <= 0) {
	        	log.info("--------------------红包系统发送奖品个数为空");
				code=-1;
			}else{//更新红包领取状态
				Long rId = (Long) map.get("idNo");//红包记录id
				CJRecord record = sweepstakeMapper.getRecordById(rId);
				if(record!=null){
					record.setStatus("1");
					sweepstakeMapper.updateCJRecordStatus(rId);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			log.info("--------------------红包系统发送奖品异常"+e.getMessage());
			code = -1;
		}
		return code;
	}

	@Override
	public CJRegulationPrize getRegulationPrize(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return sweepstakeMapper.getRegulationPrize(map);
	}

	@Override
	public CJRecord getRecordById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return sweepstakeMapper.getRecordById(id);
	}

	@Override
	public Integer addUserMessage(UserMessage message) throws Exception {
		// TODO Auto-generated method stub
		return sweepstakeMapper.addUserMessage(message);
	}

	@Override
	public CJRecord getRecordByRandomMd5AndPhone(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return sweepstakeMapper.getRecordByRandomMd5AndPhone(map);
	}
	
	/**
	 * 获取奖池
	 * @param regulationId
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public CJPond getPond(final Long regulationId) throws Exception {
		ShardedJedis shardedJedis = null;
		String json = "";
		try{
			shardedJedis= redisDataSource.getRedisClient();
	        if (shardedJedis == null) {
	        	throw new Exception("连接redis服务器出现错误");
	        }
	        String key =shardedJedis.get("regulationId" + regulationId);
	        if(key==null){
	        	shardedJedis.set("regulationId" + regulationId, "-");
	        	key="-";
	        }
	        log.info("-------------------key:"+key);
	        final String listkey = "pondList" + key + regulationId;
	        log.info("-------------------listkey:"+listkey);
	        json = shardedJedis.lpop(listkey);
	        log.info("-------------------json:"+json);
			CJPond pond = JSON.parseObject(json, CJPond.class); 
			 log.info("-------------------pond:"+pond);
			if (pond!=null) {
				log.info("-------------------pond.getPrizeId():"+pond.getPrizeId()+"------pond.getRegulationId():"+pond.getRegulationId());
			}
			if(pond != null) return pond;
			new Thread(new Runnable() {
				@Override
				public void run() {
					lock.lock();
					// TODO Auto-generated method stub
					ShardedJedis shardedJedis = null;
					List<CJPond> list = null;
					try{
						list =sweepstakeMapper.getPondList(regulationId,5000);
						log.info("----------------Thread-list"+list);
						shardedJedis= redisDataSource.getRedisClient();
						log.info("----------------run--shardedJedis:"+shardedJedis);
				        for(int i=0,l=list.size();i<l;i++){
				        	CJPond pond = list.get(i);
				        	shardedJedis.rpush(listkey,JSON.toJSONString(pond));
				        }
				        sweepstakeMapper.deletePonds(regulationId,5000);
					}catch(Exception e){
						e.printStackTrace();
						if(!list.isEmpty()){
							for(int i=0,l=list.size();i<l;i++){
					        	CJPond pond = list.get(i);
					        	shardedJedis.lrem(listkey,-1,JSON.toJSONString(pond));
					        }
						}
					}finally {
						lock.unlock();
						redisDataSource.returnResource(shardedJedis);	
			        }
				}
			}).start();
			if("-".equals(key)){
				key = "+";
			}else{
				key = "-";
			}
			shardedJedis.set("regulationId" + regulationId, key);
			json = shardedJedis.lpop("pondList" + key + regulationId);
			log.info("--------------------json:"+json);
			pond = JSON.parseObject(json, CJPond.class); 
			log.info("---------------------pond:"+pond);
			if (pond!=null) {
				log.info("-------------final------pond.getPrizeId():"+pond.getPrizeId()+"------pond.getRegulationId():"+pond.getRegulationId());
			}
			if(pond != null) return pond;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}finally {
			redisDataSource.returnResource(shardedJedis);	
        }
		return null;
	}

	/**
	 * 抽奖
	 */
	@Transactional
	@Override
	public Map<String, Object> sweepstake(Map<String, Object> map)throws Exception {
		Long regulationId = Long.valueOf(String.valueOf(map.get("regulationId")));//规则id
		Long activiteId = Long.valueOf(String.valueOf(map.get("activiteId")));//活动id
        Long memberId = Long.valueOf(String.valueOf(map.get("memberId")));//用户id	
        String phone = (String)map.get("phone");//手机号
        String boundPhone = (String)map.get("boundPhone");//注册用户绑定手机号
        CJActivite activite = (CJActivite)map.get("activite");//活动信息
        Map rmap = new TreeMap<String, Object>();
        CJPond pond = getPond(regulationId);//获取池
        log.info("-----------------pond:"+pond);
        if (pond!=null) {
        	log.info("-----------------pond:"+pond+"---pond.getPrizeId():"+pond.getPrizeId()+"----pond.getRegulationId():"+pond.getRegulationId());
		}
        if (pond==null) {
        	rmap.put("code", Message.CODE_CJ_NULLPOND_ERROR);
        	rmap.put("message","您来晚了，活动已结束");
        	return rmap;
		}
 		
 		//更新用户可用抽奖机会
 		if (memberId!=null&&memberId!=0L) {
// 			sweepstakeMapper.updateMemberCJTimeByMemberId(memberId);
 			int updateCount = memberService.updateMemberCJTimeByMemberId(memberId,activiteId);
 			if(updateCount<=0){
 				rmap.put("code", Message.CODE_CJ_ERROR);
 	        	rmap.put("message","您今天已经没有抽奖机会");
 				return rmap;
 			}
 			
		}
 		
 		//红包字典code
 		String code = getDictionaryCodeByName("红包");
 		log.info("红包code:"+code);
 		//奖品具体信息
 		CJRecord record = new CJRecord();
 		CJPrize prize =new CJPrize();
 		RegulationAndPrize regulationAndPrize = new RegulationAndPrize();
 		if (pond.getPrizeId()!=null) {
 			String prizeId = Base64.getFromBASE64(pond.getPrizeId());
 			if (prizeId!=null&&!"no".equals(prizeId)) {
 				Long pId = Long.valueOf(prizeId);
 	 			Map rpmap = new HashMap<String, Object>();
 	 			rpmap.put("pId", pId);
 	 			rpmap.put("rId", regulationId);
 	 			log.info("pid:"+pId+"    rid:"+regulationId);
 	 			regulationAndPrize = sweepstakeMapper.getRegulationAndPrize(rpmap);
 	 	 		log.info("regulationAndPrize  ========"+regulationAndPrize);
 	 	 		//如果抽中的是红包 typeCode=10002001为红包奖品
 	 	 		if (regulationAndPrize!=null&&code.equals(regulationAndPrize.getTypeCode())) {
 	 	 			record.setStatus("0");
 				}else {
 					record.setStatus("1");
 				}
 	 	 		String prizeName = regulationAndPrize.getAmount()==null?regulationAndPrize.getPrizeName():regulationAndPrize.getAmount().toString().concat("元").concat(regulationAndPrize.getPrizeName());
 	 	 		if(boundPhone!=null){//老用户发站内信和短信，新用户不发
	 	 	 		if (regulationAndPrize!=null) {//中奖给用户发站内信和短信   （短信暂缓）
	 	 	 			if (activite!=null) {
	 	 	 				// 短信模板 + 奖品名称处理
							String messageTemplate = activite.getMessageTemplate() == null ? "": activite.getMessageTemplate();
							String content = "";
							if(messageTemplate!=null){
								content = messageTemplate.replace("XX", prizeName);
								log.info("短信和站内信内容为 ： content = " + content);
							}
							if (activite.getIsZ() != null && activite.getIsZ() == 1) {// 是否发站内信，0不发，1发
								UserMessage message = new UserMessage();
								message.setUseriId(String.valueOf(memberId));
								message.setTitle("系统消息");
								message.setContent(content);
								message.setType("1");
								message.setReadStatus(0);
								sweepstakeMapper.addUserMessage(message);
							}
							if (activite.getIsM() != null&& activite.getIsM() == 1) {// 是否发短信，0不发，1发
//								HttpClient.send(phone, content, "", "", "");
								HttpClient.businSms(null, SmsFromType.APPSWEEPSTAKE.getCode(), phone, content, null);//抽奖短信01005
							}
						}
	 				}
 	 	 		}
 	 	 		record.setDetail(prizeName);
			}else {
				record.setDetail("未中奖");
				prize.setName("未中奖");
				prize.setPrizeLevel("0");
			}
		}else {
			record.setDetail("未中奖");
			prize.setName("未中奖");
			prize.setPrizeLevel("0");
		}
 		log.info("-----------prize.getPrizeLevel()-----------0001:"+prize.getPrizeLevel());
 		if (memberId!=0L) {//已注册
			record.setUserId(memberId);
			if (StringUtil.isEmpty(record.getStatus())) {
				record.setStatus("1");
			}
			record.setPhone("");
		}else {
			record.setPhone(phone);
			record.setStatus("0");
			record.setUserId(memberId); 
		}
 		
 		record.setCjAid(activiteId);
 		record.setPrizeId(pond.getPrizeId()==null?"":pond.getPrizeId());
 		record.setSerialNumber(String.valueOf(pond.getId()));
 		record.setRegulationId(regulationId);
 		int random = (int)((Math.random()*9+1)*10000000);
 		String randomMd5 = new MD5().getMD5ofStr(String.valueOf(System.currentTimeMillis()).concat(String.valueOf(random)));
 		record.setRandomMd5(randomMd5);
 		sweepstakeMapper.addCjRecord(record);
 		Long rId = record.getId();
	 	log.info("添加record 纪录id========="+rId);	
 		if (regulationAndPrize!=null) {
 			//奖品信息
 			prize.setPrizeLevel(regulationAndPrize.getPrizeLevel()==null?"0":regulationAndPrize.getPrizeLevel());//奖品级别
 			prize.setAmount(regulationAndPrize.getAmount());//奖品面额
 			prize.setName(regulationAndPrize.getPrizeName());//奖品名称
 			log.info("-----------prize.getPrizeLevel():-----------0002:"+prize.getPrizeLevel());
 			if(boundPhone!=null){//老用户更新中奖记录，发红包
 				log.info("boundPhone不为空，发红包");
	 			String cache_key = "EBANK_APP_CHOUJIANG_RECORD_LIST_AID_"+activiteId;
	 			List<CJRecord> rList = (List<CJRecord>) memcachedClient.get(cache_key);
	 			if (rList!=null&&rList.size()>0) {
					if(record!=null&&!"0".equals(prize.getPrizeLevel())) {
						CJRecord cord = new CJRecord();
						cord.setCjDate(new Date());
						cord.setDetail(record.getDetail());
						if (StringUtil.isEmpty(boundPhone)&&StringUtil.isNotEmpty(record.getPhone())) {
							String phone1 = record.getPhone().substring(0, record.getPhone().length()-8).concat("****").concat(record.getPhone().substring(record.getPhone().length()-4,record.getPhone().length()));
							cord.setBoundPhone(phone1);
						}else if (StringUtil.isNotEmpty(boundPhone)) {
							String phone1 = boundPhone.substring(0, boundPhone.length()-8).concat("****").concat(boundPhone.substring(boundPhone.length()-4,boundPhone.length()));
							cord.setBoundPhone(phone1);
						}
						if (rList.size()==100) {
							rList.remove(rList.size()-1);
						}
						rList.add(0,cord);
						boolean flag = memcachedClient.set(cache_key, rList,ConstantUtil.INSTANCE.getWinRecordListCacheTime());
						if (flag) {
							log.info("**********获奖记录加入缓存("+ConstantUtil.INSTANCE.getWinRecordListCacheTime()+"s)************");
						}
					}
				}
	 			//抽中的是红包，直接派发红包
	 			log.info("redMap=============code:"+code+" regulationAndPrize.getTypeCode():"+regulationAndPrize.getTypeCode());
	 			if (code.equals(regulationAndPrize.getTypeCode())&&memberId!=0L&&regulationAndPrize.getCampaignId()!=null) {
	 				Map redMap = new HashMap<String, Object>();
	 				redMap.put("regulationPrizeId", regulationAndPrize.getRegulationPrizeId());
	 				redMap.put("idNo", rId);
	 				redMap.put("memberId",memberId);
	 				redMap.put("phone",phone);
	 				redMap.put("campaignId",regulationAndPrize.getCampaignId());
	 				rmap.put("redMap", redMap);
	 			}
 			}
		}
 		
		rmap.put("recordno", randomMd5);
 		rmap.put("prize", prize);
 		log.info("-----------prize:"+prize.getPrizeLevel()+"----------------prizeName:"+prize.getName());
		return rmap;
	}

	@Override
	public void updateMemberCJTimeByMemberId(Long memberId) throws Exception {
		// TODO Auto-generated method stub
		sweepstakeMapper.updateMemberCJTimeByMemberId(memberId);
	}

	@Override
	public Integer getPondCountByRid(Long rId) throws Exception {
		// TODO Auto-generated method stub
		return sweepstakeMapper.getPondCountByRid(rId);
	}

	@Override
	public RegulationAndPrize getRegulationAndPrize(Map<String, Object> map)throws Exception {
		/*Long pId = Long.valueOf(String.valueOf(map.get("pId")));
		Long regulationId = Long.valueOf(String.valueOf(map.get("rId")));
		String rpkey = "CJ_RegulationAndPrize_prizeId_"+pId+"_regulationId_"+regulationId;
		RegulationAndPrize regulationAndPrize = (RegulationAndPrize)memcachedClient.get(rpkey);
 		if (regulationAndPrize==null) {
 			Map rpmap = new HashMap<String, Object>();
 			rpmap.put("pId", pId);
 			rpmap.put("rId", regulationId);
 			regulationAndPrize = sweepstakeMapper.getRegulationAndPrize(rpmap);
 			if (regulationAndPrize!=null) {
				boolean flag = memcachedClient.set(rpkey, regulationAndPrize,30 * 60);
				if (flag) {
					log.info("--------------规则关联和奖品具体信息加入缓存(30分钟)-------------");
				}
			}
		}*/
		return sweepstakeMapper.getRegulationAndPrize(map);
	}

	@Override
	public String getDictionaryCodeByName(String name) throws Exception {
		String dckey = "DictionaryCodeByName_"+name;
		String code  = (String) memcachedClient.get(dckey);
		if (code==null) {
			code = sweepstakeMapper.getDictionaryCodeByName(name);
			boolean flag = memcachedClient.set(dckey, code ,ConstantUtil.INSTANCE.getDictionaryCacheTime());
			if (flag) {
				log.info("------------字典"+name+"code加入缓存("+ConstantUtil.INSTANCE.getDictionaryCacheTime()+"s)------------");
			}
		}
		return code==null?"":code;
	}

	@Override
	public List<CJPond> getPondList(long regulationId,int count) {
		// TODO Auto-generated method stub
		return sweepstakeMapper.getPondList(regulationId,count);
	}

	@Override
	public void clearPonds(long regulationId) {
		// TODO Auto-generated method stub
		sweepstakeMapper.clearPonds(regulationId);
	}

	@Override
	public CJActivite getUesbleActivite() throws Exception {
		//查询活动信息
 		String key = "CJ_ACTIVITE_INFO_BY_UESBLE";
 		CJActivite activite = (CJActivite)memcachedClient.get(key);
 		if (activite==null) {
 			activite = sweepstakeMapper.getUesbleActivite();
 			if (activite!=null) {
				boolean flag = memcachedClient.set(key, activite,ConstantUtil.INSTANCE.getActiviteCacheTime());
				if (flag) {
					log.info("--------------获取进行中的活动信息加入缓存("+ConstantUtil.INSTANCE.getActiviteCacheTime()+"s)-------------");
				}
			}
		}
		return activite;
	}

	public UsePacketsWriteService getUsePacketsWriteService() {
		return usePacketsWriteService;
	}

	public void setUsePacketsWriteService(
			UsePacketsWriteService usePacketsWriteService) {
		this.usePacketsWriteService = usePacketsWriteService;
	}

	@Override
	public Map updateActivity(long aid) {
		Map jsonMap = new TreeMap();
		String key = "CJ_ACTIVITE_INFO_BYID_"+aid;
		CJActivite activite  = sweepstakeMapper.getActiviteById(aid);
		if (activite!=null) {
			boolean flag = memcachedClient.set(key, activite,ConstantUtil.INSTANCE.getActiviteCacheTime());
			if (flag) {
				log.info("--------------sweepstake更新活动信息加入缓存("+ConstantUtil.INSTANCE.getActiviteCacheTime()+"s)-------------");
			}
		}else{
			jsonMap.put("code", Message.CODE_ERROR);
			jsonMap.put("message","活动不存在");
		}
		jsonMap.put("code", Message.CODE_SUCCESS);
		jsonMap.put("message",Message.MSG_SUCCESS);
		return jsonMap;
	}

	@Override
	public void sendMessage(Long memberId,CJActivite activite,String type) throws Exception {
		MemberDO member = memberService.getMemberDOById(memberId);
		String phone = null;
		String content = null;
		if(member !=null){
			phone = member.getBoundPhone();
		}
		
		if("1".equals(type)){//投资
			content = "恭喜您！通过投资获得1次抽奖机会~";
		}else if("2".equals(type)){//分享
			content = "恭喜您！通过分享获得1次抽奖机会~";
		}
	
		log.info("短信和站内信内容为 ： content = " + content);
		if (activite.getIsZ() != null && activite.getIsZ() == 1) {// 是否发站内信，0不发，1发
			UserMessage message = new UserMessage();
			message.setUseriId(String.valueOf(memberId));
			message.setTitle("系统消息");
			message.setContent(content);
			message.setType("1");
			message.setReadStatus(0);
			sweepstakeMapper.addUserMessage(message);
		}
		if (activite.getIsM() != null&& activite.getIsM() == 1) {// 是否发短信，0不发，1发
			HttpClient.businSms(null, SmsFromType.APPSWEEPSTAKE.getCode(), phone, content, null);//抽奖短信01005
		}	
	}

	@Override
	public List<CJActivite> getAllUsedZpActivity() {
		
		return sweepstakeMapper.getAllUsedZpActivity();
	}
	
}
