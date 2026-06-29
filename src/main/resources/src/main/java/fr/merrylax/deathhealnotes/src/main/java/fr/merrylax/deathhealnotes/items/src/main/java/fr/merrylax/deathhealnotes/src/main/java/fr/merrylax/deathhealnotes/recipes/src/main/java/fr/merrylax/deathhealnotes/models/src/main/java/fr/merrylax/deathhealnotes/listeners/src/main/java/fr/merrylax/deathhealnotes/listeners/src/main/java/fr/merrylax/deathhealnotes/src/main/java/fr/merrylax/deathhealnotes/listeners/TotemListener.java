package fr.merrylax.deathhealnotes.listeners;

import fr.merrylax.deathhealnotes.DeathHealNotes;
import fr.merrylax.deathhealnotes.managers.DeathManager;
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

        if (!(event.getEntity() instanceof org.bukkit.entity.Player player)) {
            return;
        }

        if (deathManager.isCondemned(player.getUniqueId())) {
            event.setCancelled(true);
        }

    }

}
