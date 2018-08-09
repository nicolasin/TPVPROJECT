package es.nicolas.uca.tpv.Repository;

import org.springframework.data.repository.CrudRepository;

import es.nicolas.uca.tpv.entity.Alergeno;


public interface AlergenoDAO extends CrudRepository<Alergeno, Long> {

	//@Query("select nombre from alergenos where id in (select alergenos_id from componentes_alergenos where componente_id IN (select id_componente from componentes_productos where id_producto = 8))")
	//@Query("select a from Alergeno a where Componente.id = :id")
	//public List<Alergeno> findAllByComponenteId(@Param("id") Long id);

}
