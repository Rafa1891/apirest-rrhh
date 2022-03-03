package com.formacion.apirestrrhh.service;

import java.util.List;

import com.formacion.apirestrrhh.entity.Jefe;



public interface JefeService {

	public List<Jefe> findAll();

	public Jefe findById(Long id);

	public Jefe save(Jefe jefe);

	public void delete(Long id);
}
