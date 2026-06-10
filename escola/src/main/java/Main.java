import java.util.Arrays;

import org.bson.types.ObjectId;

import com.escola.conexao.MongoConnection;
import com.escola.csv.AlunoCSV;
import com.escola.csv.ImportadorCSV;
import com.escola.csv.ProfessorCSV;
import com.escola.csv.TurmaCSV;
import com.escola.dao.AlunoDAO;
import com.escola.dao.ProfessorDAO;
import com.escola.dao.TurmaDAO;
import com.escola.model.Aluno;
import com.escola.model.Professor;
import com.escola.model.Turma;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        AlunoDAO alunoDAO = new AlunoDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();
        TurmaDAO turmaDAO = new TurmaDAO();

        //LIMPAR BANCO
        turmaDAO.excluirTodos();
        alunoDAO.excluirTodos();
        professorDAO.excluirTodos();

        //TESTE CSV
       /* Thread t1 = new Thread(
                new ImportadorCSV<Professor>(
                        "C:\\Users\\lucas\\OneDrive\\Documentos\\professores.csv",
                        new ProfessorCSV(professorDAO)
                )
        );
        t1.start();
        t1.join();

        Thread t2 = new Thread(
                new ImportadorCSV<Aluno>(
                        "C:\\Users\\lucas\\OneDrive\\Documentos\\alunos.csv",
                        new AlunoCSV(alunoDAO)
                )
        );
        t2.start();
        t2.join();

        Thread t3 = new Thread(
                new ImportadorCSV<Turma>(
                        "C:\\Users\\lucas\\OneDrive\\Documentos\\turmas.csv",
                        new TurmaCSV(professorDAO, alunoDAO, turmaDAO)
                )
        );
        t3.start();
        t3.join();

        System.out.println("\n=== ALUNOS ===");
        alunoDAO.listarTodos().forEach(a ->
                System.out.println(a.getNome() + " - " + a.getMatricula())
        );

        System.out.println("\n=== PROFESSORES ===");
        professorDAO.listarTodos().forEach(p ->
                System.out.println(p.getNome() + " - " + p.getDisciplina())
        );

        System.out.println("\n=== TURMAS ===");

        turmaDAO.listarTodos().forEach(t -> {

            System.out.println("Turma: " + t.getNome());

            Professor professor =
                    professorDAO.buscarPorId(
                            t.getProfessorId().toHexString()
                    );

            System.out.println(
                    "Professor: "
                    + professor.getNome()
            );

            System.out.println("Alunos:");

            for (ObjectId alunoId : t.getAlunosIds()) {

                Aluno aluno =
                        alunoDAO.buscarPorId(
                                alunoId.toHexString()
                        );

                System.out.println(
                        " - "
                        + aluno.getNome()
                        + " ("
                        + aluno.getMatricula()
                        + ")"
                );
            }

            System.out.println("--------------------");
        });*/
        
        
        //EXCLUINDO ALUNO COM VINCULAÇÃO EM TURMA
        /*
        Professor professor =
                new Professor("Janaína", "Matemática");
        professorDAO.salvar(professor);
        
        Aluno aluno =
                new Aluno("Lucas", "100");
        alunoDAO.salvar(aluno);

        Turma turma = new Turma();
        turma.setNome("Turma Teste");
        

        turma.setProfessorId(
                new ObjectId(professor.getId())
        );

        turma.setAlunosIds(
                Arrays.asList(
                        new ObjectId(aluno.getId())
                )
        );

        turmaDAO.salvar(turma);

        System.out.println(
                "Turma criada com sucesso."
        );

        try {

            System.out.println(
                    "\nTentando excluir aluno..."
            );

            alunoDAO.excluir(aluno.getId());

            System.out.println(
                    "Aluno excluído!"
            );

        } catch (RuntimeException e) {

            System.out.println(
                    "ERRO: " + e.getMessage()
            );
        }
*/
        

        MongoConnection.close();
    }
}