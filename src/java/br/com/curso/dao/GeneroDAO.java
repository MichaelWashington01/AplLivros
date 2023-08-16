/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.curso.dao;

import br.com.curso.model.Genero;
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
public class GeneroDAO implements GenericDAO{
    
    private Connection conexao;
    
    public GeneroDAO() throws Exception{
    conexao = SingleConnection.getConnection();
    }

    @Override
    public boolean cadastrar(Object objeto) {
        Genero oGenero = (Genero)objeto;
        boolean retorno=false;
        if(oGenero.getIdGenero()==0){
            retorno = this.inserir(oGenero);
        }else{
            retorno = this.alterar(oGenero);
        }
        return retorno;
    }

    @Override
    public boolean inserir(Object objeto) {
                Genero oGenero = (Genero)objeto;
        PreparedStatement stmt = null;
        String sql = "Insert into genero (descricao) values (?)";
        try{
          stmt = conexao.prepareStatement(sql);
          stmt.setString(1, oGenero.getDescricao());
          stmt.execute();
          conexao.commit();
          return true;
        }catch (Exception ex){
            try{
            System.out.println("Problemas ao cadastrar a Genero! Erro:" + ex.getMessage());
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
                Genero oGenero = (Genero)objeto;    
        PreparedStatement stmt = null;
        String sql = "update genero set descricao=? where idGenero=?";
        try{
          stmt = conexao.prepareStatement(sql);
          stmt.setString(1, oGenero.getDescricao());
          stmt.setInt(2, oGenero.getIdGenero());
          stmt.execute();
          conexao.commit();
          return  true;
        } catch (Exception ex){
            try{
                System.out.println("Problemas ao alterar a Genero! Erro:" +ex.getMessage());
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
        int idGenero = numero;
        PreparedStatement stmt = null;
        
        String sql = "delete from genero where idGenero=?";
        try{
            stmt=conexao.prepareStatement(sql);
            stmt.setInt(1, idGenero);
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex){
            System.out.println("Problemas ao excluir a Genero! Erro:" +ex.getMessage());
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
        int idGenero = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Genero oGenero = null;
        String sql="select * from genero where idGenero=?";
        
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idGenero);
            rs=stmt.executeQuery();
            while(rs.next()){
                oGenero = new Genero();
                oGenero.setIdGenero(rs.getInt("idGenero"));
                oGenero.setDescricao(rs.getString("descricao"));
            }
            return oGenero;
        }catch(Exception ex){
            System.out.println("Problemas ao carregar Genero! Erro:" + ex.getMessage());
            return  false;
        }
    }

    @Override
    public List<Object> listar() {
                 List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from genero order by idGenero";
        try{
        stmt = conexao.prepareStatement(sql);
        rs=stmt.executeQuery();
        while (rs.next()){
        Genero oGenero = new Genero();
        oGenero.setIdGenero(rs.getInt("idGenero"));
        oGenero.setDescricao(rs.getString("descricao"));
              
       resultado.add(oGenero);
          }
       }catch (SQLException ex){
          System.out.println("Problemas ao listar Genero! Erro: "+ex.getMessage());
       }
      return resultado;
    }
    
}
