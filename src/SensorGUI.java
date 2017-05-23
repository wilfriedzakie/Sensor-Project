import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;

public class SensorGUI {

	private JFrame frame;
	private JTextField textField;
	ArrayList<Sensor> sensorList= new ArrayList<Sensor>() ;
	private DefaultListModel modelsensor;
	private JList list;
	private JButton btnStopSensor;
	private JButton btnResumeSensor;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SensorGUI window = new SensorGUI();
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
	public SensorGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(204, 255, 255));
		frame.setBounds(100, 100, 450, 373);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Sensor");
		
		textField = new JTextField();
		textField.setBounds(10, 44, 144, 27);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnCreateSensor = new JButton("Create Sensor");
		btnCreateSensor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sensor sensor=new Sensor(textField.getText());
				modelsensor.addElement(sensor.toString());
				sensorList.add(sensor);
			}
		});
		btnCreateSensor.setBounds(186, 46, 120, 23);
		frame.getContentPane().add(btnCreateSensor);
		
		JLabel lblEnterSensorName = new JLabel("Enter Sensor Name");
		lblEnterSensorName.setFont(new Font("Sylfaen", Font.PLAIN, 13));
		lblEnterSensorName.setBounds(10, 22, 112, 22);
		frame.getContentPane().add(lblEnterSensorName);
		
		modelsensor=new DefaultListModel();
		list=new JList(modelsensor);	
		//list.setModel(modelsensor);
		
		
		list.setBounds(10, 95, 180, 161);
		frame.getContentPane().add(list);
		
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
		    @Override
		    public void run() {
		    	int index=list.getSelectedIndex();
		    	modelsensor.clear();
		    	for(int i=0;i<sensorList.size();i++)
				{
					modelsensor.addElement(sensorList.get(i).toString());
				}
				
				list.setModel(modelsensor);
				list.setSelectedIndex(index);
				
				/**refreshing */
				
		    }
		}, 1000, 1000);		
		
		
		
	}
}
