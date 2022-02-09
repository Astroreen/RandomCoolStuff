package io.github.astroneer.randomcoolstuff.Listeners;

import io.github.astroneer.randomcoolstuff.RandomCoolStuff;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerInteractWithItemFrameListener implements Listener {

    RandomCoolStuff plugin = RandomCoolStuff.getPlugin();
    Logger log = plugin.getLogger();

    @EventHandler
    private void onInteractWithItemFrame(PlayerInteractEntityEvent e){

        boolean ShearItemFrameEnabled = plugin.getConfig().getBoolean("ItemFrame.enabled");

        if (ShearItemFrameEnabled){

            /*
             * Necessary checking and configuration
             */
            Entity entity = e.getRightClicked();
            EntityType entityType = entity.getType();
            Player player = e.getPlayer();
            ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
            Material configItemToUse = Material.valueOf(plugin.getConfig().getString("ItemFrame.itemToUse"));
            ItemFrame itemFrame = null;
            World world = player.getWorld();

            //Checking what type of item frame it is
            if (entityType == EntityType.GLOW_ITEM_FRAME){
                itemFrame = (GlowItemFrame) entity;
            } else if (entityType == EntityType.ITEM_FRAME){
                itemFrame = (ItemFrame) entity;
            } else {
                log.log(Level.WARNING, "ItemFrame was not found!");
            }

            //Trying to get ItemFrame location
            Location loc = null;
            try{
                loc = itemFrame.getLocation();
            } catch (NullPointerException npe){
                log.log(Level.WARNING, "ItemFrame location was not found");
                throw npe;
            }

            /*
             * Start if the process/checking
             * Make ItemFrame Invisible
             */

            if (player.isSneaking() && itemFrame.isVisible() && itemInMainHand.getType().equals(configItemToUse)){

                e.setCancelled(true);
                boolean isConfigItemFrameGlow = plugin.getConfig().getBoolean("ItemFrame.applyGlowingEffectToGlowingItemFrame");

                //Config usage
                Material configDropItem = Material.valueOf(plugin.getConfig().getString("ItemFrame.dropItem"));
                int configItemAmount = plugin.getConfig().getInt("ItemFrame.dropItemAmount");
                Sound configSound = Sound.valueOf(plugin.getConfig().getString("ItemFrame.soundName"));
                float configSoundVolume = 1;
                float configSoundPitch = 1;
                Sound s = Sound.ENTITY_SHEEP_SHEAR;

                /*
                 * Some more checking of config file
                 */

                    //Are sound volume is wrong?
                    try {
                        configSoundVolume = (float) plugin.getConfig().getDouble("ItemFrame.soundVolume");
                    } catch (Exception exc) {
                        log.log(Level.WARNING,"Incorrect sound volume, using the standard.");
                    }

                    //Are sound pitch is wrong?
                    try {
                        configSoundPitch = (float) plugin.getConfig().getDouble("ItemFrame.soundPitch");
                    } catch (Exception exc) {
                        log.log(Level.WARNING, "Incorrect sound pitch, using the standard.");
                    }

                    //Check if the sound is right
                    try {
                        s = Sound.valueOf(String.valueOf(configSound));
                    } catch (Exception exception) {
                        log.log(Level.WARNING, "Incorrect sound in the config, using the standard.");
                    }

                /*
                 * End of the process
                 * Applying changes to ItemFrame
                 */
                itemFrame.setVisible(false);

                if(entityType == EntityType.GLOW_ITEM_FRAME){
                    itemFrame.setGlowing(isConfigItemFrameGlow);
                }

                player.playSound(player.getLocation(), s, configSoundVolume, configSoundPitch );
                ItemStack drop = new ItemStack(configDropItem, configItemAmount);
                world.dropItem(loc, drop);
            }
        }
    }
}
