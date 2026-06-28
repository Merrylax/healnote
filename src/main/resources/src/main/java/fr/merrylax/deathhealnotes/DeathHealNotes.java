package fr.merrylax.deathhealnotes;

import fr.merrylax.deathhealnotes.items.ItemManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathHealNotes extends JavaPlugin {

    private static DeathHealNotes instance;

    private ItemManager itemManager;

    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();

        itemManager = new ItemManager(this);

        getLogger().info("========================================");
        getLogger().info("DeathHealNotes est activé !");
        getLogger().info("Version : " + getDescription().getVersion());
        getLogger().info("Développeur : Merrylax");
        getLogger().info("========================================");

        // TODO : Chargement des recettes
        // TODO : Enregistrement des événements
        // TODO : Enregistrement des commandes
        // TODO : Chargement des données

    }

    @Override
    public void onDisable() {

        getLogger().info("========================================");
        getLogger().info("DeathHealNotes est désactivé.");
        getLogger().info("========================================");

    }

    public static DeathHealNotes getInstance() {
        return instance;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

}
