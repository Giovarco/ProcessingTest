package evolv.io.View;

import evolv.io.Model.Circle;
import evolv.io.Model.Creature;
import evolv.io.Model.Food;
import processing.core.PApplet;
import processing.core.PGraphics;

public class CreatureWantedFoodDrawer extends PApplet {

    public CreatureWantedFoodDrawer(PGraphics pGraphics) {
        this.g = pGraphics;
    }

    public void draw(Creature creature) {
        Food closestFood = creature.getWantedFood();
        line(creature.getX(), creature.getY(), closestFood.getX(), closestFood.getY());
    }
}
