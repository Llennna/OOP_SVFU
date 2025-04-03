
import java.lang.reflect.Constructor;
import java.sql.*;

import java.sql.Connection;

import java.sql.DriverManager;
public class DataBaseConn {
    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver").newInstance();
            //Connection conn = DriverManager.getConnection("jdbc:h2:mem:MYGAME",
            Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test",
                    "sa", "");
            Statement st = conn.createStatement();
//            st.execute("INSERT INTO TEST VALUES(default,'HELLO')");
//            st.execute("INSERT INTO TEST(NAME) VALUES('JOHN')");
//            String name1 = "Jack";
//            String q = "insert into TEST(name) values(?)";
//            PreparedStatement st1 = null;
//            st1 = conn.prepareStatement(q);
//            st1.setString(1, name1);
//            st1.execute();
            ResultSet result;
//            st.execute("CREATE table units (id INT PRIMARY KEY AUTO_INCREMENT, x INT, y INT, type VARCHAR(20))");
//            st.execute("INSERT INTO units (x,y,type) VALUES ( 1, 2,'Rook')");
//            st.execute("INSERT INTO units (x,y,type) VALUES ( 2, 5,'Queen')");
//            st.execute("INSERT INTO units (x,y,type) VALUES ( 6, 3,'Knight')");
            st.execute("INSERT INTO units (x,y,type) VALUES ( 7, 3,'Bishop')");

            result = st.executeQuery("SELECT * FROM units");
            while (result.next()) {
                int id = result.getInt(1);
                int x = result.getInt(2);
                int y = result.getInt(3);
                String type = result.getString(4);
                System.out.println(" "+x+","+y);
//                Constructor<Unit> constructor = Unit.class.getDeclaredConstructor(int.class, int.class);
//                Unit unit = constructor.newInstance(x,y);
//                System.out.println(unit.getX()+","+ unit.getY());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}