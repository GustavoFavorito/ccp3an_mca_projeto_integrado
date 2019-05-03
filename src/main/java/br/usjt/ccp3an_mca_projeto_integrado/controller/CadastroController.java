package br.usjt.ccp3an_mca_projeto_integrado.controller;

import br.usjt.ccp3an_mca_projeto_integrado.model.Usuario;
import br.usjt.ccp3an_mca_projeto_integrado.service.CadastroService;
import br.usjt.ccp3an_mca_projeto_integrado.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CadastroController {

    private LoginService loginService;
    private CadastroService cadastroService;

    @GetMapping(value = {"/cadastro"})
    public ModelAndView login (HttpServletRequest request) {
        request.getSession().setAttribute("fazerCadastro", "fazerCadastro");
        ModelAndView mv = new ModelAndView ("cadastro");
        mv.addObject(new Usuario());
        return mv;
    }

    @PostMapping("/fazerCadastro")
    public String fazerCadastro(Usuario usuario, Model model){
        if(loginService.verificaLogin(usuario)) {
            model.addAttribute("erroCadastro", "erroCadastro");
        } else {
            cadastroService.salvar(usuario);
        }
        return "cadastro";
    }
}
