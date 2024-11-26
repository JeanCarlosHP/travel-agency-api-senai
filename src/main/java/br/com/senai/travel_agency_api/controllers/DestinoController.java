package br.com.senai.travel_agency_api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.travel_agency_api.models.Destino;
import br.com.senai.travel_agency_api.services.DestinoService;

@RestController
@RequestMapping("/api/destinos")
public class DestinoController {

  private final DestinoService destinoService;

  public DestinoController(DestinoService destinoService) {
    this.destinoService = destinoService;
  }

  @PostMapping
  public ResponseEntity<Destino> cadastrarDestino(@RequestBody Destino destino) {
    return ResponseEntity.ok(destinoService.cadastrarDestino(destino));
  }

  @GetMapping
  public ResponseEntity<List<Destino>> listarDestinos() {
    return ResponseEntity.ok(destinoService.listarDestinos());
  }

  @GetMapping("/pesquisar")
  public ResponseEntity<List<Destino>> pesquisarDestinos(
    @RequestParam(required = false) String nome,
    @RequestParam(required = false) String localizacao
  ) {
    return ResponseEntity.ok(destinoService.pesquisarPorNomeOuLocalizacao(nome, localizacao));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Destino> buscarDestinoPorId(@PathVariable Long id) {
    return destinoService.buscarPorId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PatchMapping("/{id}/avaliar")
  public ResponseEntity<Destino> avaliarDestino(@PathVariable Long id, @RequestParam double nota) {
    return destinoService.avaliarDestino(id, nota)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> excluirDestino(@PathVariable Long id) {
    if (destinoService.excluirDestino(id)) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}
