/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.curso.controller.livro;

import br.com.curso.dao.GenericDAO;
import br.com.curso.dao.LivroDAO;
import br.com.curso.model.Autor;
import br.com.curso.model.Editora;
import br.com.curso.model.Genero;
import br.com.curso.model.Livro;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author maico
 */
@WebServlet(name = "LivroCadastrar", urlPatterns = {"/LivroCadastrar"})
public class LivroCadastrar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        int idLivro = Integer.parseInt(request.getParameter("idlivro"));
        int idAutor = Integer.parseInt(request.getParameter("idautor"));
        int idEditora = Integer.parseInt(request.getParameter("ideditora"));
        int idGenero = Integer.parseInt(request.getParameter("idgenero"));
        
        String titulo = request.getParameter("titulo");
        int ano = Integer.parseInt(request.getParameter("ano"));
        String comentario = request.getParameter("comentario");
        String mensagem = null;
        
        try{
            Livro oLivro = new Livro();
            oLivro.setIdLivro(idLivro);
            oLivro.setTitulo(titulo);
            oLivro.setAno(ano);
            oLivro.setComentario(comentario);

            oLivro.setAutor(new Autor(idAutor, ""));
            oLivro.setEditora(new Editora(idEditora, ""));
            oLivro.setGenero(new Genero(idGenero, ""));
            
            GenericDAO dao = new LivroDAO();
            
            if(dao.cadastrar(oLivro)){
                mensagem = "livro cadastrado com sucesso!";
            }else{
                mensagem = "Problemas ao cadastrar Livro.Verifique os dados informados e tente novamente!";
            }
            request.setAttribute(mensagem, mensagem);
            response.sendRedirect("LivroListar");
        }catch(Exception ex){
            System.out.println("Problemas no Servlet ao cadastrar Livro! Erro:"+ex.getMessage());
            ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
