package models;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface LojaDistribuida extends Remote{

	public boolean adicionarProduto (String nome, String tipoDeProduto, double preco,double peso, String marca, String tamanho) throws RemoteException;
	public boolean apagarProduto (String codigo, String tipoDeProduto) throws RemoteException;
	public ArrayList<Produto> listarProdutos(String tipo) throws RemoteException;
	public ArrayList<Alimento> listarAlimentos() throws RemoteException;
	public ArrayList<Produto> pesquisarProduto (String nome) throws RemoteException;
	public boolean alterarProduto (String codigo, String nome, String tipoDeProduto, double preco, double peso, String marca, String tamanho) throws RemoteException;
	public int exibirQuantidade() throws RemoteException;
	
}
