package it.univr.model;

import java.io.Serializable;

public class CaselloBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cod_ca;
	private String nome_a;
	private String nome_ca;
	private float distanza;
	private int postazioni_totali;
	private int postazioni_telepass;
	
	public int getCod_ca() {
		return cod_ca;
	}
	public int getPostazioni_totali() {
		return postazioni_totali;
	}
	public void setPostazioni_totali(int postazioni_totali) {
		this.postazioni_totali = postazioni_totali;
	}
	public int getPostazioni_telepass() {
		return postazioni_telepass;
	}
	public void setPostazioni_telepass(int postazioni_telepass) {
		this.postazioni_telepass = postazioni_telepass;
	}
	public void setCod_ca(int cod_ca) {
		this.cod_ca = cod_ca;
	}
	public String getNome_ca() {
		return nome_ca;
	}
	public void setNome_ca(String nome_ca) {
		this.nome_ca = nome_ca;
	}
	public float getDistanza() {
		return distanza;
	}
	public void setDistanza(float distanza) {
		this.distanza = distanza;
	}
	public String getNome_a() {
		return nome_a;
	}
	public void setNome_a(String nome_a) {
		this.nome_a = nome_a;
	}
	
	

}
