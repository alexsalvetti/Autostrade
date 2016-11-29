package it.univr.view;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;

import it.univr.control.DataSource;
import it.univr.model.CaselloBean;

@ManagedBean(name = "casello")
@SessionScoped

public class CaselloView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataSource ds=null;
	private String nomesceltocasello=null;
	private CaselloBean casello;
	
	
	public CaselloView(){
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
	
	
	public CaselloBean getInfoCaselloScelto(String nomesceltocasello){
		if (this.ds != null)
			this.casello = ds.getInfoCaselloScelto(nomesceltocasello);
		return this.casello;
	}

	public String getNomesceltoCasello() {
		return nomesceltocasello;
	}

	public String setNomesceltoCasello(String nomesceltocasello) {
		this.nomesceltocasello = nomesceltocasello;
		return "casello";
	}
	
   

}
