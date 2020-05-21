<%-- 
    Document   : form
    Created on : 28 de abr de 2020, 19:56:13
    Author     : savio
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>  
    <head>
        <link rel="stylesheet" type="text/css" href="/oflix/css/format.css">
        <link rel="stylesheet" type="text/css" href="/oflix/css/body.css">
        <link rel="stylesheet" type="text/css" href="/oflix/css/formatForm.css">
        <link rel="stylesheet" type="text/css" href="/oflix/css/list.css">
        <link rel="stylesheet" type="text/css" href="/oflix/css/header.css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
            String method = (String) request.getAttribute("method");
            boolean edit = method.equals("Editar");
        %>
        <title>Categoria - <% out.println(method); %></title>
    </head>
        <body>
            <%
                Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
                Map<String, String[]> params = (Map<String, String[]>) request.getAttribute("params");
                if(params == null) params = new HashMap();
                if(errors == null) errors = new HashMap();
            %>
            <div class="container">
                <header>
                    <jsp:include page="../WEB-INF/Utilidades/header.jsp"></jsp:include>
                </header>
                    <jsp:include page="../WEB-INF/Utilidades/authentication_header.jsp"></jsp:include>
                <main>
                    <div class="background">
                    <form class="form" method="POST">
                        <div>
                            <label class="category">Dados da Categoria: </label>
                            <fieldset <% if(errors.get("nome_catg") != null) out.println("class=error"); %>>
                                <label class="required">Nome</label>
                                <input name="nome_catg" type="text" placeholder="Título.."
                                    <% if(params.get("nome_catg") != null) out.println("value=\""+ params.get("nome_catg")[0] + "\""); %>
                                >
                                <% if(errors.get("nome_catg") != null) out.println("<div>"+ errors.get("nome_catg") +"</div>"); %>
                            </fieldset>
                        </div>
                        <fieldset>
                           <button name="submit" type="submit" data-submit="...Sending">Enviar</button>
                        </fieldset>
                    </form>
                </main>
            </div>
        </div>
    </body>
</html>
