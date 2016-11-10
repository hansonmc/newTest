package com.jlc.api.util.noteplatform;

import com.jlc.api.dto.DuanXinDO;
import com.jlc.api.utils.StringUtils;
import com.jlc.mkt.sms.interfaceapi.service.SmsApiService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoader;

import java.util.HashMap;
import java.util.Map;

public class HttpClient {
	private static final Log log = LogFactory.getLog(HttpClient.class);
	private static SmsApiService smsService;

	
//	public static SmsApiService getSmsService() {
//		return smsService;
//	}
//
//	public static void setSmsService(SmsApiService smsService) {
//		HttpClient.smsService = smsService;
//	}



	/**
     * 发送短信
     *
     * @param mobiles 终端号码，多个号码时使用半角逗号分隔，每次最多提交 300 个号码
     * @param msg     信息内容
     * @param ExNo    扩展号码，可为空，为空时不做扩展
     * @param AtTime  定时时间，可为空 , 为空时立即发送 （小于当前时间的将被处理为立即发送 ，超过当前时间 365 天的 将返回失败）
     * @param ExParam 可为空
     * @return XML描述
     * @throws Exception
     */
    public static boolean send(String mobiles, String msg, String ExNo,String AtTime, String ExParam) throws Exception {

//        boolean isFlat = false;
//
//        List<SmsChannel> smsChannelList = SmsChannelFactory.getInstance().getSmsChannelList();
//
//        if(smsChannelList == null || smsChannelList.size() == 0){
//            Logger.info("---无可发短信通道---");
//            return isFlat;
//        }
//
//        if(SmsSendCount.get() == null)SmsSendCount.initialValue();
//        if(SmsSendCount.get() >= smsChannelList.size()){
//            Logger.info("---连发"+smsChannelList.size()+"次均失败---");
//            SmsSendCount.remove();
//            return isFlat;
//        }
//
//        SmsSendCount.set(SmsSendCount.get()+1);
//
//        SmsChannel smsChannel = SmsChannelFactory.getInstance().getMobileChannel().get(mobiles);
//
//        if(smsChannel == null || System.currentTimeMillis() - smsChannel.getTime() > SmsChannelFactory.getInstance().getCacheTime()*1000){
//            smsChannel = smsChannelList.get(0);
//        }else{
//            int index = smsChannel.getIndex();
//            if(index == smsChannelList.size()-1)index = 0;
//            else index++;
//            smsChannel = smsChannelList.get(index);
//        }
//
//        isFlat = send_channel(smsChannel,mobiles,msg,ExNo,AtTime,ExParam);
//        smsChannel.setTime(System.currentTimeMillis());
//        SmsChannelFactory.getInstance().getMobileChannel().put(mobiles, smsChannel);
//
//        if(!isFlat){
//            return send(mobiles,msg,ExNo,AtTime,ExParam);
//        }
//
//        SmsSendCount.remove();

//        return isFlat;
        return false;

    }
    
    
    
    public static boolean send_channel(SmsChannel channel, String mobiles, String msg, String ExNo, String AtTime, String ExParam) throws Exception {
        boolean isFlat = false;
//        if (channel == null) return isFlat;
//        if (channel.getCode().equals("lc")) {//蓝创
//            isFlat = sent_lc(mobiles, msg);
////        } else if (channel.getCode().equals("mw")) {
////            isFlat = sent_mw(mobiles, msg);
////        } else if (channel.getCode().equals("md")) {
////            isFlat = send_md(mobiles, msg, ExNo, AtTime, ExParam);
//        }else if (channel.getCode().equals("gd")) {//国都
//            isFlat = sent_gd(mobiles, msg);
////        }else if (channel.getCode().equals("ym")) {
////            isFlat = sent_ym(mobiles, msg);
//        }
        return isFlat;
    }
	
	
	
