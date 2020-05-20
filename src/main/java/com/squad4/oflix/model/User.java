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
    // Creates all the attributes
    public Integer id = null;
    private String id_func = null;
    private Integer id_end = null;
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
    private boolean ativo = true;

    public User() {}
    public User(Map<String, String[]> paramList){
        super(paramList);
        update(paramList);
    }
    
    //Used to update the attributes
    public void update(Map<String, String[]> paramList){
        if(paramList.containsKey("nome")) this.nome_pes = paramList.get("nome")[0];
        if(paramList.containsKey("id_func")) this.id_func = paramList.get("id_func")[0];
        if(paramList.containsKey("cpf")) this.cpf = paramList.get("cpf")[0];
        if(paramList.containsKey("email")) this.email = paramList.get("email")[0];
        if(paramList.containsKey("senha")) this.senha = paramList.get("senha")[0];
        if(paramList.containsKey("telefone_1")) this.telefone_1 = paramList.get("telefone_1")[0];
        if(paramList.containsKey("telefone_2")) this.telefone_2 = paramList.get("telefone_2")[0];
        if(paramList.containsKey("sexo")) this.sexo = paramList.get("sexo")[0];
        if(paramList.containsKey("estado")) this.estado = paramList.get("estado")[0];
        if(paramList.containsKey("cidade")) this.cidade = paramList.get("cidade")[0];
        if(paramList.containsKey("bairro")) this.bairro = paramList.get("bairro")[0];
        if(paramList.containsKey("rua")) this.rua = paramList.get("rua")[0];
        if(paramList.containsKey("numero")) this.num_residencia = paramList.get("numero")[0];
        if(paramList.containsKey("complemento")) this.complemento = paramList.get("complemento")[0];
        if(paramList.containsKey("ativo")) this.ativo = paramList.get("ativo")[0].equals("true");
    }
    
    // Turns the status to inactive
    public void destroy(){
        if(id == null) return;
        
        String sql = "UPDATE tb_pessoa SET ativo=false WHERE id=" + id +";";
        try {
            Connection conexao = new DAO().connect();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.execute();
            
            stmt.close();
        } catch (ClassNotFoundException | SQLException ex) {}
    }
    
    // Returns a usefull map to the front-end
    public Map<String, String[]> toMap(){
        Map<String, String[]> attributes = new HashMap();
        String temp[] = {""};
        temp[0] = this.nome_pes;
        attributes.put("nome", temp.clone());
        temp[0] = this.id_func;
        attributes.put("id_func", temp.clone());
        temp[0] = this.cpf;
        attributes.put("cpf", temp.clone());
        temp[0] = this.email;
        attributes.put("email", temp.clone());
        temp[0] = this.telefone_1;
        attributes.put("telefone_1", temp.clone());
        temp[0] = this.telefone_2;
        if(this.telefone_2 == null) temp[0] = "";
        attributes.put("telefone_2", temp.clone());
        temp[0] = this.sexo;
        attributes.put("sexo", temp.clone());
        temp[0] = this.estado;
        attributes.put("estado", temp.clone());
        temp[0] = this.cidade;
        attributes.put("cidade", temp.clone());
        temp[0] = this.bairro;
        attributes.put("bairro", temp.clone());
        temp[0] = this.rua;
        attributes.put("rua", temp.clone());
        temp[0] = this.num_residencia;
        attributes.put("numero", temp.clone());
        temp[0] = this.complemento;
        if(this.complemento == null) temp[0] = "";
        attributes.put("complemento", temp.clone());

        return attributes;
    }
    
    // Valids all the fields
    @Override
    public boolean valid(){
        try {
            super.valid();
            validNome(nome_pes);
            validFunc(id_func);
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
        } catch (SQLException | ClassNotFoundException ex) {}
        return false;
    }

    // Saves the data on the database
    public boolean save(){
        if(!valid()) return false;
        String sql0 = "";
        String sql1 = ";\n";
        String sqlI1 = "";
        String sql2 = "";
        String sqlI2 = "";
        if(id == null){
            sql0 = "DO $$\n" +
                "DECLARE tempid bigint;\n" +
                "BEGIN\n" +
                "\n";
            sqlI1 = "INSERT INTO tb_endereco(estado, cidade, bairro, rua, num_residencia, complemento)\n" +
                "VALUES\n" +
                "( ? , ? , ? , ? , ? , ? );\n";
            sql1 = "RETURNING id INTO tempid;\n";
            sqlI2 = "INSERT INTO tb_pessoa(id_end, id_func, nome_pes, email, telefone_1, telefone_2, sexo, ativo, cpf)\n" +
                "VALUES\n" +
                "(tempid, ? , ? , ? , ? , ? , ? , ? , ? )\n";
            sql2 = ";END $$";
        }
        else{
            sqlI1 = "UPDATE tb_endereco\n" +
                    "SET estado= ?, cidade= ?, bairro= ?, rua= ?, num_residencia= ?, complemento= ? \n" +
                    "WHERE ID="+ id_end;
            sqlI2 = "UPDATE tb_pessoa\n" +
                    "SET id_func= ?, nome_pes= ?, email= ?, telefone_1= ?, telefone_2= ?, sexo= ?, ativo= ? \n" +
                    "WHERE id=" + id +";";
        }
        try {
            Connection conexao = new DAO().connect();
            PreparedStatement stmt = conexao.prepareStatement(sqlI1);
            stmt.setString(1, estado);
            stmt.setString(2, cidade);
            stmt.setString(3, bairro);
            stmt.setString(4, rua);
            stmt.setInt(5, Integer.parseInt(num_residencia));
            stmt.setString(6, complemento);
            sqlI1 = stmt.toString();

            stmt = conexao.prepareStatement(sqlI2);
            stmt.setInt(1, Integer.parseInt(id_func));
            stmt.setString(2, nome_pes);
            stmt.setString(3, email);
            stmt.setInt(4, Integer.parseInt(telefone_1));
            if(telefone_2 == null || telefone_2.equals("")) stmt.setObject(5, null);
            else stmt.setInt(5, Integer.parseInt(telefone_2));
            stmt.setString(6, sexo);
            stmt.setBoolean(7, ativo);
            if(this.id == null) stmt.setString(8, cpf);
            
            sqlI2 = stmt.toString();
            
            stmt = conexao.prepareStatement(sql0 + sqlI1 + sql1 + sqlI2 + sql2);
            stmt.execute();
            
            stmt.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    // Finds an user by its id
    static public User find(int id,Map<String, Object> params){
        String where = null;
        try{ if(params.get("where") != null) where = (String) params.get("where"); }catch(Exception e){}

        String sql = "SELECT * FROM tb_pessoa INNER JOIN tb_endereco ON tb_endereco.id = id_end WHERE ";
        if(where != null) sql += where + " AND ";
        sql += "tb_pessoa.id = ?";
        sql += " LIMIT(1)";
        
        User resource = new User();
        Map<String, String[]> attributes = new HashMap(); 
        String temp[] = {""};
        String temp1[] = {"adasd"};
        
        try {
            Connection cnt = new DAO().connect();
            PreparedStatement stmt = cnt.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            boolean teste = rs.next();
            if(!teste) return new User();
            
            resource.id = rs.getInt(1);
            resource.id_end = rs.getInt(2);
            temp[0] = rs.getString(4);
            attributes.put("nome", temp.clone());
            temp[0] = Integer.toString(rs.getInt(3));
            attributes.put("id_func", temp.clone());
            temp[0] = rs.getString("cpf");
            attributes.put("cpf", temp.clone());
            temp[0] = rs.getString("email");
            attributes.put("email", temp.clone());
            temp[0] = rs.getString("telefone_1");
            attributes.put("telefone_1", temp.clone());
            temp[0] = rs.getString("telefone_2");
            attributes.put("telefone_2", temp.clone());
            temp[0] = rs.getString("sexo");
            attributes.put("sexo", temp.clone());
            temp[0] = rs.getString("estado");
            attributes.put("estado", temp.clone());
            temp[0] = rs.getString("cidade");
            attributes.put("cidade", temp.clone());
            temp[0] = rs.getString("bairro");
            attributes.put("bairro", temp.clone());
            temp[0] = rs.getString("rua");
            attributes.put("rua", temp.clone());
            temp[0] = rs.getString("num_residencia");
            attributes.put("numero", temp.clone());
            temp[0] = rs.getString("complemento");
            attributes.put("complemento", temp.clone());
            
            resource.update(attributes);
            
            stmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
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
        
        String order = "ORDER BY ativo = true DESC, nome_pes ASC\n" +
                "LIMIT(" + limit + ") OFFSET(" + (page - 1) * limit  + ")\n";
        String select = "tb_pessoa.id, nome_pes, cpf, nome_func, ativo\n";
        String sql = "FROM tb_pessoa\n" +
            "INNER JOIN tb_funcao ON tb_funcao.id = id_func\n";
        if(where != null) sql += "WHERE " + where;
        if(search != null){
            if(where != null) sql += " AND ";
            else sql += "WHERE ";
            sql += "nome_pes LIKE ? || '%'";
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
                resource.put("id", Integer.toString(rs.getInt("id")));
                resource.put("nome", rs.getString("nome_pes"));
                resource.put("cpf", rs.getString("cpf"));
                resource.put("funcao", rs.getString("nome_func"));
                boolean ativo = rs.getBoolean("ativo");
                String ativoTexto;
                if(ativo) ativoTexto = "Ativo";
                else ativoTexto = "Inativo";
                resource.put("ativo", ativoTexto);
                resources.add(resource);
            }
            
            stmt = cnt.prepareStatement("SELECT COUNT(*) " + sql + ";");
            if(search != null) stmt.setString(1, search);
            rs = stmt.executeQuery();
            
            rs.next();
            
            count = rs.getInt(1);
            
            stmt.close();
            
        } catch (ClassNotFoundException | SQLException ex) {}
        
        finalReturn.put("resources", resources);
        finalReturn.put("pageQuantity", (int) Math.ceil(count / (float) limit));

        return finalReturn;
    }
    
    public static Map<String, String> selectFunction() throws SQLException, ClassNotFoundException{
        Map<String, String> result = new HashMap();
        Connection cnt = new DAO().connect();
        String sql = "SELECT nome_func, id FROM tb_funcao WHERE NOT id IN (1)";
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
        validString("nome", nome, false, params);
    }
    public void validFunc(String id_func) throws SQLException, ClassNotFoundException{
        List<Object> options = Arrays.asList(User.selectFunction().values().toArray());
        validSelect("id_func", id_func, options, new HashMap<String, Object>());
    }
    public void validCpf(String cpf){
        Map<String, Object> params = new HashMap<String, Object>()
        {{
            put("regex", "\\A[0-9]{3}([.][0-9]{3}){2}[-][0-9]{2}\\Z");
            put("uniqueness", "tb_pessoa");
            put("id", id);
        }};
        validString("cpf", cpf, false, params);
    }
    public void validEmail(String email){
        Map<String, Object> params = new HashMap<String, Object>()
        {{
            put("regex", "\\A[A-z0-9]+[@][A-z0-9]+[.][A-z0-9]+([.][A-z0-9]+)*\\Z");
            put("length", 60);
            put("uniqueness", "tb_pessoa");
            put("id", id);
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
