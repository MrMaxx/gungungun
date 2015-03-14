package de.overwatch.gungungun.game.builder;


import de.overwatch.gungungun.domain.Hero;
import de.overwatch.gungungun.domain.Party;
import de.overwatch.gungungun.domain.TokenBlueprint;

import java.util.*;

public class TestPartyBuilder {

    private Set<Hero> heroes = new HashSet<>();
    private Party party;


    public TestPartyBuilder(){
        Random rand = new Random();
        party = new Party();
        party.setId(rand.nextLong());
        party.setName("TestParty_"+party.getId());
    }

    public TestPartyBuilder withHero(TokenBlueprint tokenBlueprint){
        Hero hero = new TestHeroBuilder().build(tokenBlueprint);
        heroes.add(hero);
        hero.setParty(party);
        return this;
    }

    public Party build(){
        party.setHeroes(heroes);

        return party;
    }


}
