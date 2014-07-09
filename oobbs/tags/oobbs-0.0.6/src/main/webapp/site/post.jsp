<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="oobbs.Constants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
<title>
	<c:choose>
	  <c:when test="<%=Constants.CREATE_THREAD.equals(request.getAttribute(Constants.POST_MODE))%>">
	    Post A New Thread
	  </c:when>
	  <c:when test="<%=Constants.CREATE_REPLY.equals(request.getAttribute(Constants.POST_MODE))%>">
	    Post Reply
	  </c:when>
	  <c:when test="<%=Constants.UPDATE_POST.equals(request.getAttribute(Constants.POST_MODE))%>">
	    Update Post
	  </c:when>
	</c:choose>
</title>
</head>

<body>
<div style="margin: 0 auto; width: 98%">
	<form action="<c:choose>
					<c:when test="<%=Constants.CREATE_THREAD.equals(request.getAttribute(Constants.POST_MODE))%>">
						<c:url value="/site/createThread.action"/>
					</c:when>
					<c:when test="<%=Constants.CREATE_REPLY.equals(request.getAttribute(Constants.POST_MODE))%>">
						<c:url value="/site/createReply.action"/>
					</c:when>
					<c:when test="<%=Constants.UPDATE_POST.equals(request.getAttribute(Constants.POST_MODE))%>">
						<c:url value="/site/updatePost.action"/>
					</c:when></c:choose>" method="post">
	    <c:if test="<%=Constants.CREATE_THREAD.equals(request.getAttribute(Constants.POST_MODE))%>">
			<input type="hidden" name="forumId" value="${forumId}"/>
		</c:if>
		<c:if test="<%=Constants.CREATE_REPLY.equals(request.getAttribute(Constants.POST_MODE))%>">
			<input type="hidden" name="threadId" value="${threadId}"/>
		</c:if>
		<c:if test="<%=Constants.UPDATE_POST.equals(request.getAttribute(Constants.POST_MODE))%>">
			<input type="hidden" name="post.id" value="${post.id}"/>
		</c:if>
		<table align="center" width="100%" border="1">
			<tbody>
				<tr>
					<th colspan="2">
						<c:choose>
							<c:when test="<%=Constants.CREATE_THREAD.equals(request.getAttribute(Constants.POST_MODE))%>">Post A New Thread</c:when>
	  						<c:when test="<%=Constants.CREATE_REPLY.equals(request.getAttribute(Constants.POST_MODE))%>">Post Reply</c:when>
	  						<c:when test="<%=Constants.UPDATE_POST.equals(request.getAttribute(Constants.POST_MODE))%>">Update Post</c:when>
						</c:choose>
					</th>
				</tr>
				<tr>
					<td><b>Subject:</b></td>
					<td><input  id="title" name="subjectPostDTO.title"/></td>
				</tr>
				<tr>
					<td><b>Message Body:</b></td>
					<td><textarea rows="10" cols="100" id="messageBody" name="subjectPostDTO.messageBody"></textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"/></td>
				</tr>
			</tbody>
		</table>
	</form>
</div>
</body>