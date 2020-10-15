import java.io.*;
import java.net.*;

public class ClientSide {

    public static void main(String[] args) throws IOException {
        try {
            Socket mySocket = new Socket("localhost", 8000);
            BufferedReader first = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            String answer = first.readLine();
            System.out.println(answer);
            DataOutputStream output = new DataOutputStream(mySocket.getOutputStream());
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));
            String productName = "";
            String productType = "";
            String productPrice = "";
            do {
                System.out.println(reader1.readLine());
                productName = reader2.readLine();
                output.writeUTF(productName);
                output.flush();
                if (productName.equals("stop")) {
                    System.out.println("Exiting Chackers Stock Taking App. Good Bye!");
                    break;
                }
                System.out.println(reader1.readLine());
                productType = reader2.readLine();
                output.writeUTF(productType);
                output.flush();
                System.out.println(reader1.readLine());
                productPrice = reader2.readLine();
                output.writeUTF(productPrice);
                output.flush();
                System.out.println("Server says: " + reader1.readLine());
            } while (!productName.equals("stop"));
            mySocket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
