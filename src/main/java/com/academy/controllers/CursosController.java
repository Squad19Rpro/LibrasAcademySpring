package com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.academy.repository.AlunoRepository;
import com.academy.repository.CursosRepository;

import com.academy.entidades.Cursos;

@Controller
@RequestMapping("/cursos")
public class CursosController {
	
	@Autowired
	private CursosRepository cursosRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("cursos/home");
        modelAndView.addObject("cursos", cursosRepository.findAll());

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("cursos/detalhes");
        modelAndView.addObject("curso", cursosRepository.getReferenceById(id));

        return modelAndView;
    }
    
    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("cursos/formulario");
        modelAndView.addObject("cursos", new Cursos());
        modelAndView.addObject("estudantes", alunoRepository.findAll());
//        modelAndView.addObject("lideres", funcionarioRepositorio.findByCargoNome("Gerente"));
//        modelAndView.addObject("funcionarios", funcionarioRepositorio.findByCargoNomeNot("Gerente"));

        return modelAndView;
    }
    
    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("cursos/formulario");

        modelAndView.addObject("cursos", new Cursos());
        modelAndView.addObject("alunos", alunoRepository.findAll());
//        modelAndView.addObject("lideres", funcionarioRepositorio.findByCargoNome("Gerente"));
//        modelAndView.addObject("funcionarios", funcionarioRepositorio.findByCargoNomeNot("Gerente"));

        return modelAndView;
    }

    @PostMapping({"/cadastrar", "/{id}/editar"})
    public String salvar(Cursos curso) {
        cursosRepository.save(curso);

        return "redirect:/cursos";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        cursosRepository.deleteById(id);

        return "redirect:/cursos";
    }
}