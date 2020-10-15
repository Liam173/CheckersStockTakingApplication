import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServerSide {
    private static int store = 0;
    public static String equation() {
        String sum = "";
        return sum;
    }

    public static void main(String[] args) throws IOException {
        try {
            ServerSocket serverListener = new ServerSocket(8000);
            System.out.println("Server is starting.....");
            Socket mySocket = serverListener.accept();
            System.out.println("Client is connected...");
            PrintWriter toClient = new PrintWriter(mySocket.getOutputStream(), true);
            toClient.println("Welcome to Checkers Stock Taking App");
            toClient.flush();
            DataInputStream input = new DataInputStream(mySocket.getInputStream());
            String productName = "";
            String productType = "";
            double productPrice = 0;
            String problem = "";
            String temp1 = "";
            while (!temp1.equals("stop")) {
                toClient.println("Enter product name:");
                productName = input.readUTF();
                toClient.flush();
                toClient.println("Enter product type:");
                productType = input.readUTF();
                toClient.flush();
                toClient.println("Enter product price:");
                productPrice = Double.parseDouble(input.readUTF());
                toClient.flush();
                System.out.println("System received product details: " + productName + " " + productType + " " + productPrice);
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/checkersproducts", "root", "");
                    System.out.println("Connection to database successful");
                    String query = "INSERT INTO products (prodName, prodType, prodPrice)" + "VALUES('" + productName + "','" + productType + "','" + productPrice + "');";
                    PreparedStatement preparedStmt = connection.prepareStatement(query);
                    System.out.println("Product has been added to the database");
                    toClient.println("Product has been added");
                    toClient.flush();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
