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
import java.util.HashSet;
import java.util.Map;

@WebServlet(urlPatterns = {"/filmes/*"})

public class MoviesController extends Controller {
    @Override
    protected void doIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> params = new HashMap(request.getParameterMap());
        params = Movie.getResources(params);
        request.setAttribute("resources" , params.get("resources"));
        request.setAttribute("pageNumbers", params.get("pageQuantity"));
        request.getRequestDispatcher("/Filme/index.jsp").forward(request, response);
    }
    
    @Override
    protected void doNew(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("method", "Novo");
        try { request.setAttribute("functionCategory", Movie.selectCategory()); } catch (SQLException | ClassNotFoundException ex) {}
        request.getRequestDispatcher("/Filme/form.jsp").forward(request, response);
    }
    
    @Override
    protected void doCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Movie movie = new Movie(request.getParameterMap());
        
        if(movie.save()){
            response.sendRedirect(request.getAttribute("simplePath") + "/filmes");
        }
        else{
            request.setAttribute("method", "Novo");
            try { request.setAttribute("functionCategory", Movie.selectCategory()); } catch (SQLException | ClassNotFoundException ex) {}
            request.setAttribute("errors", movie.getErrors());
            request.setAttribute("params", request.getParameterMap());
            request.getRequestDispatcher("/Filme/form.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> params = new HashMap();

        Movie movie = Movie.find((int) request.getAttribute("id"), params);
        if(movie.getId() == null){
            response.setStatus(404);
            return;
        }
        
        request.setAttribute("params", movie.toMap());
        request.setAttribute("method", "Editar");

        try { request.setAttribute("functionCategory", Movie.selectCategory()); } catch (SQLException | ClassNotFoundException ex) {}
        request.getRequestDispatcher("/Filme/form.jsp").forward(request, response);
    }
    
    @Override
    protected void doUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> params = new HashMap();
        Movie movie = Movie.find((int) request.getAttribute("id"), params);
        movie.update(request.getParameterMap());
        
        if(movie.save()){
            response.sendRedirect(request.getAttribute("simplePath") + "/filmes");
        }
        else{
            request.setAttribute("method", "Editar");
            try { request.setAttribute("functionCategory", Movie.selectCategory()); } catch (SQLException | ClassNotFoundException ex) {}
            request.setAttribute("errors", movie.getErrors());
            request.setAttribute("params", request.getParameterMap());
            request.getRequestDispatcher("/Filme/form.jsp").forward(request, response);
        }
    }
}
