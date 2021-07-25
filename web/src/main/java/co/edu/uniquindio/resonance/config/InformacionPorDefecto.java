package co.edu.uniquindio.resonance.config;

import co.edu.uniquindio.resonance.bean.ModeradorBean;
import co.edu.uniquindio.resonance.entidades.*;
import co.edu.uniquindio.resonance.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Component
public class InformacionPorDefecto implements CommandLineRunner {

    @Autowired
    private CiudadServicio ciudadServicio;
    @Autowired
    private CategoriaServicio categoriaServicio;
    @Autowired
    private LugarServicio lugarServicio;
    @Autowired
    private CalificacionServicio calificacionServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;
    //new ws
    @Autowired
    private AdministradorServicio administradorServicio;
    @Autowired
    private ModeradorServicio moderadorServicio;

    @Autowired
    private HorarioServicio horarioServicio;




    @Override
    public void run(String... args) throws Exception {

        if(!ciudadServicio.existenCiudades()) {
            Ciudad ciudad = new Ciudad("Armenia");
            ciudadServicio.registrarCiudad(ciudad);
            Ciudad ciudad1 = new Ciudad("Calarca");
            ciudadServicio.registrarCiudad(ciudad1);
            Ciudad ciudad2 = new Ciudad("Circasia");
            ciudadServicio.registrarCiudad(ciudad2);
            Ciudad ciudad3 = new Ciudad( "La Tebaida");
            ciudadServicio.registrarCiudad(ciudad3);
            Ciudad ciudad4 = new Ciudad("Sevilla");
            ciudadServicio.registrarCiudad(ciudad4);


            Categoria categoria = new Categoria("Restaurante", "Categoria dedicada a sitios de comida, cafes, etc");
            Categoria categoria1 = new Categoria("Hotel", "Categoria reservada a hospedajes hoteleros");

            categoriaServicio.registrarCategoria(categoria);
            categoriaServicio.registrarCategoria(categoria1);

            if (lugarServicio.listarLugares().size()==0){
                Usuario usuario = new Usuario("user", "Carlos", "caflores@gmail.com", "user");
                usuarioServicio.registrarUsuario(usuario);

                Lugar lugar = new Lugar(categoria,"Un genocidio que da gusto", "Mussolinis pizza");
                lugar.setLatitud(4.553806232733308);
                lugar.setLongitud(-75.65548385940279);
                Lugar lugar2 = new Lugar(categoria1,"Un hostal  pequeño para los tacaños", "Pobretel");
                lugar2.setLatitud(4.553806232733308);
                lugar2.setLongitud(-75.65548385940279);
                lugar.getFoto().add("vacio.png");
                lugar2.getFoto().add("vacio.png");
                lugar.setCiudad(ciudad);
                lugar2.setCiudad(ciudad1);
                lugar.setUsuario(usuario);
                lugar2.setUsuario(usuario);
                lugarServicio.registrarLugar(lugar);
                lugarServicio.registrarLugar(lugar2);

                DateTimeFormatter formatter =
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S", Locale.US);

                String text1 = "2011-02-18 08:30:00.0";
                String text2 = "2011-02-18 22:30:00.0";
                LocalDateTime localDateTime = LocalDateTime.parse(text1, formatter);
                LocalTime local = localDateTime.toLocalTime();

                LocalDateTime localDateTime2 = LocalDateTime.parse(text2, formatter);
                LocalTime local2 = localDateTime2.toLocalTime();
                Horario h = new Horario("Lunes", local, local2, false, lugar);
                Horario h2 = new Horario("Domingo", local, local2, false, lugar);
                horarioServicio.registrarHorario(h);
                horarioServicio.registrarHorario(h2);

                //new es
                Administrador admin = new Administrador("admin","admin","admin@gmail.com","admin");
                administradorServicio.registrarAdministrador(admin);
                Moderador mod = new Moderador("mod","mod","mod@gmail.com","mod");
                mod.setAdministrador(admin);
                moderadorServicio.registarModerador(mod);




                Calificacion calificacion = new Calificacion(1,"Brutal","Soy bipolar", usuario,lugar);
                Calificacion calificacion2 = new Calificacion(4,"Nada mal","Buenos precios", usuario,lugar2);

                lugarServicio.crearCalificacion(calificacion);
                lugarServicio.crearCalificacion(calificacion2);



        }
        }


    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

}
