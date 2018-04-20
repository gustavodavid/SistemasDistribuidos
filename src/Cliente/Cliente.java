package Cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import Controllers.ControllerConexoes;
import Models.Conexao;


public class Cliente {
	
	private ControllerConexoes contexto;
	final private String SEPARADOR_ID = "";
	
	public Cliente(ControllerConexoes contexto) {
		
		this.contexto = contexto;
	}
	
	public void enviarMensagem(String mensagem ) {
		
		System.out.println("Número de servidores: "+this.contexto.getConexoes().size());
		
		for (Conexao conexao: this.contexto.getConexoes()) {
			
			conexao.enviarMensagem(mensagem);
		}
	}

	public void enviarConfiguracoesMaquinaConexao(String ip, String configuracoesMaquina) {
		// TODO Auto-generated method stub
		
		System.out.println("Enviando configurações da máquina para o ip: "+ip);
		
		for (Conexao conexao : this.contexto.getConexoes()) {
			
			if( conexao.getEquals(ip)) {
				
				conexao.enviarMensagem("getConfiguracoesMaquinaResponse"+SEPARADOR_ID+configuracoesMaquina);
			}
		}
	}
}
