package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import azione.Azione;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, String> comando2azione; 
	private Map<String, String> esito2pagina;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String prossimaPagina = null;
		String comando = this.leggiComando(request.getServletPath());
		String nomeAzione = this.comando2azione.get(comando);
		if (nomeAzione==null) {
			prossimaPagina = "/error.jsp";
		}
		else {
			Azione azione = null;
			try {
				azione =(Azione)Class.forName(nomeAzione).newInstance();
				String esitoAzione = azione.esegui(request);
				prossimaPagina = this.esito2pagina.get(esitoAzione);
			} catch (InstantiationException e) {
				prossimaPagina = "/error.jsp";
			} catch (IllegalAccessException e) {
				prossimaPagina = "/error.jsp";
			} catch (ClassNotFoundException e) {
				prossimaPagina = "/error.jsp";
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			ServletContext application  = getServletContext();
			RequestDispatcher rd = application.getRequestDispatcher(prossimaPagina);
			rd.forward(request, response);		
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String prossimaPagina = null;
		String comando = this.leggiComando(request.getServletPath());
		String nomeAzione = this.comando2azione.get(comando);
		if (nomeAzione==null) {
			prossimaPagina = "/error.jsp";
		}
		else {
			Azione azione = null;
			try {
				azione =(Azione)Class.forName(nomeAzione).newInstance();
				String esitoAzione = azione.esegui(request);
				prossimaPagina = this.esito2pagina.get(esitoAzione);
			} catch (InstantiationException e) {
				prossimaPagina = "/error.jsp";
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				prossimaPagina = "/error.jsp";
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				prossimaPagina = "/error.jsp";
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			ServletContext application  = getServletContext();
			RequestDispatcher rd = application.getRequestDispatcher(prossimaPagina);
			rd.forward(request, response);		
		}
	}
	
	@Override
	public void init() {
		this.comando2azione = new HashMap<String, String>();
		this.esito2pagina = new HashMap<String, String>();

		this.comando2azione.put("stats","azione.AzioneStats");
		
		this.esito2pagina.put("statsOK","/index.jsp");
		this.esito2pagina.put("statsKO","/index.jsp");
				
		this.esito2pagina.put("errore","/error.jsp");
	}

	public String leggiComando(String s){
		String ritornata=s.substring(1,s.length()-3);
		return ritornata;
	}
}

