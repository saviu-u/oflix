<%-- 
    Document   : form
    Created on : 28 de abr de 2020, 19:56:13
    Author     : savio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/format.css"/>     <%-- não está linkando --%>
        <link rel="stylesheet" type="text/css" href="css/formatForm.css"/> <%-- não está linkando --%>
        
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
            
            
                        /*   format.css   */
            .container {
                display: grid;
                grid-template-rows:   10vh 100% 10vh;
                grid-template-areas: "h"
                    "m"
                    "f"
            }

            header{
                background: #f5f5f5;
                grid-area: h;

            }

            main{
                padding:20px;
                background: white;
                border-radius: 5px;
                margin: 0 auto;
                grid-area: m;
                font-family: 'Varela Round', sans-serif;
                font-size: 16px;
                min-height: 600px;

            }

            footer{
                grid-area:"f";
                background: #f5f5f5;
            }


            img{
                width: 18px;
                height: 18px;

            }
            .icon{
                border-radius: 5px;
            }
            /* fim */

            /* form.css */
            fieldset{
                border: medium none !important;
                min-width: 100%;
                padding: 10px 0 0 0;
                width: 100%;
                
            }
            
            .form input[type="tel"],
            .form input[type="text"],
            .form input[type="email"],
            .form button[type="submit"]
            
            {
                width: 100%;
                border: 1px solid #ccc;
                background: #FFF;
                margin: 0 0 5px;
                padding: 10px;
            }
            .form button[type="submit"] {
                cursor: pointer;
                width: 100%;
                border: none;
                background: #299be4;
                color: #FFF;
                margin: 0 0 5px;
                padding: 10px;
                font-size: 15px;
            }
            
            .tipo_sexo{
                font-size: 15px;
            }

            label{
                font-size: 18px;
                padding-left: 5px;
            }
            select{
                width: 100%;
                border: 1px solid #ccc;
                background: #FFF;
                margin: 0 0 5px;
                padding: 10px;
            }
            .fiel_radio{
                display: block;
                position: relative;
                padding-left: 35px;
                margin-bottom: 12px;
                cursor: pointer;
                font-size: 22px;
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                user-select: none;
            }
            .form button[type="submit"]:hover {
                background: #f5f5f5;
                -webkit-transition: background 0.3s ease-in-out;
                -moz-transition: background 0.3s ease-in-out;
                transition: background-color 0.3s ease-in-out;
                color: black;
            }
            .form button[type="submit"]:active {
                box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.5);
            }           
            /* fim */
            
            
            
            </style>
    </head>
        <body>
            <div class="container">
                <header></header>
                <main>
                <jsp:include page="../WEB-INF/Utilidades/header.jsp"></jsp:include>
                <form class="form" action="<% out.println((String) request.getAttribute("pathToSearch"));%>/new" method="POST">
                    <fieldset>
                        <input id="nome" required="required" type="text" placeholder="Nome" >
                    </fieldset>
                    <fieldset>
                        <input id="cpf"  maxlength="11" minlength="11" required="required" type="text" placeholder="Cpf" pattern="[0-9]+$">
                    </fieldset>
                    <fieldset>
                        <input type="email" required="required" placeholder="Email" class="input-text" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"/>
                    </fieldset>
                    <fieldset>
                        <input type="tel" required="required" maxlength="11" minlength="10 "id="telefone_1" pattern="[0-9]+$" placeholder="Telefone: formato 16994004439"/>
                    </fieldset>
                    <fieldset>
                        <input id="telefone2" required="required" maxlength="11" minlength="10" type="tel" pattern="[0-9]+$" placeholder="Celular: formato 16994004439"" />
                    </fieldset>
                    <fieldset class="Fiel_radio">
                        <label>Sexo: </label>
                        <label class="tipo_sexo">Masculino
                            <input type="radio" name="radio"  required="required" value="M">
                        <label class="tipo_sexo">Feminino
                            <input type="radio"  name="radio"  required="required" value="F">
                        <label class="tipo_sexo">Outros
                            <input type="radio"  name="radio" required="required" value="O">
                    </fieldset>
                    <fieldset>
                        <input id="senha" required="required" type="text" placeholder="Senha" >
                    </fieldset>
                        <br>
                        <label>Endereço: </label>
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
                        <input id="cidade" required="required" type="text" placeholder="Cidade" >
                    </fieldset>
                    <fieldset>
                        <input id="bairro" required="required" type="text" placeholder="Bairro" >
                    </fieldset>
                     <fieldset>
                        <input id="rua" required="required" type="text" placeholder="Rua" >
                    </fieldset>
                    <fieldset>
                        <input id="num_residencia" required="required" type="text" pattern="[0-9]+$" placeholder="Numero da residência" >
                    </fieldset>
                    <fieldset>
                        <input id="complemento" required="required" type="text" placeholder="Complemento" >
                    </fieldset>
                    <fieldset>
                       <button name="submit" required="required" type="submit" data-submit="...Sending">Enviar</button>
                    </fieldset>
                </form>                   
            </main>
            <footer></footer>
        </div>
    </body>
</html>
