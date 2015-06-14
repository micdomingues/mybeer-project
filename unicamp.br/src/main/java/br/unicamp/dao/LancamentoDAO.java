package br.unicamp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import br.unicamp.factory.ConnectionFactory;
import br.unicamp.model.Lancamento;

public class LancamentoDAO extends ConnectionFactory
{
	private static LancamentoDAO instance;
	
	public static LancamentoDAO getInstance()
	{
		if(instance == null)
			instance = new LancamentoDAO();
		return instance;
	}
	
	public Lancamento adicionar(Lancamento lancamento)
	{
		if(lancamento != null)
		{
			int res;
			Connection conexao = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String comando = null;
			
			conexao = criarConexao();			
			try
			{				
				comando = "INSERT INTO LANCAMENTO (IDFUNCIONARIO, IDCLIENTE, VALOR) VALUES (?, ?, ?)";	
				pstmt = conexao.prepareStatement(comando, Statement.RETURN_GENERATED_KEYS);
				
				pstmt.setInt(1, lancamento.getIdfuncionario());
				pstmt.setInt(2, lancamento.getIdcliente());
				pstmt.setFloat(3, lancamento.getValor());
				
				res = pstmt.executeUpdate();	            
	            rs = pstmt.getGeneratedKeys();
				
				if(res <= 0)
	            {
	                lancamento = null;
	            }
				else
				{
					lancamento.setCodlancamento(((rs.next())?rs.getInt(1):0));
				}
			}
			catch (Exception e) 
			{
				System.out.println("Erro ao adicionar lancamento: " + e);
				e.printStackTrace();
			}
    		finally
		 	{
 				fecharConexao(conexao, pstmt, rs);
	 		}
		}
		else
			lancamento = null;
		
		return lancamento;
	}
	
	public ArrayList<Lancamento> listarTodos(int idfuncionario)
	{
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Lancamento> lancamentos = null;
		
		conexao = criarConexao();
		lancamentos = new ArrayList<Lancamento>();		
		try
		{
			pstmt = conexao.prepareStatement("SELECT LAN.CODLANCAMENTO, LAN.IDFUNCIONARIO, CONCAT(PFUN.NOME, ' ', PFUN.SOBRENOME) AS NOMEFUNCIONARIO,"
					+ " LAN.IDCLIENTE, CONCAT(PCLI.NOME, ' ', PCLI.SOBRENOME) AS NOMECLIENTE, LAN.VALOR, DATE_FORMAT( DATA,'%d/%m/%Y %H:%i') AS DATA "
					+ "FROM LANCAMENTO LAN INNER JOIN ((FUNCIONARIO FUN INNER JOIN PESSOA PFUN ON (FUN.ID = PFUN.ID)) INNER JOIN BAR ON "
					+ "(FUN.CODBAR = BAR.CODBAR)) ON (LAN.IDFUNCIONARIO = FUN.ID) INNER JOIN PESSOA PCLI ON (LAN.IDCLIENTE = PCLI.ID) "
					+ "WHERE LAN.IDFUNCIONARIO = ?");
			pstmt.setInt(1, idfuncionario);
			
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Lancamento lancamento = new Lancamento();
				
				lancamento.setCodlancamento(rs.getInt("CODLANCAMENTO"));
				lancamento.setIdfuncionario(rs.getInt("IDFUNCIONARIO"));
				lancamento.setNomefuncionario(rs.getString("NOMEFUNCIONARIO"));
				lancamento.setIdcliente(rs.getInt("IDCLIENTE"));
				lancamento.setNomecliente(rs.getString("NOMECLIENTE"));
				lancamento.setValor(rs.getFloat("VALOR"));
				lancamento.setData(rs.getString("DATA"));
				
				lancamentos.add(lancamento);
			}
			
		}
		catch (Exception e) 
		{
			System.out.println("Erro ao listar todos os lancamentos: " + e);
			e.printStackTrace();
		}
		finally
		{
			fecharConexao(conexao, pstmt, rs);
		}
		return lancamentos;
	}

}
