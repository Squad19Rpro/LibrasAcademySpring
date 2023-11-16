package com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.academy.entidades.Aluno;
import com.academy.repository.AlunoRepository;

@Controller
@RequestMapping("/src")
public class casdastroSite {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("aluno/formulario");
        modelAndView.addObject("aluno", new Aluno());      

        return modelAndView;
    }
	
	@PostMapping("/cadastrar")
    public String cadastrar(Aluno aluno) {
        //String senhaEncriptada = SenhaUtils.encode(aluno.getSenha());//
        //aluno.setSenha(senhaEncriptada);
        alunoRepository.save(aluno);

        return "redirect:/";
    }
}
