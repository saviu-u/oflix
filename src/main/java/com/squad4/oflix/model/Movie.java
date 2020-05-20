package com.squad4.oflix.model;

import DAO.DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Movie extends Model {
    // Creates all the attributes
    private Integer id = null;
    private String id_catg = null;
    private String nome_filme = null;
    private String sinopse = null;
    private String quant_estoque = null;
    private String class_indicativa = null;

    public Movie() {}
    public Movie(Map<String, String[]> paramList){
        super(paramList);
        update(paramList);
    }
    
    public Integer getId(){
        return id;
    }
    
    //Used to update the attributes
    public void update(Map<String, String[]> paramList){
        if(paramList.containsKey("id_catg")) this.id_catg = paramList.get("id_catg")[0];
        if(paramList.containsKey("nome_filme")) this.nome_filme = paramList.get("nome_filme")[0];
        if(paramList.containsKey("sinopse")) this.sinopse = paramList.get("sinopse")[0];
        if(paramList.containsKey("quant_estoque")) this.quant_estoque = paramList.get("quant_estoque")[0];
        if(paramList.containsKey("class_indicativa")) this.class_indicativa = paramList.get("class_indicativa")[0];
    }
    
    // Returns a usefull map to the front-end
    public Map<String, String[]> toMap(){
        Map<String, String[]> attributes = new HashMap();
        String temp[] = {""};
        temp[0] = this.id_catg;
        attributes.put("id_catg", temp.clone());
        temp[0] = this.nome_filme;
        attributes.put("nome_filme", temp.clone());
        temp[0] = this.sinopse;
        if(this.sinopse == null) temp[0] = "";
        attributes.put("sinopse", temp.clone());
        temp[0] = this.quant_estoque;
        attributes.put("quant_estoque", temp.clone());
        temp[0] = this.class_indicativa;
        attributes.put("class_indicativa", temp.clone());     

        return attributes;
    }
    
    // Valids all the fields
    @Override
    public boolean valid(){
        try {
            super.valid();
            validCatg(id_catg);
            validNome(nome_filme);
            validSinopse(sinopse);
            validEstoque(quant_estoque);
            validClass(class_indicativa);
            return getErrors().isEmpty();
        } catch (SQLException | ClassNotFoundException ex) {}
        return false;
    }

    // Saves the data on the database
    public boolean save(){
        if(!valid()) return false;
        String sql = "";
        if(id == null){
            sql = "INSERT INTO tb_filme(id_catg, nome_filme, sinopse, quant_estoque, class_indicativa) "
                    + "VALUES (?, ?, ?, ?, ?)";
        }
        else{
            sql = "UPDATE tb_filme\n" +
                    "SET id_catg= ?, nome_filme= ?, sinopse= ?, quant_estoque= ?, class_indicativa= ? \n" +
                    "WHERE ID="+ id + ";";
        }
        try {
            Connection conexao = new DAO().connect();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            System.out.println("TESTE: " + id_catg);
            stmt.setInt(1, Integer.parseInt(id_catg));
            stmt.setString(2, nome_filme);
            stmt.setString(3, sinopse);
            stmt.setInt(4, Integer.parseInt(quant_estoque));
            stmt.setInt(5, Integer.parseInt(class_indicativa));
            stmt.execute();
            
            stmt.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    // Finds an user by its id
    static public Movie find(int id,Map<String, Object> params){
        String where = null;
        if(params.get("where") != null) where = (String) params.get("where");

        String sql = "SELECT * FROM tb_filme WHERE ";
        if(where != null) sql += where + " AND ";
        sql += "tb_filme.id = ?";
        sql += " LIMIT(1)";
        
        Movie resource = new Movie();
        Map<String, String[]> attributes = new HashMap(); 
        String temp[] = {""};
        
        try {
            Connection cnt = new DAO().connect();
            PreparedStatement stmt = cnt.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            boolean teste = rs.next();
            if(!teste) return new Movie();
            
            resource.id = rs.getInt(1);
            temp[0] = rs.getString(2);
            attributes.put("id_catg", temp.clone());
            temp[0] = rs.getString(3);
            attributes.put("nome_filme", temp.clone());
            temp[0] = rs.getString(4);
            attributes.put("sinopse", temp.clone());
            temp[0] = rs.getString(5);
            attributes.put("quant_estoque", temp.clone());
            temp[0] = rs.getString(6);
            attributes.put("class_indicativa", temp.clone());
            
            resource.update(attributes);
            
            stmt.close();
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
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
        
        String order = "ORDER BY nome_filme ASC\n" +
                "LIMIT(" + limit + ") OFFSET(" + (page - 1) * limit  + ")\n";
        String select = "tb_filme.id, nome_filme, nome_catg, quant_estoque, class_indicativa\n";
        String sql = "FROM tb_filme\n"
                + "INNER JOIN tb_categoria ON tb_categoria.id = id_catg\n";
        if(where != null) sql += "WHERE " + where;
        if(search != null){
            if(where != null) sql += " AND ";
            else sql += "WHERE ";
            sql += "nome_filme LIKE ? || '%'";
        }
        sql += "\n";
        
        // Starts threatments
        
        try {
            Connection cnt = new DAO().connect();
            PreparedStatement stmt = cnt.prepareStatement("SELECT " + select + sql + order + ";");
            if(search != null) stmt.setString(1, search);
            System.out.println("TESTE: " + stmt.toString());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                resource = new HashMap();
                resource.put("id", Integer.toString(rs.getInt(1)));
                resource.put("nome_filme", rs.getString(2));
                resource.put("nome_catg", rs.getString(3));
                resource.put("quant_estoque", rs.getString(4));
                resource.put("class_indicativa", rs.getString(5));
                resources.add(resource);
            }
            
            stmt = cnt.prepareStatement("SELECT COUNT(*) " + sql + ";");
            if(search != null) stmt.setString(1, search);
            rs = stmt.executeQuery();
            
            rs.next();
            
            count = rs.getInt(1);
            
            stmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Movie.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Movie.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finalReturn.put("resources", resources);
        finalReturn.put("pageQuantity", (int) Math.ceil(count / (float) limit));

        return finalReturn;
    }
    
    public static Map<String, String> selectCategory() throws SQLException, ClassNotFoundException{
        Map<String, String> result = new HashMap();
        Connection cnt = new DAO().connect();
        String sql = "SELECT nome_catg, id FROM tb_categoria";
        PreparedStatement stmt = cnt.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){ result.put(rs.getString(1), Integer.toString(rs.getInt(2))); }
        return result;
    }
    
    // All set functions

    public void validNome(String nome){
        Map<String, Object> params = new HashMap<String, Object>()
        {{
            put("length", 255);
        }};
        validString("nome_filme", nome, false, params);
    }
    public void validCatg(String id_catg) throws SQLException, ClassNotFoundException{
        List<Object> options = Arrays.asList(Movie.selectCategory().values().toArray());
        validSelect("id_catg", id_catg, options, new HashMap<String, Object>());
    }
    public void validEstoque(String quant_estoque){
        validInteger("quant_estoque", quant_estoque, false, new HashMap<String, Object>());
    }
    public void validClass(String class_indicativa){
        validInteger("class_indicativa", class_indicativa, false, new HashMap<String, Object>());
    }
    public void validSinopse(String sinopse){
        Map<String, Object> params = new HashMap<String, Object>()
        {{
            put("length", 511);
        }};
        validString("sinopse", sinopse, true, params);
    }
}
