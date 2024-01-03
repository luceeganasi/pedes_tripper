package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Pedes;
import com.mygdx.game.Sprites.Car;
import com.mygdx.game.Sprites.Player;

import java.io.Console;
import java.util.ArrayList;

public class PlayScreen implements Screen {
    private Pedes game;
    private Texture groundTexture;
    private Vector2 groundPos1, groundPos2;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Player player;
    private Texture carTexture, newCarTexture;

    private Texture playerTexture;

    private boolean isPlayerMovingUp = false;

    private ArrayList<Car> cars;
    private ArrayList<Car> cars1;

    private float minSpawnTime = 10f; // Minimum spawn time in seconds
    private float maxSpawnTime = 20f; // Maximum spawn time in seconds
    private float minSpawnTime1 = 10f; // Minimum spawn time in seconds
    private float maxSpawnTime1 = 20f; // Maximum spawn time in seconds
    private float timeSinceLastCar = 0;
    private static final int NUM_CARS = 50;
    private static final int NUM_CARS1 = 50;
    private float[] carSpawnTimers = new float[NUM_CARS];
    private float[] carSpawnTimers1 = new float[NUM_CARS1];







    public PlayScreen(Pedes game){
        this.game = game;
        groundTexture = new Texture("roadv2.png");
        gamecam = new OrthographicCamera();
        gamecam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gamePort = new FitViewport(Pedes.V_WIDTH, Pedes.V_HEIGHT, gamecam);
//
        groundPos1 = new Vector2(gamePort.getScreenX() - (gamePort.getWorldWidth() / 2), gamePort.getScreenX() - (gamePort.getWorldWidth() / 2));
        groundPos2 = new Vector2(gamePort.getScreenX() - (gamePort.getWorldWidth() / 2), gamePort.getScreenX() - (gamePort.getWorldWidth() / 2) + groundTexture.getHeight());


        playerTexture = new Texture("character.png");
        player = new Player(playerTexture, new Vector2(0, gamePort.getScreenX() - (gamePort.getWorldWidth() / 2) + playerTexture.getHeight() - 200));

        carTexture = new Texture("car-right.png"); // Load car texture
        newCarTexture = new Texture("car-left.png"); // Load car texture
        cars = new ArrayList<>();
        cars1 = new ArrayList<>();

    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if (Gdx.input.isTouched())
            gamecam.position.y += 200 * dt;
    }

    public void resetSpawnTimers() {
        for (int i = 0; i < NUM_CARS; i++) {
            carSpawnTimers[i] = MathUtils.random(minSpawnTime, maxSpawnTime);
        }
        timeSinceLastCar = 0; // Reset the timer
    }

    public void resetSpawnTimers1() {
        for (int i = 0; i < NUM_CARS1; i++) {
            carSpawnTimers1[i] = MathUtils.random(minSpawnTime1, maxSpawnTime1);
        }
        timeSinceLastCar = 0; // Reset the timer
    }




    public void randomSpawner() {
        ArrayList<Vector2> carPositions = new ArrayList<>(); // Store spawned car positions
        ArrayList<Vector2> carPositions1 = new ArrayList<>(); // Store spawned car positions

        for (Car car : cars) {
            carPositions.add(car.getPosition());
        }

        for (int i = 0; i < NUM_CARS; i++) {
            float newCarSpawnY = getCarSpawnY(i);
            float newCarSpawnX = getCarSpawnX(); // Get the new car's x-axis position


            // Check if the new car position collides with existing car positions
            boolean canSpawn = true;
            for (Vector2 pos : carPositions) {
                if (Math.abs(newCarSpawnX - pos.x) < 500 && Math.abs(newCarSpawnY - pos.y) < 300) {
                    canSpawn = false;
                    break;
                }
            }

            if (canSpawn) {
                spawnNewCar(new Vector2(newCarSpawnX, newCarSpawnY)); // Spawn a new car
                carPositions.add(new Vector2(newCarSpawnX, newCarSpawnY)); // Add the new car's position to the list
                carSpawnTimers[i] = MathUtils.random(minSpawnTime, maxSpawnTime); // Reset timer for the next car spawn
                resetSpawnTimers();
            }

        }
        for (Car car : cars1) {
            carPositions1.add(car.getPosition());
        }

        for (int i = 0; i < NUM_CARS1; i++) {

            // Spawn cars with the new texture (right to left)
            float newCarSpawnY1 = getCarSpawnY1(i);
            float newCarSpawnX1 = getCarSpawnX1(); // Get the new car's x-axis position

            // Check if the new car position collides with existing car positions
            boolean canSpawn1 = true;
            for (Vector2 pos : carPositions1) {
                if (Math.abs(newCarSpawnX1 - pos.x) < 500 && Math.abs(newCarSpawnY1 - pos.y) < 300) {
                    canSpawn1 = false;
                    break;
                }
            }

            if (canSpawn1) {
                spawnNewCarRightToLeft(new Vector2(newCarSpawnX1, newCarSpawnY1)); // Spawn a new car with right-to-left movement
                carPositions1.add(new Vector2(newCarSpawnX1, newCarSpawnY1)); // Add the new car's position to the list
                carSpawnTimers1[i] = MathUtils.random(minSpawnTime1, maxSpawnTime1); // Reset timer for the next car spawn
                resetSpawnTimers1();
            }


        }
    }

