<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ page import="oobbs.domainmodel.forum.Forum" %>
<%@ taglib uri="http://localhost/oobbs/tags" prefix="oobbs" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<head>
	<title>Forum Groups</title>
</head>

<body>

<oobbs:paging action="/oobbs/site/getForumById.action?forumId=${forumId}" param="page"></oobbs:paging>
<table class="tablebg table-white" cellspacing="1" align="center" width="98%" border="1">
	<!-- Header -->
	<tr>
		<th colspan="2">&nbsp;Topic&nbsp;</th>
		<th width="50">&nbsp;Author&nbsp;</th>
		<th width="50">&nbsp;Posts&nbsp;</th>
		<th>&nbsp;Last Poster&nbsp;</th>
	</tr>
	
	<!-- Forum List Table -->
	<c:forEach items="${forum.threads}" var="thread">
		<tr>
			<td class="forum-cat" colspan="2"><h4><a href="<c:url value="/site/getThreadById.action"><c:param name="forumId" value="${forum.id}"/><c:param name="threadId" value="${thread.id}"/></c:url>"><c:out value="${thread.title}"/></a></h4></td>
			<td class="forum-cat"><h4><c:out value="${thread.author}"/></h4></td>
			<td class="forum-cat"><h4><c:out value="${thread.replyCount}"/></h4></td>
			<td class="forum-cat"><h4><c:out value="Tommy"/></h4></td>
		</tr>
	</c:forEach>
</table>

</body>