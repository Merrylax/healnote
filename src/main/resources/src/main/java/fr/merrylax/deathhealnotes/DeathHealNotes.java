package fr.merrylax.deathhealnotes;


import fr.merrylax.deathhealnotes.items.ItemManager;
import fr.merrylax.deathhealnotes.listeners.BookListener;
import fr.merrylax.deathhealnotes.listeners.DeathNoteListener;
import fr.merrylax.deathhealnotes.listeners.HealNoteListener;
import fr.merrylax.deathhealnotes.managers.DeathManager;
import fr.merrylax.deathhealnotes.recipes.RecipeManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathHealNotes extends JavaPlugin {

    private static DeathHealNotes instance;

    private ItemManager itemManager;
    private RecipeManager recipeManager;
    private DeathManager deathManager;

    private DeathNoteListener deathNoteListener;
    private BookListener bookListener;
    private HealNoteListener healNoteListener;

    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();

        // Managers
        itemManager = new ItemManager(this);

        recipeManager = new RecipeManager(this);
        recipeManager.registerRecipes();

        deathManager = new DeathManager(this);
        deathManager.startTask();

        // Listeners
        deathNoteListener = new DeathNoteListener(this);
        bookListener = new BookListener(this);
        healNoteListener = new HealNoteListener(this);

        getServer().getPluginManager().registerEvents(deathNoteListener, this);
        getServer().getPluginManager().registerEvents(bookListener, this);
        getServer().getPluginManager().registerEvents(healNoteListener, this);

        getLogger().info("========================================");
        getLogger().info("DeathHealNotes est activé !");
        getLogger().info("Version : " + getDescription().getVersion());
        getLogger().info("Développeur : Merrylax");
        getLogger().info("========================================");
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

    public DeathNoteListener getDeathNoteListener() {
        return deathNoteListener;
    }

    public BookListener getBookListener() {
        return bookListener;
    }

    public HealNoteListener getHealNoteListener() {
        return healNoteListener;
    }

}
