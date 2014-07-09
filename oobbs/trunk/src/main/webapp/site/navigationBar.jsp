<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="oobbs.Constants"%>

<c:forEach items="<%=request.getAttribute(Constants.REQ_ATTRIB_NAVIGATION_PATH)%>" var="node">
	<a href="${node.requestPath}">${node.name}</a>&nbsp|
</c:forEach>
