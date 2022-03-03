package com.formacion.apirestrrhh.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacion.apirestrrhh.entity.Departamento;




@Repository
public interface DepartamentoDao extends CrudRepository<Departamento,Long>{

}
