import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Client implements ActionListener{
  private DataInputStream  console   = null;
private DataOutputStream streamOut = null;
  JButton sendBtn;
  JTextArea message;
  JTextField sendTxt;
  String userName;
  Socket s;
  public Client(){
userName = JOptionPane.showInputDialog("User´s name");
    JFrame window = new JFrame("TrinityChat");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel main =  new JPanel();
    main.setLayout(new BorderLayout() );
    JPanel subPanel=new JPanel();
    subPanel.setLayout(new BorderLayout());
    message=new JTextArea();
    sendBtn=new JButton("Send");
    sendBtn.addActionListener(this);
    sendTxt=new JTextField();
    JLabel label1=new JLabel(userName+": ");
    subPanel.add(label1,BorderLayout.WEST);
    subPanel.add(sendBtn,BorderLayout.EAST);
    subPanel.add(sendTxt,BorderLayout.CENTER);
    main.add(message,BorderLayout.CENTER);
    main.add(subPanel,BorderLayout.SOUTH);

    window.getContentPane().add(main);

window.setSize(600,600);
window.setVisible(true);

  }
  private void send(){
    try{
      streamOut.writeUTF(sendTxt.getText());
      streamOut.flush();

    }
   catch(IOException ioe)
   {  System.out.println("Sending error: " + ioe.getMessage());
 }
 }
    public void actionPerformed(ActionEvent evt){
if( evt.getSource() == sendBtn ){
  String m=sendTxt.getText();
  message.append(userName+": "+m+"\n");
  send();
    sendTxt.setText(null);
}

    }

public void createConecction(){
 s=new Socket("localhost",3333); 
}

public void talkMessenger(){
  
}
public static void main(String args[])throws Exception{

DataInputStream din=new DataInputStream(s.getInputStream());
DataOutputStream dout=new DataOutputStream(s.getOutputStream());
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

String str="",str2="";
while(!str.equals("stop")){
str=br.readLine();
dout.writeUTF(str);
dout.flush();
str2=din.readUTF();
System.out.println("Server says: "+str2);
}

dout.close();
s.close();*/
new Client();
}
}
