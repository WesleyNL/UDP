package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import io.InputHandler;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;

public class TelaUser extends JFrame {
	private static final long serialVersionUID = -3038640998840020961L;
	private JPanel contentPane;
	private JEditorPane epMensagens = new JEditorPane();
	private JScrollPane spMensagens = new JScrollPane(epMensagens);
	private InputHandler mensageiro;
	private JTextField txtMensagem = new JTextField();
	private JButton btEnviar = new JButton("Enviar msg");
	
	public TelaUser(final InputHandler mensageiro) {
		this.mensageiro = mensageiro;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		spMensagens.setBounds(0, 0, 434, 100);
		epMensagens.setEditable(false);
		
		txtMensagem.setBounds(0, 138, 434, 26);
		btEnviar.setBounds(172, 199, 100, 26);
		
		btEnviar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mensageiro.readInput(txtMensagem.getText());
				txtMensagem.setText("");
			}
		});
		
		contentPane.add(spMensagens);
		contentPane.add(txtMensagem);
		contentPane.add(btEnviar);
		
		setVisible(true);
	}
	
	public void insereMensagem(String mensagem)
	{
		epMensagens.setText(epMensagens.getText() + "\n" + mensagem);
	}

}
