<%-- 
    Document   : form
    Created on : 28 de abr de 2020, 19:56:13
    Author     : savio
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
        <title>Filmes - <% out.println(method); %></title>
    </head>
    <body>
        <%
            Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
            Map<String, String[]> params = (Map<String, String[]>) request.getAttribute("params");
            Map<String, String> functions = (Map<String, String>) request.getAttribute("functionCategory");
            if(params == null) params = new HashMap();
            if(errors == null) errors = new HashMap();
            if(functions == null) functions = new HashMap();
        %>
        <header>
            <jsp:include page="../WEB-INF/Utilidades/header.jsp"></jsp:include>
        </header>
        <div class="container">
            <main>
                <div class="background">
                <form class="form" method="POST">
                    <div>
                        <label class="category">Dados do filme: </label>
                        <fieldset <% if(errors.get("nome_filme") != null) out.println("class=error"); %>>
                            <label class="required">Nome</label>
                            <input name="nome_filme" type="text" placeholder="Título.."
                                <% if(params.get("nome_filme") != null) out.println("value=\""+ params.get("nome_filme")[0] + "\""); %>
                            >
                            <% if(errors.get("nome_filme") != null) out.println("<div>"+ errors.get("nome_filme") +"</div>"); %>
                        </fieldset>
                        <fieldset <% if(errors.get("id_catg") != null) out.println("class=error"); %>>
                            <label class="required">Categoria</label>
                            <select name="id_catg">
                                <option value="" selected disabled hidden></option>
                                <%
                                    for(String key : functions.keySet()){
                                        String selected = "";
                                        if(params.get("id_catg") != null && functions.get(key).equals(params.get("id_catg")[0])) selected = "selected";
                                        out.println("<option value=" + functions.get(key)+ " " + selected + ">" + key + "</option>");
                                    }
                                %>
                            </select>
                            <% if(errors.get("id_catg") != null) out.println("<div>"+ errors.get("id_catg") +"</div>"); %>
                        </fieldset>
                        <fieldset <% if(errors.get("sinopse") != null) out.println("class=error"); %>>
                            <label>Sinopse</label>
                            <textarea name="sinopse" maxlength="511" rows="5" cols="60"><% if(params.get("sinopse") != null) out.println(params.get("sinopse")[0]); %></textarea>
                            <% if(errors.get("sinopse") != null) out.println("<div>"+ errors.get("sinopse") +"</div>"); %>
                        </fieldset>
                        <fieldset <% if(errors.get("quant_estoque") != null) out.println("class=error"); %>>
                            <label class="required">Estoque</label>
                            <input name="quant_estoque" type="tel" maxlength="8"
                                <% if(params.get("quant_estoque") != null) out.println("value=\""+ params.get("quant_estoque")[0] + "\""); %>
                            >
                            <% if(errors.get("quant_estoque") != null) out.println("<div>"+ errors.get("quant_estoque") +"</div>"); %>
                        </fieldset>
                        <fieldset <% if(errors.get("class_indicativa") != null) out.println("class=error"); %>>
                            <label class="required">Classificação</label>
                            <input name="class_indicativa" type="tel" maxlength="8"
                                <% if(params.get("class_indicativa") != null) out.println("value=\""+ params.get("class_indicativa")[0] + "\""); %>
                            >
                            <% if(errors.get("class_indicativa") != null) out.println("<div>"+ errors.get("class_indicativa") +"</div>"); %>
                        </fieldset>
                    </div>
                    <fieldset>
                       <button name="submit" type="submit" data-submit="...Sending">Enviar</button>
                    </fieldset>
                </form>
            </main>
        </div>
    </body>
</html>