    /**
     * 漫道发送短信
     * 
     * @param mobiles
     *                终端号码，多个号码时使用半角逗号分隔，每次最多提交 300 个号码
     * @param msg
     *                信息内容
     * @param ExNo
     *                扩展号码，可为空，为空时不做扩展
     * @param AtTime
     *                定时时间，可为空 , 为空时立即发送 （小于当前时间的将被处理为立即发送 ，超过当前时间 365 天的 将返回失败）
     * @param ExParam
     *                可为空
     * @return XML描述
	 * @throws Exception 
     */
    private static boolean send_md(String mobiles, String msg, String ExNo,String AtTime, String ExParam) throws Exception{
//    	Logger.info("---漫道短信接口开始---");
//
//	    Client client;
//		client = new Client(AddressConstants.NOTE_SN,AddressConstants.NOTE_PWD);
//		//获取信息
//		String result = client.mdgetSninfo();
//		System.out.print(result);
//
//		boolean isFlat= false;
//		//短信发送
//		String   content   =   java.net.URLEncoder.encode(msg+"【金联储】",  "utf-8");
//		String result_mt = client.mdsmssend(mobiles, content, ExNo, AtTime, "", "");
//		Logger.info("---短信发送返回信息："+result_mt);
//
//		Logger.info("---漫道短信接口结束---");
//
//		if(result_mt.startsWith("-")){
//			Logger.info("短信发送失败,出错原因："+result_mt);
//			throw new Exception("提交失败!");
//		}else{
//			isFlat = true;
//			Logger.info("短信发送成功!");
//		}
//		return isFlat;
		return false;

    }
	
	
	/**
     * 
     * 蓝创短信接口
     * @param mobiles 手机号 多个手机号以“,”分割 
     * @param msg     短信内容
     * */
    private static boolean sent_lc(String mobiles, String msg) throws Exception{
	    	
//    	Logger.info("---蓝创短信接口开始---");
//
////      msg = msg.indexOf(SmsChannelFactory.getInstance().getSignature()) == 0?msg:(SmsChannelFactory.getInstance().getSignature()+msg);
//
//      Logger.info("---手机号："+mobiles);
//	    Logger.info("---短信内容："+msg);
//
//      SmsSendPojo smsSendPojo = (SmsSendPojo) ContextLoader.getCurrentWebApplicationContext().getBean("smsSendPojo");
//
//      String returnString = null;
//
//      try{
//      	returnString = HttpSender.batchSend(smsSendPojo.getUrl(), smsSendPojo.getAccount(), smsSendPojo.getPswd(), mobiles, msg, smsSendPojo.getNeedstatus(), smsSendPojo.getProduct(), smsSendPojo.getExtno());
//      }catch(Exception e){
//	       	 Logger.error(e.getMessage());
//			 Logger.info("---蓝创短信接口发送失败---");
//			 Logger.info("---蓝创短信接口结束---");
//			 return false;
//      }
//      Logger.info("---短信发送返回信息：" + returnString);
//      if(StringUtil.isNotEmpty(returnString) && returnString.split("\n").length > 1){
//      	Logger.info("---msgid:"+returnString.split("\n")[1]);
//      }else{
//      	Logger.info("---msgid:");
//      }
//      Logger.info("---蓝创短信接口结束---");
//
//      if (StringUtil.isEmpty(returnString)) {
//          return false;
//      }
//
//      String returnCode = null;
//
//      try{
//      	returnCode = returnString.split("\n")[0].split(",")[1];
//      	Logger.info("---短信返回状态:" + SmsReturnCodeMap.getName(returnCode));
//      }catch(Exception e){
//      	Logger.info("---短信返回状态:");
//      }
//
//      if (StringUtil.isNotEmpty(returnCode) && returnCode.equals("0")) {
//          SmsSendCount.set_channel("channel", "0");
//          SmsSendCount.set_channel("msgid", StringUtil.isNotEmpty(returnString.split("\n")[1])?returnString.split("\n")[1]:"");
//          return true;
//      } else {
//          return false;
//      }
          return false;
	}
    
    /**
     * 云通讯语音验证码发送
     *
     * @param mobiles 手机号
     * @param msg    验证码
     */
    public static boolean voiceVerifySend(String mobiles, String msg) throws Exception {
	      boolean isFlat = false;
//	      if (SmsSendCount.get() == null) SmsSendCount.initialValue();
//	      if (SmsSendCount.get() >= 2) {
//	          Logger.info("---连发两次均失败---");
//	          SmsSendCount.remove();
//	          return isFlat;
//	      }
//	      SmsSendCount.set(SmsSendCount.get() + 1);
//	      isFlat = voice_sent_ytx(mobiles, msg);
//	      if (!isFlat) {
//	          return voice_sent_ytx(mobiles, msg);
//	      }
//	      SmsSendCount.remove();
	      return isFlat;
	  }
    
    
    
