package server;

import java.util.ArrayList;

public class ServerConfig {
    public int port;
    public String path;
    public String status;
    public String request;
    public ArrayList<ArrayList<String>> pages = new ArrayList<>();

    public ServerConfig(){

    }

    public ServerConfig(int port,String path, String status) {
        this.port = port;
        this.path = path;
        this.status = status;
        this.request = "";
        pages = new ArrayList<>();
    }


    public int getPort() {
        return port;

    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getWebPath() {
        return path;
    }

    public void setWebPath(String webPath) {
        this.path = webPath;
    }

    public String getServerStatus() {
        return status;
    }

    public void setServerStatus(String serverStatus) {
        this.status = serverStatus;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

  /*  public void addPages(ArrayList<String> L){
        ArrayList<ArrayList<String>> website = new ArrayList<>();
        L.add("button1.html");
        L.add("button2.html");
        L.add("maintenance.html");
        website.add(L);
    }*/
}
