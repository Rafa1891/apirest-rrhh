package com.formacion.apirestrrhh.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacion.apirestrrhh.entity.Empleado;


@Repository
public interface EmpleadoDao extends CrudRepository<Empleado,Long>{

}
