package Wex;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    
    @Override
    public void onEnable() {
        // TODO Auto-generated method stub
        Schematic s = new Schematic();
        CommandManager cm = new CommandManager(s);
        this.getCommand("pasteschmeatic").setExecutor(cm);
    }
    @Override
    public void onDisable() {
        // TODO Auto-generated method stub
        
    }
}
