<%-- 
    Document   : searchbar
    Created on : 29 de mar de 2020, 19:59:40
    Author     : savio
--%>

<div class="search-container">
    <form action="
    <% out.println((String) request.getAttribute("pathToSearch")); %>
    ">
        <input type="text" placeholder="Search.." name="search" class="textbox">
        <button type="submit" class="icon"><img src="img/search3.png"></button>
    </form>
</div>
