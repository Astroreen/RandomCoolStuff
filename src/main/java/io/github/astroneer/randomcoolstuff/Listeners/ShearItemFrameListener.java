package io.github.astroneer.randomcoolstuff.Listeners;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerShearEntityEvent;

public class ShearItemFrameListener implements Listener {

    @EventHandler
    private void ShearItemFrame (PlayerShearEntityEvent e){
        Entity entity = e.getEntity();
        Player player = e.getPlayer();

        if (entity.getType() == EntityType.ITEM_FRAME){
            ItemFrame itemFrame = (ItemFrame) entity;

            Sound configSound = null;
            float configSoundVolume = 1;
            float configSoundPitch = 1;
            Sound s = null;


            try {
                s = Sound.valueOf(String.valueOf(configSound));
            } catch (Exception exception) {
                s = Sound.ENTITY_SHEEP_SHEAR;
            }

            itemFrame.setVisible(false);
            player.playSound(player.getLocation(), s, configSoundVolume, configSoundPitch );
        }

    }
}
