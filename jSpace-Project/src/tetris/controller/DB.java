package tetris.controller;

public class DB {

    private static String MoveRight = "Right";
    private static String MoveLeft = "Left";
    private static String RotateRight = "Up";
    private static String RotateLeft = "Z";
    private static String SoftDrop = "Down";
    private static String HardDrop = "Space";
    private static String Hold = "C";

    public DB() {}

    //Getters
    public static String getMoveRightControl() { return MoveRight; }
    public static String getMoveLeftControl() { return MoveLeft; }
    public static String getRotateRightControl() { return RotateRight; }
    public static String getRotateLeftControl() {
        return RotateLeft;
    }
    public static String getSoftDropControl() {
        return SoftDrop;
    }
    public static String getHardDropControl() {
        return HardDrop;
    }
    public static String getHoldControl() {
        return Hold;
    }

    //setters
    public void setMoveRightKey(String key) {
        MoveRight = key;
    }
    public void setMoveLeftKey(String key) {
        MoveLeft = key;
    }
    public void setRotateRightKey(String key) {
        RotateRight = key;
    }
    public void setRotateLeftKey(String key) {
        RotateLeft = key;
    }
    public void setSoftDropKey(String key) {
        SoftDrop = key;
    }
    public void setHardDropKey(String key) {
        HardDrop= key;
    }
    public void setHoldKey(String key) {
        Hold = key;
    }
}
