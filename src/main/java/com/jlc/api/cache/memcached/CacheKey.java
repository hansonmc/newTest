package com.jlc.api.cache.memcached;

public class CacheKey {
	/**
	 * memcached过期时间
	 */
	// 五分钟
	public static final Integer FIVE_MINUTES_TIME = 300;
    //一分钟
	public static final Integer ONE_MINUTES_TIME = 60;
	
	// 一星期
	public static final Integer ONE_WEEK = 24*60*60*1000;
	
	/**
	 * token
	 */
	public static final String TOKEN_PREFIX = "APP_TOKEN_";
	
	/**
	 * 项目前缀
	 */
	public static final String HEAD = "EBANKAPP_";
	
	/**
	 * 手机验证码缓存
	 */
	public static final String CODE_CASHED = HEAD + "CODE_CASHED";
	/**
	 * 投资项目列表缓存
	 */
	public static final String INVESTLIST_CASHED = HEAD + "INVESTLIST_CASHED";
	/**
	 * 投资项目详情缓存
	 */
	public static final String INVESTDETAIL_CASHED = HEAD + "INVESTDETAIL_CASHED";
	/**
	 * 债转项目列表缓存
	 */
	public static final String ZCREDITASSIGNLIST_CASHED = HEAD + "ZCREDITASSIGN_LIST_CASHED";
	/**
	 * 债转项目详情缓存
	 */
	public static final String ZCREDITASSIGNDETAIL_CASHED = HEAD + "ZCREDITASSIGN_DETAIL_CASHED";
	
	
	

	public static final String STEEL_INFO = HEAD + "STEEL_INFO_";
	/**
	 * 3.2.11 获取石油信息详情
	 */
	public static final String OIL_INFO = HEAD + "OIL_INFO_";
	/**
	 * 3.2.1 大宗要闻订制时，获取的所有产品
	 */
	public static final String NEWS_INDUSTRY = HEAD + "NEWS_INDUSTRY";
	/**
	 * 3.2.8 订制前大宗要闻可浏览的栏目
	 */
	public static final String FREE_PART_NEWS = HEAD + "FREE_PART_NEWS";
	public static final String FIXED_PART_NEWS = HEAD + "FIXED_PART_NEWS";
	/**
	 * 3.2.10.1获得大宗要闻列表
	 */
	public static final String NEWS_LIST = HEAD + "NEWS_LIST_";
	/**
	 * 3.3.1 内参资讯 获得用户订制前可以浏览的栏目列表
	 */
	public static final String FREE_PART = HEAD + "FREE_PART";

	/**
	 * 3.3.2 根据栏目获取资讯列表
	 */
	public static final String INFO_LIST = HEAD + "INFO_LIST_";

	/**
	 * 3.3.4获取所有行业(订制)
	 */
	public static final String INDUSTRY_ID = HEAD + "INDUSTRY_ID_";

	/**
	 * 3.3.5根据行业classId获取细分行业(订制)
	 */
	public static final String INDUSTRY_CLASSID = HEAD + "INDUSTRY_CLASSID_";

	/**
	 * 3.3.6.1获取细分行业下的具体可订制内容（包括资讯、行情、快讯）(订制)
	 */
	///緩存有問題
	public static final String OPEN_PRODUCT = HEAD + "OPEN_PRODUCT_";

	
	/**
	 * 3.3.17获取行情数据
	 */
	public static final String PRICE_CLASSID = HEAD + "PRICE_CLASSID_";
	///緩存有問題
	public static final String PRICE_APPID_DATETYPE = HEAD + "PRICE_";
	public static final String PRICEMAP_CLASSID = HEAD + "PRICEMAP_CLASSID_";
	
	/**
	 * 3.3.30  根据栏目获取推送快讯列表（新加）
	 */
	public static final String SEND_COMMENT_LIST = HEAD + "SEND_COMMENT_LIST_";
	
	/**
	 * memcached过期时间
	 */
	// 一小时
	public static final Integer ONE_HOURS_TIME = 60 * 60;
	
	// 30秒
	public static final Integer THIRTY_SECOND_TIME = 30;
	
	
}
