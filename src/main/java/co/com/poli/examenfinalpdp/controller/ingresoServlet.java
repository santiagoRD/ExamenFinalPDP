/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.poli.examenfinalpdp.controller;

import co.com.poli.examenfinalpdp.business.implementation.CursoBusinessImpl;
import co.com.poli.examenfinalpdp.business.implementation.InscripcionesBusinessImpl;
import co.com.poli.examenfinalpdp.model.Tblcurso;
import co.com.poli.examenfinalpdp.model.Tblinscripciones;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SANTIAGO
 */
public class ingresoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        RequestDispatcher rd = null;
        InscripcionesBusinessImpl inscripcionesBusinessImpl = new InscripcionesBusinessImpl();
        CursoBusinessImpl cursoBusinessImpl = new CursoBusinessImpl();

        String accion = request.getParameter("accion");

        Tblinscripciones inscripciones;
        Tblcurso curso;

        switch (accion) {
            case "inscribir":
                Boolean est = false;
                String idinscripcion = request.getParameter("txtidinscrip");
                String documento = request.getParameter("txtdoc");
                String idCurso = request.getParameter("txtcurso");
                double estrato = Integer.parseInt(request.getParameter("txtestrato"));
                est = inscripcionesBusinessImpl.validarEstrato(estrato);
                if (est) {
                    curso = cursoBusinessImpl.obtenerCurso(idCurso);
                    if (curso == null) {
                        String mensaje = "Curso no existente";
                        session.setAttribute("MENSAJE", mensaje);
                        rd = request.getRequestDispatcher("mensaje.jsp");
                    } else {
                        inscripciones = new Tblinscripciones(idinscripcion, documento, estrato, curso);
                        inscripcionesBusinessImpl.crearInscripcion(inscripciones);
                        String mensaje = "Inscripcion exitosa";
                        session.setAttribute("MENSAJE", mensaje);
                        rd = request.getRequestDispatcher("mensaje.jsp");
                    }
                } else {
                    String mensaje = "Estrato Incorrecto";
                    session.setAttribute("MENSAJE", mensaje);
                    rd = request.getRequestDispatcher("mensaje.jsp");
                }

                break;

            case "registrar":
                Boolean sw = false;
                String idmateria = request.getParameter("txtidmateria");
                String nombre = request.getParameter("txtnombre");
                double nummCreditos = Double.parseDouble(request.getParameter("txtnumcred"));
                int cupoMax = Integer.parseInt(request.getParameter("txtcupomax"));
                int cupoMin = Integer.parseInt(request.getParameter("txtcupomin"));

                curso = new Tblcurso(idmateria, nombre, nummCreditos, cupoMax, cupoMin);

                sw = cursoBusinessImpl.crearCurso(curso);
                if (sw == false) {
                    String mensaje = "Curso Creado";
                    session.setAttribute("MENSAJE", mensaje);
                    rd = request.getRequestDispatcher("mensaje.jsp");
                } else {
                    String mensaje = "Error";
                    session.setAttribute("MENSAJE", mensaje);
                    rd = request.getRequestDispatcher("mensaje.jsp");
                }

                break;
        }
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
