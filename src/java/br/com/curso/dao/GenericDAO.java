
package br.com.curso.dao;

import java.util.List;


public interface GenericDAO {
 
    public boolean cadastrar(Object objeto);
    public boolean inserir(Object objeto);
    public boolean alterar(Object objeto);
    public boolean excluir(int numero);
    public Object carregar(int numero);
    public List<Object>listar();
    
}
