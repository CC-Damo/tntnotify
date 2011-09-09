	package uk.co.ddelay.bukkit;
	
	import java.util.logging.Logger;
	
	import org.bukkit.event.Event;
	import org.bukkit.event.Event.Priority;
	import org.bukkit.plugin.PluginDescriptionFile;
	import org.bukkit.plugin.PluginManager;
	import org.bukkit.plugin.java.JavaPlugin;
	
	public class TNTNotify extends JavaPlugin {
		
		private Logger log = Logger.getLogger("Minecraft");
		
		private TNTNotifyBlockListener blockListner = new TNTNotifyBlockListener(this);
		
		public void onEnable() {
			PluginManager pm = this.getServer().getPluginManager();
			pm.registerEvent(Event.Type.BLOCK_PLACE, this.blockListner, Priority.Highest, this);
			pm.registerEvent(Event.Type.BLOCK_BREAK, this.blockListner, Priority.Highest, this);
			
			this.logMessage("Enabled");
		}
	
		public void onDisable() {
			this.logMessage("Disabled");
		}
		
		protected void logMessage(String msg){
			PluginDescriptionFile pdFile = this.getDescription();
			this.log.info(pdFile.getName() + " " + pdFile.getVersion() + ": " +msg);
			
		}
		
	
	}
