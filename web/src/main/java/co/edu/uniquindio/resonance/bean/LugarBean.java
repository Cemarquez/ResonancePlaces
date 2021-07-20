package co.edu.uniquindio.resonance.bean;

import co.edu.uniquindio.resonance.entidades.*;
import co.edu.uniquindio.resonance.servicios.CategoriaServicio;
import co.edu.uniquindio.resonance.servicios.CiudadServicio;
import co.edu.uniquindio.resonance.servicios.LugarServicio;
import co.edu.uniquindio.resonance.servicios.UsuarioServicio;
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
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class LugarBean {
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Getter @Setter
    private Usuario usuario;

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

    @Getter @Setter
    private List<Categoria> categorias;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private List <Lugar> lugares;

    @Value("${upload.url}")
    private String urlImagenes;

    private ArrayList<String> imagenes;

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

    }

    public String registrarLugar(){
        try {
            if(lugar.getLatitud() != 0 || lugar.getLongitud() != 0 ){
                if(!imagenes.isEmpty()){

                    lugar.setFoto(imagenes);
                    lugarServicio.registrarLugar(lugar);
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta",
                            "Registro exitoso");
                    FacesContext.getCurrentInstance().addMessage(null, facesMsg);
                }else{
                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta",
                            "Es necesario subir imagenes del sitio");
                    FacesContext.getCurrentInstance().addMessage(null, facesMsg);
                }


            }else{
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta",
                        "Es necesario ubicar el lugar dentro del mapa");
                FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            }

        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta",
                    e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }

        return null;
    }


    public void subirImagenes(FileUploadEvent event)
    {
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
