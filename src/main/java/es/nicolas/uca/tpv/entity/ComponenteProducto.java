package es.nicolas.uca.tpv.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ComponentesProductos")
public class ComponenteProducto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4802770337784193056L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	@JoinColumn(name = "idComponente")
	Componente componente;
	@OneToOne
	@JoinColumn(name = "idProducto")
	Producto producto;
	@Column(name = "cantidad")
	double cantidad;

	public ComponenteProducto() {

	}

	public ComponenteProducto(Componente componente, Producto producto, Double cantidad) {
		this.componente = componente;
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Componente getComponente() {
		return componente;
	}

	public void setComponente(Componente c) {
		this.componente = c;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto p) {
		this.producto = p;
	}

}
