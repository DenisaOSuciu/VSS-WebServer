package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Controller {
    private WebServer webServer;

    public Controller(WebServer webServer) {
        this.webServer = webServer;
    }

    public static ServerSocket newServerSocket(int serverPort) throws IOException {

            ServerSocket serverSocket = new ServerSocket(serverPort);
            return serverSocket;

    }

    public static Socket newSocket(ServerSocket serverSocket) throws Exception {
        try {
            Socket newCS = acceptSocket(serverSocket);

            return newCS;
        } catch (Exception exception) {
            System.out.println("Couldn't create a client");
            throw exception;
        }
    }

    public static Socket acceptSocket(ServerSocket serverSocket) throws Exception {
        try {
            return serverSocket.accept();
        } catch (Exception exception) {
            System.out.println("Failed to accept soclet");
            throw exception;
        }
    }

    public static void closeSocket(Socket socket) throws  IOException {
        try {
            socket.close();
            System.out.println("Socket was closed");
        } catch (Exception exception) {
            System.out.println("failed to close this client");
            throw exception;
        }
    }

    public void respond(OutputStream out, String status, String typeOfContent, byte[] content) throws IOException {
        out.write(("HTTP/1.1 \r\n" + status).getBytes());
        out.write("\r\n".getBytes());
        out.write(content);
        out.write("\r\n\r\n".getBytes());
    }

    public void reqHandle() {
        try (ServerSocket sS = this.newServerSocket(webServer.getPort())) {
            Socket clientS = this.newSocket(sS);
            clientHandle(clientS);
            this.closeSocket(clientS);
           // this.closeServerSocket(sS);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Communication problem");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clientHandle(Socket clientSocket) {
        String contentType;
        String rawPath;
        Path file;
        ArrayList<String> inputs = new ArrayList<>();

        try {
            String inputString;
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            OutputStream out = clientSocket.getOutputStream();


            while ((inputString = in.readLine()) != null) {
                System.out.println("Input : " + inputString);
                inputs.add(inputString);

                if (inputString.isEmpty()) {
                    break;
                }
            }

            if (!inputs.isEmpty()) {
                rawPath = inputs.get(0).split(" ")[1];
                if (rawPath.equals("/") || rawPath.equals("/index.html")) {
                    file = Paths.get(webServer.getWebPath(), "/index.html");
                }
                else {
                    file = Paths.get(rawPath);
                }

                if (!file.toString().contains("index")) {
                    if (webServer.pages.get(0).contains(rawPath.substring(1))) {
                        String aux = webServer.getWebPath() + "/html";
                        file = Paths.get(aux, rawPath);
                  /*  } else if (webServer.pages.get(1).contains(rawPath.substring(1))) {
                        String aux = webServer.getWebPath() + "/html/htmllvl1";
                        file = Paths.get(aux, rawPath);
                    } else if (webServer.pages.get(2).contains(rawPath.substring(1))) {
                        String aux = webServer.getWebPath() + "/html/htmllvl1/htmllvl2";
                        file = Paths.get(aux, rawPath);
                    } else {
                        file = Paths.get(rawPath);
                    }*/
                    } else if (webServer.pages.get(1).contains(rawPath.substring(1))) {
                        String aux = webServer.getWebPath() + "/html";
                        file = Paths.get(aux, rawPath);
                    } else if (webServer.pages.get(2).contains(rawPath.substring(1))) {
                        String aux = webServer.getWebPath() + "/html";
                        file = Paths.get(aux, rawPath);
                    } else {
                        file = Paths.get(rawPath);
                    }

                }

                contentType = Files.probeContentType(file);

                String temp = webServer.getRequest() + " " + file;
                webServer.setRequest(temp);


                if (webServer.getServerStatus().equals("Running")) {

                    if (Files.exists(file)) {
                        respond(out, "Running", contentType, Files.readAllBytes(file));
                    } else {
                        respond(out, "Error 404", contentType, Files.readAllBytes(Paths.get(webServer.getWebPath(), "Error404.html")));
                    }
                }


                in.close();
                out.close();
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.err.println("Communication problem");
        }

    }
}
