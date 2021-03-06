package com.lothbrok.game.renderers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.lothbrok.game.assets.Assets;
import com.lothbrok.game.constants.Resolution;
import com.lothbrok.game.constants.UIConstants;
import com.lothbrok.game.controllers.PauseController;

public class PauseRenderer extends EndOfGameRenderer {

    private PauseController pauseController;

    private Image pausedLogo;
    private TextButton btnResume;

    private InputMultiplexer inputMultiplexer;

    public PauseRenderer(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer, PauseController pauseController, Assets assets) {
        super(spriteBatch, shapeRenderer, assets);
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(new PauseKeyProcessor());

        this.pauseController = pauseController;
        pausedLogo = skin.get(UIConstants.UI_PAUSED_LOGO, Image.class);
        rootTable.add(pausedLogo).expand().center().padTop(Resolution.instance.getPaddingMedium()).row();
        buildBtnResume();
        buildBtnMainMenu();
    }

    private void buildBtnResume() {
        TextButton.TextButtonStyle style = skin.get(UIConstants.UI_TEXT_BUTTON_STYLE, TextButton.TextButtonStyle.class);
        style.font = assets.getPrVikingFont().getLargeFont();
        style.fontColor = skin.get(UIConstants.UI_SKIN_COLOR_WHITE, Color.class);
        btnResume = new TextButton(UIConstants.UI_BTN_RESUME, style);
        btnResume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pauseController.setPaused(false);
            }
        });

        rootTable.add(btnResume).expand().padTop(Resolution.instance.getPaddingSmall()).row();
    }

    public InputMultiplexer getInputMultiplexer() {
        return inputMultiplexer;
    }

    private class PauseKeyProcessor extends InputAdapter{

        @Override
        public boolean keyUp(int keycode) {
            if(keycode == Input.Keys.ESCAPE) {
                pauseController.switchPaused();
            }
            return true;
        }
    }
}
