package com.jlc.api.constants;

public class Message {

	/**
	 * 返回code码
	 */
	public static final int CODE_SUCCESS = 0; // 成功
	
	public static final int CODE_ERROR = 1;   // 错误
	
	public static final int CODE_CJ_ERROR = 2;   // 抽奖 没有抽奖机会
	
	public static final int CODE_CJ_NULLPOND_ERROR = 3;   // 抽奖 奖池被抽完
	
	public static final int CODE_TIMEOUT = -1;// 时间过期
	
	public static final int CODE_SIGN_ERROR = -2;// 签名失败
	
	public static final int CODE_MSG_ERROR = -3;   // 缺少必要参数
	
	public static final int CODE_EXPERTISE_ERROR = -4;   //异常信息

	public static final int CODE_URL_REFUSE=-9;//访问IP非法
	
	public static final int CODE_EMPTY_TOKEN_ERROR = -10;//无法获取token参数
	
	public static final int CODE_TIME_REFUSE=-11;//当前时间访问非法
	
	
	/**
	 * 返回message信息内容
	 */
	public static final String MSG_EMPTY_TOKEN_ERROR = "出错啦，请稍后重试";
	
	public static final String MSG_HBHUISHOU_ERROR = "系统繁忙，请稍等一会";
	
	public static final String MSG_SUCCESS = "操作成功";
	
	public static final String MSG_TIME_OUT = "时间过期";

	public static final String MSG_SIGN_ERROR = "签名失败";
	
	public static final String MSG_NEEDPARAM_ERROR = "缺少必要参数";

	public static final String MSG_ERROR = "操作失败";
	
	public static final String MSG_MISS_PARAM = "参数缺失";

	public static final String MSG_PARAM_ILLEGAL = "参数不合法";

	public static final String MSG_USER_NOTLOGIN = "用户未登录";
	
	public static final String MSG_MEMBER_NOT_EXISTS = "该用户不存在";
	
	public static  String MSG_EXPERTISE_ERROR = "出错啦，请稍后重试";
	
	public static final String MSG_LIANLIAN_REFUSE="充值参数不完整" ; 
	
	public static final String MSG_URL_REFUSE="访问非法" ; //访问IP非法
	
	public static final String MSG_TIME_REFUSE="访问非法" ; //当前时间访问非法
	
	public static final String MSG_IP_ERROR = "IP已经达到访问上限";
	
	
}
