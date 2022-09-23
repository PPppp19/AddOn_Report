/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.report;

import com.br.connection.ConnectDB2;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author Wattana
 */
public class Report extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Report</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Report at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        HttpSession session = request.getSession(true);

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/pdf");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Connection conn = null;
        try {

            if (request.getParameter("report").contentEquals("MO_Report")) {

                System.out.println("Mo mo    PDF ____________________________");
                conn = ConnectDB2.ConnectionDB();
                String path = getServletContext().getRealPath("/jaspers/");
                System.out.println("path: " + path);
                Map parameters = new HashMap();
//                System.out.println(request.getParameter("facility") + " : " + request.getParameter("warehouse") + " : " + request.getParameter("mono"));
                parameters.put("cono", session.getAttribute("cono"));
                parameters.put("divi", session.getAttribute("divi"));
                parameters.put("fac", request.getParameter("facility"));
                parameters.put("whs", request.getParameter("warehouse"));
                parameters.put("mono", request.getParameter("mono"));
//                parameters.put("imagesDir", path);

                try {

                    OutputStream outStream = response.getOutputStream();
                    System.out.println(path + "MO_Report.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(path + "MO_Report.jrxml");
                    JasperReport subjasperReport1 = JasperCompileManager.compileReport(path + "subMO_Report1.jrxml");
                    JasperReport subjasperReport2 = JasperCompileManager.compileReport(path + "subMO_Report2.jrxml");
                    JasperReport subjasperReport3 = JasperCompileManager.compileReport(path + "subMO_Report3.jrxml");

                    parameters.put("SUBREPORT_DIR1", subjasperReport1);
                    parameters.put("SUBREPORT_DIR2", subjasperReport2);
                    parameters.put("SUBREPORT_DIR3", subjasperReport3);

                    JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameters, conn);
                    JasperExportManager.exportReportToPdfStream(jasp, outStream);

                } catch (Exception ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (request.getParameter("report").contentEquals("MO_Report_Excel")) {
                String path = getServletContext().getRealPath("/jaspers/");
                Map parameters = new HashMap();
                parameters.put("cono", session.getAttribute("cono"));
                parameters.put("divi", session.getAttribute("divi"));
                parameters.put("fac", request.getParameter("facility"));
                parameters.put("whs", request.getParameter("warehouse"));
                parameters.put("mono", request.getParameter("mono"));

                try {

                    System.out.println(path + "MO_Report_Excel.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(path + "MO_Report_Excel.jrxml");
                    JasperReport subjasperReport1 = JasperCompileManager.compileReport(path + "subMO_Report1.jrxml");
                    JasperReport subjasperReport2 = JasperCompileManager.compileReport(path + "subMO_Report2.jrxml");
                    JasperReport subjasperReport3 = JasperCompileManager.compileReport(path + "subMO_Report3.jrxml");

                    parameters.put("SUBREPORT_DIR1", subjasperReport1);
                    parameters.put("SUBREPORT_DIR2", subjasperReport2);
                    parameters.put("SUBREPORT_DIR3", subjasperReport3);

                    try {
                        conn = ConnectDB2.ConnectionDB();
                    } catch (Exception ex) {
                        Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameters, conn);
                    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + "MO_Report_Excel" + ".xlsx" + "\"");
                    JRXlsxExporter exporterXls = new JRXlsxExporter();
                    ServletOutputStream ouputStream = response.getOutputStream();
                    exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, jasp);
                    exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                    exporterXls.exportReport();
                    ouputStream.flush();
                    ouputStream.close();

                } catch (Exception ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 

            /////////////////////// MPM012 ///////////////////////////////////
            
            else if (request.getParameter("report").contentEquals("MPM012")) {

                System.out.println("MPM012   PDF ____________________________");

                String fdate = request.getParameter("fdate").toString().replaceAll("-", "");
                String tdate = request.getParameter("tdate").toString().replaceAll("-", "");
                String warehouse = request.getParameter("warehouse").toString();
                String company = session.getAttribute("cono").toString();
                String  username  =  session.getAttribute("user").toString() ;
//                String[] getCompany = request.getParameter("company").split(" : ");
//                company = getCompany[0];

                System.out.println(fdate + " : " + tdate + " : " + warehouse + " : " + company + " : "+ username);

                conn = ConnectDB2.ConnectionDB();
                String path = getServletContext().getRealPath("/jaspers/");
//                System.out.println("path: " + path);
                Map parameters = new HashMap();

                parameters.put("username", session.getAttribute("username"));
                parameters.put("fdate", fdate);
                parameters.put("tdate", tdate);
                parameters.put("whs", warehouse);
                parameters.put("company", company);


                try {

                    OutputStream outStream = response.getOutputStream();
                    System.out.println(path + "MPM012.jrxml");                   
                    JasperReport jasperReport = JasperCompileManager.compileReport(path + "MPM012.jrxml");

                    JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameters, conn);
                    //JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
                    JasperExportManager.exportReportToPdfStream(jasp, outStream);

                } catch (Exception ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if (request.getParameter("report").contentEquals("MPM012_Excel")) {
                String fdate = request.getParameter("fdate").toString().replaceAll("-", "");
                String tdate = request.getParameter("tdate").toString().replaceAll("-", "");
                String warehouse = request.getParameter("warehouse").toString();
                String company = session.getAttribute("cono").toString();
                String  username  =  session.getAttribute("user").toString() ;
//                String[] getCompany = request.getParameter("company").split(" : ");
//                company = getCompany[0];

                System.out.println(fdate + " : " + tdate + " : " + warehouse + " : " + company + " : "+ username);

                conn = ConnectDB2.ConnectionDB();
                String path = getServletContext().getRealPath("/jaspers/");
//                System.out.println("path: " + path);
                Map parameters = new HashMap();

                parameters.put("username", session.getAttribute("username"));
                parameters.put("fdate", fdate);
                parameters.put("tdate", tdate);
                parameters.put("whs", warehouse);
                parameters.put("company", company);


                JasperDesign JPD;
                try {
                    JPD = JRXmlLoader.load(path + "MPM012_Excel.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(JPD);

                    try {
                        conn = ConnectDB2.ConnectionDB();
                    } catch (Exception ex) {
                        Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameters, conn);
                    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + "MPM012_Excel" + ".xlsx" + "\"");
                    JRXlsxExporter exporterXls = new JRXlsxExporter();
                    ServletOutputStream ouputStream = response.getOutputStream();
                    exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, jasp);
                    exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                    exporterXls.exportReport();
                    ouputStream.flush();
                    ouputStream.close();

                } catch (Exception ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if (request.getParameter("report").contentEquals("MPM024")) {

                System.out.println("MPM024   PDF ____________________________");

                String fac = request.getParameter("fac").toString();
                String mo = request.getParameter("mo").toString();
                String company = session.getAttribute("cono").toString();
//                String[] getCompany = request.getParameter("company").split(" : ");
//                company = getCompany[0];

                System.out.println(fac + " : " + mo + " : " + company);

                conn = ConnectDB2.ConnectionDB();
                String path = getServletContext().getRealPath("/jaspers/");

                Map parameters = new HashMap();

                parameters.put("Fac", fac);
                parameters.put("Mono", mo);
                parameters.put("Company", company);
                
//                parameters.put("Fac", "1A1");
//                parameters.put("Mono", "0012161088");
//                parameters.put("Company", "10");


                try {

                    
                    OutputStream outStream = response.getOutputStream();
                    System.out.println(path + "MPM024.jrxml");
                    System.out.println(path + "sub_report1.jrxml");
                    
                    JasperReport jasperReport = JasperCompileManager.compileReport(path + "MPM024.jrxml");
                    
//                    JasperReport sub_report1 = JasperCompileManager.compileReport(path + "sub_report1.jrxml");

                    JasperReport sub_report1 = JasperCompileManager.compileReport(path + "sub_report.jrxml");
                    JasperReport sub_report2 = JasperCompileManager.compileReport(path + "sub_report2.jrxml");
                    JasperReport sub_report3 = JasperCompileManager.compileReport(path + "sub_report3.jrxml");                  
//
                    parameters.put("SUBREPORT_DIR1", sub_report1);
                    parameters.put("SUBREPORT_DIR2", sub_report2);
                    parameters.put("SUBREPORT_DIR3", sub_report3);
//
//                    
//
                    JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameters, conn);
                    JasperExportManager.exportReportToPdfStream(jasp, outStream);
//
                } catch (Exception ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            else if (request.getParameter("report").contentEquals("MPM024_Excel")) {

      

                String fac = request.getParameter("fac").toString();
                String mo = request.getParameter("mo").toString();
                String company = session.getAttribute("cono").toString();


                System.out.println(fac + " : " + mo + " : " + company);



                conn = ConnectDB2.ConnectionDB();
                String path = getServletContext().getRealPath("/jaspers/");
//                System.out.println("path: " + path);
                Map parameters = new HashMap();

                parameters.put("Fac", fac);
                parameters.put("Mono", mo);
                parameters.put("Company", company);
                
                    JasperReport sub_report1 = JasperCompileManager.compileReport(path + "sub_report.jrxml");
                    JasperReport sub_report2 = JasperCompileManager.compileReport(path + "sub_report2.jrxml");
                    JasperReport sub_report3 = JasperCompileManager.compileReport(path + "sub_report3.jrxml");                  
//
                    parameters.put("SUBREPORT_DIR1", sub_report1);
                    parameters.put("SUBREPORT_DIR2", sub_report2);
                    parameters.put("SUBREPORT_DIR3", sub_report3);
                


                JasperDesign JPD;
                try {
                    JPD = JRXmlLoader.load(path + "MPM024_Excel.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(JPD);

                    try {
                        conn = ConnectDB2.ConnectionDB();
                    } catch (Exception ex) {
                        Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameters, conn);
                    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + "MPM024_Excel" + ".xlsx" + "\"");
                    JRXlsxExporter exporterXls = new JRXlsxExporter();
                    ServletOutputStream ouputStream = response.getOutputStream();
                    exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, jasp);
                    exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                    exporterXls.exportReport();
                    ouputStream.flush();
                    ouputStream.close();

                } catch (Exception ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if (request.getParameter("report").contentEquals("MPM029")) {

                System.out.println("MPM029   PDF ____________________________");
                String fdate = request.getParameter("fdate").toString().replaceAll("-", "");
                String whs = request.getParameter("whs").toString();
                String[] getwarehouse = request.getParameter("whs").split(" : ");
                whs = getwarehouse[0];

                System.out.println(fdate + " : " + whs);

                conn = ConnectDB2.ConnectionDB();
                String path = getServletContext().getRealPath("/jaspers/");
                Map parameters = new HashMap();

//                parameters.put("fdate", "20160104");
//                parameters.put("whs", "A31");
                parameters.put("fdate", fdate);
                parameters.put("whs", whs);
                

                try {

                    OutputStream outStream = response.getOutputStream();
                    System.out.println(path + "MPM029.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(path + "MPM02911.jrxml");

                    JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameters, conn);
                    JasperExportManager.exportReportToPdfStream(jasp, outStream);

                } catch (Exception ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            
            else if (request.getParameter("report").contentEquals("MPM029_Excel")) {
              
                String fdate = request.getParameter("fdate").toString().replaceAll("-", "");
                String whs = request.getParameter("whs").toString();
                String[] getwarehouse = request.getParameter("whs").split(" : ");
                whs = getwarehouse[0];

                System.out.println(fdate + " : " + whs);
                conn = ConnectDB2.ConnectionDB();
                String path = getServletContext().getRealPath("/jaspers/");
//                System.out.println("path: " + path);
                Map parameters = new HashMap();

                parameters.put("fdate", fdate);
                parameters.put("whs", whs);


                JasperDesign JPD;
                try {
                    JPD = JRXmlLoader.load(path + "MPM02911_Excel.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(JPD);

                    try {
                        conn = ConnectDB2.ConnectionDB();
                    } catch (Exception ex) {
                        Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameters, conn);
                    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + "MPM029_Excel" + ".xlsx" + "\"");
                    JRXlsxExporter exporterXls = new JRXlsxExporter();
                    ServletOutputStream ouputStream = response.getOutputStream();
                    exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, jasp);
                    exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                    exporterXls.exportReport();
                    ouputStream.flush();
                    ouputStream.close();

                } catch (Exception ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if (request.getParameter("report").contentEquals("Sum_MO_Report")) {

                conn = ConnectDB2.ConnectionDB();
                String path = getServletContext().getRealPath("/jaspers/");
                System.out.println("path: " + path);
                Map parameters = new HashMap();
//                System.out.println("from date: " + sdf.format(request.getParameter("fromdate").toString()));
                parameters.put("cono", session.getAttribute("cono"));
                parameters.put("divi", session.getAttribute("divi"));
                parameters.put("fac", request.getParameter("facility"));
                parameters.put("whs", request.getParameter("warehouse"));
                parameters.put("", request.getParameter("fromdate"));
                parameters.put("todate", request.getParameter("todate"));
                parameters.put("fromdate", request.getParameter("fromdate"));
//                parameters.put("imagesDir", path);

                Jasper JP;
                try {
                    JP = new Jasper("Sum_MO_Report.jrxml", path, parameters);
                    OutputStream outStream = response.getOutputStream();
                    JP.getreport(outStream);

                } catch (Exception ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (request.getParameter("report").contentEquals("Sum_MO_Report_Excel")) {
                String path = getServletContext().getRealPath("/jaspers/");
                Map parameters = new HashMap();
                parameters.put("cono", session.getAttribute("cono"));
                parameters.put("divi", session.getAttribute("divi"));
                parameters.put("fac", request.getParameter("facility"));
                parameters.put("whs", request.getParameter("warehouse"));
                parameters.put("fromdate", request.getParameter("fromdate"));
                parameters.put("todate", request.getParameter("todate"));

                JasperDesign JPD;
                try {
                    JPD = JRXmlLoader.load(path + "Sum_MO_Report_Excel.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(JPD);

                    try {
                        conn = ConnectDB2.ConnectionDB();
                    } catch (Exception ex) {
                        Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameters, conn);
                    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + "Sum_MO_Report_Excel" + ".xlsx" + "\"");
                    JRXlsxExporter exporterXls = new JRXlsxExporter();
                    ServletOutputStream ouputStream = response.getOutputStream();
                    exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, jasp);
                    exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                    exporterXls.exportReport();
                    ouputStream.flush();
                    ouputStream.close();

                } catch (Exception ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("report").contentEquals("Sum_MODetail_Report_Excel")) {
                String path = getServletContext().getRealPath("/jaspers/");
                Map parameters = new HashMap();
                parameters.put("cono", session.getAttribute("cono"));
                parameters.put("divi", session.getAttribute("divi"));
                parameters.put("fac", request.getParameter("facility"));
                parameters.put("whs", request.getParameter("warehouse"));
                parameters.put("fromdate", request.getParameter("fromdate"));
                parameters.put("todate", request.getParameter("todate"));

                JasperDesign JPD;
                try {
                    JPD = JRXmlLoader.load(path + "Sum_MODetail_Report_Excel.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(JPD);

                    try {
                        conn = ConnectDB2.ConnectionDB();
                    } catch (Exception ex) {
                        Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameters, conn);
                    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + "Sum_MODetail_Report_Excel" + ".xlsx" + "\"");
                    JRXlsxExporter exporterXls = new JRXlsxExporter();
                    ServletOutputStream ouputStream = response.getOutputStream();
                    exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, jasp);
                    exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                    exporterXls.exportReport();
                    ouputStream.flush();
                    ouputStream.close();

                } catch (Exception ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }

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
