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
        <link rel="stylesheet" type="text/css" href="css/format.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuários</title>
        <style>
            * {box-sizing: border-box;}
            body {
                background: #f5f5f5;
                margin: 0;
                font-family: Arial, Helvetica, sans-serif;
            }
            <jsp:include page="../WEB-INF/Utilidades/listcss.jsp"></jsp:include>
            <jsp:include page="../WEB-INF/Utilidades/headercss.jsp"></jsp:include>
            <jsp:include page="../WEB-INF/Utilidades/searchbarcss.jsp"></jsp:include>
            </style>
        </head>
        <body>
            <div class="container">
                <header></header>
                <main>
                <jsp:include page="../WEB-INF/Utilidades/header.jsp"></jsp:include>
                <jsp:include page="../WEB-INF/Utilidades/searchbar.jsp"></jsp:include>
                    <table class="list">
                        <tr>
                            <th>Nome</th>
                            <th>CPF</th>
                            <th>Funçao</th>
                        </tr>
                        
                        <!-- Criação dos dados da tabela -->
                    <%
                        List<Map> usuarios = (List) request.getAttribute("resources");
                        for (int i = 0; i < usuarios.size(); i++) {
                            out.println("<tr>");
                            out.println("<td>" + usuarios.get(i).get("nome") + "</td>");
                            out.println("<td>" + usuarios.get(i).get("cpf") + "</td>");
                            out.println("<td>" + usuarios.get(i).get("funcao") + "</td>");
                            out.println("</tr>");
                        }
                    %>
                </table>
                <jsp:include page="../WEB-INF/Utilidades/pagecounters.jsp"></jsp:include>
            </main>
                <footer></footer>
        </div>
    </body>
</html>
