package com.lothbrok.game.model.entities;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.lothbrok.game.model.entities.components.AttackingComponent;
import com.lothbrok.game.model.entities.components.GravityComponent;
import com.lothbrok.game.model.entities.components.JumpingComponent;
import com.lothbrok.game.model.entities.components.MovementComponent;
import com.lothbrok.game.model.entities.components.TiledCollisionComponent;

public class Player extends Entity {

    private GravityComponent gravityComponent;
    private MovementComponent movementComponent;
    private JumpingComponent jumpingComponent;
    private TiledCollisionComponent tiledCollisionComponent;
    private AttackingComponent attackingComponent;

    // Setup
    public Player(Vector2 position, Map map) {
        super(position);
        setupComponents(map);
    }

    private void setupComponents(Map map) {
        gravityComponent = new GravityComponent(this, 1.008f, 3f, 0.8f);
        movementComponent = new MovementComponent(this, 1.01f, 2f, 1f);
        jumpingComponent = new JumpingComponent(this, 1.6f, 0.992f, 1.4f, 2.4f);
        tiledCollisionComponent = new TiledCollisionComponent(this, (TiledMap)map);
        attackingComponent = new AttackingComponent(this);
    }



    // Update
    public void update(float deltaTime) {
        prevPosition.x = position.x;
        prevPosition.y = position.y;
        updateActionState(deltaTime);
        updateMovingState();
    }

    private void updateActionState(float deltaTime) {
        if(actionState == Entity.ActionState.JUMPING) {
            actionState = Entity.ActionState.MIDJUMP;
        } else if(actionState != Entity.ActionState.ATTACKING) {
            actionState = Entity.ActionState.FALLING;
        }

        gravityComponent.applyGravity(deltaTime);
        if(tiledCollisionComponent.isBottomColliding()) {
            actionState = Entity.ActionState.STANDING;
            position.y = prevPosition.y;
        }
    }

    private void updateMovingState() {
        if(movementState == MovementState.MOVING) {
            movementState = Entity.MovementState.MIDMOVING;
        } else {
            movementState = Entity.MovementState.STANDING;
        }
    }




    // Control methods
    public void moveLeft(float deltaTime) {
        movementComponent.moveLeft(deltaTime);
        if(tiledCollisionComponent.isLeftColliding()) {
            position.x = prevPosition.x;
        }
    }

    public void moveRight(float deltaTime) {
        movementComponent.moveRight(deltaTime);
        if(tiledCollisionComponent.isRightColliding()) {
            position.x = prevPosition.x;
        }
    }

    public void jump(float deltaTime) {
        jumpingComponent.jump(deltaTime);
        if(tiledCollisionComponent.isTopColliding()) {
            actionState = ActionState.FALLING;
        }
    }

    public void startAttacking() {
        attackingComponent.startAttacking();
    }

    public void stopAttacking() {
        attackingComponent.stopAttacking();
    }

    public void updateBoundingBox(Rectangle body, Rectangle foot) {
        tiledCollisionComponent.updateBoundingBox(body, foot);
    }

    public boolean isActuallyMoving() {
        return position.x != prevPosition.x;
    }

    public float getSpeed() {
        return movementComponent.getSpeed();
    }

    public Rectangle getBoundingBox() {
        return tiledCollisionComponent.getBoundingBox();
    }

    public Rectangle getFootSensor() {
        return tiledCollisionComponent.getFootSensor();
    }

    public AttackingComponent getAttackingComponent() {
        return attackingComponent;
    }
}
