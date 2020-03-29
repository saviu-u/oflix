package com.squad4.oflix.resources;

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
    
    int id;
    String action;
    boolean badRequest;
 
    protected boolean processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String path =
            "http://"+request.getServerName()+
            ":"+request.getLocalPort()+
            request.getContextPath();
        request.setAttribute("simplePath", path);
        
        request.setAttribute(
            "pathToSearch",
            request.getContextPath() + request.getServletPath()
        );

        path += request.getServletPath();
        if(request.getPathInfo() != null) path += request.getPathInfo();
        request.setAttribute("path", path);

        id = getId(request);
        action = getAction(request);
        badRequest = extraParameters(request);
        
        request.setAttribute("id", id);
        response.setContentType("text/html;charset=UTF-8");
        
        if (badRequest){ response.setStatus(404); return false; }
        // response.getWriter().println(id + " | " + action + " | " + badRequest);
        return true;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(!processRequest(request, response));
        switch (action) {
            case "index":
                doIndex(request, response);
                break;
            case "show":
                doShow(request, response);
                break;
            case "edit":
                doEdit(request, response);
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
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        
        if(action == ""){
            if(idBool) action = "show";
            else action = "index";
        }
        return action;
    }
    private boolean extraParameters(HttpServletRequest request){
        int i;
        int args = 0;
        if(id != -1) args++ ;
        if(action != "index" && action != "show" ) args++ ;
        
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
