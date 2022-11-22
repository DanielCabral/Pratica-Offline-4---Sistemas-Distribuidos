package models;

import controller.Arquivo;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Loja implements LojaDistribuida, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Arquivo arquivo = new Arquivo();
	json.JSONArray jAFunc = new json.JSONArray();
	json.JSONArray jAProd = new json.JSONArray();

	public boolean adicionarUsuario(String matricula, String cpf, String nome, String salario) throws RemoteException{
		ArrayList<Funcionario> listaDeUsuarios = new ArrayList<>();
		String textoCompleto = arquivo.lerArquivo("src/data/Funcionarios.txt");
		
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
		arquivo.gravarArquivo("src/data/Funcionarios.txt", jsArray);
		
		return true;
	}

	public synchronized boolean logar(Funcionario func) throws RemoteException {
		jAFunc = arquivo.lerArquivoFunc();
		boolean logou = false;
		for (int i = 0; i < jAFunc.length(); i++) {
			Funcionario funcionario= new Funcionario(jAFunc.getJSONObject(i));
			
			if(funcionario.getMatricula().equals(func.getMatricula()) && funcionario.getCpf().equals(func.getCpf())) {
				logou = true;
			}
		}
		return logou;
	}
	
	@Override
	public synchronized boolean adicionarProduto(Alimento produto) throws RemoteException {
		ArrayList<Produto> listaDeProdutos = new ArrayList<>();
		if(produto.getPreco() < 0 || produto.getPeso() < 0)
			return false;
		listaDeProdutos.add(produto);

		jAProd = arquivo.lerArquivoProd("src/data/Alimentos.txt");
		for (Produto l : listaDeProdutos) {
			jAProd.put(l.toJson());
		}
		arquivo.gravarArquivo("src/data/Alimentos.txt", jAProd);
		return true;
	}

	@Override
	public synchronized boolean adicionarProduto(Eletronico produto) throws RemoteException {
		ArrayList<Produto> listaDeProdutos = new ArrayList<>();
		if(produto.getPreco() < 0)
			return false;

		listaDeProdutos.add(produto);

		jAProd = arquivo.lerArquivoProd("src/data/Eletronicos.txt");
		for (Produto l : listaDeProdutos) {
			jAProd.put(l.toJson());
		}
		arquivo.gravarArquivo("src/data/Eletronicos.txt", jAProd);
		return true;
	}

	@Override
	public synchronized boolean adicionarProduto(Roupa produto) throws RemoteException {
		ArrayList<Produto> listaDeProdutos = new ArrayList<>();
		if(produto.getPreco() < 0)
			return false;
		listaDeProdutos.add(produto);

		jAProd = arquivo.lerArquivoProd("src/data/Roupas.txt");
		for (Produto l : listaDeProdutos) {
			jAProd.put(l.toJson());
		}
		arquivo.gravarArquivo("src/data/Roupas.txt", jAProd);
		return true;
	}
	
	@Override
	public synchronized boolean apagarProduto(String codigo, String tipoDeProduto) throws RemoteException {
		if(codigo == null)
			return false;
		ArrayList<Produto> listaDeProdutos = new ArrayList<>();
		String textoCompleto = arquivo.lerArquivo("src/data/"+tipoDeProduto+"s.txt");
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
			//Se codigo do produto � igual ao codigo passado, n�o adiciona a lista.
			if(produto.codigo.equals(codigo)) {
				removeu = true;
			}else {
				listaDeProdutos.add(produto);
			}
		}
				
		//S� regravar o arquivo caso o codigo passado exista
		if(removeu) {
		json.JSONArray jsArray = new json.JSONArray();
			for (Produto l : listaDeProdutos) {
				jsArray.put(l.toJson());
			}
			arquivo.gravarArquivo("src/data/"+tipoDeProduto+"s.txt", jsArray);
		}
		return removeu;
	}
		
	@Override
	public synchronized ArrayList<Alimento> listarAlimentos() throws RemoteException {
		List<Alimento> listaDeProdutos = new ArrayList<>();
		String textoCompleto = arquivo.lerArquivo("src/data/Alimentos.txt");
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
		String textoCompleto = arquivo.lerArquivo("src/data/Eletronicos.txt");
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
		String textoCompleto = arquivo.lerArquivo("src/data/Roupas.txt");
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
		if(nome == null || nome.equals(""))
			return null;

		String textoCompleto = arquivo.lerArquivo("src/data/Alimentos.txt");
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		
		for (int i = 0; i < jA.length(); i++) {
			Alimento alimento= null;
			
			alimento = new Alimento(jA.getJSONObject(i));

			if(alimento.getNome().startsWith(nome)) {
				listaDeProdutos.add(alimento);
			}
		}
		Collections.sort(listaDeProdutos);
		return (ArrayList<Alimento>) listaDeProdutos;
	}
	
	@Override
	public synchronized ArrayList<Eletronico> pesquisarEletronico(String nome) throws RemoteException {
		List<Eletronico> listaDeProdutos = new ArrayList<>();
		if(nome == null || nome.equals(""))
			return null;
		String textoCompleto = arquivo.lerArquivo("src/data/Eletronicos.txt");
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		
		for (int i = 0; i < jA.length(); i++) {
			Eletronico eletronico= null;

			eletronico = new Eletronico(jA.getJSONObject(i));

			if(eletronico.getNome().startsWith(nome)) {
				listaDeProdutos.add(eletronico);
			}
		}
		Collections.sort(listaDeProdutos);
		return (ArrayList<Eletronico>) listaDeProdutos;
	}
	
	@Override
	public synchronized ArrayList<Roupa> pesquisarRoupa(String nome) throws RemoteException {
		List<Roupa> listaDeProdutos = new ArrayList<>();
		if(nome == null || nome.equals(""))
			return null;
		String textoCompleto = arquivo.lerArquivo("src/data/Roupas.txt");
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		
		for (int i = 0; i < jA.length(); i++) {
			Roupa roupa= null;

			roupa = new Roupa(jA.getJSONObject(i));

			if(roupa.getNome().startsWith(nome)) {
				listaDeProdutos.add(roupa);
			}
		}
		Collections.sort(listaDeProdutos);
		return (ArrayList<Roupa>) listaDeProdutos;
	}
	
	@Override
	public synchronized boolean alterarProduto(Alimento produto) throws RemoteException {
		if(produto.getCodigo() == null)
			return false;
		ArrayList<Produto> listaDeProdutos = new ArrayList<>();
		jAProd = arquivo.lerArquivoProd("src/data/Alimentos.txt");
		for (int i = 0; i < jAProd.length(); i++) {
				Produto produtoTemp = new Alimento(jAProd.getJSONObject(i));
			if(produto.getCodigo().equals(produtoTemp.getCodigo())) {
				listaDeProdutos.add(produto);
			}else {
				listaDeProdutos.add(produtoTemp);
			}
		}

		json.JSONArray jsArray = new json.JSONArray();
		for (Produto l : listaDeProdutos) {
			jsArray.put(l.toJson());
		}

		arquivo.gravarArquivo("src/data/Alimentos.txt", jsArray);
		return true;
	}

	@Override
	public synchronized boolean alterarProduto(Eletronico produto) throws RemoteException {
		if(produto.getCodigo() == null)
			return false;
		ArrayList<Produto> listaDeProdutos = new ArrayList<>();
		jAProd = arquivo.lerArquivoProd("src/data/Eletronicos.txt");
		for (int i = 0; i < jAProd.length(); i++) {
			Produto produtoTemp = new Eletronico(jAProd.getJSONObject(i));
			if(produto.getCodigo().equals(produtoTemp.getCodigo())) {
				listaDeProdutos.add(produto);
			}else {
				listaDeProdutos.add(produtoTemp);
			}
		}

		json.JSONArray jsArray = new json.JSONArray();
		for (Produto l : listaDeProdutos) {
			jsArray.put(l.toJson());
		}

		arquivo.gravarArquivo("src/data/Eletronicos.txt", jsArray);
		return true;
	}

	@Override
	public synchronized boolean alterarProduto(Roupa produto) throws RemoteException {
		if(produto.getCodigo() == null)
			return false;
		ArrayList<Produto> listaDeProdutos = new ArrayList<>();
		jAProd = arquivo.lerArquivoProd("src/data/Roupas.txt");
		for (int i = 0; i < jAProd.length(); i++) {
			Produto produtoTemp = new Roupa(jAProd.getJSONObject(i));
			if(produto.getCodigo().equals(produtoTemp.getCodigo())) {
				listaDeProdutos.add(produto);
			}else {
				listaDeProdutos.add(produtoTemp);
			}
		}

		json.JSONArray jsArray = new json.JSONArray();
		for (Produto l : listaDeProdutos) {
			jsArray.put(l.toJson());
		}

		arquivo.gravarArquivo("src/data/Roupas.txt", jsArray);
		return true;
	}

	
	@Override
	public synchronized int exibirQuantidade() throws RemoteException {
		ArrayList<Produto> listaDeProdutos = new ArrayList<>();
		String textoCompleto = arquivo.lerArquivo("src/data/Alimentos.txt");
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		for (int i = 0; i < jA.length(); i++) {
			Produto produto = new Produto(jA.getJSONObject(i));
			listaDeProdutos.add(produto);
		}
		
		textoCompleto = arquivo.lerArquivo("src/data/Eletronicos.txt");
		jA = new json.JSONArray(textoCompleto);
		for (int i = 0; i < jA.length(); i++) {
			Produto produto = new Produto(jA.getJSONObject(i));
			listaDeProdutos.add(produto);
		}
		
		textoCompleto = arquivo.lerArquivo("src/data/Roupas.txt");
		jA = new json.JSONArray(textoCompleto);
		for (int i = 0; i < jA.length(); i++) {
			Produto produto = new Produto(jA.getJSONObject(i));
			listaDeProdutos.add(produto);
		}
		
		return listaDeProdutos.size();
	}
	
}
