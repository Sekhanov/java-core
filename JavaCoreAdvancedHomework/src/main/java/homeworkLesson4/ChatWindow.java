package homeworkLesson4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class ChatWindow extends JFrame {

	private static final long serialVersionUID = 3895525282035317205L;

	public ChatWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("MyChat");
		setBounds(500, 300, 500, 500);	
		JTextArea chatTextArea = new JTextArea();
		chatTextArea.setEditable(false);
		chatTextArea.setLineWrap(true);
		chatTextArea.setWrapStyleWord(true);
		chatTextArea.setBackground(new Color(179, 236, 255));
		
		JScrollPane jScrollPane = new JScrollPane(chatTextArea);		
		getContentPane().add(jScrollPane, BorderLayout.CENTER);
		
		JTextPane chatMessagePane = new JTextPane();
		chatMessagePane.setBackground(new Color(66, 244, 206));
		chatMessagePane.setPreferredSize(new Dimension(370, 50));
		chatMessagePane.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					e.consume();
					String message = chatMessagePane.getText();
					chatMessagePane.setText("");
					chatTextArea.append(message + "\n");
				}
			}
		});
		JScrollPane chatMessageScrollPane = new JScrollPane(chatMessagePane);		
		JButton sendMessageButton = new JButton("Отправить");
		sendMessageButton.setPreferredSize(new Dimension(100, 50));
		sendMessageButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String message = chatMessagePane.getText();
				chatMessagePane.setText("");
				chatTextArea.append(message + "\n");
				
			}
		});
		JPanel interfacePanel = new JPanel();
		interfacePanel.setLayout(new FlowLayout());
		interfacePanel.add(chatMessageScrollPane);
		interfacePanel.add(sendMessageButton);
		getContentPane().add(interfacePanel, BorderLayout.SOUTH);
		setVisible(true);
		


		
	}
}
