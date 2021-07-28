package co.edu.uniquindio.resonance.repositorios;

import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.entidades.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservaRepo  extends JpaRepository<Reserva, Integer> {

    @Query("SELECT r FROM Reserva r WHERE r.usuario.nickname = ?1")
    List<Reserva> obtenerReservas(String nickname);



}
