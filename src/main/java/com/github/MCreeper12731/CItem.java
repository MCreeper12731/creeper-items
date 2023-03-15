package com.github.MCreeper12731.CreeperItems2;

import com.github.MCreeper12731.CreeperItems2.citem.CItemClickListener;
import com.github.MCreeper12731.CreeperItems2.citem.CItemScrollListener;
import org.bukkit.NamespacedKey;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;

public class CItem {

    private final ItemStack item;
    private final Map<Interaction, CItemClickListener> clickListeners = new HashMap<>();
    private CItemScrollListener scrollListener;

    CItem(NamespacedKey key, ItemStack item, String id) {

        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(key, PersistentDataType.STRING, id);

        this.item = item;
    }

    public CItem withClickListener(Interaction interaction, CItemClickListener listener) {
        clickListeners.put(interaction, listener);
        return this;
    }

    public CItem withScrollListener(CItemScrollListener listener) {
        this.scrollListener = listener;
        return this;
    }

    public CItemClickListener getClickListener(Interaction interaction) {
        return clickListeners.get(interaction);
    }

    public CItemScrollListener getScrollListener() {
        return scrollListener;
    }

    public void onClick(Interaction interaction, PlayerInteractEvent event) {
        CItemClickListener listener = clickListeners.get(interaction);
        if (listener == null) return;
        listener.execute(event);
    }

    public void onScroll(PlayerItemHeldEvent event) {
        if (scrollListener == null) return;
        scrollListener.execute(event);
    }

    public ItemStack getItem() {
        return item;
    }
}
