package evolv.io.View;

import evolv.io.Model.Circle;
import processing.core.PApplet;
import processing.core.PGraphics;

public class CircleView extends PApplet {

    public CircleView(PGraphics pGraphics) {
        this.g = pGraphics;
    }

    public void draw(Circle circle) {
        fill(circle.getColor().getRed(), circle.getColor().getGreen(), circle.getColor().getBlue());
        ellipse(circle.getX(), circle.getY(), circle.getDiameter(), circle.getDiameter());
    }
}
