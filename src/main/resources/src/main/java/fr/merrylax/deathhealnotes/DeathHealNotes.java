package fr.merrylax.deathhealnotes;

import org.bukkit.plugin.java.JavaPlugin;

public final class DeathHealNotes extends JavaPlugin {

    private static DeathHealNotes instance;

    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();

        getLogger().info("========================================");
        getLogger().info("DeathHealNotes est activé !");
        getLogger().info("Version : " + getDescription().getVersion());
        getLogger().info("Développeur : Merrylax");
        getLogger().info("========================================");

        // TODO : Enregistrement des commandes
        // TODO : Enregistrement des événements
        // TODO : Chargement des recettes
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

}
