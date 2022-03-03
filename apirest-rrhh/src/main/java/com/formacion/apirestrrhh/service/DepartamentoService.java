package com.formacion.apirestrrhh.service;

import java.util.List;

import com.formacion.apirestrrhh.entity.Departamento;


public interface DepartamentoService {
	
	public List<Departamento> findAll();

	public Departamento findById(Long id);

	public Departamento save(Departamento jefe);

	public void delete(Long id);

}
