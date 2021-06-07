package br.com.b2w.desafio.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.b2w.desafio.model.Planeta;

@Repository
public interface PlanetaRepository extends MongoRepository<Planeta, String> {

	@Query("{'nome' : ?0 }")
	public Optional<Planeta> findByNome(String nome);
	

}
