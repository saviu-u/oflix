<%-- 
    Document   : pagecounters
    Created on : 29 de mar de 2020, 20:06:24
    Author     : savio
--%>

<%
    int pages = (int) request.getAttribute("pageNumbers");
    for(int i=0; i < pages; i++){
        out.println(
            "<a href= " + (String) request.getAttribute("path") + ">" +
            (i + 1) +
            "</a>"
        );
    }
%>
