/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.data;

import com.br.connection.ConnectDB2;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Wattana
 */
public class Update {

    public static void Facility(String cono, String divi, String code, String desc, String name, String type, String muun, String rate, String ref1, String ref2) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "UPDATE BRLDTA0100.A1CASU\n"
                        + "SET A1DESC = '" + desc + "'\n"
                        + ",A1NAME = '" + name + "'\n"
                        + ",A1TYPE = '" + type + "'\n"
                        + ",A1MUUN = '" + muun + "'\n"
                        + ",A1RATE = '" + rate + "'\n"
                        + ",A1REF1 = '" + ref1 + "'\n"
                        + ",A1REF2 = '" + ref2 + "'\n"
                        + "WHERE A1CONO = '" + cono + "'\n"
                        + "AND A1DIVI = '" + divi + "'\n"
                        + "AND A1CODE = '" + code + "'";
                System.out.println("UpdateFacility\n" + query);
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
                String query = "UPDATE BRLDTA0100.A1TYPE\n"
                        + "SET ATNAME = '" + desc + "'\n"
                        + ",ATREF1 = '" + ref + "'\n"
                        + "WHERE ATCONO = '" + cono + "'\n"
                        + "AND ATDIVI = '" + divi + "'\n"
                        + "AND ATTYPE = '" + code + "'";
                System.out.println("UpdateType\n" + query);
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
                String query = "UPDATE BRLDTA0100.A2CASL\n"
                        + "SET A2SLVL = '" + level + "'\n"
                        + ",A2DESC = '" + desc + "'\n"
                        + ",A2REF1 = '" + ref1 + "'\n"
                        + ",A2REF2 = '" + ref2 + "'\n"
                        + "WHERE A2CONO = '" + cono + "'\n"
                        + "AND A2STID = '" + struct + "'\n"
                        + "AND A2DIVI = '" + divi + "'\n"
                        + "AND A2CODE = '" + code + "'";
                System.out.println("UpdateLevel\n" + query);
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
                String query = "UPDATE BRLDTA0100.APERIO\n"
                        + "SET PEYEA4 = '" + year + "'\n"
                        + ",PEMONT = '" + month + "'\n"
                        + ",PEDESC = '" + desc + "'\n"
                        + "WHERE PECONO = '" + cono + "'\n"
                        + "AND PEDIVI = '" + divi + "'\n"
                        + "AND PECODE = '" + code + "'";
                System.out.println("UpdatePeriod\n" + query);
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
                String query = "UPDATE BRLDTA0100.A3CAWC\n"
                        + "SET A3STID = '" + struct + "'\n"
                        + ",A3ASTR = '" + str + "'\n"
                        + ",A3ADES = '" + desc + "'\n"
                        + ",A3METY = '" + mety + "'\n"
                        + ",A3UEHR = '" + uehr + "'\n"
                        + ",A3MELA = '" + mela + "'\n"
                        + ",A3PERS = '" + per + "'\n"
                        + ",A3ACCT = '" + acc + "'\n"
                        + ",A3AAC1 = '" + aac1 + "'\n"
                        + ",A3AAC2 = '" + aac2 + "'\n"
                        + ",A3ABOI = '" + boi + "'\n"
                        + ",A3ARE1 = '" + ref1 + "'\n"
                        + ",A3ARE2 = '" + ref2 + "'\n"
                        + ",A3ARE3 = '" + ref3 + "'\n"
                        + "WHERE A3CONO = '" + cono + "'\n"
                        + "AND A3DIVI = '" + divi + "'\n"
                        + "AND A3CODE = '" + code + "'\n"
                        + "AND A3AITM = '" + itm + "'";
                System.out.println("UpdateWorkCenter\n" + query);
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

