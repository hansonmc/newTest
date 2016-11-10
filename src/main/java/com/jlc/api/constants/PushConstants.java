package com.jlc.api.constants;

public class PushConstants {
	
	/**
	 * 项目推送类型及每次推送数量
	 */
	public static String SEND_TYPE_PROJECT = "project";
	public static int SEND_TYPE_PROJECT_NUM = 2;
	
	// Android推送设备标识
	public static int ANDROID_DEV = 2;
	// IOS推送设备标识
	public static int IOS_DEV = 3;
	
	//安卓百度推送的key                       
//	public static String apiKey = "RYvppOd0exadDl4PBvG7gbQ8";           //正式
//	public static String secretKey = "ow3McvvIgMpClZLpUp1YCjyoUsIE8Su6";//正式
//	
//	public static String apiKey1 = "DZK7eWuYuBgLO3q9hfFlmGOf";           //正式
//	public static String secretKey1 = "AOuI37pOrlzHthy5VarsN1inoUdKPofG";//正式
	
	public static String apiKey = "pR4EyWBF0GPm0owKct3pG18n";             //测试
	public static String secretKey = "BGWWWHLn6htSFhcq4YltQ3fCyvXXDWe5";  //测试

	
	public static String appstore_test_url="https://sandbox.itunes.apple.com/verifyReceipt";
	public static String appstore_url="https://buy.itunes.apple.com/verifyReceipt";
}
