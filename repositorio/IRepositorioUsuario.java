package repositorio;

import perfis.Perfil;
import exceptions.UJCException;
import exceptions.UNCException;

public interface IRepositorioUsuario {
	public void cadastrar(Perfil usuario) throws UJCException;
	public Perfil buscar(String usuario);
	public void atualizar(Perfil usuario) throws UNCException;
}