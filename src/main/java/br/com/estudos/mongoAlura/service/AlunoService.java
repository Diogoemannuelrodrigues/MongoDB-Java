package br.com.estudos.mongoAlura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estudos.mongoAlura.model.Aluno;
import br.com.estudos.mongoAlura.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepo;

	public List<Aluno> listAlunos() {
		return alunoRepo.findAll();
	}

	public Aluno salvarAluno(Aluno aluno) {
		return alunoRepo.save(aluno);
	}

	public Optional<Aluno> obterAlunoPor(String id) {
		Optional<Aluno> aluno2 = alunoRepo.findById(id);
		return aluno2;
	}
	
	public void deletarAluno(String codigo) {
		alunoRepo.deleteById(codigo);
	}

}
