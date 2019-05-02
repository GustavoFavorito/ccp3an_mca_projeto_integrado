package br.usjt.ccp3an_mca_projeto_integrado.controller;

import br.usjt.ccp3an_mca_projeto_integrado.model.Usuario;
import br.usjt.ccp3an_mca_projeto_integrado.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping (value = {"/login", "/"})
    public ModelAndView login () {
        ModelAndView mv = new ModelAndView ("login");
        mv.addObject(new Usuario());
        return mv;
    }

    @PostMapping("/fazerLogin")
    public String fazerLogin (HttpServletRequest request, Usuario usuario, Model model) {
        if (loginService.logar(usuario)) {
            request.getSession().setAttribute("usuarioLogado", usuario);

            if(loginService.verificarPermissao(usuario).equals("administrador")) {
                return "index";
            } else {
                return "";
            }
        } else {
            model.addAttribute("erroLogin", "erroLogin");
            return "login";
        }
    }

    @PostMapping("/fazerCadastro")
    public String fazerCadastro(Usuario usuario, Model model){
        if(loginService.verificaLogin(usuario)) {
            model.addAttribute("erroCadastro", "erroCadastro");
        } else {
            loginService.salvar(usuario);
        }
        return "login";
    }
}