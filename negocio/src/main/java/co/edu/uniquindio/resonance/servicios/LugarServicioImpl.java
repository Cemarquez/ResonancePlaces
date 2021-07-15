package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Lugar;
import co.edu.uniquindio.resonance.repositorios.LugarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LugarServicioImpl implements LugarServicio{

    @Autowired
    private LugarRepo lugarRepo;

    @Override
    public Lugar registrarLugar(Lugar lugar) throws Exception {
        return null;
    }

    @Override
    public void eliminarLugar(Lugar lugar) throws Exception {

    }

    @Override
    public Lugar actualizarLugar(Lugar lugar) throws Exception {
        return null;
    }

    @Override
    public List<Lugar> listarLugares() {
        return null;
    }

    @Override
    public List<Lugar> buscarLugares(String parametro) {

        return lugarRepo.buscarLugares(parametro);
    }
}
