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

import br.com.estudos.mongoAlura.model.Aluno;
import br.com.estudos.mongoAlura.service.AlunoService;


@Controller
public class AlunoController {

	@Autowired
	private AlunoService alunoService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/aluno/listar")
	public String listar(Model model) {
		List<Aluno> alunos = alunoService.listAlunos();
		model.addAttribute("alunos", alunos);
		return "aluno/listar";
	}

	@GetMapping("/aluno/cadastrar")
	public String cadastrar(Model model) {
		model.addAttribute("aluno", new Aluno());
		return "aluno/cadastrar";
	}

	@PostMapping("/aluno/salvar")
	public String salvar(@ModelAttribute Aluno aluno) {
		alunoService.salvarAluno(aluno);
		System.out.println("Aluno(a): " + aluno.getNome() + " salvo(a).");
//		return "redirect:/";
		if(alunoService.obterAlunoPor(aluno.getCodigo()).isPresent()) {
			return "aluno/cadastradosucesso";
		}
		else {
			return "aluno/error";
		}
	}

	@GetMapping("/aluno/visualizar/{codigo}")
	public String visualizar(@PathVariable String codigo, Model model) {
		Optional<Aluno> aluno = alunoService.obterAlunoPor(codigo);
		model.addAttribute("aluno", aluno);
		return "aluno/visualizar";
	}

//	@RequestMapping(value = "/aluno/deletar/{codigo}", method = RequestMethod.GET)
//	public String deletar(String codigo) {
//		if(codigo == null) {
//			throw new IllegalArgumentException("Nao encontrado");
//		} else {
//			Optional<Aluno> alunoFInd = alunoService.obterAlunoPor(codigo);
//		}
//		alunoService.deletarAluno(codigo);
////		System.out.println("Excluído o Aluno(a) "+ aluno.getNome() + " e codigo: "+ aluno.getCodigo());
//		return "aluno/deletar";
//	}

	@GetMapping("/aluno/deletar/{codigo}")
	public String deleteUser(@PathVariable("codigo") String codigo) {
	    alunoService.deletarAluno(codigo);
	    return "aluno/deletar";
	}
	
	

}
