package evolv.io.View;

import evolv.io.Model.Circle;
import evolv.io.Model.Creature;
import evolv.io.Model.Food;
import processing.core.PApplet;
import processing.core.PGraphics;

import java.util.List;

public class CircleDrawer extends PApplet {

    public CircleDrawer(PGraphics pGraphics) {
        this.g = pGraphics;
    }

    public void draw(List<Creature> creatureList, List<Food> foodList) {
        for (Creature creature : creatureList) {
            drawCircle(creature);
        }
        for (Food food : foodList) {
            drawCircle(food);
        }
    }

    private void drawCircle(Circle circle) {
        fill(circle.getColor().getRed(), circle.getColor().getGreen(), circle.getColor().getBlue());
        ellipse(circle.getX(), circle.getY(), circle.getDiameter(), circle.getDiameter());
    }
}