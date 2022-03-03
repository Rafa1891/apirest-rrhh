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

import com.formacion.apirestrrhh.entity.Departamento;
import com.formacion.apirestrrhh.service.DepartamentoService;


@RestController
@RequestMapping("/api_rrhh")
public class DepartamentoController {

	
	@Autowired
	private DepartamentoService servicio;
	
	@GetMapping("/departamentos")
	public List<Departamento> index(){
		return servicio.findAll();
	}
	

	@GetMapping("/departamentos/{id}")
	public ResponseEntity<?> buscarDepartamentosporId(@PathVariable Long id){
		
		Departamento departamento=null;
		
		Map<String,Object> response=new HashMap<>();
		
		try {
			departamento=servicio.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(departamento==null) {
			response.put("mensaje", "El departamento id:".concat(id.toString().concat(" no existe en la BD.")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Departamento>(departamento,HttpStatus.OK);
	}
	
	
	@PostMapping("/departamento")
	public ResponseEntity<?> guardardepartamento(@RequestBody Departamento departamento) {
		
    Departamento departamentoNuevo=null;
		Map<String,Object> response=new HashMap<>();
		
		try {
			departamentoNuevo=servicio.save(departamento);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar inserción.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El departamento fue creado con éxito.");
		response.put("departamento", departamentoNuevo);
		
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	
	@PutMapping("/departamento/{id}")
	public ResponseEntity<?> actualizarDepartamento(@RequestBody Departamento departamento,@PathVariable Long id) {
		
		Departamento departamentoActualizado=servicio.findById(id);
		
		Map<String,Object> response=new HashMap<>();
		
		if(departamentoActualizado==null) {
			response.put("mensaje", "El departamento id:".concat(id.toString().concat(" no existe en la BD.")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
				
		try {
			departamentoActualizado.setId_departamento(id);
			departamentoActualizado.setNombre(departamento.getNombre());
			departamentoActualizado.setUbicacion(departamento.getUbicacion());
			
			
			
			servicio.save(departamentoActualizado);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar actualización.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El departamento fue actualizado con éxito.");
		response.put("departamento", departamentoActualizado);
		
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
		
	}
	
	
	
	@DeleteMapping("/departamento/{id}")
	public ResponseEntity<?> eliminarDepartamento(@PathVariable Long id) {
				
		Departamento departamento=servicio.findById(id);
		Map<String,Object> response=new HashMap<>();
		
		try {
			servicio.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(departamento==null) {
			response.put("mensaje", "El departamento id:".concat(id.toString().concat(" no existe en la BD.")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		response.put("mensaje", "Eliminado con éxito.");
		response.put("departamento", departamento);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}
