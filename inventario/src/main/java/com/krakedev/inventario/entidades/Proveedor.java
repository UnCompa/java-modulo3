package com.krakedev.inventario.entidades;

public class Proveedor {
	private String identifficador;
	private String tipoDocumento;
	private String nombre;
	private String telefono;
	private String correo;
	private String direccion;

	public Proveedor() {
		
	}
	
	public Proveedor(String identifficador, String tipoDocumento, String nombre, String telefono, String correo,
			String direccion) {
		super();
		this.identifficador = identifficador;
		this.tipoDocumento = tipoDocumento;
		this.nombre = nombre;
		this.telefono = telefono;
		this.correo = correo;
		this.direccion = direccion;
	}

	public String getIdentifficador() {
		return identifficador;
	}

	public void setIdentifficador(String identifficador) {
		this.identifficador = identifficador;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Proveedor [identifficador=" + identifficador + ", tipoDocumento=" + tipoDocumento + ", nombre=" + nombre
				+ ", telefono=" + telefono + ", correo=" + correo + ", direccion=" + direccion + "]";
	}

	
}
