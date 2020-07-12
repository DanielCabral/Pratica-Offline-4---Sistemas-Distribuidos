package models;

import java.io.Serializable;

import json.JSONObject;

public class Funcionario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String matricula;
	private String cpf;
	private String nome;
	private String salario;
	public Funcionario(String matricula, String cpf, String nome, String salario) {
		super();
		this.matricula = matricula;
		this.cpf = cpf;
		this.nome = nome;
		this.salario = salario;
	}
	
	public Funcionario(JSONObject j) {
		this.nome = j.getString("nome");
		this.matricula = j.getString("matricula");
		this.cpf = j.getString("cpf");
		this.salario = j.getString("salario");
	}
	
	public json.JSONObject toJson() {
		json.JSONObject json = new json.JSONObject();
		json.put("nome", this.nome);
		json.put("matricula", this.matricula);
		json.put("cpf", this.cpf);
		json.put("salario", this.salario);
		return json;
	}
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSalario() {
		return salario;
	}
	
	public void setSalario(String salario) {
		salario = salario;
	}
	
}
