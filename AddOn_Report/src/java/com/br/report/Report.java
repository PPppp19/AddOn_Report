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
            

            if (request.getParameter("report").contentEquals("MPM001")) {

                System.out.println("Mo mo    PDF __________1__________________");
                conn = ConnectDB2.ConnectionDB();
                String path = getServletContext().getRealPath("/jaspers/");
                System.out.println("path: " + path);
                Map parameters = new HashMap();
//                System.out.println(request.getParameter("facility") + " : " + request.getParameter("warehouse") + " : " + request.getParameter("mono"));
                parameters.put("cono", session.getAttribute("cono").toString());
                parameters.put("divi", session.getAttribute("divi").toString());
                parameters.put("fac", request.getParameter("fac").toString());
                parameters.put("whs",  request.getParameter("whs").toString());
                parameters.put("mono", request.getParameter("mono").toString());
                parameters.put("username", session.getAttribute("user").toString());
                parameters.put("com", session.getAttribute("cono").toString());
//                parameters.put("com", session.getAttribute("cono").toString());


//                parameters.put("cono", session.getAttribute("cono").toString());
//                parameters.put("divi", "101");
//                parameters.put("fac", "1A1");
//                parameters.put("whs", "10");
//                parameters.put("mono", "0012161088");
//                parameters.put("username", "PPppp");
   
                
//                parameters.put("imagesDir", path);

                try {

                    OutputStream outStream = response.getOutputStream();
                    System.out.println(path + "MPM001.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(path + "MPM001.jrxml");
                    JasperReport subjasperReport1 = JasperCompileManager.compileReport(path + "sub_mpm001_1.jrxml");
                    JasperReport subjasperReport2 = JasperCompileManager.compileReport(path + "sub2.jrxml");
                    JasperReport subjasperReport3 = JasperCompileManager.compileReport(path + "sub3.jrxml");

                    parameters.put("SUBREPORT_DIR1", subjasperReport1);
                    parameters.put("SUBREPORT_DIR2", subjasperReport2);
                    parameters.put("SUBREPORT_DIR3", subjasperReport3);
                    
                    System.out.println("------------------------------");
                    System.out.println("PPPPPPPPPPPPPP " + path );
                    System.out.println(subjasperReport1);
                    System.out.println(subjasperReport2);
                    System.out.println(subjasperReport3);
                    System.out.println("------------------------------");

                    JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameters, conn);
                    JasperExportManager.exportReportToPdfStream(jasp, outStream);

                } catch (Exception ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }

            } 
            
            else if (request.getParameter("report").contentEquals("MPM001_Excel")) {
                String path = getServletContext().getRealPath("/jaspers/");
                Map parameters = new HashMap();
                parameters.put("cono", session.getAttribute("cono").toString());
                parameters.put("divi", session.getAttribute("divi").toString());
                parameters.put("fac", request.getParameter("fac").toString());
                parameters.put("whs",  request.getParameter("whs").toString());
                parameters.put("mono", request.getParameter("mono").toString());
                parameters.put("username", session.getAttribute("user").toString());
                parameters.put("com", session.getAttribute("cono").toString());
                

                

                try {

                    System.out.println(path + "MO_Report_Excel.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(path + "MPM001_Excel.jrxml");
                    JasperReport subjasperReport1 = JasperCompileManager.compileReport(path + "sub_mpm001_1.jrxml");
                    JasperReport subjasperReport2 = JasperCompileManager.compileReport(path + "sub2.jrxml");
                    JasperReport subjasperReport3 = JasperCompileManager.compileReport(path + "sub3.jrxml");

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
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + "MPM001rxml_Excel" + ".xlsx" + "\"");
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
            //////
            else if (request.getParameter("report").contentEquals("MO_Report")) {

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

            } 
            
            else if (request.getParameter("report").contentEquals("MO_Report_Excel")) {
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
            
            
            //////////////////////////  whs005  ////////////////////////////////////////////////
            
            
            else if (request.getParameter("report").contentEquals("WHS005")) {

                System.out.println("WHS005  _______________");

               String whs = request.getParameter("whs").toString();
                String  com = session.getAttribute("cono").toString(); 
                String username = session.getAttribute("user").toString() ;
                String fdate = request.getParameter("fdate").toString().replaceAll("-", "");
                String tdate = request.getParameter("tdate").toString().replaceAll("-", "");


                conn = ConnectDB2.ConnectionDB();
                String path = getServletContext().getRealPath("/jaspers/");
                Map parameters = new HashMap();

                parameters.put("whs", whs);
                parameters.put("username", username);
                parameters.put("com", com);
                parameters.put("fdate", fdate);
                parameters.put("tdate", tdate);


                

                try {

                    OutputStream outStream = response.getOutputStream();
                    System.out.println(path + "WHS005.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(path + "WHS005.jrxml");

                    JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameters, conn);
                    JasperExportManager.exportReportToPdfStream(jasp, outStream);

                } catch (Exception ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }
                                System.out.println("-00099999    PDF ____________________________");


            }
            
            
            else if (request.getParameter("report").contentEquals("WHS005_Excel")) {


                String whs = request.getParameter("whs").toString();
                String  com = session.getAttribute("cono").toString(); 
                String username = session.getAttribute("user").toString() ;
                String fdate = request.getParameter("fdate").toString().replaceAll("-", "");
                String tdate = request.getParameter("tdate").toString().replaceAll("-", "");
                
                conn = ConnectDB2.ConnectionDB();
                String path = getServletContext().getRealPath("/jaspers/");
//                System.out.println("path: " + path);
                Map parameters = new HashMap();

                parameters.put("whs", whs);
                parameters.put("username", username);
                parameters.put("com", com);
                parameters.put("fdate", fdate);
                parameters.put("tdate", tdate);

                JasperDesign JPD;
                try {
                    JPD = JRXmlLoader.load(path + "WHS005_Excel.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(JPD);

                    try {
                        conn = ConnectDB2.ConnectionDB();
                    } catch (Exception ex) {
                        Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameters, conn);
                    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + "WHS005_Excel" + ".xlsx" + "\"");
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
            

            
            
            ////////////////////////////////////////////////////////////////////////////////////////
            
            
            
            
            else if (request.getParameter("report").contentEquals("WHS021")) {

                System.out.println("WHS021   PDF _______12______65_______________");
                String whs = request.getParameter("whs").toString();
                String lot = request.getParameter("vLotN").toString();
                String  com = session.getAttribute("cono").toString(); 
                String username = session.getAttribute("user").toString() ;
//                
//                String[] getwarehouse = request.getParameter("whs").split(" : ");
//                whs = getwarehouse[0];

                //System.out.println(lot + " : " + whs);

                conn = ConnectDB2.ConnectionDB();
                String path = getServletContext().getRealPath("/jaspers/");
                Map parameters = new HashMap();

//                parameters.put("lot", "1456277");
//                parameters.put("whs", "A41");
//                parameters.put("username", "PPppp");
//                parameters.put("com", "10");

                parameters.put("lot", lot);
                parameters.put("whs", whs);
                parameters.put("username", username);
                parameters.put("com", com);

                

                try {

                    OutputStream outStream = response.getOutputStream();
                    System.out.println(path + "WHS021.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(path + "WHS021.jrxml");

                    JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameters, conn);
                    JasperExportManager.exportReportToPdfStream(jasp, outStream);

                } catch (Exception ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            
            
            else if (request.getParameter("report").contentEquals("WHS021_Excel")) {


                String whs = request.getParameter("whs").toString();
                String lot = request.getParameter("vLotN").toString();
                String  com = session.getAttribute("cono").toString(); 
                String username = session.getAttribute("user").toString() ;
                
                conn = ConnectDB2.ConnectionDB();
                String path = getServletContext().getRealPath("/jaspers/");
//                System.out.println("path: " + path);
                Map parameters = new HashMap();

                parameters.put("lot", lot);
                parameters.put("whs", whs);
                parameters.put("username", username);
                parameters.put("com", com);

                JasperDesign JPD;
                try {
                    JPD = JRXmlLoader.load(path + "WHS021_Excel.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(JPD);

                    try {
                        conn = ConnectDB2.ConnectionDB();
                    } catch (Exception ex) {
                        Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameters, conn);
                    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + "WHS021_Excel" + ".xlsx" + "\"");
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
            //////////////////////////////////////////////////////////////////
            
             else if (request.getParameter("report").contentEquals("WHS021R")) {

                System.out.println("WHS021R   PDF ____________________________");
                String whs = request.getParameter("whs").toString();
                String lot = request.getParameter("lot").toString();
                String  com = session.getAttribute("cono").toString(); 
                String username = session.getAttribute("user").toString() ;
//                
//                String[] getwarehouse = request.getParameter("whs").split(" : ");
//                whs = getwarehouse[0];

                //System.out.println(lot + " : " + whs);

                conn = ConnectDB2.ConnectionDB();
                String path = getServletContext().getRealPath("/jaspers/");
                Map parameters = new HashMap();

//                parameters.put("lot", "1456277");
//                parameters.put("whs", "A41");
//                parameters.put("username", "PPppp");
//                parameters.put("com", "10");

                parameters.put("lot", lot);
                parameters.put("whs", whs);
                parameters.put("username", username);
                parameters.put("com", com);

                

                try {

                    OutputStream outStream = response.getOutputStream();
                    System.out.println(path + "WHS021R.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(path + "WHS021R.jrxml");

                    JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameters, conn);
                    JasperExportManager.exportReportToPdfStream(jasp, outStream);

                } catch (Exception ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            
               else if (request.getParameter("report").contentEquals("WHS021R_Excel")) {


                String whs = request.getParameter("whs").toString();
                String lot = request.getParameter("lot").toString();
                String  com = session.getAttribute("cono").toString(); 
                String username = session.getAttribute("user").toString() ;
                
                conn = ConnectDB2.ConnectionDB();
                String path = getServletContext().getRealPath("/jaspers/");
//                System.out.println("path: " + path);
                Map parameters = new HashMap();

                parameters.put("lot", lot);
                parameters.put("whs", whs);
                parameters.put("username", username);
                parameters.put("com", com);

                JasperDesign JPD;
                try {
                    JPD = JRXmlLoader.load(path + "WHS021R_Excel.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(JPD);

                    try {
                        conn = ConnectDB2.ConnectionDB();
                    } catch (Exception ex) {
                        Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameters, conn);
                    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + "WHS021R_Excel" + ".xlsx" + "\"");
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

            
            else if (request.getParameter("report").contentEquals("WHS027")) {

                System.out.println("WHS027   PDF ____________________________");
                String whs = request.getParameter("whs").toString();
                String lot = request.getParameter("lot").toString();
                String  com = session.getAttribute("cono").toString(); 
                String username = session.getAttribute("user").toString() ;


                conn = ConnectDB2.ConnectionDB();
                String path = getServletContext().getRealPath("/jaspers/");
                Map parameters = new HashMap();


                parameters.put("lot", lot);
                parameters.put("whs", whs);
                parameters.put("username", username);
                parameters.put("com", com);


//                parameters.put("lot", "1465182");
//                parameters.put("whs", "A1G");
//                parameters.put("username", "PPppp");
//                parameters.put("com", "10");

                

                try {

                    OutputStream outStream = response.getOutputStream();
                    System.out.println(path + "WHS027.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(path + "WHS027.jrxml");

                    JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameters, conn);
                    JasperExportManager.exportReportToPdfStream(jasp, outStream);

                } catch (Exception ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            
             else if (request.getParameter("report").contentEquals("WHS027_Excel")) {


                String whs = request.getParameter("whs").toString();
                String lot = request.getParameter("lot").toString();
                String  com = session.getAttribute("cono").toString(); 
                String username = session.getAttribute("user").toString() ;
                
                conn = ConnectDB2.ConnectionDB();
                String path = getServletContext().getRealPath("/jaspers/");
//                System.out.println("path: " + path);
                Map parameters = new HashMap();

                parameters.put("lot", lot);
                parameters.put("whs", whs);
                parameters.put("username", username);
                parameters.put("com", com);

                JasperDesign JPD;
                try {
                    JPD = JRXmlLoader.load(path + "WHS027_ExcelPP.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(JPD);

                    try {
                        conn = ConnectDB2.ConnectionDB();
                    } catch (Exception ex) {
                        Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameters, conn);
                    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + "WHS027_ExcelPP" + ".xlsx" + "\"");
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
              

                
//                String[] getCompany = request.getParameter("company").split(" : ");
//                company = getCompany[0];

                System.out.println(fdate + " : " + tdate + " : " + warehouse + " : " + company + " : ");

                conn = ConnectDB2.ConnectionDB();
                String path = getServletContext().getRealPath("/jaspers/");
//                System.out.println("path: " + path);
                Map parameters = new HashMap();

                parameters.put("username", session.getAttribute("user"));
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

//                String[] getCompany = request.getParameter("company").split(" : ");
//                company = getCompany[0];

                System.out.println(fdate + " : " + tdate + " : " + warehouse + " : " + company + " : ");

                conn = ConnectDB2.ConnectionDB();
                String path = getServletContext().getRealPath("/jaspers/");
//                System.out.println("path: " + path);
                Map parameters = new HashMap();

                parameters.put("username", session.getAttribute("user"));
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
                parameters.put("username", session.getAttribute("user"));
                
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
                parameters.put("username", session.getAttribute("user"));
                
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
                    System.out.println(fdate);
                        System.out.println(whs);
                

                String[] getwarehouse = request.getParameter("whs").split(" : ");
                whs = getwarehouse[0];

                System.out.println(fdate + " : " + whs);

                conn = ConnectDB2.ConnectionDB();
                String path = getServletContext().getRealPath("/jaspers/");
                Map parameters = new HashMap();

//                parameters.put("fdate", "20160104");
//                parameters.put("whs", "A31");

                    JasperReport sub_report1 = JasperCompileManager.compileReport(path + "header.jrxml");
                    JasperReport sub_report2 = JasperCompileManager.compileReport(path + "Sum_all_total.jrxml");
                    JasperReport sub_report3 = JasperCompileManager.compileReport(path + "in_mpm029.jrxml"); 
                    JasperReport sub_report4 = JasperCompileManager.compileReport(path + "out_mpm029.jrxml"); 
//
                    parameters.put("SUBREPORT_DIR_Head", sub_report1);
                    parameters.put("SUBREPORT_DIR_Total", sub_report2);
                    parameters.put("SUBREPORT_DIR_in", sub_report3);
                    parameters.put("SUBREPORT_DIR_out", sub_report4);


                    
                    
                parameters.put("fdate", fdate);
                parameters.put("whs", whs);
                parameters.put("com", session.getAttribute("cono"));
                

                try {

                    OutputStream outStream = response.getOutputStream();
                    System.out.println(path + "MPM029.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(path + "MPM029_Main.jrxml");

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

                    JasperReport sub_report1 = JasperCompileManager.compileReport(path + "header.jrxml");
                    JasperReport sub_report2 = JasperCompileManager.compileReport(path + "Sum_all_total.jrxml");
                    JasperReport sub_report3 = JasperCompileManager.compileReport(path + "in_mpm029.jrxml"); 
                    JasperReport sub_report4 = JasperCompileManager.compileReport(path + "out_mpm029.jrxml"); 
//
                    parameters.put("SUBREPORT_DIR_Head", sub_report1);
                    parameters.put("SUBREPORT_DIR_Total", sub_report2);
                    parameters.put("SUBREPORT_DIR_in", sub_report3);
                    parameters.put("SUBREPORT_DIR_out", sub_report4);
                    
                parameters.put("fdate", fdate);
                parameters.put("whs", whs);
                parameters.put("com", session.getAttribute("cono"));



                JasperDesign JPD;
                try {
                   

                    JPD = JRXmlLoader.load(path + "MPM029_Main_Excel.jrxml");
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
