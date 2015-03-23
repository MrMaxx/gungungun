package de.overwatch.gungungun.service;


import de.overwatch.gungungun.domain.*;
import de.overwatch.gungungun.game.heuristic.HeuristicName;
import de.overwatch.gungungun.repository.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;

@Transactional
@Service
public class PartyService {

    @Inject
    private PartyRepository partyRepository;
    @Inject
    private HeroRepository heroRepository;
    @Inject
    private BehaviorRepository behaviorRepository;
    @Inject
    private UserRepository userRepository;
    @Inject
    private TokenBlueprintRepository tokenBlueprintRepository;

    public void createDefaultParty(Long userId){

        User owner = userRepository.findOne(userId);

        Party party = new Party();
        party.setName("name me");
        party.setUser(owner);

        partyRepository.saveAndFlush(party);

        TokenBlueprint blueprint = tokenBlueprintRepository.findOne(1l);

        Hero hero = new Hero();
        hero.setTokenBlueprint(blueprint);
        hero.setName("name me");
        hero.setParty(party);

        heroRepository.saveAndFlush(hero);

        Behavior behavior1 = new Behavior();
        behavior1.setHero(hero);
        behavior1.setHeuristicName(HeuristicName.CLOSER_DISTANCE_TO_NEAREST_ENEMY);
        behavior1.setPriority(100);

        Behavior behavior2 = new Behavior();
        behavior2.setHero(hero);
        behavior2.setHeuristicName(HeuristicName.SHOOTING_IS_GOOD);
        behavior2.setPriority(100);

        behaviorRepository.saveAndFlush(behavior1);
        behaviorRepository.saveAndFlush(behavior2);


    }



}
