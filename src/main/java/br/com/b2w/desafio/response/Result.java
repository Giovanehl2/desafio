package br.com.b2w.desafio.response;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Result {
    public String name;
    public String rotation_period;
    public String orbital_period;
    public String diameter;
    public String climate;
    public String gravity;
    public String terrain;
    public String surface_water;
    public String population;
    public List<String> residents;
    public List<String> films;
    public Date created;
    public Date edited;
    public String url;
}
