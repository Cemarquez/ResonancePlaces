package co.edu.uniquindio.resonance.config;

import co.edu.uniquindio.resonance.entidades.Categoria;
import co.edu.uniquindio.resonance.entidades.Ciudad;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.servicios.CategoriaServicio;
import co.edu.uniquindio.resonance.servicios.CiudadServicio;
import co.edu.uniquindio.resonance.servicios.LugarServicio;
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
                Lugar lugar2 = new Lugar(categoria1,"Un hostal  pequeño para los tacaños", "Pobretel");
                lugarServicio.registrarLugar(lugar);
                lugarServicio.registrarLugar(lugar2);


            }

        }














    }
}
