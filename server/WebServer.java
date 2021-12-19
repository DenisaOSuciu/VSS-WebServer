package server;
import exceptions.WrongPortException;
import exceptions.WrongStatusException;
import exceptions.WrongWebPathException;
import java.util.ArrayList;

public class WebServer extends ServerConfig{
    public ArrayList<ArrayList<String>> pages = new ArrayList<>();

    public WebServer() {
     super();}

    public WebServer(int port, String webPath, String serverStatus) throws WrongPortException, WrongStatusException, WrongWebPathException {
        super(port,webPath,serverStatus);
        validateWebServerInputs(port, webPath, serverStatus);
    }


    public void validateWebServerInputs(int port, String webPath, String serverStatus) throws WrongPortException, WrongStatusException, WrongWebPathException {
        if (port != 44055)
            throw new WrongPortException();
        if (!serverStatus.equals("Stopped"))
            throw new WrongStatusException();
        if (!webPath.equals("src/main/java/website")) {
            throw new WrongWebPathException();
        }
    }

     public void addList(ArrayList<String> L) {
        pages.add(L);
    }

    public void addPageOnL(String page, int i) {
        pages.get(i).add(page);
    }

}