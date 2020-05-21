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

@WebServlet(urlPatterns = {"/locacao/*"})

public class RentsController extends Controller {
    @Override
    protected void doIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> params = new HashMap(request.getParameterMap());
        params = Rent.getResources(params);
        request.setAttribute("resources" , params.get("resources"));
        request.setAttribute("pageNumbers", params.get("pageQuantity"));
        request.getRequestDispatcher("/Locacao/index.jsp").forward(request, response);
    }
    
    @Override
    protected void doNew(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("method", "Novo");
        try { request.setAttribute("customersAvailable", Rent.customersAvailable()); } catch (SQLException | ClassNotFoundException ex) {}
        try { request.setAttribute("moviesAvailable", Rent.moviesAvailable()); } catch (SQLException | ClassNotFoundException ex) {}
        request.getRequestDispatcher("/Locacao/form.jsp").forward(request, response);
    }
    
    @Override
    protected void doCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Rent rent = new Rent(request.getParameterMap());
        
        if(rent.save()){
            response.sendRedirect(request.getAttribute("simplePath") + "/locacao");
        }
        else{
            request.setAttribute("method", "Novo");
            try { request.setAttribute("customersAvailable", Rent.customersAvailable()); } catch (SQLException | ClassNotFoundException ex) {}
            try { request.setAttribute("moviesAvailable", Rent.moviesAvailable()); } catch (SQLException | ClassNotFoundException ex) {}
            request.setAttribute("errors", rent.getErrors());
            request.setAttribute("params", request.getParameterMap());
            request.getRequestDispatcher("/Locacao/form.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> params = new HashMap();

        Rent rent = Rent.find((int) request.getAttribute("id"), params);
        if(rent.getId() == null){
            response.setStatus(404);
            return;
        }     
        
        request.setAttribute("params", rent.toMap());
        request.setAttribute("method", "Editar");

        try { request.setAttribute("customersAvailable", Rent.customersAvailable(rent.getPesId())); } catch (SQLException | ClassNotFoundException ex) {}
        try { request.setAttribute("moviesAvailable", Rent.moviesAvailable(rent.getFilmeId())); } catch (SQLException | ClassNotFoundException ex) {}
        request.getRequestDispatcher("/Locacao/form.jsp").forward(request, response);
    }
    
    @Override
    protected void doUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, Object> params = new HashMap();
        String[] rejectedParams = {"data_aluguel"};
        Rent rent = Rent.find((int) request.getAttribute("id"), params);
        rent.update(rejectParams(request.getParameterMap(), rejectedParams));
        
        if(rent.save()){
            response.sendRedirect(request.getAttribute("simplePath") + "/locacao");
        }
        else{
            request.setAttribute("method", "Editar");
            try { request.setAttribute("customersAvailable", Rent.customersAvailable(rent.getPesId())); } catch (SQLException | ClassNotFoundException ex) {}
            try { request.setAttribute("moviesAvailable", Rent.moviesAvailable(rent.getFilmeId())); } catch (SQLException | ClassNotFoundException ex) {}
            request.setAttribute("errors", rent.getErrors());
            request.setAttribute("params", request.getParameterMap());
            request.getRequestDispatcher("/Locacao/form.jsp").forward(request, response);
        }
    }
}
