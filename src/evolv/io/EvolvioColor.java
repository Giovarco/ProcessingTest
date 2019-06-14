package evolv.io;

import evolv.io.Model.Circle;
import evolv.io.View.CircleView;
import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EvolvioColor extends PApplet {

    public static final int CREATURE_COUNT = 10;
    public static final int FOOD_COUNT = 10;
    public static final int CREATURE_DIAMETER = 20;
    public static final int FOOD_DIAMETER = 10;

    CircleView circleView;

    List<Circle> creatureList;
    List<Circle> foodList;

    @Override
    public void settings() {
        size(1000,700);
    }

    @Override
    public void setup() {
        circleView = new CircleView(this.g);

        creatureList = new ArrayList<>();
        for(int i = 0; i < CREATURE_COUNT; i++) {
            PVector creaturePosition = getRandomPosition();
            creatureList.add(new Circle(creaturePosition, CREATURE_DIAMETER, new Color(255,0,0)));
        }

        foodList = new ArrayList<>();
        for(int i = 0; i < FOOD_COUNT; i++) {
            PVector foodPosition = getRandomPosition();
            foodList.add(new Circle(new PVector(foodPosition.x, foodPosition.y), FOOD_DIAMETER, new Color(0,0,255)));
        }

    }

    @Override
    public void draw() {
        clear();
        background(255);
        drawBoard();

        for(Circle creature : creatureList) {
            Circle closestFood = null;
            float closestFoodDistance = Float.POSITIVE_INFINITY;
            for(Circle food : foodList) {
                float currentFoodDistance = dist(creature.getX(), creature.getY(), food.getX(), food.getY());
                if(currentFoodDistance < closestFoodDistance) {
                    closestFoodDistance = currentFoodDistance;
                    closestFood = food;
                }
            }
            if(closestFood != null) {
                moveCreatureTowardsFood(creature, closestFood);
                line(creature.getX(), creature.getY(), closestFood.getX(), closestFood.getY());
            }
        }

        for(Circle creature : creatureList) {
            List<Circle> foodToRemove = new ArrayList<>();
            for(Circle food : foodList) {
                if(food.getX() - food.getDiameter() > creature.getX() - creature.getDiameter()) {
                    if(food.getX() + food.getDiameter() < creature.getX() + creature.getDiameter()) {
                        if(food.getY() - food.getDiameter() > creature.getY() - creature.getDiameter()) {
                            if(food.getY() + food.getDiameter() < creature.getY() + creature.getDiameter()) {
                                foodToRemove.add(food);
                            }
                        }
                    }
                }
            }
            foodList.removeAll(foodToRemove);
        }
    }

    private void drawBoard() {
        for(Circle creature : creatureList) {
            circleView.draw(creature);
        }
        for(Circle food : foodList) {
            circleView.draw(food);
        }
    }

    private void moveCreatureTowardsFood(Circle creature, Circle food) {
        PVector vectorDistanceToClosestCreature = new PVector(food.getX() - creature.getX(), food.getY() - creature.getY());
        PVector normalizedVectorDistance = vectorDistanceToClosestCreature.normalize();
        creature.setX(creature.getX() + normalizedVectorDistance.x);
        creature.setY(creature.getY() + normalizedVectorDistance.y);
    }

    private PVector getRandomPosition() {
        return new PVector(random(0, width), random(0, height));
    }
}
