package io.github.astroneer.randomcoolstuff.Listeners;

import io.github.astroneer.randomcoolstuff.RandomCoolStuff;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerBreakItemFrameListener implements Listener {

    RandomCoolStuff plugin = RandomCoolStuff.getPlugin();

    @EventHandler
    public void onBreakItemFrame(EntityDamageByEntityEvent e){

        boolean ShearItemFrameEnabled = plugin.getConfig().getBoolean("ItemFrame.enabled");

        /*
         * Start of process
         * Dropping invisible item frames items
         */
        if (ShearItemFrameEnabled){
            Entity entity = e.getEntity();
            ItemFrame frame = null;

            if(entity.getType() == EntityType.ITEM_FRAME) {
                frame = (ItemFrame) entity;
            } else if (entity.getType() == EntityType.GLOW_ITEM_FRAME){
                frame = (GlowItemFrame) entity;
            } else if (frame == null) return;

            if(frame.isVisible()){
                return;
            }

            Entity damager = e.getDamager();

            if (damager instanceof Player){

                ItemStack dropFromInvisFrame = new ItemStack(Material.STICK, 8);
                World world = entity.getWorld();
                Location loc = frame.getLocation();
                ItemStack itemInFrame = frame.getItem();

                //Drop Items
                if(!frame.isVisible() && !frame.isGlowing()){

                    world.dropItem(loc, dropFromInvisFrame);
                    world.dropItem(loc, itemInFrame);

                } else if(!frame.isVisible() && frame.isGlowing()){
                    ItemStack dropGlowInkSac = new ItemStack(Material.GLOW_INK_SAC, 1);
                    world.dropItem(loc, dropFromInvisFrame);
                    world.dropItem(loc, dropGlowInkSac);
                    world.dropItem(loc, itemInFrame);
                }

                /*
                 *End of the process
                 *Removing ItemFrame
                 */
                frame.remove();
                ((Player) damager).playSound(loc, Sound.ENTITY_ITEM_FRAME_BREAK, 1, 1);
            }
        }
    }
}
