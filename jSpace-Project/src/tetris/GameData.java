package tetris;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

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
        System.out.println("LayoutX" + target.getLayoutX()/28);
        System.out.println("LayoutY" + target.getLayoutY()/28);
        System.out.println("Type" + target.getClass());
        for(Shape shape: shapeList) {
            if(!Shape.intersect(shape, target).getLayoutBounds().isEmpty()) {
                Shape.subtract(shape,target);
                shape = Shape.subtract(shape, target);
            }
        }
    }
    
}
