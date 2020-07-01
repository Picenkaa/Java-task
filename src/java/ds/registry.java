package ds;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class registry implements Serializable {

    private ArrayList<buildings> buildings_l = new ArrayList();
 
    Connection conn;
    Statement stmt;

    public boolean prideti(String ad, String ow,int s,int m, String pt) {
        try {
            this.prisijungtiPrieDB();
            String uzklausa = "INSERT INTO test.ps (Address,Owner,Size,Market_Value,Property_Type) VALUES ('" + ad + "', '" + ow + "', '" + s + "', '" + m + "', '" + pt + "')";
            stmt.execute(uzklausa);
            this.atsijungtiNuoDB();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

   

    public boolean salinti(String addd) {
        try {
            this.prisijungtiPrieDB();
            String uzklausa = "DELETE FROM test.ps WHERE Address ='" + addd + "'";
            stmt.execute(uzklausa);
            this.atsijungtiNuoDB();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean keisti(String ad, int nmv) {
        try {
            this.prisijungtiPrieDB();
            String uzklausa = "UPDATE test.ps SET Market_Value = '" + nmv + "'  WHERE Address = '" + ad + "'";
            stmt.execute(uzklausa);
            this.atsijungtiNuoDB();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

   

   
    public ArrayList<buildings> gautiSarasa() { // gauti sarasa
        this.buildings_l = new ArrayList();
        try {
            prisijungtiPrieDB();
            String sql = "SELECT * FROM test.ps";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String add = rs.getString("Address");
                String own = rs.getString("Owner");
               String size2= rs.getString("Size");
                 String Mar_val= rs.getString("Market_Value");
                 String Prop_type = rs.getString("Property_Type");
                   int size_int=Integer.parseInt(size2);  
                    int Mar_val_int=Integer.parseInt(Mar_val);                  
                buildings_l.add(new buildings(add,own,size_int,Mar_val_int,Prop_type));
            }
            rs.close();
            atsijungtiNuoDB();
        } catch (Exception e) {
            System.out.println("klaida gaunant duomenys: ");
            e.printStackTrace();
        }
        return buildings_l;
    }

    public void prisijungtiPrieDB() { //
        try {
            Class.forName("com.mysql.jdbc.Driver"); //add to library myslq driver 
            String DB_URL = "jdbc:mysql://localhost:3306/test";
            String USER = "root";
            String PASS = "";
            buildings_l.clear();
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("prisijungta");
            stmt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void atsijungtiNuoDB() {
        if (stmt != null && conn != null) {
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
