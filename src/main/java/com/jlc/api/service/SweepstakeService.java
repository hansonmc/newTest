package com.jlc.api.service;

import java.util.List;
import java.util.Map;

import com.jlc.api.dto.CJActivite;
import com.jlc.api.dto.CJPond;
import com.jlc.api.dto.CJPrize;
import com.jlc.api.dto.CJRecord;
import com.jlc.api.dto.CJRegulation;
import com.jlc.api.dto.CJRegulationPrize;
import com.jlc.api.dto.RegulationAndPrize;
import com.jlc.api.dto.UserMessage;

@SuppressWarnings("unchecked")
public interface SweepstakeService {
	
	//获取活动信息
	public CJActivite getActiviteById(Long id)throws Exception;
	
	//获奖记录
	public List<CJRecord> getWinRecordList(Long aid)throws Exception;
	
	//生成奖池
	public Map createPond(Long id) ;

	//修改该规则为进行中
	public void updateRegulationStatus(long rid, int status) throws Exception;

	//插入奖池数据
	public void insertPondBatch(List<CJPond> list) throws Exception;

	public Integer getCJCountByAid(Long aid)throws Exception;
	
	//根据活动规则获取对应规则奖池总数量
	public Integer getPondCountByRegulationId(Long regulationId)throws Exception;
	
	//未注册用户抽奖中奖并注册后派发奖品
	public Map distributePrizes(Map<String, Object> map)throws Exception;
	
	//新增抽奖记录
	public Long addCjRecord(CJRecord cjRecord)throws Exception;
	
	//抽奖后删除已抽过的奖池记录
	public Integer deletePondById(Long pId)throws Exception;
	
	//根据奖品id查询奖品信息
	public CJPrize getPrizeById(Long pId)throws Exception;
	
	//验证手机号是否注册过
	public String checkPhone(String phone)throws Exception;
	
	//获取奖池
	public CJPond getPondByRid(Long rId)throws Exception;
	
	//获取规则信息
	public CJRegulation getRegulationById(Long id)throws Exception;
	
	//获取用户当日中奖次数
	public Integer getMemberWinCount(Map<String, Object> map)throws Exception;
	
	//给抽中红包奖品用户派发红包
	public Integer distributeRedPacket(Map<String, Object> map)throws Exception;
	
	//获取规则奖品关联信息
	public CJRegulationPrize getRegulationPrize(Map<String, Object> map)throws Exception;
	
	//获取抽奖记录
	public CJRecord getRecordById(Long id)throws Exception;
	
	//中奖加站内信
	public Integer addUserMessage(UserMessage message)throws Exception;
	
	//获取抽奖记录
	public CJRecord getRecordByRandomMd5AndPhone(Map<String, Object> map)throws Exception;
	
	//抽奖
	public Map<String,Object> sweepstake(Map<String, Object> map)throws Exception;
	
	//更新用户可用抽奖机会
	public void updateMemberCJTimeByMemberId(Long memberId)throws Exception;

	//查询对应规则下已生成的奖池数量
	public Integer getPondCountByRid(Long rId)throws Exception;
	
	//获取规则关联和奖品信息
	public RegulationAndPrize getRegulationAndPrize(Map<String,Object> map)throws Exception;
	
	//获取字典code
	public String getDictionaryCodeByName(String name)throws Exception;
	//获取制定数量数据放到redis中
	public List<CJPond> getPondList(long regulationId,int count);
	
	
	//获取数据到redis中以便抽奖
	public void addRedisPonds(long regulationId);

	//奖池清洗
	public void clearPonds(long regulationId);
	
	//获取进行中的活动
	public CJActivite getUesbleActivite()throws Exception;

	//更新缓存中的活动
	public Map updateActivity(long aid);

	//发送站内信和短信
	public void sendMessage(Long memberId, CJActivite activite,String type)throws Exception;
	
	public List<CJActivite> getAllUsedZpActivity();
}
