<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <title>短信测试页</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
   	    <script src="<%=path%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
        <script  type="text/javascript">
             $(function(){
                  $("#send").click(function(){
                      if($.trim($("#mobile").val())==''){
                          alert("提示：手机号不能为空！");
                          return false;
                      }
                      if($.trim($("#msg").val())==''){
                          alert("提示：信息不能为空！");
                          return false;
                      }
                      $.ajax({
							url: '<%=path%>/sms/smsAction!send.dhtml',
							type: 'post',
							data: {'mobile':$.trim($("#mobile").val()),'msg':$.trim($("#msg").val())},
							async: false,
							dataType: "text",
							success:function(data){
						          alert("提示：短信发送成功！");
							},
							error:function(){
							     alert("提示：服务异常！");
							}
					 });	
                  });
                  $("#dial").click(function(){
                      if($.trim($("#mobile_dial").val())==''){
                          alert("提示：手机号不能为空！");
                          return false;
                      }
                      if($.trim($("#checkCode").val())==''){
                          alert("提示：验证码不能为空！");
                          return false;
                      }
                      $.ajax({
							url: '<%=path%>/sms/smsAction!send.dhtml',
							type: 'post',
							data: {'mobile':$.trim($("#mobile").val()),'msg':$.trim($("#msg").val())},
							async: false,
							dataType: "text",
							success:function(data){
						          alert("提示：语音播报成功！");
							},
							error:function(){
							     alert("提示：服务异常！");
							}
					 });	
                  });
                });   
	    </script>
</head>
<body>
	<div style="width:100%;text-align:center;">
	    <div style="width:50%;margin:0 auto;margin-top:50px;">
		    <div style="width:100%;">
		     		<div style="width:30%;float:left;text-align:right;">手机号：</div>
		     		<div style="width:70%;float:left;text-align:left;">
		               <input type="text" id="mobile" value="" size="50"/>
		            </div>
		            <div style="clear: both;"></div>
		    </div>
		    <div style="width:100%;margin-top:10px;">
				    <div style="width:30%;float:left;text-align:right;">信息：</div>
				    <div style="width:70%;float:left;text-align:left;">
				       <textarea id="msg" style="width:360px;height:100px;text-align:left;">亲爱的用户，您的验证码是123456，5分钟内有效。</textarea>
				    </div>   
				    <div style="clear: both;"></div>
		    </div>
		    <div style="width:100%;margin-top:10px;padding:5px;">
		          <span style="border:1px solid #000000;padding:2px;cursor:pointer;" id="send">短信发送</span>
		    </div>
		    <div style="clear: both;"></div>
	    </div>
	    <div style="width:50%;margin:0 auto;margin-top:50px;border-top:1px solid #000000;padding-top:50px;">
		    <div style="width:100%;">
		     		<div style="width:30%;float:left;text-align:right;">手机号：</div>
		     		<div style="width:70%;float:left;text-align:left;">
		               <input type="text" id="mobile_dial" value="" size="50"/>
		            </div>
		            <div style="clear: both;"></div>
		    </div>
		    <div style="width:100%;margin-top:10px;">
				    <div style="width:30%;float:left;text-align:right;">验证码：</div>
				    <div style="width:70%;float:left;text-align:left;">
				        <input type="text" id="checkCode" value="" size="10"/>
				    </div>   
				    <div style="clear: both;"></div>
		    </div>
		    <div style="width:100%;margin-top:10px;padding:5px;">
		          <span style="border:1px solid #000000;padding:2px;cursor:pointer;" id="dial">语音播报</span>
		    </div>
		    <div style="clear: both;"></div>
	    </div>
	</div>
</body>
</html>