package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.entidades.Horario;
import co.edu.uniquindio.resonance.repositorios.HorarioRepo;
import javassist.Loader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HorarioTest {

    @Autowired
    private HorarioRepo horarioRepo;


    @Test
    public void registrarHorario(){

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        try {
            Date horaInicio = sdf.parse("07:00");
            Date horaFin = sdf.parse("22:00");

            Horario horario = new Horario("Horario entre semana",horaInicio,horaFin,false);
            Horario horarioGuardado = horarioRepo.save(horario);
            Assertions.assertNotNull(horarioGuardado);



        }

        catch (Exception e){
            e.printStackTrace();
        }



    }

    @Test
    public void eliminarHorario(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        try {
            Date horaInicio = sdf.parse("07:00");
            Date horaFin = sdf.parse("22:00");

            Horario horario = new Horario("Horario entre semana",horaInicio,horaFin,false);
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
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        try {
            Date horaInicio = sdf.parse("07:00");
            Date horaFin = sdf.parse("22:00");

            Horario horario = new Horario("Horario entre semana",horaInicio,horaFin,false);
            Horario horarioGuardado = horarioRepo.save(horario);

            horarioGuardado.setDescripcion("Horario festivo");

            horarioRepo.save(horarioGuardado);
            Horario buscado = horarioRepo.findById(1).orElse(null);
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
}
