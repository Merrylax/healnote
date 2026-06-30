package fr.merrylax.deathhealnotes.listeners;

import fr.merrylax.deathhealnotes.DeathHealNotes;
import fr.merrylax.deathhealnotes.items.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class HealNoteInteractListener implements Listener {

    private final ItemManager itemManager;

    private final Set<UUID> editingPlayers = new HashSet<>();

    public HealNoteInteractListener(DeathHealNotes plugin) {
        this.itemManager = plugin.getItemManager();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        ItemStack item = event.getItem();

        if (item == null) {
            return;
        }

        if (!itemManager.isHealNote(item)) {
            return;
        }

        Action action = event.getAction();

        if (action != Action.RIGHT_CLICK_AIR &&
                action != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        event.setCancelled(true);

        editingPlayers.add(player.getUniqueId());

        player.openBook(item);
    }

    public boolean isEditing(Player player) {
        return editingPlayers.contains(player.getUniqueId());
    }

    public void stopEditing(Player player) {
        editingPlayers.remove(player.getUniqueId());
    }
}
