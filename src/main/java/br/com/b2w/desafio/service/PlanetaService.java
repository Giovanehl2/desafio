package br.com.b2w.desafio.service;

import java.util.Collection;
import java.util.Optional;

import br.com.b2w.desafio.model.Planeta;

public interface PlanetaService {

    public Collection<Planeta> listarPlanetas();

    public Optional<Planeta> buscarPlanetaPorId( String id);

    public Optional<Planeta> buscarPlanetaPorNome( String id);

    public Planeta editarPlaneta( String id, Planeta planeta);
    
    public Planeta salvarPlaneta( Planeta planeta); 
    
    public boolean deletarPlaneta(String id);

}
