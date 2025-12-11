package edu.scoutsPoo.webApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.scoutsPoo.webApp.entities.Graduacion;


@RestController
@RequestMapping("/graduaciones")
public class GraduacionController {

    @GetMapping
    public Graduacion[] listar() {
        return Graduacion.values();
    }
}
