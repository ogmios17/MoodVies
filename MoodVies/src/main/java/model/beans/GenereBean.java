package model.beans;

import java.io.Serializable;

public class GenereBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id_genere;
    private String nome;
	
	public GenereBean() {
		id_genere = 0;
        nome = "";
	}

	public int getIdGenere() {
		return id_genere;
	}

	public void setIdGenere(int id_genere) {
		this.id_genere = id_genere;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}    

}