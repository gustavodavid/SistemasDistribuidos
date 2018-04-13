package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Controllers.ControllerConexoes;

public class Servidor {
	
	private ControllerConexoes contexto;
	private ServerSocket servidor;
	private Thread agenteEscutarNovasConexoes;
	
	public Servidor(ControllerConexoes contexto) {
		
		this.contexto = contexto;
		
		this.instanciarServidor(this.contexto.getPortaServidor());
	}
	
	// Serviços
	private void instanciarServidor(String porta) {
		
		try {
			
			System.out.println(porta);
			
			this.servidor = new ServerSocket(Integer.parseInt(porta));
			
			AgenteEscutarNovaConexao agente = new AgenteEscutarNovaConexao(this);
			this.agenteEscutarNovasConexoes = new Thread(agente);
			this.agenteEscutarNovasConexoes.start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void receberNovaConexao(Socket novaConexao) {
		
		this.getContexto().receberNovaConexao(novaConexao);
	}
	
	// Get&Set
	public ServerSocket getServidor() {
		return servidor;
	}

	public void setServidor(ServerSocket servidor) {
		this.servidor = servidor;
	}

	public ControllerConexoes getContexto() {
		return contexto;
	}

	public void setContexto(ControllerConexoes contexto) {
		this.contexto = contexto;
	}
}