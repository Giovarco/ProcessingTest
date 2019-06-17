package evolv.io;

import evolv.io.Model.Creature;
import evolv.io.Model.Food;
import evolv.io.View.CircleDrawer;
import evolv.io.View.CreatureWantedFoodDrawer;
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
    private static final int MAX_FRAMERATE = 60;
    private static final int FOOD_ENERGY = 50;

    CreatureWantedFoodDrawer creatureWantedFoodDrawer;
    CircleDrawer circleDrawer;

    List<Creature> creatureList;
    List<Food> foodList;

    @Override
    public void settings() {
        size(1000,700);
    }

    @Override
    public void setup() {
        frameRate(MAX_FRAMERATE);
        
        creatureWantedFoodDrawer = new CreatureWantedFoodDrawer(this.g);
        circleDrawer = new CircleDrawer(this.g);

        creatureList = new ArrayList<>();
        for(int i = 0; i < CREATURE_COUNT; i++) {
            createCreature();
        }

        foodList = new ArrayList<>();
        for(int i = 0; i < FOOD_COUNT; i++) {
            createFood();
        }

    }

    @Override
    public void draw() {
        compute();
        drawAll();
    }

    private void drawAll() {
        clear();
        background(255);
        circleDrawer.draw(creatureList, foodList);
        creatureWantedFoodDrawer.draw(creatureList);
    }

    private void compute() {
        List<Creature> creaturesToRemove = new ArrayList<>();

        for(Creature creature : creatureList) {
            Food closestFood = getClosestFood(creature);
            creature.setWantedFood(closestFood);

            if(closestFood != null) {
                moveCreatureTowardsFood(creature);
                creature.setEnergy(creature.getEnergy()-1);
                if(creature.getEnergy() <= 0 ) {
                    creaturesToRemove.add(creature);
                }
            }

            removeEatenFood(creature);
        }

        creatureList.removeAll(creaturesToRemove);

        renewOnBoardFood();

        renewOnBoardCreatures();
    }

    private void removeEatenFood(Creature creature) {
        List<Food> foodToRemove = new ArrayList<>();
        for(Food food : foodList) {
            if(areColliding(creature, food)) {
                creature.setEnergy(creature.getEnergy() + FOOD_ENERGY);
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

    private void moveCreatureTowardsFood(Creature creature) {
        Food creatureWantedFood = creature.getWantedFood();
        PVector vectorDistanceToClosestCreature = new PVector(creatureWantedFood.getX() - creature.getX(), creatureWantedFood.getY() - creature.getY());
        PVector normalizedVectorDistance = vectorDistanceToClosestCreature.normalize();
        float xIncrement = normalizedVectorDistance.x * creature.getSpeed();
        float yIncrement = normalizedVectorDistance.y * creature.getSpeed();
        creature.setX(creature.getX() + xIncrement / MAX_FRAMERATE);
        creature.setY(creature.getY() + yIncrement / MAX_FRAMERATE);
    }

    private PVector getRandomPosition() {
        return new PVector(random(0, width), random(0, height));
    }

    private void createFood() {
        PVector foodPosition = getRandomPosition();
        foodList.add(new Food(new PVector(foodPosition.x, foodPosition.y), FOOD_DIAMETER, new Color(0,0,255)));
    }

    private void renewOnBoardFood() {
        int foodOnBoard = foodList.size();
        if(foodOnBoard < FOOD_COUNT) {
            int foodToGenerate = FOOD_COUNT - foodOnBoard;
            while(foodToGenerate != 0) {
                createFood();
                foodToGenerate--;
            }
        }
    }

    private void createCreature() {
        PVector creaturePosition = getRandomPosition();
        creatureList.add(new Creature(creaturePosition, CREATURE_DIAMETER, new Color(255,0,0),width/2));
    }

    private void renewOnBoardCreatures() {
        int creatureOnBoard = creatureList.size();
        if(creatureOnBoard < CREATURE_COUNT) {
            int creaturesToGenerate = CREATURE_COUNT - creatureOnBoard;
            while(creaturesToGenerate != 0) {
                createCreature();
                creaturesToGenerate--;
            }
        }
    }
}
