package com.squad4.oflix.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author savio
 */
public class Model {
    private Map<String, String> errors;
    
    public Map<String, String> getErrors(){
        return errors;
    }
    
    public boolean valid(){
        return true;
    }
 
    public void create(){
        
    }

    public boolean save(){
        return true;
    }
    
    public Model find(int id){
        return new Model();
    }
    
    public List<Model> where(){
        return new ArrayList<Model>();
    }
}
