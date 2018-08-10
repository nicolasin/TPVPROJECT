package es.nicolas.uca.tpv.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Productos")
public class Producto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4832795945140252078L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnore
	@NotEmpty
	private String nombre;
	@JsonIgnore
	@NotNull
	private double precio;
	@JsonIgnore
	@OneToMany(mappedBy = "producto")
	Set<ComponenteProducto> componentes = new HashSet<>();
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "CategoriasProductos", joinColumns = { @JoinColumn(name = "idProducto") }, inverseJoinColumns = {
			@JoinColumn(name = "idCategoria") })
	Set<Categoria> categorias = new HashSet<>();

	public Producto() {

	}

	public Producto(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<ComponenteProducto> getComponentesAndStock() {
		return componentes;
	}

	public Set<Componente> getComponentes() {
		return (Set<Componente>) componentes.stream().map(x -> x.getComponente()).collect(Collectors.toSet());
	}

	public void setComponentes(Set<ComponenteProducto> componentes) {
		this.componentes = componentes;
	}

	public void addComponente(Componente c, Double stock) {
		ComponenteProducto cp = new ComponenteProducto(c, this, stock);
		if (!componentes.contains(cp)) {
			componentes.add(cp);
		}

	}

	public void removeComponente(Componente c) {
		List<ComponenteProducto> componentesAEliminar = componentes.stream().filter(x -> x.getComponente().equals(c))
				.collect(Collectors.toList());
		componentes.removeAll(componentesAEliminar);
	}

	public Set<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(Set<Categoria> categorias) {
		this.categorias = categorias;
	}

	public void addCategoria(Categoria c) {
		if (!categorias.contains(c)) {
			categorias.add(c);
			c.addProducto(this);
		}
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void removeCategoria(Categoria c) {
		if (categorias.contains(c)) {
			categorias.remove(c);
			c.removeProducto(this);
		}
	}

	public Set<Alergeno> getAlergenosFromProducto() {
		Set<Alergeno> alergenos = new HashSet<Alergeno>();
		for (ComponenteProducto componente : componentes) {
			for (Alergeno alergeno : componente.getComponente().getAlergenos()) {
				alergenos.add(alergeno);
			}
		}
		return alergenos;
	}
}
