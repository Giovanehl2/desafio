package br.com.b2w.desafio.model;

import java.util.List;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Planeta {
	
	@Id
	private String  id;
	private String  nome;
	private String clima;
	private String terreno;
	private List<String> filmes;
	private Integer numeroDeFilmes;

}
