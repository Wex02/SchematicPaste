package Wex;

import net.minecraft.server.v1_16_R3.NBTCompressedStreamTools;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;



 
public class Schematic {
    static HashMap<Integer,String> Palette = new HashMap<>();;

    //Parsing the blockdata string from the palette to BlockData
    //The blockdata string looks like this minecraft:furnace[lit=true] for example
    public BlockData getBlockData(int index){
        //Getting the string from the HashMap
        String palettestring = Palette.get(index);
        //Parsing it into BlockData
        return Bukkit.getServer().createBlockData(palettestring);
    }

    //Palette is made of a key and a value like AIR:0 or DIRT:12 it is stored in the schem file
    //Reading in from the schem file and storing in a HashMap
    public void getPalette(NBTTagCompound paletteCompound){
     for(String s : paletteCompound.getKeys()){
        Palette.put(paletteCompound.getInt(s), s);
    } 
    }

    public void pasteSchematic(File f,Location l){
    FileInputStream fis;
    try {
        fis = new FileInputStream(f);
        //Reading in the schem file
        NBTTagCompound nbt = NBTCompressedStreamTools.a(fis);     
           
    short width = nbt.getShort("Width");
    short height = nbt.getShort("Height");
    short length = nbt.getShort("Length");
    byte[] blocks = nbt.getByteArray("BlockData");
    getPalette(nbt.getCompound("Palette"));
    //Loop XYZ coords
    for(int x = 0; x < width; ++x){
            for(int y = 0; y < height; ++y){
             for(int z = 0; z < length; ++z){
                //Block Index in schem file
                int index = (y * length * width) + (z * width) + x;
                //Location where the schem gets pasted + offset by the block location
                Location BlockLocation = new Location(l.getWorld(), l.getX()+x, l.getY()+y, l.getZ()+z);
                Block block = BlockLocation.getBlock();
                //Setting block data at location
                block.setBlockData(getBlockData((int)blocks[index]& 0xFF));
                //(int)blocks[index] getting the block id from the array 
                //& 0xFF making it unsigned
            }
            }
        }      
    
    } catch (Exception e) {
        e.printStackTrace();
    }

    }
    
}
