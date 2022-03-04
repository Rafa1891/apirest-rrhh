package com.formacion.apirestrrhh.dao;




import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacion.apirestrrhh.entity.Usuario;




@Repository
public interface UsuarioDao extends CrudRepository<Usuario,Long>{

	public Usuario findByUsername(String username);
	
	/*tambien se puede hacer así
	 * @Query("select u from Usuario u where u.username=?1")
	 * public Usuario findByUsername(String username);
	 */
	
	
}
