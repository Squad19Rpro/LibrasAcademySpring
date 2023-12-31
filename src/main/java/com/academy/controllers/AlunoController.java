package com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.academy.entidades.Aluno;
import com.academy.repository.AlunoRepository;
import com.academy.repository.CursosRepository;

@Controller
@RequestMapping("/alunos")
public class AlunoController {
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private CursosRepository cursosRepository;
	
	@GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("aluno/home");
        
        modelAndView.addObject("estudantes", cursosRepository.findAll());
        modelAndView.addObject("alunos", alunoRepository.findAll());
        
        return modelAndView;
	}
	
	@GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("aluno/detalhes");
        modelAndView.addObject("cursos", cursosRepository.getReferenceById(id));
        modelAndView.addObject("aluno", alunoRepository.getReferenceById(id));

        return modelAndView;
    }
	
	@GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("aluno/formulario");
        modelAndView.addObject("aluno", new Aluno());      

        return modelAndView;
    }
	
	@GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("aluno/editAluno");
        modelAndView.addObject("aluno", alunoRepository.getReferenceById(id));

        return modelAndView;
    }
    
    @PostMapping("/cadastrar")
    public String cadastrar(Aluno aluno) {
        alunoRepository.save(aluno);

        return "redirect:/alunos";
    }
	
	@SuppressWarnings("unlikely-arg-type")
	@PostMapping("/{id}/editar")
    public String editar(Aluno aluno, @PathVariable Long id) throws Exception {
        alunoRepository.save(aluno);
        if (alunoRepository.equals(aluno.getCpf())) {
            throw new Exception("Cpf já existe");
        }

        if (alunoRepository.equals(aluno.getEmail())) {
            throw new Exception("Email já existe");
        }

        return "redirect:/alunos";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        alunoRepository.deleteById(id);

        return "redirect:/alunos";
    }
	
}
