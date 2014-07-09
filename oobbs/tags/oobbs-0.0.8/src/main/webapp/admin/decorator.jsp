<%@ include file="/admin/taglibs.jsp"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><decorator:title/></title>
        <link rel="stylesheet" type="text/css" href="<c:url value='/admin/styles/layout.css'/>" />
    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include page="header.jsp" />
            </div>
            <div id="menu">
                <jsp:include page="menu.jsp" />
            </div>
            <div id="mainContent">
                <div id="sidebar">
                    <jsp:include page="sidebar.jsp" />
                </div>
                <div id="content">
                    <decorator:body/>
                </div>
            </div>
            <div id="footer">
                <jsp:include page="footer.jsp" />
            </div>
        </div>
    </body>
</html>