package mongodb;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	private final AlunoService service;
	
	public AlunoController(AlunoService service) {
		this.service = service;
	}
	
	@GetMapping("/tudo")
	public List<Aluno> allAlunos() {
		return service.tudo();
	}
	
	@DeleteMapping("/delete")
	public List<Aluno> deleteAll() {
		return service.deleteAll();
	}
}
