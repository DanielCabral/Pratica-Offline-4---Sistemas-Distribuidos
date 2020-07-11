package models;

import json.JSONObject;

public class Eletronico extends Produto {
	private static final long serialVersionUID = -3601146087129783624L;
	private String marca;
	
	public Eletronico(JSONObject j) {
		super(j);
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
