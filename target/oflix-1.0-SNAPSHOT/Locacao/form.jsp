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
        <title>Locação - <% out.println(method); %></title>
    </head>
        <body>
            <%
                Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
                Map<String, String[]> params = (Map<String, String[]>) request.getAttribute("params");
                Map<String, String> customers = (Map<String, String>) request.getAttribute("customersAvailable");
                Map<String, String> movies = (Map<String, String>) request.getAttribute("moviesAvailable");
                if(params == null) params = new HashMap();
                if(errors == null) errors = new HashMap();
                if(customers == null) customers = new HashMap();
                if(movies == null) movies = new HashMap();
            %>
            <div class="container">
                <header>
                    <jsp:include page="../WEB-INF/Utilidades/header.jsp"></jsp:include>
                </header>
                   
                <main>
                    <div class="background">
                    <form class="form" method="POST">
                        <div>
                            <label class="category">Dados da locação: </label>
                            <fieldset <% if(errors.get("data_aluguel") != null) out.println("class=error"); %>>
                                <label class="required">Data de locação</label>
                                <input name="data_aluguel" type="date"
                                    <% if(params.get("data_aluguel") != null) out.println("value=\""+ params.get("data_aluguel")[0] + "\""); %>
                                >
                                <% if(errors.get("data_aluguel") != null) out.println("<div>"+ errors.get("data_aluguel") +"</div>"); %>
                            </fieldset>
                            
                            <fieldset <% if(errors.get("id_pes") != null) out.println("class=error"); %>>
                                <label class="required">Cliente</label>
                                <select name="id_pes">
                                    <option value="" selected disabled hidden></option>
                                    <%
                                        for(String key : customers.keySet()){
                                            String selected = "";
                                            if(params.get("id_pes") != null && customers.get(key).equals(params.get("id_pes")[0])) selected = "selected";
                                            out.println("<option value=" + customers.get(key)+ " " + selected + ">" + key + "</option>");
                                        }
                                    %>
                                </select>
                                <% if(errors.get("id_pes") != null) out.println("<div>"+ errors.get("id_pes") +"</div>"); %>
                            </fieldset>
                            <fieldset <% if(errors.get("id_filme") != null) out.println("class=error"); %>>
                                <label class="required">Filmes</label>
                                <select name="id_filme">
                                    <option value="" selected disabled hidden></option>
                                    <%
                                        for(String key : movies.keySet()){
                                            String selected = "";
                                            if(params.get("id_filme") != null && movies.get(key).equals(params.get("id_filme")[0])) selected = "selected";
                                            out.println("<option value=" + movies.get(key)+ " " + selected + ">" + key + "</option>");
                                        }
                                    %>
                                </select>
                                <% if(errors.get("id_filme") != null) out.println("<div>"+ errors.get("id_filme") +"</div>"); %>
                            </fieldset>
                            
                            <fieldset <% if(errors.get("ativo") != null) out.println("class=error"); %>>
                                <label class="required">Devolvido: </label>
                                <input type="radio" name="ativo" required="required" value="false" <% if(params.get("ativo") == null || params.get("ativo")[0].equals("false")) out.println("checked"); %>>
                                <label class="tipo_sexo">Não</label>
                                <input type="radio" name="ativo" required="required" value="true" <% if(params.get("ativo") != null && params.get("ativo")[0].equals("true")) out.println("checked"); %>>
                                <label class="tipo_sexo">Sim</label>
                                <% if(errors.get("ativo") != null) out.println("<div>"+ errors.get("ativo") +"</div>"); %>
                                <br>
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
