/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.curso.dao;

import br.com.curso.model.Autor;
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
public class AutorDAO implements GenericDAO{
    
    private Connection conexao;
    
    public AutorDAO()throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public boolean cadastrar(Object objeto) {
        Autor oAutor = (Autor)objeto;
        boolean retorno=false;
        if(oAutor.getIdAutor()==0){
            retorno = this.inserir(oAutor);
        }else{
            retorno = this.alterar(oAutor);
        }
        return retorno;
    }

    @Override
    public boolean inserir(Object objeto) {
        Autor oAutor = (Autor)objeto;
        PreparedStatement stmt = null;
        String sql = "Insert into autor (descricao) values (?)";
        try{
          stmt = conexao.prepareStatement(sql);
          stmt.setString(1, oAutor.getDescricao());
          stmt.execute();
          conexao.commit();
          return true;
        }catch (Exception ex){
            try{
            System.out.println("Problemas ao cadastrar a Autor! Erro:" + ex.getMessage());
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
        Autor oAutor = (Autor)objeto;    
        PreparedStatement stmt = null;
        String sql = "update autor set descricao=? where idAutor=?";
        try{
          stmt = conexao.prepareStatement(sql);
          stmt.setString(1, oAutor.getDescricao());
          stmt.setInt(2, oAutor.getIdAutor());
          stmt.execute();
          conexao.commit();
          return  true;
        } catch (Exception ex){
            try{
                System.out.println("Problemas ao alterar a Autor! Erro:" +ex.getMessage());
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
        int idAutor = numero;
        PreparedStatement stmt = null;
        
        String sql = "delete from autor where idAutor=?";
        try{
            stmt=conexao.prepareStatement(sql);
            stmt.setInt(1, idAutor);
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex){
            System.out.println("Problemas ao excluir a Autor! Erro:" +ex.getMessage());
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
        int idAutor = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Autor oAutor = null;
        String sql="select * from autor where idAutor=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idAutor);
            rs=stmt.executeQuery();
            while(rs.next()){
                oAutor = new Autor();
                oAutor.setIdAutor(rs.getInt("idAutor"));
                oAutor.setDescricao(rs.getString("descricao"));
            }
            return oAutor;
        }catch(Exception ex){
            System.out.println("Problemas ao carregar Autor! Erro:" + ex.getMessage());
            return  false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from autor order by idAutor";
        try{
        stmt = conexao.prepareStatement(sql);
        rs=stmt.executeQuery();
        while (rs.next()){
        Autor oAutor = new Autor();
        oAutor.setIdAutor(rs.getInt("idAutor"));
        oAutor.setDescricao(rs.getString("descricao"));
              
       resultado.add(oAutor);
          }
       }catch (SQLException ex){
          System.out.println("Problemas ao listar Modelo! Erro: "+ex.getMessage());
       }
      return resultado;
    }
    
}
