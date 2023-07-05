package model.beans;

import java.io.Serializable;

public class UtenteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
    private String nome;
    private String cognome;
    private String password;
	private String ruolo;
	private String telefono;
	
	public UtenteBean() {
		email = "";
        nome = "";
        cognome = "";
		password = "";
		ruolo = "";
		telefono = "";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}    

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}    

	public String getPassword() {
		return password;
	}    

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	} 
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	} 		

}