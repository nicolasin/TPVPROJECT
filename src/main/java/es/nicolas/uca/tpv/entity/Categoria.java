package es.nicolas.uca.tpv.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import es.nicolas.uca.tpv.services.UploadFileServiceImpl;
import es.nicolas.uca.tpv.services.Interface.IUploadFileService;


@Entity
@Table(name = "Categorias")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 2254120630994200045L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;
	@Column(name = "nombre")
	String nombre;
	@ManyToMany(mappedBy = "categorias", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	Set<Producto> productos = new HashSet<>();
	@Column(name="foto")
	String foto;

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Categoria() {

	}

	public Set<Producto> getProductos() {
		return productos;
	}

	public void setProductos(Set<Producto> productos) {
		this.productos = productos;
	}

	public Categoria(String nombre) {
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

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + "]";
	}

	public void addProducto(Producto p) {
		if (!productos.contains(p)) {
			productos.add(p);
			p.addCategoria(this);
		}
	}

	public void removeProducto(Producto p) {
		if (productos.contains(p)) {
			productos.remove(p);
			p.removeCategoria(this);
		}
	}
	@PreRemove
	private void eliminarFotoYRelaciones() {
		IUploadFileService uploadFileService = new UploadFileServiceImpl();
		uploadFileService.delete(foto);
		for(Producto prod: productos) {
			prod.categorias.remove(this);
		}
		productos.clear();
		
		
	}
}
