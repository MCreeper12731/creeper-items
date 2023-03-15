package com.github.MCreeper12731;

import com.github.MCreeper12731.citem.CItemUsageListener;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class CreeperItems {

    private final NamespacedKey key;
    private final Map<String, CItem> cItems = new HashMap<>();

    public CreeperItems(JavaPlugin plugin, String namespace) {
        this.key = new NamespacedKey(plugin, namespace);
        Bukkit.getPluginManager().registerEvents(new CItemUsageListener(this, key), plugin);
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
