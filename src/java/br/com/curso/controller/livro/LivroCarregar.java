/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.curso.controller.livro;

import br.com.curso.dao.AutorDAO;
import br.com.curso.dao.EditoraDAO;
import br.com.curso.dao.GeneroDAO;
import br.com.curso.dao.LivroDAO;
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
@WebServlet(name = "LivroCarregar", urlPatterns = {"/LivroCarregar"})
public class LivroCarregar extends HttpServlet {

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
        try{
            int idLivro = Integer.parseInt(request.getParameter("idLivro"));
            
            LivroDAO oLivroDAO = new LivroDAO();
            
            request.setAttribute("livro",oLivroDAO.carregar(idLivro));
            
            AutorDAO oAutorDAO = new AutorDAO();
            request.setAttribute("autors", oAutorDAO.listar());
            
            EditoraDAO oEditoraDAO = new EditoraDAO();
            request.setAttribute("editoras", oEditoraDAO.listar());
            
            GeneroDAO oGeneroDAO = new GeneroDAO();
            request.setAttribute("generos", oGeneroDAO.listar());
                    
            request.getRequestDispatcher("/cadastros/livro/livroCadastrar.jsp").forward(request, response);
        }catch (Exception ex){
            System.out.println("Erro carregar veiculo" +ex.getMessage());
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
