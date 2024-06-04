package com.aluracursos.screenmatch.service;

import com.aluracursos.screenmatch.dto.SerieDTO;
import com.aluracursos.screenmatch.model.Serie;
import com.aluracursos.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    // inyección de dependencias
    @Autowired
    private SerieRepository repository;

    public List<SerieDTO> obtenerSeries() {
        //return series
        return convierteDatos(repository.findAll());
    }

    public List<SerieDTO> obtenerTop5() {
        return convierteDatos(repository.findTop5ByOrderByEvaluacionDesc());

    }

    public List<SerieDTO> obtenerLanzamientosMasRecientes() {
        return convierteDatos(repository.lanzamientosMasRecientes());
    }

    public List<SerieDTO> convierteDatos(List<Serie> serie) {
        return serie.stream()
                //conversion de tipo serie a tipo serieDTO
                .map(s -> new SerieDTO(s.getId(),s.getTitulo(), s.getTotalTemporadas(), s.getEvaluacion(), s.getPoster(),
                        s.getGenero(), s.getActores(), s.getSinopsis()))
                .collect(Collectors.toList());


    }

    public SerieDTO obtenerPorId(Long id) {
        Optional<Serie> serie = repository.findById(id);
        if (serie.isPresent()) {
            Serie s = serie.get();
            return new SerieDTO(s.getId(),s.getTitulo(), s.getTotalTemporadas(), s.getEvaluacion(), s.getPoster(),
                    s.getGenero(), s.getActores(), s.getSinopsis());

        }
        return null;

    }

}