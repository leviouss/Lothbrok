package com.lothbrok.game.renderers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.lothbrok.game.assets.Assets;
import com.lothbrok.game.constants.Resolution;
import com.lothbrok.game.constants.UIConstants;
import com.lothbrok.game.screens.MainMenuScreen;
import com.lothbrok.game.screens.utils.ColorRectangleActor;

public class EndOfGameRenderer implements Disposable {

    protected Assets assets;

    protected Stage stage;
    protected Skin skin;

    protected Table rootTable;

    protected ColorRectangleActor background;
    protected TextButton btnMainMenu;

    public EndOfGameRenderer() {}

    public EndOfGameRenderer(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer, Assets assets) {
        this.assets = assets;
        stage = new Stage(new FitViewport(Resolution.instance.getMenuWidth(),
                Resolution.instance.getMenuHeight()), spriteBatch);
        skin = assets.getUI();
        Gdx.input.setInputProcessor(stage);
        rebuildStage(shapeRenderer);
    }

    protected void rebuildStage(ShapeRenderer shapeRenderer) {
        stage.clear();
        rootTable = new Table();
        rootTable.setFillParent(true);

        background = new ColorRectangleActor(shapeRenderer, new Color(0f, 0f, 0f, 0.5f),
                new Rectangle(stage.getCamera().position.x - stage.getCamera().viewportWidth/2,
                stage.getCamera().position.y - stage.getCamera().viewportHeight/2,
                stage.getCamera().viewportWidth,
                stage.getCamera().viewportHeight));
        stage.addActor(background);

        stage.addActor(rootTable);
    }

    protected void buildBtnMainMenu() {
        TextButton.TextButtonStyle style = skin.get(UIConstants.UI_TEXT_BUTTON_STYLE, TextButton.TextButtonStyle.class);
        style.font = assets.getPrVikingFont().getLargeFont();
        style.fontColor = skin.get("white", Color.class);
        btnMainMenu = new TextButton("MAIN MENU", style);
        btnMainMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen(assets));
            }
        });

        rootTable.add(btnMainMenu).expand().padTop(25f).row();
    }

    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
