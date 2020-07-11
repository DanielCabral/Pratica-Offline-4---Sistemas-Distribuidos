package models;

import java.io.Serializable;

import json.JSONObject;

public class Alimento extends Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	private double peso;
	
	public Alimento(JSONObject j) {
		super(j);
		this.peso= j.getDouble("peso");
	}
	
	
	public Alimento(String codigo, String nome, String tipoDeProduto, double preco, double peso) {
		super(codigo, nome, tipoDeProduto, preco);
		this.peso = peso;
	}
	
	public json.JSONObject toJson() {
		json.JSONObject json = new json.JSONObject();
		json.put("codigo", this.codigo);
		json.put("nome", this.nome);
		json.put("tipoDeProduto", this.tipoDeProduto);
		json.put("preco", this.preco);
		json.put("peso", this.peso);
		return json;
	}
	
	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	

}
