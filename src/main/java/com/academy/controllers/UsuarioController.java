package com.academy.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.academy.entidades.Funcionario;
import com.academy.repository.FuncionarioRepository;

@Controller
public class UsuarioController {
	
	@Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping("/login")
    public String login() {
        return "usuario/login";
    }

    @GetMapping("/perfil")
    public ModelAndView perfil(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("usuario/perfil");

        Funcionario usuario = funcionarioRepository.findByEmail(principal.getName()).get();
        modelAndView.addObject("usuario", usuario);
   

        return modelAndView;
    }
}
