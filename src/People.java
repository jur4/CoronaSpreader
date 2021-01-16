import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class People extends Rectangle {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private int peopleCoordinateX;
    private int peopleCoordinateY;



    public People() {
        this.setHeight(HEIGHT);
        this.setWidth(WIDTH);
        this.setFill(Color.BLACK);

    }



    public int getPeopleCoordinateX() {
        return peopleCoordinateX;
    }

    public void setPeopleCoordinateX(int peopleCoordinateX) {
        this.peopleCoordinateX = peopleCoordinateX;
    }

    public int getPeopleCoordinateY() {
        return peopleCoordinateY;
    }

    public void setPeopleCoordinateY(int peopleCoordinateY) {
        this.peopleCoordinateY = peopleCoordinateY;
    }


    @Override
    public String toString() {
        return "People{" +
                "peopleCoordinateX=" + peopleCoordinateX +
                ", peopleCoordinateY=" + peopleCoordinateY +
                '}';
    }
}
