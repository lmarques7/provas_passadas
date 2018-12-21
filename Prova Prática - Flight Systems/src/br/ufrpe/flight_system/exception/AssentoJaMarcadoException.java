package br.ufrpe.flight_system.exception;

public class AssentoJaMarcadoException extends Exception {
    
	private static final long serialVersionUID = 7457087928992345316L;
	
	private int idAssento;
    
    public AssentoJaMarcadoException(int idAssento) {
        super(String.format("Este assento (ID: %d) já está marcado", idAssento));
        
        this.idAssento = idAssento;
    }

    public int getIdAssento() {
        return idAssento;
    }

    public void setIdAssento(int idAssento) {
        this.idAssento = idAssento;
    }
}
