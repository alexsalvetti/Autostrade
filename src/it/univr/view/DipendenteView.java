package it.univr.view;


import java.io.Serializable;

import it.univr.control.DataSource;
import it.univr.model.DipendenteBean;
import it.univr.model.SessionBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;


@ManagedBean(name = "dipendente")
@SessionScoped
/**
 * Definisce il Bean per la gestione di un cliente
 * @author Davide Faustini, Alex Salvetti
 *
 */
public class DipendenteView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataSource ds=null;
	private String username, password, nome, error;
	private int codice;
	
	/**
	 * Costruttore del Bean.
	 */
	public DipendenteView(){
		this.ds=null;
	}

	@PostConstruct
	/**
	 * Metodo che viene invocato dopo la costruzione del Bean.
	 * Crea una nuova istanza del datasource.
	 */
	public void initialize() { //inizializzo il data source
		try {
			this.ds = new DataSource();
		} catch (ClassNotFoundException e) {
			this.ds = null;
		}
	}
	
	/**
	 * 
	 * @return nome Il nome del cliente
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * 
	 * @param nome Il nome del cliente da impostare
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * 
	 * @return username Username associato all'utente
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @param username Username da associare all'utente
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	//TODO Che non sia il caso di togliere il getPassword()? :D 
	public String getPassword() {
		return password;
	}
	
	/**
	 * 
	 * @param password Imposta la password relativa all'username
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 
	 * @return codice Codice relativo all'utente
	 */
	public int getCodice() {
		return codice;
	}

	/**
	 * 
	 * @param codice Imposta il codice dell'utente
	 */
	public void setCodice(int codice) {
		this.codice = codice;
	}

	/**
	 * 
	 * @return error Eventuale messaggio di errore
	 */
	public String getError() {
		return error;
	}

	/**
	 * 
	 * @param error Imposta un eventuale messaggio di errore
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * Gestisce il login, chiamando l'apposito metodo nel DataSource.
	 *
	 * @return success Login andato a buon fine
	 * @return failure Login non andato a buon fine
	 */
	public String login(){
		if(ds!=null){
			DipendenteBean res=ds.getLogin(this.username,this.password);
			if(res!=null){
				HttpSession session = SessionBean.getSession();
				//login andato a buon fine
				this.codice=res.getCod_d();
				this.nome=res.getNome_d();

	            session.setAttribute("username", nome);
				error="";
				return "success";
			}
			else {
				error="Errore nel login.";
				return "failure";
			}
		}
		return "failure";
	}

}