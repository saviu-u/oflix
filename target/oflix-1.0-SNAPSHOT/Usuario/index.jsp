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
        <title>Usuários</title>
    </head>
    <body>
        <div class="container">
            <header>
                <jsp:include page="../WEB-INF/Utilidades/header.jsp"></jsp:include>     
            </header>
                <jsp:include page="../WEB-INF/Utilidades/authentication_header.jsp"></jsp:include>
            <main>
                <div class="background">
                    <jsp:include page="../WEB-INF/Utilidades/searchbar.jsp"></jsp:include>
                     <table class="list">
                        <tr>
                            <th>Nome</th>
                            <th>CPF</th>
                            <th>Funçao</th>
                            <th>Edit/Delete</th>
                        </tr>
                        
                    
                    <!-- Criação dos dados da tabela -->
                        <%
                            List<Map> usuarios = (List) request.getAttribute("resources");
                            for (int i = 0; i < usuarios.size(); i++) {
                                out.println("<tr>");
                                out.println("<td>" + usuarios.get(i).get("nome") + "</td>");
                                out.println("<td>" + usuarios.get(i).get("cpf") + "</td>");
                                out.println("<td>" + usuarios.get(i).get("funcao") + "</td>");
                                out.println("<td><a href=''><img class='img-delete-edit' src='/oflix/img/edit.png'><a href=''><img class='img-delete-edit'src='/oflix/img/lixo.png'></td>");
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
