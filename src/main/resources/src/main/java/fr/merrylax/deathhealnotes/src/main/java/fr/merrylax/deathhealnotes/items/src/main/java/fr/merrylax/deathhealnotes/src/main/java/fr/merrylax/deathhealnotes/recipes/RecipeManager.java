package fr.merrylax.deathhealnotes.recipes;

import fr.merrylax.deathhealnotes.DeathHealNotes;
import fr.merrylax.deathhealnotes.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

public class RecipeManager {

    private final DeathHealNotes plugin;
    private final ItemManager itemManager;

    public RecipeManager(DeathHealNotes plugin) {
        this.plugin = plugin;
        this.itemManager = plugin.getItemManager();
    }

    /**
     * Enregistre toutes les recettes du plugin.
     */
    public void registerRecipes() {
        registerCursedPageRecipe();
        registerDeathNoteRecipe();
        registerHealNoteRecipe();
        registerBookOfDestinyRecipe();
    }

    /**
     * Recette de la Page Maudite.
     */
    private void registerCursedPageRecipe() {

        NamespacedKey key = new NamespacedKey(plugin, "cursed_page");

        ShapedRecipe recipe = new ShapedRecipe(
                key,
                itemManager.getCursedPage()
        );

        recipe.shape(
                "BBB",
                "BPB",
                "BBB"
        );

        recipe.setIngredient('B', Material.BLACK_DYE);
        recipe.setIngredient('P', Material.PAPER);

        Bukkit.addRecipe(recipe);
    }

    /**
     * Recette du Death Note.
     */
    private void registerDeathNoteRecipe() {

        NamespacedKey key = new NamespacedKey(plugin, "death_note");

        ShapedRecipe recipe = new ShapedRecipe(
                key,
                itemManager.getDeathNote()
        );

       recipe.shape(
    "TNT",
    "NBN",
    "TNT"
);

recipe.setIngredient('T', Material.TOTEM_OF_UNDYING);
recipe.setIngredient('N', Material.PAPER);
recipe.setIngredient('B', Material.WRITTEN_BOOK); 

        Bukkit.addRecipe(recipe);
    }

    /**
     * Recette du Heal Note.
     */
    private void registerHealNoteRecipe() {

        NamespacedKey key = new NamespacedKey(plugin, "heal_note");

        ShapedRecipe recipe = new ShapedRecipe(
                key,
                itemManager.getHealNote()
        );

        recipe.shape(
    "NTN",
    "TBT",
    "NTN"
);

recipe.setIngredient('N', Material.PAPER);
recipe.setIngredient('T', Material.TOTEM_OF_UNDYING);
recipe.setIngredient('B', Material.BOOK);

        Bukkit.addRecipe(recipe);
    }

    /**
     * Recette du Livre des Destins.
     */
    private void registerBookOfDestinyRecipe() {

        NamespacedKey key = new NamespacedKey(plugin, "book_of_destiny");

        ShapedRecipe recipe = new ShapedRecipe(
                key,
                itemManager.getBookOfDestiny()
        );

        recipe.shape(
                "DDD",
                "DBD",
                "DDD"
        );

        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('B', Material.BOOK);

        Bukkit.addRecipe(recipe);
    }

}
