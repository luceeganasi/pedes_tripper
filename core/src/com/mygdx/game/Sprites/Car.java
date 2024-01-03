package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Car {
    private Texture carTexture;
    private Vector2 position;
    private Vector2 velocity;
    private Rectangle bounds;
    private Rectangle bounds1;
    private float width;
    private float height;

    public Car(Texture carTexture, Vector2 position, Vector2 velocity) {
        this.carTexture = carTexture;
        this.position = position;
        this.velocity = velocity;
        this.bounds = new Rectangle(position.x - getWidth() / 2, position.y, carTexture.getWidth(),getHeight()/2);
        this.bounds1 = new Rectangle(position.x - getWidth() / 2, position.y, carTexture.getWidth(),getHeight()/2);
    }

    private float getWidth() {
        return carTexture.getWidth();
    }
    private float getHeight(){
        return carTexture.getHeight();
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(position.x, position.y, width, height);
    }


    // Getter for car's position
    public Vector2 getPosition() {
        return position;
    }

    public void update(float deltaTime) {
        position.x += velocity.x * deltaTime; // Move the car horizontally (left to right)
        bounds.setPosition(position.x, position.y); // Update bounds position
    }

    public void update1(float deltaTime) {
        position.x += velocity.x * deltaTime; // Move the car horizontally (left to right)
        bounds1.setPosition(position.x, position.y); // Update bounds position
    }

    public void draw(SpriteBatch batch) {
        batch.draw(carTexture, position.x, position.y); // Draw the car at its current position
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Rectangle getBounds1() {
        return bounds1;
    }

}
