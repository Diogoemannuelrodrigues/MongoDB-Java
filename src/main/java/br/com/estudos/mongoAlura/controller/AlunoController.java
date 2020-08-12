package br.com.estudos.mongoAlura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.estudos.mongoAlura.model.Aluno;
import br.com.estudos.mongoAlura.service.AlunoService;

@Controller
public class AlunoController {
	
	@Autowired
	private AlunoService service;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/aluno/listar")
	public String listar(Model model){
		List<Aluno> alunos = service.listAlunos();
		model.addAttribute("alunos", alunos);
		return "Aluno/listar";
	}
	
	@GetMapping("/aluno/cadastrar")
	public String cadastrar(Model model){
		model.addAttribute("aluno", new Aluno()); 
		return "Aluno/cadastrar";
	}
	
	@PostMapping("/aluno/salvar")
	public String salvar(@ModelAttribute Aluno aluno){
		service.salvarAluno(aluno);	
		System.out.println("Aluno "+ aluno.getNome() +" salvo");
		return "redirect:/";
	}

}
