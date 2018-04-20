package Servicos;

import Controllers.ControllerConexoes;

public class Servicos {
	
	final private String SEPARADOR_ID = "";
	
	private ControllerConexoes contexto;
	
	
	
	public Servicos(ControllerConexoes contexto) {
		
		this.contexto = contexto;
	}
	
	public void identificarServico(String ip, String mensagem) {
		
		// Estrutura da mensagem -> id(separador_id)dados
		
		String[] aux = mensagem.split(SEPARADOR_ID);
		
		String id =  aux[0];
		String dados = aux[1];
		
		switch ( id ) {
		
		case "getConfiguracoesMaquinaRequest":
			// Existe um computador solicitando as configurações da minha máquina
			// Enviar uma mensagem para o mesmo informando as configurações
			// Colocar id getConfiguracoesMaquinaResponse + dados
			
			this.contexto.enviarConfiguracoesMaquinaConexao(ip);
			
			break;
			
		case "getConfiguracoesMaquinaResponse":
			// Existe um computador me informando as configurações do computador dele
			// Devo atualizar os dados deste computador no meu sistema
			
			this.contexto.atualizarConfiguracoesMaquinaConexao(ip, dados);
			
			break;
			
		default:
			break;
		
		}
	}
}