    private float getCarSpawnX() {
        // Implement your logic to get a new x-axis position for the car
        return MathUtils.random(1000, 6000); // Randomly spawn between x = 1000 and x = 6000
    }

    private float getCarSpawnX1() {
        // Implement your logic to get a new x-axis position for the car
        return MathUtils.random(-6000, -1000); // Randomly spawn between x = -6000 and x = -1000
    }



    public float getCarSpawnY(int index) {
        return index * 420 - 1180;
    }

    public float getCarSpawnY1(int index) {
        return index * 420 - 920;
    }


    public void spawnNewCar(Vector2 position) {
        Car newCar = new Car(carTexture, position, new Vector2(-550, 0)); // Change the velocity to move from right to left
        cars.add(newCar); // Add the new car to the list
    }

    public void spawnNewCarRightToLeft(Vector2 position) {
        Car newCar = new Car(newCarTexture, position, new Vector2(550, 0)); // Change the velocity to move from right to left
        cars1.add(newCar); // Add the new car to the list
    }



    public void updateGround(){


        float cameraBottom = gamecam.position.y - gamecam.viewportHeight / 2 + 1200;

        // Scroll ground textures in the opposite direction of camera movement (up when camera moves down, and vice versa)
        if (cameraBottom > groundPos1.y + groundTexture.getHeight()) {
            groundPos1.add(0, groundTexture.getHeight() * 2);
        }

        if (cameraBottom > groundPos2.y + groundTexture.getHeight()) {
            groundPos2.add(0, groundTexture.getHeight() * 2);
        }

        // Reverse scrolling when moving up (scroll down when camera moves up)
        if (cameraBottom < groundPos1.y) {
            groundPos1.add(0, -groundTexture.getHeight() * 2);
        }
        if (cameraBottom < groundPos2.y) {
            groundPos2.add(0, -groundTexture.getHeight() * 2);
        }

    }

    public void trackPlayer() {
        float centerY = gamecam.position.y - (gamecam.viewportHeight * 0.5f);

        float leftBoundary = -gamecam.viewportWidth * 0.5f;
        float rightBoundary = gamecam.viewportWidth * 0.5f - player.getWidth();

        if (player.getPosition().x < leftBoundary) {
            player.getPosition().x = leftBoundary;
        } else if (player.getPosition().x > rightBoundary) {
            player.getPosition().x = rightBoundary;
        }



        if (player.getPosition().y >= centerY + 450) {
            isPlayerMovingUp = true;
        } else {
            isPlayerMovingUp = false;
        }

        if (isPlayerMovingUp) {
            gamecam.position.x = 0;
            gamecam.position.y = player.getPosition().y + player.getHeight() / 2;
        } else {
            gamecam.position.x = 0; // Adjust the X position as needed
            gamecam.position.y = -450; // Adjust the Y position as needed


            // Prevent the player from moving below the bottom edge of the viewport
            float bottomEdge = gamecam.position.y - (gamecam.viewportHeight * 0.5f);
            if (player.getPosition().y < bottomEdge) {
                player.getPosition().y = bottomEdge;
            }
        }
    }

    public void collision(){
        for (Car car : cars) {
            if (player.getBounds().overlaps(car.getBounds())) {
                // Collision detected between player and car
                // Perform actions such as game over, score decrement, etc.

                System.out.println("hehe");
                game.setScreen(new GameOverScreen(game));
            }
        }

        for (Car car : cars1) {
            if (player.getBounds().overlaps(car.getBounds1())) {
                // Collision detected between player and car
                // Perform actions such as game over, score decrement, etc.

                System.out.println("hehe");
                game.setScreen(new GameOverScreen(game));
            }
        }
    }



    public void update(float dt){
        // Increment timer
        timeSinceLastCar += dt;


        handleInput(dt);
        randomSpawner();
        trackPlayer();
        updateGround();
        collision();

        gamecam.update();

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(gamecam.combined);
        player.update();

        gamecam.position.x = 0; // Adjust the X position as needed
        gamecam.position.y = -450; // Adjust the Y position as needed


        game.batch.begin();

        game.batch.draw(groundTexture, groundPos1.x, groundPos1.y);
        game.batch.draw(groundTexture, groundPos2.x, groundPos2.y);

        for (Car car : cars) {
            car.update(delta); // Update each car's position
            car.draw(game.batch); // Draw each car on the screen
        }

        for (Car car : cars1) {
            car.update1(delta); // Update each car's position
            car.draw(game.batch); // Draw each car on the screen
        }

        player.draw(game.batch);
//
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        groundTexture.dispose();
        playerTexture.dispose();
        carTexture.dispose();

    }
}
