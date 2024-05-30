package com.aluracursos.screenmatch.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SerieController {
    //para obtener datos de una ruta en especifico
    //endpoint
    @GetMapping("/series")
    public  String mostrarMensaje(){
        return "Mensaje aplicación web";
    }
}
