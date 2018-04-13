package GUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controllers.ControllerConexoes;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIEnviarMensagem extends JFrame {

	private ControllerConexoes controller = new ControllerConexoes();
	private JPanel contentPane;
	JPanel painelButton = new JPanel();
	JButton enviarMensagem = new JButton("Enviar");
	JPanel painelText = new JPanel();
	JTextPane textArea = new JTextPane();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIEnviarMensagem frame = new GUIEnviarMensagem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIEnviarMensagem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		contentPane.add(painelButton, BorderLayout.EAST);
		enviarMensagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String mensagem = textArea.getText();
				System.out.println("GUI enviando mensagem: "+mensagem);
				controller.enviarMensagem(mensagem);
			}
		});
		
		
		painelButton.add(enviarMensagem);
		
		
		contentPane.add(painelText, BorderLayout.CENTER);
		painelText.setLayout(new BorderLayout(0, 0));
		
		
		textArea.setText("Digite o texto...");
		painelText.add(textArea, BorderLayout.CENTER);
	}

}
