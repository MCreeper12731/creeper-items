package com.github.MCreeper12731.citem;

import com.github.MCreeper12731.CItem;
import com.github.MCreeper12731.CreeperItems;
import com.github.MCreeper12731.Interaction;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class CItemUsageListener implements Listener {

    private final CreeperItems plugin;
    private final NamespacedKey key;

    public CItemUsageListener(CreeperItems plugin, NamespacedKey key) {
        this.plugin = plugin;
        this.key = key;
    }

    @EventHandler
    public void onItemClick(PlayerInteractEvent event) {

        ItemStack heldItem = event.getItem();
        if (heldItem == null) return;
        if (!isCustomItem(heldItem)) return;

        CItem item = plugin.getItem(getId(heldItem));

        if (item == null) return;

        Action action = event.getAction();
        Player player = event.getPlayer();

        switch (action) {
            case LEFT_CLICK_AIR -> {
                item.onClick(Interaction.LEFT_CLICK_ANY, event);
                item.onClick(Interaction.LEFT_CLICK_AIR, event);
                if (player.isSneaking()) {
                    item.onClick(Interaction.SNEAK_LEFT_CLICK_ANY, event);
                    item.onClick(Interaction.SNEAK_LEFT_CLICK_AIR, event);
                }
            }
            case LEFT_CLICK_BLOCK -> {
                item.onClick(Interaction.LEFT_CLICK_ANY, event);
                item.onClick(Interaction.LEFT_CLICK_BLOCK, event);
                if (player.isSneaking()) {
                    item.onClick(Interaction.SNEAK_LEFT_CLICK_ANY, event);
                    item.onClick(Interaction.SNEAK_LEFT_CLICK_BLOCK, event);
                }
            }
            case RIGHT_CLICK_AIR -> {
                item.onClick(Interaction.RIGHT_CLICK_ANY, event);
                item.onClick(Interaction.RIGHT_CLICK_AIR, event);
                if (player.isSneaking()) {
                    item.onClick(Interaction.SNEAK_RIGHT_CLICK_ANY, event);
                    item.onClick(Interaction.SNEAK_RIGHT_CLICK_AIR, event);
                }
            }
            case RIGHT_CLICK_BLOCK -> {
                item.onClick(Interaction.RIGHT_CLICK_ANY, event);
                item.onClick(Interaction.RIGHT_CLICK_BLOCK, event);
                if (player.isSneaking()) {
                    item.onClick(Interaction.SNEAK_RIGHT_CLICK_ANY, event);
                    item.onClick(Interaction.SNEAK_RIGHT_CLICK_BLOCK, event);
                }
            }
        }

    }

    @EventHandler
    public void onItemScroll(PlayerItemHeldEvent event) {

        if (!event.getPlayer().isSneaking()) return;

        ItemStack heldItem = event.getPlayer().getInventory().getItem(event.getPreviousSlot());
        if (heldItem == null) return;
        if (!isCustomItem(heldItem)) return;

        CItem item = plugin.getItem(getId(heldItem));

        item.onScroll(event);

    }

    private boolean isCustomItem(ItemStack item) {
        return item.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING);
    }

    private String getId(ItemStack item) {
        return item.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING);
    }

}
