package it.univr.view;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import java.util.List;

import it.univr.control.DataSource;
import it.univr.model.IngressoBean;

@ManagedBean(name = "ingresso")
@SessionScoped

public class IngressoView implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataSource ds=null;
	private List<IngressoBean> ingressibiglietto;
	private List<IngressoBean> ingressitelepass;
	public IngressoView(){
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
	
	public List<IngressoBean> getIngressiBiglietto(String nomesceltocasello){
		if (this.ds != null)
			this.ingressibiglietto = ds.getIngressiBiglietto(nomesceltocasello);
		return this.ingressibiglietto;
	}
	
	public List<IngressoBean> getIngressiTelepass(String nomesceltocasello){
		if (this.ds != null)
			this.ingressitelepass = ds.getIngressiTelepass(nomesceltocasello);
		return this.ingressitelepass;
	}
	

}
