import exceptions.*;
import gui.UserInterface;
import server.*;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws WrongPortException, WrongStatusException, WrongWebPathException, IOException {
       ArrayList<ArrayList<String>> website = new ArrayList<>();
      ArrayList<String> list=new ArrayList<>();
        website.add(list);
        website.get(0).add("html/button1.html");
        website.get(0).add("html/button2.html");
        website.get(0).add("html/maintenance.html");

        WebServer webServer = new WebServer(44055, "src/main/java/website", "Stopped");
        webServer.pages=website;
        Controller controller = new Controller(webServer);
        UserInterface gui =new UserInterface();
        webServer.setServerStatus("Running");

        while (true) {

            controller.reqHandle();
        }
    }
}
