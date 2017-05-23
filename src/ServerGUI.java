

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.event.ListSelectionListener;



import javax.swing.event.ListSelectionEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ServerGUI {
	
	private DefaultListModel modelsensor;
	private DefaultListModel modelrecords;
	Server server;
	private JFrame frame;
	private JList list;
	private ArrayList<ServerConnection> connection;
	JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void display(Server server) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerGUI window = new ServerGUI(server);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ServerGUI(Server server) {
		this.server=server;
		initialize();
		
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(204, 255, 255));
		frame.setBounds(300, 100, 473, 444);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setTitle("Server");
		
			
		JLabel lblSensorList = new JLabel("Sensors List");
		lblSensorList.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSensorList.setBounds(10, 31, 89, 14);
		frame.getContentPane().add(lblSensorList);
		
		JLabel lblValues = new JLabel("Temperature");
		lblValues.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblValues.setBounds(224, 31, 104, 14);
		frame.getContentPane().add(lblValues);
		
		//Server server= new Server(); 
		connection= connection=server.getConnections();
		modelsensor=new DefaultListModel();
		for(int i=0;i<connection.size();i++)
		{
			modelsensor.addElement(connection.get(i).getName());
		}
		list=new JList(modelsensor);	
		list.setModel(modelsensor);
		
		
		list.setBounds(10, 60, 180, 208);
		frame.getContentPane().add(list);
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
		    @Override
		    public void run() {
		    	int index=list.getSelectedIndex();
		    	modelsensor.clear();
		    	connection= server.connections;
		    	for(int i=0;i<connection.size();i++)
				{
					modelsensor.addElement(connection.get(i).getName());
				}
				
				list.setModel(modelsensor);
				list.setSelectedIndex(index);
				
				/**refreshing */
				
		    }
		}, 1000, 1000);
		
		textArea = new JTextArea();
		textArea.setBounds(224, 56, 210, 212);
		frame.getContentPane().add(textArea);
		textArea.setLineWrap(true);
		textArea.getScrollableTracksViewportWidth();
		
		
	}
}
