package tetris;

import javafx.beans.property.SimpleStringProperty;

public class HighScoreData {

    private final SimpleStringProperty name;
    private final SimpleStringProperty score;

    public HighScoreData(String name, String score) {

        this.name = new SimpleStringProperty(name);
        this.score = new SimpleStringProperty(score);
    }

    public String toString() {
        return name.getValue() + "," + score.getValue();
    }

    public String getName() {
        return name.get();
    }

    public String getScore() {
        return score.getValue();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setScore(String score) {
        this.score.set(score);
    }
}
