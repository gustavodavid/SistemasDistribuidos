package Utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ConfiguracoesMaquina {
	
	// Configurações para comunicação
	private String ip;
	private String porta;
	
	// Configurações físicas da máquina
	private String cpu;
	private String memoria;
	private Boolean ativa;
	
	public ConfiguracoesMaquina(String cpu, String memoria, String porta) {
		
		this.cpu = cpu;
		this.memoria = memoria;
		this.porta = porta;
		this.ativa = false;
		
		this.descobrirIpv4();
	}
	
	public void descobrirIpv4() {
		InetAddress iAddress;
		try {
			
			iAddress = InetAddress.getLocalHost();
			
			this.setIp(iAddress.getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Get&Set
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPorta() {
		return porta;
	}

	public void setPorta(String porta) {
		this.porta = porta;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getMemoria() {
		return memoria;
	}

	public void setMemoria(String memoria) {
		this.memoria = memoria;
	}

	public Boolean getAtiva() {
		return ativa;
	}

	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}
}