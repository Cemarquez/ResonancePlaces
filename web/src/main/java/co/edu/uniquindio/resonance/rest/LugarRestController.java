package co.edu.uniquindio.resonance.rest;

import co.edu.uniquindio.resonance.dto.Mensaje;
import co.edu.uniquindio.resonance.entidades.*;
import co.edu.uniquindio.resonance.repositorios.DenunciaRepo;
import co.edu.uniquindio.resonance.servicios.DenunciaServicio;
import co.edu.uniquindio.resonance.servicios.LugarServicio;
import co.edu.uniquindio.resonance.servicios.ModeradorServicio;
import co.edu.uniquindio.resonance.servicios.UsuarioServicio;
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

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private DenunciaServicio denunciaServicio;

    @Autowired
    private ModeradorServicio moderadorServicio;


    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Lugar lugar) throws Exception {
        try {
            lugarServicio.registrarLugar(lugar);
            return ResponseEntity.status(201).body(new Mensaje("Lugar creado correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> eliminar(@PathVariable(name = "codigo") Integer codigo) {

        try {
            lugarServicio.eliminarLugar(lugarServicio.obtenerLugar(codigo));
            return ResponseEntity.status(200).body(new Mensaje("Lugar eliminado correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody  Lugar lugar) {
        try {
            lugarServicio.actualizarLugar(lugar);
            return ResponseEntity.status(200).body(new Mensaje("Lugar actualizado correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }

    }

    @GetMapping
    public List<Lugar> listar() {
        return lugarServicio.listarLugares();
    }


    @GetMapping("/busqueda/{parametro}")
    public List<Lugar> buscar(@PathVariable(name = "parametro") String parametro) {
        return lugarServicio.buscarLugares(parametro);
    }


    @GetMapping("/{codigo}")
    public ResponseEntity<?> obtener( @PathVariable(name = "codigo") Integer codigo ){
        try {
           return ResponseEntity.status(200).body(lugarServicio.obtenerLugar(codigo));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PostMapping("/reserva/")
    public ResponseEntity<?> registrarReserva(@RequestBody Reserva reserva) {
        try {
            usuarioServicio.registrarReserva(reserva);
            return ResponseEntity.status(201).body(new Mensaje("La reserva ha sido registrada correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }


    }

    @DeleteMapping("/reserva/{id}")
    public ResponseEntity<?> eliminarReserva(@PathVariable(name = "id") Integer id) {
        try {
            usuarioServicio.eliminarReserva(id);
            return ResponseEntity.status(200).body(new Mensaje("Reserva cancelada correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/reserva/{nickname}")
    public ResponseEntity<?> obtenerReservas(@PathVariable(name = "nickname") String nickname) {
        try {
            return ResponseEntity.status(201).body(usuarioServicio.obtenerReservas(nickname));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PostMapping("/denuncia/")
    public ResponseEntity<?> registrarDenuncia(@RequestBody Denuncia denuncia) {
        try {
            usuarioServicio.registrarDenuncia(denuncia);
            return ResponseEntity.status(201).body(new Mensaje("La denuncia ha sido registrada correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @GetMapping("/denuncia/")
    public List<Denuncia> obtenerDenunciasSinAprobar() {
        return denunciaServicio.obtenerDenunciasSinAprobar();
    }

    @PutMapping("/denuncia/aprobar/{id}")
    public ResponseEntity<?> aprobarDenuncia(@PathVariable(name = "id") Integer id) {
        try {
            moderadorServicio.aprobarDenuncia(denunciaServicio.obtenerDenunciaID(id));
            return ResponseEntity.status(201).body(new Mensaje("La denuncia ha sido aprobada correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PutMapping("/denuncia/rechazar/{id}")
    public ResponseEntity<?> rechazarDenuncia(@PathVariable(name = "id") Integer id) {
        try {
            moderadorServicio.rechazarDenuncia(denunciaServicio.obtenerDenunciaID(id));
            return ResponseEntity.status(201).body(new Mensaje("La denuncia ha sido rechazada correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }
}
