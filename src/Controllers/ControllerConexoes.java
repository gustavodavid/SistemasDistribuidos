package Controllers;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

import Cliente.AgenteConectarComServidor;
import Cliente.Cliente;
import Servidor.Servidor;
import Utils.ConfiguracoesMaquina;

public class ControllerConexoes {
	
	private Cliente cliente;
	private Servidor servidor;
	
	private ArrayList<Socket> conexoes = new ArrayList<Socket>();
	
	private ConfiguracoesMaquina configuracoesMaquina;
	
	public ControllerConexoes() {
		
		this.configuracoesMaquina = new ConfiguracoesMaquina("10", "5", "12345");
		
		this.servidor = new Servidor(this);
		
		this.varrerRede();
	}
	
	// Serviços
	public void receberNovaConexao(Socket novaConexao) {
		
		System.out.println("Conexão "+novaConexao.getInetAddress().getHostAddress()+" adicionada a lista.");
		this.conexoes.add(novaConexao);
	}
	
	public void enviarMensagem(String mensagem) {
		
		System.out.println("Número de servidores: "+this.conexoes.size());
		
		for (Socket s : conexoes) {
			
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
	
	private void varrerRede() {
		
		String ipBase = this.getIpBase();
		int ipInicial = 0;
		int ipFinal = 255;
		
		for (int i = ipInicial; i <= ipFinal; i++) {
			
			String ipVerificado = ipBase+String.valueOf(i);
			
			if( this.configuracoesMaquina.getIp().equals(ipVerificado) ) {

				System.out.println("Meu ip: "+ipVerificado);
			} else {
				
				if(this.existeConexao(ipVerificado).equals(ipVerificado)) {
					System.out.println("Meu ip: "+ipVerificado);
					
				} else {
				
				AgenteConectarComServidor agente = new AgenteConectarComServidor(this, ipVerificado, 12345);
				
				Thread agenteConectarComServidor = new Thread(agente);
				agenteConectarComServidor.start();
		
				}

			}
		}
		
		System.out.println("Terminou a varredura!");
	}
	
	public String existeConexao(String ipVerificado) {
//		System.out.println("Verificando se existe conexão já estabelecida!");
		for (Socket s : this.conexoes) {
			
			if(s.getInetAddress().getHostAddress().equals(ipVerificado)) {
				return s.getInetAddress().getHostAddress();
			}
		}
		
		return "";
	}
	
	// Get&Set
	
	public String getPortaServidor() {
		
		return this.configuracoesMaquina.getPorta();
	}
	
	public ArrayList<Socket> getConexoes() {
		return conexoes;
	}

	public String getIpBase() {
		System.out.println("IP: "+this.configuracoesMaquina.getIp());
		String ip = this.configuracoesMaquina.getIp();
		String[] ipBaseAux = ip.split("\\.");
		System.out.println(ipBaseAux.length);
		
		return (ipBaseAux[0]+"."+ipBaseAux[1]+"."+ipBaseAux[2]+".");
	}
}