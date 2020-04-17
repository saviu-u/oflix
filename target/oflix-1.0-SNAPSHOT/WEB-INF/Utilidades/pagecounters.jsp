<%-- 
    Document   : pagecounters
    Created on : 29 de mar de 2020, 20:06:24
    Author     : savio
--%>

<%@page import="java.util.Map"%>
<%
    int pages = (int) request.getAttribute("pageNumbers");
    String path = (String) request.getAttribute("path");
    Map<String, String[]> params = (Map<String, String[]>) request.getAttribute("params");
    
    String param = "";
    for(int i = 0; i < params.size(); i++){
        String key = params.keySet().toArray()[i].toString();
        if("offset".equals(key)) continue;

        param += key + "=" + params.get(key)[0] + "&";
    }
    
    for(int i=0; i < pages; i++){
        out.println(
            "<a href= " + path + "?" + param + "offset=" + i * 12 + ">" +
            (i + 1) +
            "</a>"
        );
    }
%>
