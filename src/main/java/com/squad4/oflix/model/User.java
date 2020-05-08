package com.squad4.oflix.model;

import DAO.DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User extends Model {
    private String id_func;
    private String nome_pes;
    private String cpf;
    private String email;
    private int telefone_1;
    private int telefone_2;
    private String sexo;
    private String senha;
    
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private int num_residencia;
    private String complemento;
    
    private String id_end;

    public User(Map<String, String> paramList){
        this.tempFields = paramList;
    }
    
    @Override
    public boolean valid(){
        // nome_pes
        return true;
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
    
    public void setNome(String nome){
        if(nome == null){
            errors.put("nome", MISSING_VALUE);
            return;
        }
        if(nome.length() > 255){
            errors.put("nome", OVERFLOW);
            return;
        }
        this.nome_pes = nome;
    }
    public void setCpf(String cpf){
        if(cpf == null){
            errors.put("cpf", MISSING_VALUE);
            return;
        }
        if(!cpf.matches("\\A[1-9]{3}([.][1-9]{3}){2}[-][1-9]{2}\\Z")){
            errors.put("cpf", INVALID);
            return;
        }
        this.cpf = cpf;
    }
    public void setEmail(String email){
        if(email == null){
            errors.put("email", MISSING_VALUE);
            return;
        }
        if(email.length() > 60){
            errors.put("email", OVERFLOW);
            return;
        }
        if(!email.matches("\\A[a-zA-Z]+[@][a-zA-Z]+[.][a-zA-Z]+([.][a-zA-Z]+)*\\Z")){
            errors.put("email", INVALID);
            return;
        }
        this.email = email;
    }
    public void setTelefone1(String telefone){
        if(telefone == null){
            errors.put("telefone1", MISSING_VALUE);
            return;
        }
        try{
            Integer.parseInt(telefone);
        }
        catch(NumberFormatException e){
            errors.put("telefone1", INVALID);
            return;
        }
        if(Math.abs(Integer.parseInt(telefone)) > 2147483647){
            errors.put("telefone1", OVERFLOW);
            return;
        }
        this.telefone_1 = Integer.parseInt(telefone);
    }
    public void setTelefone2(String telefone){
        if(telefone == null) return;
        try{
            Integer.parseInt(telefone);
        }
        catch(NumberFormatException e){
            errors.put("telefone2", INVALID);
            return;
        }
        if(Math.abs(Integer.parseInt(telefone)) > 2147483647){
            errors.put("telefone2", OVERFLOW);
            return;
        }
        this.telefone_2 = Integer.parseInt(telefone);
    }
    public void setSexo(String sexo){
        if(sexo == "M" || sexo == "F"){
            errors.put("sexo", MISSING_VALUE);
            return;
        }
        this.sexo = sexo;
    }
    public void setEstado(String estado){
        if(estado == null){
            errors.put("estado", MISSING_VALUE);
            return;
        }
        if(estado.length() > 45){
            errors.put("estado", OVERFLOW);
            return;
        }
        this.estado = estado;
    }
    public void setCidade(String cidade){
        if(cidade == null){
            errors.put("cidade", MISSING_VALUE);
            return;
        }
        if(cidade.length() > 45){
            errors.put("cidade", OVERFLOW);
            return;
        }
        this.cidade = cidade;
    }
    public void setBairro(String bairro){
        if(bairro == null){
            errors.put("bairro", MISSING_VALUE);
            return;
        }
        if(bairro.length() > 45){
            errors.put("bairro", OVERFLOW);
            return;
        }
        this.bairro = bairro;
    }
    public void setRua(String rua){
        if(rua == null){
            errors.put("rua", MISSING_VALUE);
            return;
        }
        if(rua.length() > 45){
            errors.put("rua", OVERFLOW);
            return;
        }
        this.rua = rua;
    }
    public void setNumero(String numero){
        if(numero == null){
            errors.put("numero", MISSING_VALUE);
            return;
        }
        try{
            Integer.parseInt(numero);
        }
        catch(NumberFormatException e){
            errors.put("numero", INVALID);
            return;
        }
        if(Math.abs(Integer.parseInt(numero)) > 2147483647){
            errors.put("numero", OVERFLOW);
            return;
        }
        this.num_residencia = Integer.parseInt(numero);
    }
    public void setComplemento(String complemento){
        if(complemento == null){
            return;
        }
        if(complemento.length() > 10){
            errors.put("complemento", OVERFLOW);
            return;
        }
        this.complemento = complemento;
    }
    
    
    static public List<Map> getResources(String param){
        List<Map> resources = new ArrayList<>();
        Map<String, String> resource;
        
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
