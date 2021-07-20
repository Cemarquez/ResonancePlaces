package co.edu.uniquindio.resonance.config;

import co.edu.uniquindio.resonance.entidades.*;
import co.edu.uniquindio.resonance.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        }

        if (categoriaServicio.listarCategorias().size()==0){

            Categoria categoria = new Categoria("Restaurante", "Categoria dedicada a sitios de comida, cafes, etc");
            Categoria categoria1 = new Categoria("Hotel", "Categoria reservada a hospedajes hoteleros");

            categoriaServicio.registrarCategoria(categoria);
            categoriaServicio.registrarCategoria(categoria1);

            if (lugarServicio.listarLugares().size()==0){

                Lugar lugar = new Lugar(categoria,"Un genocidio que da gusto", "Mussolinis pizza");
                lugar.setLatitud(4.553806232733308);
                lugar.setLongitud(-75.65548385940279);
                Lugar lugar2 = new Lugar(categoria1,"Un hostal  pequeño para los tacaños", "Pobretel");
                lugar2.setLatitud(4.553806232733308);
                lugar2.setLongitud(-75.65548385940279);
                lugarServicio.registrarLugar(lugar);
                lugarServicio.registrarLugar(lugar2);

                Usuario usuario = new Usuario("elFrentes", "Carlos", "caflores@gmail.com", "popololo352");
                usuarioServicio.registrarUsuario(usuario);

                Calificacion calificacion = new Calificacion(1,"Brutal","Soy bipolar", usuario,lugar);
                Calificacion calificacion2 = new Calificacion(4,"Nada mal","Buenos precios", usuario,lugar2);

                lugarServicio.crearCalificacion(calificacion);
                lugarServicio.crearCalificacion(calificacion2);

            }

        }














    }
}
