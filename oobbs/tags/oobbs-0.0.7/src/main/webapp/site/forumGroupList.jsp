<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<head>
	<title>Forum Groups</title>
</head>

<body>

<table class="tablebg table-white" cellspacing="1" align="center" width="98%" border="1">
	
	<!-- Header -->
	<tr>
		<th colspan="2">&nbsp;Forum&nbsp;</th>
		<th width="50">&nbsp;Topics&nbsp;</th>
		<th width="50">&nbsp;Posts&nbsp;</th>
		<th>&nbsp;Last post&nbsp;</th>
	</tr>
	
	<!-- Forum Group List Table -->
	<c:forEach items="${forumGroups}" var="forumGroup">
		<tr>
			<td class="forum-cat" colspan="5"><h4><c:out value="${forumGroup.name}"/></h4></td>
		</tr>
		<c:forEach items="${forumGroup.forums}" var="forum">
			<tr>
				<td class="row1" width="50" align="center">Pic</td>
				<td class="row1" width="100%">				
					<a class="forumlink" href="<c:url value="/site/getForumById.action"><c:param name="forumId" value="${forum.id}"/></c:url>"><c:out value="${forum.name}"/></a>
					<p><c:out value="${forum.description}"/></p>
				</td>
				<td class="row2" align="center"><p class="topicdetails">${forum.threadCount}</p></td>
				<td class="row2" align="center"><p class="topicdetails">${forum.postCount}</p></td>
				<td class="row2 last-post" >					
					<p class="topicdetailsx">Subject: <a href="#">${forum.latestPostTitle}</a>
					<p class="topicdetailsx">Posted by: <a href="#">${forum.latestPostAuthor}</a></p>
					<p class="topicdetailsx" style="white-space: nowrap;">${forum.latestPostCreationTime}</p>				
				</td>
			</tr>
		</c:forEach>
	</c:forEach>
</table>

</body>