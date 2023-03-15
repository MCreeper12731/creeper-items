package com.github.MCreeper12731;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class CreeperItems {

    private final JavaPlugin plugin;
    private final NamespacedKey key;
    private final Map<String, CItem> cItems = new HashMap<>();

    public CreeperItems(JavaPlugin plugin, String namespace) {
        this.plugin = plugin;
        this.key = new NamespacedKey(plugin, namespace);
    }

    public CItem create(ItemStack itemStack, String id) {
        CItem cItem = new CItem(key, itemStack, id);
        cItems.put(id, cItem);
        return cItem;
    }

    public CItem getItem(String id) {
        return cItems.get(id);
    }


}
