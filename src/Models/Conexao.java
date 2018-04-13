package Models;

import java.net.Socket;

public class Conexao {
	
	private Socket conexao;
	private String nomeMaquina;
	private Thread agenteEscutarMensagens;

	
	// Serviços
	public void enviarMensagem(String mensagem) {
		
	}
	
	public void receberMensagem(String mensagem) {
		
	}
	
	// Get&Set
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
	
}