package br.usjt.ccp3an_mca_projeto_integrado.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String mostrarIndex() {
        return "index";
    }
}
