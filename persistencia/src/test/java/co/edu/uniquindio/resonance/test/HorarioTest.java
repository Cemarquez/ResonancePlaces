package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.entidades.Horario;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.repositorios.HorarioRepo;
import co.edu.uniquindio.resonance.repositorios.LugarRepo;
import javassist.Loader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Clase Test para la entidad horario
 * @author Brian Giraldo - Cesar Marquez - Esteban Sanchez
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HorarioTest {

    /**
     * Repositorio de la entidad horario
     */
    @Autowired
    private HorarioRepo horarioRepo;
    /**
     * Repositorio de la entidad lugar
     */
    @Autowired
    private LugarRepo lugarRepo;


    /**
     * Método que permite registrar un horario en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void registrarHorario(){

        Lugar lugar = new Lugar();
        lugar.setCodigo(1);
        lugar.setDescripcion("Mejor lugar de comidas rapidas");
        lugar.setNombre("Chorimburger");
        lugar.setEstado(false);

        Lugar lugarGuardado = lugarRepo.save(lugar);
        Assertions.assertNotNull(lugarGuardado);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        try {
            Date horaInicio = sdf.parse("07:00");
            Date horaFin = sdf.parse("22:00");

            Horario horario = new Horario("Horario entre semana",horaInicio,horaFin,false,lugarGuardado);

            Horario horarioGuardado = horarioRepo.save(horario);
            Assertions.assertNotNull(horarioGuardado);



        }

        catch (Exception e){
            e.printStackTrace();
        }



    }
    /**
     * Método que permite eliminar un horario en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void eliminarHorario(){

        Lugar lugar = new Lugar();
        lugar.setCodigo(1);
        lugar.setDescripcion("Mejor lugar de comidas rapidas");
        lugar.setNombre("Chorimburger");
        lugar.setEstado(false);

        Lugar lugarGuardado = lugarRepo.save(lugar);
        Assertions.assertNotNull(lugarGuardado);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        try {
            Date horaInicio = sdf.parse("07:00");
            Date horaFin = sdf.parse("22:00");

            Horario horario = new Horario("Horario entre semana",horaInicio,horaFin,false,lugarGuardado);
            Horario horarioGuardado = horarioRepo.save(horario);
            horarioRepo.delete(horarioGuardado);
            Horario buscado = horarioRepo.findById(1).orElse(null);
            Assertions.assertNull(buscado);



        }

        catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Método que permite actualizar un horario en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void actualizarHorario(){

        Lugar lugar = new Lugar();
        lugar.setCodigo(1);
        lugar.setDescripcion("Mejor lugar de comidas rapidas");
        lugar.setNombre("Chorimburger");
        lugar.setEstado(false);

        Lugar lugarGuardado = lugarRepo.save(lugar);
        Assertions.assertNotNull(lugarGuardado);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        try {
            Date horaInicio = sdf.parse("07:00");
            Date horaFin = sdf.parse("22:00");

            Horario horario = new Horario("Horario entre semana",horaInicio,horaFin,false,lugarGuardado);
            Horario horarioGuardado = horarioRepo.save(horario);

            horarioGuardado.setDescripcion("Horario festivo");

            horarioRepo.save(horarioGuardado);
            Horario buscado = horarioRepo.findById(horarioGuardado.getCodigo()).orElse(null);
            Assertions.assertEquals("Horario festivo",buscado.getDescripcion());



        }

        catch (Exception e){
            e.printStackTrace();
        }

    }
    /**
     * Método que permite listar horario en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    public void listarHorarios(){
        List<Horario> lista = horarioRepo.findAll();

        System.out.println(lista);
    }

    /**
     * Método que permite listar  horario en la base de datos en forma de test para verificar su correcto funcionamiento
     */
    @Test
    @Sql({"classpath:categorias.sql", "classpath:ubicaciones.sql", "classpath:usuarios.sql","classpath:administradores.sql","classpath:moderadores.sql", "classpath:ciudades.sql","classpath:lugares.sql", "classpath:horarios.sql"})
    public void listarHorariosSQL(){
        List<Horario> lista = horarioRepo.findAll();

        System.out.println(lista.get(0).getHoraInicio());

    }
}
