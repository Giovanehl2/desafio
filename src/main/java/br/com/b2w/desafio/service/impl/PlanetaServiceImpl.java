package br.com.b2w.desafio.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.b2w.desafio.model.Planeta;
import br.com.b2w.desafio.repository.PlanetaRepository;
import br.com.b2w.desafio.response.FilmeResponse;
import br.com.b2w.desafio.response.PlanetaResponse;
import br.com.b2w.desafio.response.Result;
import br.com.b2w.desafio.service.PlanetaService;

@Service
public class PlanetaServiceImpl implements PlanetaService {

	
	@Autowired
	PlanetaRepository repository;


	@Override
	public Collection<Planeta> listarPlanetas() {	
		return repository.findAll();
	}

	@Override
	public Optional<Planeta> buscarPlanetaPorId(String id) {
		return repository.findById(id);
	}

	@Override
	public Optional<Planeta> buscarPlanetaPorNome(String nome) {
		return repository.findByNome(nome);
	}

	@Override
	public Planeta editarPlaneta(String id, Planeta planeta) {
		try {
			Planeta editPlaneta = buscarPlanetaPorId(id).get();

			editPlaneta.setClima(planeta.getClima());
			editPlaneta.setNome(planeta.getNome());
			editPlaneta.setTerreno(planeta.getTerreno());

			return repository.save(editPlaneta);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Planeta salvarPlaneta(Planeta planeta) {
		try {

				StringBuilder str = new StringBuilder();
				str.append("https://swapi.dev/api/planets/?search=");
				str.append(planeta.getNome());
				
				RestTemplate restTemplate = new RestTemplate();
				
				ResponseEntity<PlanetaResponse> response = restTemplate.getForEntity(str.toString(), PlanetaResponse.class);
				
				List<String> titulosDosFilmes  = processarDadosApi(response.getBody().getResults());
				
				planeta.setFilmes(titulosDosFilmes);
				planeta.setNumeroDeFilmes(titulosDosFilmes.size());
				
				return repository.insert(planeta);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deletarPlaneta(String id) {
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
			
		return false;
	}
	
	private List<String> processarDadosApi(List<Result> results){
		List<String> filmes = new ArrayList<String>();
		StringBuilder str = new StringBuilder();
		RestTemplate restTemplateFilme = new RestTemplate();

		str.append("http://swapi.dev/api/films/");
		for (Result obj : results) {

			for (String url : obj.getFilms()) {
				url = url.replace("http","https");
				FilmeResponse responseFilme = restTemplateFilme.getForObject(url, FilmeResponse.class );	
				responseFilme.getTitle();
				filmes.add(responseFilme.getTitle());
//				  final String uri = "https://swapi.dev/api/films/1/";
//				    RestTemplate restTemplate = new RestTemplate();
//				     
//				    HttpHeaders headers = new HttpHeaders();
//				    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//
//				    HttpEntity<FilmeResponse> entity = new HttpEntity<FilmeResponse>(headers);
//				     
//				    ResponseEntity<FilmeResponse> response = restTemplate.exchange(uri, HttpMethod.GET, entity, FilmeResponse.class);
//				    
//				    response.getBody();
			}
			
		}
		return filmes;
	}
	
}
