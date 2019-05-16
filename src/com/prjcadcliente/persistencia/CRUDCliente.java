package com.prjcadcliente.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.prjcadcliente.dominio.Cliente;

/**
 * <b>CRUDCliente</b><br>
 * Essa classe permite manipular as informa��es do cliente. Aqui voc� encontrar�
 * os seguinte comandos:
 * <ul>
 * 	<li>Cadastro</li>
 * 	<li>Pesquisar por nome ou por id</li>
 * 	<li>Atualizar</li>
 * 	<li>Deletar</li>
 * </ul>
 * @author pedro.hsventura
 *
 */
public class CRUDCliente {
	
	//Declara��o das inst�ncias de comunica��o com o banco
	//de dados
	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	
	public String cadastrar(Cliente cliente) {
		
		String msg = "";
		
		
		//Cria��o dos objetos para a conex�o com o banco de dados
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/clientedb","root","");
			
			String consulta = "INSERT INTO tbcliente(nome,email,telefone,idade)values(?,?,?,?)";
					
			pst = con.prepareStatement(consulta);
			
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getEmail());
			pst.setString(3, cliente.getTelefone());
			pst.setInt(4, cliente.getIdade());
			
			int r = pst.executeUpdate();
			
			;
			if (r > 0)
				msg = "Cadastro realizado com sucesso!";
			else
				msg = "N�o foi possivel realizar o cadastro!";
			
			
			
		}
		catch(SQLException ex) {
			msg = "Erro ao tentar cadastrar: "+ex.getMessage();
		}
		catch(Exception e) {
			msg = "Erro inesperado: "+e.getMessage();
			
		}
		finally {
			try{con.close();}catch(Exception e) {e.printStackTrace();}
		}
		
		return msg;
		
		}
	public String atualizar(Cliente cliente) {
String msg = "";
		
		
		//Cria��o dos objetos para a conex�o com o banco de dados
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/clientedb","root","");
			
			String consulta = "UPDATE tbcliente SET nome=?,email=?,telefone=?,idade=? WHERE id=?";
					
			pst = con.prepareStatement(consulta);
			
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getEmail());
			pst.setString(3, cliente.getTelefone());
			pst.setInt(4, cliente.getIdade());
			pst.setInt(5, cliente.getId());
			
			int r = pst.executeUpdate();
			
			;
			if (r > 0)
				msg = "Atualiza��o realizada com sucesso!";
			else
				msg = "N�o foi possivel realizar a atualiza��o!";
			
			
			
		}
		catch(SQLException ex) {
			msg = "Erro ao tentar atualizar: "+ex.getMessage();
		}
		catch(Exception e) {
			msg = "Erro inesperado: "+e.getMessage();
			
		}
		finally {
			try{con.close();}catch(Exception e) {e.printStackTrace();}
		}
		
		return msg;
	}
	public String deletar(Cliente cliente) {

		String msg = "";
		
		
		//Cria��o dos objetos para a conex�o com o banco de dados
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/clientedb","root","");
			
			String consulta = "DELETE FROM tbcliente WHERE id=?";
					
			pst = con.prepareStatement(consulta);
			
			pst.setInt(1, cliente.getId());
			
			int r = pst.executeUpdate();
			
			;
			if (r > 0)
				msg = "Deletado com sucesso!";
			else
				msg = "N�o foi Deletar!";
			
			
			
		}
		catch(SQLException ex) {
			msg = "Erro ao deletar: "+ex.getMessage();
		}
		catch(Exception e) {
			msg = "Erro inesperado: "+e.getMessage();
			
		}
		finally {
			try{con.close();}catch(Exception e) {e.printStackTrace();}
		}
		
		return msg;
	}
	
	public List<Cliente> PesquisarPorNome(String nome){
		
		List<Cliente> lista = new ArrayList<Cliente>();
		
		try {
			//carregar o drive de comunica��o com o banco de dados 
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			
			//Chamar o gerenciador de driver 
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/clientedb","root","");
			
			//vamos criar a consulta para selecionar os clientes por nome
			String consulta = "Select * from tbcliente where nome=?";
			
			pst = con.prepareStatement(consulta);
			
			pst.setString(1,nome);
			
			//Vamos execultar a consulta e guardar o resultado na vari�vel rs
			rs = pst.executeQuery();
			
			/*
			 * Vamos pegar um cliente por vez que est� no rs e adiciona-lo 
			 * a lista de clientes para, ent�o retorna-la
			 */
			while(rs.next()) {
				lista.add(new Cliente(
						rs.getInt(0),rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
			}//Fim do while
			
		}//Fim do try
		
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {con.close();} catch(Exception e) {e.printStackTrace();}
		}
		
		return lista;
	}
	
	public List<Cliente> PesquisarPorId(int id){
		return null;
	}
	
	public List<Cliente> PesquisarTodos(){
List<Cliente> lista = new ArrayList<Cliente>();
		
		try {
			//carregar o drive de comunica��o com o banco de dados 
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			
			//Chamar o gerenciador de driver 
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/clientedb","root","");
			
			//vamos criar a consulta para selecionar os clientes por nome
			String consulta = "Select * from tbcliente ";
			
			pst = con.prepareStatement(consulta);
			
			//Vamos execultar a consulta e guardar o resultado na vari�vel rs
			rs = pst.executeQuery();
			
			/*
			 * Vamos pegar um cliente por vez que est� no rs e adiciona-lo 
			 * a lista de clientes para, ent�o retorna-la
			 */
			while(rs.next()) {
				lista.add(new Cliente(
						rs.getInt(0),rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
			}//Fim do while
			
		}//Fim do try
		
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {con.close();} catch(Exception e) {e.printStackTrace();}
		}
		
		return lista;
	}
}
