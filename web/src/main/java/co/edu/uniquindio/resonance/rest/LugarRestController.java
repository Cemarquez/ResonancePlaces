package co.edu.uniquindio.resonance.rest;

import co.edu.uniquindio.resonance.dto.Mensaje;
import co.edu.uniquindio.resonance.entidades.Calificacion;
import co.edu.uniquindio.resonance.entidades.Favorito;
import co.edu.uniquindio.resonance.entidades.Horario;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.servicios.LugarServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/lugar")
public class LugarRestController {

    @Autowired
    private LugarServicio lugarServicio;


    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Lugar lugar) throws Exception {
        try {
            lugarServicio.registrarLugar(lugar);
            return ResponseEntity.status(201).body(new Mensaje("Usuario creado correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> eliminar(@PathVariable(name = "codigo") Integer codigo) {

        try {
            lugarServicio.eliminarLugar(lugarServicio.obtenerLugar(codigo));
            return ResponseEntity.status(200).body(new Mensaje("Usuario eliminado correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody  Lugar lugar) {
        try {
            lugarServicio.actualizarLugar(lugar);
            return ResponseEntity.status(200).body(new Mensaje("Usuario actualizado correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }

    }

    @GetMapping
    public List<Lugar> listar() {
        return lugarServicio.listarLugares();
    }

    /*
    @GetMapping("/{parametro}")
    public List<Lugar> buscar(@PathVariable(name = "parametro") String parametro) {
        return lugarServicio.buscarLugares(parametro);
    }
         */

    @GetMapping("/{codigo}")
    public ResponseEntity<?> obtener( @PathVariable(name = "codigo") Integer codigo ){
        try {
           return ResponseEntity.status(200).body(lugarServicio.obtenerLugar(codigo));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }
}
