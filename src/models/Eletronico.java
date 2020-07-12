package models;

import java.io.Serializable;

import json.JSONObject;

public class Eletronico extends Produto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String marca;
	
	public Eletronico(JSONObject j) {
		super(j);
		this.marca = j.getString("marca");
	}
	
	public Eletronico(String codigo, String nome, String tipoDeProduto, double preco, String marca) {
		super(codigo, nome, tipoDeProduto, preco);
		this.marca = marca;
	}
	
	public json.JSONObject toJson() {
		json.JSONObject json = new json.JSONObject();
		json.put("codigo", this.codigo);
		json.put("nome", this.nome);
		json.put("tipoDeProduto", this.tipoDeProduto);
		json.put("preco", this.preco);
		json.put("marca", this.marca);
		return json;
	}

	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}

}