    /**
     * 云通讯语音验证码发送
     *
     * @param mobile 手机号
     * @param msg    验证码
     */
    @SuppressWarnings("unchecked")
	public static boolean voice_sent_ytx(String mobile, String msg) throws Exception {

        boolean isFlat = false;
        
//        Logger.info("---云通调语音验证码发送开始---");
//
//        Logger.info("---客户手机号：" + mobile);
//        Logger.info("---语音验证码：" + msg);
//
//
//        HashMap<String, Object> result = null;
//
//        YyyzmPojo yyyzmPojo = (YyyzmPojo) ContextLoader.getCurrentWebApplicationContext().getBean("yyyzmPojo");
//
//        CCPRestSDK restAPI = new CCPRestSDK();
//        restAPI.init(yyyzmPojo.getServerIp(), yyyzmPojo.getServerPort());// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
//        restAPI.setAccount(yyyzmPojo.getAccountSid(), yyyzmPojo.getAccountToken());// 初始化主帐号和主帐号TOKEN
//        restAPI.setAppId(yyyzmPojo.getAppId());// 初始化应用ID
//
//        try{
//        	result = restAPI.voiceVerify( msg,mobile, yyyzmPojo.getPhone_show(), yyyzmPojo.getRepeatNum(), yyyzmPojo.getRespUrl(), "", "", "", "");
//        }catch(Exception e){
//        	Logger.error(e.getMessage());
//			Logger.info("---云通调语音验证码发送失败---");
//			Logger.info("---云通调语音验证码发送结束---");
//			return isFlat;
//        }
//
//        Logger.info("---云通调语音验证码发送返回信息：" + result);
//
//        String msgId = "";
//        try{
//	        if (result != null && !result.isEmpty() && "000000".equals(result.get("statusCode"))) {
//	        	 Map<String,Object> o = (Map<String,Object>)result.get("data");
//	     		 o = (Map<String,Object>)o.get("VoiceVerify");
//	     		 msgId = o.get("callSid").toString();
//	        }
//        }catch(Exception e){
//
//        }
//
//        Logger.info("---msgid："+msgId);
//
//        Logger.info("---云通调语音验证码发送结束---");
//
//        if (result != null && !result.isEmpty() && "000000".equals(result.get("statusCode"))) {
//        	SmsSendCount.set_channel("channel", "9");
//	        SmsSendCount.set_channel("msgid", String.valueOf(msgId));
//            isFlat = true;
//        }

        return isFlat;

    }
    
    
    /**
    *
    * 国都短信接口
    * @param mobiles (单发一个手机号)
    * @param msg     短信内容
    * */
   private static boolean sent_gd(String mobiles, String msg) throws Exception{

//       Logger.info("---国都短信接口开始---");
//
//       msg = msg.indexOf(SmsChannelFactory.getInstance().getSignature()) == 0?msg:(SmsChannelFactory.getInstance().getSignature()+msg);
//
//	   Logger.info("---手机号："+mobiles);
//	   Logger.info("---短信内容："+msg);
//
//       SmsSendGdPojo smsSendMwPojo = (SmsSendGdPojo)ContextLoader.getCurrentWebApplicationContext().getBean("smsSendGdPojo");
//
//		/*将内容用URLEncoder编两次GBK*/
//		try {
//			msg = URLEncoder.encode(msg, "GBK");
//			msg=URLEncoder.encode(msg, "GBK");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			Logger.error(e.getMessage());
//			Logger.info("---国都短信接口发送失败---");
//			Logger.info("---国都短信接口结束---");
//			return false;
//		}
//
//		/*消息参数*/
//		String str="OperID="+smsSendMwPojo.getAccount()+"&OperPass="+smsSendMwPojo.getPwd()+"&AppendID="+smsSendMwPojo.getAppendId()+"&DesMobile="+mobiles.trim()+"&Content="+msg;
//		String response = null;
//
//		try{
//			response = postURL(str, smsSendMwPojo.getUrl());
//		}catch(Exception e){
//			 Logger.error(e.getMessage());
//			 Logger.info("---国都短信接口发送失败---");
//			 Logger.info("---国都短信接口结束---");
//			 return false;
//		}
//
//		Logger.info("---国都发送返回信息："+response);
//
//		if( !StringUtils.isEmpty(response) && response.split(",").length > 1){
//			 Logger.info("---msgid:"+response.split(",")[1]);
//		}else{
//			 Logger.info("---msgid:");
//		}
//
//        Logger.info("---国都短信接口结束---");
//
//		if (StringUtils.isEmpty(response)) {
//            return false;
//        }
//
//		String returnCode = response.split(",")[0];
//	    Logger.info("---短信返回状态:" + SmsGdReturnCodeMap.getName(returnCode));
//
//	    if (returnCode.equals("03")) {
//	         SmsSendCount.set_channel("channel", "3");
//	         SmsSendCount.set_channel("msgid", !StringUtils.isEmpty(response.split(",")[1])?response.split(",")[1]:"");
//	         return true;
//	    } else {
//	         return false;
//	    }
	         return false;

   }
    
   
   /**post方式 发送url串*/
	/**
	 * @param commString   需要发送的url参数串
	 * @param address   	需要发送的url地址
	 * @return rec_string  国都返回的自定义格式的串
	 * @catch Exception
	 */
	public static String postURL(String commString, String address) {
//		String rec_string = "";
//		URL url = null;
//		HttpURLConnection urlConn = null;
//		try {
//			/*得到url地址的URL类*/
//			url = new URL(address);
//			/*获得打开需要发送的url连接*/
//			urlConn = (HttpURLConnection) url.openConnection();
//			/*设置连接超时时间*/
//			urlConn.setConnectTimeout(30000);
//			/*设置读取响应超时时间*/
//			urlConn.setReadTimeout(30000);
//			/*设置post发送方式*/
//			urlConn.setRequestMethod("POST");
//			/*发送commString*/
//			urlConn.setDoOutput(true);
//			OutputStream out = urlConn.getOutputStream();
//			out.write(commString.getBytes());
//			out.flush();
//			out.close();
//			/*发送完毕 获取返回流，解析流数据*/
//			BufferedReader rd = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "GBK"));
//			StringBuffer sb = new StringBuffer();
//			int ch;
//			while ((ch = rd.read()) > -1) {
//				sb.append((char) ch);
//			}
//			rec_string = sb.toString().trim();
//			/*解析完毕关闭输入流*/
//			rd.close();
//		} catch (Exception e) {
//			/*异常处理*/
//			rec_string = "-107";
//			System.out.println(e);
//		} finally {
//			/*关闭URL连接*/
//			if (urlConn != null) {
//				urlConn.disconnect();
//			}
//		}
//		/*返回响应内容*/
//		return rec_string;
		return null;
	}
	
