package repositorio;

import perfis.Perfil;
import java.util.Vector;
import exceptions.UJCException;
import exceptions.UNCException;

public class RepositorioVector implements IRepositorioUsuario {
	private Vector<Perfil> usuariosRepo;
	
	public RepositorioVector() {
		usuariosRepo = new Vector<Perfil>();
	}
	public void cadastrar(Perfil usuario) throws UJCException{
		if (buscar(usuario.getUsuario()) == null) {
			this.usuariosRepo.add(usuario);
		}
		else {
			throw new UJCException(usuario.getUsuario());
		}
	}

	public Perfil buscar(String usuario) {
		for (int i = 0; i < usuariosRepo.size(); i++) {
			if (usuariosRepo.get(i).getUsuario().equals(usuario)) {
				return usuariosRepo.get(i);
			}
		}
		return null;
	}

	public void atualizar(Perfil usuario) throws UNCException{
		Perfil usuarioVelho = buscar(usuario.getUsuario());
		if (usuarioVelho == null) {
			throw new UNCException(usuario.getUsuario());
		} else {
			usuarioVelho = usuario;
		}
	}
}
