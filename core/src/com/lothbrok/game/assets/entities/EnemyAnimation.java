package com.lothbrok.game.assets.entities;

import com.badlogic.gdx.math.Rectangle;
import com.lothbrok.game.assets.spriter.SpriterAnimation;
import com.lothbrok.game.assets.utils.AssetsConstants;

public class EnemyAnimation extends EntityAnimation {

    public EnemyAnimation(SpriterAnimation animation) {
        super(animation, AssetsConstants.ENEMY_ANIMATION_ENTITY_ENEMY, AssetsConstants.ENEMY_ANIMATION_WALKING);
    }

    @Override
    public Rectangle getBodyBoundingBox() {
        return getBodyBoundingBox(AssetsConstants.ENEMY_ANIMATION_SPRITE_BODY,
                AssetsConstants.ENEMY_ANIMATION_SPRITE_LEFT_LEG,
                AssetsConstants.ENEMY_ANIMATION_BOTTOM_DELTA,
                AssetsConstants.ENEMY_ANIMATION_TOP_DELTA);
    }

    @Override
    public Rectangle getWeaponBoundingBox() {
        return getWeaponBoundingBox(AssetsConstants.ENEMY_ANIMATION_SPRITE_WEAPON);
    }

    @Override
    public Rectangle getFootSensor() {
        return getFootSensor(AssetsConstants.ENEMY_ANIMATION_SPRITE_LEFT_LEG,
                AssetsConstants.ENEMY_ANIMATION_SPRITE_RIGHT_LEG,
                AssetsConstants.ENEMY_ANIMATION_BOTTOM_DELTA);
    }
}