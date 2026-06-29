package fr.merrylax.deathhealnotes.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import fr.merrylax.deathhealnotes.DeathHealNotes;
import fr.merrylax.deathhealnotes.items.ItemManager;
import fr.merrylax.deathhealnotes.managers.DeathManager;
import org.bukkit.event.Listener;

public class DeathNoteListener implements Listener {

    private final DeathHealNotes plugin;
    private final ItemManager itemManager;
    private final DeathManager deathManager;

    public DeathNoteListener(DeathHealNotes plugin) {

        this.plugin = plugin;
        this.itemManager = plugin.getItemManager();
        this.deathManager = plugin.getDeathManager();

    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        ItemStack item = event.getItem();

        if (item == null) {
            return;
        }

        if (!itemManager.isDeathNote(item)) {
            return;
        }

        Action action = event.getAction();

        if (action != Action.RIGHT_CLICK_AIR &&
            action != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        event.setCancelled(true);

        // TODO :
        // Ici, nous ouvrirons le Death Note.
    }
}
