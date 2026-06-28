package fr.merrylax.deathhealnotes.managers;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import fr.merrylax.deathhealnotes.DeathHealNotes;
import fr.merrylax.deathhealnotes.models.CondemnedPlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DeathManager {

    private final DeathHealNotes plugin;

    /*
     * Liste des condamnations actives.
     * Clé = UUID de la victime
     */
    private final Map<UUID, CondemnedPlayer> condemnedPlayers;

    public DeathManager(DeathHealNotes plugin) {

        this.plugin = plugin;

        this.condemnedPlayers = new HashMap<>();

    }

    /**
     * Retourne true si le joueur est déjà condamné.
     */
    public boolean isCondemned(Player player) {

        return condemnedPlayers.containsKey(player.getUniqueId());

    }

    /**
     * Retourne la condamnation d'un joueur.
     */
    public CondemnedPlayer getCondemnation(Player player) {

        return condemnedPlayers.get(player.getUniqueId());

    }
    /**
     * Ajoute une nouvelle condamnation.
     *
     * @param victim La victime.
     * @param owner Le propriétaire du Death Note.
     * @param durationMillis Durée avant la mort (en millisecondes).
     * @return true si la condamnation a été créée.
     */
    public boolean condemn(Player victim, Player owner, long durationMillis) {

        if (isCondemned(victim)) {
            return false;
        }

        long startTime = System.currentTimeMillis();
        long endTime = startTime + durationMillis;

        CondemnedPlayer condemnedPlayer = new CondemnedPlayer(
                victim.getUniqueId(),
                owner.getUniqueId(),
                startTime,
                endTime
        );

        condemnedPlayers.put(
                victim.getUniqueId(),
                condemnedPlayer
        );

        return true;
    }

    /**
     * Supprime une condamnation.
     */
    public void removeCondemnation(Player player) {

        condemnedPlayers.remove(player.getUniqueId());

    }

    /**
     * Retourne toutes les condamnations actives.
     */
    public Map<UUID, CondemnedPlayer> getCondemnedPlayers() {

        return condemnedPlayers;
    }
        /**
     * Démarre la vérification automatique des condamnations.
     */
    public void startTask() {

        new BukkitRunnable() {

            @Override
            public void run() {

                for (CondemnedPlayer condemned : condemnedPlayers.values().toArray(new CondemnedPlayer[0])) {

                    if (!condemned.isExpired()) {
                        continue;
                    }

                    Player victim = Bukkit.getPlayer(condemned.getVictimUUID());

                    if (victim == null) {
                        continue;
                    }

                    victim.setHealth(0.0);

                    condemnedPlayers.remove(victim.getUniqueId());

                }

            }

        }.runTaskTimer(plugin, 20L, 20L);

    }
}
