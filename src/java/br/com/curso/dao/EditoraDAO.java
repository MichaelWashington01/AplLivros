/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.curso.dao;

import br.com.curso.model.Editora;
import br.com.curso.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maico
 */
public class EditoraDAO implements GenericDAO{
    
    
    private Connection conexao;
    
    public EditoraDAO() throws Exception{
    conexao = SingleConnection.getConnection();
    }

    @Override
    public boolean cadastrar(Object objeto) {
                Editora oEditora = (Editora)objeto;
        boolean retorno=false;
        if(oEditora.getIdEditora()==0){
            retorno = this.inserir(oEditora);
        }else{
            retorno = this.alterar(oEditora);
        }
        return retorno;
    }

    @Override
    public boolean inserir(Object objeto) {
        Editora oEditora = (Editora)objeto;
        PreparedStatement stmt = null;
        String sql = "Insert into editora (descricao) values (?)";
        try{
          stmt = conexao.prepareStatement(sql);
          stmt.setString(1, oEditora.getDescricao());
          stmt.execute();
          conexao.commit();
          return true;
        }catch (Exception ex){
            try{
            System.out.println("Problemas ao cadastrar a Editora! Erro:" + ex.getMessage());
            ex.printStackTrace();
              conexao.rollback();
          }catch(SQLException e){
                System.out.println("Erro"+ e.getMessage());
                e.printStackTrace();
          }
            return false;
       }
    }

    @Override
    public boolean alterar(Object objeto) {
        Editora oEditora = (Editora)objeto;    
        PreparedStatement stmt = null;
        String sql = "update editora set descricao=? where idEditora=?";
        try{
          stmt = conexao.prepareStatement(sql);
          stmt.setString(1, oEditora.getDescricao());
          stmt.setInt(2, oEditora.getIdEditora());
          stmt.execute();
          conexao.commit();
          return  true;
        } catch (Exception ex){
            try{
                System.out.println("Problemas ao alterar a Editora! Erro:" +ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            }catch (SQLException e){
                System.out.println("Erro:" +e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public boolean excluir(int numero) {
                int idEditora = numero;
        PreparedStatement stmt = null;
        
        String sql = "delete from editora where idEditora=?";
        try{
            stmt=conexao.prepareStatement(sql);
            stmt.setInt(1, idEditora);
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex){
            System.out.println("Problemas ao excluir a Editora! Erro:" +ex.getMessage());
            try{
                conexao.rollback();
            }catch (Exception e){
                System.out.println("Erro rolback"+ e.getMessage());
                e.printStackTrace();
            }
            return  false;
        }
    }

    @Override
    public Object carregar(int numero) {
        int idEditora = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Editora oEditora = null;
        String sql="select * from editora where idEditora=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idEditora);
            rs=stmt.executeQuery();
            while(rs.next()){
                oEditora = new Editora();
                oEditora.setIdEditora(rs.getInt("idEditora"));
                oEditora.setDescricao(rs.getString("descricao"));
            }
            return oEditora;
        }catch(Exception ex){
            System.out.println("Problemas ao carregar Editora! Erro:" + ex.getMessage());
            return  false;
        }
    }

    @Override
    public List<Object> listar() {
         List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from editora order by idEditora";
        try{
        stmt = conexao.prepareStatement(sql);
        rs=stmt.executeQuery();
        while (rs.next()){
        Editora oEditora = new Editora();
        oEditora.setIdEditora(rs.getInt("idEditora"));
        oEditora.setDescricao(rs.getString("descricao"));
              
       resultado.add(oEditora);
          }
       }catch (SQLException ex){
          System.out.println("Problemas ao listar Editora! Erro: "+ex.getMessage());
       }
      return resultado;
    }
    
}
