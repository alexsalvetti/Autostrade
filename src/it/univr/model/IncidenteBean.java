package it.univr.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class IncidenteBean implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timestamp i_data_ora;
	private String i_nome_ca1;
	private String i_nome_ca2;
	private float lunghezza_coda;
	private String foto_incidente;
	
	public String getFoto_incidente() {
		return foto_incidente;
	}
	public void setFoto_incidente(String foto_incidente) {
		this.foto_incidente = foto_incidente;
	}
	public Timestamp getI_data_ora() {
		return i_data_ora;
	}
	public void setI_data_ora(Timestamp i_data_ora) {
		this.i_data_ora = i_data_ora;
	}
	public String getI_nome_ca1() {
		return i_nome_ca1;
	}
	public void setI_nome_ca1(String i_nome_ca1) {
		this.i_nome_ca1 = i_nome_ca1;
	}
	public String getI_nome_ca2() {
		return i_nome_ca2;
	}
	public void setI_nome_ca2(String i_nome_ca2) {
		this.i_nome_ca2 = i_nome_ca2;
	}
	public float getLunghezza_coda() {
		return lunghezza_coda;
	}
	public void setLunghezza_coda(float lunghezza_coda) {
		this.lunghezza_coda = lunghezza_coda;
	}
	
	


}
