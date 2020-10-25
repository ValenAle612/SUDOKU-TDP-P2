package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.swing.FocusManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class ArchivoInvalido extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Create the frame.
	 */
	public ArchivoInvalido() {
		
		setIconImage(new ImageIcon(getClass().getResource("/img/6.png")).getImage());
		
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("      GAME OVER");
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setForeground(Color.YELLOW);
		lblNewLabel.setFont(new Font("Britannic Bold", Font.PLAIN, 60));
		lblNewLabel.setBounds(-42, -17, 595, 120);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("/img/pacman grande2.png")));
		lblNewLabel_1.setBounds(324, 90, 108, 115);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(getClass().getResource("/img/grande2.png")));
		lblNewLabel_3.setBounds(12, 97, 108, 100);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("CONTINUAR");
		btnNewButton.setBorder(new CompoundBorder(new LineBorder(new Color(204, 255, 0), 1, true), new LineBorder(new Color(255, 0, 255), 3, true)));
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setFont(new Font("Bookman Old Style", Font.PLAIN, 21));
		btnNewButton.setBounds(132, 118, 167, 45);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("               ARCHIVO INVALIDO");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setForeground(Color.YELLOW);
		lblNewLabel_2.setBounds(58, 58, 296, 45);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(getClass().getResource("/img/linea.png")));
		lblNewLabel_4.setBounds(-12, 214, 309, 19);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(getClass().getResource("/img/linea.png")));
		lblNewLabel_5.setBounds(285, 206, 296, 35);
		contentPane.add(lblNewLabel_5);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Window ventana = FocusManager.getCurrentManager().getActiveWindow();
				ventana.dispose();
				
			}
		});
	}
}
