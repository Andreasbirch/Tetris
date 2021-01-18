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
        } catch (Exception e) {}
    }

    public void addHighScore (String name, String score) {

        HighScoreData highScoreData = new HighScoreData(name, score);
        boolean added = false;

        int i = 0;

        if (data.size() == 0) {
            data.add(0, highScoreData);
            writeHighScore();
        } else {
            while (i < data.size()) {
                if (Integer.parseInt(data.get(i).getScore()) > Integer.parseInt(score)) {
                    i++;
                } else {
                    added = true;
                    data.add(i, highScoreData);
                    writeHighScore();
                    break;
                }
            } 

            if (data.size() < 10 && added == false) {
                data.add((data.size()), highScoreData);
                writeHighScore();
            }
        }

        if (data.size() == 11) {
            data.remove(10);
        }
    }

    public boolean isHighScore (int score) {
        int i = 0;
        if (data.size() < 10) {
            return true;
        }
        while (i < data.size()) {
            if (Integer.parseInt(data.get(i).getScore()) > score) {
                i++;
            } else {
                return true;
            }
        }
        return false;
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
    }

    public ObservableList<HighScoreData> getData() {
        return data;
    }
}
