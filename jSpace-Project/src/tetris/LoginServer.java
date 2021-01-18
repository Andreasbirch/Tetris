package tetris;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jspace.FormalField;
import org.jspace.SequentialSpace;
import org.jspace.Space;

import java.io.*;

public class LoginServer implements Runnable {

    Space channelUserServer = new SequentialSpace();
    Space channelServerUser = new SequentialSpace();
    String username, password, resp;
    Object[] response, credentials;
    File file = new  File("credentials.txt");
    ObservableList<LoginData> data = FXCollections.observableArrayList();
    String[] parts;
    private boolean canLogin;

    public LoginServer (String username, String password) {
        this.username = username;
        this.password = password;
        readLoginData();
        run();
    }

    @Override
    public void run() {

        try {
            channelUserServer.put(username, password);
            credentials = channelUserServer.get(new FormalField(String.class), new FormalField(String.class));
            resp = check(credentials);
            channelServerUser.put(resp);
            response = channelServerUser.get(new FormalField(String.class));
            if (response[0].equals("ok")) {
                canLogin = true;
            } else {
                canLogin = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String check(Object[] credentials) {

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
        } catch (Exception e) {}
    }

    public void writeUser() {
        LoginData loginData = new LoginData(username, password);
        data.add(loginData);

        try {
            PrintWriter pw = new PrintWriter(file);
            for (int i = 0; i < data.size(); i++) {
                pw.println(data.get(i).getUsername() + "," + data.get(i).getPassword());
            }
            pw.flush();
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean getCanLogin() {
        return canLogin;
    }
}
