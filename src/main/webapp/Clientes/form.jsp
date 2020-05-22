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
        <title>Clientes - <% out.println(method); %></title>
    </head>
        <body>
            <%
                Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
                Map<String, String[]> params = (Map<String, String[]>) request.getAttribute("params");
                Map<String, String> functions = (Map<String, String>) request.getAttribute("functionSelect");
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
                            <label class="category">Dados pessoais: </label>
                            <fieldset <% if(errors.get("nome") != null) out.println("class=error"); %>>
                                <label class="required">Nome</label>
                                <input name="nome" type="text" placeholder="Nome Sobrenome"
                                    <% if(params.get("nome") != null) out.println("value=\""+ params.get("nome")[0] + "\""); %>
                                >
                                <% if(errors.get("nome") != null) out.println("<div>"+ errors.get("nome") +"</div>"); %>
                            </fieldset>
                            <fieldset <% if(errors.get("cpf") != null) out.println("class=error"); %>>
                                <label class="required">CPF</label>
                                <input name="cpf" type="text" maxlength="14" placeholder="XXX.XXX.XXX-XX"
                                    <% if(edit) out.println("readonly"); %>
                                    <% if(params.get("cpf") != null) out.println("value=\""+ params.get("cpf")[0] + "\""); %>
                                >
                                <% if(errors.get("cpf") != null) out.println("<div>"+ errors.get("cpf") +"</div>"); %>
                            </fieldset>
                            <fieldset <% if(errors.get("email") != null) out.println("class=error"); %>>
                                <label class="required">Email</label>
                                <input name="email" type="email" placeholder="email@email.com"
                                    <% if(params.get("email") != null) out.println("value=\""+ params.get("email")[0] + "\""); %>
                                >
                                <% if(errors.get("email") != null) out.println("<div>"+ errors.get("email") +"</div>"); %>
                            </fieldset>
                            <!--
                            <fieldset>
                                <label class="required">Senha</label>
                                <input id="senha" type="password" maxlength="10"/>
                            </fieldset>
                            -->
                            <fieldset <% if(errors.get("sexo") != null) out.println("class=error"); %>>
                                <label class="required">Sexo: </label>
                                <input type="radio" name="sexo" required="required" value="M" <% if(params.get("sexo") == null || params.get("sexo")[0].equals("M")) out.println("checked"); %>>
                                <label class="tipo_sexo">Masculino</label>
                                <input type="radio"  name="sexo" required="required" value="F" <% if(params.get("sexo") != null && params.get("sexo")[0].equals("F")) out.println("checked"); %>>
                                <label class="tipo_sexo">Feminino</label>
                                <input type="radio"  name="sexo" required="required" value="O" <% if(params.get("sexo") != null && params.get("sexo")[0].equals("O")) out.println("checked"); %>>
                                <label class="tipo_sexo">Outros</label>
                                <% if(errors.get("sexo") != null) out.println("<div>"+ errors.get("sexo") +"</div>"); %>
                                <br>
                            </fieldset>
                        </div>
                        <br>
                        <div>
                            <label class="category">Dados de contato: </label>
                            <fieldset <% if(errors.get("telefone_1") != null) out.println("class=error"); %>>
                                <label class="required">Telefone</label>
                                <input name="telefone_1" type="tel" maxlength="8" placeholder="XXXXXXXX"
                                    <% if(params.get("telefone_1") != null) out.println("value=\""+ params.get("telefone_1")[0] + "\""); %>
                                >
                                <% if(errors.get("telefone_1") != null) out.println("<div>"+ errors.get("telefone_1") +"</div>"); %>
                            </fieldset>
                            <fieldset <% if(errors.get("telefone_2") != null) out.println("class=error"); %>>
                                <label>Celular</label>
                                <input name="telefone_2" type="tel" maxlength="9" placeholder="XXXXXXXXx"
                                    <% if(params.get("telefone_2") != null) out.println("value=\""+ params.get("telefone_2")[0] + "\""); %>
                                >
                                <% if(errors.get("telefone_2") != null) out.println("<div>"+ errors.get("telefone_2") +"</div>"); %>
                            </fieldset>
                            </div>
                            <br>
                        <div>
                            <label class="category">Endereço: </label>
                            <fieldset <% if(errors.get("estado") != null) out.println("class=error"); %>>
                                <label class="required">Estado</label>
                                <input name="estado" type="text" placeholder="São Paulo"
                                    <% if(params.get("estado") != null) out.println("value=\""+ params.get("estado")[0] + "\""); %>
                                >
                                <% if(errors.get("estado") != null) out.println("<div>"+ errors.get("estado") +"</div>"); %>
                            </fieldset>
                            <fieldset <% if(errors.get("cidade") != null) out.println("class=error"); %>>
                                <label class="required">Cidade</label>
                                <input name="cidade" type="text" placeholder="Ribeirão Preto"
                                    <% if(params.get("cidade") != null) out.println("value=\""+ params.get("cidade")[0] + "\""); %>
                                >
                                <% if(errors.get("cidade") != null) out.println("<div>"+ errors.get("cidade") +"</div>"); %>
                            </fieldset>
                            <fieldset <% if(errors.get("bairro") != null) out.println("class=error"); %>>
                                <label class="required">Bairro</label>
                                <input name="bairro" type="text" placeholder="Jardim dos Palmaers"
                                    <% if(params.get("bairro") != null) out.println("value=\""+ params.get("bairro")[0] + "\""); %>
                                >
                                <% if(errors.get("bairro") != null) out.println("<div>"+ errors.get("bairro") +"</div>"); %>
                            </fieldset>
                            <fieldset <% if(errors.get("rua") != null) out.println("class=error"); %>>
                                <label class="required">Rua</label>
                                <input name="rua" type="text" placeholder="Rua, Logradouro"
                                    <% if(params.get("rua") != null) out.println("value=\""+ params.get("rua")[0] + "\""); %>
                                >
                                <% if(errors.get("rua") != null) out.println("<div>"+ errors.get("rua") +"</div>"); %>
                            </fieldset>
                            <fieldset <% if(errors.get("numero") != null) out.println("class=error"); %>>
                                <label class="required">Numero</label>
                                <input name="numero" type="text" placeholder="202"
                                    <% if(params.get("numero") != null) out.println("value=\""+ params.get("numero")[0] + "\""); %>
                                >
                                <% if(errors.get("numero") != null) out.println("<div>"+ errors.get("numero") +"</div>"); %>
                            </fieldset>
                            <fieldset <% if(errors.get("complemento") != null) out.println("class=error"); %>>
                                <label class>Complemento</label>
                                <input name="complemento" type="text" placeholder="Apto 26."
                                    <% if(params.get("complemento") != null) out.println("value=\""+ params.get("complemento")[0] + "\""); %>
                                >
                                <% if(errors.get("complemento") != null) out.println("<div>"+ errors.get("complemento") +"</div>"); %>
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
