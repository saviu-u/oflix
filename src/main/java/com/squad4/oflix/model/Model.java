package com.squad4.oflix.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    static final int DEFAULT_LIMIT = 12;
    static final int DEFAULT_PAGE = 1;
    
    private Map<String, String> errors = new HashMap<>();
    
    public Model(){}
    public Model(Map<String, String[]> paramList){}
    
    public Map<String, String> getErrors(){
        return errors;
    }
    
    static public List<Map> getResources(String param){
        return null;
    }
    
    /*
    static public int pageQuantity(String param){
        int temp_limit = limit;
        Model.limit = DEFAULT_LIMIT;
        return (int) Math.ceil(count(param) / (float) temp_limit);
    };
    */
    
    static public int count(String params){
        return -1;
    }
    
    public boolean valid(){
        errors = new HashMap<>();
        return false;
    }
 
    //public Model create(Map<String, String[]> paramList){
    //    Model subclass = new Model(paramList) {};
    //    subclass.save();
    //    return subclass;
    //}

    public boolean save(){
        return false;
    }
    
    public Model find(int id){
        return null;
    }
    
    public List<Model> where(){
        return null;
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
        if(params.get("minimum") != null && value.length() <= (int) params.get("length")){
            errors.put(key, UNDERFLOW);
            return;
        }
        errors.remove(key);
    }
    
    void validInteger(String key, Integer value, boolean optional, Map<String, Object> params){
        if(value == null){
            if(!optional) errors.put(key, MISSING_VALUE);
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
