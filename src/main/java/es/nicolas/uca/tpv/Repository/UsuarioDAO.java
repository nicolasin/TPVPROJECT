package es.nicolas.uca.tpv.Repository;

import org.springframework.data.repository.CrudRepository;

import es.nicolas.uca.tpv.entity.Usuario;

public interface UsuarioDAO extends CrudRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username);

}
