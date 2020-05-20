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

@WebServlet(urlPatterns = {"/categorias/*"})

public class CategoryController extends Controller {
    @Override
    protected void doIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> params = new HashMap(request.getParameterMap());
        params = Category.getResources(params);
        request.setAttribute("resources" , params.get("resources"));
        request.setAttribute("pageNumbers", params.get("pageQuantity"));
        request.getRequestDispatcher("/Categoria/index.jsp").forward(request, response);
    }
    
    @Override
    protected void doNew(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("method", "Novo");
        request.getRequestDispatcher("/Categoria/form.jsp").forward(request, response);
    }
    
    @Override
    protected void doCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Category category = new Category(request.getParameterMap());
        
        if(category.save()){
            response.sendRedirect(request.getAttribute("simplePath") + "/categorias");
        }
        else{
            request.setAttribute("method", "Novo");
            request.setAttribute("errors", category.getErrors());
            request.setAttribute("params", request.getParameterMap());
            request.getRequestDispatcher("/Categoria/form.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> params = new HashMap();

        Category category = Category.find((int) request.getAttribute("id"), params);
        if(category.getId() == null){
            response.setStatus(404);
            return;
        }
        
        request.setAttribute("params", category.toMap());
        request.setAttribute("method", "Editar");

        request.getRequestDispatcher("/Categoria/form.jsp").forward(request, response);
    }
    
    @Override
    protected void doUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> params = new HashMap();
        Category category = Category.find((int) request.getAttribute("id"), params);
        category.update(request.getParameterMap());
        
        if(category.save()){
            response.sendRedirect(request.getAttribute("simplePath") + "/categorias");
        }
        else{
            request.setAttribute("method", "Editar");
            request.setAttribute("errors", category.getErrors());
            request.setAttribute("params", request.getParameterMap());
            request.getRequestDispatcher("/Categoria/form.jsp").forward(request, response);
        }
    }
}
