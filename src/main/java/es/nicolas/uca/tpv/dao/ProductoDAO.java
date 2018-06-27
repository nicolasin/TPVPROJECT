package es.nicolas.uca.tpv.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import es.nicolas.uca.tpv.entity.Producto;

public interface ProductoDAO extends PagingAndSortingRepository<Producto, Long> {

}
