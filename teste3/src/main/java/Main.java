import java.util.List;

import com.escola.conexao.MongoConnection;
import com.escola.dao.AlunoDAO;
import com.escola.model.Aluno;

public class Main {
    public static void main(String[] args) {
        AlunoDAO alunoDAO = new AlunoDAO();
        alunoDAO.excluirTodos();
        
        List<Aluno> alunos = alunoDAO.listarTodos();
        alunos.forEach(a -> System.out.println("Nome: " + a.getNome() + " | RA: " + a.getRA()));

        System.out.println("Importação finalizada. Fechando conexão.");
        MongoConnection.close();
    }
}