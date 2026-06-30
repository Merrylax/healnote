package fr.merrylax.deathhealnotes.managers;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.Particle;
import org.bukkit.Sound;
import fr.merrylax.deathhealnotes.DeathHealNotes;
import fr.merrylax.deathhealnotes.models.CondemnedPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
public class DeathManager {

    private final DeathHealNotes plugin;

    private final Map<UUID, CondemnedPlayer> condemnedPlayers = new HashMap<>();
    private final Map<UUID, Long> forcedDeaths = new HashMap<>();
   
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

        forceKill(player);

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

        World world = Bukkit.getWorlds().get(0); // monde principal

if (world != null) {

    // 🌙 nuit + orage
    world.setTime(18000);
    world.setStorm(true);
    world.setThundering(true);

    // ⚡ éclairs aléatoires
    for (Player p : Bukkit.getOnlinePlayers()) {
        p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1f, 1f);
        p.sendMessage("§4☠ Un Death Note a été utilisé...");
    }

    // ⏳ retour météo normal après 3 secondes (60 ticks)
    Bukkit.getScheduler().runTaskLater(plugin, () -> {
        world.setStorm(false);
        world.setThundering(false);
        world.setClearWeatherDuration(6000);
    }, 60L);
}
Player victimPlayer = Bukkit.getPlayer(victim);

if (victimPlayer != null) {

    victimPlayer.getWorld().strikeLightningEffect(victimPlayer.getLocation());

    victimPlayer.getWorld().spawnParticle(
            org.bukkit.Particle.SMOKE,
            victimPlayer.getLocation(),
            50,
            0.5, 1, 0.5,
            0.02
    );

    victimPlayer.sendTitle(
            "§4☠ DEATH NOTE",
            "§7Ton nom a été écrit...",
            10,
            60,
            20
    );
}       
        return true;

    }
/**
 * Annule la condamnation d'un joueur.
 */
public boolean healPlayer(UUID victim) {

    CondemnedPlayer condemnedPlayer = condemnedPlayers.get(victim);

    if (condemnedPlayer == null || !condemnedPlayer.isActive()) {
        return false;
    }

    condemnedPlayer.setActive(false);
    condemnedPlayers.remove(victim);

    return true;
}
public void forceKill(Player player) {

    if (player == null) {
        return;
    }

    forcedDeaths.put(player.getUniqueId(), System.currentTimeMillis());

    player.setHealth(0.0);
}

public boolean isForcedDeath(UUID uuid) {
    return forcedDeaths.containsKey(uuid);
}

public void clearForcedDeath(UUID uuid) {
    forcedDeaths.remove(uuid);
}

}
