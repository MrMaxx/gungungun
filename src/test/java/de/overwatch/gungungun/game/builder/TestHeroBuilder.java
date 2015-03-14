package de.overwatch.gungungun.game.builder;


import de.overwatch.gungungun.domain.Hero;
import de.overwatch.gungungun.domain.TokenBlueprint;

import java.util.Random;

public class TestHeroBuilder {

    public Hero build(TokenBlueprint tokenBlueprint){
        Random rand = new Random();
        Hero hero = new Hero();
        hero.setId(rand.nextLong());
        hero.setName("TESTHERO_"+hero.getId());
        hero.setTokenBlueprint(tokenBlueprint);
        return hero;
    }

}
