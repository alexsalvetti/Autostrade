package it.univr.control;

import it.univr.model.AutostradaBean;
import it.univr.model.CaselloBean;
import it.univr.model.DipendenteBean;
import it.univr.model.IncidenteBean;
import it.univr.model.IngressoBean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



/**
 * Classe DataSource.
 * Questa classe mette a disposizione i metodi per effettuare interrogazioni
 * sulla base di dati.
 * @author Davide Faustini, Alex Salvetti,  
 */
public class DataSource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** User della base di dati. */
	private String user = "userlab67";

	/** Password dell'utente della base di dati. */
	private String passwd = "sessantasetteBV";

	/** Url d'accesso alla base di dati. */
	private String url = "jdbc:postgresql://dbserver.scienze.univr.it/dblab67";

	/** Driver da utilizzare per la connessione e l'esecuzione delle query. */
	private String driver = "org.postgresql.Driver";

	/**
	 * Costruttore della classe. 
	 * Carica i driver da utilizzare per la connessione alla base di dati.
	 *
	 * @throws ClassNotFoundException 
	 * Eccezione generata nel caso in cui i driver per la connessione non siano trovati nel CLASSPATH.
	 */
	public DataSource() throws ClassNotFoundException {
		Class.forName( driver );
	}

	/**
	 * ******************************************************************************
	 * 			QUERY
	 * 
	 * *****************************************************************************.
	 */

	/**
	 * 
	 * Seleziona TUTTE le tipologie di contratto, da mostrare in HOME
	 * @return the tipologie
	 */



	/**
	 * Esegue il login del cliente preciso, ritorna NULL se è presente un errore.
	 *
	 * @param username l'utente
	 * @param password la password
	 * @return login
	 */
	public DipendenteBean getLogin(String username, String password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DipendenteBean result = new DipendenteBean();
		try {
			con = DriverManager.getConnection( url, user, passwd );

			pstmt = con.prepareStatement( "SELECT * FROM dipendente WHERE login= ? AND password=?" );
			pstmt.clearParameters();
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			// eseguo la query
			rs = pstmt.executeQuery();
			// memorizzo il risultato dell'interrogazione in Vector di Bean
			if(rs.next()){

				result.setCod_d(rs.getInt("cod_d"));
				result.setCognome_d(rs.getString("cognome_d"));

				result.setLogin(username);
				result.setNome_d(rs.getString("nome_d"));
			}else result=null;

		} catch( SQLException sqle ) { // Catturo le eventuali eccezioni
			sqle.printStackTrace();

		} finally { // alla fine chiudo la connessione.
			try {

				con.close();
			} catch( SQLException sqle1 ) {
				sqle1.printStackTrace();
			}
		}
		return result;
	}

	public List<AutostradaBean> getAutostrade() {
		// dichiarazione delle variabili
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<AutostradaBean> result = new ArrayList<AutostradaBean>();

		String q = getQueryAutostrade();

		try {
			// tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// connessione riuscita, ottengo l'oggetto per l'esecuzione
			// dell'interrogazione.
			stmt = con.createStatement();
			// eseguo l'interrogazione desiderata
			rs = stmt.executeQuery(q);

		

			// memorizzo il risultato dell'interrogazione nel Vector
			while (rs.next()) {
				result.add(makeAutostrada(rs));
			}

		} catch (SQLException sqle) { // catturo le eventuali eccezioni!
			sqle.printStackTrace();

		} finally { // alla fine chiudo la connessione.
			try {
				con.close();
			} catch (SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return result;
	}

	public String getQueryAutostrade(){

		return "SELECT distinct a.cod_a, a.nome_a, a.lunghezza, COUNT(*) AS n_caselli "
				+ "FROM autostrada a JOIN casello c on a.cod_a = c.cod_a  " 
				+ "GROUP BY a.cod_a " 
				+ "ORDER BY a.cod_a;"; 

	}
	
	public AutostradaBean makeAutostrada(ResultSet rs) throws SQLException{
		
		AutostradaBean result = new AutostradaBean();
		result.setCod_a(rs.getInt("cod_a"));
		result.setNome_a(rs.getString("nome_a"));
		result.setN_caselli(rs.getInt("n_caselli"));
		result.setLunghezza(rs.getFloat("lunghezza"));
		return result;
		
}

	
	public AutostradaBean getInfoAutostradaScelta(int codicesceltoautostrada) {
		// dichiarazione delle variabili
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		AutostradaBean result = new AutostradaBean();


		try {
			// tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// connessione riuscita, ottengo l'oggetto per l'esecuzione
			// dell'interrogazione.
			stmt = con.prepareStatement("SELECT a.cod_a, a.nome_a, a.lunghezza, a.traffico_medio FROM autostrada a WHERE a.cod_a=?");
			stmt.clearParameters();
		    stmt.setInt(1, codicesceltoautostrada);
			// eseguo l'interrogazione desiderata
			rs = stmt.executeQuery();

		

			// memorizzo il risultato dell'interrogazione nel Vector
			while (rs.next()) {
				result=makeInfoAutostradaScelta(rs);
			}

		} catch (SQLException sqle) { // catturo le eventuali eccezioni!
			sqle.printStackTrace();

		} finally { // alla fine chiudo la connessione.
			try {
				con.close();
			} catch (SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return result;
	}
	
	public String getQueryInfoAutostradaScelta(){

		return "SELECT distinct a.cod_a, a.nome_a, a.lunghezza, COUNT(*) AS n_caselli "
				+ "FROM autostrada a JOIN casello c on a.cod_a = c.cod_a  " 
				+ "GROUP BY a.cod_a " 
				+ "ORDER BY a.cod_a;"; 

	}

	private AutostradaBean makeInfoAutostradaScelta(ResultSet rs) throws SQLException {
		AutostradaBean result = new AutostradaBean();
		result.setCod_a(rs.getInt("cod_a"));
		result.setNome_a(rs.getString("nome_a"));
		result.setLunghezza(rs.getFloat("lunghezza"));
		result.setTraffico_medio(rs.getInt("traffico_medio"));
		return result;
	}
	
	public List<CaselloBean> getInfoCaselliConAutostradaScelta(int codicesceltoautostrada) {
		// dichiarazione delle variabili
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<CaselloBean> result = new ArrayList<CaselloBean>();


		try {
			// tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// connessione riuscita, ottengo l'oggetto per l'esecuzione
			// dell'interrogazione.
			stmt = con.prepareStatement("SELECT c.nome_ca, c.distanza FROM casello c WHERE c.cod_a=?");
			stmt.clearParameters();
		    stmt.setInt(1, codicesceltoautostrada);
			// eseguo l'interrogazione desiderata
			rs = stmt.executeQuery();

		

			// memorizzo il risultato dell'interrogazione nel Vector
			while (rs.next()) {
				result.add(makeInfoCaselliConAutostradaScelta(rs));
			}

		} catch (SQLException sqle) { // catturo le eventuali eccezioni!
			sqle.printStackTrace();

		} finally { // alla fine chiudo la connessione.
			try {
				con.close();
			} catch (SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return result;
	}
	
	private CaselloBean makeInfoCaselliConAutostradaScelta(ResultSet rs) throws SQLException {
		CaselloBean result = new CaselloBean();
		result.setNome_ca(rs.getString("nome_ca"));
		result.setDistanza(rs.getFloat("distanza"));
		return result;
	}

	public CaselloBean getInfoCaselloScelto(String nomesceltocasello) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CaselloBean result = new CaselloBean();


		try {
			// tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// connessione riuscita, ottengo l'oggetto per l'esecuzione
			// dell'interrogazione.
			stmt = con.prepareStatement(" SELECT a.nome_a, c.nome_ca, c.distanza, c.postazioni_totali, c.postazioni_telepass FROM casello c JOIN autostrada a ON c.cod_a=a.cod_a WHERE c.nome_ca=?;");
			stmt.clearParameters();
		    stmt.setString(1, nomesceltocasello);
			// eseguo l'interrogazione desiderata
			rs = stmt.executeQuery();

		

			// memorizzo il risultato dell'interrogazione nel Vector
			while (rs.next()) {
				result=makeInfoCaselloScelto(rs);
			}

		} catch (SQLException sqle) { // catturo le eventuali eccezioni!
			sqle.printStackTrace();

		} finally { // alla fine chiudo la connessione.
			try {
				con.close();
			} catch (SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return result;
	}

	private CaselloBean makeInfoCaselloScelto(ResultSet rs) throws SQLException {
		CaselloBean result = new CaselloBean();
		result.setNome_a(rs.getString("nome_a"));
		result.setNome_ca(rs.getString("nome_ca"));
		result.setDistanza(rs.getFloat("distanza"));
		result.setPostazioni_totali(rs.getInt("postazioni_totali"));
		result.setPostazioni_telepass(rs.getInt("postazioni_telepass"));
		return result;
		
	}

	public List<IncidenteBean> getIncidenti(){
		// dichiarazione delle variabili
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<IncidenteBean> result = new ArrayList<IncidenteBean>();
	
		try {
			// tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// connessione riuscita, ottengo l'oggetto per l'esecuzione
			// dell'interrogazione.
			stmt = con.prepareStatement("SELECT i.i_data_ora, i.nome_ca1, i.nome_ca2, i.lunghezza_coda, i.foto_incidente FROM incidente i WHERE in_corso=1");
			stmt.clearParameters();
			// eseguo l'interrogazione desiderata
			rs = stmt.executeQuery();

			
			// memorizzo il risultato dell'interrogazione nel Vector
			while (rs.next()) {
			
				result.add(makeIncidenti(rs));
			}

		} catch (SQLException sqle) { // catturo le eventuali eccezioni!
			sqle.printStackTrace();

		} finally { // alla fine chiudo la connessione.
			try {
				con.close();
			} catch (SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return result;
	}
	
	private IncidenteBean makeIncidenti(ResultSet rs) throws SQLException {
		IncidenteBean result = new IncidenteBean();
		result.setI_data_ora(rs.getTimestamp("i_data_ora"));
		result.setI_nome_ca1(rs.getString("nome_ca1"));
		result.setI_nome_ca2(rs.getString("nome_ca2"));
		result.setLunghezza_coda(rs.getFloat("lunghezza_coda"));
		result.setFoto_incidente(rs.getString("foto_incidente"));
		return result;
	}

	@SuppressWarnings("deprecation")
	public List<IngressoBean> getIngressiBiglietto(String nomesceltocasello) {
		// dichiarazione delle variabili
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<IngressoBean> result = new ArrayList<IngressoBean>();
		Timestamp clockstart = new Timestamp(System.currentTimeMillis());
		clockstart.setHours(00);
		clockstart.setMinutes(00);
		clockstart.setSeconds(00);
		Timestamp clockend = new Timestamp(System.currentTimeMillis());
		clockend.setHours(23);
		clockend.setMinutes(59);
		clockend.setSeconds(59);
	
		try {
			// tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// connessione riuscita, ottengo l'oggetto per l'esecuzione
			// dell'interrogazione.
			stmt = con.prepareStatement("SELECT b.num_b, b.c_data_ora_i, b.nome_ca2, b.c_data_ora_u FROM biglietto b WHERE b.c_data_ora_i>=? and b.c_data_ora_i<=? and b.nome_ca1=?");
			stmt.clearParameters();
			stmt.setTimestamp(1, clockstart);
			stmt.setTimestamp(2, clockend);
		    stmt.setString(3, nomesceltocasello);
			// eseguo l'interrogazione desiderata
			rs = stmt.executeQuery();


			// memorizzo il risultato dell'interrogazione nel Vector
			while (rs.next()) {
				
				result.add(makeIngressiBiglietto(rs));
			}

		} catch (SQLException sqle) { // catturo le eventuali eccezioni!
			sqle.printStackTrace();

		} finally { // alla fine chiudo la connessione.
			try {
				con.close();
			} catch (SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return result;
	}


	@SuppressWarnings("deprecation")
	public List<IngressoBean> getIngressiTelepass(String nomesceltocasello) {
		// dichiarazione delle variabili
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<IngressoBean> result = new ArrayList<IngressoBean>();
		Timestamp clockstart = new Timestamp(System.currentTimeMillis());
		clockstart.setHours(00);
		clockstart.setMinutes(00);
		clockstart.setSeconds(00);
		Timestamp clockend = new Timestamp(System.currentTimeMillis());
		clockend.setHours(23);
		clockend.setMinutes(59);
		clockend.setSeconds(59);
	
		try {
			// tentativo di connessione al database
			con = DriverManager.getConnection(url, user, passwd);
			// connessione riuscita, ottengo l'oggetto per l'esecuzione
			// dell'interrogazione.
			stmt = con.prepareStatement("SELECT vt.cod_cl, vt.t_data_ora_i, vt.nome_ca2, vt.t_data_ora_u FROM viaggio_telepass vt WHERE vt.t_data_ora_i>=? and vt.t_data_ora_i<=? and vt.nome_ca1=?");
			stmt.clearParameters();
			stmt.setTimestamp(1, clockstart);
			stmt.setTimestamp(2, clockend);
		    stmt.setString(3, nomesceltocasello);
			// eseguo l'interrogazione desiderata
			rs = stmt.executeQuery();

		

			// memorizzo il risultato dell'interrogazione nel Vector
			while (rs.next()) {
				
				result.add(makeIngressiTelepass(rs));
			}

		} catch (SQLException sqle) { // catturo le eventuali eccezioni!
			sqle.printStackTrace();

		} finally { // alla fine chiudo la connessione.
			try {
				con.close();
			} catch (SQLException sqle1) {
				sqle1.printStackTrace();
			}
		}
		return result;
	}
	
	private IngressoBean makeIngressiBiglietto(ResultSet rs) throws SQLException {
		IngressoBean result = new IngressoBean();
		result.setNum_b(rs.getInt("num_b"));
		result.setB_data_ora_i(rs.getTimestamp("c_data_ora_i"));
		result.setB_data_ora_u(rs.getTimestamp("c_data_ora_u"));
		result.setB_nome_ca2(rs.getString("nome_ca2"));
		return result;
	}

	private IngressoBean makeIngressiTelepass(ResultSet rs) throws SQLException {
		IngressoBean result = new IngressoBean();
		result.setCod_cl(rs.getInt("cod_cl"));
		result.setT_data_ora_i(rs.getTimestamp("t_data_ora_i"));
		result.setT_data_ora_u(rs.getTimestamp("t_data_ora_u"));
		result.setT_nome_ca2(rs.getString("nome_ca2"));
		return result;
	}
	


/**
 * ******************************************************************************
 * 			Java Data Bean
 * 			Per ogni query, crea un JDBean
 * 
 * *****************************************************************************.
 */

/**
 * maketipologiaBean.
 *   
 * @param rs ResultSet
 * @return tipologia la tipologia 
 * @throws SQLException eccezione SQL exception
 */




}