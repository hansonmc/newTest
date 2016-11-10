package com.jlc.api.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlc.api.cache.memcached.IMemcachedClient;
import com.jlc.api.dao.MemberMapper;
import com.jlc.api.dao.SweepstakeMapper;
import com.jlc.api.dto.CJActivite;
import com.jlc.api.dto.CJActiviteConditionTime;
import com.jlc.api.dto.CjTimes;
import com.jlc.api.dto.MemberDO;
import com.jlc.api.service.MemberService;
import com.jlc.api.utils.ConstantUtil;
import com.jlc.api.utils.DateUtils;
import com.jlc.api.utils.StringUtil;

@Service("memberService")
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private SweepstakeMapper sweepstakeMapper;
	
	@Autowired
	private IMemcachedClient memcachedClient;
	
	
	private static Logger log = Logger.getLogger(MemberServiceImpl.class);
	@Override
	public MemberDO getMemberDOById(Long userId) {
		String key = "CJ_MEMBERINFO_BYID"+userId;
		MemberDO member = (MemberDO)memcachedClient.get(key);
 		if (member==null) {
 			member = memberMapper.getMemberDOById(userId);
 			if (member!=null) {
				memcachedClient.set(key, member,ConstantUtil.INSTANCE.getMemberInfoCacheTime());
			}
		}
		return member;
	}

	@Override
	public Integer getMemberInvestCount(Long userId) {
		// TODO Auto-generated method stub
		return memberMapper.getMemberInvestCount(userId);
	}

	@Override
	public Integer getMemberUusableCJTime(Long userId,Long aId) {
		// TODO Auto-generated method stub
//		return memberMapper.getMemberUusableCJTime(userId);
		return getMemberZpGetTime(userId, 0,aId);
	}
	
	
	/*//查询该活动的条件
	private CJActiviteConditions getActiviteConditions(Map<String, Object> map){
		//查询该活动的条件
		String id = String.valueOf(map.get("aId"));//活动id
		String type = String.valueOf(map.get("type"));//类型
		String key = "CJ_ACTIVITECONDITIONS_BYAID"+id+"_TYPE_"+type;
		CJActiviteConditions cJActiviteConditions = (CJActiviteConditions)memcachedClient.get(key);
 		if (cJActiviteConditions==null) {
 			cJActiviteConditions = memberMapper.getActiviteConditions(map);
 			if (cJActiviteConditions!=null) {
				memcachedClient.set(key, cJActiviteConditions,ConstantUtil.INSTANCE.getActiviteConditionsCacheTime());
			}
		}
        return cJActiviteConditions;
	}*/
	
	/**
	 * 查询该活动的条件
	 * 
	 */
	private CJActiviteConditionTime getActiviteConditionTime(Long aId){
		String tkey = "CJ_CJACTIVITECONDITIONTIME_BYAID_"+aId;
		CJActiviteConditionTime activiteConditionTime = (CJActiviteConditionTime)memcachedClient.get(tkey);
		if (activiteConditionTime==null) {
			activiteConditionTime = memberMapper.getActiviteConditionsTime(aId);
			memcachedClient.set(tkey, activiteConditionTime,ConstantUtil.INSTANCE.getActiviteConditionsCacheTime());
		}
		return activiteConditionTime;
	}
	
	/**
	 * 获取用户抽奖机会
	 * @param memberId
	 * @param type  = 0:可用抽奖次数  ，1：分享获得次数 ，2：投资获得次数
	 * @return
	 */
	public Integer getMemberZpGetTime(Long memberId,Integer type,Long aId){
		//type = 0:可用抽奖次数  ，1：分享获得次数 ，2：投资获得次数
		//用户获得抽奖次数
        Integer time = 0;
        
        String key = "CJ_GETMEMBERCJTIME_BYMEMBERID_"+memberId;
        CjTimes cjtime = (CjTimes)memcachedClient.get(key);
 		if (cjtime==null) {
 			cjtime = memberMapper.getCjTimeByMemberId(memberId);
 			if (cjtime!=null) {
				memcachedClient.set(key, cjtime,DateUtils.getNextZeroHourTime());
			}
		}
 		
		
 		if (cjtime!=null) {
			if (type==0) {//0：可用机会
				time = cjtime.getZpTimes()==null?0:cjtime.getZpTimes();
			}else if (type==1) {//1：分享获得
				time = cjtime.getZpGetShareTimes()==null?0:cjtime.getZpGetShareTimes();
			}else if (type==2) {//2：投资获得
				time = cjtime.getZpGetTimes()==null?0:cjtime.getZpGetTimes();
	        }
		}else {
			CJActiviteConditionTime activiteConditionTime = getActiviteConditionTime(aId);
			if (activiteConditionTime==null) {
				return 0;
			}
			Integer freetime = activiteConditionTime.getFreeTime()==null?0:activiteConditionTime.getFreeTime();
			if (type==0) {//0：可用机会
				time = freetime;
			}else{
				time = 0;
	        }
		}
		return time;
	}
	
	/**
	 * 更新用户抽奖机会
	 * @param memberId
	 * @param type = -1：使用  ，1：分享获得 ，2：投资获得
	 */
	public int updateMemberZpGetTime(Long memberId,Integer type,Long aId){
		//type = -1：使用  ，1：分享获得 ，2：投资获得
		int updateCount = 0;
		String key = "CJ_GETMEMBERCJTIME_BYMEMBERID_"+memberId;
        CjTimes cjtime  = (CjTimes)memcachedClient.get(key);
 		if (cjtime==null) {
 			cjtime = memberMapper.getCjTimeByMemberId(memberId);
 			if (cjtime!=null) {
				memcachedClient.set(key, cjtime,DateUtils.getNextZeroHourTime());
			}
		}
 		CJActiviteConditionTime activiteConditionTime = getActiviteConditionTime(aId);
		if (activiteConditionTime==null) {
			return 0;
		}
 		Integer freetime = activiteConditionTime.getFreeTime()==null?0:activiteConditionTime.getFreeTime();
 		
 		
 		List<CJActivite> activities= sweepstakeMapper.getZpActivityId();//得到转盘项目
		int count = 0;
		Long latestId = 0l;
		if(activities!=null && activities.size()>0){//有两个大转盘，获取最新转盘(六一转盘)
			latestId = activities.get(0).getId();
			log.info("lastaId:=============="+latestId+"  aId:"+aId);
		}
		
 		if (cjtime!=null) {//当天
// 			Date zpdate = DateUtils.dayFormat(cjtime.getZpDate(), "yyyy-MM-dd");
			Date thisdate = DateUtils.dayFormat(new Date(), "yyyy-MM-dd");
	 		
			cjtime.setId(memberId);
			cjtime.setZpDate(thisdate);
			if (type==-1) {//使用
				if(aId.equals(latestId)){//六一转盘
					count = 1;
				}
				if (cjtime.getZpTimes()!=null&&cjtime.getZpTimes()==0) {
					cjtime.setZpTimes(cjtime.getZpTimes()==null?0:(cjtime.getZpTimes()-count));
				}else {
					cjtime.setZpTimes(cjtime.getZpTimes()==null?0:(cjtime.getZpTimes()-count));
				}
			}else if (type==1) {//1：分享获得
				if(aId.equals(latestId)){//六一转盘
					cjtime.setZpTimes((cjtime.getZpTimes()==null?0:cjtime.getZpTimes())+1);
					cjtime.setZpGetShareTimes((cjtime.getZpGetShareTimes()==null?0:cjtime.getZpGetShareTimes())+1);
				}
				
			}else if (type==2) {//2：投资获得
				cjtime.setZpTimes((cjtime.getZpTimes()==null?0:cjtime.getZpTimes())+1);
				cjtime.setZpGetTimes((cjtime.getZpGetTimes()==null?0:cjtime.getZpGetTimes())+1);
			}
			
			updateCount = memberMapper.updateMemberCJTime(cjtime);
			memcachedClient.set(key, cjtime,DateUtils.getNextZeroHourTime());

		}else {//隔天
			
			cjtime = new CjTimes();
			if (type==-1) {//使用
				if(aId.equals(latestId)){//六一转盘
					log.info("六一活动count =1");
					count = 1;
				}
				if(count>0){
					cjtime.setZpTimes(freetime<count?0:(freetime-count));
				}else{
					cjtime.setZpTimes(freetime);
				}
				cjtime.setZpGetShareTimes(0);
				cjtime.setZpGetTimes(0);
			}else if (type==1) {//1：分享获得
				if(aId.equals(latestId)){//六一转盘
					cjtime.setZpTimes(freetime+1);
					cjtime.setZpGetShareTimes(1);
				}else{
					cjtime.setZpTimes(freetime);
					cjtime.setZpGetShareTimes(0);
				}
				
				cjtime.setZpGetTimes(0);
			}else if (type==2) {//2：投资获得
				
				cjtime.setZpTimes(freetime+1);
				cjtime.setZpGetShareTimes(0);
				cjtime.setZpGetTimes(1);
			}
			cjtime.setId(memberId);
			updateCount = memberMapper.addCjTime(cjtime);
			memcachedClient.set(key, cjtime,DateUtils.getNextZeroHourTime());

			
		}
 		return updateCount;

	}

	
	@Override
	public boolean giveCJChanceByInvest(Long memberId,BigDecimal amount,Long aId) {
		boolean isGive = false;
        
       //查询该活动的条件
		CJActiviteConditionTime activiteConditionTime = getActiviteConditionTime(aId);
		if (activiteConditionTime==null) {
			return isGive;
		}
		log.info(aId+"活动  giveCJChanceByInvest 投资可送次数："+activiteConditionTime.getInvestTime());
		//用户通过投资获得抽奖次数
        Integer invest_time = getMemberZpGetTime(memberId, 2,aId);
        log.info("投资已活得次数invest_time："+invest_time);
        Integer investtime = activiteConditionTime.getInvestTime()==null?0:activiteConditionTime.getInvestTime();
       
        if(invest_time!=null && invest_time>=0 
        		&& invest_time < investtime && (amount.compareTo(activiteConditionTime.getMinAmount()) >= 0 )) {
			//更新用户可用抽奖次数已经投资获得次数
			updateMemberZpGetTime(memberId, 2,aId);
			isGive = true;
		}
		return isGive;
	}

	@Override
	public boolean giveCJChanceByShare(Long memberId, Long aId) {
		boolean isGive = false;
		//查询该活动的条件
		CJActiviteConditionTime activiteConditionTime = getActiviteConditionTime(aId);
		if (activiteConditionTime==null) {
			return isGive;
		}
		log.info(aId+"活动  giveCJChanceByShare 分享可送次数："+activiteConditionTime.getShareTime());
		//查询用户分享获得转盘活动投资次数
		Integer share_time = getMemberZpGetTime(memberId, 1,aId);
		log.info("已分享次数share_time："+share_time);
		//活动限制分享次数大于用户已分享次数
		if(share_time!=null&&activiteConditionTime.getShareTime().compareTo(share_time)>0) {
			//修改指定用户抽奖次数
			updateMemberZpGetTime(memberId, 1,aId);
			isGive = true;
		}
		return isGive;
	}

	@Override
	public int updateMemberCJTimeByMemberId(Long memberId,Long aId) {
		return updateMemberZpGetTime(memberId, -1,aId);
	}

	@Override
	public String getDialogUrl() {
		String key  = "JLCSERVICE-API-GETDIALOGURL";
		String url = (String)memcachedClient.get(key);
		if (StringUtil.isEmpty(url)) {
			url = memberMapper.getDialogUrl();
			memcachedClient.set(key, url,ConstantUtil.INSTANCE.getDialogUrlCacheTime());
		}
		return url;
	}

	
}
