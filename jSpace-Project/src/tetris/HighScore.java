package tetris;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;


public class HighScore {

    File file = new  File("highScore.txt");
    ObservableList<HighScoreData> data = FXCollections.observableArrayList();
    String[] parts;

    public HighScore() {
        readHighScore();
    }

    private void readHighScore() {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = (br.readLine())) != null) {
                parts = line.split(",");
                HighScoreData highScoreData = new HighScoreData(parts[0], parts[1]);
                data.add(highScoreData);
            }

            System.out.println(data.toString());

        } catch (Exception e) {

        }
        System.out.println("readHighScore");
    }

    public void addHighScore (String name, String score) {

        HighScoreData highScoreData = new HighScoreData(name, score);

        int i = 0;

        if (data.size() == 0) {
            System.out.println("addHighscore1");
            data.add(0, highScoreData);
            writeHighScore();
        } else {
            while (i < data.size()) {
                System.out.println(data.get(i).getScore());
                System.out.println(score);
                if (Integer.parseInt(data.get(i).getScore()) > Integer.parseInt(score)) {
                    System.out.println("addHighscore2");
                    i++;
                } else {
                    System.out.println("addHighscore3");
                    data.add(i, highScoreData);
                    writeHighScore();
                    break;
                }
            }
        }

        if (data.size() == 11) {
            data.remove(10);
        }

        System.out.println("addHighscore");
    }

    private void writeHighScore() {

        try {
            PrintWriter pw = new PrintWriter(file);
            for (int i = 0; i < data.size(); i++) {
                pw.println(data.get(i).toString());
            }

            pw.flush();
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("writeHighScore");
    }
}
