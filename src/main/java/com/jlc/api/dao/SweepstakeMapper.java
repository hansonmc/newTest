package com.jlc.api.dao;

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


public interface SweepstakeMapper {
	
	//获取活动信息
	public CJActivite getActiviteById(Long id);
	
	//获奖记录
	public List<CJRecord> getWinRecordList(Long aId);

	//活动当前参与人数
	public Integer getCJCountByAid(Long aId);

	//获取规则信息并锁定
	public CJRegulation getRegulationByIdForUpdate(Long id) throws Exception;
	
	//获取规则对应的奖品设置列表
	public List<CJRegulationPrize> getRegulationPrizeList(Long rid);

	//获取未开始的规则，以便生成奖池
	public CJRegulation getUnbegunRegulation();

	//修改该规则为进行中
	public void updateRegulationStatus(long rid, int status);

	//插入奖池数据
	public void insertPondBatch(List<CJPond> list);
   
	
	//根据活动规则获取对应规则奖池总数量
	public Integer getPondCountByRegulationId(Long regulationId);
	
	//未注册用户抽奖中奖并注册后派发奖品
	public Integer distributePrizes(Map<String, Object> map)throws Exception;
	
	//新增抽奖记录
	public Long addCjRecord(CJRecord cjRecord)throws Exception;
	
	//抽奖后删除已抽过的奖池记录
	public Integer deletePondById(Long pId)throws Exception;
	
	//根据奖品id查询奖品信息
	public CJPrize getPrizeById(Long pId)throws Exception;
	
	//验证手机号是否注册过
	public String checkPhone(String phone)throws Exception;
	
	//获取奖池id
	public Long getPondByRid(Map<String, Object> map)throws Exception;
	
	//获取奖池
	public CJPond getPondByRid(Long rId)throws Exception;
	
	//获取规则信息
	public CJRegulation getRegulationById(Long id)throws Exception;
	
	//获取用户当日中奖次数
	public Integer getMemberWinCount(Map<String, Object> map)throws Exception;
	
	//获取规则奖品关联信息
	public CJRegulationPrize getRegulationPrize(Map<String, Object> map)throws Exception;
	
	//获取抽奖记录
	public CJRecord getRecordById(Long id)throws Exception;
	
	//中奖加站内信
	public Integer addUserMessage(UserMessage message)throws Exception;
	
	//获取抽奖记录
	public CJRecord getRecordByRandomMd5AndPhone(Map<String, Object> map)throws Exception;
	
	//更新用户可用抽奖机会
	public void updateMemberCJTimeByMemberId(Long memberId)throws Exception;
	
	//查询对应规则下已生成的奖池数量
	public Integer getPondCountByRid(Long rId)throws Exception;
	
	//获取规则关联和奖品信息
	public RegulationAndPrize getRegulationAndPrize(Map<String,Object> map)throws Exception;
	
	//获取字典code
	public String getDictionaryCodeByName(String name)throws Exception;
	
	//获取具体抽奖记录信息
	public CJRecord getWinRecordById(Long id)throws Exception;
	
	//获取制定数量数据放到redis中
	public List<CJPond> getPondList(long regulationId,int count);
	
	//批量删除制定数量数据
	public void deletePonds(long regulationId,int count);

	//奖池清洗
	public void clearPonds(long regulationId);
	
	//获取进行中的活动
	public CJActivite getUesbleActivite()throws Exception;

	//将奖品状态设置为已发送
	public void updateCJRecordUserId(Map<String, Object> map);

	//获取某手机号的领奖记录
	public int getRecordByMemberId(long memberId,long aId);
	
	//获取转盘活动
	public List<CJActivite> getZpActivityId();
	
	//更新奖品领取状态
	public void updateCJRecordStatus(long rId);
	
	//获取所有进行中转盘活动
	public List<CJActivite> getAllUsedZpActivity();
}
