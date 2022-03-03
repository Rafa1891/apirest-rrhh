package com.formacion.apirestrrhh.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formacion.apirestrrhh.entity.Jefe;




@Repository
public interface JefeDao extends CrudRepository<Jefe,Long>{

}
