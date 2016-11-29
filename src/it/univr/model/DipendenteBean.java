package it.univr.model;

import java.io.Serializable;


/**
 * Classe Dipendente.
 * Definisce un generico dipendente per un java data bean.
 * @author Davide Faustini, Alex Salvetti 
 */
public class DipendenteBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Variabili private. */
	private String nome_d,cognome_d,login;
	private int cod_d;
	
	
	public String getNome_d() {
		return nome_d;
	}
	public void setNome_d(String nome_d) {
		this.nome_d = nome_d;
	}
	public String getCognome_d() {
		return cognome_d;
	}
	public void setCognome_d(String cognome_d) {
		this.cognome_d = cognome_d;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setPassword(String password) {
	}
	public int getCod_d() {
		return cod_d;
	}
	public void setCod_d(int cod_d) {
		this.cod_d = cod_d;
	}
	

	
}
