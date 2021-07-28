package co.edu.uniquindio.resonance.filter;

import co.edu.uniquindio.resonance.bean.SeguridadBean;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SeguridadFilter implements Filter {

    public static final String PAGINA_INICIO = "/index.xhtml";
    public static final String PAGINA_MODERADOR ="/moderador/aprobarLugar.xhtml";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        ( (HttpServletResponse) servletResponse).setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        ( (HttpServletResponse) servletResponse).setHeader("Pragma", "no-cache");
        try {
            final HttpServletRequest request = (HttpServletRequest) servletRequest;
            final HttpServletResponse response = (HttpServletResponse) servletResponse;
            final String requestURI = request.getRequestURI();
                //Aplicar el filtro a esta carpeta
            if (requestURI.startsWith("/usuario/") ) {
                //Obtenemos el objeto seguridadBean de la sesión actual
                filtrarUsuario( servletRequest,  servletResponse, filterChain,requestURI,  request,   response) ;

            }else if(requestURI.startsWith("/administrador/")){

                filtrarAdmin( servletRequest,  servletResponse, filterChain,requestURI,  request,   response) ;


            }else if(requestURI.startsWith("/moderador/")){

                filtrarModerador( servletRequest,  servletResponse, filterChain,requestURI,  request,   response) ;


            }else{
                        //La página solicitada no está en la carpeta /usuario entonces el filtro no aplica

                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    public void filtrarAdmin(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain, String requestURI, HttpServletRequest request,  HttpServletResponse response) throws IOException, ServletException {

        //Obtenemos el objeto seguridadBean de la sesión actual
        SeguridadBean userManager = (SeguridadBean)
                request.getSession().getAttribute("seguridadBean");

        if (userManager != null) {
            if (userManager.isAutenticado() && userManager.getRol().equals("admin")) {
                //El usuario está logueado entonces si puede ver la página solicitada

                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                //El usuario no está logueado, entonces se redirecciona al inicio

                response.sendRedirect(request.getContextPath() + PAGINA_INICIO);
            }
        } else {
            //El usuario no está logueado, entonces se redirecciona al inicio
            response.sendRedirect(request.getContextPath() + PAGINA_INICIO);
        }


    }
    public void filtrarModerador(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain, String requestURI, HttpServletRequest request,  HttpServletResponse response) throws IOException, ServletException {

        //Obtenemos el objeto seguridadBean de la sesión actual
        SeguridadBean userManager = (SeguridadBean)
                request.getSession().getAttribute("seguridadBean");

        if (userManager != null) {
            if (userManager.isAutenticado() && userManager.getRol().equals("moderador")) {
                //El usuario está logueado entonces si puede ver la página solicitada

                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                //El usuario no está logueado, entonces se redirecciona al inicio

                response.sendRedirect(request.getContextPath() + PAGINA_INICIO);
            }
        } else {
            //El usuario no está logueado, entonces se redirecciona al inicio
            response.sendRedirect(request.getContextPath() + PAGINA_INICIO);
        }


    }


    public void filtrarUsuario(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain, String requestURI, HttpServletRequest request,  HttpServletResponse response) throws IOException, ServletException {

        //Obtenemos el objeto seguridadBean de la sesión actual
        SeguridadBean userManager = (SeguridadBean)
                request.getSession().getAttribute("seguridadBean");

        if (userManager != null) {
            if (userManager.isAutenticado() && userManager.getRol().equals("usuario")) {
                //El usuario está logueado entonces si puede ver la página solicitada

                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                //El usuario no está logueado, entonces se redirecciona al inicio

                response.sendRedirect(request.getContextPath() + PAGINA_INICIO);
            }
        } else {
            //El usuario no está logueado, entonces se redirecciona al inicio
            response.sendRedirect(request.getContextPath() + PAGINA_INICIO);
        }


    }



}

