package com.ArkCraft.arkCraft.Events.Mobs;

import com.ArkCraft.arkCraft.Entities.Mobs.Drop.DropRegistry;
import com.ArkCraft.arkCraft.Entities.Mobs.Drop.DropTable;
import com.ArkCraft.arkCraft.Entities.Mobs.MobAttributes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber
public class MobDeathEvent {

    @SubscribeEvent
    public static void onMobDeath(LivingDeathEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        if (!(event.getEntity() instanceof Mob mob)) return;
        if (!(event.getSource().getEntity() instanceof Player player)) return;

        String key = MobAttributes.getMobOriginName(mob);
        if (key.isEmpty()) return;

        DropTable table = DropRegistry.get(key);
        if (table == null) return;

        for (ItemStack stack : table.rollAll(mob.getRandom())) {
            boolean ok = player.addItem(stack);
            if (!ok || !stack.isEmpty()) {
                player.drop(stack, false);
            }
        }
    }
}




    /*@SubscribeEvent
    public static void onMobDeath(LivingDeathEvent event) {
        Entity source = event.getSource().getEntity();

        if (event.getEntity() instanceof Mob mob &&
                source instanceof Player player && Main.isArkCraftMob(mob)) {
            player.addItem(new ItemStack(ModItems.IceWolfSkin.get()));
        }
    }

     */
