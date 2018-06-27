package es.nicolas.uca.tpv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import es.nicolas.uca.tpv.entity.Componente;


public interface ComponenteDAO extends PagingAndSortingRepository<Componente, Long>{
	//selecciona los componentes de dado un Producto
	@Query("select cp.componente  from ComponenteProducto cp where cp.producto.id = :id")
	public List<Componente> findAllByProductId(@Param("id") Long id);
}
