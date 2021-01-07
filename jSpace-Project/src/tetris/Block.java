package tetris;


import java.awt.*;
import java.util.Random;

public class Block extends Rectangle {

    public Object Block() {
        Random random = new Random();
        int randomBlock = random.nextInt(2);

        switch (randomBlock){
            case 0:
                return new Straight();
            case 1:
                return new Square();
        }

        return null;
    }


}
