package me.lignumbalance;

import org.bukkit.plugin.java.JavaPlugin;
import me.lignumbalance.Listeners.villager;
import me.lignumbalance.Listeners.elytrafix;
public final class main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("\u001B[32m" + "All works!" + "\u001B[0m");
        this.getServer().getPluginManager().registerEvents(new villager(), this);
        this.getServer().getPluginManager().registerEvents(new elytrafix(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("\u001B[31m" + "Doesn't work!" + "\u001B[0m");
    }
}
