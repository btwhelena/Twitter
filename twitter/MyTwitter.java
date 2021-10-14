package twitter;

import java.util.Vector;

import perfis.Perfil;
import exceptions.*;
import repositorio.*;

public class MyTwitter implements ITwitter {
	RepositorioVector repositorio = new RepositorioVector();
	
	public void criarPerfil(Perfil usuario) throws PEException {
		try {
			this.repositorio.cadastrar(usuario);
			System.out.println("");
			System.out.println("Usuário cadastrado");
		} catch (UJCException ujce) {
			throw new PEException(usuario.getUsuario());
		}
	}

	public void cancelarPerfil(String usuario) throws PIException, PDException {
		Perfil perfil = this.repositorio.buscar(usuario);
		if (perfil != null) {
			if (perfil.isAtivo()) {
				perfil.setAtivo(false);
				System.out.println("");
				System.out.println("Perfil desativado.");
			}
			else {
				throw new PDException(usuario);
			}
		} else {
			throw new PIException(usuario);
		}
	}

	public void tweetar(String usuario, String mensagem) throws PIException, MFPException, PDException{
		if(mensagem.length() <= 140 && mensagem.length() >= 1) {
			Tweet tweet = new Tweet(usuario, mensagem);
			Perfil perfil = this.repositorio.buscar(usuario);
			if(perfil != null) {
				if(perfil.isAtivo()) {
					perfil.addTweet(tweet);
					Vector<String> seguidores = perfil.getSeguidores();
					for (String seguidor : seguidores) {
						this.repositorio.buscar(seguidor).addTweet(tweet);
					}
					System.out.println("");
					System.out.println("Mensagem tweetada.");
				} else {
					throw new PDException(usuario);
				}
			}
			else {
				throw new PIException(usuario);
			}
		}
		else {
			throw new MFPException(mensagem);
		}
	}

	public Vector<Tweet> timeline(String usuario) throws PIException, PDException {
		Perfil perfil = this.repositorio.buscar(usuario);
		if (perfil != null) {
			if (perfil.isAtivo()) {
				return perfil.getTimeline();
			} else {
				throw new PDException(usuario);
			}
		}
		else {
			throw new PIException(usuario);
		}
	}

	public Vector<Tweet> tweets(String usuario) throws PIException, PDException {
		Vector<Tweet> vetorTweets = new Vector<Tweet>();
		Perfil perfil = this.repositorio.buscar(usuario);
		if(perfil != null) {
			if(perfil.isAtivo()) {
				for (Tweet tweet : perfil.getTimeline()) {
					if (tweet.getUsuario().equals(usuario)) {
						vetorTweets.add(tweet);
					}
				}
				return vetorTweets;
			} else {
				throw new PDException(usuario);
			}
		} else {
			throw new PIException(usuario);
		}
	}

	public void seguir(String seguidor, String seguido) throws PIException, PDException, SIException {
    Perfil perfilSeguido = this.repositorio.buscar(seguido);
    Perfil perfilSeguidor = this.repositorio.buscar(seguidor);
    if (perfilSeguido != null && perfilSeguidor != null) {
      if (perfilSeguido.isAtivo() && perfilSeguidor.isAtivo()) {
        if (seguido.equals(seguidor)) {
          throw new SIException(seguido);
        }
        else {
          perfilSeguido.addSeguidor(seguidor);
          perfilSeguidor.addSeguido(seguido);
          for (Tweet tweet : tweets(seguido)) {
            perfilSeguidor.addTweet(tweet);
          }
          System.out.println("\nUsuário seguido!");
          System.out.println("Agora você também pode ver o que foi tweetado por " + seguido + ".");
        }
      } else {
        if (!perfilSeguido.isAtivo()) {
          throw new PDException(seguido);
        } else {
          throw new PDException(seguidor);
        }
      }
    } else {
      if (perfilSeguido == null) {
        throw new PIException(seguido);
      } else {
        throw new PIException(seguidor);
      }
    }
	}

	public int numeroSeguidores(String usuario) throws PIException, PDException {
		Perfil perfil = this.repositorio.buscar(usuario);
		if (perfil != null) {
			if (perfil.isAtivo()) {
				return perfil.getSeguidores().size();
			} else {
				throw new PDException(usuario);
			}
		} else {
			throw new PIException(usuario);
		}
	}

	public Vector<Perfil> seguidores(String usuario) throws PIException, PDException {
		Perfil perfil = this.repositorio.buscar(usuario);
		if (perfil != null) {
			if (perfil.isAtivo()) {
				Vector<Perfil> seguidores = new Vector<Perfil>();
				for (String seguidor : perfil.getSeguidores()) {
					seguidores.add(this.repositorio.buscar(seguidor));
				}
				return seguidores;
			} else {
				throw new PDException(usuario);
			}
		} else {
			throw new PIException(usuario);
		}
	}

  public Vector<Perfil> seguidos(String usuario) throws PIException, PDException {
		Perfil perfil = this.repositorio.buscar(usuario);
		if (perfil != null) {
			if (perfil.isAtivo()) {
				Vector<Perfil> seguidos = new Vector<Perfil>();
				for (String seguido : perfil.getSeguidos()) {
					seguidos.add(this.repositorio.buscar(seguido));
				}
				return seguidos;
			} else {
				throw new PDException(usuario);
			}
		} else {
			throw new PIException(usuario);
		}
	}

}