package es.nicolas.uca.tpv.Repository;

import org.springframework.data.repository.CrudRepository;
import es.nicolas.uca.tpv.entity.Categoria;

public interface CategoriaDAO extends CrudRepository<Categoria, Long> {

}
