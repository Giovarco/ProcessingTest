package evolv.io;

import evolv.io.Model.Circle;
import evolv.io.Model.Creature;
import evolv.io.Model.Food;
import evolv.io.View.CircleView;
import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EvolvioColor extends PApplet {

    private static final int CREATURE_COUNT = 10;
    private static final int FOOD_COUNT = 10;
    private static final int CREATURE_DIAMETER = 20;
    private static final int FOOD_DIAMETER = 10;

    CircleView circleView;

    List<Creature> creatureList;
    List<Food> foodList;

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
            creatureList.add(new Creature(creaturePosition, CREATURE_DIAMETER, new Color(255,0,0)));
        }

        foodList = new ArrayList<>();
        for(int i = 0; i < FOOD_COUNT; i++) {
            PVector foodPosition = getRandomPosition();
            foodList.add(new Food(new PVector(foodPosition.x, foodPosition.y), FOOD_DIAMETER, new Color(0,0,255)));
        }

    }

    @Override
    public void draw() {
        clear();
        background(255);
        drawBoard();

        for(Creature creature : creatureList) {
            Food closestFood = getClosestFood(creature);
            creature.setWantedFood(closestFood);
            if(closestFood != null) {
                moveCreatureTowardsFood(creature);
                drawCreatureWantedFood(creature, creature.getWantedFood());
            }
            removeEatenFood(creature);
        }
    }

    private void drawCreatureWantedFood(Creature creature, Food closestFood) {
        line(creature.getX(), creature.getY(), closestFood.getX(), closestFood.getY());
    }

    private void removeEatenFood(Creature creature) {
        List<Food> foodToRemove = new ArrayList<>();
        for(Food food : foodList) {
            if(areColliding(creature, food)) {
                foodToRemove.add(food);
            }
        }
        foodList.removeAll(foodToRemove);
    }

    private boolean areColliding(Creature creature, Food food) {
        if(food.getX() - food.getDiameter() > creature.getX() - creature.getDiameter()) {
            if(food.getX() + food.getDiameter() < creature.getX() + creature.getDiameter()) {
                if(food.getY() - food.getDiameter() > creature.getY() - creature.getDiameter()) {
                    if(food.getY() + food.getDiameter() < creature.getY() + creature.getDiameter()) {
                       return true;
                    }
                }
            }
        }
        return false;
    }

    private Food getClosestFood(Creature creature) {
        Food closestFood = null;
        float closestFoodDistance = Float.POSITIVE_INFINITY;
        for(Food food : foodList) {
            float currentFoodDistance = dist(creature.getX(), creature.getY(), food.getX(), food.getY());
            if(currentFoodDistance < closestFoodDistance) {
                closestFoodDistance = currentFoodDistance;
                closestFood = food;
            }
        }
        return closestFood;
    }

    private void drawBoard() {
        for(Creature creature : creatureList) {
            circleView.draw(creature);
        }
        for(Food food : foodList) {
            circleView.draw(food);
        }
    }

    private void moveCreatureTowardsFood(Creature creature) {
        Food creatureWantedFood = creature.getWantedFood();
        PVector vectorDistanceToClosestCreature = new PVector(creatureWantedFood.getX() - creature.getX(), creatureWantedFood.getY() - creature.getY());
        PVector normalizedVectorDistance = vectorDistanceToClosestCreature.normalize();
        creature.setX(creature.getX() + normalizedVectorDistance.x);
        creature.setY(creature.getY() + normalizedVectorDistance.y);
    }

    private PVector getRandomPosition() {
        return new PVector(random(0, width), random(0, height));
    }
}
