<%-- 
    Document   : headercss
    Created on : 29 de mar de 2020, 19:59:51
    Author     : savio
--%>

.search-container input[type=text] {
    width: 600px;
    padding: 6px;
    font-size: 17px;
}

.search-container button {
    padding: 6px 10px;
    margin-top: 8px;
    margin-left: 14px;
    background: #ddd;
    font-size: 17px;
    border: none;
    cursor: pointer;
    
}

.search-container button:hover {
    background: #ccc;
}

.topnav {
    border-radius:3px;
    overflow: hidden;
    background-color: #299be4;
}

.topnav a {
    float: left;
    display: block;
    color: #fff;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
    font-size: 17px;
}

.topnav a:hover {
    background-color: #f5f5f5;
    color: black;
    
}

.topnav a.active {
    background-color: #2196F3;
    color: white;
}