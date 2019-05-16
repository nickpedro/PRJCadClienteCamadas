package com.prjcadcliente.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.prjcadcliente.dominio.Cliente;
import com.prjcadcliente.persistencia.CRUDCliente;

public class GerenciarClientes extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtTelefone;
	private JTextField txtIdade;
	private Cliente cliente;
	private CRUDCliente crud;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciarClientes frame = new GerenciarClientes();
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
	public GerenciarClientes() {
		
		//vamos instanciar as classes Cliente e CRUD
		cliente = new Cliente();
		crud = new CRUDCliente();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						
				//Passar os dados do formulário parar o objeto cliente 
				cliente.setNome(txtNome.getText());
				cliente.setEmail(txtEmail.getText());
				cliente.setTelefone(txtTelefone.getText());
				cliente.setIdade(Integer.parseInt(txtIdade.getText()));
				
				String retorno = crud.cadastrar(cliente);
				JOptionPane.showMessageDialog(null, retorno);
				
				txtNome.setText("");
				txtEmail.setText("");
				txtTelefone.setText("");
				txtIdade.setText("");
					
			}
		});
		btnCadastrar.setBounds(121, 227, 101, 23);
		contentPane.add(btnCadastrar);
		
		JButton btnPesquisar = new JButton("PESQUISAR");
		btnPesquisar.setBounds(232, 227, 101, 23);
		contentPane.add(btnPesquisar);
		
		JButton btnAtualizar = new JButton("ATUALIZAR");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				String id = JOptionPane.showInputDialog("Digite o Id do cliente");
				
				//Passar os dados do formulário parar o objeto cliente 
				cliente.setNome(txtNome.getText());
				cliente.setEmail(txtEmail.getText());
				cliente.setTelefone(txtTelefone.getText());
				cliente.setIdade(Integer.parseInt(txtIdade.getText()));
				cliente.setId(Integer.parseInt(id));
				
				String retorno = crud.atualizar(cliente);
				
				JOptionPane.showMessageDialog(null, retorno);
				
				txtNome.setText("");
				txtEmail.setText("");
				txtTelefone.setText("");
				txtIdade.setText("");
				id="0";
				
				
				
				
			}
		});
		btnAtualizar.setBounds(10, 227, 101, 23);
		contentPane.add(btnAtualizar);
		
		JButton btnDeletar = new JButton("DELETAR");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = JOptionPane.showInputDialog("Digite o Id do cliente para apagar");
				
				cliente.setId(Integer.parseInt(id));
				
				JOptionPane.showConfirmDialog(null, crud.deletar(cliente));
			}
		});
		btnDeletar.setBounds(343, 227, 101, 23);
		contentPane.add(btnDeletar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 261, 434, 138);
		contentPane.add(scrollPane);
		
		String[] colunas = {"Id","Nome","Email","Telefone","Idade"};
		
		Object[][] dados = crud.PesquisarTodos().toArray(new Object[][] {});
		
		//Vamos construir o modelo de dados para exibir na tabela
		DefaultTableModel modelo = new DefaultTableModel(dados,colunas);
		
		table = new JTable(modelo);
		scrollPane.setViewportView(table);
		
		JLabel lblNome = new JLabel("Nome :");
		lblNome.setFont(new Font("Swis721 BlkCn BT", Font.PLAIN, 11));
		lblNome.setBounds(10, 11, 48, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(10, 28, 334, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Swis721 BlkCn BT", Font.PLAIN, 11));
		lblEmail.setBounds(10, 59, 48, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(10, 84, 334, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Swis721 BlkCn BT", Font.PLAIN, 11));
		lblTelefone.setBounds(10, 115, 48, 14);
		contentPane.add(lblTelefone);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(10, 137, 168, 20);
		contentPane.add(txtTelefone);
		txtTelefone.setColumns(10);
		
		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setFont(new Font("Swis721 BlkCn BT", Font.PLAIN, 11));
		lblIdade.setBounds(10, 168, 48, 14);
		contentPane.add(lblIdade);
		
		txtIdade = new JTextField();
		txtIdade.setBounds(10, 193, 96, 20);
		contentPane.add(txtIdade);
		txtIdade.setColumns(10);
	}
}
