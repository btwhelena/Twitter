package exceptions;

import java.lang.String;

public class SIException extends Exception{
	private static final long serialVersionUID = 1L;
	private String usuario;
	public SIException(String usuario) {
		super("seguidor = seguido");
		this.usuario = usuario;
	}
	
	public String getMessage() {
		return "Usuário não pode seguir a si mesmo. [usuário: " + this.usuario + "]";
	}
	
	public String getUsuario() {
		return this.usuario;
	}	
}