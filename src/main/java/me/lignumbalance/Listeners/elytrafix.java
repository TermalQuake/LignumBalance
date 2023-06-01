package me.lignumbalance.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EnderDragon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class elytrafix implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item != null && item.getType() == Material.FIREWORK_ROCKET && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (event.getAction() == Action.RIGHT_CLICK_AIR && event.getPlayer().isGliding()) {
                if (meta.hasLore() && meta.getLore().contains("123")) { // изменить лор надо
                    event.setCancelled(false);
                    event.getPlayer().sendMessage(ChatColor.GREEN + "можно летать");
                } else {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(ChatColor.RED + "нельзя летать");
                }
            }
        }
    }
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof EnderDragon) {
            List<ItemStack> itemsToAdd = new ArrayList<>();
            // Создание прикола в виде книги "Починка"
            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
            meta.addStoredEnchant(Enchantment.MENDING, 1, true);
            item.setItemMeta(meta);
            // Добавление элементов в список itemsToAdd
            itemsToAdd.add(item);
            // Добавление элементов в список предметов, которые выпадут после смерти существа
            event.getDrops().addAll(itemsToAdd);
            event.setDroppedExp(500);
        }
    }
//улучшение фейверка
    @EventHandler
    public void onAnvilUse(final PrepareAnvilEvent event) {
        final ItemStack firstItem = event.getInventory().getItem(0);
        final ItemStack secondItem = event.getInventory().getItem(1);
        if (firstItem == null || secondItem == null) {
            return;
        }
        if (firstItem.getType() == Material.FIREWORK_ROCKET && firstItem.hasItemMeta() && firstItem.getItemMeta().hasLore() && firstItem.getItemMeta().getLore() != null && firstItem.getItemMeta().getLore().contains("123") && secondItem.getType() == Material.DIAMOND_BLOCK) {
            final ItemStack result = new ItemStack(Material.FIREWORK_ROCKET, 1);
            final ItemMeta meta = result.getItemMeta();
            List<String> lore = new ArrayList<>();
            lore.add("456");
            meta.setLore(lore);
            result.setItemMeta(meta);
            event.setResult(result);
        }
    }
}
