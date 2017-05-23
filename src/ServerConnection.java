import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;

public class ServerConnection extends Thread {
	
	private Socket s;
	DataInputStream din;
	private boolean serverOn=true;
	private ArrayList records;
	Server server;
	String name;
	ServerGUI gui;
	
	public ServerConnection (Socket socket, Server server, ServerGUI gui)
	{
		super("ServerConnection");
		this.s=socket;	
		this.server=server;
		records=new ArrayList();
		this.gui=gui;
		
	}
	
	public void run()
	{
		try {
			din=new DataInputStream(s.getInputStream());

			Sensor sensor;
			while(serverOn){
				while(din.available()==0){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}	
				double data=din.readDouble();
				name=din.readUTF();
				
				System.out.println(name+": temperature= "+data);
				gui.textArea.setText(name+": temperature= "+data+"\n"+gui.textArea.getText().trim()+"\n");
				//Store data in the arraylist
				records.add(data);	
				super.setName(name);
			}
			din.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public ArrayList getRecords() {
		return records;
	}



public String toString()
	{
		return name;
	}
}
