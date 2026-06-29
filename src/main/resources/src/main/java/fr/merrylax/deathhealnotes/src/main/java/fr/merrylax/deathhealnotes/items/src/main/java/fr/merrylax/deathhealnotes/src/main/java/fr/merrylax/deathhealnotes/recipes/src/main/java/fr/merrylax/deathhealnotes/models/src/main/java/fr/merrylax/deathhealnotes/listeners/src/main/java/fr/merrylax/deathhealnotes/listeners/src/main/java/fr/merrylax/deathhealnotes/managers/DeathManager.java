package fr.merrylax.deathhealnotes.managers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import fr.merrylax.deathhealnotes.DeathHealNotes;
import fr.merrylax.deathhealnotes.models.CondemnedPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DeathManager {

    private final DeathHealNotes plugin;

    private final Map<UUID, CondemnedPlayer> condemnedPlayers = new HashMap<>();

    public DeathManager(DeathHealNotes plugin) {
        this.plugin = plugin;
    }
    /**
     * Ajoute une nouvelle condamnation.
     */
    public void addCondemnation(CondemnedPlayer condemnedPlayer) {

        condemnedPlayers.put(
                condemnedPlayer.getVictim(),
                condemnedPlayer
        );

    }

    /**
     * Vérifie si un joueur est condamné.
     */
    public boolean isCondemned(UUID uuid) {

        CondemnedPlayer condemnedPlayer = condemnedPlayers.get(uuid);

        return condemnedPlayer != null && condemnedPlayer.isActive();

    }

    /**
     * Récupère une condamnation.
     */
    public CondemnedPlayer getCondemnation(UUID uuid) {

        return condemnedPlayers.get(uuid);

    }

    /**
     * Retire une condamnation.
     */
    public void removeCondemnation(UUID uuid) {

        condemnedPlayers.remove(uuid);

    }

    /**
     * Retourne toutes les condamnations.
     */
    public Map<UUID, CondemnedPlayer> getCondemnedPlayers() {

        return condemnedPlayers;
    }
    /**
     * Démarre la vérification automatique des condamnations.
     */
    public void startTask() {

        plugin.getServer().getScheduler().runTaskTimer(
                plugin,
                () -> checkCondemnations(),
                20L,
                20L
        );

    }

    /**
     * Vérifie toutes les condamnations.
     */
    private void checkCondemnations() {

        long currentTime = System.currentTimeMillis();

        for (CondemnedPlayer condemnedPlayer : condemnedPlayers.values()) {

            if (!condemnedPlayer.isActive()) {
                continue;
            }

            if (currentTime >= condemnedPlayer.getDeathTime()) {

                killPlayer(condemnedPlayer);

            }

        }
    
    }
      /**
     * Tue le joueur condamné.
     */
    private void killPlayer(CondemnedPlayer condemnedPlayer) {

        Player player = Bukkit.getPlayer(condemnedPlayer.getVictim());

        if (player == null) {
            return;
        }

        player.setHealth(0.0);

        condemnedPlayer.setActive(false);

        condemnedPlayers.remove(condemnedPlayer.getVictim());
    }
        /**
     * Condamne un joueur.
     */
    public boolean condemnPlayer(UUID victim, UUID owner, long duration) {

        if (isCondemned(victim)) {
            return false;
        }

        long deathTime = System.currentTimeMillis() + duration;

        CondemnedPlayer condemnedPlayer = new CondemnedPlayer(
                victim,
                owner,
                deathTime
        );

        addCondemnation(condemnedPlayer);

        return true;

    }
}
