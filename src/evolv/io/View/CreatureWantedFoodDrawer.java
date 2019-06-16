package evolv.io.View;

import evolv.io.Model.Creature;
import evolv.io.Model.Food;
import processing.core.PApplet;
import processing.core.PGraphics;

import java.util.List;

public class CreatureWantedFoodDrawer extends PApplet {

    public CreatureWantedFoodDrawer(PGraphics pGraphics) {
        this.g = pGraphics;
    }

    public void draw(List<Creature> creatureList) {
        for (Creature creature : creatureList) {
            if (creature.getWantedFood() != null) {
                Food closestFood = creature.getWantedFood();
                line(creature.getX(), creature.getY(), closestFood.getX(), closestFood.getY());
            }
        }
    }
}
