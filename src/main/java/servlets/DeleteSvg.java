package servlets;

import HTTPeXist.HTTPeXist;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

public class DeleteSvg extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final HTTPeXist eXist;

    public DeleteSvg() {
        super();
        System.out.println("---> Entrando en init() DeleteSvg");
        eXist = new HTTPeXist("http://localHost:8080");
        System.out.println("---> Saliendo de init() DeleteSvg");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("---> Entrando en doGet() DeleteSvg");

        String collection = request.getParameter("collection");
        String resourceName = request.getParameter("svgName");
        int status = eXist.delete(collection, resourceName);

        if (status == 200) {
            request.setAttribute("informacion", collection+" kolekzioaren "+resourceName+" fitxategia ondo ezabatu da");
        } else {
            request.setAttribute("informacion", "Arazo bat egon da fitxategia ezabatzen");
        }

        System.out.println("\tRedirecting the user to index.jsp  ---> DeleteSvg status code: "+status);

        RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        rd.forward(request, response);

        System.out.println("---> Saliendo de doGet() DeleteSvg");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
