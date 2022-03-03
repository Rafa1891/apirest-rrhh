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

import com.formacion.apirestrrhh.entity.Empleado;
import com.formacion.apirestrrhh.service.EmpleadoService;

@RestController
@RequestMapping("/api_rrhh")
public class EmpleadoController {

	@Autowired
	private EmpleadoService servicio;
	
	@GetMapping("/empleados")
	public List<Empleado> index(){
		return servicio.findAll();
	}
	

	@GetMapping("/empleados/{id}")
	public ResponseEntity<?> buscarEmpleadoporId(@PathVariable Long id){
		
		Empleado empleado=null;
		
		Map<String,Object> response=new HashMap<>();
		
		try {
			empleado=  servicio.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(empleado==null) {
			response.put("mensaje", "El empleado id:".concat(id.toString().concat(" no existe en la BD.")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Empleado>(empleado,HttpStatus.OK);
	}
	
	
	@PostMapping("/empleado")
	public ResponseEntity<?> guardarEmpleado(@RequestBody Empleado empleado) {
		
        Empleado empleadoNuevo=null;
		Map<String,Object> response=new HashMap<>();
		
		try {
			empleadoNuevo=servicio.save(empleado);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar inserción.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El empleado fue creado con éxito.");
		response.put("empleado", empleadoNuevo);
		
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	
	@PutMapping("/empleado/{id}")
	public ResponseEntity<?> actualizarEmpleado(@RequestBody Empleado empleado,@PathVariable Long id) {
		
		Empleado empActualizado=servicio.findById(id);
		
		Map<String,Object> response=new HashMap<>();
		
		if(empActualizado==null) {
			response.put("mensaje", "El empleado id:".concat(id.toString().concat(" no existe en la BD.")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
				
		try {
			empActualizado.setId_empleado(id);
			empActualizado.setDNI(empleado.getDNI());
			empActualizado.setNombre(empleado.getNombre());
			empActualizado.setSalario(empleado.getSalario());
			empActualizado.setTelefono(empleado.getTelefono());
			empActualizado.setDepartamento(empleado.getDepartamento());
			
			
			servicio.save(empActualizado);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar actualización.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "EL empleado fue actualizado con éxito.");
		response.put("empleado", empActualizado);
		
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
		
	}
	
	
	
	@DeleteMapping("/empleado/{id}")
	public ResponseEntity<?> eliminarEmpleado(@PathVariable Long id) {
				
		Empleado empleado=servicio.findById(id);
		Map<String,Object> response=new HashMap<>();
		
		try {
			servicio.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(empleado==null) {
			response.put("mensaje", "El producto id:".concat(id.toString().concat(" no existe en la BD.")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		response.put("mensaje", "Eliminado con éxito.");
		response.put("producto", empleado);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}
