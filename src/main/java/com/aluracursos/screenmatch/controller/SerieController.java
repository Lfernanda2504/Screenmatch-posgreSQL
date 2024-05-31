package com.aluracursos.screenmatch.controller;


import com.aluracursos.screenmatch.dto.SerieDTO;
import com.aluracursos.screenmatch.model.Serie;
import com.aluracursos.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SerieController {
    // inyección de dependencias
    @Autowired
    private SerieRepository repository;


    //para obtener datos de una ruta en especifico
    //endpoint
    @GetMapping("/series")
    public List<SerieDTO> obtenerSeries(){
        //return series
        return repository.findAll().stream()
                //conversion de tipo serie a tipo serieDTO
                .map(s-> new SerieDTO(s.getTitulo(), s.getTotalTemporadas(), s.getEvaluacion(), s.getPoster(),
                        s.getGenero(), s.getActores(), s.getSinopsis()))
                .collect(Collectors.toList());
    }


}
