<%-- 
    Document   : index
    Created on : 14 de mar de 2020, 01:16:23
    Author     : savio
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/oflix/css/format.css">
        <link rel="stylesheet" type="text/css" href="/oflix/css/body.css">
        <link rel="stylesheet" type="text/css" href="/oflix/css/list.css">
        <link rel="stylesheet" type="text/css" href="/oflix/css/header.css">
        <link rel="stylesheet" type="text/css" href="/oflix/css/searchbar.css">
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">       
        <title>Filmes</title>
    </head>
    <body>
        <%
            String path = (String) request.getAttribute("path");
            if (!path.substring(path.length() - 1).equals("/")) {
                path += "/";
            }

        %>
        <div class="container">
            <header>
                <jsp:include page="../WEB-INF/Utilidades/header.jsp"></jsp:include>     
            </header>>
            <main>
                <div class="background">
                    <jsp:include page="../WEB-INF/Utilidades/searchbar.jsp"></jsp:include> 
                    <a href= "<% out.println(path);%>new">
                       <button class = "add_button">+Add</button>
                    </a>
                     <table class="list">
                        <tr>
                            <th>Filme</th>
                            <th>Categoria</th>
                            <th>Estoque</th>
                            <th>Classificação</th>
                            <th>Edit</th>
                        </tr>
                        
                    
                    <!-- Criação dos dados da tabela -->
                        <%
                            if(!path.substring(path.length() - 1).equals("/")) path += "/";
                            List<Map> filmes = (List) request.getAttribute("resources");
                            for (int i = 0; i < filmes.size(); i++) {
                                out.println("<tr>");
                                out.println("<td>" + filmes.get(i).get("nome_filme") + "</td>");
                                out.println("<td>" + filmes.get(i).get("nome_catg") + "</td>");
                                out.println("<td>" + filmes.get(i).get("quant_estoque") + "</td>");
                                out.println("<td>" + filmes.get(i).get("class_indicativa") + "</td>");
                                out.println("<td><a href='" + path + filmes.get(i).get("id") + "/edit'><img class='img-delete-edit' src='/oflix/img/edit.png'>");
                                out.println("</tr>");
                            }
                        %>
                    </table>
                    
                    <jsp:include page="../WEB-INF/Utilidades/pagecounters.jsp"></jsp:include>
                </div>
            </main>
            
        </div>
    </body>
</html>
