/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.curso.model;

/**
 *
 * @author maico
 */
public class Autor {
    
    private int idAutor;
    private String descricao;

    public Autor() {
        this.idAutor=0;
        this.descricao="";
    }

    public Autor(int idAutor, String descricao) {
        this.idAutor = idAutor;
        this.descricao = descricao;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
}
