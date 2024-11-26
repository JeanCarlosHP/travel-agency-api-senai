package br.com.senai.travel_agency_api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.senai.travel_agency_api.models.Destino;

@Service
public class DestinoService {
  private final List<Destino> destinos = new ArrayList<>();
  private Long idCounter = 1L;

  public Destino cadastrarDestino(Destino destino) {
    destino.setId(idCounter++);
    destinos.add(destino);
    return destino;
  }

  public List<Destino> listarDestinos() {
    return destinos;
  }

  public List<Destino> pesquisarPorNomeOuLocalizacao(String nome, String localizacao) {
    return destinos.stream()
        .filter(d -> nome == null || d.getNome().contains(nome))
        .filter(d -> localizacao == null || d.getLocalizacao().contains(localizacao))
        .toList();
  }

  public Optional<Destino> buscarPorId(Long id) {
    return destinos.stream().filter(d -> d.getId().equals(id)).findFirst();
  }

  public boolean excluirDestino(Long id) {
    return destinos.removeIf(d -> d.getId().equals(id));
  }

  public Optional<Destino> avaliarDestino(Long id, double nota) {
    return buscarPorId(id).map(destino -> {
      double novaMedia = (destino.getAvaliacaoMedia() * destino.getTotalAvaliacoes() + nota) /
          (destino.getTotalAvaliacoes() + 1);
      destino.setAvaliacaoMedia(novaMedia);
      destino.setTotalAvaliacoes(destino.getTotalAvaliacoes() + 1);
      return destino;
    });
  }
}
