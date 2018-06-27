package es.nicolas.uca.tpv.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "componentes")
public class Componente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1079175194295743221L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@NotEmpty
	private String nombre;
	@ManyToMany
	@JoinTable(name = "componentes_alergenos", joinColumns = {
			@JoinColumn(name = "componente_id") }, inverseJoinColumns = { @JoinColumn(name = "alergenos_id") })
	private Set<Alergeno> alergenos = new HashSet<>();
	@Column(name = "stock")
	private double stock;
	@OneToMany(mappedBy = "componente", fetch = FetchType.LAZY)
	Set<ComponenteProducto> productos = new HashSet<>();

	public Set<ComponenteProducto> getProductos() {
		return productos;
	}

	public void setProductos(Set<ComponenteProducto> productos) {
		this.productos = productos;
	}
	public Componente() {

	}

	public Componente(String nombre, double stock) {
		this.nombre = nombre;
		this.stock = stock;
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

	public double getStock() {
		return stock;
	}

	public void setStock(double stock) {
		this.stock = stock;
	}

	public Set<Alergeno> getAlergenos() {
		return alergenos;
	}

	public void setAlergenos(Set<Alergeno> alergenos) {
		this.alergenos = alergenos;
	}
	public void addAlergeno(Alergeno alergeno) {
		if (!alergenos.contains(alergeno)) {
			alergenos.add(alergeno);
			for (Alergeno alerg : alergenos) {
				alerg.addComponente(this);
			}
		}
	}
	public void removeAlergeno(Alergeno a) {
		if (alergenos.contains(a)) {
			for (Alergeno aler : alergenos) {
				aler.removeComponente(this);
			}
			alergenos.remove(a);

		}
	}
	@PreRemove
	private void removeRelationship() {
		alergenos.stream().forEach(x->x.removeComponente(this));
	}

}
