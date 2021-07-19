package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Ciudad;
import co.edu.uniquindio.resonance.repositorios.CiudadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadServicioImpl implements CiudadServicio{

    @Autowired
    private CiudadRepo ciudadRepo;

    @Override
    public Ciudad registrarCiudad(Ciudad ciudad) throws Exception {
        Optional<Ciudad> buscado = ciudadRepo.findById(ciudad.getCodigo());
        if(buscado.isPresent()){
            throw new Exception("Ya existe una ciudad con ese codigo!");
        }
        if(ciudad.getNombre().length()>50){
            throw new Exception("Debe ingresar un nombre con menos de 50 caracteres!");
        }



        return ciudadRepo.save(ciudad);
    }

    @Override
    public void eliminarCiudad(Ciudad ciudad) throws Exception {

    }

    @Override
    public Ciudad actualizarCiudad(Ciudad ciudad) throws Exception {
        return null;
    }

    @Override
    public List<Ciudad> listarCiudades() {
        return ciudadRepo.findAll();
    }

    @Override
    public boolean existenCiudades(){
        if(listarCiudades().size()>0 ){
            return true;
        }

        return false;
    }

    @Override
    public Ciudad obtenerCiudad(int codigo) {
        Ciudad c = ciudadRepo.findById(codigo).get();
        return c;
    }
}
