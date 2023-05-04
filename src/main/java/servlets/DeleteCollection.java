package servlets;

import HTTPeXist.HTTPeXist;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

public class DeleteCollection extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final HTTPeXist eXist;

    public DeleteCollection() {
        super();
        System.out.println("---> Entrando en init() DeleteCollection");
        eXist = new HTTPeXist("http://localHost:8080");
        System.out.println("---> Saliendo de init() DeleteCollection");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("---> Entrando en doGet() DeleteCollection");

        String collection = request.getParameter("collection");
        int status = eXist.delete(collection);

        if (status == 200) {
            request.setAttribute("informacion", "Kolekzioa "+collection+" ondo ezabatu da");
        } else {
            request.setAttribute("informacion", "Arazo bat egon da kolekzioa ezabatzen");
        }

        System.out.println("\tRedirecting the user to index.jsp  ---> DeleteCollection status code: "+status);

        RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        rd.forward(request, response);

        System.out.println("---> Saliendo de doGet() DeleteCollection");
    }
}
