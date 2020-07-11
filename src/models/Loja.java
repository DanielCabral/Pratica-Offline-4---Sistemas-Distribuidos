package models;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import controller.Arquivo;

public class Loja implements LojaDistribuida, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public boolean adicionarProduto(String nome, String tipoDeProduto, double preco, double peso, String marca, String tamanho) throws RemoteException {
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
	public boolean apagarProduto(String codigo, String tipoDeProduto) throws RemoteException {
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
	public ArrayList<Produto> listarProdutos(String tipoDeProduto) throws RemoteException {
		List<Produto> listaDeProdutos = new ArrayList<>();
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
			listaDeProdutos.add(produto);
		}
		System.out.println(listaDeProdutos.size());
		return (ArrayList<Produto>) listaDeProdutos;
	}
	
	@Override
	public ArrayList<Alimento> listarAlimentos() throws RemoteException {
		List<Alimento> listaDeProdutos = new ArrayList<>();
		String textoCompleto = Arquivo.lerArquivo("src/data/Alimentos.txt");
		System.out.println(textoCompleto);
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		
		for (int i = 0; i < jA.length(); i++) {
			Alimento produto= null;
			
			produto = new Alimento(jA.getJSONObject(i));

			listaDeProdutos.add(produto);
		}
		System.out.println(listaDeProdutos.size());
		return (ArrayList<Alimento>) listaDeProdutos;
	}
	
	@Override
	public ArrayList<Produto> pesquisarProduto(String nome) throws RemoteException {
		ArrayList<Produto> listaDeProdutos = new ArrayList<>();
		String textoCompleto = Arquivo.lerArquivo("src/data/produtos.txt");
		System.out.println(textoCompleto);
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		
		for (int i = 0; i < jA.length(); i++) {
			Produto produto = new Produto(jA.getJSONObject(i));
			if(produto.nome.startsWith(nome)) {
				listaDeProdutos.add(produto);
			}
		}
		return listaDeProdutos;
	}
	
	@Override
	public boolean alterarProduto(String codigo, String nome, String tipoDeProduto, double preco, double peso, String marca, String tamanho) throws RemoteException {
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
	public int exibirQuantidade() throws RemoteException {
		ArrayList<Produto> listaDeProdutos = new ArrayList<>();
		String textoCompleto = Arquivo.lerArquivo("src/data/produtos.txt");
		System.out.println(textoCompleto);
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		for (int i = 0; i < jA.length(); i++) {
			Produto produto = new Produto(jA.getJSONObject(i));
			listaDeProdutos.add(produto);
		}
		return listaDeProdutos.size();
	}
	
}
