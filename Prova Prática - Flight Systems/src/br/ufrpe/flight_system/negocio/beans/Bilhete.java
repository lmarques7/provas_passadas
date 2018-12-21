package br.ufrpe.flight_system.negocio.beans;

import java.io.Serializable;

import br.ufrpe.flight_system.exception.AssentoInvalidoException;
import br.ufrpe.flight_system.exception.AssentoJaMarcadoException;

public class Bilhete implements Serializable {
    
	private static final long serialVersionUID = 5755776115799918803L;
	
	private Passageiro passageiro;
    private Voo voo;
    private int idAssento;
    
    /**
     * Uma vez emitido o bilhete, somente o idAssento poderá ser alterado. Se 
     * for necessário qualquer alteração no passageiro ou no voo, o bilhete 
     * precisará ser cancelado e emitido novamente.
     * 
     * @param passageiro
     * @param voo
     * @param idAssento
     * @throws AssentoInvalidoException
     * @throws AssentoJaMarcadoException
     */
    public Bilhete(Passageiro passageiro, Voo voo, int idAssento) 
            throws AssentoInvalidoException, AssentoJaMarcadoException {
        if (passageiro == null || voo == null || idAssento < 0) {
            throw new IllegalArgumentException();
        }
        this.passageiro = passageiro;
        this.voo = voo;
        this.idAssento = idAssento;
        
        this.voo.marcarAssento(idAssento);
    }

    public int getIdAssento() {
        return idAssento;
    }

    public void setIdAssento(int idAssento) {
        this.idAssento = idAssento;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public Voo getVoo() {
        return voo;
    }
    
    public String getPassageiroNome() {
        return passageiro.getNome();
    }

    public String getPassageiroUltimoNome() {
        return passageiro.getUltimoNome();
    }

    public String getPassageiroPassaporte() {
        return passageiro.getPassaporte();
    }

    public String getPassageiroCpf() {
        return passageiro.getCpf();
    }

    /**
     * A ASSINATURA DO MÉTODO DEVE SER MANTIDA.
     * 
     * Este método deve testar a igualdade do objeto corrente (this) com outro
     * objeto informado como parâmetro e retornar 'true' somente se as seguintes
     * condições forem atingidas: 
     * - O parâmetro deve ser do tipo Bilhete
     * 
     * - Todos os atributos deverão ser considerados na comparação, isto é, um 
     * objeto do tipo Bilhete somente será igual a outro se este outro for do 
     * tipo Bilhete também e os valores dos atributos passageiro, voo e 
     * idAssento forem iguais.  
     * 
     * Não esquecer de testar potenciais condições de NullPointerException.
     */
    @Override
    public boolean equals(Object obj) {
        // TODO Implementar seguindo os comentários do método e não alterar a assinatura do mesmo
        return false;
    }
    
    @Override
    public String toString() {
        return String.format("Passageiro: %s\nVoo: %s\nAssento: %d", this.passageiro, this.voo, this.idAssento);
    }
}
