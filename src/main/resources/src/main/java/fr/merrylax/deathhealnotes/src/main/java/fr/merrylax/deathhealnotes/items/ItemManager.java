package fr.merrylax.deathhealnotes.items;

import org.bukkit.inventory.meta.BookMeta;
import fr.merrylax.deathhealnotes.DeathHealNotes;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private final DeathHealNotes plugin;
    private final NamespacedKey itemTypeKey;

    public ItemManager(DeathHealNotes plugin) {
        this.plugin = plugin;
        this.itemTypeKey = new NamespacedKey(plugin, "item_type");
    }

    /**
     * Crée une Page Maudite.
     */
    public ItemStack getCursedPage() {

        ItemStack item = new ItemStack(Material.PAPER);

        ItemMeta meta = item.getItemMeta();

        if (meta == null) {
            return item;
        }

        meta.setDisplayName(ChatColor.DARK_RED + "Page Maudite");

        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.GRAY + "Une page arrachée d'un carnet interdit...");
        lore.add("");
        lore.add(ChatColor.RED + "Objet mystérieux");

        meta.setLore(lore);

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        PersistentDataContainer data = meta.getPersistentDataContainer();

        data.set(itemTypeKey, PersistentDataType.STRING, "CURSED_PAGE");

        item.setItemMeta(meta);

        return item;
    }
      /**
     * Crée un Death Note.
     */
    public ItemStack getDeathNote() {

        ItemStack item = new ItemStack(Material.WRITTEN_BOOK);

        BookMeta meta = (BookMeta) item.getItemMeta();

        if (meta == null) {
            return item;
        }

        meta.setTitle("Death Note");
        meta.setAuthor("Inconnu");

        meta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Death Note");

        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.GRAY + "Écris le pseudo d'un joueur.");
        lore.add(ChatColor.GRAY + "Il sera condamné à mourir.");
        lore.add("");
        lore.add(ChatColor.RED + "Usage Unique");

        meta.setLore(lore);

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.getPersistentDataContainer().set(
                itemTypeKey,
                PersistentDataType.STRING,
                "DEATH_NOTE"
        );

        item.setItemMeta(meta);

        return item;
    }

    /**
     * Crée un Heal Note.
     */
    public ItemStack getHealNote() {

        ItemStack item = new ItemStack(Material.WRITTEN_BOOK);

        BookMeta meta = (BookMeta) item.getItemMeta();

        if (meta == null) {
            return item;
        }

        meta.setTitle("Heal Note");
        meta.setAuthor("Inconnu");

        meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Heal Note");

        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.GRAY + "Sauve une victime du Death Note.");
        lore.add(ChatColor.GRAY + "Le sauveteur mourra à sa place.");
        lore.add("");
        lore.add(ChatColor.RED + "Usage Unique");

        meta.setLore(lore);

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.getPersistentDataContainer().set(
                itemTypeKey,
                PersistentDataType.STRING,
                "HEAL_NOTE"
        );

        item.setItemMeta(meta);

        return item;
    }

    /**
     * Crée le Livre des Destins.
     */
    public ItemStack getBookOfDestiny() {

        ItemStack item = new ItemStack(Material.WRITTEN_BOOK);

        BookMeta meta = (BookMeta) item.getItemMeta();

        if (meta == null) {
            return item;
        }

        meta.setTitle("Livre des Destins");
        meta.setAuthor("Inconnu");

        meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Livre des Destins");

        List<String> lore = new ArrayList<>();

        lore.add(ChatColor.GRAY + "Contient les noms");
        lore.add(ChatColor.GRAY + "des personnes condamnées.");

        meta.setLore(lore);

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        meta.getPersistentDataContainer().set(
                itemTypeKey,
                PersistentDataType.STRING,
                "BOOK_OF_DESTINY"
        );

        item.setItemMeta(meta);

        return item;
    }
      /**
     * Vérifie si un objet est un de nos objets personnalisés.
     */
    private boolean hasType(ItemStack item, String type) {

        if (item == null || !item.hasItemMeta()) {
            return false;
        }

        ItemMeta meta = item.getItemMeta();

        if (meta == null) {
            return false;
        }

        PersistentDataContainer data = meta.getPersistentDataContainer();

        String value = data.get(itemTypeKey, PersistentDataType.STRING);

        return type.equals(value);
    }

    public boolean isCursedPage(ItemStack item) {
        return hasType(item, "CURSED_PAGE");
    }

    public boolean isDeathNote(ItemStack item) {
        return hasType(item, "DEATH_NOTE");
    }

    public boolean isHealNote(ItemStack item) {
        return hasType(item, "HEAL_NOTE");
    }

    public boolean isBookOfDestiny(ItemStack item) {
        return hasType(item, "BOOK_OF_DESTINY");
    }

}
