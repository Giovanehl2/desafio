package br.com.b2w.desafio.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.b2w.desafio.model.Planeta;
import br.com.b2w.desafio.service.PlanetaService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RequestMapping(value = "/planetas")
@RestController
public class PlanetaController {

	@Autowired
	PlanetaService planetaService;

	@GetMapping("/id/{id}")
	@ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso")
	@ApiResponse(responseCode = "204", description = "Registro não encontrado")
	public ResponseEntity<Planeta> buscarPlanetaPorId(@PathVariable("id") String id) {
		if (!planetaService.buscarPlanetaPorId(id).isEmpty())
			return new ResponseEntity<>(planetaService.buscarPlanetaPorId(id).get(), HttpStatus.OK);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@GetMapping
	@ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso")
	@ApiResponse(responseCode = "204", description = "Registro não encontrado")
	public ResponseEntity<Collection<Planeta>> listarPlanetas() {
		if( !planetaService.listarPlanetas().isEmpty())
			return ResponseEntity.ok(planetaService.listarPlanetas());
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		
	}

	@GetMapping("/nome/{nome}")
	@ApiResponse(responseCode = "200", description = "Registro encontrado com sucesso")
	@ApiResponse(responseCode = "204", description = "Registro não encontrado")
	public ResponseEntity<Planeta> buscarPlanetaPorNome(@PathVariable String nome) {
		if (!planetaService.buscarPlanetaPorNome(nome).isEmpty())
			return ResponseEntity.ok(planetaService.buscarPlanetaPorNome(nome).get());

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@PostMapping(produces = "application/json; charset=utf-8")
	@ApiResponse(responseCode = "200", description = "registro encontrado com sucesso")
	@ApiResponse(responseCode = "204", description = "Registro não encontrado")
	public ResponseEntity<Planeta> salvarPlaneta(@RequestBody Planeta planeta) {
		if (planetaService.buscarPlanetaPorNome(planeta.getNome()).isEmpty()) {

			return ResponseEntity.ok(planetaService.salvarPlaneta(planeta));
		}
		return new ResponseEntity<>(planetaService.salvarPlaneta(planeta), HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@DeleteMapping("/{id}")
	@ApiResponse(responseCode = "200", description = "Registro removido")
	@ApiResponse(responseCode = "500", description = "Erro ao realizar operação")
	public ResponseEntity<Planeta> deletarPlaneta(@PathVariable("id") String id) {

		if (planetaService.deletarPlaneta(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
