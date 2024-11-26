package br.com.senai.travel_agency_api.models;

public class Destino {
  private Long id;
  private String nome;
  private String localizacao;
  private String descricao;
  private Double avaliacaoMedia = 0.0;
  private int totalAvaliacoes = 0;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getLocalizacao() {
    return localizacao;
  }

  public void setLocalizacao(String localizacao) {
    this.localizacao = localizacao;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Double getAvaliacaoMedia() {
    return avaliacaoMedia;
  }

  public void setAvaliacaoMedia(Double avaliacaoMedia) {
    this.avaliacaoMedia = avaliacaoMedia;
  }

  public int getTotalAvaliacoes() {
    return totalAvaliacoes;
  }

  public void setTotalAvaliacoes(int totalAvaliacoes) {
    this.totalAvaliacoes = totalAvaliacoes;
  }
}
