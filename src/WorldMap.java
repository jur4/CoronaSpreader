import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class WorldMap extends GridPane {

    private int gameSizeX;
    private int gameSizeY;
    private Rectangle[][] tiles;




    public WorldMap(int gameSizeX, int gameSizeY) {

        this.gameSizeX = gameSizeX;
        this.gameSizeY = gameSizeY;
        tiles = new Rectangle[gameSizeX][gameSizeY];

        for (int xCount = 0; xCount < gameSizeX; xCount++) {
            for (int yCount = 0; yCount < gameSizeY; yCount++) {
                Rectangle rect = new Rectangle();
                rect.setHeight(10);
                rect.setWidth(10);
                rect.setFill(Color.WHITE);
                tiles[xCount][yCount] = rect;
                this.add(rect, xCount, yCount);


            }

        }


    }

    public Rectangle[][] getTiles() {
        return tiles;
    }
}