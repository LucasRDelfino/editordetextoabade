
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.text.AttributedCharacterIterator.Attribute;

public class Editor extends JFrame {
	private JLabel label1, label2,lbcor;
	private JButton btGravar, btAbrir, btLimpar,btnegrito,btitalico,btred;
	private JTextField tfTexto;
	private TextArea taTexto;
	private FileDialog fdAbrir, fdSalvar;
	private String nome_do_arquivo;
	private ImageIcon img1,img2,img3;
	private JComboBox cbFontes,cbtamanhos;

	public static void main(String args[]) {
		JFrame frame = new Editor();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0,600,600);
		frame.setVisible(true);
	}

	public Editor() {
		inicializarComponentes();
		definirEventos();
	}

	public void inicializarComponentes() {
		String[] fontes = {"Arial","Serif","AmericanTypewriter"};
		cbFontes = new JComboBox(fontes);
		cbFontes.setBounds(155, 90, 150, 22);
		String[] tamanho = {"10","20","30"};
		cbtamanhos = new JComboBox(tamanho);
		cbtamanhos.setBounds(320, 90, 150, 22);
		
		setTitle("Salvando Arquivos");
		setLayout(null);
		setBounds(250, 50, 500, 300);
		setResizable(false);
		img3 = new ImageIcon("C:\\Users\\Naja Info\\Desktop\\vermelho.png");
		btred= new JButton( img3);
		btred.setBounds(110,90,27,26);
		img2 = new ImageIcon("C:\\Users\\Naja Info\\Desktop\\italico.jpg");
		btitalico = new JButton( img2);
		btitalico.setBounds(80,90,27,26);
		img1 = new ImageIcon("C:\\Users\\Naja Info\\Desktop\\negrito.jpg");
		btnegrito = new JButton( img1);
		btnegrito.setBounds(47,90,27,26);
		label1 = new JLabel("Editor de Texto ");
		label1.setBounds(175, 15, 300, 50);
		label1.setFont(new Font("AmericanTypewriter",1, 30));
		label2 = new JLabel("Status: ");
		label2.setBounds(5, 500, 200, 20);
		btGravar = new JButton("Gravar");
		btGravar.setBounds(230, 450, 100, 25);
		btAbrir = new JButton("Abrir");
		btAbrir.setBounds(110, 450, 100, 25);
		btLimpar = new JButton("Limpar");
		btLimpar.setBounds(350, 450, 100, 25);
		tfTexto = new JTextField();
		tfTexto.setBounds(50, 501, 430, 20);
		tfTexto.setEditable(false);
		taTexto = new TextArea();
		taTexto.setBounds(45, 125, 480, 300);
		fdAbrir = new FileDialog(this, "Abrir arquivo", FileDialog.LOAD);
		fdSalvar = new FileDialog(this, "Salvar arquivo", FileDialog.SAVE);
		add(label1);
		add(label2);
		add(tfTexto);
		add(taTexto);
		add(btGravar);
		add(btAbrir);
		add(btLimpar);
		add(btnegrito);
		add(btitalico);
		add(btred);
		add(cbFontes);
		add(cbtamanhos);
	}

	public void definirEventos() {
		btLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taTexto.setText("");
				tfTexto.setText("");
			}
		});
		btGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fdSalvar.setVisible(true);
					if (fdSalvar.getFile() == null) {
						return;
					}
					nome_do_arquivo = fdSalvar.getDirectory()
							+ fdSalvar.getFile();
					FileWriter out = new FileWriter(nome_do_arquivo);
					out.write(taTexto.getText());
					out.close();
					tfTexto.setText("Arquivo gravado com sucesso");
				} catch (IOException erro) {
					tfTexto.setText("Erro ao gravar no arquivo"
							+ erro.toString());
				}

			}
		});
		btnegrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				taTexto.setFont(new Font( "",Font.BOLD ,15));
		     	
			
			}
			
		});
		btitalico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				taTexto.setFont(new Font("",Font.ITALIC, 15));
		     	
			
			}
			
		});
		btred.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				taTexto.setForeground(Color.red);
		     	
			
			}
			
		});
		cbFontes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                if (cbFontes.getSelectedItem()=="Arial") {
				taTexto.setFont(new Font("Arial",1, 12));
                } if (cbFontes.getSelectedItem()=="Serif") {
				taTexto.setFont(new Font("Serif",1, 12));
                } if (cbFontes.getSelectedItem()=="AmericanTypewriter") {
				taTexto.setFont(new Font("AmericanTypewriter",1, 12));
                }
			
			}
			
		});
		cbtamanhos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                if (cbtamanhos.getSelectedItem()=="10") {
				taTexto.setFont(new Font("",1, 10));
                } if (cbtamanhos.getSelectedItem()=="20") {
				taTexto.setFont(new Font("",1, 20));
                } if (cbtamanhos.getSelectedItem()=="30") {
				taTexto.setFont(new Font("",1, 30));
                }
			
			}
			
		});
		btAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fdAbrir.setVisible(true);
					if (fdAbrir.getFile() == null) {
						return;
					}
					nome_do_arquivo = fdAbrir.getDirectory()
							+ fdAbrir.getFile();
					FileReader in = new FileReader(nome_do_arquivo);
					String s = "";
					int i = in.read();
					while (i != -1) {
						s = s + (char) i;
						i = in.read();
					}
					taTexto.setText(s);
					in.close();
					tfTexto.setText("Arquivo aberto com sucesso");
				} catch (IOException erro) {
					tfTexto.setText("Erro ao abrir no arquivo"
							+ erro.toString());
				}

			}
		});
	}

}