package co.edu.uniquindio.resonance.test;

import co.edu.uniquindio.resonance.entidades.Favorito;
import co.edu.uniquindio.resonance.entidades.Usuario;
import co.edu.uniquindio.resonance.repositorios.FavoritoRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FavoritoTest {

    @Autowired
    private FavoritoRepo favoritoRepo;


    @Test
    public void registrarFavoritoTest(){
        Favorito favorito = new Favorito();
        Favorito favoritoRegistrado = favoritoRepo.save(favorito);
        Assertions.assertNotNull(favoritoRegistrado);
    }

    @Test
    public void eliminarFavoritoTest(){
        Favorito favorito = new Favorito();
        Favorito favoritoRegistrado = favoritoRepo.save(favorito);
        favoritoRepo.delete(favoritoRegistrado);
        Favorito buscado = favoritoRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    @Test
    public void actualizarFavoritoTest(){
        /**
         *  Al solo tener la private key como atributo (sin contar las FK) no se puede actualizar
         *  una llave primaria, entonces borro el elemento y creo uno con una llave nueva
         */


        Favorito favorito = new Favorito();
        Favorito favoritoRegistrado = favoritoRepo.save(favorito);
        favoritoRepo.save(favoritoRegistrado);
        favoritoRepo.delete(favoritoRegistrado);

        Favorito favoritoActualizado = new Favorito();
        favoritoActualizado.setCodigo(2);
        Favorito favoritoRegistradoActualizado = favoritoRepo.save(favoritoActualizado);
        favoritoRepo.save(favoritoRegistradoActualizado);
        Assertions.assertNotNull(favoritoRegistradoActualizado);




      //Favorito buscado = favoritoRepo.findById(777).orElse(null);
      // Assertions.assertEquals(777, buscado.getCodigo());

    }

    @Test
    public void listarFavoritos(){
        List<Favorito> lista = favoritoRepo.findAll();

        System.out.println(lista);
    }
}
