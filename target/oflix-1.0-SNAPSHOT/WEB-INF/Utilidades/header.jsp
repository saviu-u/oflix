<%-- 
    Document   : header
    Created on : 29 de mar de 2020, 18:42:42
    Author     : savio
--%>

<div class="topnav">
    <a href="<%out.println((String) request.getAttribute("simplePath"));%>">Home</a>
    <a href="<%out.println((String) request.getAttribute("simplePath"));%>/usuarios">Usuários</a>
    <a href="<%out.println((String) request.getAttribute("simplePath"));%>/clientes">Clientes</a>
    <a href="<%out.println((String) request.getAttribute("simplePath"));%>/categorias">Categorias</a>
    <a href="<%out.println((String) request.getAttribute("simplePath"));%>/dvds">DVD's</a>
    <a href="<%out.println((String) request.getAttribute("simplePath"));%>/locacao">Locação</a>
</div>