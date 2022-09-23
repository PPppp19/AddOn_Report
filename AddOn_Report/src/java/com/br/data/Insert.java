/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.data;

import com.br.connection.ConnectDB2;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Wattana
 */
public class Insert {

    public static void Facility(String cono, String divi, String code, String desc, String name, String type, String muun, String rate, String ref1, String ref2) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "INSERT INTO BRLDTA0100.A1CASU\n"
                        + "(A1CONO,A1DIVI,A1CODE,A1DESC,A1NAME,A1TYPE,A1MUUN,A1RATE,A1REF1,A1REF2)\n"
                        + "VALUES('" + cono + "'\n"
                        + ",'" + divi + "'\n"
                        + ",'" + code + "'\n"
                        + ",'" + desc + "'\n"
                        + ",'" + name + "'\n"
                        + ",'" + type + "'\n"
                        + ",'" + muun + "'\n"
                        + "," + rate + "\n"
                        + ",'" + ref1 + "'\n"
                        + ",'" + ref2 + "')";
                System.out.println("InsertFacility\n" + query);
                stmt.execute(query);

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void Type(String cono, String divi, String code, String desc, String ref) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "INSERT INTO BRLDTA0100.A1TYPE\n"
                        + "(ATCONO,ATDIVI,ATTYPE,ATNAME,ATREF1)\n"
                        + "VALUES('" + cono + "'\n"
                        + ",'" + divi + "'\n"
                        + ",'" + code + "'\n"
                        + ",'" + desc + "'\n"
                        + ",'" + ref + "')";
                System.out.println("InsertType\n" + query);
                stmt.execute(query);

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void Level(String cono, String divi, String code, String struct, String level, String desc, String ref1, String ref2) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "INSERT INTO BRLDTA0100.A2CASL\n"
                        + "(A2CONO,A2DIVI,A2CODE,A2STID,A2SLVL,A2DESC,A2REF1,A2REF2)\n"
                        + "VALUES('" + cono + "'\n"
                        + ",'" + divi + "'\n"
                        + ",'" + code + "'\n"
                        + ",'" + struct + "'\n"
                        + ",'" + level + "'\n"
                        + ",'" + desc + "'\n"
                        + ",'" + ref1 + "'\n"
                        + ",'" + ref2 + "')";
                System.out.println("InsertLevel\n" + query);
                stmt.execute(query);

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void Period(String cono, String divi, String code, String year, String month, String desc) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "INSERT INTO BRLDTA0100.APERIO\n"
                        + "(PECONO,PEDIVI,PECODE,PEYEA4,PEMONT,PEDESC)\n"
                        + "VALUES('" + cono + "'\n"
                        + ",'" + divi + "'\n"
                        + ",'" + code + "'\n"
                        + ",'" + year + "'\n"
                        + ",'" + month + "'\n"
                        + ",'" + desc + "')";
                System.out.println("InsertPeriod\n" + query);
                stmt.execute(query);

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void WorkCenter(String cono, String divi, String code, String struct, String level, String itm, String str, String desc, String mety, String uehr, String mela, String per,
            String acc, String aac1, String aac2, String boi, String ref1, String ref2, String ref3) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "INSERT INTO BRLDTA0100.A3CAWC\n"
                        + "(A3CONO,A3DIVI,A3CODE,A3STID,A3SLVL,A3AITM,A3ASTR,A3ADES,A3METY,A3UEHR,A3MELA,A3PERS,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3)\n"
                        + "VALUES('" + cono + "'\n"
                        + ",'" + divi + "'\n"
                        + ",'" + code + "'\n"
                        + ",'" + struct + "'\n"
                        + ",'" + level + "'\n"
                        + ",'" + itm + "'\n"
                        + ",'" + str + "'\n"
                        + ",'" + desc + "'\n"
                        + ",'" + mety + "'\n"
                        + ",'" + uehr + "'\n"
                        + ",'" + mela + "'\n"
                        + ",'" + per + "'\n"
                        + ",'" + acc + "'\n"
                        + ",'" + aac1 + "'\n"
                        + ",'" + aac2 + "'\n"
                        + ",'" + boi + "'\n"
                        + ",'" + ref1 + "'\n"
                        + ",'" + ref2 + "'\n"
                        + ",'" + ref3 + "')";
                System.out.println("InsertWorkCenter\n" + query);
                stmt.execute(query);

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void Bills(String cono, String divi, String code, String period, String meterstart, String meterend, String qty, String amount, String vat, String total, String totalamt, String type) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "INSERT INTO BRLDTA0100.E1ALLO\n"
                        + "(E1CONO, E1DIVI, E1CODE, E1PECO, E1METS, E1METE, E1EPQT, E1EPAT, E1EVAT, E1ETAT, E1RAAT, E1TYPE)\n"
                        + "VALUES('" + cono + "'\n"
                        + ",'" + divi + "'\n"
                        + ",'" + code + "'\n"
                        + ",'" + period + "'\n"
                        + ",'" + meterstart + "'\n"
                        + ",'" + meterend + "'\n"
                        + ",'" + qty + "'\n"
                        + ",'" + amount + "'\n"
                        + ",'" + vat + "'\n"
                        + ",'" + total + "'\n"
                        + ",'" + totalamt + "'\n"
                        + "," + type + ")";
                System.out.println("InsertBills\n" + query);
                stmt.execute(query);

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

//    public static void BillsAllocated(String cono, String divi, String code, String period, String meterstart, String meterend, String qty, String amount, String vat, String total, String totalamt, String type) throws Exception {
//
//        Connection conn = ConnectDB2.ConnectionDB();
//
//        try {
//            if (conn != null) {
//
//                Statement stmt = conn.createStatement();
//                String query = "INSERT INTO BRLDTA0100.E1ALLO\n"
//                        + "(E1CONO, E1DIVI, E1CODE, E1PECO, E1METS, E1METE, E1EPQT, E1EPAT, E1EVAT, E1ETAT, E1TYPE)\n"
//                        + "VALUES('" + cono + "'\n"
//                        + ",'" + divi + "'\n"
//                        + ",'" + code + "'\n"
//                        + ",'" + period + "'\n"
//                        + ",'" + meterstart + "'\n"
//                        + ",'" + meterend + "'\n"
//                        + ",'" + qty + "'\n"
//                        + ",'" + amount + "'\n"
//                        + ",'" + vat + "'\n"
//                        + ",'" + total + "'\n"
//                        + ",'" + totalamt + "'\n"
//                        + "," + type + ")";
//                System.out.println("InsertBills\n" + query);
//                stmt.execute(query);
//
//            } else {
//                System.out.println("Server can't connect.");
//            }
//
//        } catch (SQLException sqle) {
//            throw sqle;
//        } catch (Exception e) {
//            e.printStackTrace();
//            if (conn != null) {
//                conn.close();
//            }
//            throw e;
//        } finally {
//            if (conn != null) {
//                conn.close();
//            }
//        }
//
//    }
    public static void Allocation(String cono, String divi, String code, String period, String type) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT A2STID,A2SLVL\n"
                        + "FROM BRLDTA0100.A2CASL\n"
                        + "WHERE A2CONO = '" + cono + "'\n"
                        + "AND A2DIVI = '" + divi + "'\n"
                        + "AND A2CODE = '" + code + "'\n"
                        + "ORDER BY A2CODE";
                System.out.println("SelectStruct.ID, Level\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Statement stmt2 = conn.createStatement();
                    String query2 = "INSERT INTO BRLDTA0100.E2ALLO\n"
                            + "SELECT A3CONO,A3DIVI,A3CODE,A3STID,A3SLVL,A3AITM," + period + ",A3MELA,0,A3UEHR,0,0,A3PERS,0,0,'00'," + type + "\n"
                            + "FROM BRLDTA0100.A3CAWC\n"
                            + "WHERE A3CONO = '" + cono + "'\n"
                            + "AND A3DIVI = '" + divi + "'\n"
                            + "AND A3CODE = '" + code + "'\n"
                            + "AND A3STID = '" + mRes.getString("A2STID").trim() + "'\n"
                            + "AND A3SLVL = '" + mRes.getString("A2SLVL").trim() + "'\n"
                            + "ORDER BY A3SLVL,A3AITM";
                    System.out.println("InsertAllocation\n" + query2);
                    stmt2.execute(query2);
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void AllocationVariance(String cono, String divi, String code, String period, String type) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT A2STID,A2SLVL\n"
                        + "FROM BRLDTA0100.A2CASL\n"
                        + "WHERE A2CONO = '" + cono + "'\n"
                        + "AND A2DIVI = '" + divi + "'\n"
                        + "AND A2CODE = '" + code + "'\n"
                        + "ORDER BY A2CODE";
                System.out.println("SelectStruct.ID, Level\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Statement stmt2 = conn.createStatement();
                    String query2 = "INSERT INTO BRLDTA0100.E2ALLO\n"
                            + "SELECT E2CONO,E2DIVI,E2CODE,E2STID,E2SLVL,E2AITM,'" + period + "',E2METE,0,E2UEHR,0,0,E2PERS,0,0,'00','1'\n"
                            + "FROM BRLDTA0100.E2ALLO\n"
                            + "WHERE E2CONO = '" + cono + "'\n"
                            + "AND E2DIVI = '" + divi + "'\n"
                            + "AND E2CODE = '" + code + "'\n"
                            + "AND E2STID = '" + mRes.getString("A2STID").trim() + "'\n"
                            + "AND E2SLVL = '" + mRes.getString("A2SLVL").trim() + "'\n"
                            + "AND E2PECO = SUBSTRING(CHAR(DATE(SUBSTRING('" + period + "',0,5)||'-'||SUBSTRING('" + period + "',5,2)||'-'||'01') - 1 MONTH, ISO),0,5)||SUBSTRING(CHAR(DATE(SUBSTRING('" + period + "',0,5)||'-'||SUBSTRING('" + period + "',5,2)||'-'||'01') - 1 MONTH, ISO),6,2)\n"
                            + "AND E2TYPE = '0'";
                    System.out.println("InsertAllocationVariance\n" + query2);
                    stmt2.execute(query2);
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void BillsAllocated(String cono, String divi, String code, String period, String struct, String level, String type) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT A2STID,A2SLVL\n"
                        + "FROM BRLDTA0100.A2CASL\n"
                        + "WHERE A2CONO = '" + cono + "'\n"
                        + "AND A2DIVI = '" + divi + "'\n"
                        + "AND A2CODE = '" + code + "'\n"
                        //                        + "AND A2STID = '" + struct + "'\n"
                        + "AND A2SLVL <= '" + level + "'\n"
                        + "ORDER BY A2CODE";
                System.out.println("SelectStruct.ID, Level\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Statement stmt2 = conn.createStatement();
                    String query2 = null;
                    ResultSet mRes2 = null;

                    if ("1".equals(mRes.getString("A2SLVL").trim())) {
                        query2 = "SELECT a.*,b.E2EPQT,c.E1RAAT,ROUND((a.E2EPQT / b.E2EPQT) * 100,2) AS USE_PERCENT,ROUND(b.E2EPQT * (((a.E2EPQT / b.E2EPQT) * 100) / 100),2) AS USE_QTY,ROUND(c.E1RAAT * (((a.E2EPQT / b.E2EPQT) * 100) / 100),2) AS USE_AMT\n"
                                + "FROM \n"
                                + "(SELECT E2CONO,E2DIVI,E2CODE,E2STID,E2SLVL,E2AITM,E2PECO,E2PERS,E2EPQT,E2TYPE\n"
                                + "FROM BRLDTA0100.E2ALLO) AS a\n"
                                + "INNER JOIN \n"
                                + "(SELECT E2CONO,E2DIVI,E2CODE,E2STID,E2SLVL,E2PECO,SUM(E2EPQT) AS E2EPQT,E2TYPE\n"
                                + "FROM BRLDTA0100.E2ALLO\n"
                                + "GROUP BY E2CONO,E2DIVI,E2CODE,E2STID,E2SLVL,E2PECO,E2TYPE) AS b\n"
                                + "ON b.E2CONO = a.E2CONO\n"
                                + "AND b.E2DIVI = a.E2DIVI\n"
                                + "AND b.E2CODE = a.E2CODE\n"
                                + "AND b.E2STID = a.E2STID\n"
                                + "AND b.E2SLVL = a.E2SLVL\n"
                                + "AND b.E2PECO = a.E2PECO\n"
                                + "AND b.E2TYPE = a.E2TYPE\n"
                                + "INNER JOIN\n"
                                + "(SELECT E1CONO,E1DIVI,E1CODE,E1PECO,E1METS,E1METE,E1EPQT,E1EPAT,E1EVAT,E1ETAT,E1RAAT,E1TYPE\n"
                                + "FROM BRLDTA0100.E1ALLO) AS c\n"
                                + "ON c.E1CONO = a.E2CONO\n"
                                + "AND c.E1DIVI = a.E2DIVI\n"
                                + "AND c.E1CODE = a.E2CODE\n"
                                + "AND c.E1PECO = a.E2PECO\n"
                                + "AND c.E1TYPE = a.E2TYPE\n"
                                + "WHERE a.E2CONO = '" + cono + "'\n"
                                + "AND a.E2DIVI = '" + divi + "'\n"
                                + "AND a.E2CODE = '" + code + "'\n"
                                + "AND a.E2STID = '" + mRes.getString("A2STID").trim() + "'\n"
                                + "AND a.E2SLVL = '" + mRes.getString("A2SLVL").trim() + "'\n"
                                + "AND a.E2PECO = '" + period + "'\n"
                                + "AND a.E2EPQT > 0\n"
                                + "AND a.E2TYPE = '" + type + "'";
                        System.out.println("SelectBillsAllocated Level = 1\n" + query2);
                        mRes2 = stmt2.executeQuery(query2);

                        while (mRes2.next()) {

                            Statement stmt3 = conn.createStatement();
                            String query3 = "UPDATE BRLDTA0100.E2ALLO\n"
                                    + "SET E2PERS = '" + mRes2.getString("USE_PERCENT").trim() + "'\n"
                                    + ",E2EAQT = '" + mRes2.getString("USE_QTY").trim() + "'\n"
                                    + ",E2EAAT = '" + mRes2.getString("USE_AMT").trim() + "'\n"
                                    + "WHERE E2CONO = '" + cono + "'\n"
                                    + "AND E2DIVI = '" + divi + "'\n"
                                    + "AND E2CODE = '" + code + "'\n"
                                    + "AND E2PECO = '" + period + "'\n"
                                    + "AND E2AITM = '" + mRes2.getString("E2AITM").trim() + "'\n"
                                    + "AND E2TYPE = '" + type + "'";
                            System.out.println("UpdateBillsAllocated\n" + query3);
                            stmt3.execute(query3);

                        }

                        Statement stmt4 = conn.createStatement();
                        String query4 = "SELECT TOTAL_QTY,TOTAL_AMT,SUM(USE_QTY) AS USE_QTY,SUM(USE_AMT) AS USE_AMT,ROUND(TOTAL_QTY - SUM(USE_QTY),2) AS SUM_QTY,ROUND(TOTAL_AMT - SUM(USE_AMT),2) AS SUM_AMT\n"
                                + "FROM\n"
                                + "(SELECT a.E2CODE,a.E2PECO,b.E1EPQT AS TOTAL_QTY,b.E1RAAT AS TOTAL_AMT,ROUND((a.E2EPQT / b.E1EPQT) * b.E1EPQT,2) AS USE_QTY,ROUND((a.E2EPQT / b.E1EPQT) * E1RAAT,2) AS USE_AMT\n"
                                + "FROM \n"
                                + "(SELECT E2CONO,E2DIVI,E2CODE,E2STID,E2SLVL,E2AITM,E2PECO,E2PERS,E2EPQT,E2TYPE\n"
                                + "FROM BRLDTA0100.E2ALLO) AS a\n"
                                + "INNER JOIN\n"
                                + "(SELECT E1CONO,E1DIVI,E1CODE,E1PECO,E1METS,E1METE,E1EPQT,E1EPAT,E1EVAT,E1ETAT,E1RAAT,E1TYPE\n"
                                + "FROM BRLDTA0100.E1ALLO) AS b\n"
                                + "ON b.E1CONO = a.E2CONO\n"
                                + "AND b.E1DIVI = a.E2DIVI\n"
                                + "AND b.E1CODE = a.E2CODE\n"
                                + "AND b.E1PECO = a.E2PECO\n"
                                + "AND b.E1TYPE = a.E2TYPE\n"
                                + "WHERE a.E2CONO = '" + cono + "'\n"
                                + "AND a.E2DIVI = '" + divi + "'\n"
                                + "AND a.E2CODE = '" + code + "'\n"
                                + "AND a.E2STID = '" + mRes.getString("A2STID").trim() + "'\n"
                                + "AND a.E2SLVL = '" + mRes.getString("A2SLVL").trim() + "'\n"
                                + "AND a.E2PECO = '" + period + "'\n"
                                + "AND a.E2PERS > 0\n"
                                + "AND a.E2TYPE = '" + type + "') AS a\n"
                                + "GROUP BY TOTAL_QTY,TOTAL_AMT";
                        System.out.println("SelectDiffAllocated\n" + query4);
                        ResultSet mRes3 = stmt4.executeQuery(query4);

                        while (mRes3.next()) {
                            Statement stmt5 = conn.createStatement();
                            String query5 = "UPDATE BRLDTA0100.E2ALLO\n"
                                    + "SET E2EPQT = E2EPQT + " + mRes3.getString("SUM_QTY").trim() + "\n"
                                    + ",E2EAQT = E2EAQT + " + mRes3.getString("SUM_QTY").trim() + "\n"
                                    + ",E2EAAT = E2EAAT + " + mRes3.getString("SUM_AMT").trim() + "\n"
                                    + "WHERE E2CONO = '" + cono + "'\n"
                                    + "AND E2DIVI = '" + divi + "'\n"
                                    + "AND E2CODE = '" + code + "'\n"
                                    + "AND E2STID = '" + mRes.getString("A2STID").trim() + "'\n"
                                    + "AND E2SLVL = '" + mRes.getString("A2SLVL").trim() + "'\n"
                                    + "AND E2PECO = '" + period + "'\n"
                                    + "AND E2EAQT > 0\n"
                                    + "AND E2AITM = (SELECT MAX(E2AITM) \n"
                                    + "FROM BRLDTA0100.E2ALLO\n"
                                    + "WHERE E2CONO = '" + cono + "'\n"
                                    + "AND E2DIVI = '" + divi + "'\n"
                                    + "AND E2CODE = '" + code + "'\n"
                                    + "AND E2STID = '" + mRes.getString("A2STID").trim() + "'\n"
                                    + "AND E2SLVL = '" + mRes.getString("A2SLVL").trim() + "'\n"
                                    + "AND E2PECO = '" + period + "'\n"
                                    + "AND E2EAQT > 0\n"
                                    + "AND E2TYPE = '" + type + "')\n"
                                    + "AND E2TYPE = '" + type + "'";
                            System.out.println("UpdateDiffAllocated\n" + query5);
                            stmt5.execute(query5);
                        }

                        mRes3.close();

                    } else {

                        query2 = "SELECT a.*,b.E2EPQT,b.E2EAAT,CASE WHEN a.E2CODE = '300' THEN ROUND(b.E2EPQT * (a.E2PERS / 100),4) ELSE ROUND(b.E2EPQT * (a.E2PERS / 100),2) END AS USE_QTY\n"
                                + ",CASE WHEN a.E2CODE = '300' THEN ROUND(b.E2EAAT * (a.E2PERS / 100),4) ELSE ROUND(b.E2EAAT * (a.E2PERS / 100),2) END AS USE_AMT\n"
                                + "FROM \n"
                                + "(SELECT E2CONO,E2DIVI,E2CODE,E2STID,E2SLVL,E2AITM,E2PECO,E2PERS,E2EPQT,E2TYPE\n"
                                + "FROM BRLDTA0100.E2ALLO) AS a\n"
                                + "INNER JOIN \n"
                                + "(SELECT E2CONO,E2DIVI,E2CODE,E2STID,E2SLVL,E2AITM,E2PECO,E2EPQT,E2EAAT,E2TYPE\n"
                                + "FROM BRLDTA0100.E2ALLO) AS b\n"
                                + "ON b.E2CONO = a.E2CONO\n"
                                + "AND b.E2DIVI = a.E2DIVI\n"
                                + "AND b.E2CODE = a.E2CODE\n"
                                + "AND b.E2AITM = a.E2STID\n"
                                + "AND b.E2PECO = a.E2PECO\n"
                                + "AND b.E2TYPE = a.E2TYPE\n"
                                + "WHERE a.E2CONO = '" + cono + "'\n"
                                + "AND a.E2DIVI = '" + divi + "'\n"
                                + "AND a.E2CODE = '" + code + "'\n"
                                + "AND a.E2STID = '" + mRes.getString("A2STID").trim() + "'\n"
                                + "AND a.E2SLVL = '" + mRes.getString("A2SLVL").trim() + "'\n"
                                + "AND a.E2PECO = '" + period + "'\n"
                                + "AND a.E2PERS > 0\n"
                                + "AND a.E2TYPE = '" + type + "'";
                        System.out.println("SelectBillsAllocated Level > 1\n" + query2);
                        mRes2 = stmt2.executeQuery(query2);

                        while (mRes2.next()) {

                            Statement stmt3 = conn.createStatement();
                            String query3 = "UPDATE BRLDTA0100.E2ALLO\n"
                                    + "SET E2EPQT = '" + mRes2.getString("USE_QTY").trim() + "'\n"
                                    + ",E2EAQT = '" + mRes2.getString("USE_QTY").trim() + "'\n"
                                    + ",E2EAAT = '" + mRes2.getString("USE_AMT").trim() + "'\n"
                                    + "WHERE E2CONO = '" + cono + "'\n"
                                    + "AND E2DIVI = '" + divi + "'\n"
                                    + "AND E2CODE = '" + code + "'\n"
                                    + "AND E2PECO = '" + period + "'\n"
                                    + "AND E2AITM = '" + mRes2.getString("E2AITM").trim() + "'\n"
                                    + "AND E2TYPE = '" + type + "'";
                            System.out.println("UpdateBillsAllocated\n" + query3);
                            stmt3.execute(query3);

                        }

                        Statement stmt4 = conn.createStatement();
                        String query4 = "SELECT TOTAL_QTY,TOTAL_AMT,SUM(USE_QTY) AS USE_QTY,SUM(USE_AMT) AS USE_AMT,ROUND(TOTAL_QTY - SUM(USE_QTY),2) AS SUM_QTY,ROUND(TOTAL_AMT - SUM(USE_AMT),2) AS SUM_AMT\n"
                                + "FROM\n"
                                + "(SELECT a.E2CODE,a.E2PECO,b.E2EPQT AS TOTAL_QTY,b.E2EAAT AS TOTAL_AMT,CAST(ROUND(b.E2EPQT * (a.E2PERS / 100),2) AS DECIMAL(15,2)) AS USE_QTY,CAST(ROUND(b.E2EAAT * (a.E2PERS / 100),2) AS DECIMAL(15,2)) AS USE_AMT\n"
                                + "FROM \n"
                                + "(SELECT E2CONO,E2DIVI,E2CODE,E2STID,E2SLVL,E2AITM,E2PECO,E2PERS,E2EPQT,E2TYPE\n"
                                + "FROM BRLDTA0100.E2ALLO) AS a\n"
                                + "INNER JOIN \n"
                                + "(SELECT E2CONO,E2DIVI,E2CODE,E2STID,E2SLVL,E2AITM,E2PECO,E2EPQT,E2EAAT,E2TYPE\n"
                                + "FROM BRLDTA0100.E2ALLO) AS b\n"
                                + "ON b.E2CONO = a.E2CONO\n"
                                + "AND b.E2DIVI = a.E2DIVI\n"
                                + "AND b.E2CODE = a.E2CODE\n"
                                + "AND b.E2AITM = a.E2STID\n"
                                + "AND b.E2PECO = a.E2PECO\n"
                                + "AND b.E2TYPE = a.E2TYPE\n"
                                + "WHERE a.E2CONO = '" + cono + "'\n"
                                + "AND a.E2DIVI = '" + divi + "'\n"
                                + "AND a.E2CODE = '" + code + "'\n"
                                + "AND a.E2STID = '" + mRes.getString("A2STID").trim() + "'\n"
                                + "AND a.E2SLVL = '" + mRes.getString("A2SLVL").trim() + "'\n"
                                + "AND a.E2PECO = '" + period + "'\n"
                                + "AND a.E2PERS > 0\n"
                                + "AND a.E2TYPE = '" + type + "') AS a\n"
                                + "GROUP BY TOTAL_QTY,TOTAL_AMT";
                        System.out.println("SelectDiffAllocated\n" + query4);
                        ResultSet mRes3 = stmt4.executeQuery(query4);

                        while (mRes3.next()) {
                            Statement stmt5 = conn.createStatement();
                            String query5 = "UPDATE BRLDTA0100.E2ALLO\n"
                                    + "SET E2EPQT = E2EPQT + " + mRes3.getString("SUM_QTY").trim() + "\n"
                                    + ",E2EAQT = E2EAQT + " + mRes3.getString("SUM_QTY").trim() + "\n"
                                    + ",E2EAAT = E2EAAT + " + mRes3.getString("SUM_AMT").trim() + "\n"
                                    + "WHERE E2CONO = '" + cono + "'\n"
                                    + "AND E2DIVI = '" + divi + "'\n"
                                    + "AND E2CODE = '" + code + "'\n"
                                    + "AND E2STID = '" + mRes.getString("A2STID").trim() + "'\n"
                                    + "AND E2SLVL = '" + mRes.getString("A2SLVL").trim() + "'\n"
                                    + "AND E2PECO = '" + period + "'\n"
                                    + "AND E2EAQT > 0\n"
                                    + "AND E2AITM = (SELECT MAX(E2AITM) \n"
                                    + "FROM BRLDTA0100.E2ALLO\n"
                                    + "WHERE E2CONO = '" + cono + "'\n"
                                    + "AND E2DIVI = '" + divi + "'\n"
                                    + "AND E2CODE = '" + code + "'\n"
                                    + "AND E2STID = '" + mRes.getString("A2STID").trim() + "'\n"
                                    + "AND E2SLVL = '" + mRes.getString("A2SLVL").trim() + "'\n"
                                    + "AND E2PECO = '" + period + "'\n"
                                    + "AND E2EAQT > 0\n"
                                    + "AND E2TYPE = '" + type + "')\n"
                                    + "AND E2TYPE = '" + type + "'";
                            System.out.println("UpdateDiffAllocated\n" + query5);
                            stmt5.execute(query5);
                        }

                        mRes3.close();

                    }

                    mRes2.close();

                }

                mRes.close();

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void BillsAllocatedVariance(String cono, String divi, String code, String period, String level, String type) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT A2STID,A2SLVL\n"
                        + "FROM BRLDTA0100.A2CASL\n"
                        + "WHERE A2CONO = '" + cono + "'\n"
                        + "AND A2DIVI = '" + divi + "'\n"
                        + "AND A2CODE = '" + code + "'\n"
                        + "AND A2SLVL <= '" + level + "'\n"
                        + "ORDER BY A2CODE";
                System.out.println("SelectStruct.ID, Level\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Statement stmt2 = conn.createStatement();
                    String query2 = null;
                    ResultSet mRes2 = null;

                    if ("1".equals(mRes.getString("A2SLVL").trim())) {
                        query2 = "SELECT a.*,b.E2EPQT,c.E1RAAT,a.E2PERS AS USE_PERCENT,0 AS USE_QTY,ROUND((a.E2PERS * c.E1RAAT) / 100,2) AS USE_AMT\n"
                                + "FROM \n"
                                + "(SELECT E2CONO,E2DIVI,E2CODE,E2STID,E2SLVL,E2AITM,E2PECO,E2PERS,E2EPQT,E2TYPE\n"
                                + "FROM BRLDTA0100.E2ALLO) AS a\n"
                                + "INNER JOIN \n"
                                + "(SELECT E2CONO,E2DIVI,E2CODE,E2STID,E2SLVL,E2PECO,SUM(E2EPQT) AS E2EPQT\n"
                                + "FROM BRLDTA0100.E2ALLO\n"
                                + "GROUP BY E2CONO,E2DIVI,E2CODE,E2STID,E2SLVL,E2PECO) AS b\n"
                                + "ON b.E2CONO = a.E2CONO\n"
                                + "AND b.E2DIVI = a.E2DIVI\n"
                                + "AND b.E2CODE = a.E2CODE\n"
                                + "AND b.E2STID = a.E2STID\n"
                                + "AND b.E2SLVL = a.E2SLVL\n"
                                + "AND b.E2PECO = a.E2PECO\n"
                                + "INNER JOIN\n"
                                + "(SELECT E1CONO,E1DIVI,E1CODE,E1PECO,E1METS,E1METE,E1EPQT,E1EPAT,E1EVAT,E1ETAT,E1RAAT\n"
                                + "FROM BRLDTA0100.E1ALLO) AS c\n"
                                + "ON c.E1CONO = a.E2CONO\n"
                                + "AND c.E1DIVI = a.E2DIVI\n"
                                + "AND c.E1CODE = a.E2CODE\n"
                                + "AND c.E1PECO = a.E2PECO\n"
                                + "WHERE a.E2CONO = '" + cono + "'\n"
                                + "AND a.E2DIVI = '" + divi + "'\n"
                                + "AND a.E2CODE = '" + code + "'\n"
                                + "AND a.E2STID = '" + mRes.getString("A2STID").trim() + "'\n"
                                + "AND a.E2SLVL = '" + mRes.getString("A2SLVL").trim() + "'\n"
                                + "AND a.E2PECO = '" + period + "'\n"
                                //                                + "AND a.E2EPQT > 0\n"
                                + "AND a.E2TYPE = '" + type + "'";
                        System.out.println("SelectBillsAllocatedVariance Level = 1\n" + query2);
                        mRes2 = stmt2.executeQuery(query2);

                        while (mRes2.next()) {

                            Statement stmt3 = conn.createStatement();
                            String query3 = "UPDATE BRLDTA0100.E2ALLO\n"
                                    + "SET E2PERS = '" + mRes2.getString("USE_PERCENT").trim() + "'\n"
                                    + ",E2EAQT = '" + mRes2.getString("USE_QTY").trim() + "'\n"
                                    + ",E2EAAT = '" + mRes2.getString("USE_AMT").trim() + "'\n"
                                    + "WHERE E2CONO = '" + cono + "'\n"
                                    + "AND E2DIVI = '" + divi + "'\n"
                                    + "AND E2CODE = '" + code + "'\n"
                                    + "AND E2PECO = '" + period + "'\n"
                                    + "AND E2AITM = '" + mRes2.getString("E2AITM").trim() + "'\n"
                                    + "AND E2TYPE = '" + type + "'";
                            System.out.println("UpdateBillsAllocatedVariance\n" + query3);
                            stmt3.execute(query3);

                        }

                        Statement stmt4 = conn.createStatement();
                        String query4 = "SELECT TOTAL_QTY,TOTAL_AMT,SUM(USE_QTY) AS USE_QTY,SUM(USE_AMT) AS USE_AMT,ROUND(TOTAL_QTY - SUM(USE_QTY),2) AS SUM_QTY,ROUND(TOTAL_AMT - SUM(USE_AMT),2) AS SUM_AMT\n"
                                + "FROM\n"
                                + "(SELECT a.E2CODE,a.E2PECO,b.E1EPQT AS TOTAL_QTY,b.E1RAAT AS TOTAL_AMT,0 AS USE_QTY,ROUND((a.E2PERS * E1RAAT) / 100,2) AS USE_AMT\n"
                                + "FROM \n"
                                + "(SELECT E2CONO,E2DIVI,E2CODE,E2STID,E2SLVL,E2AITM,E2PECO,E2PERS,E2EPQT,E2TYPE\n"
                                + "FROM BRLDTA0100.E2ALLO) AS a\n"
                                + "INNER JOIN\n"
                                + "(SELECT E1CONO,E1DIVI,E1CODE,E1PECO,E1METS,E1METE,E1EPQT,E1EPAT,E1EVAT,E1ETAT,E1RAAT\n"
                                + "FROM BRLDTA0100.E1ALLO) AS b\n"
                                + "ON b.E1CONO = a.E2CONO\n"
                                + "AND b.E1DIVI = a.E2DIVI\n"
                                + "AND b.E1CODE = a.E2CODE\n"
                                + "AND b.E1PECO = a.E2PECO\n"
                                + "WHERE a.E2CONO = '" + cono + "'\n"
                                + "AND a.E2DIVI = '" + divi + "'\n"
                                + "AND a.E2CODE = '" + code + "'\n"
                                + "AND a.E2STID = '" + mRes.getString("A2STID").trim() + "'\n"
                                + "AND a.E2SLVL = '" + mRes.getString("A2SLVL").trim() + "'\n"
                                + "AND a.E2PECO = '" + period + "'\n"
                                + "AND a.E2PERS > 0\n"
                                + "AND a.E2TYPE = '" + type + "') AS a\n"
                                + "GROUP BY TOTAL_QTY,TOTAL_AMT";
                        System.out.println("SelectDiffAllocatedVariance\n" + query4);
                        ResultSet mRes3 = stmt4.executeQuery(query4);

                        while (mRes3.next()) {
                            Statement stmt5 = conn.createStatement();
                            String query5 = "UPDATE BRLDTA0100.E2ALLO\n"
                                    + "SET E2EPQT = E2EPQT + " + mRes3.getString("SUM_QTY").trim() + "\n"
                                    + ",E2EAQT = E2EAQT + " + mRes3.getString("SUM_QTY").trim() + "\n"
                                    + ",E2EAAT = E2EAAT + " + mRes3.getString("SUM_AMT").trim() + "\n"
                                    + "WHERE E2CONO = '" + cono + "'\n"
                                    + "AND E2DIVI = '" + divi + "'\n"
                                    + "AND E2CODE = '" + code + "'\n"
                                    + "AND E2STID = '" + mRes.getString("A2STID").trim() + "'\n"
                                    + "AND E2SLVL = '" + mRes.getString("A2SLVL").trim() + "'\n"
                                    + "AND E2PECO = '" + period + "'\n"
                                    //+ "AND E2EAQT > 0\n"
                                    + "AND E2AITM = (SELECT MAX(E2AITM) \n"
                                    + "FROM BRLDTA0100.E2ALLO\n"
                                    + "WHERE E2CONO = '" + cono + "'\n"
                                    + "AND E2DIVI = '" + divi + "'\n"
                                    + "AND E2CODE = '" + code + "'\n"
                                    + "AND E2STID = '" + mRes.getString("A2STID").trim() + "'\n"
                                    + "AND E2SLVL = '" + mRes.getString("A2SLVL").trim() + "'\n"
                                    + "AND E2PECO = '" + period + "'\n"
                                    //+ "AND E2EAQT > 0\n"
                                    + ")\n"
                                    + "AND E2TYPE = '" + type + "'";
                            System.out.println("UpdateDiffAllocatedVariance\n" + query5);
                            stmt5.execute(query5);
                        }

                        mRes3.close();

                    } else {

                        query2 = "SELECT a.*,b.E2EPQT,b.E2EAAT,ROUND(b.E2EPQT * (a.E2PERS / 100),2) AS USE_QTY,ROUND(b.E2EAAT * (a.E2PERS / 100),2) AS USE_AMT\n"
                                + "FROM \n"
                                + "(SELECT E2CONO,E2DIVI,E2CODE,E2STID,E2SLVL,E2AITM,E2PECO,E2PERS,E2EPQT,E2TYPE\n"
                                + "FROM BRLDTA0100.E2ALLO) AS a\n"
                                + "INNER JOIN \n"
                                + "(SELECT E2CONO,E2DIVI,E2CODE,E2STID,E2SLVL,E2AITM,E2PECO,E2EPQT,E2EAAT\n"
                                + "FROM BRLDTA0100.E2ALLO) AS b\n"
                                + "ON b.E2CONO = a.E2CONO\n"
                                + "AND b.E2DIVI = a.E2DIVI\n"
                                + "AND b.E2CODE = a.E2CODE\n"
                                + "AND b.E2AITM = a.E2STID\n"
                                + "AND b.E2PECO = a.E2PECO\n"
                                + "WHERE a.E2CONO = '" + cono + "'\n"
                                + "AND a.E2DIVI = '" + divi + "'\n"
                                + "AND a.E2CODE = '" + code + "'\n"
                                + "AND a.E2STID = '" + mRes.getString("A2STID").trim() + "'\n"
                                + "AND a.E2SLVL = '" + mRes.getString("A2SLVL").trim() + "'\n"
                                + "AND a.E2PECO = '" + period + "'\n"
                                + "AND a.E2PERS > 0\n"
                                + "AND a.E2TYPE = '" + type + "'";
                        System.out.println("SelectBillsAllocated Level > 1\n" + query2);
                        mRes2 = stmt2.executeQuery(query2);

                        while (mRes2.next()) {

                            Statement stmt3 = conn.createStatement();
                            String query3 = "UPDATE BRLDTA0100.E2ALLO\n"
                                    + "SET E2EPQT = '" + mRes2.getString("USE_QTY").trim() + "'\n"
                                    + ",E2EAQT = '" + mRes2.getString("USE_QTY").trim() + "'\n"
                                    + ",E2EAAT = '" + mRes2.getString("USE_AMT").trim() + "'\n"
                                    + "WHERE E2CONO = '" + cono + "'\n"
                                    + "AND E2DIVI = '" + divi + "'\n"
                                    + "AND E2CODE = '" + code + "'\n"
                                    + "AND E2PECO = '" + period + "'\n"
                                    + "AND E2AITM = '" + mRes2.getString("E2AITM").trim() + "'\n"
                                    + "AND E2TYPE = '" + type + "'";
                            System.out.println("UpdateBillsAllocatedVariance\n" + query3);
                            stmt3.execute(query3);

                        }

                        Statement stmt4 = conn.createStatement();
                        String query4 = "SELECT TOTAL_QTY,TOTAL_AMT,SUM(USE_QTY) AS USE_QTY,SUM(USE_AMT) AS USE_AMT,ROUND(TOTAL_QTY - SUM(USE_QTY),2) AS SUM_QTY,ROUND(TOTAL_AMT - SUM(USE_AMT),2) AS SUM_AMT\n"
                                + "FROM\n"
                                + "(SELECT a.E2CODE,a.E2PECO,b.E2EPQT AS TOTAL_QTY,b.E2EAAT AS TOTAL_AMT,CAST(ROUND(b.E2EPQT * (a.E2PERS / 100),2) AS DECIMAL(15,2)) AS USE_QTY,CAST(ROUND(b.E2EAAT * (a.E2PERS / 100),2) AS DECIMAL(15,2)) AS USE_AMT\n"
                                + "FROM \n"
                                + "(SELECT E2CONO,E2DIVI,E2CODE,E2STID,E2SLVL,E2AITM,E2PECO,E2PERS,E2EPQT,E2TYPE\n"
                                + "FROM BRLDTA0100.E2ALLO) AS a\n"
                                + "INNER JOIN \n"
                                + "(SELECT E2CONO,E2DIVI,E2CODE,E2STID,E2SLVL,E2AITM,E2PECO,E2EPQT,E2EAAT\n"
                                + "FROM BRLDTA0100.E2ALLO) AS b\n"
                                + "ON b.E2CONO = a.E2CONO\n"
                                + "AND b.E2DIVI = a.E2DIVI\n"
                                + "AND b.E2CODE = a.E2CODE\n"
                                + "AND b.E2AITM = a.E2STID\n"
                                + "AND b.E2PECO = a.E2PECO\n"
                                + "WHERE a.E2CONO = '" + cono + "'\n"
                                + "AND a.E2DIVI = '" + divi + "'\n"
                                + "AND a.E2CODE = '" + code + "'\n"
                                + "AND a.E2STID = '" + mRes.getString("A2STID").trim() + "'\n"
                                + "AND a.E2SLVL = '" + mRes.getString("A2SLVL").trim() + "'\n"
                                + "AND a.E2PECO = '" + period + "'\n"
                                + "AND a.E2PERS > 0\n"
                                + "AND E2TYPE = '" + type + "') AS a\n"
                                + "GROUP BY TOTAL_QTY,TOTAL_AMT";
                        System.out.println("SelectDiffAllocatedVariance\n" + query4);
                        ResultSet mRes3 = stmt4.executeQuery(query4);

                        while (mRes3.next()) {
                            Statement stmt5 = conn.createStatement();
                            String query5 = "UPDATE BRLDTA0100.E2ALLO\n"
                                    + "SET E2EPQT = E2EPQT + " + mRes3.getString("SUM_QTY").trim() + "\n"
                                    + ",E2EAQT = E2EAQT + " + mRes3.getString("SUM_QTY").trim() + "\n"
                                    + ",E2EAAT = E2EAAT + " + mRes3.getString("SUM_AMT").trim() + "\n"
                                    + "WHERE E2CONO = '" + cono + "'\n"
                                    + "AND E2DIVI = '" + divi + "'\n"
                                    + "AND E2CODE = '" + code + "'\n"
                                    + "AND E2STID = '" + mRes.getString("A2STID").trim() + "'\n"
                                    + "AND E2SLVL = '" + mRes.getString("A2SLVL").trim() + "'\n"
                                    + "AND E2PECO = '" + period + "'\n"
                                    //+ "AND E2EAQT > 0\n"
                                    + "AND E2AITM = (SELECT MAX(E2AITM) \n"
                                    + "FROM BRLDTA0100.E2ALLO\n"
                                    + "WHERE E2CONO = '" + cono + "'\n"
                                    + "AND E2DIVI = '" + divi + "'\n"
                                    + "AND E2CODE = '" + code + "'\n"
                                    + "AND E2STID = '" + mRes.getString("A2STID").trim() + "'\n"
                                    + "AND E2SLVL = '" + mRes.getString("A2SLVL").trim() + "'\n"
                                    + "AND E2PECO = '" + period + "'\n"
                                    //+ "AND E2EAQT > 0\n"
                                    + "AND E2TYPE = '" + type + "')";
                            System.out.println("UpdateDiffAllocatedVariance\n" + query5);
                            stmt5.execute(query5);
                        }

                        mRes3.close();

                    }

                    mRes2.close();

                }

                mRes.close();

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

}
