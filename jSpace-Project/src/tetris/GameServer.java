package tetris;

import org.jspace.*;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Random;

import org.jspace.FormalField;
import org.jspace.SequentialSpace;
import org.jspace.SpaceRepository;

public class GameServer implements Runnable{

    public GameServer() {

    }

    @Override
    public void run() {
        try {
            SpaceRepository repository = new SpaceRepository();
            SequentialSpace server = new SequentialSpace();
            repository.add("server", server);

            String ipAddress = InetAddress.getLocalHost().getHostAddress();
            String uri = "tcp://" + ipAddress + ":9001/server?keep";
            System.out.println("Hosting game on address: " + uri);

            URI myUri = new URI(uri);
            String gateUri = "tcp://" + myUri.getHost() + ":" + myUri.getPort() +  "?keep" ;
            repository.addGate(gateUri);


            //Generate a seed for blocks
            Random random = new Random();
            long seed = random.nextLong();
            server.put("SEED", seed);

            //Print IP of client when a connection is made.
            Object[] connectedMsg = server.query(new ActualField("CONNECTED"), new FormalField(String.class));
            System.out.println(connectedMsg[0] + "  " + connectedMsg[1]);


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
