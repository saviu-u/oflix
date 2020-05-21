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

public class Rent extends Model {
    // Creates all the attributes
    private Integer id = null;
    private String id_pes = null;
    private String id_filme = null;
    private String data_aluguel = null;
    private String ativo = null;

    public Rent() {}
    public Rent(Map<String, String[]> paramList){
        super(paramList);
        update(paramList);
    }
    
    public Integer getId(){
        return id;
    }
    
    public String getFilmeId(){
        return id_filme;
    }
    
    public String getPesId(){
        return id_pes;
    }
    
    //Used to update the attributes
    public void update(Map<String, String[]> paramList){
        if(paramList.containsKey("id_pes")) this.id_pes = paramList.get("id_pes")[0];
        if(paramList.containsKey("id_filme")) this.id_filme = paramList.get("id_filme")[0];
        if(paramList.containsKey("data_aluguel")) this.data_aluguel = paramList.get("data_aluguel")[0];
        if(paramList.containsKey("ativo")) this.ativo = paramList.get("ativo")[0];
    }
    
    // Returns a usefull map to the front-end
    public Map<String, String[]> toMap(){
        Map<String, String[]> attributes = new HashMap();
        String temp[] = {""};
        temp[0] = this.id_pes;
        attributes.put("id_pes", temp.clone());
        temp[0] = this.id_filme;
        attributes.put("id_filme", temp.clone());
        temp[0] = this.data_aluguel;
        attributes.put("data_aluguel", temp.clone());
        temp[0] = this.ativo;
        attributes.put("ativo", temp.clone());

        return attributes;
    }
    
    // Valids all the fields
    @Override
    public boolean valid(){
        try {
            super.valid();
            validPes(id_pes);
            validFilme(id_filme);
            validAluguel(data_aluguel);
            validAtivo(ativo);
            return getErrors().isEmpty();
        } catch (SQLException | ClassNotFoundException ex) {}
        return false;
    }

    // Saves the data on the database
    public boolean save(){
        if(!valid()) return false;
        String sql = "";
        if(id == null){
            sql = "INSERT INTO tb_aluguel(id_pes, id_filme, data_aluguel, ativo)"
                    + "VALUES (?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?)";
        }
        else{
            sql = "UPDATE tb_aluguel\n" +
                    "SET id_pes= ?, id_filme= ?, data_aluguel= TO_DATE(?, 'YYYY-MM-DD'), ativo= ?\n" +
                    "WHERE ID="+ id + ";";
        }
        try {
            Connection conexao = new DAO().connect();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id_pes));
            stmt.setInt(2, Integer.parseInt(id_filme));
            stmt.setString(3, data_aluguel);
            stmt.setBoolean(4, ativo.equals("true"));
            stmt.execute();
            
