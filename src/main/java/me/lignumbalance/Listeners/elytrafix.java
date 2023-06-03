package me.lignumbalance.Listeners;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EnderDragon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;


public class elytrafix implements Listener {
    //Дроп с дракона
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
    //Фейверки
    // возможность летать
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item != null && item.getType() == Material.FIREWORK_ROCKET && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (event.getAction() == Action.RIGHT_CLICK_AIR && event.getPlayer().isGliding()) {
                if (meta.hasLore()) {
                    List<String> lore = meta.getLore();
                    if (lore.contains("1") || lore.contains("2") || lore.contains("3")) {
                        event.setCancelled(false);
                        event.getPlayer().sendMessage(ChatColor.GREEN + "можно летать");
                    } else {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage(ChatColor.RED + "нельзя летать");
                    }
                } else {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(ChatColor.RED + "нельзя летать");
                }
            }
        }
    }

    //система прокачки 1
    @EventHandler
    public void onAnvilUse1(final PrepareAnvilEvent event) {
        final ItemStack firstItem = event.getInventory().getItem(0);
        final ItemStack secondItem = event.getInventory().getItem(1);
        if (firstItem == null || secondItem == null) {
            return;
        }
        if (firstItem.getType() == Material.FIREWORK_ROCKET && firstItem.hasItemMeta() && firstItem.getItemMeta().hasLore() && firstItem.getItemMeta().getLore().contains("1") && secondItem.getType() == Material.BLAZE_POWDER) {
            int firstItemCount = firstItem.getAmount(); // Получаем количество первоначального предмета
            final ItemStack result = new ItemStack(Material.FIREWORK_ROCKET, firstItemCount); // Создаем новый предмет с количеством, равным количеству первоначального предмета
            final FireworkMeta fireworkMeta = (FireworkMeta) result.getItemMeta(); // Получаем метаданные предмета в виде FireworkMeta
            fireworkMeta.setPower(2); // Устанавливаем мощность фейерверка
            List<String> lore = new ArrayList<>();
            lore.add("2");
            fireworkMeta.setLore(lore);
            fireworkMeta.setCustomModelData(100);
            result.setItemMeta(fireworkMeta); // Устанавливаем метаданные предмета
            event.setResult(result);
            int repairCost = 10 * firstItemCount; // Устанавливаем стоимость ремонта, зависящую от количества первоначального предмета
            event.getInventory().setRepairCost(repairCost);
        }
    }
    //система прокачки 2
    @EventHandler
    public void onAnvilUse2(final PrepareAnvilEvent event) {
        final ItemStack firstItem = event.getInventory().getItem(0);
        final ItemStack secondItem = event.getInventory().getItem(1);
        if (firstItem == null || secondItem == null) {
            return;
        }
        if (firstItem.getType() == Material.FIREWORK_ROCKET && firstItem.hasItemMeta() && firstItem.getItemMeta().hasLore() && firstItem.getItemMeta().getLore().contains("2") && secondItem.getType() == Material.FIRE_CHARGE) {
            int firstItemCount = firstItem.getAmount(); // Получаем количество первоначального предмета
            final ItemStack result = new ItemStack(Material.FIREWORK_ROCKET, firstItemCount); // Создаем новый предмет с количеством, равным количеству первоначального предмета
            final FireworkMeta fireworkMeta = (FireworkMeta) result.getItemMeta(); // Получаем метаданные предмета в виде FireworkMeta
            fireworkMeta.setPower(3); // Устанавливаем мощность фейерверка
            List<String> lore = new ArrayList<>();
            lore.add("3");
            fireworkMeta.setLore(lore);
            fireworkMeta.setCustomModelData(100);
            result.setItemMeta(fireworkMeta); // Устанавливаем метаданные предмета
            event.setResult(result);
            int repairCost = 10 * firstItemCount; // Устанавливаем стоимость ремонта
            event.getInventory().setRepairCost(repairCost);
        }
    }
}

