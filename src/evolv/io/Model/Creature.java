package evolv.io.Model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import processing.core.PVector;

import java.awt.*;

@Getter
@Setter
public class Creature extends Circle {

    Food wantedFood = null;

    /**
     * Speed expressed in pixels per second
     */
    @Setter(AccessLevel.NONE)
    float speed;

    float energy;

    public Creature(PVector pVector, float diameter, Color color, float energy) {
        super(pVector, diameter, color);
        speed = 60;
        this.energy = energy;
    }
}
