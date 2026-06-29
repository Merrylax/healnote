package fr.merrylax.deathhealnotes.listeners;

import org.bukkit.Bukkit;
import fr.merrylax.deathhealnotes.DeathHealNotes;
import fr.merrylax.deathhealnotes.managers.DeathManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;

import java.util.List;
import java.util.UUID;

public class BookListener implements Listener {

    private final DeathHealNotes plugin;
    private final DeathManager deathManager;
    private final DeathNoteListener deathNoteListener;

    public BookListener(DeathHealNotes plugin) {

        this.plugin = plugin;
        this.deathManager = plugin.getDeathManager();
        this.deathNoteListener = plugin.getDeathNoteListener();

    }
@EventHandler
public void onBookEdit(PlayerEditBookEvent event) {

    Player player = event.getPlayer();

    // Vérifie si le joueur est en train d'éditer un Death Note
    if (!deathNoteListener.isEditing(player)) {
        return;
    }

    List<String> pages = event.getNewBookMeta().getPages();

    if (pages.size() < 1) {
        deathNoteListener.stopEditing(player);
        return;
    }

    String firstLine = "";
    String secondLine = "";

    if (!pages.isEmpty()) {
        String[] lines = pages.get(0).split("\n");

        if (lines.length >= 1) {
            firstLine = lines[0];
        }

        if (lines.length >= 2) {
            secondLine = lines[1];
        }
    }

    // TODO : conversion du temps
    // TODO : appel DeathManager
    String victimName = firstLine;
String timeInput = secondLine;

if (victimName == null || victimName.isEmpty()) {
    deathNoteListener.stopEditing(player);
    return;
}

if (timeInput == null || timeInput.isEmpty()) {
    deathNoteListener.stopEditing(player);
    return;
}

long duration = parseTime(timeInput);

if (duration < 3000 || duration > 3600000) {
    deathNoteListener.stopEditing(player);
    return;
}
  UUID victimUUID = Bukkit.getOfflinePlayer(victimName).getUniqueId();

boolean success = deathManager.condemnPlayer(
        victimUUID,
        player.getUniqueId(),
        duration
);

if (!success) {
    deathNoteListener.stopEditing(player);
    return;
}  
  deathNoteListener.stopEditing(player);
}
private long parseTime(String input) {

    input = input.toLowerCase();

    if (input.endsWith("s")) {
        return Long.parseLong(input.replace("s", "")) * 1000;
    }

    if (input.endsWith("m")) {
        return Long.parseLong(input.replace("m", "")) * 60 * 1000;
    }

    if (input.endsWith("h")) {
        return Long.parseLong(input.replace("h", "")) * 60 * 60 * 1000;
    }

    return -1;
}

}
