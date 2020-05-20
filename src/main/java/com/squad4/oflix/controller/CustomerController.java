/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.squad4.oflix.controller;

import com.squad4.oflix.model.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author savio
 */
@WebServlet(urlPatterns = {"/clientes/*"})
public class CustomerController extends Controller  {
    @Override
    protected void doIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> params = new HashMap(request.getParameterMap());
        params.put("where", "id_func = 1");
        params = User.getResources(params);
        request.setAttribute("resources" , params.get("resources"));
        request.setAttribute("pageNumbers", params.get("pageQuantity"));
        request.getRequestDispatcher("/Clientes/index.jsp").forward(request, response);
    }
    
    @Override
    protected void doNew(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("method", "Novo");
        try { request.setAttribute("functionSelect", User.selectFunction(true)); } catch (SQLException | ClassNotFoundException ex) {}
        request.getRequestDispatcher("/Clientes/form.jsp").forward(request, response);
    }
    
    @Override
    protected void doCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] rejectedParams = {"ativo", "id_func"};
        User newUser = new User(rejectParams(request.getParameterMap(), rejectedParams));
        newUser.setCustomer();
        
        if(newUser.save()){
            response.sendRedirect(request.getAttribute("simplePath") + "/clientes");
        }
        else{
            request.setAttribute("method", "Novo");
            try { request.setAttribute("functionSelect", User.selectFunction(true)); } catch (SQLException | ClassNotFoundException ex) {}
            request.setAttribute("errors", newUser.getErrors());
            request.setAttribute("params", request.getParameterMap());
            request.getRequestDispatcher("/Clientes/form.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> params = new HashMap();
        params.put("where", "id_func = 1");

        User newUser = User.find((int) request.getAttribute("id"), params);
        
        request.setAttribute("params", newUser.toMap());
        request.setAttribute("method", "Editar");

        try { request.setAttribute("functionSelect", User.selectFunction(true)); } catch (SQLException | ClassNotFoundException ex) {}
        request.getRequestDispatcher("/Clientes/form.jsp").forward(request, response);
    }
    
    @Override
    protected void doUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> params = new HashMap();
        params.put("where", "id_func = 1");
        String[] rejectedParams = {"ativo", "cpf", "id_func"};
        User newUser = User.find((int) request.getAttribute("id"), params);
        newUser.update(rejectParams(request.getParameterMap(), rejectedParams));
        
        if(newUser.save()){
            response.sendRedirect(request.getAttribute("simplePath") + "/clientes");
        }
        else{
            request.setAttribute("method", "Editar");
            try { request.setAttribute("functionSelect", User.selectFunction(true)); } catch (SQLException | ClassNotFoundException ex) {}
            request.setAttribute("errors", newUser.getErrors());
            request.setAttribute("params", request.getParameterMap());
            request.getRequestDispatcher("/Clientes/form.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doDestroy(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> params = new HashMap();
        params.put("where", "id_func = 1");
        User newUser = User.find((int) request.getAttribute("id"), params);
        newUser.destroy();
        
        response.sendRedirect(request.getAttribute("simplePath") + "/clientes");
    }
}
