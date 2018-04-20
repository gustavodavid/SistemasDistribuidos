package Models;


import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class AgenteEscutarMensagens implements Runnable {

	Conexao contexto;
	
	public AgenteEscutarMensagens(Conexao contexto) {
		
		this.contexto = contexto;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		Socket cliente;
		try {
			
			Scanner scanner = new Scanner(contexto.getConexao().getInputStream());
			
			while(scanner.hasNextLine()) {
				
				this.contexto.receberMensagem( scanner.nextLine() );
			}
			
//			scanner.close();
//			contexto.conexao.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
