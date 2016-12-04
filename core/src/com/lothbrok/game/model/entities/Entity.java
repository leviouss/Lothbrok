package com.lothbrok.game.model.entities;

import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

    public enum ActionState {
        STANDING,
        FALLING,
        JUMPING,
        MIDJUMP,
        ATTACKING,
        DEAD
    }

    public enum MovementState {
        STANDING,
        MOVING,
        MIDMOVING
    }

    public enum Direction {
        LEFT,
        RIGHT
    }

    public ActionState actionState;
    public MovementState movementState;
    public Direction direction;

    public Vector2 position;
    public Vector2 prevPosition;

    public Entity(Vector2 position) {
        setupBasics(position);
    }

    private void setupBasics(Vector2 position) {
        this.position = position;
        this.prevPosition = new Vector2();
        this.prevPosition.x = position.x;
        this.prevPosition.y = position.y;
        this.actionState = ActionState.STANDING;
        this.movementState = MovementState.STANDING;
        this.direction = Direction.RIGHT;
    }
}