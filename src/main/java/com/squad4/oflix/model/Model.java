package com.squad4.oflix.model;

import java.util.List;
import java.util.Map;

/**
 *
 * @author savio
 */
public class Model {
    static final String MISSING_VALUE = "campo obrigatório";
    static final String OVERFLOW = "máximo de caracteres excedido";
    static final String INVALID = "formato inválido";
    static final int DEFAULT_LIMIT = 12;
    static final int DEFAULT_PAGE = 1;
    
    // Just for temp storage
    static int limit = DEFAULT_LIMIT;
    static int page = DEFAULT_PAGE;
    static private String paramCache;
    
    Map<String, String> errors;
    Map<String, String> tempFields;
    
    public Map<String, String> getErrors(){
        return errors;
    }
    
    static public List<Map> getResources(String param, int page, int limit){
        Model.limit = limit;
        Model.page = page;
        return getResources(param);
    }
    
    static public List<Map> getResources(String param, int page){
        Model.page = page;
        return getResources(param);
    }
    
    static public List<Map> getResources(String param){
        return null;
    }
    
    static public int pageQuantity(String param, int limit){
        Model.limit = limit;
        return pageQuantity(param);
    };
    
    static public int pageQuantity(String param){
        int temp_limit = limit;
        Model.limit = DEFAULT_LIMIT;
        return (int) Math.ceil(count(param) / (float) temp_limit);
    };
    
    static public int count(String params){
        return -1;
    }
    
    public boolean valid(){
        return false;
    }
 
    public boolean create(){
        return false;
    }

    public boolean save(){
        return false;
    }
    
    public Model find(int id){
        return new Model();
    }
    
    public List<Model> where(){
        return null;
    }
}
