package co.edu.uniquindio.resonance.servicios;

import co.edu.uniquindio.resonance.entidades.Reporte;

import java.util.List;

public interface ReporteServicio {

    Reporte registrarReporte(Reporte reporte) throws Exception;
    void eliminarReporte(Reporte reporte) throws Exception;
    Reporte actualizarReporte(Reporte reporte) throws Exception;
    List<Reporte> listarReportes();
}
