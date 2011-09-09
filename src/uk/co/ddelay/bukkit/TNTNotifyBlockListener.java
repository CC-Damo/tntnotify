package uk.co.ddelay.bukkit;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class TNTNotifyBlockListener extends BlockListener  {
	private TNTNotify plugin;
	
	
	public TNTNotifyBlockListener(TNTNotify instance) {
		this.plugin = instance;
	}

	public void onBlockPlace(BlockPlaceEvent event){
		if (event.isCancelled()) return;
		
		Block block = event.getBlock();
		Player player = event.getPlayer();
		
		if (block.getType() == Material.TNT && player.hasPermission("tntnotify.allow-placement") == false){
			block.setType(Material.CAKE_BLOCK);
			player.sendMessage(ChatColor.DARK_RED + "You Are Not Allowed to use this!");
			player.sendMessage(ChatColor.GOLD + "Have Some Cake Instead!");
			plugin.getServer().broadcastMessage(player.getName() + " " +  "Tried to place TNT");
			plugin.logMessage(player.getName() + " " + "Placed TnT @ " + block.getX() + "," + block.getY() + "," + block.getZ());
		}
	
	}
	
	public void onBlockBreak(BlockBreakEvent event){
		if (event.isCancelled()) return;
		
		Block block = event.getBlock();
		Player player = event.getPlayer();
		
		if(block.getType() == Material.TNT && player.hasPermission("tntnotify.allow-drop") == false){
			event.setCancelled(true);
			block.setType(Material.AIR);
			
			block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.CAKE, 1));
		}
	}
}
