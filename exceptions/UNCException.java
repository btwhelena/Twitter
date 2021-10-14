package exceptions;

public class UNCException extends Exception {
	private static final long serialVersionUID = 1L;
	String usuario;
	public UNCException(String usuario) {
		super("Usuário não cadastrado");
		this.usuario = usuario;
	}
	
	public String getMessage() {
		return "Não existe nenhum usuário cadastrado com o nome " + this.usuario;
	}
	
	public String getUsuario() {
		return this.usuario;
	}
}