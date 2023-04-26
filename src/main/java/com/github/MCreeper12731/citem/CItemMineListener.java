package com.github.MCreeper12731.citem;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public interface CItemMineListener {

    void execute(BlockBreakEvent event);

}
