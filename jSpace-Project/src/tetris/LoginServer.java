package tetris;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jspace.FormalField;
import org.jspace.SequentialSpace;
import org.jspace.Space;
import org.jspace.SpaceRepository;
import tetris.controller.LoginController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoginServer implements Runnable {

    Space channelUserServer = new SequentialSpace();
    Space channelServerUser = new SequentialSpace();
    String username, password;
    Object[] response, credentials;
    File file = new  File("credentials.txt");
    ObservableList<LoginData> data = FXCollections.observableArrayList();
    String[] parts;

    public LoginServer (String username, String password) {
        System.out.println("constructor");
        this.username = username;
        this.password = password;
        readLoginData();
    }

    @Override
    public void run() {
        try {
            System.out.println("run");

            channelUserServer.put(username, password);
            System.out.println("run1");

            credentials = channelUserServer.get(new FormalField(String.class), new FormalField(String.class));
            System.out.println("run3");
            channelServerUser.put(check(credentials));

            System.out.println("run2");

            response = channelServerUser.get(new FormalField(String.class));

            System.out.println(response[0]);
            System.out.println("run4");

            if (response[0].equals("ko")) {
                LoginController loginController = new LoginController();
                loginController.loginSuccess();
            } else {
                //Start login forfra med fejlbesked
            }

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public String check(Object[] credentials) {

        System.out.println("check");

        username = credentials[0].toString();
        password = credentials[1].toString();

        int i = 0;
        while (i < data.size()) {
            if(username.equals(data.get(i).getUsername()) && password.equals(data.get(i).getPassword())) {
                return "ok";
            } else {
                i++;
            }
        }
        return "ko";
    }

    public void readLoginData() {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = (br.readLine())) != null) {
                parts = line.split(",");
                LoginData loginData = new LoginData(parts[0], parts[1]);
                data.add(loginData);
            }

            System.out.println(data.toString());

        } catch (Exception e) {

        }
    }
}
