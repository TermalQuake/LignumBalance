package me.lignumbalance.Listeners;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantInventory;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class villager implements Listener {

    @EventHandler
    private void onAttemptTrade(InventoryOpenEvent event) {
        Inventory inventory = event.getInventory();
        if (inventory.getHolder() instanceof Villager) {
            if (inventory instanceof MerchantInventory) {
                Merchant merchant = ((MerchantInventory)inventory).getMerchant();
                List<MerchantRecipe> recipes = new ArrayList(merchant.getRecipes());
                merchant.getRecipes().forEach((recipe) -> {
                    ItemStack result = recipe.getResult();
                    if (result.getType() == Material.ENCHANTED_BOOK) {
                        ItemMeta itemMeta = result.getItemMeta();
                        if (itemMeta instanceof EnchantmentStorageMeta) {
                            EnchantmentStorageMeta enchantmentStorageMeta = (EnchantmentStorageMeta)itemMeta;
                            if (enchantmentStorageMeta.hasStoredEnchant(Enchantment.MENDING)) {
                                recipes.remove(recipe);
                            }
                        }
                    }
                });
                merchant.setRecipes(recipes);
            }
        }
    }
}

