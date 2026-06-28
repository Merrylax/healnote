package fr.merrylax.deathhealnotes.models;

import java.util.UUID;

public class CondemnedPlayer {

    private final UUID condemnationId;

    private final UUID victimUUID;
    private final UUID ownerUUID;

    private final long startTime;
    private final long endTime;

    private boolean saved;

    public CondemnedPlayer(UUID victimUUID,
                           UUID ownerUUID,
                           long startTime,
                           long endTime) {

        this.condemnationId = UUID.randomUUID();

        this.victimUUID = victimUUID;
        this.ownerUUID = ownerUUID;
        this.startTime = startTime;
        this.endTime = endTime;

        this.saved = false;
    }

    public UUID getCondemnationId() {
        return condemnationId;
    }

    public UUID getVictimUUID() {
        return victimUUID;
    }

    public UUID getOwnerUUID() {
        return ownerUUID;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public long getRemainingTime() {
        return Math.max(0, endTime - System.currentTimeMillis());
    }

    public boolean isExpired() {
        return System.currentTimeMillis() >= endTime;
    }

}