	/**
	 * add by xgl
	 * 添加 短信平台 统一接口文档
	 */
	
	/**
	 * 短信验证码 验证
	 * @param ip 远程ip地址
	 * @param from 来源（各平台请求）
	 * @param mobile 手机号
	 * @p msg 短信内容
	 * @param token 验证身份标识
	 * @param type 类型  1 短信  2 带 ip 验证短信  3 语音 4 带ip语音验证
	 * @return result true 成功  false 失败
	 */
	public static DuanXinDO smsCheckCode(String ip,String from,String mobile,String msg,String token,Integer type){
//		smsService = (SmsApiService) ContextLoader.getCurrentWebApplicationContext().getBean("smsService");
		DuanXinDO duanXinDO = new DuanXinDO();
//		boolean isOk = false;
//		//打印参数初始值
//		log.info("ip:["+ip+"] from:["+from+"] mobile:["+mobile+"] msg:["+msg+"] token:["+token+"] type:["+type+"]");
//		System.out.println("ip:["+ip+"] from:["+from+"] mobile:["+mobile+"] msg:["+msg+"] token:["+token+"] type:["+type+"]");
//
//		//参数验证  所有参数为必输项
////		if(!StringUtils.isEmpty(ip) && !StringUtils.isEmpty(from) && !StringUtils.isEmpty(mobile)
////				&& !StringUtils.isEmpty(msg) && !StringUtils.isEmpty(token) && type != null){
////			return isOk;
////		}
//
//		if(!isOk){
//			try {
//				Map<String,String> paramMap = new HashMap<String,String>();
//				paramMap.put("ip", ip);
//				paramMap.put("from", from);
//				paramMap.put("mobile", mobile);
//				paramMap.put("msg", msg);
//				paramMap.put("token", token);
//
//				if(type == null){
//					Logger.error("type 类型参数为空");
//					duanXinDO.setCode("-1");
//					duanXinDO.setResult(false);
//					duanXinDO.setName("参数不能为空");
//					return duanXinDO;
//				}
//
//				ReturnResult result = new ReturnResult();
//				switch(type){
//					case 1: result= smsService.smsCheckCode(mobile, msg, ip, null); break;
//					case 2: result= smsService.smsCheckCodeWithIPCheck(mobile, msg, ip, null); break;
//					case 3: result= smsService.voiceSmsCheckCode(mobile, msg, ip, null); break;
//					case 4: result= smsService.voiceSmsCheckCodeWithIPCheck(mobile, msg, ip, null); break;
////					case 1: response = HttpClientUtils.post(domainUrl+"/smsCheckCodeApi.dhtml", paramMap); break;
////					case 2: response = HttpClientUtils.post(domainUrl+"/SmsCheckCodeWithIPCheckApi.dhtml", paramMap); break;
////					case 3: response = HttpClientUtils.post(domainUrl+"/boiceSmsCheckCodeApi.dhtml", paramMap); break;
////					case 4: response = HttpClientUtils.post(domainUrl+"/boiceSmsCheckCodeWithIPCheckApi.dhtml", paramMap);break;
//				}
//				//暂定 返回值 为 json
//				if(result != null){
//					duanXinDO.setResult(result.getResult());
//					duanXinDO.setCode(result.getCode());
//					duanXinDO.setName(result.getName());
//					Logger.info("结果 result: ["+isOk+"] 验证结果 code: ["+duanXinDO.getCode()+"] 结果描述 name: ["+duanXinDO.getName()+"]");
//				}
//			} catch (Exception e) {
//				log.error("type:["+type+"] 短信验证异常",e);
//				e.printStackTrace();
//				duanXinDO.setCode("-1");
//				duanXinDO.setResult(false);
//				duanXinDO.setName("发送失败");
//				return duanXinDO;
//			}
//		}
		return duanXinDO;
	}
	
