package evolv.io;

import evolv.io.Model.Circle;
import evolv.io.View.CircleView;
import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

public class EvolvioColor extends PApplet {

    CircleView circleView;

    Circle creature;
    Circle food;

    @Override
    public void settings() {
        size(1000,700);
    }

    @Override
    public void setup() {
        circleView = new CircleView(this.g);

        creature = new Circle(new PVector(500, 500), 10, new Color(255,0,0));
        food = new Circle(new PVector(700, 600), 10, new Color(0,0,255));
    }

    @Override
    public void draw() {
        clear();
        background(255);
        drawBoard();
    }

    @Override
    public void mouseClicked() {
        creature.setX(mouseX);
        creature.setY(mouseY);
    }

    private void drawBoard() {
        circleView.draw(creature);
        circleView.draw(food);

        line(creature.getX(), creature.getY(), food.getX(), food.getY());
    }
}
