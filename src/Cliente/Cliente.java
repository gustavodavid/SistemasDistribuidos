package Cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import Controllers.ControllerConexoes;


public class Cliente {
	
	private ControllerConexoes contexto;
	
	public Cliente(ControllerConexoes contexto) {
		
		this.contexto = contexto;
	}
	
	public void enviarMensagem(String mensagem) {
		System.out.println("Client enviando mensagem: "+mensagem);
		System.out.println("Número de servidores: "+this.contexto.getConexoes().size());
		
		for (Socket s : this.contexto.getConexoes()) {
			
			try {
				System.out.println("Enviando mensagem para: "+s.getInetAddress().getHostAddress());
				PrintStream saida = new PrintStream(s.getOutputStream());
				
				saida.println(mensagem);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
