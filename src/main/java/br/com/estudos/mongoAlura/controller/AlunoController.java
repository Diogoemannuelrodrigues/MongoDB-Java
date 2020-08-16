package br.com.estudos.mongoAlura.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String listar(Model model) {
		List<Aluno> alunos = service.listAlunos();
		model.addAttribute("alunos", alunos);
		return "Aluno/listar";
	}

	@GetMapping("/aluno/cadastrar")
	public String cadastrar(Model model) {
		model.addAttribute("aluno", new Aluno());
		return "Aluno/cadastrar";
	}

	@PostMapping("/aluno/salvar")
	public String salvar(@ModelAttribute Aluno aluno) {
		service.salvarAluno(aluno);
		System.out.println("Aluno(a) " + aluno.getNome() + " salvo(a)");
		return "redirect:/";
	}

	@GetMapping("/aluno/visualizar/{codigo}")
	public String visualizar(@PathVariable String codigo, Model model) {
		Optional<Aluno> aluno = service.obterAlunoPor(codigo);
		model.addAttribute("aluno", aluno);
		return "Aluno/visualizar";
	}

	@RequestMapping(value = "/aluno/deletar/{codigo}", method = RequestMethod.GET)
	public String deletar(Aluno aluno) {
		System.out.println(aluno.getNome());
		service.deletarAluno(aluno.getCodigo());
		return "Aluno/deletar";
	}
	
//	@RequestMapping(method = RequestMethod.GET, value = "/aluno/deletar/{codigo}")
//    public String remover(@PathVariable("codigo") String codigo) {
//		Optional<Aluno> aluno = service.obterAlunoPor(codigo);
//		service.deletarAluno(codigo);
//		return "Aluno/deletar";
//    }
	
	

}
