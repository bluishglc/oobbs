<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <decorator:head></decorator:head>
        <style type="text/css">
        	#navigationBar {margin:0 auto; width:98%;}
        	#container {margin:0 auto; width:98%;;}        	
        </style>
    </head>
    <body>
        <div id="container">
            <div id="navigationBar">
                <jsp:include page="/site/navigationBar.jsp" />
            </div>
            <div id="mainContent">
                <div id="content">
                    <decorator:body/>
                </div>
            </div>
        </div>
    </body>
</html>