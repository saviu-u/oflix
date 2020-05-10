package com.squad4.oflix.controller;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author savio
 */
public class Controller extends HttpServlet {
    
    // Setting variables to some URL params
    private int id;
    private String action;
    private boolean badRequest;
 
    protected boolean processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // Gets the simple path for frontend purposes
        String path =
            "http://"+request.getServerName()+
            ":"+request.getLocalPort()+
            request.getContextPath();
        // Usually it's http://localhost:8080/oflix/
        request.setAttribute("simplePath", path);

        // Gets a helper for the search bar
        // /oflix/usuarios for example
        request.setAttribute(
            "pathToSearch",
            request.getRequestURI()
        );

        // Add full path to a request
        path += request.getServletPath();
        if(request.getPathInfo() != null) path += request.getPathInfo();
        request.setAttribute("path", path);
        
        // Add params to a attribute
        request.setAttribute("params", request.getParameterMap());

        id = getId(request);
        action = getAction(request);
        // Sends a bad request in case the address does not match
        badRequest = extraParameters(request);
        
        // Adds the ID to a atribute
        request.setAttribute("id", id);
        response.setContentType("text/html;charset=UTF-8");
        
        if (badRequest){ response.setStatus(404); return false; }
        return true;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        // Possible cases of a GET verb
        switch (action) {
            case "index":
                doIndex(request, response);
                break;
            case "edit":
                doEdit(request, response);
                break;
            case "new":
                doNew(request, response);
                break;
            default:
                response.setStatus(404);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        if("new".equals(action)){
            doCreate(request, response);
            return;
        }
        response.setStatus(404);
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        if("index".equals(action)){
            doDestroy(request, response);
            return;
        }
        response.setStatus(404);
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        if("edit".equals(action)){
            doUpdate(request, response);
            return;
        }
        response.setStatus(404);
    }
    
    protected void doIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(404);
    }
    
    protected void doShow(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(404);
    }
    
    protected void doEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(404);
    }
    
    protected void doNew(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(404);
    }
    
    protected void doCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(404);
    }

    protected void doUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(404);
    }
    
    protected void doDestroy(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(404);
    }

    @Override
    public String getServletInfo() {
        return "";
    }// </editor-fold>
    
    private void AddParameters(HttpServletRequest request, HttpServletResponse response){
    }
    private int getId(HttpServletRequest request){
        id = -1;
        
        try {
            id = Integer.parseInt(request.getPathInfo().split("/")[1]);
            if(id <= 0) id = -1;
        }
        catch(Exception e){ id = -1; }
        
        return id;
    }
    private String getAction(HttpServletRequest request){
        action = "";
        boolean idBool = id != -1;
        int index = 1;
        if(idBool) index++ ;

        try{ action = request.getPathInfo().split("/")[index]; }
        catch(Exception e){ action = ""; }
        
        if("".equals(action)){
            if(idBool) action = "show";
            else action = "index";
        }
        return action;
    }
    private boolean extraParameters(HttpServletRequest request){
        int i;
        int args = 0;
        if(id != -1) args++ ;
        if(!"index".equals(action) && !"show".equals(action) ) args++ ;
        
        try{
            i = request.getPathInfo().split("/").length;
            if (i != 0) args++;
        }
        catch(Exception e){
            i = 0;
        }
        return !(args == i);
    }
}
