package Models;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import Controllers.ControllerConexoes;
import Utils.ConfiguracoesMaquina;

public class Conexao {
	
	private ControllerConexoes contexto;
	private Socket conexao;
	private String nomeMaquina;
	private Thread agenteEscutarMensagens;
	private ConfiguracoesMaquina configuracoesMaquina;

	public Conexao(Socket conexao, ControllerConexoes contexto) {
		
		this.conexao = conexao;
		this.contexto = contexto;
		this.configuracoesMaquina = new ConfiguracoesMaquina("0", "0");
		
		this.conectarEscutarMensagem();
	}
	
	// Serviços
	public void conectarEscutarMensagem() {
		
		AgenteEscutarMensagens agente = new AgenteEscutarMensagens(this);
		this.agenteEscutarMensagens = new Thread(agente);
		this.agenteEscutarMensagens.start();
	}
	
	public void enviarMensagem(String mensagem) {
		
		try {
			System.out.println("Enviando mensagem para: "+this.getConexao().getInetAddress().getHostAddress());
			PrintStream saida = new PrintStream(this.getConexao().getOutputStream());
			
			saida.println(mensagem);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void receberMensagem(String mensagem) {
		
		String ip = this.getIP();
		
		this.contexto.receberMensagem(ip, mensagem);
	}
	
	// Get&Set
	public String getIP() {
		return this.conexao.getInetAddress().getHostAddress();
	}
	public Socket getConexao() {
		return conexao;
	}
	public void setConexao(Socket conexao) {
		this.conexao = conexao;
	}
	public String getNomeMaquina() {
		return nomeMaquina;
	}
	public void setNomeMaquina(String nomeMaquina) {
		this.nomeMaquina = nomeMaquina;
	}
	public Boolean getEquals(String ip) {
		
		if( this.getIP().equalsIgnoreCase(ip) ) {
			
			return true;
		}
		return false;
	}
}