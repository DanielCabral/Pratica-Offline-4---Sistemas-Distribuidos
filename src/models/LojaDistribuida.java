package models;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface LojaDistribuida extends Remote{

	public boolean logar(Funcionario func) throws RemoteException;
	public boolean adicionarUsuario(String matricula, String cpf, String nome, String salario) throws RemoteException;
	public boolean adicionarProduto (Alimento produto) throws RemoteException;
	public boolean adicionarProduto (Eletronico produto) throws RemoteException;
	public boolean adicionarProduto (Roupa produto) throws RemoteException;
	public boolean apagarProduto (String codigo, String tipoDeProduto) throws RemoteException;
	public ArrayList<Alimento> listarAlimentos() throws RemoteException;
	public ArrayList<Eletronico> listarEletronicos() throws RemoteException;
	public ArrayList<Roupa> listarRoupas() throws RemoteException;
	public ArrayList<Alimento> pesquisarAlimento (String nome) throws RemoteException;
	public ArrayList<Eletronico> pesquisarEletronico (String nome) throws RemoteException;
	public ArrayList<Roupa> pesquisarRoupa (String nome) throws RemoteException;
	public boolean alterarProduto (Alimento produto) throws RemoteException;
	public boolean alterarProduto (Eletronico produto) throws RemoteException;
	public boolean alterarProduto (Roupa produto) throws RemoteException;
	public int exibirQuantidade() throws RemoteException;
	
}
