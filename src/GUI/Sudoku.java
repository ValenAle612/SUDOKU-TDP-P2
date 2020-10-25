package GUI;
import Logica.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;

public class Sudoku extends JFrame {

	private JPanel contentPane;
	private Clip audio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sudoku frame = new Sudoku();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Sudoku() {
		
		setIconImage(new ImageIcon(getClass().getResource("/img/9.png")).getImage());
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 466);

		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(204, 255, 0), 3, true));
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("JUGAR");
		btnNewButton.setLocation(261, 254);
		btnNewButton.setBorder(new CompoundBorder(new LineBorder(new Color(204, 255, 0), 2), new LineBorder(new Color(255, 0, 255), 4)));
		btnNewButton.setMinimumSize(new Dimension(30, 25));
		btnNewButton.setMaximumSize(new Dimension(30, 25));
		btnNewButton.setPreferredSize(new Dimension(30, 17));
		btnNewButton.setSize(new Dimension(181, 71));
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setVerticalAlignment(SwingConstants.CENTER);
		btnNewButton.setFont(new Font("Britannic Bold", Font.ITALIC, 38));
		btnNewButton.setForeground(Color.YELLOW);
		btnNewButton.setIcon(new ImageIcon(getClass().getResource("/img/pacman grande.png")));	
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();
				
				String archivo = "/archivos/inicio.txt" ;
				
				Juego j = new Juego(archivo);
				
				if(j.archivoValido()) {
				
					SJuego sj=new SJuego(archivo,j);
					sj.setVisible(true);	
					sonidoIniciar();
					
				}else {
					
					sonidoFail();
					ArchivoInvalido vE = new ArchivoInvalido();
					vE.setVisible(true);
				}
					
			}
		});
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("SUDOKU");
		lblNewLabel_1.setBounds(81, 0, 542, 100);
		lblNewLabel_1.setFont(new Font("Britannic Bold", Font.ITALIC, 72));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.YELLOW);
		contentPane.add(lblNewLabel_1);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(-69, 72, 842, 458);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/img/Arcade - Ghostmuncher Bootleg - General Sprites.png")));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(getClass().getResource("/img/frutas.png")));
		lblNewLabel_2.setBounds(12, 13, 231, 63);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(getClass().getResource("/img/frutas.png")));
		lblNewLabel_3.setBounds(467, -6, 240, 100);
		contentPane.add(lblNewLabel_3);
		
	}
	
	/**
	 * Activa el sonido que suena al ser invalido el archivo que inicializa el juego
	 */
	private void sonidoFail() {
		try {
			audio = AudioSystem.getClip();
			audio.open(AudioSystem.getAudioInputStream(getClass().getResource("/sonidos/pm.wav")));
			audio.start();
			
		}catch(LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.getMessage();
			System.out.println("error audio");
		}
	}
	
	/**
	 * Activa el sonido que suena al iniciar el juego
	 */
	private void sonidoIniciar() {
		try {
			audio = AudioSystem.getClip(); 
			audio.open(AudioSystem.getAudioInputStream(getClass().getResource("/sonidos/sonidoInicio.wav")));
			audio.start();
			
		}catch(LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.getMessage();
			System.out.println("error audio");
		}
		
	}
}
