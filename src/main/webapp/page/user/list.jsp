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
           function del(id){
               if(!id){
                   alert("提示：数据无id！");
                   return false;
               }
               $.ajax({
							url: '<%=path%>/user/userAction!delete.dhtml',
							type: 'post',
							data: {'id':id},
							async: false,
							dataType: "json",
							success:function(data){
							        if(data){
							            if(data == -2){
					                       	  alert("提示：参数错误！");
					                    }else if(data == 1){
						                      alert("提示：删除成功！");
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
           }
	    </script>
	</head>
<body>
    <div style="width:90%;margin:0 auto;margin-top:50px;">
		 <div style="text-align:left;margin-top:50px;border:1px solid #000000;background-color: #dedede;"><span style="margin-left:20px;height:30px;line-height:30px;">当前位置：用户列表</span></div>
		 <div style="width:100%;text-align:left;margin-top:20px;">
	         <!--查询结果列表-->
	        <table class="gridtable">
	           <tr >
	               <th width="20%">序号</th>
	               <th width="40%">用户名</th> 
	               <th width="40%">操作</th>
	           </tr>
	            <s:iterator value="list" id="user" status="index">
		           <tr>
		               <td><s:property value="#index.index+1"/></td>
		               <td><s:property value="#user.name"/></td> 
		               <td> <a href='<%=path%>/user/userAction!edit.dhtml?id=<s:property value="#user.id"/>'>修改</a>   <a href='javascript:void(0);' onclick='javascript:del(<s:property value="#user.id"/>)'>删除</a>  </td> 
		           </tr>
	           </s:iterator>
	        </table>        
	     </div>
	     <div style="text-align:left;margin-top:20px;border:1px solid #000000;background-color: #dedede;"><span style="margin-left:20px;height:30px;line-height:30px;"><a href="<%=path%>/page/user/edit.jsp">新增</a></span></div>
   </div>
</body>
</html>
