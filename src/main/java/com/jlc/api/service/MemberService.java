package com.jlc.api.service;

import java.math.BigDecimal;

import com.jlc.api.dto.MemberDO;


public interface MemberService {
	
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
	public Integer getMemberUusableCJTime(Long userId,Long aId);
	
	/**
	 * 投资送抽奖机会
	 * @return
	 */
	public boolean giveCJChanceByInvest(Long userId,BigDecimal amount,Long aId);
	
	/**
	 * 分享送抽奖机会
	 * @return
	 */
	public boolean giveCJChanceByShare(Long userId,Long aId);
	
	/**
	 * 抽奖更新用户可用抽奖机会
	 * @param userId
	 * @return
	 */
	public int updateMemberCJTimeByMemberId(Long memberId,Long aId);
	
	/**
	 * 获取活动设置弹框跳转地址
	 * @param userId
	 * @return
	 */
	public String getDialogUrl();
}
