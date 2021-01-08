package tetris;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import tetris.App;
import java.util.ArrayList;
import java.util.List;

public class GameData {
    private static List<Shape> shapeList = new ArrayList<Shape>();
    private static Shape totalMass;
    private static Object instance;

    private GameData() {}

    public static List<Shape> getShapeList() {
        return shapeList;
    }

    public static Shape getTotalMass() {
        return totalMass;
    }

    public static Object getInstance() {
        return instance;
    }

    public static void setTotalMass(Shape totalMass) {
        GameData.totalMass = totalMass;
    }

    public static void removeShape(Shape target){
        Shape newShape;

        for(Shape shape: shapeList) {
            if(!Shape.intersect(shape, target).getLayoutBounds().isEmpty()) {
                newShape = Shape.subtract(shape, target);
                shapeList.add(shapeList.indexOf(shape), newShape);
                shapeList.remove(shape);
                break;
            }

        }

    }



}
