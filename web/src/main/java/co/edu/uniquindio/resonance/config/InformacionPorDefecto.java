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
            Categoria categoria2 = new Categoria("Cafeteria","Categoria reservada a las cafeterias");
            Categoria categoria3 = new Categoria("Bar","Categoria resevada a los bares");
            Categoria categoria4 = new Categoria("Discoteca","Categoría resevada a las discotecas");
            Categoria categoria5 = new Categoria("Cine","Categoría resevada a los cines");
            Categoria categoria6 = new Categoria("Centro comercial","Categoría resevada a los centros comerciales");
            Categoria categoria7 = new Categoria("Miscelanea","Categoría resevada a las tiendas, papelerias");
            Categoria categoria8 = new Categoria("Museo","Categoría resevada a los museos");
            Categoria categoria9 = new Categoria("Super mercado","Categoría resevada a los super mercados");
            Categoria categoria10 = new Categoria("Lugares de interes","Categoría resevada a los lugares varios");

            categoriaServicio.registrarCategoria(categoria);
            categoriaServicio.registrarCategoria(categoria1);
            categoriaServicio.registrarCategoria(categoria2);
            categoriaServicio.registrarCategoria(categoria3);
            categoriaServicio.registrarCategoria(categoria4);
            categoriaServicio.registrarCategoria(categoria5);
            categoriaServicio.registrarCategoria(categoria6);
            categoriaServicio.registrarCategoria(categoria7);
            categoriaServicio.registrarCategoria(categoria8);
            categoriaServicio.registrarCategoria(categoria9);
            categoriaServicio.registrarCategoria(categoria10);

            if (lugarServicio.listarLugares().size()==0){
                Usuario usuario = new Usuario("user", "user", "cemarquezz29@gmail.com", "user");
                usuarioServicio.registrarUsuario(usuario);
                Usuario usuario1 = new Usuario("Osaka", "Esteban", "esteban123@gmail.com", "123456");
                usuarioServicio.registrarUsuario(usuario1);
                Usuario usuario2= new Usuario("Ryan", "Brian", "briasn.123@gmail.com", "123456");
                usuarioServicio.registrarUsuario(usuario2);
                Usuario usuario3= new Usuario("Dash", "Miguel", "miguel12353@gmail.com", "123456");
                usuarioServicio.registrarUsuario(usuario3);
                Usuario usuario4= new Usuario("Manu", "Manuela", "manuelasd1231@gmail.com", "123456");
                usuarioServicio.registrarUsuario(usuario4);
                Usuario usuario5= new Usuario("Sebancho", "Sebastian", "sebastic123@gmail.com", "123456");
                usuarioServicio.registrarUsuario(usuario5);

                Lugar lugar = new Lugar(categoria,"Un genocidio que da gusto", "Mussolinis pizza");
                lugar.setLatitud(4.553806232733308);
                lugar.setLongitud(-75.65548385940279);
                lugar.getFoto().add("porDefecto.jpg");
                lugar.setCiudad(ciudad);
                lugar.setUsuario(usuario);


                Lugar lugar2 = new Lugar(categoria1,"Un hostal  pequeño para los tacaños", "Pobretel");
                lugar2.setLatitud(4.5234806232733308);
                lugar2.setLongitud(-75.33458385940279);
                lugar2.getFoto().add("porDefecto.jpg");
                lugar2.setCiudad(ciudad1);
                lugar2.setUsuario(usuario1);

                Lugar lugar3 = new Lugar(categoria2,"Maid cafe", "Cafeteria uwu");
                lugar3.setLatitud(4.5234806232733308);
                lugar3.setLongitud(-75.33458385940279);
                lugar3.getFoto().add("cafeteria.jpg");
                lugar3.setCiudad(ciudad2);
                lugar3.setUsuario(usuario2);

                Lugar lugar4 = new Lugar(categoria3,"Bar rock", "Bar rockefeller");
                lugar4.setLatitud(4.5234806232733308);
                lugar4.setLongitud(-75.33458385940279);
                lugar4.getFoto().add("bar.jpg");
                lugar4.setCiudad(ciudad3);
                lugar4.setUsuario(usuario3);

                Lugar lugar5 = new Lugar(categoria4,"Shango night club", "Discoteca shan-go ");
                lugar5.setLatitud(4.5234806232733308);
                lugar5.setLongitud(-75.33458385940279);
                lugar5.getFoto().add("disco.jpg");
                lugar5.setCiudad(ciudad4);
                lugar5.setUsuario(usuario4);

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
