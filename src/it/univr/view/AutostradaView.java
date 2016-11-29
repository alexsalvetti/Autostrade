package it.univr.view;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import java.util.List;

import it.univr.control.DataSource;
import it.univr.model.AutostradaBean;
import it.univr.model.CaselloBean;

@ManagedBean(name = "autostrada")
@SessionScoped

public class AutostradaView implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DataSource ds=null;
	private List<AutostradaBean> autostrade = null;
	private AutostradaBean infoautostradascelta = null;
	private int codicesceltoautostrada;
	private List<CaselloBean> infocaselliconautostradascelta = null;
	
	/**
	 * Costruttore del Bean.
	 */
	public AutostradaView(){
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
	
	public List<AutostradaBean> getAutostrade(){
		if (this.ds != null)
			this.autostrade = ds.getAutostrade();
		return this.autostrade;
	}
	
	public String setCodicesceltoAutostrada(int cod){
		this.codicesceltoautostrada = cod;
		return "autostrada";
	
	}
	
	public int getCodicesceltoAutostrada(){
		return this.codicesceltoautostrada;
	}
	
	public AutostradaBean getInfoAutostradaScelta(){
		if (this.ds != null)
			this.infoautostradascelta = ds.getInfoAutostradaScelta(codicesceltoautostrada);
		return this.infoautostradascelta;
	}
	
	public List<CaselloBean> getInfoCaselliConAutostradaScelta(){
		if (this.ds != null)
			this.infocaselliconautostradascelta = ds.getInfoCaselliConAutostradaScelta(codicesceltoautostrada);
		return this.infocaselliconautostradascelta;
	}

}
