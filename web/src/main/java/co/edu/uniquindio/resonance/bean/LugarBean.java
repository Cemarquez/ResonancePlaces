package co.edu.uniquindio.resonance.bean;

import co.edu.uniquindio.resonance.entidades.*;
import co.edu.uniquindio.resonance.servicios.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@ViewScoped
public class LugarBean {
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Getter @Setter
    private Usuario usuario;
    @Value(value ="#{seguridadBean.usuario}")
    private Usuario usuarioLogin;

    @Autowired
    private LugarServicio lugarServicio;

    @Getter @Setter
    private Lugar lugar;

    @Autowired
    private CategoriaServicio categoriaServicio;

    @Getter @Setter
    private Categoria categoria;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Getter @Setter
    private Ciudad ciudad;

    @Autowired
    private HorarioServicio horarioServicio;

    @Getter @Setter
    private List<Categoria> categorias;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private List <Lugar> lugares;

    @Value("${upload.url}")
    private String urlImagenes;

    private ArrayList<String> imagenes;


    @Setter @Getter
    private Horario horarioLunes;

    @Setter @Getter
    private Horario horarioMartes;

    @Setter @Getter
    private Horario horarioMiercoles;

    @Setter @Getter
    private Horario horarioJueves;

    @Setter @Getter
    private Horario horarioViernes;

    @Setter @Getter
    private Horario horarioSabado;

    @Setter @Getter
    private Horario horarioDomingo;

    @Getter @Setter
    private List<Horario> horarios;

    @Getter @Setter
    private ArrayList<Horario> abierto;


    @PostConstruct
    public void inicializar(){
        this.lugar = new Lugar();
        this.usuario = new Usuario();
        this.categoria = new Categoria();
        this.imagenes = new ArrayList<>();
        this.ciudad = new Ciudad();
        this.categorias = categoriaServicio.listarCategorias();
        this.ciudades = ciudadServicio.listarCiudades();
        this.lugares = lugarServicio.listarLugares();
        this.horarios = new ArrayList<>();
        this.horarioLunes = new Horario("Lunes", false);
        this.horarioMartes = new Horario("Martes", false);
        this.horarioMiercoles = new Horario("Miercoles", false);
        this.horarioJueves = new Horario("Jueves", false);
        this.horarioViernes = new Horario("Viernes", false);
        this.horarioSabado = new Horario("Sabado", false);
        this.horarioDomingo = new Horario("Domingo", false);
        this.abierto = new ArrayList<>();
        horarios.add(horarioLunes);
        horarios.add(horarioMartes);
        horarios.add(horarioMiercoles);
        horarios.add(horarioJueves);
        horarios.add(horarioViernes);
        horarios.add(horarioSabado);
        horarios.add(horarioDomingo);

    }

    public String registrarLugar(){
        if(usuarioLogin!=null) {
            try {
                if (lugar.getLatitud() != 0 || lugar.getLongitud() != 0) {
                    if (!imagenes.isEmpty()) {
                        lugar.setUsuario(usuarioLogin);
                        lugar.setFoto(imagenes);
                        lugarServicio.registrarLugar(lugar);
                        registrarHorarios();
                        return "/usuario/perfil?faces-redirect=true";
                    } else {
                        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta",
                                "Es necesario subir imagenes del sitio");
                        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
                    }


                } else {
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta",
                            "Es necesario ubicar el lugar dentro del mapa");
                    FacesContext.getCurrentInstance().addMessage(null, facesMsg);
                }

            } catch (Exception e) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta",
                        e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            }

        }
        return null;
    }

    public void registrarHorarios() throws Exception {
            for(int i = 0; i<horarios.size(); i++){
                Horario h = horarios.get(i);
                h.setLugar(lugar);
                if(!h.isCerrado()){
                 abierto.add(h);
                }
            }

            for(Horario h : abierto) {
               horarioServicio.registrarHorario(h);
            }
    }

    public void subirImagenes(FileUploadEvent event) {
        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);
        if(nombreImagen!= null)
        {

            imagenes.add(nombreImagen);

        }
    }

    public String subirImagen(UploadedFile file)
    {
        try {
            InputStream input = file.getInputStream();
            String filename = FilenameUtils.getName(file.getFileName());
            String basename = FilenameUtils.getBaseName(filename) + "_";
            String extension = "." + FilenameUtils.getExtension(filename);
            File fileDest = File.createTempFile(basename, extension, new File(urlImagenes));
            FileOutputStream output = null;
            output = new FileOutputStream(fileDest);
            IOUtils.copy(input, output);
            return fileDest.getName();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

}