            stmt.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    // Finds an user by its id
    static public Rent find(int id,Map<String, Object> params){
        String where = null;
        if(params.get("where") != null) where = (String) params.get("where");

        String sql = "SELECT id, id_pes, id_filme, TO_CHAR(data_aluguel, 'YYYY-MM-DD') AS data_aluguel, ativo FROM tb_aluguel WHERE ";
        if(where != null) sql += where + " AND ";
        sql += "tb_aluguel.id = ?";
        sql += " LIMIT(1)";
        
        Rent resource = new Rent();
        Map<String, String[]> attributes = new HashMap(); 
        String temp[] = {""};
        
        try {
            Connection cnt = new DAO().connect();
            PreparedStatement stmt = cnt.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            boolean teste = rs.next();
            if(!teste) return new Rent();
            
            resource.id = rs.getInt(1);
            temp[0] = rs.getString(2);
            attributes.put("id_pes", temp.clone());
            temp[0] = rs.getString(3);
            attributes.put("id_filme", temp.clone());
            temp[0] = rs.getString(4);
            attributes.put("data_aluguel", temp.clone());
            temp[0] = "false";
            if(rs.getBoolean(5)) temp[0] = "true";
            attributes.put("ativo", temp.clone());
            
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

        String order = "ORDER BY data_aluguel DESC\n" +
                "LIMIT(" + limit + ") OFFSET(" + (page - 1) * limit  + ")\n";
        String select = "tb_aluguel.id, tb_pessoa.nome_pes, tb_filme.nome_filme, TO_CHAR(data_aluguel, 'DD/MM/YYYY') AS data_aluguel, tb_aluguel.ativo\n";
        String sql = "FROM tb_aluguel\n"
                + "INNER JOIN tb_pessoa ON tb_pessoa.id = id_pes INNER JOIN tb_filme ON tb_filme.id = id_filme\n";
        if(where != null) sql += "WHERE " + where;
        if(search != null){
            if(where != null) sql += " AND ";
            else sql += "WHERE ";
            sql += "tb_pessoa.nome_pessoa LIKE ? || '%'";
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
                resource.put("nome_pes", rs.getString(2));
                resource.put("nome_filme", rs.getString(3));
                resource.put("data_aluguel", rs.getString(4));
                String ativo = "Sim";
                if(rs.getBoolean(5)) ativo = "Não";
                resource.put("tb_aluguel", ativo);
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
    
    public static Map<String, String> customersAvailable(String id) throws SQLException, ClassNotFoundException{
        Map<String, String> result = new HashMap();
        Connection cnt = new DAO().connect();
        String sql;
        if(id == null) sql = "SELECT nome_pes, id FROM tb_pessoa WHERE id_func = 1 AND ativo = true;";
        else sql = "SELECT nome_pes, id FROM tb_pessoa WHERE id = " + id + ";";
        PreparedStatement stmt = cnt.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){ result.put(rs.getString(1), Integer.toString(rs.getInt(2))); }
        return result;
    }
    
    public static Map<String, String> customersAvailable() throws SQLException, ClassNotFoundException{
        return customersAvailable(null);
    }
    
    public static Map<String, String> moviesAvailable(String id) throws SQLException, ClassNotFoundException{
        Map<String, String> result = new HashMap();
        Connection cnt = new DAO().connect();
        String sql;
        if(id == null){
            sql = "SELECT nome_filme, id FROM tb_filme\n" +
                "WHERE quant_estoque > (SELECT COUNT(*) FROM tb_aluguel WHERE id_filme = tb_filme.id AND ativo = false); ";
        }
        else{
        sql = "SELECT nome_filme, id FROM tb_filme\n" +
                "WHERE id = " + id + "; ";
        }
        PreparedStatement stmt = cnt.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){ result.put(rs.getString(1), Integer.toString(rs.getInt(2))); }
        return result;
    }
    
    public static Map<String, String> moviesAvailable() throws SQLException, ClassNotFoundException{
        return moviesAvailable(null);
    }
    
    // All set functions

    public void validAluguel(String data_aluguel){
        Map<String, Object> params = new HashMap<String, Object>()
        {{
            put("regex", "\\A([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))\\Z");
        }};
        validString("data_aluguel", data_aluguel, false, params);
    }
    public void validPes(String id_pes) throws SQLException, ClassNotFoundException{
        List<Object> options = Arrays.asList(Rent.customersAvailable(this.id_pes).values().toArray());
        validSelect("id_pes", id_pes, options, new HashMap<String, Object>());
    }
    public void validFilme(String id_filme) throws SQLException, ClassNotFoundException{
        List<Object> options = Arrays.asList(Rent.moviesAvailable(this.id_filme).values().toArray());
        validSelect("id_filme", id_filme, options, new HashMap<String, Object>());
    }
    public void validAtivo(String ativo){
        List<Object> options = new ArrayList<Object>(Arrays.asList("true", "false"));
        validSelect("ativo", ativo, options, new HashMap<String, Object>());
    }
}
