package com.lothbrok.game.model.entities;

import com.badlogic.gdx.math.Vector2;

public abstract class AbstractMovingEntity implements MovingEntity {

    protected Vector2 position;
    protected float speed;
    protected float maxJumpHeight;
    protected float jumpSpeed;

    public enum ActionState {
        STANDING,
        FALLING,
        JUMPING
    }

    public enum MovingState {
        WALKINGLEFT,
        WALKINGRIGHT
    }

    protected ActionState actionState;
    protected MovingState movingState;

    public abstract void update(float deltaTime);

    public Vector2 getPosition() {
        return position;
    }

    public ActionState getActionState() {
        return actionState;
    }

    public MovingState getMovingState() {
        return movingState;
    }
}
