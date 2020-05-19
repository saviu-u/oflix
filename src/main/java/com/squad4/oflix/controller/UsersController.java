package com.squad4.oflix.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.squad4.oflix.model.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/usuarios/*"})

public class UsersController extends Controller {
    @Override
    protected void doIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> params = new HashMap(request.getParameterMap());
        params.put("where", "NOT id_func = 1");
        params = User.getResources(params);
        request.setAttribute("resources" , params.get("resources"));
        request.setAttribute("pageNumbers", params.get("pageQuantity"));
        request.getRequestDispatcher("/Usuario/index.jsp").forward(request, response);
    }
    
    @Override
    protected void doNew(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("method", "Novo");
        try { request.setAttribute("functionSelect", User.selectFunction()); } catch (SQLException | ClassNotFoundException ex) {}
        request.getRequestDispatcher("/Usuario/form.jsp").forward(request, response);
    }
    
    @Override
    protected void doCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] rejectedParams = {"ativo"};
        User newUser = new User(rejectParams(request.getParameterMap(), rejectedParams));
        
        if(newUser.save()){
            response.sendRedirect(request.getAttribute("simplePath") + "/usuarios");
        }
        else{
            request.setAttribute("method", "Novo");
            try { request.setAttribute("functionSelect", User.selectFunction()); } catch (SQLException | ClassNotFoundException ex) {}
            request.setAttribute("errors", newUser.getErrors());
            request.setAttribute("params", request.getParameterMap());
            request.getRequestDispatcher("/Usuario/form.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("method", "Novo");
        request.getRequestDispatcher("/Usuario/form.jsp").forward(request, response);
    }
}
