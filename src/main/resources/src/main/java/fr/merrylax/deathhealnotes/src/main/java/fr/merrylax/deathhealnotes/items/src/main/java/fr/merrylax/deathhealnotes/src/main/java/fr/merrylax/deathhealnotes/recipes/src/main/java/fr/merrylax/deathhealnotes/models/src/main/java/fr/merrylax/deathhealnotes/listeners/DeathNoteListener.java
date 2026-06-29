package fr.merrylax.deathhealnotes.listeners;

import fr.merrylax.deathhealnotes.DeathHealNotes;
import fr.merrylax.deathhealnotes.items.ItemManager;
import fr.merrylax.deathhealnotes.managers.DeathManager;
import org.bukkit.event.Listener;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class DeathNoteListener implements Listener {

    private final DeathHealNotes plugin;
    private final ItemManager itemManager;
    private final DeathManager deathManager;

    private final Set<UUID> editingDeathNotes = new HashSet<>();

    public DeathNoteListener(DeathHealNotes plugin) {

        this.plugin = plugin;
        this.itemManager = plugin.getItemManager();
        this.deathManager = plugin.getDeathManager();

    }

}
