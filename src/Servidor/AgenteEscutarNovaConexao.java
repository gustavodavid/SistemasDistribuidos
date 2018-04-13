package Servidor;

import java.io.IOException;
import java.net.Socket;

public class AgenteEscutarNovaConexao implements Runnable {
	
	Servidor contexto;
	
	public AgenteEscutarNovaConexao(Servidor contexto) {
		
		this.contexto = contexto;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true) {
		
			Socket cliente;
			try {
				cliente = contexto.getServidor().accept();
			
				System.out.println("Nova conexão com o cliente "+cliente.getInetAddress().getHostAddress());
			
				this.contexto.receberNovaConexao(cliente);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
