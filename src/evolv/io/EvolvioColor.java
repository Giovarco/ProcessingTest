package evolv.io;

import evolv.io.Model.Circle;
import evolv.io.View.CircleView;
import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EvolvioColor extends PApplet {

    CircleView circleView;

    List<Circle> creatureList;
    Circle food;

    @Override
    public void settings() {
        size(1000,700);
    }

    @Override
    public void setup() {
        circleView = new CircleView(this.g);

        creatureList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            PVector creaturePosition = new PVector(random(0, width), random(0, height));
            creatureList.add(new Circle(creaturePosition, 10, new Color(255,0,0)));
        }

        food = new Circle(new PVector(700, 600), 10, new Color(0,0,255));
    }

    @Override
    public void draw() {
        clear();
        background(255);
        drawBoard();
    }

    private void drawBoard() {
        for(Circle creature : creatureList) {
            circleView.draw(creature);
        }
        circleView.draw(food);
    }
}
