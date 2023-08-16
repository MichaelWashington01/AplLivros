<%-- 
    Document   : cidade
    Created on : 03/05/2023, 22:13:23
    Author     : maico
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="iso-8859-1" %>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>
    
    
    <h2>Livros</h2>
    <div class="col-12 panel-body">
    <table id="datatable" class="table table-striped table-bordered basic-datatable">
        <thead>
            <tr>
                <th align="left">ID</th>
                <th align="left">Titulo</th>
                <th align="left">Autor</th>
                <th align="left">Editora</th>
                <th align="left">Genêro</th>
                <th align="left">Ano</th>
                <th align="left">Comentario</th>
                <th align="right"></th>
                <th align="right"></th>
            </tr>
            
        </thead>
        <tbody>
            <c:forEach var="livro" items="${livros}">
                <tr>
                    <td align="left">${livro.idLivro}</td>
                    <td align="left">${livro.titulo}</td>
                    <td align="left">${livro.autor.descricao}</td>
                    <td align="left">${livro.editora.descricao}</td>
                    <td align="left">${livro.genero.descricao}</td>
                    <td align="left">${livro.ano}</td>
                    <td align="left">${livro.comentario}</td>
                    <td align="center">
                        <a href=
                           "${pageContext.request.contextPath}/LivroExcluir?idLivro=${livro.idLivro}">
                            <button class="btn btn-group-lg 
                                    <c:out value="${livro.titulo == 'titulo' ? 'btn-danger': 'btn-success'}"/>">
                                <c:out value="${livro.titulo == 'titulo' ? 'Inativar' : 'Excluir'}"/>
                            </button>
                            </a>
                    </td>
                            <td align="center">   
                                <a href=
                                   "${pageContext.request.contextPath}/LivroCarregar?idLivro=${livro.idLivro}">
                                    <button class="btn btn-group-lg btn-success"/>Alterar</button>
                                </a>
                               </td>       
                        </tr>
            </c:forEach>    
        </tbody>
        
    </table>
    </div>
    <div align="center">
        <a href="${pageContext.request.contextPath}/LivroNovo">Novo</a>
        <a href="index.jsp">Voltar à Página inicial</a>
    </div>
        
        <script>
            
            $(document).ready(function(){
              console.log('entrei ready');  
                $('#datatable').DataTable({
                   "oLanguage":{
                       "sProcessing": "Processando...",
                       "sLengthMenu": "Mostrar _MENU_registros", 
                       "sZeroRecords": "Nenhum registro encontrado.",
                       "sInfo": "Mostrando de _START_até _END_de _TOTAL_registros",
                       "sInfoEmpty": "Mostrando de 0 até 0 de 0 registros",
                       "sInfoFiltered": "",
                       "sInfoPostFix": "",
                       "sSearch": "Buscar:",
                       "sUrl": "",
                       
                       "oPaginate": {
                           "sFirst": "Primeiro",
                           "sPrevious": "Anterior",
                           "sNext": "Seguinte",
                           "sLast": "Último"
                       }
                   }          
               });
            });     
        </script>
        
        <%@include file="/footer.jsp"%>
