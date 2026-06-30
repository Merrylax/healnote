package fr.merrylax.deathhealnotes.models;

import java.util.UUID;

public class CondemnedPlayer {

    private final UUID victim;
    private final UUID owner;
    private final long deathTime;

    private boolean active = true;

    public CondemnedPlayer(UUID victim, UUID owner, long deathTime) {
        this.victim = victim;
        this.owner = owner;
        this.deathTime = deathTime;
    }

    public UUID getVictim() {
        return victim;
    }

    public UUID getOwner() {
        return owner;
    }

    public long getDeathTime() {
        return deathTime;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
