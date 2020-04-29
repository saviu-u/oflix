<%-- 
    Document   : form
    Created on : 28 de abr de 2020, 19:56:13
    Author     : savio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuários - <% out.println((String) request.getAttribute("method")); %></title>
        <style>
            * {box-sizing: border-box;}
            body {
                background: #f5f5f5;
                margin: 0;
                font-family: Arial, Helvetica, sans-serif;
            }
            <jsp:include page="../WEB-INF/Utilidades/listcss.jsp"></jsp:include>
            <jsp:include page="../WEB-INF/Utilidades/headercss.jsp"></jsp:include>
        </style>
    </head>
    <body>
        <div class="container">
            <header></header>
            <main>
                <jsp:include page="../WEB-INF/Utilidades/header.jsp"></jsp:include>
                <form action="<% out.println((String) request.getAttribute("pathToSearch")); %>/new" method="POST">
                    <label for="nome">Nome:</label><br>
                    <input id="nome" type="text" value="John"><br><br>
                    <label for="cpf">CPF:</label><br>
                    <input id="cpf" type="text"><br><br>
                    <button>Enviar</button>
                </form>
            </main>
            <footer></footer>
        </div>
    </body>
</html>
