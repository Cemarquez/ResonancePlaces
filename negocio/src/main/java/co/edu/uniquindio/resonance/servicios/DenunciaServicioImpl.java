package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Denuncia;
import co.edu.uniquindio.resonance.repositorios.DenunciaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DenunciaServicioImpl implements DenunciaServicio{

    @Autowired
    DenunciaRepo denunciaRepo;


    @Override
    public List<Denuncia> obtenerDenunciasSinAprobar() {





        return denunciaRepo.obtenerDenunciasSinAprobar();
    }
}
