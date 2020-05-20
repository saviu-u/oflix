package com.squad4.oflix.model;

import DAO.DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Category extends Model {
    // Creates all the attributes
    private Integer id = null;
    private String nome_catg;

    public Category() {}
    public Category(Map<String, String[]> paramList){
        super(paramList);
        update(paramList);
    }
    
    public Integer getId(){
        return id;
    }
    
    //Used to update the attributes
    public void update(Map<String, String[]> paramList){
        if(paramList.containsKey("nome_catg")) this.nome_catg = paramList.get("nome_catg")[0];
    }
    
    // Returns a usefull map to the front-end
    public Map<String, String[]> toMap(){
        Map<String, String[]> attributes = new HashMap();
        String temp[] = {""};
        temp[0] = this.nome_catg;
        attributes.put("nome_catg", temp.clone());

        return attributes;
    }
    
    // Valids all the fields
    @Override
    public boolean valid(){
        super.valid();
        validNome(nome_catg);
        return getErrors().isEmpty();
    }

    // Saves the data on the database
    public boolean save(){
        if(!valid()) return false;
        String sql = "";
        if(id == null){
            sql = "INSERT INTO tb_categoria(nome_catg) "
                    + "VALUES (?)";
        }
        else{
            sql = "UPDATE tb_categoria\n" +
                    "SET nome_catg=? \n" +
                    "WHERE ID="+ id + ";";
        }
        try {
            Connection conexao = new DAO().connect();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome_catg);
            stmt.execute();
            
            stmt.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    // Finds an user by its id
    static public Category find(int id,Map<String, Object> params){
        String where = null;
        if(params.get("where") != null) where = (String) params.get("where");

        String sql = "SELECT * FROM tb_categoria WHERE ";
        if(where != null) sql += where + " AND ";
        sql += "tb_categoria.id = ?";
        sql += " LIMIT(1)";
        
        Category resource = new Category();
        Map<String, String[]> attributes = new HashMap(); 
        String temp[] = {""};
        
        try {
            Connection cnt = new DAO().connect();
            PreparedStatement stmt = cnt.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            boolean teste = rs.next();
            if(!teste) return new Category();
            
            resource.id = rs.getInt(1);
            temp[0] = rs.getString(2);
            attributes.put("nome_catg", temp.clone());
            
            resource.update(attributes);
            
            stmt.close();
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resource;
    }

    // Get a list of users
    static public Map<String, Object> getResources(Map<String, Object> params){
        // Mandatory vars
        Map<String, Object> finalReturn = new HashMap();
        int count = 1;
        int page = DEFAULT_PAGE;
        int limit = DEFAULT_LIMIT;
        String where = null;
        String search = null;
        List<Map> resources = new ArrayList<>();
        Map<String, String> resource;
        
        // Set params by received params
        
        try{ if(params.get("page") != null) page = Integer.parseInt(((String[]) params.get("page"))[0]); }catch(NumberFormatException e){}
        try{ if(params.get("limit") != null) limit = Integer.parseInt(((String[]) params.get("limit"))[0]); }catch(NumberFormatException e){}
        try{ if(params.get("where") != null) where = (String) params.get("where"); }catch(Exception e){}
        try{ if(params.get("search") != null) search = ((String[]) params.get("search"))[0]; }catch(Exception e){}
        
        String order = "ORDER BY nome_catg ASC\n" +
                "LIMIT(" + limit + ") OFFSET(" + (page - 1) * limit  + ")\n";
        String select = "id, nome_catg\n";
        String sql = "FROM tb_categoria\n";
        if(where != null) sql += "WHERE " + where;
        if(search != null){
            if(where != null) sql += " AND ";
            else sql += "WHERE ";
            sql += "nome_catg LIKE ? || '%'";
        }
        sql += "\n";
        
        // Starts threatments
        
        try {
            Connection cnt = new DAO().connect();
            PreparedStatement stmt = cnt.prepareStatement("SELECT " + select + sql + order + ";");
            if(search != null) stmt.setString(1, search);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                resource = new HashMap();
                resource.put("id", Integer.toString(rs.getInt(1)));
                resource.put("nome_catg", rs.getString(2));
                resources.add(resource);
            }
            
            stmt = cnt.prepareStatement("SELECT COUNT(*) " + sql + ";");
            if(search != null) stmt.setString(1, search);
            rs = stmt.executeQuery();
            
            rs.next();
            
            count = rs.getInt(1);
            
            stmt.close();
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finalReturn.put("resources", resources);
        finalReturn.put("pageQuantity", (int) Math.ceil(count / (float) limit));

        return finalReturn;
    }
    
    // All set functions

    public void validNome(String nome_catg){
        Map<String, Object> params = new HashMap<String, Object>()
        {{
            put("length", 20);
        }};
        validString("nome_catg", nome_catg, false, params);
    }
}
