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

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HorarioTest {

    @Autowired
    private HorarioRepo horarioRepo;
    @Autowired
    private LugarRepo lugarRepo;


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

    @Test
    public void listarHorarios(){
        List<Horario> lista = horarioRepo.findAll();

        System.out.println(lista);
    }

    @Sql
    @Sql({"classpath:horarios.sql,","classpath:lugares.sql"})
    public void listarHorariosSQL(){
        List<Horario> lista = horarioRepo.findAll();

        System.out.println(lista.get(0).getDescripcion());

    }
}
