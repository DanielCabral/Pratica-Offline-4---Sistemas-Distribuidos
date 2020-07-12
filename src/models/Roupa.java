package models;

import java.io.Serializable;

import json.JSONObject;

public class Roupa extends Produto implements Serializable, Comparable<Roupa>{

	private static final long serialVersionUID = 1L;
	private String tamanho;

	public Roupa(JSONObject j) {
		super(j);
		this.tamanho= j.getString("tamanho");
	}
	
	
	public Roupa(String codigo, String nome, String tipoDeProduto, double preco, String tamanho) {
		super(codigo, nome, tipoDeProduto, preco);
		this.tamanho = tamanho;
	}

	
	public json.JSONObject toJson() {
		json.JSONObject json = new json.JSONObject();
		json.put("codigo", this.codigo);
		json.put("nome", this.nome);
		json.put("tipoDeProduto", this.tipoDeProduto);
		json.put("preco", this.preco);
		json.put("tamanho", this.tamanho);
		return json;
	}
	
	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	
	 @Override
	    public int compareTo(Roupa r) {
	        return this.nome.compareTo(r.getNome());
	  }
}