    public static void LevelWorkCenter(String cono, String divi, String code, String struct, String level, String itm, String str, String desc, String mety, String uehr, String mela, String per,
            String acc, String aac1, String aac2, String boi, String ref1, String ref2, String ref3) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "UPDATE BRLDTA0100.A3CAWC\n"
                        + "SET A3STID = '" + struct + "'\n"
                        + "WHERE A3CONO = '" + cono + "'\n"
                        + "AND A3DIVI = '" + divi + "'\n"
                        + "AND A3CODE = '" + code + "'\n"
                        + "AND A3AITM = '" + itm + "'";
                System.out.println("LevelWorkCenter\n" + query);
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
                String query = "UPDATE BRLDTA0100.E1ALLO\n"
                        + "SET E1METS = '" + meterstart + "'\n"
                        + ",E1METE = '" + meterend + "'\n"
                        + ",E1EPQT = '" + qty + "'\n"
                        + ",E1EPAT = '" + amount + "'\n"
                        + ",E1EVAT = '" + vat + "'\n"
                        + ",E1ETAT = '" + total + "'\n"
                        + ",E1RAAT = '" + totalamt + "'\n"
                        + "WHERE E1CONO = '" + cono + "'\n"
                        + "AND E1DIVI = '" + divi + "'\n"
                        + "AND E1CODE = '" + code + "'\n"
                        + "AND E1PECO = '" + period + "'\n"
                        + "AND E1TYPE = '" + type + "'";
                System.out.println("UpdateBills\n" + query);
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

    public static void Allocation(String cono, String divi, String code, String struct, String level, String itm, String period, String meterstart, String meterend, String rate, String hour, String qty, String pers, String amt, String total, String status, String type) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "UPDATE BRLDTA0100.E2ALLO\n"
                        + "SET E2STID = '" + struct + "'\n"
                        + ",E2SLVL = '" + level + "'\n"
                        + ",E2METS = '" + meterstart + "'\n"
                        + ",E2METE = '" + meterend + "'\n"
                        + ",E2UEHR = '" + rate + "'\n"
                        + ",E2HOUR = '" + hour + "'\n"
                        + ",E2EPQT = '" + qty + "'\n"
                        //                        + ",E2EPQT = ROUND(" + meterend + " - " + meterstart + ",2)\n"
                        + ",E2PERS = '" + pers + "'\n"
                        + ",E2EAQT = '" + amt + "'\n"
                        + ",E2EAAT = '" + total + "'\n"
                        + "WHERE E2CONO = '" + cono + "'\n"
                        + "AND E2DIVI = '" + divi + "'\n"
                        + "AND E2CODE = '" + code + "'\n"
                        + "AND E2PECO = '" + period + "'\n"
                        + "AND E2AITM = '" + itm + "'\n"
                        + "AND E2TYPE = '" + type + "'";
                System.out.println("UpdateAllocation\n" + query);
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

    public static void StatusAllocation(String cono, String divi, String code, String period, String oldstatus, String newstatus, String type) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "UPDATE BRLDTA0100.E2ALLO\n"
                        + "SET E2STAT = '" + newstatus + "'\n"
                        + "WHERE E2CONO = '" + cono + "'\n"
                        + "AND E2DIVI = '" + divi + "'\n"
                        + "AND E2CODE = '" + code + "'\n"
                        + "AND E2PECO = '" + period + "'\n"
                        + "AND E2STAT = '" + oldstatus + "'\n"
                        + "AND E2TYPE = '" + type + "'";
                System.out.println("UpdateAllocation\n" + query);
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

    public static void StatusAllocationLevel(String cono, String divi, String code, String period, String struct, String level, String oldstatus, String newstatus, String type) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "UPDATE BRLDTA0100.E2ALLO\n"
                        + "SET E2STAT = '" + newstatus + "'\n"
                        + "WHERE E2CONO = '" + cono + "'\n"
                        + "AND E2DIVI = '" + divi + "'\n"
                        + "AND E2CODE = '" + code + "'\n"
                        + "AND E2PECO = '" + period + "'\n"
                        + "AND E2STID = '" + struct + "'\n"
                        + "AND E2SLVL= '" + level + "'\n"
                        + "AND E2STAT = '" + oldstatus + "'\n"
                        + "AND E2TYPE = '" + type + "'";
                System.out.println("UpdateAllocation\n" + query);
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

}
