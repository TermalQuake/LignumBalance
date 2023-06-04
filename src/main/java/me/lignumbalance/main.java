package me.lignumbalance;

//main
import org.bukkit.Bukkit;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import me.lignumbalance.Listeners.villager;
import me.lignumbalance.Listeners.elytrafix;

//fireworkFly
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.Collections;

public final class main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("\u001B[32m" + "All works!" + "\u001B[0m");
        this.getServer().getPluginManager().registerEvents(new villager(), this);
        this.getServer().getPluginManager().registerEvents(new elytrafix(), this);

        // craft balanced firework
        ItemStack fireworkFly = new ItemStack(Material.FIREWORK_ROCKET);

        NamespacedKey key = new NamespacedKey(this, "my-custom-firework");
        ItemMeta im = fireworkFly.getItemMeta();
        FireworkMeta meta = (FireworkMeta) Bukkit.getItemFactory().getItemMeta(Material.FIREWORK_ROCKET);

        meta.setPower(1);
        im.setDisplayName("Чудо Фейверк");
        im.setLore(Collections.singletonList("1. Можно летать"));
        fireworkFly.setItemMeta(im);

        // Set firework power
        FireworkMeta fireworkMeta = (FireworkMeta) fireworkFly.getItemMeta();
        fireworkMeta.setPower(1);
        fireworkFly.setItemMeta(fireworkMeta);

        ShapedRecipe recipe = new ShapedRecipe(key, fireworkFly);
        //recipe.shape("AAA", "ABA", "AAA"); //craft 8diamonds=1FR
        recipe.shape("BGB", "GFG","BGB");
        recipe.setIngredient('B', Material.BLAZE_POWDER);
        recipe.setIngredient('G', Material.GUNPOWDER);
        recipe.setIngredient('F', Material.FIREWORK_ROCKET);

        // Register craft
        getServer().addRecipe(recipe);
    }

    @Override
    public void onDisable() {
        System.out.println("\u001B[31m" + "Doesn't work!" + "\u001B[0m");
    }
}
