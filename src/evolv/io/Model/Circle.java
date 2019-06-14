package evolv.io.Model;

import lombok.AccessLevel;
import lombok.Getter;
import processing.core.PVector;

import java.awt.*;

@Getter
public class Circle {

    Color color;
    float diameter;

    @Getter(AccessLevel.NONE)
    PVector position;

    public Circle(PVector pVector, float diameter, Color color) {
        position = pVector;
        this.diameter = diameter;
        this.color = color;
    }

    public void setX(float x) { position.x = x; }

    public void setY(float y) { position.y = y; }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }
}
