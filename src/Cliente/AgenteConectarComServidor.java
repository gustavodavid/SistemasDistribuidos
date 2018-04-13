package Cliente;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import Controllers.ControllerConexoes;

public class AgenteConectarComServidor implements Runnable {

	ControllerConexoes contexto;
	String ip;
	int porta;
	
	public AgenteConectarComServidor(ControllerConexoes controllerConexoes, String ip, int porta) {
		// TODO Auto-generated constructor stub
		
		this.contexto = controllerConexoes;
		this.ip = ip;
		this.porta = porta;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			
			Socket novaConexao = new Socket(this.ip, this.porta);
			System.out.println("Cliente se conectou ao servidor!");
			
			this.contexto.receberNovaConexao(novaConexao);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Não possui servidor conectado neste ip:"+ip+" com esta porta:"+String.valueOf(porta));
			
			//e.printStackTrace();
		}
	}
}
