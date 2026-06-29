package fr.merrylax.deathhealnotes;

import fr.merrylax.deathhealnotes.items.ItemManager;
import fr.merrylax.deathhealnotes.managers.DeathManager;
import fr.merrylax.deathhealnotes.recipes.RecipeManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathHealNotes extends JavaPlugin {

    private static DeathHealNotes instance;

    private ItemManager itemManager;
    private RecipeManager recipeManager;
    private DeathManager deathManager;

    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();

        // Gestionnaires
        itemManager = new ItemManager(this);

        recipeManager = new RecipeManager(this);
        recipeManager.registerRecipes();

        deathManager = new DeathManager(this);
        deathManager.startTask();

        getLogger().info("========================================");
        getLogger().info("DeathHealNotes est activé !");
        getLogger().info("Version : " + getDescription().getVersion());
        getLogger().info("Développeur : Merrylax");
        getLogger().info("========================================");

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

    public RecipeManager getRecipeManager() {
        return recipeManager;
    }

    public DeathManager getDeathManager() {
        return deathManager;
    }

}
