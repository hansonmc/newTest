package com.jlc.api.dao;

import java.util.Map;

import com.jlc.api.dto.CJActiviteConditionTime;
import com.jlc.api.dto.CJActiviteConditions;
import com.jlc.api.dto.CjTimes;
import com.jlc.api.dto.MemberDO;



public interface MemberMapper {  
	
	/**
	 * 查询用户详细信息
	 * @param userId
	 * @return
	 */
	public MemberDO getMemberDOById(Long userId);
	
	/**
	 * 查询用户投资次数
	 * @param userId
	 * @return
	 */
	public Integer getMemberInvestCount(Long userId);
	
	/**
	 * 查询用户可用抽奖机会
	 * @param userId
	 * @return
	 */
	public Integer getMemberUusableCJTime(Long userId);
	
	/**
	 * 获取用户投资获得抽奖次数
	 * @return
	 */
	public Integer getMemberInvestZpTimes(Long memberId);
	
	/**
	 * 获取用户投资获得抽奖次数
	 * @return
	 */
	public Integer getMemberShareZpTimes(Long memberId);
	
	/**
	 * 获取抽奖活动投资赠送抽奖次数设置
	 * @return
	 */
	public CJActiviteConditions getActiviteConditions(Map<String, Object> map);
	
	/**
	 * 投资用户更新抽奖次数 
	 * @return
	 */
	public Integer giveCJChanceByInvest(Long memberId);
	
	/**
	 * 分享用户更新抽奖次数
	 * @return
	 */
	public Integer giveCJChanceByShare(Long memberId);
	
	/**
	 * 新增用户抽奖机会
	 * @return
	 */
	public Integer addCjTime(CjTimes time);
	
	/**
	 * 获取用户抽奖机会
	 * @return
	 */
	public CjTimes getCjTimeByMemberId(Long memberId);
	
	/**
	 * 更新用户可用抽奖机会
	 * @return
	 */
	public Integer updateMemberCJTimeByMemberId(Long memberId);
	
	/**
	 * 获取活动规则抽奖次数
	 * @return
	 */
	public CJActiviteConditionTime getActiviteConditionsTime(Long aId);
	
	
	/**
	 * 更新用户抽奖机会
	 * @return
	 */
	public Integer updateMemberCJTime(CjTimes time);
	
	/**
	 * 获取活动设置弹框跳转地址
	 * @param userId
	 * @return
	 */
	public String getDialogUrl();
	

}

