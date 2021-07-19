package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Categoria;

import java.util.List;

public interface CategoriaServicio {

    Categoria registrarCategoria(Categoria categoria) throws Exception;
    void eliminarCategoria(Categoria categoria) throws Exception;
    Categoria actualizarCategoria(Categoria categoria) throws Exception;
    List<Categoria> listarCategorias();
    Categoria obtenerCategoria(int codigo);

}
