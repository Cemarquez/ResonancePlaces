package co.edu.uniquindio.resonance.bean;

import co.edu.uniquindio.resonance.entidades.Administrador;
import co.edu.uniquindio.resonance.entidades.Moderador;
import co.edu.uniquindio.resonance.entidades.Reporte;
import co.edu.uniquindio.resonance.repositorios.Reporte1DTO;
import co.edu.uniquindio.resonance.repositorios.Reporte2DTO;
import co.edu.uniquindio.resonance.repositorios.Reporte3DTO;
import co.edu.uniquindio.resonance.repositorios.Reporte4DTO;
import co.edu.uniquindio.resonance.servicios.AdministradorServicio;
import lombok.Getter;
import lombok.Setter;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;

import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.polar.PolarAreaChartDataSet;
import org.primefaces.model.charts.polar.PolarAreaChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class AdministradorBean {
    @Autowired
    private AdministradorServicio administradorServicio;


    @Getter @Setter
    private Moderador moderador;
    @Value(value = "#{seguridadBean.administrador}")
    @Getter @Setter
    private Administrador administradorLogin;
    @Getter @Setter
    private List<Moderador> moderadores ;
    @Getter @Setter
    private BarChartModel barModel;

    @Getter @Setter
    private PolarAreaChartModel  polarAreaModel;
    @Getter @Setter
    private DonutChartModel donutModel;
    @Getter @Setter
    private PieChartModel pieModel;

    @Getter @Setter
    protected List<Reporte> reportes;

    @Getter @Setter
    private List<Reporte1DTO> reporte1;

    @Getter @Setter
    private List<Reporte2DTO> reporte2;

    @Getter @Setter
    private List<Reporte3DTO> reporte3;

    @Getter @Setter
    private List<Reporte4DTO> reporte4;



    @PostConstruct
    public void inicializar()
    {

        this.moderadores =administradorServicio.listarModeradores(administradorLogin.getNickname());
        this.moderador = new Moderador();
        this.reporte1 = administradorServicio.generarReporte1();
        this.reporte2 = administradorServicio.generarReporte2();
        this.reporte3 = administradorServicio.generarReporte3();
        this.reporte4 = administradorServicio.generarReporte4();
        createBarModel();
        createPolarAreaModel();
        createDonutModel();
        createPieModel();

    }


    public void crearModerador(){
        try {
           administradorServicio.crearModerador(moderador,administradorLogin.getNickname());
            this.moderadores =administradorServicio.listarModeradores(administradorLogin.getNickname());
            moderador = new Moderador();
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta",
                    "Registro exitoso");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta",
                    e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }

    }

    public void eliminarModerador(Moderador mod){

        try {
            administradorServicio.eliminarModerador(administradorLogin.getNickname(),mod);
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta",
                    "Moderador eliminado exitosamente");
            FacesContext.getCurrentInstance().addMessage(null ,facesMsg);
            this.moderadores =administradorServicio.listarModeradores(administradorLogin.getNickname());
        } catch (Exception e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta",
                    e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }

    }

    public void createBarModel() {

        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Reporte 1");

        List<String> labels = new ArrayList<>();

        List<Number> values = new ArrayList<>();
        for (int i=0; i<reporte1.size();i++){

            values.add(reporte1.get(i).getCantidad());
            labels.add(reporte1.get(i).getCategoria());

        }


        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);





        data.setLabels(labels);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setFontSize(16);
        title.setText("Cantidad de lugares creados por categoria ");
        options.setTitle(title);



        Legend legend = new Legend();
        legend.setDisplay(false);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);



        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barModel.setOptions(options);


    }

    private void createPolarAreaModel() {
        polarAreaModel = new PolarAreaChartModel();
        ChartData data = new ChartData();

        PolarAreaChartDataSet dataSet = new PolarAreaChartDataSet();
        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        for (int i=0; i<reporte2.size();i++){

            values.add(reporte2.get(i).getCantidad());
            labels.add(reporte2.get(i).getCiudad());

        }
        dataSet.setData(values);
        data.setLabels(labels);
        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(75, 192, 192)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("rgb(201, 203, 207)");
        bgColors.add("rgb(54, 162, 235)");
        dataSet.setBackgroundColor(bgColors);
        data.addChartDataSet(dataSet);
        polarAreaModel.setData(data);
    }

    public void createDonutModel() {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();

        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        for(int i=0; i<reporte3.size();i++){

            values.add(reporte3.get(i).getCantidad());
            labels.add(reporte3.get(i).getLugar());


        }

        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);


        data.setLabels(labels);

        donutModel.setData(data);
    }

    private void createPieModel() {
        pieModel = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        for (int i=0;i<reporte4.size();i++){

            values.add(reporte4.get(i).getCantidadComentarios());
            labels.add(reporte4.get(i).getLugar());


        }



        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);


        data.setLabels(labels);

        pieModel.setData(data);
    }



}
