/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api;

import com.br.data.Delete;
import com.br.data.Insert;
import com.br.data.Select;
import com.br.data.Update;
import com.br.utility.ConnectM3;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.codehaus.jettison.json.JSONException;

/**
 *
 * @author Wattana
 */
public class Sync extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Sync</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Sync at " + request.getContextPath() + "</h1>");
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

//        if(session.getAttribute("cono") == null){
//            response.sendRedirect("./login.jsp");
//        }
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
//        System.out.println("page = " + request.getParameter("page"));

        try {

            if (request.getParameter("page").equals("Company")) {
                try {
                    out.print(ConnectM3.getCompany());
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Facility")) {
                try {
                    out.print(Select.Facility(request.getParameter("cono"), request.getParameter("divi")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Warehouse")) {
                try {
                    out.print(Select.Warehouse(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("fac")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            else if (request.getParameter("page").equals("getWarehouse")) {
                try {
                    out.print(Select.getWarehouse(request.getParameter("cono"), request.getParameter("divi")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if (request.getParameter("page").equals("FacilityInventory")) {
                try {
                    out.print(Select.FacilityInventory(request.getParameter("cono"), request.getParameter("divi")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if (request.getParameter("page").equals("Type")) {
                try {
                    out.print(Select.Type(request.getParameter("cono"), request.getParameter("divi")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Level")) {
                try {
                    out.print(Select.Level(request.getParameter("cono"), request.getParameter("divi")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("LevelDetail")) {
                try {
                    out.print(Select.LevelDetail(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("code"), request.getParameter("struct"), request.getParameter("level")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Period")) {
                try {
                    out.print(Select.Period(request.getParameter("cono"), request.getParameter("divi")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("WorkCenter")) {
                try {
                    out.print(Select.WorkCenter(request.getParameter("cono"), request.getParameter("divi")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Bills")) {
                try {
                    out.print(Select.Bills(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("period"), request.getParameter("type")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("BillsDetail")) {
                try {
                    out.print(Select.BillsDetail(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("code"), request.getParameter("period")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Allocation")) {
                try {
                    out.print(Select.Allocation(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("code"), request.getParameter("period"), request.getParameter("fromstatus"), request.getParameter("tostatus"), request.getParameter("type")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("AllocationDetail")) {
                try {
                    out.print(Select.AllocationDetail(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("code"), request.getParameter("struct"), request.getParameter("level"), request.getParameter("period"), request.getParameter("type")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Voucher")) {
                try {
                    out.print(Select.Voucher(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("code"), request.getParameter("period"), request.getParameter("type")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("VoucherVariance")) {
                try {
                    out.print(Select.VoucherVariance(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("code"), request.getParameter("period"), request.getParameter("type")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if (request.getParameter("page").equals("Inventory")) {
                try {
                    out.print(Select.Inventory(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("code"), request.getParameter("period"), request.getParameter("type")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("PostToM3Voucher")) {
                out.print(ConnectM3.callGLS100(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("code"), request.getParameter("period"), request.getParameter("date"), request.getParameter("txtFunc"), request.getParameter("txtVCText"), request.getParameter("type")));
                out.flush();
            } else if (request.getParameter("page").equals("PostToM3Inventory")) {
                out.print(ConnectM3.preMMS100(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("code"), request.getParameter("period"), request.getParameter("date")));
                out.flush();
            } else if (request.getParameter("page").equals("Monitor")) {
                out.print(Select.Monitor(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("code"), request.getParameter("period"), request.getParameter("type")));
                out.flush();
            }

        } catch (Exception ex) {
            Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
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
        HttpSession session = request.getSession(true);

//        if (session.getAttribute("cono") == null) {
//            response.sendRedirect("./login.jsp");
//        }
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
//        System.out.println("page = " + request.getParameter("page"));

        try {

            if (request.getParameter("page").equals("InsertFacility")) {
                try {
                    Insert.Facility(request.getParameter("A1CONO"), request.getParameter("A1DIVI"), request.getParameter("A1CODE"), request.getParameter("A1DESC"),
                            request.getParameter("A1NAME"), request.getParameter("A1TYPE"), request.getParameter("A1MUUN"), request.getParameter("A1RATE"),
                            request.getParameter("A1REF1"), request.getParameter("A1REF2"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("UpdateFacility")) {
                try {
                    Update.Facility(request.getParameter("A1CONO"), request.getParameter("A1DIVI"), request.getParameter("A1CODE"), request.getParameter("A1DESC"),
                            request.getParameter("A1NAME"), request.getParameter("A1TYPE"), request.getParameter("A1MUUN"), request.getParameter("A1RATE"),
                            request.getParameter("A1REF1"), request.getParameter("A1REF2"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("DeleteFacility")) {
                try {
                    Delete.Facility(request.getParameter("A1CONO"), request.getParameter("A1DIVI"), request.getParameter("A1CODE"), request.getParameter("A1DESC"),
                            request.getParameter("A1NAME"), request.getParameter("A1TYPE"), request.getParameter("A1MUUN"), request.getParameter("A1RATE"),
                            request.getParameter("A1REF1"), request.getParameter("A1REF2"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("InsertType")) {
                try {
                    Insert.Type(request.getParameter("ATCONO"), request.getParameter("ATDIVI"), request.getParameter("ATTYPE"), request.getParameter("ATNAME"), request.getParameter("ATREF1"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("UpdateType")) {
                try {
                    Update.Type(request.getParameter("ATCONO"), request.getParameter("ATDIVI"), request.getParameter("ATTYPE"), request.getParameter("ATNAME"), request.getParameter("ATREF1"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("DeleteType")) {
                try {
                    Delete.Type(request.getParameter("ATCONO"), request.getParameter("ATDIVI"), request.getParameter("ATTYPE"), request.getParameter("ATNAME"), request.getParameter("ATREF1"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("InsertLevel")) {
                try {
                    Insert.Level(request.getParameter("A2CONO"), request.getParameter("A2DIVI"), request.getParameter("A2CODE"), request.getParameter("A2STID"),
                            request.getParameter("A2SLVL"), request.getParameter("A2DESC"), request.getParameter("A2REF1"), request.getParameter("A2REF2"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("UpdateLevel")) {
                try {
                    Update.Level(request.getParameter("A2CONO"), request.getParameter("A2DIVI"), request.getParameter("A2CODE"), request.getParameter("A2STID"),
                            request.getParameter("A2SLVL"), request.getParameter("A2DESC"), request.getParameter("A2REF1"), request.getParameter("A2REF2"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("DeleteLevel")) {
                try {
                    Delete.Level(request.getParameter("A2CONO"), request.getParameter("A2DIVI"), request.getParameter("A2CODE"), request.getParameter("A2STID"),
                            request.getParameter("A2SLVL"), request.getParameter("A2DESC"), request.getParameter("A2REF1"), request.getParameter("A2REF2"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("InsertPeriod")) {
                try {
                    Insert.Period(request.getParameter("PECONO"), request.getParameter("PEDIVI"), request.getParameter("PECODE"), request.getParameter("PEYEA4"),
                            request.getParameter("PEMONT"), request.getParameter("PEDESC"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("UpdatePeriod")) {
                try {
                    Update.Period(request.getParameter("PECONO"), request.getParameter("PEDIVI"), request.getParameter("PECODE"), request.getParameter("PEYEA4"),
                            request.getParameter("PEMONT"), request.getParameter("PEDESC"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("DeletePeriod")) {
                try {
                    Delete.Period(request.getParameter("PECONO"), request.getParameter("PEDIVI"), request.getParameter("PECODE"), request.getParameter("PEYEA4"),
                            request.getParameter("PEMONT"), request.getParameter("PEDESC"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("InsertWorkCenter")) {
                try {
                    Insert.WorkCenter(request.getParameter("A3CONO"), request.getParameter("A3DIVI"), request.getParameter("A3CODE"), request.getParameter("A3STID"),
                            request.getParameter("A3SLVL"), request.getParameter("A3AITM"), request.getParameter("A3ASTR"), request.getParameter("A3ADES"),
                            request.getParameter("A3METY"), request.getParameter("A3UEHR"), request.getParameter("A3MELA"), request.getParameter("A3PERS"),
                            request.getParameter("A3ACCT"), request.getParameter("A3AAC1"), request.getParameter("A3AAC2"), request.getParameter("A3ABOI"),
                            request.getParameter("A3ARE1"), request.getParameter("A3ARE2"), request.getParameter("A3ARE3"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("UpdateWorkCenter")) {
                try {
                    Update.WorkCenter(request.getParameter("A3CONO"), request.getParameter("A3DIVI"), request.getParameter("A3CODE"), request.getParameter("A3STID"),
                            request.getParameter("A3SLVL"), request.getParameter("A3AITM"), request.getParameter("A3ASTR"), request.getParameter("A3ADES"),
                            request.getParameter("A3METY"), request.getParameter("A3UEHR"), request.getParameter("A3MELA"), request.getParameter("A3PERS"),
                            request.getParameter("A3ACCT"), request.getParameter("A3AAC1"), request.getParameter("A3AAC2"), request.getParameter("A3ABOI"),
                            request.getParameter("A3ARE1"), request.getParameter("A3ARE2"), request.getParameter("A3ARE3"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("DeleteWorkCenter")) {
                try {
                    Delete.WorkCenter(request.getParameter("A3CONO"), request.getParameter("A3DIVI"), request.getParameter("A3CODE"), request.getParameter("A3STID"),
                            request.getParameter("A3SLVL"), request.getParameter("A3AITM"), request.getParameter("A3ASTR"), request.getParameter("A3ADES"),
                            request.getParameter("A3METY"), request.getParameter("A3UEHR"), request.getParameter("A3MELA"), request.getParameter("A3PERS"),
                            request.getParameter("A3ACCT"), request.getParameter("A3AAC1"), request.getParameter("A3AAC2"), request.getParameter("A3ABOI"),
                            request.getParameter("A3ARE1"), request.getParameter("A3ARE2"), request.getParameter("A3ARE3"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("UpdateLevelWorkCenter")) {
                try {
                    Update.LevelWorkCenter(request.getParameter("A3CONO"), request.getParameter("A3DIVI"), request.getParameter("A3CODE"), request.getParameter("A3STID"),
                            request.getParameter("A3SLVL"), request.getParameter("A3AITM"), request.getParameter("A3ASTR"), request.getParameter("A3ADES"),
                            request.getParameter("A3METY"), request.getParameter("A3UEHR"), request.getParameter("A3MELA"), request.getParameter("A3PERS"),
                            request.getParameter("A3ACCT"), request.getParameter("A3AAC1"), request.getParameter("A3AAC2"), request.getParameter("A3ABOI"),
                            request.getParameter("A3ARE1"), request.getParameter("A3ARE2"), request.getParameter("A3ARE3"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("DeleteLevelWorkCenter")) {
                try {
                    Delete.LevelWorkCenter(request.getParameter("A3CONO"), request.getParameter("A3DIVI"), request.getParameter("A3CODE"), request.getParameter("A3STID"),
                            request.getParameter("A3SLVL"), request.getParameter("A3AITM"), request.getParameter("A3ASTR"), request.getParameter("A3ADES"),
                            request.getParameter("A3METY"), request.getParameter("A3UEHR"), request.getParameter("A3MELA"), request.getParameter("A3PERS"),
                            request.getParameter("A3ACCT"), request.getParameter("A3AAC1"), request.getParameter("A3AAC2"), request.getParameter("A3ABOI"),
                            request.getParameter("A3ARE1"), request.getParameter("A3ARE2"), request.getParameter("A3ARE3"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("InsertBills")) {
                try {
                    Insert.Bills(request.getParameter("E1CONO"), request.getParameter("E1DIVI"), request.getParameter("E1CODE"), request.getParameter("E1PECO"),
                            request.getParameter("E1METS"), request.getParameter("E1METE"), request.getParameter("E1EPQT"), request.getParameter("E1EPAT"),
                            request.getParameter("E1EVAT"), request.getParameter("E1ETAT"), request.getParameter("E1RAAT"), request.getParameter("E1TYPE"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("UpdateBills")) {
                try {
                    Update.Bills(request.getParameter("E1CONO"), request.getParameter("E1DIVI"), request.getParameter("E1CODE"), request.getParameter("E1PECO"),
                            request.getParameter("E1METS"), request.getParameter("E1METE"), request.getParameter("E1EPQT"), request.getParameter("E1EPAT"),
                            request.getParameter("E1EVAT"), request.getParameter("E1ETAT"), request.getParameter("E1RAAT"), request.getParameter("E1TYPE"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("DeleteBills")) {
                try {
                    Delete.Bills(request.getParameter("E1CONO"), request.getParameter("E1DIVI"), request.getParameter("E1CODE"), request.getParameter("E1PECO"),
                            request.getParameter("E1METS"), request.getParameter("E1METE"), request.getParameter("E1EPQT"), request.getParameter("E1EPAT"),
                            request.getParameter("E1EVAT"), request.getParameter("E1ETAT"), request.getParameter("E1RAAT"), request.getParameter("E1TYPE"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("DeleteBillsVariance")) {
                try {
                    Delete.BillsVariance(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("code"), request.getParameter("period"), request.getParameter("type"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if (request.getParameter("page").equals("InsertBillsAllocated")) {
                try {
                    Insert.BillsAllocated(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("code"), request.getParameter("period"), request.getParameter("struct"), request.getParameter("level"), request.getParameter("type"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("InsertBillsAllocatedVariance")) {
                try {
                    Insert.BillsAllocatedVariance(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("code"), request.getParameter("period"), request.getParameter("level"), request.getParameter("type"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("InsertAllocation")) {
                try {
                    Insert.Allocation(request.getParameter("A3CONO"), request.getParameter("A3DIVI"), request.getParameter("E2CODE"), request.getParameter("E2PECO"), request.getParameter("E2TYPE"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("InsertAllocationVariance")) {
                try {
                    Insert.AllocationVariance(request.getParameter("A3CONO"), request.getParameter("A3DIVI"), request.getParameter("E2CODE"), request.getParameter("E2PECO"), request.getParameter("E2TYPE"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("UpdateAllocation")) {
                try {
                    Update.Allocation(request.getParameter("E2CONO"), request.getParameter("E2DIVI"), request.getParameter("E2CODE"), request.getParameter("E2STID"),
                            request.getParameter("E2SLVL"), request.getParameter("E2AITM"), request.getParameter("E2PECO"), request.getParameter("E2METS"),
                            request.getParameter("E2METE"), request.getParameter("E2UEHR"), request.getParameter("E2HOUR"), request.getParameter("E2EPQT"),
                            request.getParameter("E2PERS"), request.getParameter("E2EAQT"), request.getParameter("E2EAAT"), request.getParameter("E2STAT"),
                            request.getParameter("E2TYPE"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("DeleteAllocation")) {
                try {
                    Delete.Allocation(request.getParameter("E2CONO"), request.getParameter("E2DIVI"), request.getParameter("E2CODE"), request.getParameter("E2STID"),
                            request.getParameter("E2SLVL"), request.getParameter("E2AITM"), request.getParameter("E2PECO"), request.getParameter("E2METS"),
                            request.getParameter("E2METE"), request.getParameter("E2UEHR"), request.getParameter("E2HOUR"), request.getParameter("E2EPQT"),
                            request.getParameter("E2PERS"), request.getParameter("E2EAQT"), request.getParameter("E2EAAT"), request.getParameter("E2STAT"),
                            request.getParameter("E2TYPE"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("DeleteAllocationVariance")) {
                try {
                    Delete.AllocationVariance(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("code"), request.getParameter("period"), request.getParameter("type"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if (request.getParameter("page").equals("UpdateStatusAllocated")) {
                try {
                    Update.StatusAllocation(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("code"), request.getParameter("period"), request.getParameter("oldstatus"), request.getParameter("newstatus"), request.getParameter("type"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("UpdateStatusAllocatedLevel")) {
                try {
                    Update.StatusAllocationLevel(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("code"), request.getParameter("period"), request.getParameter("struct"), request.getParameter("level"), request.getParameter("oldstatus"), request.getParameter("newstatus"), request.getParameter("type"));
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
        }

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
