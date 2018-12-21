package br.ufrpe.flight_system.exception;

public class AssentoInvalidoException extends Exception {

	private static final long serialVersionUID = 7180804204542671123L;
	
	private int idAssento;
    
    public AssentoInvalidoException(int idAssento) {
        super("Este ID de assento não é válido para o Voo. ID: " + idAssento);
        this.idAssento = idAssento;
    }

    public int getIdAssento() {
        return idAssento;
    }

    public void setIdAssento(int idAssento) {
        this.idAssento = idAssento;
    }
}
