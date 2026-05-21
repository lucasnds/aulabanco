package mongodb;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlunoRepository extends MongoRepository<Aluno, String> {
	public List<Aluno> findByName(String nome);
	public List<Aluno> findByAge(String age);
}
