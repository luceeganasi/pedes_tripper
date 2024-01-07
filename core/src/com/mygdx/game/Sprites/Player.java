package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Player {
    private Texture texture;
    private Vector2 position;
    private float speed;
    private Rectangle bounds;

    private float totalDistanceY; // New variable to track total distance traveled in Y axis
    private float initialY; // Store initial Y position

    public Player(Texture texture, Vector2 position){
        this.texture = texture;
        this.position = position;
        this.speed = 2f;
        this.bounds = new Rectangle(position.x - getWidth() / 2, position.y, texture.getWidth(), getHeight()/8);

        this.totalDistanceY = 0; // Initialize totalDistanceY to zero
        this.initialY = position.y; // Store initial Y position
    }

    public float getTotalDistanceY() {
        return totalDistanceY;
    }

    public void update(float cameraY){


        // Update the player's position based on user input
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            position.x += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            position.x -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            position.y += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            position.y -= speed;
        }

        // Update totalDistanceY when there's movement in the Y axis
        totalDistanceY = position.y - initialY + cameraY;
//        bounds.setPosition(position.x, position.y);
        bounds.setPosition(position.x, position.y); // Update bounds position

    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getWidth(){
        return texture.getWidth();
    }

    public float getHeight(){
        return texture.getHeight();
    }


}


