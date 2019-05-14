package com.prjcadcliente.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.prjcadcliente.dominio.Cliente;

/**
 * <b>CRUDCliente</b><br>
 * Essa classe permite manipular as informações do cliente. Aqui você encontrará
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
	
	//Declaração das instâncias de comunicação com o banco
	//de dados
	private Connection con = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	
	public String cadastrar(Cliente cliente) {
		
		String msg = "";
		
		
		//Criação dos objetos para a conexão com o banco de dados
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
				msg = "Não foi possivel realizar o cadastro!";
			
			
			
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
		
		
		//Criação dos objetos para a conexão com o banco de dados
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
				msg = "Atualização realizada com sucesso!";
			else
				msg = "Não foi possivel realizar a atualização!";
			
			
			
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
		
		
		//Criação dos objetos para a conexão com o banco de dados
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
				msg = "Não foi Deletar!";
			
			
			
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
		return null;
	}
	
	public List<Cliente> PesquisarPorId(int id){
		return null;
	}
	
	public List<Cliente> PesquisarTodos(){
		return null;
	}
}
