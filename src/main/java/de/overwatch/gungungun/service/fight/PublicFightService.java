package de.overwatch.gungungun.service.fight;

import de.overwatch.gungungun.domain.Fight;
import de.overwatch.gungungun.repository.FightRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

@Service
public class PublicFightService {

    @Inject
    private FightRepository fightRepository;

    public List<PublicFight> getFights(Long participatingUserId){
        List<Fight> fights = fightRepository.findByUserId(participatingUserId);

        List<PublicFight> result = new LinkedList<>();
        for(Fight fight: fights){
            result.add(new PublicFight(fight));
        }
        return result;
    }

}
