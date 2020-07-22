package PHPAPI;

import java.util.List;
import java.util.StringJoiner;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class phpapi extends JavaPlugin {
	Logger log = Bukkit.getLogger();
	String prefix = "[PHP API]";
	String mc = "phpapi";
	
	public void onEnable() {
		print("Plugin enabled!");
	}
	
	public void onDisable() {
		print("Plugin disabled!");
	}
	
	public void print(String t) {
		log.info(prefix + " " + t);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	    if(command.getName().equalsIgnoreCase(mc)){
	    	if(args.length >= 1) {
	    		
	    		//Command handler
	    		if(args[0].equalsIgnoreCase("gpd")) {
	    			if(args.length == 2) {
	    				List<String> groups = PermissionsEx.getUser(args[1]).getParentIdentifiers(); //Get list of groups
	    				StringJoiner t = new StringJoiner(",");
	    				for(String i : groups) {
	    					t.add(i); //Add i group to t
	    				};
	    				sender.sendMessage(t.toString()); //Send
	    			}else {
	    				sender.sendMessage("Usage: /" + mc + " gpd <player>");
	    			}
	    		}else if(args[0].equalsIgnoreCase("mon")) {
	    			StringJoiner names = new StringJoiner(",");
	    			for (Player p : Bukkit.getOnlinePlayers()) {
	    				List<String> groups = PermissionsEx.getUser(p.getName()).getParentIdentifiers(); //Get list of groups
	    				StringJoiner t = new StringJoiner(",");
	    				for(String i : groups) {
	    					t.add(i); //Add i group to t
	    				};
	    			    names.add(String.format("[%s,[%s]]", p.getName(), t.toString())); //[player1, [player1donates]]
	    			}
	    			sender.sendMessage(String.format("[[%d, %d],[%s]]", getServer().getMaxPlayers(), getServer().getOnlinePlayers().size(), names.toString())); //[[Max,current],[[player1, [player1donates]],[player2, [player2donates]]]]
	    		}else {
	    			sender.sendMessage(prefix + " ERROR unknown command, please see help!"); //Help message
	    		}
	    		////////////////
	    		
	    	}else{
	    		sender.sendMessage(prefix + " Plugin help:\n  /" + mc + " gpd <player> -> Get player donate\n  /" + mc + " mon -> Monitoring"); //Help message
	    	}
	    	return true;
	    }
	    return false;
	}
}