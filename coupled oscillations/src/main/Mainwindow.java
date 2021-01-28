
package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.*;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.filechooser.FileFilter;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import shapes.*;
import javax.swing.JRadioButton;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JCheckBox;
import javax.swing.SpinnerNumberModel;
//import actions.*;

public class Mainwindow {

	//create Icons with image ...
	//public static ImageIcon testIcon = new ImageIcon("testIcon.png");
	
	//public static PaintPanel mainPanel;
	public static JFrame mainFrame;
	public static PaintPanel mainPanel;
	public static int numbofmass = 2;
	public static JRadioButton Horbtn;
	public static JRadioButton Verbtn;
	public static Mass[] mass = new Mass[numbofmass];
	public static Point[] Opoint = new Point[numbofmass];
	public static amp[] amps = new amp[numbofmass];
	public static Thread tr1;
	public static boolean IsMoving = false;
	public static JButton StopBtn;
	public static JButton StartBtn;
	public static JSpinner dtspinner;
	
	public static void main(String[] args)
	{
		
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screensize.getWidth();
		double height = screensize.getHeight();
		
		mainFrame = new JFrame("oscillation");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(0, 0, (int)width, (int)height);
		mainFrame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("mass :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(1047, 216, 51, 22);
		mainFrame.getContentPane().add(lblNewLabel);
		
		JSpinner numofmass = new JSpinner();
		numofmass.setBounds(1092, 215, 51, 23);
		numofmass.setValue(2);
		mainFrame.getContentPane().add(numofmass);
		
		StopBtn = new JButton("Stop");
		StopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IsMoving = false;
				tr1.stop();
				StopBtn.setEnabled(false);
				StartBtn.setEnabled(true);
				shapes.Shape.Allshapes.clear();
				numbofmass = (Integer)numofmass.getValue();
				mass = new Mass[numbofmass];
				Opoint = new Point[numbofmass];
				amps = new amp[numbofmass];
				for(int i = 0 ; i < numbofmass ; i++){
					mass[i] = new Mass(1.00,(((int)mainPanel.getWidth())/(numbofmass+1))*(i+1)  ,(int)mainPanel.getHeight()/4);
					Opoint[i] = new Point(mass[i].getCenter().x,mass[i].getCenter().y);
					amps[i] = new amp((((int)mainPanel.getWidth())/(numbofmass+1))*(i+1)  ,(int)3*mainPanel.getHeight()/4);
				}
				mainPanel.repaint();
			}
		});
		StopBtn.setEnabled(false);
		StopBtn.setBounds(1047, 131, 89, 22);
		mainFrame.getContentPane().add(StopBtn);
		
		mainPanel = new PaintPanel();
		mainPanel.setBounds(1, 1, 1046 , 659);
		mainFrame.getContentPane().add(mainPanel);
		
		Verbtn = new JRadioButton("Vertical");
		Verbtn.setSelected(true);
		Verbtn.setBounds(1057, 160, 109, 22);
		mainFrame.getContentPane().add(Verbtn);
		
		Horbtn = new JRadioButton("Horizontal");
		Horbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Verbtn.setSelected(false);
				shapes.Shape.Allshapes.clear();
				numbofmass = (Integer)numofmass.getValue();
				mass = new Mass[numbofmass];
				Opoint = new Point[numbofmass];
				amps = new amp[numbofmass];
				for(int i = 0 ; i < numbofmass ; i++){
					mass[i] = new Mass(1.00,(((int)mainPanel.getWidth())/(numbofmass+1))*(i+1)  ,(int)mainPanel.getHeight()/4);
					Opoint[i] = new Point(mass[i].getCenter().x,mass[i].getCenter().y);
					amps[i] = new amp((((int)mainPanel.getWidth())/(numbofmass+1))*(i+1)  ,(int)3*mainPanel.getHeight()/4);
				}
				mainPanel.repaint();
			}
		});
		Horbtn.setSelected(false);
		Horbtn.setBounds(1053, 185, 109, 23);
		mainFrame.getContentPane().add(Horbtn);
		
		JButton EditBtn = new JButton("Edit");
		EditBtn.setBounds(1047, 97, 89, 23);
		mainFrame.getContentPane().add(EditBtn);
		
		StartBtn = new JButton("Start");
		StartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IsMoving = true;
				tr1 = new Thread(new Move());
				tr1.start();
				StartBtn.setEnabled(false);
				StopBtn.setEnabled(true);
			}
		});
		StartBtn.setBounds(1047, 63, 89, 23);
		mainFrame.getContentPane().add(StartBtn);
		
		
		
		JLabel deltlabel = new JLabel("dt :");
		deltlabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		deltlabel.setBounds(1047, 27, 51, 22);
		mainFrame.getContentPane().add(deltlabel);
		
		dtspinner = new JSpinner();
		dtspinner.setModel(new SpinnerNumberModel(new Double(0.01), null, null, new Double(0.01)));
		dtspinner.setBounds(1075, 29, 51, 23);
		mainFrame.getContentPane().add(dtspinner);
		Verbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Horbtn.setSelected(false);
				shapes.Shape.Allshapes.clear();
				numbofmass = (Integer)numofmass.getValue();
				mass = new Mass[numbofmass];
				Opoint = new Point[numbofmass];
				amps = new amp[numbofmass];
				for(int i = 0 ; i < numbofmass ; i++){
					mass[i] = new Mass(1.00,(((int)mainPanel.getWidth())/(numbofmass+1))*(i+1)  ,(int)mainPanel.getHeight()/4);
					Opoint[i] = new Point(mass[i].getCenter().x,(int)mainPanel.getHeight()/4);
					amps[i] = new amp((((int)mainPanel.getWidth())/(numbofmass+1))*(i+1)  ,(int)3*mainPanel.getHeight()/4);
				}
				mainPanel.repaint();
			}
		});
		mainFrame.setVisible(true);
		
		//Spring[] spring = new Spring[numbofmass + 1];
		mass = new Mass[numbofmass];
		Opoint = new Point[numbofmass];
		amps = new amp[numbofmass];
		for(int i = 0 ; i < numbofmass ; i++){
			mass[i] = new Mass(1.00,(int)((mainPanel.getWidth())/(numbofmass+1))*(i+1),(int)mainPanel.getHeight()/4);
			Opoint[i] = new Point(mass[i].getCenter().x,mass[i].getCenter().y); 
			amps[i] = new amp((int)((mainPanel.getWidth())/(numbofmass+1))*(i+1),(int)3*mainPanel.getHeight()/4);
		}
		//spring[0] = new Spring(100.00,new Point(10, (int)mainPanel.getHeight()/2),new Point(mass[0].getCenter().x ,mass[0].getCenter().y));
		//for(int i = 1 ; i < numbofmass ; i++){
		//	spring[i] = new Spring(100.00,new Point(mass[i-1].getCenter().x ,mass[i-1].getCenter().y),new Point(mass[i].getCenter().x ,mass[i].getCenter().y));
		//}
		//spring[numbofmass] = new Spring(100.00,new Point(mass[numbofmass-1].getCenter().x, mass[numbofmass-1].getCenter().y),new Point((int)mainPanel.getWidth() - 15, (int)mainPanel.getHeight()/2));
		mainPanel.repaint();

		EditBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				shapes.Shape.Allshapes.clear();
				numbofmass = (Integer)numofmass.getValue();
				//Spring[] spring = new Spring[numbofmass + 1];
				mass = new Mass[numbofmass];
				Opoint = new Point[numbofmass];
				amps = new amp[numbofmass];
				for(int i = 0 ; i < numbofmass ; i++){
					mass[i] = new Mass(1.00,(((int)mainPanel.getWidth())/(numbofmass+1))*(i+1)  ,(int)mainPanel.getHeight()/4);
					Opoint[i] = new Point(mass[i].getCenter().x,mass[i].getCenter().y);
					amps[i] = new amp((((int)mainPanel.getWidth())/(numbofmass+1))*(i+1)  ,(int)3*mainPanel.getHeight()/4);
				}
				//spring[0] = new Spring(100.00,new Point(10, (int)mainPanel.getHeight()/2),new Point(mass[0].getCenter().x ,mass[0].getCenter().y));
				//for(int i = 1 ; i < numbofmass ; i++){
				//	spring[i] = new Spring(100.00,new Point(mass[i-1].getCenter().x ,mass[i-1].getCenter().y),new Point(mass[i].getCenter().x ,mass[i].getCenter().y));
				//}
				//spring[numbofmass] = new Spring(100.00,new Point(mass[numbofmass-1].getCenter().x, mass[numbofmass-1].getCenter().y),new Point((int)mainPanel.getWidth() - 15, (int)mainPanel.getHeight()/2));
				mainPanel.repaint();
			}
		});
		
		int n = 4;
		for (int r = 1 ; r <= n ; r++){
			System.out.println(2*Math.sin((r*Math.PI)/(2*(n+1))));
		}
				
	}
}
