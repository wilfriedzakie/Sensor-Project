import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.*;
import java.net.*;
import java.net.Socket;
import java.util.ArrayList;



public class Server{

	ServerSocket myServerSocket;
	
    Socket s;
    DataOutputStream dout;
    
    public ArrayList<ServerConnection> getConnections() {
		return connections;
	}


	DataInputStream din;
    
	//ArrayList 
	ArrayList<ServerConnection> connections= new ArrayList<ServerConnection>() ;
 
    boolean on=true; 

    public static void main (String[] args) 
    { 
    	Server server=new Server();
        
    } 
    
    
    public Server() 
    { 
        try 
        { 
        	ServerGUI gui=new ServerGUI(this);
        	
        	myServerSocket = new ServerSocket(3333);
        	while(on){
        		
        		s=myServerSocket.accept();
        		ServerConnection sc= new ServerConnection(s,this,gui);
        		sc.start();
        		connections.add(sc);
        		try {
					sc.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        	}
        } 
        catch(IOException e) 
        { 
        	e.printStackTrace();
            System.out.println("Could not create server socket on port 11111. Quitting."); 
            System.exit(-1); 
        } 
    } 
    
     
}
