package br.usjt.ccp3an_mca_projeto_integrado.controller;

import br.usjt.ccp3an_mca_projeto_integrado.model.Usuario;
import br.usjt.ccp3an_mca_projeto_integrado.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @PostMapping ("/fazerLogin")
    public String fazerLogin (HttpServletRequest request, Usuario usuario) {
        if (loginService.logar(usuario)) {
            request.getSession().setAttribute("usuarioLogado", usuario);

            if(loginService.verificarPermissao(usuario).equals("administrador")) {
                return "indexAdministrador";
            } else if (loginService.verificarPermissao(usuario).equals("user")){
                return "indexUsuario";
            } else {
                return "login";
            }
        } else {
            return "login";
        }
    }
}