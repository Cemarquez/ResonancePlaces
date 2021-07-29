package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Categoria;
import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.repositorios.CategoriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServicioImpl implements  CategoriaServicio{

    @Autowired
    private CategoriaRepo categoriaRepo;

    @Override
    public Categoria registrarCategoria(Categoria categoria) throws Exception {
        /*
        Categoria buscado = categoriaRepo.findByNombre(categoria.getNombre());
        if(categoria!=null){
            throw new Exception("Ya existe una categoria con este nombre!");
        }

         */

        Categoria guardado = categoriaRepo.save(categoria);
        return guardado;
    }

    @Override
    public void eliminarCategoria(Categoria categoria) throws Exception {
        Categoria buscado = categoriaRepo.findByNombre(categoria.getNombre());
        if(categoria!=null){
            throw new Exception("No existe una categoria con este nombre!");
        }

        categoriaRepo.delete(categoria);
    }

    @Override
    public Categoria actualizarCategoria(Categoria categoria) throws Exception {
        Optional<Categoria> buscado = categoriaRepo.findById(categoria.getCodigo());
        if(!buscado.isPresent()){
            throw new Exception("No existe una categoria con este codigo");
        }

        Categoria guardado = categoriaRepo.save(categoria);
        return guardado;
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepo.findAll();
    }

    @Override
    public Categoria obtenerCategoria(int codigo) {
        Categoria categoria = categoriaRepo.findById(codigo).get();

        return categoria;
    }

    @Override
    public List<Lugar> obtenerLugares(int codigo) {
        return categoriaRepo.obtenerLugares(codigo);
    }
}
