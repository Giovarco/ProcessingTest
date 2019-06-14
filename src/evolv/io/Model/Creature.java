package evolv.io.Model;

import lombok.Getter;
import lombok.Setter;
import processing.core.PVector;

import java.awt.*;

@Getter
@Setter
public class Creature extends Circle {

    Food wantedFood = null;

    public Creature(PVector pVector, float diameter, Color color) {
        super(pVector, diameter, color);
    }
}
