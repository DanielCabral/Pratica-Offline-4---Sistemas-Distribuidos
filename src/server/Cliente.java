package server;


import models.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
	private static String nomeServidor = "127.0.0.1";
	private static int porta = 12344;
	private static final String NOMEOBJDIST = "MinhaLoja";
	Registry registro;
	LojaDistribuida stub;
	public Cliente() {
		try {
			// Obtendo refer^encia do servi¸co de registro
			registro = LocateRegistry.getRegistry(nomeServidor, porta);
					
			// Procurando pelo objeto distribu´ıdo registrado previamente com o NOMEOBJDIST
			stub = (LojaDistribuida) registro.lookup(NOMEOBJDIST);
		} catch (RemoteException | NotBoundException ex) {
			Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public synchronized boolean logar(Funcionario func) throws RemoteException{
		return stub.logar(func);
	}
	
	public boolean adicionarUsuario(String matricula, String cpf, String nome, String salario) throws RemoteException{
		return stub.adicionarUsuario(matricula, cpf, nome, salario);
	}
	
	public boolean adicionarProduto (Alimento produto) throws RemoteException{
		return stub.adicionarProduto(produto);
	}

	public boolean adicionarProduto (Eletronico produto) throws RemoteException{
		return stub.adicionarProduto(produto);
	}

	public boolean adicionarProduto (Roupa produto) throws RemoteException{
		return stub.adicionarProduto(produto);
	}
	
	public boolean apagarProduto (String codigo, String tipoDeProduto) throws RemoteException{
		return stub.apagarProduto(codigo, tipoDeProduto);
	}
	
	public boolean alterarProduto (Alimento produto) throws RemoteException{
		return stub.alterarProduto(produto);
	}

	public boolean alterarProduto (Eletronico produto) throws RemoteException{
		return stub.alterarProduto(produto);
	}

	public boolean alterarProduto (Roupa produto) throws RemoteException{
		return stub.alterarProduto(produto);
	}
	
	public ArrayList<Alimento> pesquisarAlimento(String nome) throws RemoteException {
		return stub.pesquisarAlimento(nome);
	}

	public ArrayList<Alimento> listarAlimento() throws RemoteException {
		return stub.listarAlimentos();
	}
	
	public ArrayList<Eletronico> pesquisarEletronico(String nome) throws RemoteException {
		return stub.pesquisarEletronico(nome);
	}

	public ArrayList<Eletronico> listarEletronico() throws RemoteException {
		return stub.listarEletronicos();
	}
	
	public ArrayList<Roupa> pesquisarRoupa(String nome) throws RemoteException {
		return stub.pesquisarRoupa(nome);
	}

	public ArrayList<Roupa> listarRoupa() throws RemoteException {
		return stub.listarRoupas();
	}
	
	
	public static void main(String args[]) {
		try {
			System.out.println("Conectando no servidor "+ nomeServidor);
			
			// Obtendo refer^encia do servi¸co de registro
			Registry registro = LocateRegistry.getRegistry(nomeServidor, porta);
			
			// Procurando pelo objeto distribu´ıdo registrado previamente com o NOMEOBJDIST
			LojaDistribuida stub = (LojaDistribuida) registro.lookup(NOMEOBJDIST);
			
			// Invocando m´etodos do objeto distribu´ıdo			
					
			//System.out.println("Cadastrou: " + stub.adicionarProduto("Arroz", "Alimento", 20.5, 5, "", ""));
			System.out.println(stub.exibirQuantidade());
			//System.out.println(stub.adicionarUsuario("232", "232", "Jose", "232"));
			
			System.out.println("Fim da execução do cliente!"); 
		} catch (RemoteException | NotBoundException ex) {
			Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
