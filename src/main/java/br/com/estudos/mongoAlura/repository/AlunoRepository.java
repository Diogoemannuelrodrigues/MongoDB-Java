package br.com.estudos.mongoAlura.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.estudos.mongoAlura.model.Aluno;

@Repository
public interface AlunoRepository extends MongoRepository<Aluno, String> {

}
