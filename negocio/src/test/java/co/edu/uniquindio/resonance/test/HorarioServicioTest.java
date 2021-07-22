package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.NegocioApplication;
import co.edu.uniquindio.resonance.entidades.Horario;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.repositorios.HorarioRepo;
import co.edu.uniquindio.resonance.repositorios.LugarRepo;
import co.edu.uniquindio.resonance.servicios.HorarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * Clase Test para HorarioServicio
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class HorarioServicioTest {
    @Autowired
    private LugarRepo lugarRepo;

    @Autowired
    private HorarioRepo horarioRepo;


}
