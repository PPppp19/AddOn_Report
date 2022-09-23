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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jettison.json.JSONArray;

/**
 *
 * @author Wattana
 */
public class Select {

    public static JSONArray Company() throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT CCCONO,CCDIVI,CCCONM,'\"'|| TRIM(CCCONO) || ' : ' || TRIM(CCDIVI) || ' : ' || TRIM(CCCONM) || '\"' AS COMPANY\n"
                        + "FROM M3FDBPRD.CMNDIV\n"
                        + "WHERE CCDIVI != ''\n"
                        + "ORDER BY CCCONO";
                System.out.println("SelectCompany\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("CCCONO", mRes.getString(1).trim());
                    mMap.put("CCDIVI", mRes.getString(2).trim());
                    mMap.put("CCCONM", mRes.getString(3).trim());
                    mMap.put("COMPANY", mRes.getString(4).trim());
                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray Facility(String cono, String divi) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT CFFACI,CFFACN,CFFACI || ' : ' || CFFACN AS FACILITY \n"
                        + "FROM M3FDBPRD.CFACIL\n"
                        + "WHERE CFCONO = '" + cono + "'\n"
                        + "AND CFDIVI = '" + divi + "'";
                System.out.println("SelectFacility\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("CFFACI", mRes.getString(1).trim());
                    mMap.put("CFFACN", mRes.getString(2).trim());
                    mMap.put("FACILITY", mRes.getString(3).trim());
                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }
    
    public static JSONArray Warehouse(String cono, String divi, String fac) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT MWWHLO,MWWHNM,MWWHLO || ' : ' || MWWHNM AS WAREHOUSE \n"
                    + "FROM M3FDBPRD.MITWHL\n"
                    + "WHERE MWCONO = '" + cono + "'\n"
                    + "AND MWDIVI = '" + divi + "'\n"
                    + "AND MWFACI = '" + fac + "'\n"
                    + "ORDER BY MWWHLO";
                System.out.println("SelectFacility\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("MWWHLO", mRes.getString(1).trim());
                    mMap.put("MWWHNM", mRes.getString(2).trim());
                    mMap.put("WAREHOUSE", mRes.getString(3).trim());
                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    
    public static JSONArray getWarehouse(String cono, String divi) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT MWWHLO,MWWHNM,MWWHLO || ' : ' || MWWHNM AS WAREHOUSE \n"
                    + "FROM M3FDBPRD.MITWHL";

                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("MWWHLO", mRes.getString(1).trim());
                    mMap.put("MWWHNM", mRes.getString(2).trim());
                    mMap.put("WAREHOUSE", mRes.getString(3).trim());
                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }
    public static JSONArray FacilityInventory(String cono, String divi) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT A1CONO,A1DIVI,A1CODE,A1DESC,A1NAME,A1TYPE,A1MUUN,A1RATE,COALESCE(A1REF1,'') AS A1REF1,COALESCE(A1REF2,'') AS A1REF2,A1CODE || ' : ' || A1DESC AS ALLOCATION\n"
                        + "FROM BRLDTA0100.A1CASU\n"
                        + "WHERE A1CONO = '" + cono + "'\n"
                        + "AND A1DIVI = '" + divi + "'\n"
                        + "AND A1CODE = '300'\n"
                        + "ORDER BY A1CODE";
                System.out.println("SelectFacility\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("A1CONO", mRes.getString(1).trim());
                    mMap.put("A1DIVI", mRes.getString(2).trim());
                    mMap.put("A1CODE", mRes.getString(3).trim());
                    mMap.put("A1DESC", mRes.getString(4).trim());
                    mMap.put("A1NAME", mRes.getString(5).trim());
                    mMap.put("A1TYPE", mRes.getString(6).trim());
                    mMap.put("A1MUUN", mRes.getString(7).trim());
                    mMap.put("A1RATE", mRes.getString(8).trim());
                    mMap.put("A1REF1", mRes.getString(9).trim());
                    mMap.put("A1REF2", mRes.getString(10).trim());
                    mMap.put("ALLOCATION", mRes.getString(11).trim());
                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray Type(String cono, String divi) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT ATCONO,ATDIVI,ATTYPE,ATNAME,ATREF1,ATTYPE || ' : ' || ATNAME AS TYPE\n"
                        + "FROM BRLDTA0100.A1TYPE\n"
                        + "WHERE ATCONO = '" + cono + "'\n"
                        + "AND ATDIVI = '" + divi + "'\n"
                        + "ORDER BY ATTYPE";
                System.out.println("SelectType\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("ATCONO", mRes.getString(1).trim());
                    mMap.put("ATDIVI", mRes.getString(2).trim());
                    mMap.put("ATTYPE", mRes.getString(3).trim());
                    mMap.put("ATNAME", mRes.getString(4).trim());
                    mMap.put("ATREF1", mRes.getString(5).trim());
                    mMap.put("TYPE", mRes.getString(6).trim());
                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray Level(String cono, String divi) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT A2CONO,A2DIVI,A2CODE,A2STID,A2SLVL,A2DESC,COALESCE(A2REF1,'') AS A2REF1,COALESCE(A2REF2,'') AS A2REF2\n"
                        + "FROM BRLDTA0100.A2CASL\n"
                        + "WHERE A2CONO = '" + cono + "'\n"
                        + "AND A2DIVI = '" + divi + "'\n"
                        + "ORDER BY A2CODE";
                System.out.println("SelectLevel\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("A2CONO", mRes.getString(1).trim());
                    mMap.put("A2DIVI", mRes.getString(2).trim());
                    mMap.put("A2CODE", mRes.getString(3).trim());
                    mMap.put("A2STID", mRes.getString(4).trim());
                    mMap.put("A2SLVL", mRes.getString(5).trim());
                    mMap.put("A2DESC", mRes.getString(6).trim());
                    mMap.put("A2REF1", mRes.getString(7).trim());
                    mMap.put("A2REF2", mRes.getString(8).trim());
                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray Period(String cono, String divi) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT PECONO,PEDIVI,PECODE,PEYEA4,PEMONT,PEDESC\n"
                        + "FROM BRLDTA0100.APERIO\n"
                        + "WHERE PECONO = '" + cono + "'\n"
                        + "AND PEDIVI = '" + divi + "'\n"
                        + "ORDER BY PECODE,PEYEA4,PEMONT";
                System.out.println("SelectPeriod\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("PECONO", mRes.getString(1).trim());
                    mMap.put("PEDIVI", mRes.getString(2).trim());
                    mMap.put("PECODE", mRes.getString(3).trim());
                    mMap.put("PEYEA4", mRes.getString(4).trim());
                    mMap.put("PEMONT", mRes.getString(5).trim());
                    mMap.put("PEDESC", mRes.getString(6).trim());
                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray WorkCenter(String cono, String divi) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT A3CONO,A3DIVI,A3CODE,A3STID,CHAR(A3SLVL) AS A3SLVL,A3AITM,A3ASTR,A3ADES,A3METY,A3UEHR,A3MELA,A3PERS,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                        + "FROM BRLDTA0100.A3CAWC\n"
                        + "WHERE A3CONO = '" + cono + "'\n"
                        + "AND A3DIVI = '" + divi + "'\n"
                        + "ORDER BY A3CODE,A3STID,A3AITM";
                System.out.println("SelectWorkCenter\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("A3CONO", mRes.getString(1).trim());
                    mMap.put("A3DIVI", mRes.getString(2).trim());
                    mMap.put("A3CODE", mRes.getString(3).trim());
                    mMap.put("A3STID", mRes.getString(4).trim());
                    mMap.put("A3SLVL", mRes.getString(5).trim());
                    mMap.put("A3AITM", mRes.getString(6).trim());
                    mMap.put("A3ASTR", mRes.getString(7).trim());
                    mMap.put("A3ADES", mRes.getString(8).trim());
                    mMap.put("A3METY", mRes.getString(9).trim());
                    mMap.put("A3UEHR", mRes.getString(10).trim());
                    mMap.put("A3MELA", mRes.getString(11).trim());
                    mMap.put("A3PERS", mRes.getString(12).trim());
                    mMap.put("A3ACCT", mRes.getString(13).trim());
                    mMap.put("A3AAC1", mRes.getString(14).trim());
                    mMap.put("A3AAC2", mRes.getString(15).trim());
                    mMap.put("A3ABOI", mRes.getString(16).trim());
                    mMap.put("A3ARE1", mRes.getString(17).trim());
                    mMap.put("A3ARE2", mRes.getString(18).trim());
                    mMap.put("A3ARE3", mRes.getString(19).trim());
                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray LevelDetail(String cono, String divi, String code, String struct, String level) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT A3CONO,A3DIVI,A3CODE,A3STID,A3SLVL,A3AITM,A3ASTR,A3ADES,A3METY,A3UEHR,A3MELA,A3PERS,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                        + "FROM BRLDTA0100.A3CAWC\n"
                        + "WHERE A3CONO = '" + cono + "'\n"
                        + "AND A3DIVI = '" + divi + "'\n"
                        + "AND A3CODE = '" + code + "'\n"
                        + "AND A3STID = '" + struct + "'\n"
                        + "AND A3SLVL = '" + level + "'\n"
                        + "ORDER BY A3CODE,A3STID,A3AITM";
                System.out.println("SelectLevelDetail\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("A3CONO", mRes.getString(1).trim());
                    mMap.put("A3DIVI", mRes.getString(2).trim());
                    mMap.put("A3CODE", mRes.getString(3).trim());
                    mMap.put("A3STID", mRes.getString(4).trim());
                    mMap.put("A3SLVL", mRes.getString(5).trim());
                    mMap.put("A3AITM", mRes.getString(6).trim());
                    mMap.put("A3ASTR", mRes.getString(7).trim());
                    mMap.put("A3ADES", mRes.getString(8).trim());
                    mMap.put("A3METY", mRes.getString(9).trim());
                    mMap.put("A3UEHR", mRes.getString(10).trim());
                    mMap.put("A3MELA", mRes.getString(11).trim());
                    mMap.put("A3PERS", mRes.getString(12).trim());
                    mMap.put("A3ACCT", mRes.getString(13).trim());
                    mMap.put("A3AAC1", mRes.getString(14).trim());
                    mMap.put("A3AAC2", mRes.getString(15).trim());
                    mMap.put("A3ABOI", mRes.getString(16).trim());
                    mMap.put("A3ARE1", mRes.getString(17).trim());
                    mMap.put("A3ARE2", mRes.getString(18).trim());
                    mMap.put("A3ARE3", mRes.getString(19).trim());
                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray Bills(String cono, String divi, String period, String type) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT E1CONO, E1DIVI, E1CODE,TRIM(b.A1CODE || ' : ' || b.A1DESC) AS DESC, E1PECO, E1METS, E1METE, E1EPQT, E1EPAT, E1EVAT, E1ETAT, E1RAAT\n"
                        + "FROM BRLDTA0100.E1ALLO a,BRLDTA0100.A1CASU b\n"
                        + "WHERE E1CONO = '" + cono + "'\n"
                        + "AND E1DIVI = '" + divi + "'\n"
                        + "AND E1PECO = '" + period + "'\n"
                        + "AND E1TYPE = '" + type + "'\n"
                        + "AND b.A1CONO = E1CONO\n"
                        + "AND b.A1CODE = E1CODE\n"
                        + "ORDER BY E1PECO,E1CODE";
                System.out.println("SelectBills\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("E1CONO", mRes.getString(1).trim());
                    mMap.put("E1DIVI", mRes.getString(2).trim());
                    mMap.put("E1CODE", mRes.getString(3).trim());
                    mMap.put("DESC", mRes.getString(4).trim());
                    mMap.put("E1PECO", mRes.getString(5).trim());
                    mMap.put("E1METS", mRes.getString(6).trim());
                    mMap.put("E1METE", mRes.getString(7).trim());
                    mMap.put("E1EPQT", mRes.getString(8).trim());
                    mMap.put("E1EPAT", mRes.getString(9).trim());
                    mMap.put("E1EVAT", mRes.getString(10).trim());
                    mMap.put("E1ETAT", mRes.getString(11).trim());
                    mMap.put("E1RAAT", mRes.getString(12).trim());
                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray BillsDetail(String cono, String divi, String code, String period) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT E1CONO, E1DIVI, E1CODE, E1PECO, E1METS, E1METE, E1EPQT, E1EPAT, E1EVAT, E1ETAT, E1RAAT\n"
                        + "FROM BRLDTA0100.E1ALLO\n"
                        + "WHERE E1CONO = '" + cono + "'\n"
                        + "AND E1DIVI = '" + divi + "'\n"
                        + "AND E1CODE = '" + code + "'\n"
                        + "AND E1PECO = '" + period + "'\n"
                        + "ORDER BY E1PECO,E1CODE";
                System.out.println("SelectBills\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("E1CONO", mRes.getString(1).trim());
                    mMap.put("E1DIVI", mRes.getString(2).trim());
                    mMap.put("E1CODE", mRes.getString(3).trim());
                    mMap.put("E1PECO", mRes.getString(4).trim());
                    mMap.put("E1METS", mRes.getString(5).trim());
                    mMap.put("E1METE", mRes.getString(6).trim());
                    mMap.put("E1EPQT", mRes.getString(7).trim());
                    mMap.put("E1EPAT", mRes.getString(8).trim());
                    mMap.put("E1EVAT", mRes.getString(9).trim());
                    mMap.put("E1ETAT", mRes.getString(10).trim());
                    mMap.put("E1RAAT", mRes.getString(11).trim());
                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray Allocation(String cono, String divi, String code, String period, String fromstatus, String tostatus, String type) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT E2CONO,E2DIVI,E2CODE,E2STID,E2SLVL,A2DESC,E2PECO,E2STAT\n"
                        + "FROM BRLDTA0100.E2ALLO,BRLDTA0100.A2CASL\n"
                        + "WHERE E2CONO = '" + cono + "'\n"
                        + "AND E2DIVI = '" + divi + "'\n"
                        + "AND E2CODE = '" + code + "'\n"
                        + "AND E2PECO = '" + period + "'\n"
                        + "AND E2TYPE = '" + type + "'\n"
                        + "AND A2CONO = E2CONO\n"
                        + "AND A2DIVI = E2DIVI\n"
                        + "AND A2STID = E2STID\n"
                        + "AND A2CODE = E2CODE\n"
                        + "AND E2STAT BETWEEN '" + fromstatus + "' AND '" + tostatus + "'\n"
                        + "GROUP BY E2CONO,E2DIVI,E2CODE,E2STID,E2SLVL,A2DESC,E2PECO,E2STAT\n"
                        + "ORDER BY E2CODE,E2PECO,E2STID,E2SLVL";
                System.out.println("SelectAllocation\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("E2CONO", mRes.getString(1).trim());
                    mMap.put("E2DIVI", mRes.getString(2).trim());
                    mMap.put("E2CODE", mRes.getString(3).trim());
                    mMap.put("E2STID", mRes.getString(4).trim());
                    mMap.put("E2SLVL", mRes.getString(5).trim());
                    mMap.put("A2DESC", mRes.getString(6).trim());
                    mMap.put("E2PECO", mRes.getString(7).trim());
                    mMap.put("E2STAT", mRes.getString(8).trim());
                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray AllocationDetail(String cono, String divi, String code, String struct, String level, String period, String type) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT E2CONO,E2DIVI,E2CODE,E2STID,E2SLVL,E2AITM,A3ADES,E2PECO,E2METS,E2METE,E2UEHR,E2HOUR,CAST(E2EPQT AS DECIMAL(15,2)) AS E2EPQT,CAST(E2PERS AS DECIMAL(15,2)) AS E2PERS,ROUND(E2EAQT,4) AS E2EAQT,ROUND(E2EAAT,4) AS E2EAAT,E2STAT\n"
                        + "FROM BRLDTA0100.E2ALLO,BRLDTA0100.A3CAWC\n"
                        + "WHERE E2CONO = '" + cono + "'\n"
                        + "AND E2DIVI = '" + divi + "'\n"
                        + "AND E2CODE = '" + code + "'\n"
                        + "AND E2STID = '" + struct + "'\n"
                        + "AND E2SLVL = '" + level + "'\n"
                        + "AND E2PECO = '" + period + "'\n"
                        + "AND E2TYPE = '" + type + "'\n"
                        + "AND A3CONO = E2CONO\n"
                        + "AND A3DIVI = E2DIVI\n"
                        + "AND A3CODE = E2CODE\n"
                        + "AND A3AITM = E2AITM\n"
                        + "ORDER BY E2STID,E2SLVL,E2AITM";
                System.out.println("SelectAllocationDetail\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("E2CONO", mRes.getString(1).trim());
                    mMap.put("E2DIVI", mRes.getString(2).trim());
                    mMap.put("E2CODE", mRes.getString(3).trim());
                    mMap.put("E2STID", mRes.getString(4).trim());
                    mMap.put("E2SLVL", mRes.getString(5).trim());
                    mMap.put("E2AITM", mRes.getString(6).trim());
                    mMap.put("A3ADES", mRes.getString(7).trim());
                    mMap.put("E2PECO", mRes.getString(8).trim());
                    mMap.put("E2METS", mRes.getString(9).trim());
                    mMap.put("E2METE", mRes.getString(10).trim());
                    mMap.put("E2UEHR", mRes.getString(11).trim());
                    mMap.put("E2HOUR", mRes.getString(12).trim());
                    mMap.put("E2EPQT", mRes.getString(13).trim());
                    mMap.put("E2PERS", mRes.getString(14).trim());
                    mMap.put("E2EAQT", mRes.getString(15).trim());
                    mMap.put("E2EAAT", mRes.getString(16).trim());
                    mMap.put("E2STAT", mRes.getString(17).trim());
                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray Voucher(String cono, String divi, String code, String period, String type) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT NO,a.A3ACCT AS ACCC,a.A3AAC1 AS COST,'' AS BONO,SUBSTRING(A3ADES,0,9) AS REF1,A3ARE2 AS REF2,A3ARE3 AS REF3,'Accu. ' || TRIM(c.A1DESC) ||  ' for ' || TRIM(b.E2PECO) AS VOUC,ROUND(b.E2EAAT,4) AS AMT,b.E2STAT\n"
                        + "FROM\n"
                        + "(SELECT NO,A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                        + "FROM \n"
                        + "(SELECT '1' AS NO,A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                        + "FROM BRLDTA0100.A3CAWC\n"
                        + "WHERE A3CONO = '" + cono + "'\n"
                        + "AND A3DIVI = '" + divi + "'\n"
                        + "AND A3CODE = '" + code + "'\n"
                        + "AND A3SLVL = '2'\n"
                        + "GROUP BY A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                        + "ORDER BY A3STID,A3AITM) a\n"
                        + "UNION ALL \n"
                        + "(SELECT '2' AS NO,A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                        + "FROM BRLDTA0100.A3CAWC a\n"
                        + "WHERE A3CONO = '" + cono + "'\n"
                        + "AND A3DIVI = '" + divi + "'\n"
                        + "AND A3CODE = '" + code + "'\n"
                        + "AND A3SLVL = '1'\n"
                        + "AND A3ASTR = '0'\n"
                        + "GROUP BY A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                        + "ORDER BY A3STID,A3AITM)) AS a\n"
                        + "INNER JOIN\n"
                        + "(SELECT E2CONO, E2DIVI, E2CODE, E2STID, E2SLVL, E2AITM, E2PECO, E2METS, E2METE, E2UEHR, E2HOUR, E2EPQT, E2PERS, E2EAQT, E2EAAT, E2STAT\n"
                        + "FROM BRLDTA0100.E2ALLO a\n"
                        + "WHERE E2CONO = '" + cono + "'\n"
                        + "AND E2DIVI = '" + divi + "'\n"
                        + "AND E2CODE = '" + code + "'\n"
                        + "AND E2PECO = '" + period + "'\n"
                        + "AND E2STAT = '10'\n"
                        + "AND E2TYPE = '" + type + "'\n"
                        //+ "AND E2EAAT > 0\n"
                        + ") AS b\n"
                        + "ON b.E2AITM = a.A3AITM\n"
                        + "INNER JOIN\n"
                        + "(SELECT A1CONO, A1DIVI, A1CODE, A1DESC, A1NAME, A1TYPE, A1MUUN, A1RATE, A1REF1, A1REF2\n"
                        + "FROM BRLDTA0100.A1CASU) AS c\n"
                        + "ON c.A1CONO = b.E2CONO\n"
                        + "AND c.A1DIVI = b.E2DIVI\n"
                        + "AND c.A1CODE = b.E2CODE\n"
                        + "UNION ALL\n"
                        + "SELECT '3' AS NO,c.A1REF1 AS ACCC,'' AS COST,'' AS BONO,'HO' AS REF1,CHAR(b.E2PECO) AS REF2,'' AS REF3,'Accu. ' || TRIM(c.A1DESC) ||  ' for ' || TRIM(b.E2PECO) AS VOUC,ROUND(SUM(E2EAAT) * -1,4) AS AMT,b.E2STAT\n"
                        + "FROM BRLDTA0100.A3CAWC a,BRLDTA0100.E2ALLO b,BRLDTA0100.A1CASU c\n"
                        + "WHERE A3CONO = '" + cono + "'\n"
                        + "AND A3DIVI = '" + divi + "'\n"
                        + "AND A3CODE = '" + code + "'\n"
                        + "AND A3ASTR = '0'\n"
                        + "AND b.E2CONO = A3CONO\n"
                        + "AND b.E2DIVI = A3DIVI\n"
                        + "AND b.E2CODE = A3CODE\n"
                        + "AND b.E2AITM = A3AITM\n"
                        + "AND b.E2PECO = '" + period + "'\n"
                        + "AND b.E2STAT = '10'\n"
                        + "AND b.E2TYPE = '" + type + "'\n"
                        + "AND c.A1CONO = A3CONO\n"
                        + "AND c.A1DIVI = A3DIVI\n"
                        + "AND c.A1CODE = A3CODE\n"
                        + "GROUP BY c.A1REF1,c.A1DESC,b.E2PECO,b.E2STAT\n"
                        + "ORDER BY NO";
                System.out.println("Voucher\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("NO", mRes.getString(1).trim());
                    mMap.put("ACCC", mRes.getString(2).trim());
                    mMap.put("COST", mRes.getString(3).trim());
                    mMap.put("BONO", mRes.getString(4).trim());
                    mMap.put("REF1", mRes.getString(5).trim());
                    mMap.put("REF2", mRes.getString(6).trim());
                    mMap.put("REF3", mRes.getString(7).trim());
                    mMap.put("VOUC", mRes.getString(8).trim());
                    mMap.put("AMT", mRes.getString(9).trim());
                    mMap.put("E2STAT", mRes.getString(10).trim());
                    mJSonArr.put(mMap);
//                    System.out.println(mJSonArr);
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

        return mJSonArr;

    }

    public static JSONArray VoucherVariance(String cono, String divi, String code, String period, String type) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT NO,a.A3ACCT AS ACCC,COALESCE(a.A3AAC1,'') AS COST,'' AS BONO,SUBSTRING(A3ADES,0,9) AS REF1,A3ARE2 AS REF2,A3ARE3 AS REF3,'Var. ' || TRIM(c.A1DESC) ||  ' for ' || SUBSTRING(CHAR(DATE(SUBSTRING(b.E2PECO,0,5)||'-'||SUBSTRING(b.E2PECO,5,2)||'-'||'01') - 1 MONTH, ISO),0,5)||SUBSTRING(CHAR(DATE(SUBSTRING(b.E2PECO,0,5)||'-'||SUBSTRING(b.E2PECO,5,2)||'-'||'01') - 1 MONTH, ISO),6,2) AS VOUC,ROUND(b.E2EAAT,4) AS AMT,b.E2STAT\n"
                        + "FROM\n"
                        + "(SELECT NO,A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                        + "FROM \n"
                        + "(SELECT '1' AS NO,A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                        + "FROM BRLDTA0100.A3CAWC\n"
                        + "WHERE A3CONO = '" + cono + "'\n"
                        + "AND A3DIVI = '" + divi + "'\n"
                        + "AND A3CODE = '" + code + "'\n"
                        + "AND A3SLVL = '2'\n"
                        + "GROUP BY A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                        + "ORDER BY A3STID,A3AITM) a\n"
                        + "UNION ALL \n"
                        + "(SELECT '2' AS NO,A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                        + "FROM BRLDTA0100.A3CAWC a\n"
                        + "WHERE A3CONO = '" + cono + "'\n"
                        + "AND A3DIVI = '" + divi + "'\n"
                        + "AND A3CODE = '" + code + "'\n"
                        + "AND A3SLVL = '1'\n"
                        + "AND A3ASTR = '0'\n"
                        + "GROUP BY A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                        + "ORDER BY A3STID,A3AITM)) AS a\n"
                        + "INNER JOIN\n"
                        + "(SELECT E2CONO, E2DIVI, E2CODE, E2STID, E2SLVL, E2AITM, E2PECO, E2METS, E2METE, E2UEHR, E2HOUR, E2EPQT, E2PERS, E2EAQT, E2EAAT, E2STAT\n"
                        + "FROM BRLDTA0100.E2ALLO a\n"
                        + "WHERE E2CONO = '" + cono + "'\n"
                        + "AND E2DIVI = '" + divi + "'\n"
                        + "AND E2CODE = '" + code + "'\n"
                        + "AND E2PECO = '" + period + "'\n"
                        + "AND E2STAT = '10'\n"
                        + "AND E2TYPE = '" + type + "'\n"
                        //+ "AND E2EAAT > 0\n"
                        + ") AS b\n"
                        + "ON b.E2AITM = a.A3AITM\n"
                        + "INNER JOIN\n"
                        + "(SELECT A1CONO, A1DIVI, A1CODE, A1DESC, A1NAME, A1TYPE, A1MUUN, A1RATE, A1REF1, A1REF2, A1REF3\n"
                        + "FROM BRLDTA0100.A1CASU) AS c\n"
                        + "ON c.A1CONO = b.E2CONO\n"
                        + "AND c.A1DIVI = b.E2DIVI\n"
                        + "AND c.A1CODE = b.E2CODE\n"
                        + "UNION ALL\n"
                        + "SELECT '3' AS NO,c.A1REF2 AS ACCC,COALESCE(c.A1REF3,'') AS COST,'' AS BONO,'HO' AS REF1,CHAR(b.E2PECO) AS REF2,'' AS REF3,'Var. ' || TRIM(c.A1DESC) ||  ' for ' || SUBSTRING(CHAR(DATE(SUBSTRING(b.E2PECO,0,5)||'-'||SUBSTRING(b.E2PECO,5,2)||'-'||'01') - 1 MONTH, ISO),0,5)||SUBSTRING(CHAR(DATE(SUBSTRING(b.E2PECO,0,5)||'-'||SUBSTRING(b.E2PECO,5,2)||'-'||'01') - 1 MONTH, ISO),6,2) AS VOUC,ROUND(SUM(E2EAAT) * -1,4) AS AMT,b.E2STAT\n"
                        + "FROM BRLDTA0100.A3CAWC a,BRLDTA0100.E2ALLO b,BRLDTA0100.A1CASU c\n"
                        + "WHERE A3CONO = '" + cono + "'\n"
                        + "AND A3DIVI = '" + divi + "'\n"
                        + "AND A3CODE = '" + code + "'\n"
                        + "AND A3ASTR = '0'\n"
                        + "AND b.E2CONO = A3CONO\n"
                        + "AND b.E2DIVI = A3DIVI\n"
                        + "AND b.E2CODE = A3CODE\n"
                        + "AND b.E2AITM = A3AITM\n"
                        + "AND b.E2PECO = '" + period + "'\n"
                        + "AND b.E2STAT = '10'\n"
                        + "AND b.E2TYPE = '" + type + "'\n"
                        + "AND c.A1CONO = A3CONO\n"
                        + "AND c.A1DIVI = A3DIVI\n"
                        + "AND c.A1CODE = A3CODE\n"
                        + "GROUP BY c.A1REF2,c.A1REF3,c.A1DESC,b.E2PECO,b.E2STAT\n"
                        + "ORDER BY NO";
                System.out.println("Voucher\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("NO", mRes.getString(1).trim());
                    mMap.put("ACCC", mRes.getString(2).trim());
                    mMap.put("COST", mRes.getString(3).trim());
                    mMap.put("BONO", mRes.getString(4).trim());
                    mMap.put("REF1", mRes.getString(5).trim());
                    mMap.put("REF2", mRes.getString(6).trim());
                    mMap.put("REF3", mRes.getString(7).trim());
                    mMap.put("VOUC", mRes.getString(8).trim());
                    mMap.put("AMT", mRes.getString(9).trim());
                    mMap.put("E2STAT", mRes.getString(10).trim());
                    mJSonArr.put(mMap);
//                    System.out.println(mJSonArr);
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

        return mJSonArr;

    }

    public static List<String> RSVoucher(String cono, String divi, String code, String period, String type) {

        List<String> getList = new ArrayList<String>();
        Connection conn = null;
        try {
            conn = ConnectDB2.ConnectionDB();
            Statement stmt = conn.createStatement();
            String query = "SELECT NO,a.A3ACCT AS ACCC,a.A3AAC1 AS COST,'' AS BONO,SUBSTRING(A3ADES,0,9) AS REF1,A3ARE2 AS REF2,A3ARE3 AS REF3,'Accu. ' || TRIM(c.A1DESC) ||  ' for ' || TRIM(b.E2PECO) AS VOUC,ROUND(b.E2EAAT,4) AS AMT,b.E2STAT\n"
                    + "FROM\n"
                    + "(SELECT NO,A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                    + "FROM \n"
                    + "(SELECT '1' AS NO,A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                    + "FROM BRLDTA0100.A3CAWC\n"
                    + "WHERE A3CONO = '" + cono + "'\n"
                    + "AND A3DIVI = '" + divi + "'\n"
                    + "AND A3CODE = '" + code + "'\n"
                    + "AND A3SLVL = '2'\n"
                    + "GROUP BY A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                    + "ORDER BY A3STID,A3AITM) a\n"
                    + "UNION ALL \n"
                    + "(SELECT '2' AS NO,A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                    + "FROM BRLDTA0100.A3CAWC a\n"
                    + "WHERE A3CONO = '" + cono + "'\n"
                    + "AND A3DIVI = '" + divi + "'\n"
                    + "AND A3CODE = '" + code + "'\n"
                    + "AND A3SLVL = '1'\n"
                    + "AND A3ASTR = '0'\n"
                    + "GROUP BY A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                    + "ORDER BY A3STID,A3AITM)) AS a\n"
                    + "INNER JOIN\n"
                    + "(SELECT E2CONO, E2DIVI, E2CODE, E2STID, E2SLVL, E2AITM, E2PECO, E2METS, E2METE, E2UEHR, E2HOUR, E2EPQT, E2PERS, E2EAQT, E2EAAT, E2STAT\n"
                    + "FROM BRLDTA0100.E2ALLO a\n"
                    + "WHERE E2CONO = '" + cono + "'\n"
                    + "AND E2DIVI = '" + divi + "'\n"
                    + "AND E2CODE = '" + code + "'\n"
                    + "AND E2PECO = '" + period + "'\n"
                    + "AND E2STAT = '10'\n"
                    + "AND E2TYPE = '" + type + "'\n"
                    + "AND E2EAAT > 0\n"
                    + ") AS b\n"
                    + "ON b.E2AITM = a.A3AITM\n"
                    + "INNER JOIN\n"
                    + "(SELECT A1CONO, A1DIVI, A1CODE, A1DESC, A1NAME, A1TYPE, A1MUUN, A1RATE, A1REF1, A1REF2\n"
                    + "FROM BRLDTA0100.A1CASU) AS c\n"
                    + "ON c.A1CONO = b.E2CONO\n"
                    + "AND c.A1DIVI = b.E2DIVI\n"
                    + "AND c.A1CODE = b.E2CODE\n"
                    + "UNION ALL\n"
                    + "SELECT '3' AS NO,c.A1REF1 AS ACCC,'' AS COST,'' AS BONO,'HO' AS REF1,CHAR(b.E2PECO) AS REF2,'' AS REF3,'Accu. ' || TRIM(c.A1DESC) ||  ' for ' || TRIM(b.E2PECO) AS VOUC,ROUND(SUM(E2EAAT) * -1,4) AS AMT,b.E2STAT\n"
                    + "FROM BRLDTA0100.A3CAWC a,BRLDTA0100.E2ALLO b,BRLDTA0100.A1CASU c\n"
                    + "WHERE A3CONO = '" + cono + "'\n"
                    + "AND A3DIVI = '" + divi + "'\n"
                    + "AND A3CODE = '" + code + "'\n"
                    + "AND A3ASTR = '0'\n"
                    + "AND b.E2CONO = A3CONO\n"
                    + "AND b.E2DIVI = A3DIVI\n"
                    + "AND b.E2CODE = A3CODE\n"
                    + "AND b.E2AITM = A3AITM\n"
                    + "AND b.E2PECO = '" + period + "'\n"
                    + "AND b.E2STAT = '10'\n"
                    + "AND b.E2TYPE = '" + type + "'\n"
                    + "AND c.A1CONO = A3CONO\n"
                    + "AND c.A1DIVI = A3DIVI\n"
                    + "AND c.A1CODE = A3CODE\n"
                    + "GROUP BY c.A1REF1,c.A1DESC,b.E2PECO,b.E2STAT\n"
                    + "ORDER BY NO";
            System.out.println("RSVoucher\n" + query);
            ResultSet mRes = stmt.executeQuery(query);

            while (mRes.next()) {
                getList.add(
                        mRes.getString("NO").trim() + " ; "
                        + mRes.getString("ACCC").trim() + " ; "
                        + mRes.getString("COST").trim() + " ; "
                        + mRes.getString("BONO").trim() + " ; "
                        + mRes.getString("REF1").trim() + " ; "
                        + mRes.getString("REF2").trim() + " ; "
                        + mRes.getString("REF3").trim() + " ; "
                        + mRes.getString("VOUC").trim() + " ; "
                        + mRes.getString("AMT").trim() + " ; "
                        + mRes.getString("E2STAT").trim());
            }
            return getList;

        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
            }
        }
        return null;
    }

    public static List<String> RSVoucherVariance(String cono, String divi, String code, String period, String type) {

        List<String> getList = new ArrayList<String>();
        Connection conn = null;
        try {
            conn = ConnectDB2.ConnectionDB();
            Statement stmt = conn.createStatement();
            String query = "SELECT NO,a.A3ACCT AS ACCC,a.A3AAC1 AS COST,'' AS BONO,SUBSTRING(A3ADES,0,9) AS REF1,A3ARE2 AS REF2,A3ARE3 AS REF3,'Var. ' || TRIM(c.A1DESC) ||  ' for ' || SUBSTRING(CHAR(DATE(SUBSTRING(b.E2PECO,0,5)||'-'||SUBSTRING(b.E2PECO,5,2)||'-'||'01') - 1 MONTH, ISO),0,5)||SUBSTRING(CHAR(DATE(SUBSTRING(b.E2PECO,0,5)||'-'||SUBSTRING(b.E2PECO,5,2)||'-'||'01') - 1 MONTH, ISO),6,2) AS VOUC,ROUND(b.E2EAAT,4) AS AMT,b.E2STAT\n"
                    + "FROM\n"
                    + "(SELECT NO,A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                    + "FROM \n"
                    + "(SELECT '1' AS NO,A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                    + "FROM BRLDTA0100.A3CAWC\n"
                    + "WHERE A3CONO = '" + cono + "'\n"
                    + "AND A3DIVI = '" + divi + "'\n"
                    + "AND A3CODE = '" + code + "'\n"
                    + "AND A3SLVL = '2'\n"
                    + "GROUP BY A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                    + "ORDER BY A3STID,A3AITM) a\n"
                    + "UNION ALL \n"
                    + "(SELECT '2' AS NO,A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                    + "FROM BRLDTA0100.A3CAWC a\n"
                    + "WHERE A3CONO = '" + cono + "'\n"
                    + "AND A3DIVI = '" + divi + "'\n"
                    + "AND A3CODE = '" + code + "'\n"
                    + "AND A3SLVL = '1'\n"
                    + "AND A3ASTR = '0'\n"
                    + "GROUP BY A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                    + "ORDER BY A3STID,A3AITM)) AS a\n"
                    + "INNER JOIN\n"
                    + "(SELECT E2CONO, E2DIVI, E2CODE, E2STID, E2SLVL, E2AITM, E2PECO, E2METS, E2METE, E2UEHR, E2HOUR, E2EPQT, E2PERS, E2EAQT, E2EAAT, E2STAT\n"
                    + "FROM BRLDTA0100.E2ALLO a\n"
                    + "WHERE E2CONO = '" + cono + "'\n"
                    + "AND E2DIVI = '" + divi + "'\n"
                    + "AND E2CODE = '" + code + "'\n"
                    + "AND E2PECO = '" + period + "'\n"
                    + "AND E2STAT = '10'\n"
                    + "AND E2TYPE = '" + type + "'\n"
                    //                    + "AND E2EAAT > 0\n"
                    + ") AS b\n"
                    + "ON b.E2AITM = a.A3AITM\n"
                    + "INNER JOIN\n"
                    + "(SELECT A1CONO, A1DIVI, A1CODE, A1DESC, A1NAME, A1TYPE, A1MUUN, A1RATE, A1REF1, A1REF2, A1REF3\n"
                    + "FROM BRLDTA0100.A1CASU) AS c\n"
                    + "ON c.A1CONO = b.E2CONO\n"
                    + "AND c.A1DIVI = b.E2DIVI\n"
                    + "AND c.A1CODE = b.E2CODE\n"
                    + "UNION ALL\n"
                    + "SELECT '3' AS NO,c.A1REF2 AS ACCC,c.A1REF3 AS COST,'' AS BONO,'HO' AS REF1,CHAR(b.E2PECO) AS REF2,'' AS REF3,'Var. ' || TRIM(c.A1DESC) ||  ' for ' || SUBSTRING(CHAR(DATE(SUBSTRING(b.E2PECO,0,5)||'-'||SUBSTRING(b.E2PECO,5,2)||'-'||'01') - 1 MONTH, ISO),0,5)||SUBSTRING(CHAR(DATE(SUBSTRING(b.E2PECO,0,5)||'-'||SUBSTRING(b.E2PECO,5,2)||'-'||'01') - 1 MONTH, ISO),6,2) AS VOUC,ROUND(SUM(E2EAAT) * -1,4) AS AMT,b.E2STAT\n"
                    + "FROM BRLDTA0100.A3CAWC a,BRLDTA0100.E2ALLO b,BRLDTA0100.A1CASU c\n"
                    + "WHERE A3CONO = '" + cono + "'\n"
                    + "AND A3DIVI = '" + divi + "'\n"
                    + "AND A3CODE = '" + code + "'\n"
                    + "AND A3ASTR = '0'\n"
                    + "AND b.E2CONO = A3CONO\n"
                    + "AND b.E2DIVI = A3DIVI\n"
                    + "AND b.E2CODE = A3CODE\n"
                    + "AND b.E2AITM = A3AITM\n"
                    + "AND b.E2PECO = '" + period + "'\n"
                    + "AND b.E2STAT = '10'\n"
                    + "AND b.E2TYPE = '" + type + "'\n"
                    + "AND c.A1CONO = A3CONO\n"
                    + "AND c.A1DIVI = A3DIVI\n"
                    + "AND c.A1CODE = A3CODE\n"
                    + "GROUP BY c.A1REF2,c.A1REF3,c.A1DESC,b.E2PECO,b.E2STAT\n"
                    + "ORDER BY NO";
            System.out.println("RSVoucherVariance\n" + query);
            ResultSet mRes = stmt.executeQuery(query);

