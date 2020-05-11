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
        <title>Usuários - <% out.println((String) request.getAttribute("method")); %></title>
    </head>
        <body>
            <%
                Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
                if(errors == null) errors= new HashMap<String, String>();
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
                        <label class="category">Dados pessoais: </label>
                        <fieldset <% if(errors.get("nome") != null) out.println("class=error"); %>>
                            <label class="required">Nome</label>
                            <input name="nome" type="text" placeholder="Nome Sobrenome">
                            <% if(errors.get("nome") != null) out.println("<div>"+ errors.get("nome") +"</div>"); %>
                        </fieldset>
                        <fieldset <% if(errors.get("cpf") != null) out.println("class=error"); %>>
                            <label class="required">CPF</label>
                            <input name="cpf" type="text" maxlength="11" placeholder="XXX.XXX.XXX-XX"/>
                            <% if(errors.get("cpf") != null) out.println("<div>"+ errors.get("cpf") +"</div>"); %>
                        </fieldset>
                        <fieldset <% if(errors.get("email") != null) out.println("class=error"); %>>
                            <label class="required">Email</label>
                            <input name="email" type="email" placeholder="email@email.com"/>
                            <% if(errors.get("email") != null) out.println("<div>"+ errors.get("email") +"</div>"); %>
                        </fieldset>
                         <fieldset <% if(errors.get("senha") != null) out.println("class=error"); %>>
                            <label class="required">Senha</label>
                            <input id="senha" type="password" maxlength="10"/>
                            <% if(errors.get("senha") != null) out.println("<div>"+ errors.get("senha") +"</div>"); %>
                        </fieldset>
                        <fieldset <% if(errors.get("sexo") != null) out.println("class=error"); %>>
                            <label class="required">Sexo: </label>
                            <input type="radio" name="sexo" required="required" value="M" checked>
                            <label class="tipo_sexo">Masculino</label>
                            <input type="radio"  name="sexo" required="required" value="F">
                            <label class="tipo_sexo">Feminino</label>
                            <input type="radio"  name="sexo" required="required" value="O">
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
                            <input name="telefone_1" type="tel" maxlength="8" placeholder="XXXXXXXX"/>
                            <% if(errors.get("telefone_1") != null) out.println("<div>"+ errors.get("telefone_1") +"</div>"); %>
                        </fieldset>
                        <fieldset <% if(errors.get("telefone_2") != null) out.println("class=error"); %>>
                            <label class="required">Celular</label>
                            <input name="telefone_2" type="tel" maxlength="9" placeholder="XXXXXXXXx"/>
                            <% if(errors.get("telefone_2") != null) out.println("<div>"+ errors.get("telefone_2") +"</div>"); %>
                        </fieldset>
                        </div>
                        <br>
                    <div>
                        <label class="category">Endereço: </label>
                        <fieldset>
                            <select name="estados-brasil">
                                <option value="AC">Acre</option>
                                <option value="AL">Alagoas</option>
                                <option value="AP">Amapá</option>
                                <option value="AM">Amazonas</option>
                                <option value="BA">Bahia</option>
                                <option value="CE">Ceará</option>
                                <option value="DF">Distrito Federal</option>
                                <option value="ES">Espírito Santo</option>
                                <option value="GO">Goiás</option>
                                <option value="MA">Maranhão</option>
                                <option value="MT">Mato Grosso</option>
                                <option value="MS">Mato Grosso do Sul</option>
                                <option value="MG">Minas Gerais</option>
                                <option value="PA">Pará</option>
                                <option value="PB">Paraíba</option>
                                <option value="PR">Paraná</option>
                                <option value="PE">Pernambuco</option>
                                <option value="PI">Piauí</option>
                                <option value="RJ">Rio de Janeiro</option>
                                <option value="RN">Rio Grande do Norte</option>
                                <option value="RS">Rio Grande do Sul</option>
                                <option value="RO">Rondônia</option>
                                <option value="RR">Roraima</option>
                                <option value="SC">Santa Catarina</option>
                                <option value="SP">São Paulo</option>
                                <option value="SE">Sergipe</option>
                                <option value="TO">Tocantins</option>
                            </select>
                        </fieldset>
                        <fieldset>
                            <label class="required">Cidade</label>
                            <input name="cidade" type="text" placeholder="Ribeirão Preto">
                        </fieldset>
                        <fieldset>
                            <label class="required">Bairro</label>
                            <input name="bairro" type="text" placeholder="Jardim dos Palmaers" >
                        </fieldset>
                         <fieldset>
                             <label class="required">Rua</label>
                            <input name="rua" type="text" placeholder="Rua, Logradouro" >
                        </fieldset>
                        <fieldset>
                            <label class="required">Numero</label>
                            <input name="numero" type="text" placeholder="202">
                        </fieldset>
                        <fieldset>
                            <label class>Complemento</label>
                            <input name="complemento" type="text" placeholder="Apto 26." >
                        </fieldset>
                    </div>
                    <fieldset>
                       <button name="submit" type="submit" data-submit="...Sending">Enviar</button>
                    </fieldset>
                </form>
                    </div>
            </main>
                        
            
        </div>
    </body>
</html>
