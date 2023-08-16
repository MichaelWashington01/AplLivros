/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.curso.model;

/**
 *
 * @author maico
 */
public class Genero {
    private int idGenero;
    private String descricao;

    public Genero() {
        this.idGenero=0;
        this.descricao="";
    }

    public Genero(int idGenero, String descricao) {
        this.idGenero = idGenero;
        this.descricao = descricao;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
}
