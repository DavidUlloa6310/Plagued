package com.mygdx.game.sprites;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.screens.PlayScreen;

public class Ninja extends GameCharacter {
    public Ninja(World world, PlayScreen screen) {
        super(world, screen, "ninja");
    }
}
