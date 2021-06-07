package br.com.b2w.desafio.response;

import java.util.List;

import lombok.Data;

@Data
public class PlanetaResponse {
    public int count;
    public Object next;
    public Object previous;
    public List<Result> results;
}
