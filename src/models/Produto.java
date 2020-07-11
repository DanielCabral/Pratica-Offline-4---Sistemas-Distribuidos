package models;

import java.io.Serializable;
import java.util.UUID;

public class Produto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String nome;
	protected String codigo;
	protected String tipoDeProduto;
	protected Double preco;
	
	public Produto(json.JSONObject j) {
		this.nome = j.getString("nome");
		this.codigo = j.getString("tipoDeProduto");
		this.tipoDeProduto = j.getString("placa");
		this.preco = j.getDouble("preco");
	}
	
	public Produto(String codigo, String nome, String tipoDeProduto, double preco) {
		this.codigo = codigo;
		this.nome = nome;
		this.tipoDeProduto = tipoDeProduto;
		this.preco = preco;
	}
	
	
	public json.JSONObject toJson() {
		json.JSONObject json = new json.JSONObject();
		json.put("codigo", this.codigo);
		json.put("nome", this.nome);
		json.put("tipoDeProduto", this.tipoDeProduto);
		json.put("preco", this.preco);
		return json;
	}
	
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTipoDeProduto() {
		return tipoDeProduto;
	}

	public void setTipoDeProduto(String tipoDeProduto) {
		this.tipoDeProduto = tipoDeProduto;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public static String gerarCodigo() {
		final String uuid = UUID.randomUUID().toString().replace("-", "");
	    return uuid;
	}
}
