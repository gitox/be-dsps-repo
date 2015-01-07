<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Test</title>
    </head>

    <body>
        <jsp:useBean id="sResults"  class="com.dsps.beans.SearchResultsBean" scope="request"/>
        <% for(String mvie:sResults.getMmItems()){ %>
	        <%= mvie %>
	    <br />
        <% } %>
    </body>
</html>