package com.squad4.oflix.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User extends Model {
    private static final int DEFAULT_LIMIT = 12;
    private static final int DEFAULT_PAGE = 1;
    static private int limit = DEFAULT_LIMIT;
    static private int page = DEFAULT_PAGE;
    
    static private String paramCache;
    
    static public List<Map> getResources(String param, int page, int limit){
        User.limit = limit;
        User.page = page;
        return getResources(param);
    }
    
    static int pageQuantity(String param, int limit){
        User.limit = limit;
        return pageQuantity(param);
    };
    
    static int pageQuantity(String param){
        int temp_limit = limit;
        User.limit = DEFAULT_LIMIT;
        return (int) Math.ceil(count(param) / (float) temp_limit);
    };

    static public List<Map> getResources(String param){
        List<Map> resources = new ArrayList<>();
        Map<String, String> resource = new HashMap<>();
        
        String[] NameListArray = {
            "Roberto",
            "Carlos",
            "Sávio"
        };
        String[] CPFListArray = {
            "111.222.333",
            "222.333.444",
            "333.444.555"
        };
        String[] FuncaoListArray = {
            "Atendente",
            "Aaaa",
            "CCc"
        };
        
        for(int i=0;i<3;i++){
            resource = new HashMap<>();
            resource.put("nome", NameListArray[i]);
            resource.put("cpf", CPFListArray[i]);
            resource.put("funcao", FuncaoListArray[i]);
            resources.add(resource);
        }

        User.limit = DEFAULT_LIMIT;
        User.page = DEFAULT_PAGE;
        return resources;
    }
    static public int count(String params){
        return 3;
    }
}
