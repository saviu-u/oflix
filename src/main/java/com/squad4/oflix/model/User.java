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

public class User extends Model {
    private String id_func = null;
    private String nome_pes = null;
    private String cpf = null;
    private String email = null;
    private String senha = null;
    // Integer
    private String telefone_1 = null;
    // Integer
    private String telefone_2 = null;
    private String sexo = null;
    private String estado = null;
    private String cidade = null;
    private String bairro = null;
    private String rua = null;
    // Integer
    private String num_residencia = null;
    private String complemento = null;
    
    private String id_end = null;

    public User() {}
    public User(Map<String, String[]> paramList){
        super(paramList);
        try {this.nome_pes = (paramList.get("nome")[0]);} catch(Exception e) {}
        try {this.cpf = (paramList.get("cpf")[0]);} catch(Exception e) {}
        try {this.email = (paramList.get("email")[0]);} catch(Exception e) {}
        try {this.senha = (paramList.get("senha")[0]);} catch(Exception e) {}
        try {this.telefone_1 = (paramList.get("telefone_1")[0]);} catch(Exception e) {}
        try {this.telefone_2 = (paramList.get("telefone_2")[0]);} catch(Exception e) {}
        try {this.sexo = (paramList.get("sexo")[0]);} catch(Exception e) {}
        try {this.estado = (paramList.get("estado")[0]);} catch(Exception e) {}
        try {this.cidade = (paramList.get("cidade")[0]);} catch(Exception e) {}
        try {this.bairro = (paramList.get("bairro")[0]);} catch(Exception e) {}
        try {this.rua = (paramList.get("rua")[0]);} catch(Exception e) {}
        try {this.num_residencia = (paramList.get("numero")[0]);} catch(Exception e) {}
        try {this.complemento = (paramList.get("complemento")[0]);} catch(Exception e) {}
    }
    
    @Override
    public boolean valid(){
        super.valid();
        validNome(nome_pes);
        validCpf(cpf);
        validEmail(email);
        // validSenha(senha);
        validTelefone1(telefone_1);
        validTelefone2(telefone_2);
        validSexo(sexo);
        validEstado(estado);
        validCidade(cidade);
        validBairro(bairro);
        validRua(rua);
        validNumero(num_residencia);
        validComplemento(complemento);
        
        return getErrors().isEmpty();
    }
    
    @Override
    public boolean save(){
        if(!valid()) return false;
        //int i = 0;
        
        //String sql = "INSERT INTO tb_teste1 (nome, sobrenome)\n" +
        //             "VALUES ('asd', 'asd')\n" +
        //             "RETURNING id";
        try {
            Connection conexao = new DAO().connect();
            PreparedStatement stmt = conexao.prepareStatement("");
            
            //ResultSet result = stmt.executeQuery();
            //result.next();
            stmt.close();
        } catch (SQLException ex) {} catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

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
        
        try{ if(params.containsKey("page")) page = Integer.parseInt((String)params.get("page")); }catch(NumberFormatException e){}
        try{ if(params.containsKey("limit")) limit = Integer.parseInt((String)params.get("limit")); }catch(NumberFormatException e){}
        try{ if(params.containsKey("where")) where = (String) params.get("where"); }catch(Exception e){}
        try{ if(params.containsKey("search")) search = (String) params.get("search"); }catch(Exception e){}
        
        // Starts threatments
        
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
        
        finalReturn.put("resources", resources);
        finalReturn.put("pageQuantity", (int) Math.ceil(count / (float) limit));

        return finalReturn;
    }

    static public int count(String params){
        return 3;
    }
    
    // All set functions

    public void validNome(String nome){
        Map<String, Object> params = new HashMap<String, Object>()
        {{
            put("length", 255);
        }};
        validString("nome", nome, false, params);
    }
    public void validCpf(String cpf){
        Map<String, Object> params = new HashMap<String, Object>()
        {{
            put("regex", "\\A[0-9]{3}([.][0-9]{3}){2}[-][0-9]{2}\\Z");
        }};
        validString("cpf", cpf, false, params);
    }
    public void validEmail(String email){
        Map<String, Object> params = new HashMap<String, Object>()
        {{
            put("regex", "\\A[a-zA-Z]+[@][a-zA-Z]+[.][a-zA-Z]+([.][a-zA-Z]+)*\\Z");
            put("length", 60);
        }};
        validString("email", email, false, params);
    }
    public void validSenha(String senha){
        Map<String, Object> params = new HashMap<String, Object>()
        {{
            put("length", 10);
            put("minimum", 8);
        }};
        validString("senha", senha, false, params);
    }
    public void validTelefone1(String telefone){
        validInteger("telefone_1", telefone, false, new HashMap<String, Object>());
    }
    public void validTelefone2(String telefone){
        validInteger("telefone_2", telefone, true, new HashMap<String, Object>());
    }
    public void validSexo(String sexo){
        List<Object> options = new ArrayList<Object>(Arrays.asList("M","F","O"));
        validSelect("sexo", sexo, options, new HashMap<String, Object>());
    }
    public void validEstado(String estado){
        Map<String, Object> params = new HashMap<String, Object>()
        {{
            put("length", 45);
        }};
        validString("estado", estado, false, params);
    }
    public void validCidade(String cidade){
        Map<String, Object> params = new HashMap<String, Object>()
        {{
            put("length", 45);
        }};
        validString("cidade", cidade, false, params);
    }
    public void validBairro(String bairro){
        Map<String, Object> params = new HashMap<String, Object>()
        {{
            put("length", 45);
        }};
        validString("bairro", bairro, false, params);
    }
    public void validRua(String rua){
        Map<String, Object> params = new HashMap<String, Object>()
        {{
            put("length", 45);
        }};
        validString("rua", rua, false, params);
    }
    public void validNumero(String numero){
        validInteger("numero", numero, false, new HashMap<String, Object>());
    }
    public void validComplemento(String complemento){
        Map<String, Object> params = new HashMap<String, Object>()
        {{
            put("length", 10);
        }};
        validString("complemento", complemento, true, params);
    }
}
