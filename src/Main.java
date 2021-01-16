import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main extends Application {

    final static int WORLD_WIDTH = 3;
    final static int WORLD_HEIGHT = 3;
    final static int PEOPLE_COUNT = 2;

    private WorldMap worldMap;
    private List<People> peopleList = new ArrayList<>();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        worldMap = new WorldMap(WORLD_WIDTH, WORLD_HEIGHT);
        Scene scene = new Scene(worldMap);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new WindowEventEventHandler());
        setPeople(PEOPLE_COUNT);
        startWorld();

    }


    private void setPeople(int peopleCount) {

        for (int i = 0; i < peopleCount; i++) {
            People people = new People();
            int randomPositionX = getRandomPositionX();
            int randomPositionY = getRandomPositionY();
            people.setPeopleCoordinateX(randomPositionX);
            people.setPeopleCoordinateY(randomPositionY);
            peopleList.add(people);
            worldMap.add(people, people.getPeopleCoordinateX(), people.getPeopleCoordinateY());
            

        }

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::startWorld, 0, 5000, TimeUnit.MILLISECONDS);

    }

    private void startWorld() {

        Platform.runLater(() -> {

            for (People people : peopleList) {

                worldMap.getChildren().remove(people);
                setNewPosition(people);
                worldMap.add(people, people.getPeopleCoordinateX(), people.getPeopleCoordinateY());
            }

        });


    }

    private void setNewPosition(People people) {

        double rndValue = Math.random() * 9;
        switch ((int) rndValue) {
            case 1:
                people.setPeopleCoordinateX(people.getPeopleCoordinateX() - 1);
                people.setPeopleCoordinateY(people.getPeopleCoordinateY() + 1);
                if (!checkOccupied(people)) {
                    people.setPeopleCoordinateX(people.getPeopleCoordinateX() + 1);
                    people.setPeopleCoordinateY(people.getPeopleCoordinateY() - 1);

                }
                break;
            case 2:
                people.setPeopleCoordinateY(people.getPeopleCoordinateY() + 1);
                if (!checkOccupied(people)) {
                    people.setPeopleCoordinateY(people.getPeopleCoordinateY() - 1);
                }
                break;
            case 3:
                people.setPeopleCoordinateX(people.getPeopleCoordinateX() + 1);
                people.setPeopleCoordinateY(people.getPeopleCoordinateY() + 1);
                if (!checkOccupied(people)) {
                    people.setPeopleCoordinateX(people.getPeopleCoordinateX() - 1);
                    people.setPeopleCoordinateY(people.getPeopleCoordinateY() - 1);
                }
                break;
            case 4:
                people.setPeopleCoordinateX(people.getPeopleCoordinateX() + 1);
                if (!checkOccupied(people)) {
                    people.setPeopleCoordinateX(people.getPeopleCoordinateX() - 1);
                }
                break;
            case 5:
                people.setPeopleCoordinateX(people.getPeopleCoordinateX() + 1);
                people.setPeopleCoordinateY(people.getPeopleCoordinateY() - 1);
                if (!checkOccupied(people)) {
                    people.setPeopleCoordinateX(people.getPeopleCoordinateX() - 1);
                    people.setPeopleCoordinateY(people.getPeopleCoordinateY() + 1);
                }
                break;
            case 6:
                people.setPeopleCoordinateY(people.getPeopleCoordinateY() - 1);
                if (!checkOccupied(people)) {
                    people.setPeopleCoordinateY(people.getPeopleCoordinateY() + 1);
                }
                break;
            case 7:
                people.setPeopleCoordinateX(people.getPeopleCoordinateX() - 1);
                people.setPeopleCoordinateY(people.getPeopleCoordinateY() - 1);
                if (!checkOccupied(people)) {
                    people.setPeopleCoordinateX(people.getPeopleCoordinateX() + 1);
                    people.setPeopleCoordinateY(people.getPeopleCoordinateY() + 1);
                }
                break;
            case 8:
                people.setPeopleCoordinateX(people.getPeopleCoordinateX() - 1);
                if (!checkOccupied(people)) {
                    people.setPeopleCoordinateX(people.getPeopleCoordinateX() + 1);
                }
                break;
        }

        //check Worldbounds

        if (people.getPeopleCoordinateX() <= 0) {
            people.setPeopleCoordinateX(1);
        }
        if (people.getPeopleCoordinateX() >= WORLD_WIDTH) {
            people.setPeopleCoordinateX(WORLD_WIDTH - 1);
        }

        if (people.getPeopleCoordinateY() <= 0) {
            people.setPeopleCoordinateY(1);
        }
        if (people.getPeopleCoordinateY() >= WORLD_HEIGHT) {
            people.setPeopleCoordinateY(WORLD_HEIGHT - 1);
        }

        //check is position occupied


    }

    boolean checkOccupied(People people) {

        boolean isOccupied = false;
        for (People people1 : peopleList) {

            if (people.getPeopleCoordinateX() == people1.getPeopleCoordinateX() && people.getPeopleCoordinateY() == people1.getPeopleCoordinateY()) {
                isOccupied = true;
            } else {
                isOccupied = false;
            }

            //  System.out.println("istBelegt " + isOccupied + " People" + people + "People1 " + people1);
        }
        System.out.println("Occupied:  " + isOccupied + "People: " + people);
        return isOccupied;
    }

    private int getRandomPositionX() {
        double randomValueX = Math.random() * WORLD_WIDTH;
        return (int) randomValueX;
    }

    private int getRandomPositionY() {
        double randomValueY = Math.random() * WORLD_HEIGHT;
        return (int) randomValueY;
    }


    private static class WindowEventEventHandler implements EventHandler<WindowEvent> {
        @Override
        public void handle(WindowEvent e) {
            Platform.exit();
            System.exit(0);
        }
    }
}