	/**
	 * 业务短信
	 * @param ip 远程ip
	 * @param from 平台请求点
	 * @param mobile 手机号
	 * @param msg 短信内容
	 */
	public static boolean businSms(String ip,String from,String mobile,String msg,String flag_busi){
		//打印参数初始值
		log.info("ip:["+ip+"] form:["+from+"] mobile:["+mobile+"] msg:["+msg+"] flag_busi:["+flag_busi+"]");
		smsService = (SmsApiService) ContextLoader.getCurrentWebApplicationContext().getBean("smsService");
		//参数验证
		if(StringUtils.isEmpty(from) && StringUtils.isEmpty(mobile) && StringUtils.isEmpty(msg)){
			return false;
		}
		
		try {
			Map<String,String> paramMap = new HashMap<String,String>();
			paramMap.put("ip", ip);
			paramMap.put("from", from);
			paramMap.put("mobile", mobile);
			paramMap.put("msg", msg);
			paramMap.put("flag_busi", flag_busi);
			
			smsService.busiSms(mobile, msg, ip, null, null);
		} catch (Exception e) {
			log.error("业务短信验证异常",e);
			System.out.println("业务短信验证异常");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 短信状态
	 * @param mobile 手机号
	 * @param msgId 短息id
	 * @param status 短信状态
	 */
	public static boolean smsStatus(String mobile,String msgId,String status){
//		//打印参数初始值
//		log.info("mobile:["+mobile+"] msgId:["+msgId+"] status:["+status+"]");
//		//参数验证
//		if(StringUtils.isEmpty(mobile) && StringUtils.isEmpty(msgId)
//				&& StringUtils.isEmpty(status)){
//			return false;
//		}
//
//		try {
//			Map<String,String> paramMap = new HashMap<String,String>();
//			paramMap.put("msgId", msgId);
//			paramMap.put("mobile", mobile);
//			paramMap.put("status", status);
//
//			String domainUrl = Constants.SERVICE_MKT;
//			HttpClientUtils.post(domainUrl+"/smsStatusReportApi.dhtml", paramMap);
//		} catch (Exception e) {
//			log.error("短信状态异常 ",e);
//			System.out.println("短信状态异常 ");
//			e.printStackTrace();
//			return false;
//		}
//		return true;
		return false;
	}
}