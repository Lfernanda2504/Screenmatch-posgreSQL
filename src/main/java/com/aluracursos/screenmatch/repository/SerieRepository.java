package com.aluracursos.screenmatch.repository;

import com.aluracursos.screenmatch.dto.EpisodioDTO;
import com.aluracursos.screenmatch.model.Categoria;
import com.aluracursos.screenmatch.model.Episodio;
import com.aluracursos.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    //encontrar por titulo
   Optional <Serie> findByTituloContainsIgnoreCase(String tituloSerie);

   List<Serie>findTop5ByOrderByEvaluacionDesc();
   List <Serie>findByGenero(Categoria categoria);
   //List<Serie> findByTotalTemporadasLessThanEqualAndEvaluacionGreaterThanEqual(int totalTemporadas, Double evaluacion);
    //permite hacer una Query nativa
//    @Query(value = "SELECT * fROM series WHERE series.total_temporadas <= 6 AND series.evaluacion  >= 7.5", nativeQuery = true)
//    List <Serie> seriePorTemporadasYEvaluaciones();

    @Query("SELECT s FROM Serie s WHERE s.totalTemporadas <= :totalTemporadas AND s.evaluacion >= :evaluacion")
    List<Serie> seriesPorTemparadaYEvaluacion(int totalTemporadas, Double evaluacion);

    //JOIN hace una intersección de series y episodios
    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE%:tituloEpisodio%")
    List<Episodio> episodiosPorNombre(String tituloEpisodio);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.evaluacion DESC LIMIT 5")
    List<Episodio> top5Episodios(Serie serie);

    //List<Serie>findOrderByFechaDeLanzamientoDesc();
    @Query("SELECT s FROM Serie s " + "JOIN s.episodios e " + "GROUP BY s " + "ORDER BY MAX(e.fechaDeLanzamiento) DESC LIMIT 5")
    List<Serie> lanzamientosMasRecientes();

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s.id = :id AND e.temporada = :numeroTemporada")
    List<Episodio> obtenerTemporadasPorNumero(Long id, Long numeroTemporada);
}

