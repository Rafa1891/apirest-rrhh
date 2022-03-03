package com.formacion.apirestrrhh.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="jefes")
public class Jefe implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id_jefe;
	@Column(nullable=false,unique=true)
	private String DNI;
	@Column(nullable=false)
	private String nombre;
	@Column(nullable=false)
	private double salario;
	@Column(nullable=false)
	private int telefono;
	@ManyToOne
	@JoinColumn(name="id_departamento")
	private Departamento departamento;
	
	public Long getId_jefe() {
		return id_jefe;
	}
	public void setId_jefe(Long id_jefe) {
		this.id_jefe = id_jefe;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	

	
	
}
