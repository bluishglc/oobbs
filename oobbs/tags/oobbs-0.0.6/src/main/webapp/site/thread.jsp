<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ page import="oobbs.domainmodel.forum.Thread"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://localhost/oobbs/tags" prefix="oobbs" %>

<head>
	<title>Insert title here</title>
	<script type="text/javascript"></script>
</head>

<body>
<div style="margin: 0 auto; width: 98%">

<!-- New Topic and Post Reply links-->
<a href="<c:url value="/site/navToCreateThread.action"><c:param name="forumId" value="${forumId}"/></c:url>">New Thread</a>
<a href="<c:url value="/site/navToCreateReply.action"><c:param name="threadId" value="${threadId}"/></c:url>">Post Reply</a>
${postListPager.htmlSnippet}<br></br>
<oobbs:paging action="/oobbs/site/getThreadById.action?threadId=${threadId}" param="page"></oobbs:paging>
<!-- Post list. -->
<c:forEach items="${threadDTO.posts}" var="post">
	<br></br>
	<table align="center" width="100%" border="1">
		<tbody>
			<tr height="25">
				<td align="center" valign="middle" width="200">${post.author}</td>
				<td>
					<div style="float: left"><b>Title:&nbsp;</b>${post.title}</div>
					<div style="float: right"><b>Posted:&nbsp;</b>${post.creationTime}</div>
				</td>
			</tr>
			<tr>
				<td width="200">${post.author}</td>
				<td>${post.messageBody}</td>
			</tr>
		</tbody>
	</table>
</c:forEach>
<br></br>
<!-- Quick reply. -->
<b>Quick reply:</b>
<form action="<c:url value="/site/createReply.action"/>" method="post">
	<input type="hidden" name="threadId" value="${threadId}"/>
	<table align="center" width="100%" border="1">
		<tbody>
			<tr><th colspan="2">Post Reply</th></tr>
			<tr>
				<td><b>Subject:</b></td>
				<td><input  id="title" name="replyPostDTO.title"/></td>
			</tr>
			<tr>
				<td><b>Message Body:</b></td>
				<td><textarea rows="10" cols="100" id="messageBody" name="replyPostDTO.messageBody"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"/></td>
			</tr>
		</tbody>
	</table>
</form>

</div>
</body>

