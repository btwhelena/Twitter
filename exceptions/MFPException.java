package exceptions;

public class MFPException extends Exception {
	private static final long serialVersionUID = 1L;
	private String mensagem;
	public MFPException(String mensagem) {
		super("Mensagem fora do padrão");
		this.mensagem = mensagem;
	}
	
	public String getMessage() {
		if (mensagem.length() < 1) {
			return "Mensagem muito curta. O tweet deve possuir 1-140 caracteres.";
		} else {
			return "Mensagem muito longa. O tweet deve possuir 1-140 carccteres.";
		}
	}
	
	public String getMensagemEnviada() {
		return this.mensagem;
	}
}
