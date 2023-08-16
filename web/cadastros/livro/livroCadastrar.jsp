<%-- 
    Document   : cidadeCadastrar
    Created on : 03/05/2023, 22:14:40
    Author     : maico
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="iso-8859-1" %>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<form name="cadastrarlivro" action="LivroCadastrar" method="POST">
    <table>
        <thead>
            <tr>
                <th colspan="2" align="center">
                    Cadastro de Livros</th>
            </tr>
            <tr>
                <th colspan="2" align="center">${mensagem}</th>
            </tr>
        </thead>
        <tbody>
            <tr><td>ID:</td>
                <td><input type="text" name="idlivro" id="idlivro" value="${livro.idLivro}" readonly="readonly"/></td></tr>
            <tr><td>Titulo:</td>
                <td><input type="text" name="titulo" id="titulo" value="${livro.titulo}"  size="50" maxlength="50"/></td></tr>
            
            <tr>
                <td>Autor:</td>
                <td>
                    <select name="idautor" id="idautor">
                        <option value="">Selecione</option>
                        <c:forEach var="autor" items="${autors}">
                            <option value="${autor.idAutor}"
                                    ${livro.autor.idAutor == autor.idAutor ? "selected" : ""}>
                                    ${autor.descricao}
                            </option>
                        </c:forEach>
                </td>
            </tr>
                        <tr>
                <td>Editora:</td>
                <td>
                    <select name="ideditora" id="ideditora">
                        <option value="">Selecione</option>
                        <c:forEach var="editora" items="${editoras}">
                            <option value="${editora.idEditora}"
                                    ${livro.editora.idEditora == editora.idEditora ? "selected" : ""}>
                                    ${editora.descricao}
                            </option>
                        </c:forEach>
                </td>
            </tr>
            
                        <tr>
                <td>Genero:</td>
                <td>
                    <select name="idgenero" id="idgenero">
                        <option value="">Selecione</option>
                        <c:forEach var="genero" items="${generos}">
                            <option value="${genero.idGenero}"
                                    ${livro.genero.idGenero == genero.idGenero ? "selected" : ""}>
                                    ${genero.descricao}
                            </option>
                        </c:forEach>
                </td>
            </tr>
            
            <tr><td>Ano:</td>
                <td><input type="text" name="ano" id="ano" value="${livro.ano}"  size="50" maxlength="50"/></td></tr>
            
            <tr><td>Comentario:</td>
                <td><input type="text" name="comentario" id="comentario" value="${livro.comentario}"  size="50" maxlength="100"/></td></tr>
            
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" name="cadastrar" id="cadastrar" value="Cadastrar"/>
                    <input type="reset" name="limpar" id="limpar" value="Limpar"/>
                </td>
            </tr>
            <tr>
                <td align="center" colspan="2"><h5><a href="index.jsp">Voltar à Página inicial</a></h5></td>
            </tr>
        </tbody>
    </table>
</form>
                 <%@include file="/footer.jsp"%>

