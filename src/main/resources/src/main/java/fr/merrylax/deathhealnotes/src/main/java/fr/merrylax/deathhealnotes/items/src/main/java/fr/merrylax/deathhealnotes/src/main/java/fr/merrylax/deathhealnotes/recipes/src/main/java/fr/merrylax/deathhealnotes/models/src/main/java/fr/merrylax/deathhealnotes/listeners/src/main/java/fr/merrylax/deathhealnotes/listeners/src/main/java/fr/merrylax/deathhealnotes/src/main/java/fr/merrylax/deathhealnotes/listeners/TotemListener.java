package fr.merrylax.deathhealnotes.listeners;

import fr.merrylax.deathhealnotes.DeathHealNotes;
import fr.merrylax.deathhealnotes.managers.DeathManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;

public class TotemListener implements Listener {

    private final DeathManager deathManager;

    public TotemListener(DeathHealNotes plugin) {
        this.deathManager = plugin.getDeathManager();
    }

    @EventHandler
    public void onTotem(EntityResurrectEvent event) {

        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        if (
        !deathManager.isCondemned(player.getUniqueId())
        && !deathManager.isForcedDeath(player.getUniqueId())
) {
    return;
}
        }

        event.setCancelled(true);

        Bukkit.getScheduler().runTaskLater(
                DeathHealNotes.getInstance(),
                () -> {
                    if (player.isOnline()) {
                        player.setHealth(0.0);
deathManager.clearForcedDeath(player.getUniqueId());
                    }
                },
                1L
        );
    }
}
