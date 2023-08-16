/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.curso.dao;

import br.com.curso.model.Autor;
import br.com.curso.model.Editora;
import br.com.curso.model.Genero;
import br.com.curso.model.Livro;
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
public class LivroDAO implements GenericDAO{
    
    
        private Connection conexao;
    
    public LivroDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public boolean cadastrar(Object objeto) {
                Livro oLivro = (Livro) objeto;
        Boolean retorno=false;
        if (oLivro.getIdLivro()== 0){
            retorno = this.inserir(oLivro);
        }else{
            retorno = this.alterar(oLivro);
        }
        return retorno;
    }

    @Override
    public boolean inserir(Object objeto) {
        Livro oLivro = (Livro) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into livro (titulo, autor, editora, genero, ano, comentario) values (?,?,?,?,?,?)";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oLivro.getTitulo());
            stmt.setInt(2, oLivro.getAutor().getIdAutor());
            stmt.setInt(3, oLivro.getEditora().getIdEditora());
            stmt.setInt(4, oLivro.getGenero().getIdGenero());
            stmt.setInt(5, oLivro.getAno());
            stmt.setString(6, oLivro.getComentario());
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex){
            try{
                System.out.println("Problemas ao cadastrar a Livro ! Erro:"+ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            }catch (SQLException e){
                System.out.println("Erro:"+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public boolean alterar(Object objeto) {
        Livro oLivro = (Livro) objeto;
        PreparedStatement stmt = null;
        String sql = "update livro set titulo=?, autor=?, editora=?, genero=?, ano=?, comentario=? where idlivro=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oLivro.getTitulo());
            stmt.setInt(2, oLivro.getAutor().getIdAutor());
            stmt.setInt(3, oLivro.getEditora().getIdEditora());
            stmt.setInt(4, oLivro.getGenero().getIdGenero());
            stmt.setInt(5, oLivro.getAno());
            stmt.setString(6, oLivro.getComentario());
            stmt.setInt(7, oLivro.getIdLivro());
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex){
            try{
                System.out.println("Problemas ao alterar a Livro! Erro:"+ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            }catch (SQLException e){
                System.out.println("Erro:"+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public boolean excluir(int numero) {
        int idLivro = numero;
        PreparedStatement stmt = null;
        
        String sql = "delete from livro where idlivro=?";
        try{
            stmt=conexao.prepareStatement(sql);
            stmt.setInt(1, idLivro);
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex){
            System.out.println("Problemas ao excluir a Livro! Erro:" +ex.getMessage());
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
        int idLivro = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Livro oLivro = null;
        String sql = "select * from livro where idlivro=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idLivro);
            rs=stmt.executeQuery();
            while (rs.next()){
                oLivro = new Livro();
                oLivro.setIdLivro(rs.getInt("idLivro"));
                oLivro.setTitulo(rs.getString("titulo"));
                oLivro.setAno(rs.getInt("ano"));
                oLivro.setComentario(rs.getString("comentario"));
                
                AutorDAO oAutorDAO = new AutorDAO();
                oLivro.setAutor((Autor)oAutorDAO.carregar(rs.getInt("autor")));
                
                EditoraDAO oEditoraDAO = new EditoraDAO();
                oLivro.setEditora((Editora)oEditoraDAO.carregar(rs.getInt("editora")));
                
                GeneroDAO oGeneroDAO = new GeneroDAO();
                oLivro.setGenero((Genero)oGeneroDAO.carregar(rs.getInt("genero")));
                
            }
            return oLivro;
        }catch (Exception ex) {
            System.out.println("Problemas ao carregar Livro! Erro:"+ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
                List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from livro order by idLivro";
        try{
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while (rs.next()){
                Livro oLivro = new Livro();
                oLivro.setIdLivro(rs.getInt("idLivro"));
                oLivro.setTitulo(rs.getString("titulo"));
                oLivro.setAno(rs.getInt("ano"));
                oLivro.setComentario(rs.getString("comentario"));
                
 
                AutorDAO oAutorDAO = null;
                GeneroDAO oGeneroDAO = null;
                EditoraDAO oEditoraDAO = null;
                
                try{
                    oAutorDAO = new AutorDAO();
                }catch (Exception ex){
                    System.out.println("Erro buscar autor"+ex.getMessage());
                    ex.printStackTrace();
                    
                }try {
                    oEditoraDAO = new EditoraDAO();
                } catch (Exception e) {
                    System.out.println("Erro buscar editora"+e.getMessage());
                    e.printStackTrace(); 
                }try{
                    oGeneroDAO = new GeneroDAO();
                }catch (Exception x){
                    System.out.println("Erro buscar generoo" + x.getMessage());
                    x.printStackTrace();
                }
                
                oLivro.setAutor((Autor)oAutorDAO.carregar(rs.getInt("autor")));
                oLivro.setEditora((Editora)oEditoraDAO.carregar(rs.getInt("editora")));
                oLivro.setGenero((Genero)oGeneroDAO.carregar(rs.getInt("genero")));

                resultado.add(oLivro);
            }
        }catch (SQLException ex){
            System.out.println("Problemas ao listar Livro! Erro:"+ex.getMessage());
        }
        return resultado;
    }
    
}
