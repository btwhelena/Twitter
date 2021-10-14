package exceptions;

public class PIException extends Exception {
	private static final long serialVersionUID = 1L;
	private String usuario;
	
	public PIException(String usuario) {
		super("Usuário inexistente");
		this.usuario = usuario;
	}
	
	public String getMessage() {
		return "Este usuário não está cadastrado. [usuário: " + this.usuario + "]";
	}
	
	public String getUsuario() {
		return this.usuario;
	}
}