package com.jlc.api.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.jlc.api.constants.Constants;
import com.jlc.api.constants.Message;
import com.jlc.api.dto.CJActivite;
import com.jlc.api.dto.CJRecord;
import com.jlc.api.dto.CJRegulation;
import com.jlc.api.dto.MemberDO;
import com.jlc.api.service.MemberService;
import com.jlc.api.service.SweepstakeService;
import com.jlc.api.utils.JedisLock;
import com.jlc.api.utils.RedisUtil;
import com.jlc.api.utils.StringUtil;
import com.jlc.api.utils.TokenCheckUtil;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/sweepstake")
@ParentPackage("json-default")
@Results( { @Result(name = ActionSupport.SUCCESS, type = "json"),
@Result(name = ActionSupport.ERROR, type = "json") })
@SuppressWarnings({ "unchecked", "serial" })
public class SweepstakeAction  extends BaseAction{
	
	@Autowired
	private SweepstakeService sweepstakeService;
	@Autowired
	private MemberService memberService;
	
	private JSON jsonResult;//返回结果
	// redis分布式事务锁，加锁时间
    private int timeoutMsecs = 100000;
    // 过期时间
    private int expireMsecs =  60000000;
	private static Logger log = Logger.getLogger(SweepstakeAction.class);
	/**
	 * 更新缓存活动信息(后台调用，暂时弃用，改为当奖池放入redis后，再更新活动绑定规则信息)
	 */
	@Action(value="updateActivity", results={@Result(type="json",
			params={"root","jsonResult","contentType","application/json;charset=utf-8"} )})
	public String updateActivity(){
		Map jsonMap = new TreeMap();
		/*
		HttpServletRequest request = ServletActionContext.getRequest();
		String aid = request.getParameter("aId");//活动id
		try{
			if(StringUtil.isNotEmpty(aid)){
				jsonMap = sweepstakeService.updateActivity(Long.parseLong(aid));
			}else{
				jsonMap.put("code", Message.CODE_ERROR);
	 			jsonMap.put("message",Message.MSG_MISS_PARAM);
			}
		}catch(Exception e){
			log.error("更新缓存活动信息失败"+e.getMessage());
			jsonMap.put("code", Message.CODE_ERROR);
 			jsonMap.put("message",Message.MSG_EMPTY_TOKEN_ERROR);
			
		}
		
		jsonResult = (JSON) JSON.toJSON(jsonMap);
		return SUCCESS;*/
		
		jsonMap.put("code", Message.CODE_SUCCESS);
		jsonMap.put("message",Message.MSG_SUCCESS);
		jsonResult = (JSON) JSON.toJSON(jsonMap);
		return SUCCESS;
	}
	
