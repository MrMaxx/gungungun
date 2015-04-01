package de.overwatch.gungungun.service.fight;


import de.overwatch.gungungun.domain.Arena;

public class PublicArena {


    private Long id;
    private String arenaKey;

    public PublicArena(Arena arena) {
        this.id = arena.getId();
        this.arenaKey = arena.getArenaKey();
    }

    public Long getId() {
        return id;
    }

    public String getArenaKey() {
        return arenaKey;
    }
}
