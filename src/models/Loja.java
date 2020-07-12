package models;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import controller.Arquivo;

public class Loja implements LojaDistribuida, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public boolean adicionarUsuario(String matricula, String cpf, String nome, String salario) throws RemoteException{
		ArrayList<Funcionario> listaDeUsuarios = new ArrayList<>();
		String textoCompleto = Arquivo.lerArquivo("src/data/Funcionarios.txt");
		
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		for (int i = 0; i < jA.length(); i++) {
			Funcionario produto= new Funcionario(jA.getJSONObject(i));
			listaDeUsuarios.add(produto);
		}
		
		Funcionario funcionario = new Funcionario(matricula, cpf, nome, salario);
		listaDeUsuarios.add(funcionario);
		
		json.JSONArray jsArray = new json.JSONArray();
		for (Funcionario l : listaDeUsuarios) {
			jsArray.put(l.toJson());
		}
		Arquivo.gravarArquivo("src/data/Funcionarios.txt", jsArray);
		
		return true;
	}
	
	public synchronized boolean logar(String matricula, String cpf) throws RemoteException {
		String textoCompleto = Arquivo.lerArquivo("src/data/Funcionarios.txt");
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		boolean logou = false;
		for (int i = 0; i < jA.length(); i++) {
			Funcionario funcionario= new Funcionario(jA.getJSONObject(i));
			
			if(funcionario.getMatricula().equals(matricula) && funcionario.getCpf().equals(cpf)) {
				logou = true;
			}
		}
		return logou;
	}
	
	@Override
	public synchronized boolean adicionarProduto(String nome, String tipoDeProduto, double preco, double peso, String marca, String tamanho) throws RemoteException {
		ArrayList<Produto> listaDeProdutos = new ArrayList<>();
		String textoCompleto = Arquivo.lerArquivo("src/data/"+tipoDeProduto+"s.txt");
		
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		for (int i = 0; i < jA.length(); i++) {
			Produto produto= null;
			if(tipoDeProduto.equals("Alimento")) {
				produto = new Alimento(jA.getJSONObject(i));
			}else if(tipoDeProduto.equals("Eletronico")) {
				produto = new Eletronico(jA.getJSONObject(i));
			}else {
				produto = new Roupa(jA.getJSONObject(i));
			}
			listaDeProdutos.add(produto);
			
		}
		
		Produto rc;
		if(tipoDeProduto.equals("Alimento")) {
			rc = new Alimento(Produto.gerarCodigo(), nome, tipoDeProduto, preco, peso);
		}else if(tipoDeProduto.equals("Eletronico")) {
			rc = new Eletronico(Produto.gerarCodigo(), nome, tipoDeProduto, preco, marca);
		}else {
			rc = new Roupa(Produto.gerarCodigo(), nome, tipoDeProduto, preco, tamanho);
		}
		listaDeProdutos.add(rc);
		
		json.JSONArray jsArray = new json.JSONArray();
		for (Produto l : listaDeProdutos) {
			jsArray.put(l.toJson());
		}
		Arquivo.gravarArquivo("src/data/"+tipoDeProduto+"s.txt", jsArray);
		return true;
	}
	
	@Override
	public synchronized boolean apagarProduto(String codigo, String tipoDeProduto) throws RemoteException {
		ArrayList<Produto> listaDeProdutos = new ArrayList<>();
		String textoCompleto = Arquivo.lerArquivo("src/data/"+tipoDeProduto+"s.txt");
		System.out.println(textoCompleto);
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		boolean removeu = false;
		for (int i = 0; i < jA.length(); i++) {
			Produto produto= null;
			if(tipoDeProduto.equals("Alimento")) {
				produto = new Alimento(jA.getJSONObject(i));
			}else if(tipoDeProduto.equals("Eletronico")) {
				produto = new Eletronico(jA.getJSONObject(i));
			}else {
				produto = new Roupa(jA.getJSONObject(i));
			}
			//Se codigo do produto é igual ao codigo passado, não adiciona a lista.
			if(produto.codigo.equals(codigo)) {
				removeu = true;
			}else {
				listaDeProdutos.add(produto);
			}
		}
				
		//Só regravar o arquivo caso o codigo passado exista
		if(removeu) {
		json.JSONArray jsArray = new json.JSONArray();
			for (Produto l : listaDeProdutos) {
				jsArray.put(l.toJson());
			}
			Arquivo.gravarArquivo("src/data/"+tipoDeProduto+"s.txt", jsArray);
		}
		return true;
	}
		
	@Override
	public synchronized ArrayList<Alimento> listarAlimentos() throws RemoteException {
		List<Alimento> listaDeProdutos = new ArrayList<>();
		String textoCompleto = Arquivo.lerArquivo("src/data/Alimentos.txt");
		System.out.println(textoCompleto);
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		
		for (int i = 0; i < jA.length(); i++) {
			Alimento produto= null;
			
			produto = new Alimento(jA.getJSONObject(i));

			listaDeProdutos.add(produto);
		}

		Collections.sort(listaDeProdutos);
		return (ArrayList<Alimento>) listaDeProdutos;
	}
	@Override
	public synchronized ArrayList<Eletronico> listarEletronicos() throws RemoteException {
		List<Eletronico> listaDeProdutos = new ArrayList<>();
		String textoCompleto = Arquivo.lerArquivo("src/data/Eletronicos.txt");
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		
		for (int i = 0; i < jA.length(); i++) {
			Eletronico produto= null;
			
			produto = new Eletronico(jA.getJSONObject(i));

			listaDeProdutos.add(produto);
		}
		Collections.sort(listaDeProdutos);
		return (ArrayList<Eletronico>) listaDeProdutos;
	}
	
	@Override
	public synchronized ArrayList<Roupa> listarRoupas() throws RemoteException {
		List<Roupa> listaDeProdutos = new ArrayList<>();
		String textoCompleto = Arquivo.lerArquivo("src/data/Roupas.txt");
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		
		for (int i = 0; i < jA.length(); i++) {
			Roupa produto= null;
			
			produto = new Roupa(jA.getJSONObject(i));

			listaDeProdutos.add(produto);
		}
		Collections.sort(listaDeProdutos);
		return (ArrayList<Roupa>) listaDeProdutos;
	}
		
	@Override
	public synchronized ArrayList<Alimento> pesquisarAlimento(String nome) throws RemoteException {
		List<Alimento> listaDeProdutos = new ArrayList<>();
		String textoCompleto = Arquivo.lerArquivo("src/data/Alimentos.txt");
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		
		for (int i = 0; i < jA.length(); i++) {
			Alimento alimento= null;
			
			alimento = new Alimento(jA.getJSONObject(i));

			if(alimento.getNome().startsWith(nome)) {
				System.out.println("ok");
				listaDeProdutos.add(alimento);
			}
		}
		Collections.sort(listaDeProdutos);
		return (ArrayList<Alimento>) listaDeProdutos;
	}
	
	@Override
	public synchronized ArrayList<Eletronico> pesquisarEletronico(String nome) throws RemoteException {
		List<Eletronico> listaDeProdutos = new ArrayList<>();
		String textoCompleto = Arquivo.lerArquivo("src/data/Eletronicos.txt");
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		
		for (int i = 0; i < jA.length(); i++) {
			Eletronico alimento= null;
			
			alimento = new Eletronico(jA.getJSONObject(i));

			if(alimento.getNome().startsWith(nome)) {
				System.out.println("ok");
				listaDeProdutos.add(alimento);
			}
		}
		Collections.sort(listaDeProdutos);
		return (ArrayList<Eletronico>) listaDeProdutos;
	}
	
	@Override
	public synchronized ArrayList<Roupa> pesquisarRoupa(String nome) throws RemoteException {
		List<Roupa> listaDeProdutos = new ArrayList<>();
		String textoCompleto = Arquivo.lerArquivo("src/data/Roupas.txt");
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		
		for (int i = 0; i < jA.length(); i++) {
			Roupa alimento= null;
			
			alimento = new Roupa(jA.getJSONObject(i));

			if(alimento.getNome().startsWith(nome)) {
				System.out.println("ok");
				listaDeProdutos.add(alimento);
			}
		}
		Collections.sort(listaDeProdutos);
		return (ArrayList<Roupa>) listaDeProdutos;
	}
	
	@Override
	public synchronized boolean alterarProduto(String codigo, String nome, String tipoDeProduto, double preco, double peso, String marca, String tamanho) throws RemoteException {
		ArrayList<Produto> listaDeProdutos = new ArrayList<>();
		String textoCompleto = Arquivo.lerArquivo("src/data/"+tipoDeProduto+"s.txt");
		System.out.println(textoCompleto);
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		for (int i = 0; i < jA.length(); i++) {
			Produto produto= null;
			if(tipoDeProduto.equals("Alimento")) {
				produto = new Alimento(jA.getJSONObject(i));
			}else if(tipoDeProduto.equals("Eletronico")) {
				produto = new Eletronico(jA.getJSONObject(i));
			}else {
				produto = new Roupa(jA.getJSONObject(i));
			}
			if(produto.codigo.equals(codigo)) {
				Produto rc;
				if(tipoDeProduto.equals("Alimento")) {
					rc = new Alimento(codigo, nome, tipoDeProduto, preco, peso);
				}else if(tipoDeProduto.equals("Eletronico")) {
					rc = new Eletronico(codigo, nome, tipoDeProduto, preco, marca);
				}else {
					rc = new Roupa(codigo, nome, tipoDeProduto, preco, tamanho);
				}
				listaDeProdutos.add(rc);
			}else {
				listaDeProdutos.add(produto);
			}
		}
				
		
		
		json.JSONArray jsArray = new json.JSONArray();
		for (Produto l : listaDeProdutos) {
			jsArray.put(l.toJson());
		}
		Arquivo.gravarArquivo("src/data/"+tipoDeProduto+"s.txt", jsArray);
		return true;
	}
	
	@Override
	public synchronized int exibirQuantidade() throws RemoteException {
		ArrayList<Produto> listaDeProdutos = new ArrayList<>();
		String textoCompleto = Arquivo.lerArquivo("src/data/Alimentos.txt");
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		for (int i = 0; i < jA.length(); i++) {
			Produto produto = new Produto(jA.getJSONObject(i));
			listaDeProdutos.add(produto);
		}
		
		textoCompleto = Arquivo.lerArquivo("src/data/Eletronicos.txt");
		jA = new json.JSONArray(textoCompleto);
		for (int i = 0; i < jA.length(); i++) {
			Produto produto = new Produto(jA.getJSONObject(i));
			listaDeProdutos.add(produto);
		}
		
		textoCompleto = Arquivo.lerArquivo("src/data/Roupas.txt");
		jA = new json.JSONArray(textoCompleto);
		for (int i = 0; i < jA.length(); i++) {
			Produto produto = new Produto(jA.getJSONObject(i));
			listaDeProdutos.add(produto);
		}
		
		return listaDeProdutos.size();
	}
	
}
