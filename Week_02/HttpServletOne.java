package follow.phenix.ice.nio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class HttpServletOne {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8801);
        while (true) {
            Socket socket = serverSocket.accept();
            try {
                server(socket);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void server(Socket socket) throws InterruptedException, IOException {
        TimeUnit.SECONDS.sleep(1);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println("HTTP/1.1 200 OK");
        printWriter.println("Context-Type:text/html;charset=utf-8");
        printWriter.println();
        printWriter.write("hello nio");
        printWriter.close();
        socket.close();
    }
}