            while (mRes.next()) {
                getList.add(
                        mRes.getString("NO").trim() + " ; "
                        + mRes.getString("ACCC").trim() + " ; "
                        + mRes.getString("COST").trim() + " ; "
                        + mRes.getString("BONO").trim() + " ; "
                        + mRes.getString("REF1").trim() + " ; "
                        + mRes.getString("REF2").trim() + " ; "
                        + mRes.getString("REF3").trim() + " ; "
                        + mRes.getString("VOUC").trim() + " ; "
                        + mRes.getString("AMT").trim() + " ; "
                        + mRes.getString("E2STAT").trim());
            }
            return getList;

        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
            }
        }
        return null;
    }

    public static JSONArray Inventory(String cono, String divi, String code, String period, String type) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT NO,a.A3ACCT AS ACCC,a.A3AAC1 AS COST,A3ADES AS DESC,A3ARE1 AS REF1,A3ARE2 AS REF2,A3ARE3 AS REF3,'Accu. ' || TRIM(c.A1DESC) ||  ' for ' || TRIM(b.E2PECO) AS VOUC,ROUND(b.E2EAQT,4) AS QTY,b.E2STAT\n"
                        + "FROM\n"
                        + "(SELECT NO,A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                        + "FROM \n"
                        + "(SELECT '1' AS NO,A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                        + "FROM BRLDTA0100.A3CAWC\n"
                        + "WHERE A3CONO = '" + cono + "'\n"
                        + "AND A3DIVI = '" + divi + "'\n"
                        + "AND A3CODE = '" + code + "'\n"
                        + "AND A3SLVL = '2'\n"
                        + "GROUP BY A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                        + "ORDER BY A3STID,A3AITM) a\n"
                        + "UNION ALL \n"
                        + "(SELECT '2' AS NO,A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                        + "FROM BRLDTA0100.A3CAWC a\n"
                        + "WHERE A3CONO = '" + cono + "'\n"
                        + "AND A3DIVI = '" + divi + "'\n"
                        + "AND A3CODE = '" + code + "'\n"
                        + "AND A3SLVL = '1'\n"
                        + "AND A3ASTR = '0'\n"
                        + "GROUP BY A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                        + "ORDER BY A3STID,A3AITM)) AS a\n"
                        + "INNER JOIN\n"
                        + "(SELECT E2CONO, E2DIVI, E2CODE, E2STID, E2SLVL, E2AITM, E2PECO, E2METS, E2METE, E2UEHR, E2HOUR, E2EPQT, E2PERS, E2EAQT, E2EAAT, E2STAT\n"
                        + "FROM BRLDTA0100.E2ALLO a\n"
                        + "WHERE E2CONO = '" + cono + "'\n"
                        + "AND E2DIVI = '" + divi + "'\n"
                        + "AND E2CODE = '" + code + "'\n"
                        + "AND E2PECO = '" + period + "'\n"
                        + "AND E2STAT = '10'\n"
                        //+ "AND E2EAAT > 0\n"
                        + "AND E2TYPE = '" + type + "'\n"
                        + ") AS b\n"
                        + "ON b.E2AITM = a.A3AITM\n"
                        + "INNER JOIN\n"
                        + "(SELECT A1CONO, A1DIVI, A1CODE, A1DESC, A1NAME, A1TYPE, A1MUUN, A1RATE, A1REF1, A1REF2\n"
                        + "FROM BRLDTA0100.A1CASU) AS c\n"
                        + "ON c.A1CONO = b.E2CONO\n"
                        + "AND c.A1DIVI = b.E2DIVI\n"
                        + "AND c.A1CODE = b.E2CODE\n"
                        + "ORDER BY NO";
                System.out.println("Inventory\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("NO", mRes.getString(1).trim());
                    mMap.put("ACCC", mRes.getString(2).trim());
                    mMap.put("COST", mRes.getString(3).trim());
                    mMap.put("DESC", mRes.getString(4).trim());
                    mMap.put("REF1", mRes.getString(5).trim());
                    mMap.put("REF2", mRes.getString(6).trim());
                    mMap.put("REF3", mRes.getString(7).trim());
                    mMap.put("VOUC", mRes.getString(8).trim());
                    mMap.put("QTY", mRes.getString(9).trim());
                    mMap.put("E2STAT", mRes.getString(10).trim());
                    mJSonArr.put(mMap);
//                    System.out.println(mJSonArr);
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

        return mJSonArr;

    }

    public static List<String> RSInventory(String cono, String divi, String code, String period) {

        List<String> getList = new ArrayList<String>();
        Connection conn = null;
        try {
            conn = ConnectDB2.ConnectionDB();
            Statement stmt = conn.createStatement();
            String query = "SELECT NO,a.A3ACCT AS ACCC,a.A3AAC1 AS COST,A3ADES AS DESC,A3ARE1 AS REF1,A3ARE2 AS REF2,A3ARE3 AS REF3,'Accu. ' || TRIM(c.A1DESC) ||  ' for ' || TRIM(b.E2PECO) AS VOUC,ROUND(b.E2EAQT,4) AS QTY,b.E2STAT\n"
                    + "FROM\n"
                    + "(SELECT NO,A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                    + "FROM \n"
                    + "(SELECT '1' AS NO,A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                    + "FROM BRLDTA0100.A3CAWC\n"
                    + "WHERE A3CONO = '" + cono + "'\n"
                    + "AND A3DIVI = '" + divi + "'\n"
                    + "AND A3CODE = '" + code + "'\n"
                    + "AND A3SLVL = '2'\n"
                    + "GROUP BY A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                    + "ORDER BY A3STID,A3AITM) a\n"
                    + "UNION ALL \n"
                    + "(SELECT '2' AS NO,A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                    + "FROM BRLDTA0100.A3CAWC a\n"
                    + "WHERE A3CONO = '" + cono + "'\n"
                    + "AND A3DIVI = '" + divi + "'\n"
                    + "AND A3CODE = '" + code + "'\n"
                    + "AND A3SLVL = '1'\n"
                    + "AND A3ASTR = '0'\n"
                    + "GROUP BY A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                    + "ORDER BY A3STID,A3AITM)) AS a\n"
                    + "INNER JOIN\n"
                    + "(SELECT E2CONO, E2DIVI, E2CODE, E2STID, E2SLVL, E2AITM, E2PECO, E2METS, E2METE, E2UEHR, E2HOUR, E2EPQT, E2PERS, E2EAQT, E2EAAT, E2STAT\n"
                    + "FROM BRLDTA0100.E2ALLO a\n"
                    + "WHERE E2CONO = '" + cono + "'\n"
                    + "AND E2DIVI = '" + divi + "'\n"
                    + "AND E2CODE = '" + code + "'\n"
                    + "AND E2PECO = '" + period + "'\n"
                    + "AND E2STAT = '10'\n"
                    + "AND E2EAQT > 0\n"
                    + ") AS b\n"
                    + "ON b.E2AITM = a.A3AITM\n"
                    + "INNER JOIN\n"
                    + "(SELECT A1CONO, A1DIVI, A1CODE, A1DESC, A1NAME, A1TYPE, A1MUUN, A1RATE, A1REF1, A1REF2\n"
                    + "FROM BRLDTA0100.A1CASU) AS c\n"
                    + "ON c.A1CONO = b.E2CONO\n"
                    + "AND c.A1DIVI = b.E2DIVI\n"
                    + "AND c.A1CODE = b.E2CODE\n"
                    + "ORDER BY NO";
            System.out.println("RSInventory\n" + query);
            ResultSet mRes = stmt.executeQuery(query);

            while (mRes.next()) {
                getList.add(
                        mRes.getString("NO").trim() + " ; "
                        + mRes.getString("ACCC").trim() + " ; "
                        + mRes.getString("COST").trim() + " ; "
                        + mRes.getString("DESC").trim() + " ; "
                        + mRes.getString("REF1").trim() + " ; "
                        + mRes.getString("REF2").trim() + " ; "
                        + mRes.getString("REF3").trim() + " ; "
                        + mRes.getString("VOUC").trim() + " ; "
                        + mRes.getString("QTY").trim() + " ; "
                        + mRes.getString("E2STAT").trim());
            }
            return getList;

        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
            }
        }
        return null;
    }

    public static List<String> RSInvenLot(String cono, String divi, String whs, String item) {

        List<String> getList = new ArrayList<String>();
        Connection conn = null;
        try {
            conn = ConnectDB2.ConnectionDB();
            Statement stmt = conn.createStatement();
            String query = "SELECT MLITNO,MLBANO,DECIMAL(MLSTQT-MLALQT,10,2) ONHAND\n"
                    + "FROM M3FDBPRD.MITLOC\n"
                    + "WHERE MLCONO = '" + cono + "'\n"
                    + "AND MLWHLO = '" + whs + "'\n"
                    + "AND MLSTAS = 2\n"
                    + "AND MLITNO = '" + item + "'\n"
                    + "ORDER BY MLPRDT";
            System.out.println("RSInvenLot\n" + query);
            ResultSet mRes = stmt.executeQuery(query);

            while (mRes.next()) {
                getList.add(
                        mRes.getString("MLITNO").trim() + " ; "
                        + mRes.getString("MLBANO").trim() + " ; "
                        + mRes.getString("ONHAND").trim());
            }
            return getList;

        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex.toString());
                }
            }
        }
        return null;
    }

    public static JSONArray Monitor(String cono, String divi, String code, String period, String type) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT NO,a.A3ACCT AS ACCC,a.A3AAC1 AS COST,'' AS BONO,A3ADES AS REF1,A3ARE2 AS REF2,A3ARE3 AS REF3,'Accu. ' || TRIM(c.A1DESC) ||  ' for ' || TRIM(b.E2PECO) AS VOUC,ROUND(b.E2EAQT,4) AS QTY,ROUND(b.E2EAAT,4) AS AMT,b.E2STAT\n"
                        + "FROM\n"
                        + "(SELECT NO,A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                        + "FROM \n"
                        + "(SELECT '1' AS NO,A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                        + "FROM BRLDTA0100.A3CAWC\n"
                        + "WHERE A3CONO = '" + cono + "'\n"
                        + "AND A3DIVI = '" + divi + "'\n"
                        + "AND A3CODE = '" + code + "'\n"
                        + "AND A3SLVL = '2'\n"
                        + "GROUP BY A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                        + "ORDER BY A3STID,A3AITM) a\n"
                        + "UNION ALL \n"
                        + "(SELECT '2' AS NO,A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                        + "FROM BRLDTA0100.A3CAWC a\n"
                        + "WHERE A3CONO = '" + cono + "'\n"
                        + "AND A3DIVI = '" + divi + "'\n"
                        + "AND A3CODE = '" + code + "'\n"
                        + "AND A3SLVL = '1'\n"
                        + "AND A3ASTR = '0'\n"
                        + "GROUP BY A3STID,A3AITM,A3ADES,A3ACCT,A3AAC1,A3AAC2,A3ABOI,A3ARE1,A3ARE2,A3ARE3\n"
                        + "ORDER BY A3STID,A3AITM)) AS a\n"
                        + "INNER JOIN\n"
                        + "(SELECT E2CONO, E2DIVI, E2CODE, E2STID, E2SLVL, E2AITM, E2PECO, E2METS, E2METE, E2UEHR, E2HOUR, E2EPQT, E2PERS, E2EAQT, E2EAAT, E2STAT\n"
                        + "FROM BRLDTA0100.E2ALLO a\n"
                        + "WHERE E2CONO = '" + cono + "'\n"
                        + "AND E2DIVI = '" + divi + "'\n"
                        + "AND E2CODE = '" + code + "'\n"
                        + "AND E2PECO = '" + period + "'\n"
                        //+ "AND E2STAT = '10'\n"
                        //+ "AND E2EAAT > 0\n"
                        + "AND E2TYPE = '" + type + "'\n"
                        + ") AS b\n"
                        + "ON b.E2AITM = a.A3AITM\n"
                        + "INNER JOIN\n"
                        + "(SELECT A1CONO, A1DIVI, A1CODE, A1DESC, A1NAME, A1TYPE, A1MUUN, A1RATE, A1REF1, A1REF2\n"
                        + "FROM BRLDTA0100.A1CASU) AS c\n"
                        + "ON c.A1CONO = b.E2CONO\n"
                        + "AND c.A1DIVI = b.E2DIVI\n"
                        + "AND c.A1CODE = b.E2CODE\n"
                        + "ORDER BY NO";
                System.out.println("Monitor\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("NO", mRes.getString(1).trim());
                    mMap.put("ACCC", mRes.getString(2).trim());
                    mMap.put("COST", mRes.getString(3).trim());
                    mMap.put("BONO", mRes.getString(4).trim());
                    mMap.put("REF1", mRes.getString(5).trim());
                    mMap.put("REF2", mRes.getString(6).trim());
                    mMap.put("REF3", mRes.getString(7).trim());
                    mMap.put("VOUC", mRes.getString(8).trim());
                    mMap.put("QTY", mRes.getString(9).trim());
                    mMap.put("AMT", mRes.getString(10).trim());
                    mMap.put("E2STAT", mRes.getString(11).trim());
                    mJSonArr.put(mMap);
//                    System.out.println(mJSonArr);
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

        return mJSonArr;

    }

}
