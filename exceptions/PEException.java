package exceptions;

public class PEException extends Exception {
	private static final long serialVersionUID = 1L;
	private String usuario;
	
	public PEException(String usuario) {
		super("Perfil já existe");
		this.usuario = usuario;
	}
	
	public String getMessage() {
		return "Este usuário já existe. [usuário : " + this.usuario + "]";
	}
	
	public String getUsuario() {
		return this.usuario;
	}
}