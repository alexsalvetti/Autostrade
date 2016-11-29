package it.univr.view;

import it.univr.control.DataSource;
import it.univr.model.IncidenteBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "incidente")
@SessionScoped

public class IncidenteView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataSource ds=null;
	private List<IncidenteBean> incidenti = null;


	public IncidenteView(){
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

	public List<IncidenteBean> getIncidenti(){
		if (this.ds != null)
			this.incidenti = ds.getIncidenti();
		return this.incidenti;
	}


}