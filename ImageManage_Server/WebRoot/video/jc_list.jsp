<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<!--框架必需start-->
<script type="text/javascript" src="js/jquery-1.4.js"></script>
<script type="text/javascript" src="js/framework.js"></script>
<link href="css/import_basic.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" id="skin"
	prePath="<%=basePath%>" />
<!--框架必需end-->

<!--引入组件start-->
<script type="text/javascript" src="js/attention/zDialog/zDrag.js"></script>
<script type="text/javascript" src="js/attention/zDialog/zDialog.js"></script>
<!--引入弹窗组件end-->
<script type="text/javascript" src="js/nav/ddaccordion.js"></script>
<script type="text/javascript" src="js/text/text-overflow.js"></script>
<!--修正IE6支持透明PNG图start-->
<script type="text/javascript" src="js/method/pngFix/supersleight.js"></script>
<!--修正IE6支持透明PNG图end-->
<!-- 引入分页JS -->
<script type="text/javascript" src="js/page.js"></script>	
<script type="text/javascript">
	function videoInput(jcRecId){
		top.Dialog.open({URL:"<%=basePath%>queryAction!videoInputNew.do?jcRecId="+jcRecId,Width:900,Height:480,Title:"视频播放"});
	}
</script>

</head>
<body>	
<div>
	<table class="tableStyle" align="center">
	<tr>
		<th width="50px;" >序号</th>
		<th >车型</th>
		<th >车号</th>
		<th >作业日期</th>
		<th >操作</th>
	</tr>
	<c:forEach var="rec" items="${pm.datas}">	
	<tr align="center">
		<td >${rec.jcRecId }</td>
		<td >${rec.jcType }</td>
		<td >${rec.jcNum }</td>
		<td >${rec.taskDate }</td>
		<td><a href="javascript:void(0);" onclick="videoInput(${rec.jcRecId });" style="color:blue;">视频播放</a></td>
	</tr>
	</c:forEach>
	</table>
</div>
<!-- 处理分页 -->
共有信息${pm.count }条,一页可显示${pageSize}条记录。
<div class="float_right padding5 paging">
	<div class="float_left padding_top4">
		<pg:pager maxPageItems="${pageSize }" url="queryAction!queryJcList.do" items="${pm.count }" export="page1=pageNumber">		
		    <pg:param name="" value=""/>
	  		<pg:first>
				 <span><a href="${pageUrl }" id="first">首页</a></span>
	 		</pg:first>
	 		<pg:prev>
				  <span><a href="${pageUrl }">上一页</a></span>
	  		</pg:prev>
	 	  		<pg:pages>
				<c:choose>
					<c:when test="${page1==pageNumber }">
						<span class="paging_current"><a href="javascript:;">${pageNumber }</a></span>
					</c:when>
					<c:otherwise>
						<span><a href="${pageUrl }">${pageNumber }</a></span>
					</c:otherwise>
				</c:choose>
	  		</pg:pages>
	  		<pg:next>
			    <span><a href="${pageUrl }">下一页</a></span>
	  		</pg:next>
	  		<pg:last>
			  	<span><a href="${pageUrl }">末页</a></span>
	 		</pg:last>
	 	</pg:pager>
	</div>
	<div class="clear"></div>
</div>		
</body>
<link href="<%=basePath %>My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>My97DatePicker/WdatePicker.js"></script>
</html>