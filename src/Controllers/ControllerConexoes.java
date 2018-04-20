package Controllers;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

import Cliente.AgenteConectarComServidor;
import Cliente.Cliente;
import Models.Conexao;
import Servidor.Servidor;
import Utils.ConfiguracoesMaquina;

public class ControllerConexoes {
	
	private Cliente cliente;
	private Servidor servidor;
	
	private ArrayList<Conexao> conexoes = new ArrayList<Conexao>();
	
	private ConfiguracoesMaquina configuracoesMaquina;
	
	public ControllerConexoes() {
		
		this.configuracoesMaquina = new ConfiguracoesMaquina("10", "5", "12345");
		
		this.servidor = new Servidor(this);
		
		this.varrerRede();
	}
	
	// Serviços
	public void receberNovaConexao(Socket novoSocket) {
		
		String ipNovaConexao = novoSocket.getInetAddress().getHostAddress();
		
		System.out.println("Conexão "+ipNovaConexao+" adicionada a lista.");
		
		Conexao conexaoJaExistente = this.existeConexao(ipNovaConexao);
		
		if(conexaoJaExistente != null) {
			
			this.conexoes.remove(conexaoJaExistente);
		}
		
		Conexao novaConexao = new Conexao(novoSocket, this);
		
		this.conexoes.add(novaConexao);
	}
	
	public void enviarMensagem(String mensagem) {
		
		this.cliente.enviarMensagem(mensagem);
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
				
				Conexao conexaoExistente = this.existeConexao(ipVerificado);
				
				if( conexaoExistente == null) {
					System.out.println("Meu ip: "+ipVerificado);
					
					this.conectarComServidor(ipVerificado);
				}
			}
		}
		
		System.out.println("Terminou a varredura!");
	}
	
	private void conectarComServidor(String ipVerificado) {
		
		AgenteConectarComServidor agente = new AgenteConectarComServidor(this, ipVerificado, 12345);
		
		Thread agenteConectarComServidor = new Thread(agente);
		agenteConectarComServidor.start();
	}
	
	
	public void receberMensagem(String ip, String mensagem) {
		
		System.out.println(ip+": "+mensagem);
	}
	
	public Conexao existeConexao(String ipVerificado) {
		System.out.println("Verificando se já existe conexão estabelecida!");
		
		for (Conexao conexao : this.conexoes) {
			
			String ipConexao = conexao.getConexao().getInetAddress().getHostAddress();
			
			if(ipVerificado.equals(ipConexao)) {
				
				return conexao;
			}
		}
		
		return null;
	}
	
	// Get&Set
	
	public String getPortaServidor() {
		
		return this.configuracoesMaquina.getPorta();
	}
	
	public ArrayList<Conexao> getConexoes() {
		return conexoes;
	}

	public String getIpBase() {
		System.out.println("IP: "+this.configuracoesMaquina.getIp());
		String ip = this.configuracoesMaquina.getIp();
		String[] ipBaseAux = ip.split("\\.");
		System.out.println(ipBaseAux.length);
		
		return (ipBaseAux[0]+"."+ipBaseAux[1]+"."+ipBaseAux[2]+".");
	}

	public void atualizarConfiguracoesMaquinaConexao(String ip, String dados) {
		// TODO Auto-generated method stub
		
		
	}

	public void enviarConfiguracoesMaquinaConexao(String ip) {
		// TODO Auto-generated method stub
		
		this.cliente.enviarConfiguracoesMaquinaConexao(ip, ""+this.configuracoesMaquina.getConfiguracoesMaquina());
	}
}