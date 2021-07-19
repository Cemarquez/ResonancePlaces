package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Categoria;
import co.edu.uniquindio.resonance.repositorios.CategoriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServicioImpl implements  CategoriaServicio{

    @Autowired
    private CategoriaRepo categoriaRepo;

    @Override
    public Categoria registrarCategoria(Categoria categoria) throws Exception {
        return null;
    }

    @Override
    public void eliminarCategoria(Categoria categoria) throws Exception {

    }

    @Override
    public Categoria actualizarCategoria(Categoria categoria) throws Exception {
        return null;
    }

    @Override
    public List<Categoria> listarCategorias() {
        return null;
    }

    @Override
    public Categoria obtenerCategoria(int codigo) {
        Categoria categoria = categoriaRepo.findById(codigo).get();

        return categoria;
    }
}
