package de.overwatch.gungungun.game.builder;

import de.overwatch.gungungun.domain.TokenBlueprint;

/**
 * Created by mhoeflich on 12.03.15.
 */
public class TestTokenBlueprint {

    public static final TokenBlueprint GRUNT = new TokenBlueprint();
    static{
        GRUNT.setId(1L);
        GRUNT.setActionsPerTurn(4);
        GRUNT.setHealth(100);
        GRUNT.setLongRangeAttackRange(10);
        GRUNT.setLongRangeWeaponDamageMaximum(10);
        GRUNT.setLongRangeWeaponDamageMinimum(5);
        GRUNT.setMoveRange(2);
        GRUNT.setShortRangeAttackRange(1);
        GRUNT.setShortRangeWeaponDamageMaximum(25);
        GRUNT.setShortRangeWeaponDamageMinimum(10);
        GRUNT.setTokenKey("GRUNT");
    }


}
