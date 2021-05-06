package com.perisic.beds.peripherals;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ShowImageFromURL {

	@SuppressWarnings("deprecation")
	public static void show(String urlLocation) { 
		Image image = null;
		JFrame frmAdultHealthChecker = new JFrame();
		frmAdultHealthChecker.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmAdultHealthChecker.getContentPane().setForeground(Color.LIGHT_GRAY);
		frmAdultHealthChecker.setTitle("Adult Health Checker");
		frmAdultHealthChecker.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Bar Chart Analysis");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(250, 11, 178, 22);
		frmAdultHealthChecker.getContentPane().add(lblNewLabel);

		System.out.println(urlLocation);
		JLabel lblimage = new JLabel(new ImageIcon(urlLocation));

		frmAdultHealthChecker.add(lblimage);
		frmAdultHealthChecker.setSize(1366, 700);		
		frmAdultHealthChecker.setVisible(true);

	}
}
