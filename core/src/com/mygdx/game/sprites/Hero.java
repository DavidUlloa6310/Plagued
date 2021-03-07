package com.mygdx.game.sprites;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.screens.PlayScreen;

public abstract class Hero extends GameCharacter{
    public Hero(World world, PlayScreen screen, String name, int width, int height) {
        super(world, screen, name, width, height);
    }

    public abstract void passive();
    public abstract void primary();
    public abstract void shift();
    public abstract void special();
    public abstract void ultimate();
}
