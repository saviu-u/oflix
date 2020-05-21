package com.squad4.oflix.model;

import DAO.DAO;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author savio
 */
public abstract class Model {
    static final String MISSING_VALUE = "campo obrigatório";
    static final String OVERFLOW = "máximo de caracteres excedido";
    static final String UNDERFLOW = "muito curto";
    static final String INVALID_FORMAT = "formato inválido";
    static final String INVALID_VALUE = "campo inválido";
    static final String ALREADY_EXIST = "já está em uso";
    static final int DEFAULT_LIMIT = 12;
    static final int DEFAULT_PAGE = 1;
    
    private Map<String, String> errors = new HashMap<>();
    
    public Model(){}
    public Model(Map<String, String[]> paramList){}
    
    public Map<String, String> getErrors(){
        return errors;
    }
    
    public boolean valid(){
        errors = new HashMap<>();
        return false;
    }
    
    // Helpers
    
    void validString(String key, String value, boolean optional, Map<String, Object> params){
        if(value == null || value.isEmpty()){
            if(!optional) errors.put(key, MISSING_VALUE);
            return;
        }
        if(params.get("regex") != null && !value.matches((String) params.get("regex"))){
            errors.put(key, INVALID_FORMAT);
            return;
        }
        if(params.get("length") != null && value.length() > (int) params.get("length")){
            errors.put(key, OVERFLOW);
            return;
        }
        if(params.get("minimum") != null && value.length() <= (int) params.get("minimum")){
            errors.put(key, UNDERFLOW);
            return;
        }
        if(params.get("uniqueness") != null){
            try {
                Connection cnt = new DAO().connect();
                String sql = "SELECT COUNT(*) FROM " + params.get("uniqueness") + " WHERE " + key + " = ? AND id != " + params.get("id") + ";";
                PreparedStatement stmt = cnt.prepareStatement(sql);
                stmt.setString(1, value);
                ResultSet rs = stmt.executeQuery();
                rs.next();
                if(rs.getInt(1) != 0) errors.put(key, ALREADY_EXIST);
                stmt.close();
                return;
            } catch (ClassNotFoundException | SQLException ex) {}
        }

        errors.remove(key);
    }
    
    void validInteger(String key, String value, boolean optional, Map<String, Object> params){
        Integer num;
        
        if(value == null || value.isEmpty()){
            if(!optional) errors.put(key, MISSING_VALUE);
            return;
        }

        try{
            num = Integer.parseInt(value);
        }
        catch(NumberFormatException e){
            errors.put(key, INVALID_VALUE);
            return;
        }

        errors.remove(key);
    }
    
    void validSelect(String key, Object value, List<Object> options, Map<String, Object> params){
        if(!options.contains(value)){
            errors.put(key, INVALID_VALUE);
            return;
        }
        errors.remove(key);
    }
}
