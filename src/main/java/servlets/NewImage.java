package servlets;

import HTTPeXist.HTTPeXist;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

public class NewImage extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final HTTPeXist eXist;

    public NewImage() {
        super();
        System.out.println("---> Entrando en init()de listResource");
        eXist = new HTTPeXist("http://localHost:8080");
        System.out.println("---> Saliendo de init()de LoginServlet");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("---> Entrando en doPost() de NewImage");

        String collection = request.getParameter("collection");
        String svgName = request.getParameter("svgName");

        eXist.subir(collection,svgName);
        eXist.subirString(collection, "<svg>Sartu zure svg</svg>", svgName);

        String imagenSVG= eXist.read(collection, svgName);

        System.out.println("Servlet- DatosXML " + collection);
        System.out.println("Servlet- DatosXML " + svgName);

        request.setAttribute("collection", collection);
        request.setAttribute("svgName", svgName);
        request.setAttribute("imagenSVG", imagenSVG);

        String imagenURI = "http://localhost:8080/exist/rest/db/" + collection + "/" + svgName + "/";
        request.setAttribute("imagenURI", imagenURI);

        System.out.println("     Redireccionando a imagenEdit.jsp");
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/imagenEdit.jsp");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        rd.forward(request, response);

        System.out.println("---> Saliendo de doPost() de NewImage");
    }
}
