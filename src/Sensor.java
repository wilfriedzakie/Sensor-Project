import java.io.*;
import java.net.Socket;
import java.util.ArrayList;



public class Sensor  extends Thread {
	private double data;
	private String name;
	
	private transient Socket s;
	private DataOutputStream theOut;
	private Sensor sensor;
	
//Sensor constructor
	public Sensor(String name){
		this.name=name;
		super.setName(name);
		data=(int) (Math.random()+20);
		try {
			s = new Socket("localhost",3333);
			theOut	=new DataOutputStream(s.getOutputStream());
			this.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			this.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	  }
	
	public void run(){

		while(true)
		{
			try {		
				this.setData((int)(Math.random()*40));
				theOut.writeDouble(this.getData());
				theOut.writeUTF(this.getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}       
		
	}
	
	
	public double getData() {
		return data;
	}

	public void setData(double data) {
		this.data=data;
	}

	public String toString()
	{
		return name;
	}
		
}
