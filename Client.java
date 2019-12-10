package socket;

import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Client implements ActionListener {
	JButton sendBtn;
	JTextArea message;
	JTextField sendTxt;
	String userName;

	private DataInputStream din;
	private DataOutputStream dout;
	public Client() {
		userName = JOptionPane.showInputDialog("User´s name");
		JFrame window = new JFrame("TrinityChat");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel main = new JPanel();
		main.setLayout(new BorderLayout());
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new BorderLayout());
		message = new JTextArea();
		sendBtn = new JButton("Send");
		sendBtn.addActionListener(this);
		sendTxt = new JTextField();
		JLabel label1 = new JLabel(userName + ": ");
		subPanel.add(label1, BorderLayout.WEST);
		subPanel.add(sendBtn, BorderLayout.EAST);
		subPanel.add(sendTxt, BorderLayout.CENTER);
		main.add(message, BorderLayout.CENTER);
		main.add(subPanel, BorderLayout.SOUTH);

		window.getContentPane().add(main);

		window.setSize(600, 600);
		window.setVisible(true);
	}


	public void actionPerformed(ActionEvent evt){
		if (evt.getSource() == sendBtn) {
			String m = sendTxt.getText();
			message.append(userName + ": " + m + "\n");
            try {
				sendMessage(m);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sendTxt.setText(null);
            
		}

	}
	public void sendMessage(String o)throws Exception{
		dout.writeUTF(o);
		dout.flush();
	}

public String getTexto() {
	String i=sendTxt.getText();
	return i;
}
public void printSer(String o) {
	message.append("Server: "+o+"\n");
	
}

public void printSer1()throws Exception{

	Socket s=new Socket("localhost",3333); 
	 din=new DataInputStream(s.getInputStream());  
	 dout=new DataOutputStream(s.getOutputStream()); 
		String str="", str2="";
		while(!str.equals("stop")){  
		str=getTexto();  
		str2=din.readUTF();  
		System.out.println("Server says: "+str2);
		printSer(str2);
		} 
		  
		dout.close();  
		s.close(); 

}

	public static void main(String args[])throws Exception{
		Client d=new Client();
		 d.printSer1();
		}

		
	}
