<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
   	    <script src="<%=path%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
   	    <style type="text/css">
			table.gridtable {
			    width:100%;
			    text-align:center;
				font-family: verdana,arial,sans-serif;
				font-size:13px;
				color:#333333;
				border-width: 1px;
				border-color: #666666;
				border-collapse: collapse;
			}
			table.gridtable th {
				border-width: 1px;
				padding: 8px;
				border-style: solid;
				border-color: #666666;
				background-color: #dedede;
			}
			table.gridtable td {
				border-width: 1px;
				padding: 8px;
				border-style: solid;
				border-color: #666666;
				background-color: #ffffff;
			}
</style>
	    <script  type="text/javascript">
             $(function(){
                  $("#save").click(function(){
                      if($.trim($("#name").val())==''){
                          alert("提示：用户名不能为空！");
                          return false;
                      }
                      $.ajax({
							url: '<%=path%>/user/userAction!save.dhtml',
							type: 'post',
							data: {'id':'<s:property value="user.id"/>','name':$.trim($("#name").val())},
							async: false,
							dataType: "json",
							success:function(data){
							        if(data){
							            if(data == -2){
					                       	  alert("提示：参数错误！");
					                    }else if(data == 1){
						                      alert("提示：保存成功！");
						                      location.href="<%= path%>/user/userAction!list.dhtml";
					                    }
				                    }else{
				                        alert("提示：服务异常！");
				                    }
							},
							error:function(){
							     alert("提示：服务异常！");
							}
					 });	
                  });
                  $("#back").click(function(){
                       location.href="<%= path%>/user/userAction!list.dhtml";
                  });
             });
	    </script>
	</head>
<body>
    <div style="width:90%;margin:0 auto;margin-top:50px;">
		 <div style="text-align:left;margin-top:50px;border:1px solid #000000;background-color: #dedede;"><span style="margin-left:20px;height:30px;line-height:30px;">当前位置：增加用户</span></div>
		 <div style="width:100%;text-align:left;margin-top:20px;">
	         <!--查询结果列表-->
	        <table class="gridtable">
		           <tr>
		               <td style="width:30%;text-align:right;margin-right:5px;">用户名：</td>
		               <td style="width:70%;text-align:left;margin-left:5px;"><input type="text" id="name" size="50px" value='<s:property value="user.name"/>'/></td> 
		           </tr>
	        </table>        
	     </div>
	     <div style="margin-top:20px;border:1px solid #000000;background-color: #dedede;height:30px;line-height:30px;">
		         <div style="text-align:center;width:50%;float:left;"><span id="save" style="cursor:pointer;">保存</span></div>
		         <div style="text-align:center;width:50%;float:left;"><span id="back" style="cursor:pointer;">取消</span></div>
		         <div style="clear: both;"></div>
	     </div>
   </div>
</body>
</html>
