package br.usjt.ccp3an_mca_projeto_integrado.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("arquivo")
public class ArquivoController {

	@GetMapping("upload")
    public String upload() {
        return "arquivo/upload";
    }
}
