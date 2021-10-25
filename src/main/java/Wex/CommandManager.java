package Wex;
import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandManager implements CommandExecutor {
    Schematic sam;
    public CommandManager(Schematic s) {
    sam = s;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
            @NotNull String[] args) {
        sam.pasteSchematic(new File(Bukkit.getServer().getWorldContainer()+"/plugins/WorldEdit/schematics/datatest.schem"),new Location(Bukkit.getWorlds().get(0),0,20,0));
        return false;
    }
    
}
