package com.formacion.apirestrrhh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.formacion.apirestrrhh.entity.Jefe;

import com.formacion.apirestrrhh.service.JefeService;

@RestController
@RequestMapping("/api_rrhh")
public class JefeController {

	@Autowired
	private JefeService servicio;
	
	@GetMapping("/jefes")
	public List<Jefe> index(){
		return servicio.findAll();
	}
	

	@GetMapping("/jefes/{id}")
	public ResponseEntity<?> buscarJefesporId(@PathVariable Long id){
		
		Jefe jefe=null;
		
		Map<String,Object> response=new HashMap<>();
		
		try {
			jefe=servicio.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(jefe==null) {
			response.put("mensaje", "El jefe id:".concat(id.toString().concat(" no existe en la BD.")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Jefe>(jefe,HttpStatus.OK);
	}
	
	
	@PostMapping("/jefe")
	public ResponseEntity<?> guardarjefe(@RequestBody Jefe jefe) {
		
    Jefe jefeNuevo=null;
		Map<String,Object> response=new HashMap<>();
		
		try {
			jefeNuevo=servicio.save(jefe);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar inserción.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El jefe fue creado con éxito.");
		response.put("jefe", jefeNuevo);
		
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	
	@PutMapping("/jefe/{id}")
	public ResponseEntity<?> actualizarJefe(@RequestBody Jefe jefe,@PathVariable Long id) {
		
		Jefe jefeActualizado=servicio.findById(id);
		
		Map<String,Object> response=new HashMap<>();
		
		if(jefeActualizado==null) {
			response.put("mensaje", "El jefe id:".concat(id.toString().concat(" no existe en la BD.")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
				
		try {
			jefeActualizado.setId_jefe(id);
			jefeActualizado.setDNI(jefe.getDNI());
			jefeActualizado.setNombre(jefe.getNombre());
			jefeActualizado.setSalario(jefe.getSalario());
			jefeActualizado.setTelefono(jefe.getTelefono());
			jefeActualizado.setDepartamento(jefe.getDepartamento());
			
			
			servicio.save(jefeActualizado);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar actualización.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El jefe fue actualizado con éxito.");
		response.put("jefe", jefeActualizado);
		
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
		
	}
	
	
	
	@DeleteMapping("/jefe/{id}")
	public ResponseEntity<?> eliminarJefe(@PathVariable Long id) {
				
		Jefe jefe=servicio.findById(id);
		Map<String,Object> response=new HashMap<>();
		
		try {
			servicio.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(jefe==null) {
			response.put("mensaje", "El jefe id:".concat(id.toString().concat(" no existe en la BD.")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		response.put("mensaje", "Eliminado con éxito.");
		response.put("jefe", jefe);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}