	/**
	 * 生成奖池
	 * @throws Exception 
	 * @throws NumberFormatException 
	 * 
	 */
	@Action(value="createPond", results={@Result(type="json",
			params={"root","jsonResult","contentType","application/json;charset=utf-8"} )})
	public String createPond() {
		Map jsonMap = new TreeMap();
		try{
			//通过链接获取参数 规则id
	 		HttpServletRequest request = ServletActionContext.getRequest();
			String rid = request.getParameter("rid");//规则id
	 		log.info("=====生成奖池rid:"+rid);
			if (StringUtil.isEmpty(rid)) {
	 			jsonMap.put("code", Message.CODE_ERROR);
	 			jsonMap.put("message",Message.MSG_PARAM_ILLEGAL);
			}else{
				jsonMap = sweepstakeService.createPond(Long.valueOf(rid));
			}
		}catch(Exception e){
			e.printStackTrace();
			jsonMap.put("code", Message.CODE_ERROR);
 			jsonMap.put("message",Message.MSG_EMPTY_TOKEN_ERROR);
		}
		jsonResult = (JSON) JSON.toJSON(jsonMap);
 		return SUCCESS;
 	}
	/**
	 * 抽奖
	 * @throws Exception 
	 * 
	 */
	@Action(value="sweepstake", results={@Result(type="json",
			params={"root","jsonResult","contentType","application/json;charset=utf-8"} )})
	public String sweepstake(){
		Long t1= System.currentTimeMillis();
		Map jsonMap = new TreeMap();
		String phone = "";
		String userId = "";
		String userName = "";
		JedisLock lock = null;
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			String aid = request.getParameter("aId");//活动id
			String token = request.getParameter("token");//token
			phone = request.getParameter("phone");//抽奖手机号
			
			//已登录用户不传手机号
			if(StringUtil.isEmpty(phone)&& StringUtil.isNotEmpty(token)){
				userId = token.split(",")[1];
				MemberDO member = memberService.getMemberDOById(Long.parseLong(userId));
				phone = member.getBoundPhone();
				userName =member.getName();
			}
			
			// 分布式事务锁
			if(StringUtil.isNotEmpty(phone)){//手机号不空，用手机号做锁
				lock = new JedisLock("sweepstake_phone_"+phone, timeoutMsecs, expireMsecs);
			}else if(StringUtil.isEmpty(phone) && StringUtil.isNotEmpty(userId)){//手机号为空，userId不为空
				lock = new JedisLock("sweepstake_userId_"+userId, timeoutMsecs, expireMsecs);
			}
            
              
            while (!lock.acquire(RedisUtil.getJedis())) {
                 Thread.sleep(1000);
            }
            
			String boundPhone = null;//注册用户绑定手机号
	 		Map rmap = new HashMap<String, Object>();
	 		if (StringUtil.isEmpty(aid)) {
	 			jsonMap.put("code", Message.CODE_ERROR);
	 			jsonMap.put("message",Message.MSG_PARAM_ILLEGAL);
	 			jsonResult = (JSON) JSON.toJSON(jsonMap);
	 			return SUCCESS;
			}
	 		if(StringUtil.isNotEmpty(phone)){
				if(!phone.matches(Constants.phoneRegex)){
					jsonMap.put("code", Message.CODE_ERROR);
		 			jsonMap.put("message",Message.MSG_PARAM_ILLEGAL);
		 			jsonResult = (JSON) JSON.toJSON(jsonMap);
		 			return SUCCESS;
				}
			}
	 		//验证token
	 		boolean is_login = false;//标记用户是否登陆
	 		Long memberId = 0L;//用户id
	 		
	 		
	 		if (StringUtil.isNotEmpty(token)) {//登陆用户抽奖
	 			if (TokenCheckUtil.checkToken(token)) {
	 				memberId = Long.valueOf(token.split(",")[1]);
	 	 			is_login = true;
	 	 			log.info("token正确==============================1");
				}else{
					if(StringUtil.isNotEmpty(phone)){	//未登陆用户抽奖
						String cMemberId = sweepstakeService.checkPhone(phone);
						if (StringUtil.isNotEmpty(cMemberId)) {//已注册  regcode:-1:未注册  0：登陆  1：已注册
							memberId = Long.valueOf(cMemberId);
							rmap.put("regcode", "1");
							log.info("已注册==============================2");
						}else {//未注册
							rmap.put("regcode", "-1");
							log.info("未注册==============================3");
						}
					}
//		 			jsonMap.put("code", Message.CODE_TIMEOUT);
//		 			jsonMap.put("message",Message.MSG_TIME_OUT);
//		 			jsonResult = (JSON) JSON.toJSON(jsonMap);
//		 			return SUCCESS;
				}
			}else{
				if(StringUtil.isNotEmpty(phone)){	//未登陆用户抽奖
						String cMemberId = sweepstakeService.checkPhone(phone);
						if (StringUtil.isNotEmpty(cMemberId)) {//已注册  regcode:-1:未注册  0：登陆  1：已注册
							memberId = Long.valueOf(cMemberId);
							rmap.put("regcode", "1");
							log.info("已注册==============================2");
						}else {//未注册
							rmap.put("regcode", "-1");
							log.info("未注册==============================3");
						}
					}
			}
	 		
	 		
	 		Long activiteId = Long.valueOf(aid);
	 		//查询活动信息
	 		CJActivite activite = sweepstakeService.getActiviteById(activiteId);
	 		if (activite==null) {
	 			log.info(activiteId+"活动为空");
	 			jsonMap.put("code", Message.CODE_ERROR);
	 			jsonMap.put("message","活动不存在或已结束");
	 			jsonResult = (JSON) JSON.toJSON(jsonMap);
	 			return SUCCESS;
			}
	 		log.info("活动存在==============================4");
	 		if (memberId!=0L) {//已注册用户
		 		MemberDO memberDO =memberService.getMemberDOById(memberId);//注册用户信息
		 		if (memberDO==null) {
		 			jsonMap.put("code", Message.CODE_TIMEOUT);
		 			jsonMap.put("message",Message.MSG_TIME_OUT);
		 			jsonResult = (JSON) JSON.toJSON(jsonMap);
		 			return SUCCESS;
				}
		 		boundPhone = memberDO.getBoundPhone();
		 		//用户类型(1:投资方,2:借款方)
		 		Integer type = memberDO.getType();
		 		if (type==null||type!=1) {
		 			jsonMap.put("code", Message.CODE_CJ_ERROR);
		 			jsonMap.put("message","您今天已经没有抽奖机会");
		 			jsonResult = (JSON) JSON.toJSON(jsonMap);
		 			return SUCCESS;
				}
		 		Integer time = memberService.getMemberUusableCJTime(memberId,activiteId);
		 		if (time==null||(time!=null&&time<=0)) {
		 			jsonMap.put("code", Message.CODE_CJ_ERROR);
		 			jsonMap.put("message","您今天已经没有抽奖机会");
		 			jsonResult = (JSON) JSON.toJSON(jsonMap);
		 			return SUCCESS;
				}
	 		}
	 		log.info(is_login+"===============================5"+phone);
	 		//具体抽奖规则
	 		Long regulationId = 0L;
	 		if(is_login){//登陆用户抽奖规则
	 			rmap.put("regcode", "0");
				//用户投资次数
				Integer invests = memberService.getMemberInvestCount(memberId);
				if (invests>0) {//登陆已投资用户抽奖规则
					regulationId = activite.getRegulation1Id();
				}else {//未投资用户抽奖规则
					regulationId = activite.getRegulation3Id();
				}
	 		}else if(StringUtil.isNotEmpty(phone)){
	 			regulationId = activite.getRegulation2Id();
	 		}
	 		log.info("regulationId:"+regulationId);
	 		//获取规则信息
	 		CJRegulation regulation = sweepstakeService.getRegulationById(regulationId);
	 		if (regulation==null) {
	 			log.info("regulation空===============================8");
	 			jsonMap.put("code", Message.CODE_ERROR);
	 			jsonMap.put("message","活动已结束");
	 			jsonResult = (JSON) JSON.toJSON(jsonMap);
	 			return SUCCESS;
			}
	 		
	 		//根据规则查询奖池是否已被抽完（改为从缓存中判断）
//	 		Integer pcount = sweepstakeService.getPondCountByRid(regulationId);
//	 		if (pcount<=0) {
//	 			jsonMap.put("code", Message.CODE_CJ_NULLPOND_ERROR);
//	 			jsonMap.put("message","您来晚了，活动已结束");
//	 			jsonResult = (JSON) JSON.toJSON(jsonMap);
//	 			return SUCCESS;
//			}
	 		
	 		//每人当日中奖上限   该选项没有设置（即值为0）默认为无上限
	 		Integer upcount = regulation.getEachAccount()==null?0:regulation.getEachAccount().intValue();
	 		
	 		//当前用户中奖数量
	 		if (memberId!=0L) {//已注册用户
				Map wmap = new HashMap<String, Object>();
		 		wmap.put("rId", regulationId);
		 		wmap.put("memberId", memberId);
		 		Integer wincount = sweepstakeService.getMemberWinCount(wmap);
		 		if (upcount!=0&&upcount<=wincount) {//超出每人当日中奖上限
		 			log.info("=达到上限==============================9");
		 			jsonMap.put("code", Message.CODE_CJ_ERROR);
		 			jsonMap.put("message","您今天已经没有抽奖机会");
		 			jsonResult = (JSON) JSON.toJSON(jsonMap);
		 			return SUCCESS;
				}
			}else {//未注册用户
				Map wmap = new HashMap<String, Object>();
		 		wmap.put("rId", regulationId);
		 		wmap.put("phone", phone);
		 		Integer wincount = sweepstakeService.getMemberWinCount(wmap);
		 		if (upcount!=0&&upcount<=wincount) {//超出每人当日中奖上限
		 			jsonMap.put("code", Message.CODE_CJ_ERROR);
		 			jsonMap.put("message","您今天已经没有抽奖机会");
		 			jsonResult = (JSON) JSON.toJSON(jsonMap);
		 			return SUCCESS;
				}
			}
	 		
	 		
	 		//抽奖关键环节
	 		Map smap = new HashMap<String, Object>();
	 		smap.put("regulationId", regulationId);
	 		smap.put("phone", phone);
	 		smap.put("memberId", memberId);
	 		smap.put("activiteId", activiteId);
	 		smap.put("activite", activite);
	 		if (boundPhone!=null) {
	 			smap.put("boundPhone", boundPhone);
			}
	 		Long t3= System.currentTimeMillis();
	 		Map<String,Object> swmap = sweepstakeService.sweepstake(smap);
	 		log.info("抽奖结束===============================10");
	 		Long t4= System.currentTimeMillis();
	 		log.info("----------------sweepstakeService.sweepstake耗时（毫秒）:"+(t4-t3));
	 		
	 		if (swmap.containsKey("code")) {
	 			log.info("===============================11");
	 			Integer code = Integer.valueOf(String.valueOf(swmap.get("code")));
	 	 		if (code!=null&&Message.CODE_CJ_NULLPOND_ERROR==code) {
	 	 			log.info("----------------您来晚了，活动已结束");
	 	 			jsonMap.put("code", Message.CODE_CJ_NULLPOND_ERROR);
	 	 			jsonMap.put("message","您来晚了，活动已结束");
	 	 			jsonResult = (JSON) JSON.toJSON(jsonMap);
	 	 			return SUCCESS;
	 			}
	 	 		if(code!=null && Message.CODE_CJ_ERROR == code){
	 	 			log.info("----------------您今天已经没有抽奖机会");
	 	 			jsonMap.put("code", Message.CODE_CJ_ERROR);
	 	 			jsonMap.put("message","您今天已经没有抽奖机会");
	 	 			jsonResult = (JSON) JSON.toJSON(jsonMap);
	 	 			return SUCCESS;
	 	 		}
			}
	 		
	 		//抽奖红包，派发红包
	 		Map redMap = (Map)swmap.get("redMap");
	 		log.info("抽奖发红包start");
	 		if (redMap!=null) {
	 			log.info("抽奖发红包 redMap存在");
	 			if(StringUtil.isNotEmpty(userName)){
	 				redMap.put("userName", userName);
	 			}
	 			Long t5= System.currentTimeMillis();
				sweepstakeService.distributeRedPacket(redMap);
				Long t6= System.currentTimeMillis();
				log.info("--------------sweepstakeService.distributeRedPacket派发红包耗时（毫秒）:"+(t6-t5));
				swmap.remove("redMap");
			}
			if(memberId!=0L){//用户是否还有抽奖机会 0：没有机会 1：还有机会
				Map wmap = new HashMap<String, Object>();
				wmap.put("rId", regulationId);
				wmap.put("memberId", memberId);
				Integer wincount = sweepstakeService.getMemberWinCount(wmap);
				if (upcount!=0&&upcount<=wincount) {//超出每人中奖上限
					swmap.put("time",0);
				}else{
					Integer time = memberService.getMemberUusableCJTime(memberId,activiteId);
					swmap.put("time",time>0?1:0);
				}
			}
	 		rmap.putAll(swmap);
	 		jsonMap.put("code", Message.CODE_SUCCESS);
			jsonMap.put("message",Message.MSG_SUCCESS);
			jsonMap.put("result", rmap);
		}catch(Exception e){
			e.printStackTrace();
			jsonMap.put("code", Message.CODE_ERROR);
			jsonMap.put("message",Message.MSG_EMPTY_TOKEN_ERROR);
		}finally{
			if(lock!=null){
				lock.release(RedisUtil.getJedis());
			}
		}
		jsonResult = (JSON) JSON.toJSON(jsonMap);
		Long t2= System.currentTimeMillis();
		log.info("------------------抽奖接口耗时总和（毫秒）："+(t2-t1));
		return SUCCESS;
 	}



	/**
	 * 未注册用户中奖并注册后派发奖品
	 * 游客抽奖，中奖后先在抽奖记录中标记该奖品为暂存未发状态，
	 * 注册后调用该接口为游客派发奖品
	 * @throws Exception 
	 * @throws NumberFormatException 
	 * 
	 */
	@Action(value="distributePrizes", results={@Result(type="json",
			params={"root","jsonResult","contentType","application/json;charset=utf-8"} )})
	public String distributePrizes(){
		Long t1= System.currentTimeMillis();
 		Map jsonMap = new TreeMap();
 		try{
	 		HttpServletRequest request = ServletActionContext.getRequest();
			String token = request.getParameter("token");//token
			String rid = request.getParameter("rId");//抽奖记录id
			String phone = request.getParameter("phone");//手机号
			
			log.info("token:"+token+"rid:"+rid+" phone:"+phone);
	 		if (StringUtil.isEmpty(token)||StringUtil.isEmpty(rid)||StringUtil.isEmpty(phone)) {
	 			jsonMap.put("code", Message.CODE_ERROR);
	 			jsonMap.put("message",Message.MSG_PARAM_ILLEGAL);
	 			jsonResult = (JSON) JSON.toJSON(jsonMap);
	 			return SUCCESS;
			}
	 		
	 		//验证token
	 		
	 		
	 		if (!TokenCheckUtil.checkToken(token)) {
	 			jsonMap.put("code", Message.CODE_ERROR);
	 			jsonMap.put("message",Message.MSG_USER_NOTLOGIN);
	 			jsonResult = (JSON) JSON.toJSON(jsonMap);
	 			return SUCCESS;
			}
	 		
	 		Long memberId = Long.valueOf(token.split(",")[1]);
	 		MemberDO member = memberService.getMemberDOById(memberId);
			String userName =member.getName();
			
	 		Map rmap = new HashMap<String, Object>();
	 		rmap.put("randomMd5", rid);
	 		rmap.put("phone", phone);
	 		CJRecord record  = sweepstakeService.getRecordByRandomMd5AndPhone(rmap);
	 		if (record==null) {
	 			jsonMap.put("code", Message.CODE_ERROR);
	 			jsonMap.put("message","该记录不存在");
	 			jsonResult = (JSON) JSON.toJSON(jsonMap);
	 			return SUCCESS;
			}
	 		
	 		//派发奖品
	 		Map map = new HashMap<String, Object>();
	 		map.put("memberId", memberId);
	 		map.put("randomMd5", rid);
	 		map.put("phone", phone);
	 		map.put("record", record);
	 		log.info("=========发红包=============");
	 		Map redmap = sweepstakeService.distributePrizes(map);
	 		log.info("redmap:"+redmap);
	 		//已注册用户抽中红包后直接派发红包
	 		if (redmap!=null&&redmap.get("idNo")!=null) {
	 			if(StringUtil.isNotEmpty(userName)){
	 				redmap.put("userName", userName);
	 			}
		 	 	sweepstakeService.distributeRedPacket(redmap);
			}
			
	 		jsonMap.put("code", Message.CODE_SUCCESS);
			jsonMap.put("message",Message.MSG_SUCCESS);
 		}catch(Exception e){
 			log.error(e);
			jsonMap.put("code", Message.CODE_ERROR);
			jsonMap.put("message",Message.MSG_EMPTY_TOKEN_ERROR);
		}
		jsonResult = (JSON) JSON.toJSON(jsonMap);
		Long t2= System.currentTimeMillis();
		log.info("----------------未注册用户中奖并注册后派发奖品接口耗时（毫秒）:"+(t2-t1));
		return SUCCESS;
 	}
	
	
	
	
	
	
	/**
	 * 获奖纪录
	 * @throws Exception  
	 * 
	 */
	@Action(value="getWinRecord", results={@Result(type="json",
			params={"root","jsonResult","contentType","application/json;charset=utf-8"} )})
	public String getWinRecord(){
		Long t1= System.currentTimeMillis();
		Map jsonMap = new TreeMap();
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			String aid = request.getParameter("aId");
	 		if (StringUtil.isEmpty(aid)) {
	 			jsonMap.put("code", Message.CODE_ERROR);
	 			jsonMap.put("message",Message.MSG_PARAM_ILLEGAL);
	 			jsonResult = (JSON) JSON.toJSON(jsonMap);
	 			return SUCCESS;
			}
	 		Long activiteId = Long.valueOf(aid);//活动id
			List<CJRecord> rList = sweepstakeService.getWinRecordList(activiteId);
			
	 		jsonMap.put("code", Message.CODE_SUCCESS);
			jsonMap.put("message",Message.MSG_SUCCESS);
			jsonMap.put("result", rList);
		}catch(Exception e){
			e.printStackTrace();
			jsonMap.put("code", Message.CODE_ERROR);
			jsonMap.put("message",Message.MSG_EMPTY_TOKEN_ERROR);
		}
		jsonResult = (JSON) JSON.toJSON(jsonMap);
		Long t2= System.currentTimeMillis();
		log.info("------------------获奖纪录接口耗时（毫秒）:"+(t2-t1));
		return SUCCESS;
 	}
	
	

	
	/**
	 * 活动详情
	 * 
	 * 当前参与人数
	 * 当前抽奖人剩余抽奖机会
	 * 活动开始日期和结束日期
	 * @throws Exception 
	 * 
	 */
	@Action(value="getActivityDetail", results={@Result(type="json",
			params={"root","jsonResult","contentType","application/json;charset=utf-8"} )})
	public String getActivityDetail(){
		Long t1= System.currentTimeMillis();
 		Map jsonMap = new TreeMap();
 		try{
	 		HttpServletRequest request = ServletActionContext.getRequest();
			String aid = request.getParameter("aId");//活动id
			String token = request.getParameter("token");//token
			log.info("aid:"+aid+"   token:"+token);
	 		if (StringUtil.isEmpty(aid)) {
	 			jsonMap.put("code", Message.CODE_ERROR);
	 			jsonMap.put("message",Message.MSG_PARAM_ILLEGAL);
	 			jsonResult = (JSON) JSON.toJSON(jsonMap);
	 			return SUCCESS;
			}
	 		//验证token
	 		if (StringUtil.isNotEmpty(token)&&!TokenCheckUtil.checkToken(token)) {
	 			jsonMap.put("code", Message.CODE_TIMEOUT);
	 			jsonMap.put("message",Message.MSG_TIME_OUT);
	 			jsonResult = (JSON) JSON.toJSON(jsonMap);
	 			return SUCCESS;
			}
	 		Long aId = Long.valueOf(aid);
	 		
	 		//活动信息
	 		CJActivite activite = sweepstakeService.getActiviteById(aId);
	 		if (activite==null) {
	 			log.info("活动不存在");
	 			jsonMap.put("code", Message.CODE_ERROR);
	 			jsonMap.put("message","活动不存在或已结束");
	 			jsonResult = (JSON) JSON.toJSON(jsonMap);
	 			return SUCCESS;
			}
	 		
	 		Map rMap = new HashMap<String, Object>();
	 		//用户信息
	 		if (StringUtil.isNotEmpty(token)&&TokenCheckUtil.checkToken(token)) {
	 			Long memberId = Long.valueOf(token.split(",")[1]);
	 			Integer time =memberService.getMemberUusableCJTime(memberId,aId);//注册用户当前可用抽奖机会
		 		rMap.put("zptime", time);
	 		}else {
	 			rMap.put("zptime", 1);
			}
	 		
	 		//当前参与抽奖人数
	 		Integer count = sweepstakeService.getCJCountByAid(aId);
	 		
	 		rMap.put("count", count);
	 		rMap.put("startDate", activite.getStarttime());
	 		rMap.put("endDate", activite.getEndtime());
	 		
	 		jsonMap.put("code", Message.CODE_SUCCESS);
			jsonMap.put("message",Message.MSG_SUCCESS);
			jsonMap.put("result", rMap);
 		}catch(Exception e){
 			e.printStackTrace();
 			jsonMap.put("code", Message.CODE_ERROR);
 			jsonMap.put("message",Message.MSG_EMPTY_TOKEN_ERROR);
		}
		jsonResult = (JSON) JSON.toJSON(jsonMap);
		Long t2= System.currentTimeMillis();
		log.info("--------------------活动详情接口耗时（毫秒）:"+(t2-t1));
		return SUCCESS;
 	}
	
	
	/**
	 * 投资送抽奖机会
	 * 
	 * @throws Exception 
	 * 
	 */
	@Action(value="giveCJChanceByInvest", results={@Result(type="json",
			params={"root","jsonResult","contentType","application/json;charset=utf-8"} )})
	public String giveCJChanceByInvest(){
		Long t1 = System.currentTimeMillis();
		Map jsonMap = new TreeMap();
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			String token = request.getParameter("token");// token
			String amount = request.getParameter("amount");// 订单金额
			log.info("投资====token："+token+"  amount"+amount);
			if (StringUtil.isEmpty(token) || StringUtil.isEmpty(amount)) {
				jsonMap.put("code", Message.CODE_ERROR);
				jsonMap.put("message", Message.MSG_PARAM_ILLEGAL);
				jsonResult = (JSON) JSON.toJSON(jsonMap);
				return SUCCESS;
			}
			// 验证token
			if (StringUtil.isNotEmpty(token) && !TokenCheckUtil.checkToken(token)) {
				jsonMap.put("code", Message.CODE_TIMEOUT);
				jsonMap.put("message", Message.MSG_TIME_OUT);
				jsonResult = (JSON) JSON.toJSON(jsonMap);
				return SUCCESS;
			}
			Long memberId = Long.valueOf(token.split(",")[1]);// 用户id
	
			// 活动信息
			CJActivite activite = sweepstakeService.getUesbleActivite();
			if (activite == null) {
				jsonMap.put("code", Message.CODE_ERROR);
				jsonMap.put("message", "活动不存在或已结束");
				jsonResult = (JSON) JSON.toJSON(jsonMap);
				return SUCCESS;
			}
	
			boolean isGive = memberService.giveCJChanceByInvest(memberId,new BigDecimal(amount), activite.getId());
			log.info("投资是否送机会==============="+isGive);
			try{
				if(isGive == true){
					sweepstakeService.sendMessage(memberId,activite,"1");//1:投资2：分享
				}
			}catch(Exception e){
				log.error("投资送机会异常"+e.getMessage());
			}
			//活动设置弹框跳转地址
			String url = memberService.getDialogUrl();
	
			Map rMap = new HashMap<String, Object>();
			rMap.put("isGive", isGive);
			rMap.put("aId", activite.getId());
			rMap.put("url", url);
	
			jsonMap.put("code", Message.CODE_SUCCESS);
			jsonMap.put("message", Message.MSG_SUCCESS);
			jsonMap.put("result", rMap);
		}catch(Exception e){
			log.error(e);
			jsonMap.put("code", Message.CODE_ERROR);
 			jsonMap.put("message",Message.MSG_EMPTY_TOKEN_ERROR);
		}
		jsonResult = (JSON) JSON.toJSON(jsonMap);
		Long t2 = System.currentTimeMillis();
		log.info("--------------------投资送抽奖机会接口耗时（毫秒）:" + (t2 - t1));
		return SUCCESS;
	}
	
	
	
	/**
	 * 分享送抽奖机会
	 * 
	 * @throws Exception 
	 * 
	 */
	@Action(value="giveCJChanceByShare", results={@Result(type="json",
			params={"root","jsonResult","contentType","application/json;charset=utf-8"} )})
	public String giveCJChanceByShare() {
		Long t1 = System.currentTimeMillis();
		Map jsonMap = new TreeMap();
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			String aid = request.getParameter("aId");// 活动id
			String token = request.getParameter("token");// token
			String platform = request.getParameter("platform");// platform
			String source = request.getParameter("source");// platform
			log.info("分享aid:"+aid+" token:"+token+"platform :"+platform+" source:"+source);
			
			if("5".equals(source)){//安卓
				if(!"weixin_circle".equalsIgnoreCase(platform)){
					jsonMap.put("code", Message.CODE_ERROR);
					jsonMap.put("message", Message.MSG_PARAM_ILLEGAL);
					jsonResult = (JSON) JSON.toJSON(jsonMap);
					return SUCCESS;
				}
			}
			if("6".equals(source)){//ios
				if(!"wxtimeline".equalsIgnoreCase(platform)){
					jsonMap.put("code", Message.CODE_ERROR);
					jsonMap.put("message", Message.MSG_PARAM_ILLEGAL);
					jsonResult = (JSON) JSON.toJSON(jsonMap);
					return SUCCESS;
				}
			}
			
			
			if (StringUtil.isEmpty(aid) || StringUtil.isEmpty(token)) {
				jsonMap.put("code", Message.CODE_ERROR);
				jsonMap.put("message", Message.MSG_PARAM_ILLEGAL);
				jsonResult = (JSON) JSON.toJSON(jsonMap);
				return SUCCESS;
			}
			// 验证token
			if (StringUtil.isNotEmpty(token) && !TokenCheckUtil.checkToken(token)) {
				jsonMap.put("code", Message.CODE_TIMEOUT);
				jsonMap.put("message", Message.MSG_TIME_OUT);
				jsonResult = (JSON) JSON.toJSON(jsonMap);
				return SUCCESS;
			}
			Long aId = Long.valueOf(aid);
			Long memberId = Long.valueOf(token.split(",")[1]);
	
			// 活动信息
			CJActivite activite = sweepstakeService.getActiviteById(aId);
			if (activite == null) {
				jsonMap.put("code", Message.CODE_ERROR);
				jsonMap.put("message", "活动不存在或已结束");
				jsonResult = (JSON) JSON.toJSON(jsonMap);
				return SUCCESS;
			}
			if(!aid.equals(String.valueOf(activite.getId()))){//不是最新转盘（六一转盘）
				jsonMap.put("code", Message.CODE_ERROR);
				jsonMap.put("message", aid+"该活动不送机会");
				jsonResult = (JSON) JSON.toJSON(jsonMap);
				return SUCCESS;
			}
			boolean flag = memberService.giveCJChanceByShare(memberId, aId);
			log.info("分享送机会==========================="+flag);
			try{
				if(flag == true){
					sweepstakeService.sendMessage(memberId,activite,"2");//1:投资2：分享
				}
			}catch(Exception e){
				log.error("分享送机会异常"+e.getMessage());
			}
			Map rMap = new HashMap<String, Object>();
			rMap.put("isGive", flag);
	
			jsonMap.put("code", Message.CODE_SUCCESS);
			jsonMap.put("message", Message.MSG_SUCCESS);
			jsonMap.put("result", rMap);
		}catch(Exception e){
			e.printStackTrace();
			jsonMap.put("code", Message.CODE_ERROR);
 			jsonMap.put("message",Message.MSG_EMPTY_TOKEN_ERROR);
		}
		jsonResult = (JSON) JSON.toJSON(jsonMap);
		Long t2 = System.currentTimeMillis();
		log.info("--------------------分享送抽奖机会接口耗时（毫秒）:" + (t2 - t1));
		return SUCCESS;
	}
	
	
	
	public JSON getJsonResult() {
		return jsonResult;
	}
	public void setJsonResult(JSON jsonResult) {
		this.jsonResult = jsonResult;
	}
}
