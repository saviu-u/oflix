<%-- 
    Document   : header
    Created on : 29 de mar de 2020, 18:42:42
    Author     : savio
--%>

<div class="topnav">
    <%
        String path = (String) request.getAttribute("simplePath");
        if(path == null) path = "http://"+request.getServerName()+
                                ":"+request.getLocalPort()+
                                request.getContextPath();
    %>
    <a href="<%out.println(path);%>">Home</a>
    <a href="<%out.println(path);%>/usuarios">Usuários</a>
    <a href="<%out.println(path);%>/clientes">Clientes</a>
    <a href="<%out.println(path);%>/categorias">Categorias</a>
    <a href="<%out.println(path);%>/filmes">Filmes</a>
    <a href="<%out.println(path);%>/locacao">Locação</a>
</div>