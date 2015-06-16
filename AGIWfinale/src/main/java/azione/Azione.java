package azione;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public abstract class Azione {
	public abstract String esegui(HttpServletRequest request) throws ServletException, IOException, InterruptedException, ExecutionException;	
}
