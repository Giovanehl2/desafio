package br.com.b2w.desafio.response;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class FilmeResponse {

    public String title;
    public int episode_id;
    public String opening_crawl;
    public String director;
    public String producer;
    public String release_date;
    public List<String> characters;
    public List<String> planets;
    public List<String> starships;
    public List<String> vehicles;
    public List<String> species;
    public Date created;
    public Date edited;
    public String url;

    
}
