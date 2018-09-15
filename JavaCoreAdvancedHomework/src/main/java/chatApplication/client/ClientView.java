package chatApplication.client;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientView extends JFrame {
	

	private static final long serialVersionUID = 1704244490943031641L;
	
	private JTextArea jTextArea;
	private JTextField jTextField;
	private JButton jButton;
	private Controller clientController;
	
	public ClientView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 500, 500, 500);
		
		jTextArea = new JTextArea();
		jTextArea.setLineWrap(true);
		jTextArea.setEditable(false);
		JScrollPane jScrollPane = new JScrollPane(jTextArea);
		add(jScrollPane, BorderLayout.CENTER);
		
		JPanel southPanel = new JPanel(new BorderLayout());	
		jTextField = new JTextField();
		jButton = new JButton("send");
		southPanel.add(jTextField, BorderLayout.CENTER);
		southPanel.add(jButton, BorderLayout.EAST);
		add(southPanel, BorderLayout.SOUTH);
		
		jTextField.addActionListener(e -> sendMessage());
		jButton.addActionListener(e -> sendMessage());
		
		setVisible(true);
	}
	
	private void readMessage() {
		while(true) {
			String message = clientController.reciveMessage();
			jTextArea.append(message + "\n");
		}
	}
	
	

	public void init(Controller clientController) {
		this.clientController = clientController;
		new Thread(() -> readMessage(), "clientMessageReader").start();
	}
	


	private void sendMessage() {
		String message = jTextField.getText();
		clientController.sendMessage(message);
		jTextField.setText("");
		
	}
}
