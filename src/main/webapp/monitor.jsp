<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <title>列队监视</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
   	    <script src="<%=path%>/js/jquery-1.4.2.min.js" type="text/javascript"></script>
   	    <style type="text/css"> 
   	         .m_out{
   	               width:100%;
   	               text-align:center;
   	               height:auto;
   	         }
   	         .m_in{
   	               width:700px;
   	               margin:20px auto;
   	               border:1px solid #000000;
   	         }
   	         .m{
   	            margin:0 auto;
   	            width:500px;
   	            height:500px;
   	         }
   	         .m_top{
   	               width:100%;
   	               margin:0;
   	               margin-top:50px;
   	         }
   	         .m_left{
   	             height:400px;
   	             width:20px;
   	             float:left;
   	         }
   	         .m_right{
   	             height:400px;
   	             width:480px;
   	             float:left;
   	         }
   	         .clear{
   	              clear:both;
   	         }
   	         .m_left_kd_out{
   	              width:19px;
   	              height:100%;
   	              float:left;
   	         }
   	         .m_left_line_out{
   	              width:1px;
   	              height:100%;
   	              float:left;
   	              text-alien:left;
   	         }
   	         .m_left_line{
   	              width:1px;
   	              height:100%;
   	              margin:0px;
   	              padding:0px;
   	              border-left:1px solid #000000;
   	         }
   	         .m_bottom{
   	              width:100%;
   	              height:20pxp;
   	              margin-left:20px;
   	         }
   	         .m_bottom_line_out{
   	              width:100%;
   	              margin-top:-1px;
   	              height:1px;
   	              border-top:1px solid #000000;
   	         }
   	         .m_bottom_kd_out{
   	              width:100%;
   	              height:50px;
   	         }
   	         .m_left_kd{
   	             margin-right:1px;
   	             margin-top:40px;
   	             height:14px;
   	             padding-bottom:5px;
   	             border-bottom:1px solid #000000;
   	         }
   	         .m_left_kd_o{
   	             border:0px;
   	             border-bottom:1px solid #000000;
   	         }
   	         .m_left_kd_top{
   	             margin-top:80px;
   	         }
   	         .m_bottom_kd{
   	             padding-left:1px;
   	             float:left;
   	             width:58px;
                 text-align:left;
   	             border-left:1px solid #000000;
   	         }
   	         .m_bottom_kd_o{
   	             float:left;
   	             margin-left:0px;
   	         }
   	         .m_right_kd_out{
   	             width:100%;
   	         }
   	         .m_right_kd{
   	             padding-left:1px;
   	             float:left;
   	             width:58px;
                 text-align:left;
                 color:red;
                 border-color:red;
   	             border-left:1px solid #000000;
   	         }
   	         .m_right_kd_left{
   	             margin-left:60px;
   	         }
   	         .m_right_kd div{
   	             margin-top:-20px;
   	         }
   	    </style>
        <script  type="text/javascript">
             var data = [0,10,100,80,40,90,120,150,200,300,500,40,30,20,10,0,0,0,0];
             var n_data = [0,0,0,0,0,0,0];
             $(function(){
                 begin();
             });  
             function maxT(array){
                 var max = 0;
                 if(array == null)return max;
                 if(array.length > 0){
                     for(var j = 0;j<=array.length-1;j++){
                          if(array[j] > max)max = array[j];
                     }
                 }
                 return max;
             } 
             function begin(){
                  if(data.length == 0)return;
                  n_data.push(data.shift());
                  n_data.shift();
                  var max = maxT(n_data);
                  for(var i=0;i<=n_data.length-1;i++){
                       var height = 0;
                       var margintop = 400;
                       if(max != 0){
                       		height = n_data[i]/max*400;
                       		margintop = 400 - height;
                       }
                       $(".m_right_kd").eq(i).css({"height":height+"px","margin-top":margintop+"px"}).find("div").text(n_data[i]);
                  }
                  setTimeout(begin, 1000);
             }
	    </script>
</head>
<body>
	<div class="m_out">
		<div class="m_in">
		    <div class="m">
		        <div class="m_top">
		               <div class="m_left">
		                    <div class="m_left_kd_out">
		                         <div class="m_left_kd m_left_kd_top">5</div>
		                         <div class="m_left_kd">4</div>
		                         <div class="m_left_kd">3</div>
		                         <div class="m_left_kd">2</div>
		                         <div class="m_left_kd">1</div>
		                         <div class="m_left_kd m_left_kd_o">0</div>
		                    </div>
		                    <div class="m_left_line_out">
		                          <div class="m_left_line"></div>
		                    </div>
		                    <div class="clear"></div>
		               </div>
		               <div class="m_right">
		                   <div class="m_right_kd_out">
		                        <div class="m_right_kd m_right_kd_left"><div></div></div>
		                        <div class="m_right_kd"><div></div></div>
		                        <div class="m_right_kd"><div></div></div>
		                        <div class="m_right_kd"><div></div></div>
		                        <div class="m_right_kd"><div></div></div>
		                        <div class="m_right_kd"><div></div></div>
		                        <div class="m_right_kd"><div></div></div>
		                   </div>
		               </div>
		              <div class="clear"></div>
		        </div>
		        <div class="m_bottom">
		               <div class="m_bottom_line_out">
		                     <div class="m_bottom_line"></div>
		               </div>
		               <div class="m_bottom_kd_out">
		                      <div class="m_bottom_kd m_bottom_kd_o">0</div>
		                      <div class="m_bottom_kd">1</div>
		                      <div class="m_bottom_kd">2</div>
		                      <div class="m_bottom_kd">3</div>
		                      <div class="m_bottom_kd">4</div>
		                      <div class="m_bottom_kd">5</div>
		                      <div class="m_bottom_kd">6</div>
		                      <div class="m_bottom_kd">7</div>
		               </div>
		        </div>
		        <div class="clear"></div>
		    </div>
	   </div>
	</div>
</body>
</html>