package br.ufrpe.flight_system.negocio;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.flight_system.dados.IRepositorioGenerico;
import br.ufrpe.flight_system.dados.RepositorioGenerico;
import br.ufrpe.flight_system.exception.AssentoInvalidoException;
import br.ufrpe.flight_system.exception.AssentoJaMarcadoException;
import br.ufrpe.flight_system.exception.ElementoJaExisteException;
import br.ufrpe.flight_system.exception.ElementoNaoExisteException;
import br.ufrpe.flight_system.negocio.beans.Bilhete;
import br.ufrpe.flight_system.negocio.beans.Passageiro;
import br.ufrpe.flight_system.negocio.beans.Voo;

public class ControladorBilhetes {
    
    private IRepositorioGenerico<Bilhete> repositorioBilhetes;
    private static ControladorBilhetes instance;
    
    private ControladorBilhetes() {
        this.repositorioBilhetes = new RepositorioGenerico<>("bilhetes.dat");
    }
    
    public static ControladorBilhetes getInstance() {
        if (instance == null) {
            instance = new ControladorBilhetes();
        }
        return instance;
    }

    public Bilhete emitirBilhete(Passageiro p, Voo v, int idAssento) 
            throws ElementoJaExisteException, AssentoInvalidoException, AssentoJaMarcadoException {
        // TODO Escrever regra de negócio a seguir antes de inserir bilhete no repositório
        // 1) Um bilhete só pode ser vendido para um voo cuja data de saída é posterior a data corrente
        // 2) Somente um bilhete pode ser emitido por assento
        // 3) O passageiro precisa pelo menos um documento de identificação (CPF ou passaporte) válido
        // 4) Lembre-se de marcar o assento no voo quando for vendido 
        // 5) Lembre-se de considerar os potenciais NullPointerException
        Bilhete b = null;
        // INSERIR SEU CÓDIGO COM A REGRA DE NEGÓCIO CRIANDO O OBJECTO b DO BILHETE
        repositorioBilhetes.inserir(b);
        
        return b;
    }

    public List<Bilhete> listar() {
        return repositorioBilhetes.listar();
    }

    public void remover(Bilhete obj) throws ElementoNaoExisteException {
        // TODO Escrever regra de negócio a seguir antes de remover bilhete do repositório
        // 1) Um bilhete só pode ser removido se o voo ainda não partiu, baseado
        // na data de hoje
        // 2) Ao remover um bilhete, o assento do voo do qual ele se referia
        // deve ser desmarcado e o objeto do voo deve ser salvo novamente no repositório de voos
        // 3) Levante possíveis exceções se o bilhete não puder ser removido 
        repositorioBilhetes.remover(obj);
    }

    public void atualizar(Bilhete bilheteEmitido, int novoAssento) 
            throws ElementoNaoExisteException, ElementoJaExisteException {
        // IMPLEMENTAÇÃO NÃO NECESSÁRIA PARA ESTA AVALIAÇÃO 
        // Escrever regra de negócio a seguir antes de atualizar o bilhete no repositório
        // Um bilhete somente pode ter o seu assento alterado.
        // Você deve verificar se o 'novoAssento' a ser alterado no
        // 'bilheteEmitido' já está ocupado no mesmo Voo, antes de atualizar o 
        // bilhete. Se o assento já estiver ocupado, você deve lançar a exceção 
        // correspondente (se necessário, crie outra exceção)       
        
        // Código a seguir não precisa ser alterado
        repositorioBilhetes.remover(bilheteEmitido);
        bilheteEmitido.setIdAssento(novoAssento);
        repositorioBilhetes.inserir(bilheteEmitido);
    }
    
    public List<Bilhete> listarBilhetesPorVoo(Voo v) {
        // TODO Implemente este método de acordo com as regras a seguir
        // Você deve retornar uma lista de Bilhetes emitidos para o pvoo
        // informado como parâmetro.
        return new ArrayList<Bilhete>();
    }

}
