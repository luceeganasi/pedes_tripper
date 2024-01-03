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

    public Player(Texture texture, Vector2 position){
        this.texture = texture;
        this.position = position;
        this.speed = 4f;
        this.bounds = new Rectangle(position.x - getWidth() / 2, position.y, texture.getWidth(), getHeight()/8);
    }

    public void update(){
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


