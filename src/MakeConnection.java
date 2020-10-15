import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MakeConnection {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/checkersproducts","root","");
            if(connection != null){
                System.out.println("Connection to database successful");
            }
        } catch (Exception e) {
            System.out.println("Connection to database failed");
        }
    }
}
