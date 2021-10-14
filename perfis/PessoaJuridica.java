package perfis;

import java.lang.String;

public class PessoaJuridica extends Perfil {
	private long cnpj;
	
	public PessoaJuridica(String usuario, long cnpj) {
		super(usuario);
		this.setCnpj(cnpj);
	}
	
	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
	}
	
	public long getCnpj() {
		return this.cnpj;
	}
}
