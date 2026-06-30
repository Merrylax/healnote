package fr.merrylax.deathhealnotes.listeners;

import fr.merrylax.deathhealnotes.DeathHealNotes;
import fr.merrylax.deathhealnotes.managers.DeathManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;

import java.util.List;

public class HealNoteListener implements Listener {

    private final DeathManager deathManager;
    private final DeathHealNotes plugin;
    private final HealNoteInteractListener healNoteInteractListener;
    
    public HealNoteListener(DeathHealNotes plugin) {
    this.plugin = plugin;
    this.deathManager = plugin.getDeathManager();
    this.healNoteInteractListener = plugin.getHealNoteInteractListener();
}
@EventHandler
public void onBookEdit(PlayerEditBookEvent event) {

    Player player = event.getPlayer();
    if (!healNoteInteractListener.isEditing(player)) {
    return;
}

    List<String> pages = event.getNewBookMeta().getPages();

    if (pages.isEmpty()) {
    healNoteInteractListener.stopEditing(player);
    return;
}

    String targetName = pages.get(0).split("\n")[0];

    Player target = Bukkit.getPlayer(targetName);

    if (target == null) {
    healNoteInteractListener.stopEditing(player);
    return;
}
    boolean success = deathManager.healPlayer(target.getUniqueId());

    if (success) {

    player.sendMessage("§aHeal Note utilisé sur " + target.getName());

    deathManager.forceKill(player);

    healNoteInteractListener.stopEditing(player);

} else {

    healNoteInteractListener.stopEditing(player);

}
}
}
