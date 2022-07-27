package model.entities;

import java.io.Serializable;
import java.time.LocalDate;

public class Reagente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String codigo;
	private String reagente;
	private String classificacao;
	private String formula;
	private String risco;
	private String unidade;
	private String fabricante;
	private String localizacao;
	private String responsavel;
	private int quantidade;
	private int quantidadeMinima;
	private float quantidadeTotal;
	private LocalDate dataCadastro;
	private LocalDate dataEntrada;
	private LocalDate dataRetirada;
	
	
	public Reagente() {
		
	}
		//cria objeto da tela entrada
	public Reagente(int id, String codigo, String reagente, String fabricante, String unidade, 
			int quantidade, String localizacao, LocalDate dataEntrada, String responsavel) {
		
		this.codigo = codigo;
		this.reagente = reagente;
		this.fabricante = fabricante;
		this.unidade = unidade;
		this.quantidade = quantidade;
		this.localizacao = localizacao;
		this.dataEntrada = dataEntrada;
		this.responsavel = responsavel;
		
	}
	
	
	
	public Reagente(String responsavel, String codigo, String reagente, String classificacao, String formula, 
			String risco, String fabricante, String unidade, int quantidadeMinima, String localizacao, 
			LocalDate dataCadastro) {
		
		this.responsavel = responsavel;
		this.codigo = codigo;
		this.reagente = reagente;
		this.classificacao = classificacao;
		this.formula = formula;
		this.risco = risco;
		this.fabricante = fabricante;
		this.unidade = unidade;
		this.quantidadeMinima = quantidadeMinima;
		this.localizacao = localizacao;
		this.dataCadastro = dataCadastro;
	
	}
	
	public Reagente(String codigo, String reagente, String classificacao, String formula,
			String localizacao, String responsavel, int quantidade, int quantidadeMinima,
			float quantidadeTotal, LocalDate dataEntrada, LocalDate dataSaida) {
		
		this.codigo = codigo;
		this.reagente = reagente;
		this.classificacao = classificacao;
		this.formula = formula;
		this.localizacao = localizacao;
		this.responsavel = responsavel;
		this.quantidade = quantidade;
		this.quantidadeMinima = quantidadeMinima;
		this.quantidadeTotal = quantidadeTotal;
		this.dataEntrada = dataEntrada;
		this.dataRetirada = dataSaida;
		
	}
	
	public Reagente(int id, String responsavel, String codigo, String reagente, String classificacao, String formula,
			String risco, String fabricante, String unidade,  int quantidadeMinima, String localizacao,
			 LocalDate dataCadastro) {
		
		this.id = id;
		this.responsavel = responsavel;
		this.codigo = codigo;
		this.reagente = reagente;
		this.classificacao = classificacao;
		this.formula = formula;
		this.risco = risco;
		this.fabricante = fabricante;
		this.unidade = unidade;
		this.quantidadeMinima = quantidadeMinima;
		this.localizacao = localizacao;
		this.dataCadastro = dataCadastro;
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getReagente() {
		return reagente;
	}

	public void setReagente(String reagente) {
		this.reagente = reagente;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getRisco() {
		return risco;
	}

	public void setRisco(String risco) {
		this.risco = risco;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getQuantidadeMinima() {
		return quantidadeMinima;
	}

	public void setQuantidadeMinima(int quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}

	public float getQuantidadeTotal() {
		return quantidadeTotal;
	}

	public void setQuantidadeTotal(float quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDate getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(LocalDate dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	
}
