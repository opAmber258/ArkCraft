package com.ArkCraft.arkCraft.Entities.NPC;




import com.ArkCraft.arkCraft.Utils;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Utils.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRenderers {

    @SubscribeEvent
    public static void registerRenderers(FMLClientSetupEvent event) {
        // 注册NPC渲染器：复用原版PlayerRenderer（原生支持3D皮肤）
        EntityRenderers.register(
                ModEntities.PLAYER_NPC.get(),
                context -> {
                    // 1. 创建玩家模型（支持3D皮肤，false=Steve模型，true=Alex细臂）
                    PlayerModel<TestNPC> playerModel = new PlayerModel<>(
                            context.bakeLayer(ModelLayers.PLAYER), // 原版玩家模型层（3D皮肤核心）
                            false // false=Steve（64x64），true=Alex（64x32细臂）
                    );

                    // 2. 创建自定义PlayerRenderer，绑定3D模型
                    return new LivingEntityRenderer<>(context, playerModel, 0.5F) {
                        // 重写纹理路径：指向3D皮肤文件
                        @Override
                        public ResourceLocation getTextureLocation(TestNPC entity) {
                            return new ResourceLocation(Utils.MOD_ID, "textures/entity/npc/npc.png");
                        }
                    };
                }
        );

        EntityRenderers.register(
                ModEntities.SKY_MICO.get(),
                context -> {
                    PlayerModel<SkyMico> playerModel = new PlayerModel<>(
                            context.bakeLayer(ModelLayers.PLAYER),
                            false
                    );

                    return new LivingEntityRenderer<>(context, playerModel, 0.5F) {
                        @Override
                        public ResourceLocation getTextureLocation(SkyMico entity) {
                            return new ResourceLocation(Utils.MOD_ID, "textures/entity/npc/sky_mico.png");
                        }
                    };
                });


        EntityRenderers.register(
                ModEntities.VERY_H.get(),
                context -> {
                    PlayerModel<VeryH> playerModel = new PlayerModel<>(
                            context.bakeLayer(ModelLayers.PLAYER),
                            false
                    );

                    return new LivingEntityRenderer<>(context, playerModel, 0.5F) {
                        @Override
                        public ResourceLocation getTextureLocation(VeryH entity) {
                            return new ResourceLocation(Utils.MOD_ID, "textures/entity/npc/very_h.png");
                        }
                    };
                }
        );

        EntityRenderers.register(
                ModEntities.MOT.get(),
                context -> {
                    // 核心修改：使用细臂玩家模型层 PLAYER_SLIM
                    PlayerModel<Mot> playerModel = new PlayerModel<>(
                            context.bakeLayer(ModelLayers.PLAYER_SLIM), // 替换为细臂模型层
                            true // slim=true 配合PLAYER_SLIM生效
                    );

                    return new LivingEntityRenderer<>(context, playerModel, 0.5F) {
                        @Override
                        public ResourceLocation getTextureLocation(Mot entity) {
                            return new ResourceLocation(Utils.MOD_ID, "textures/entity/npc/mot.png");
                        }
                    };
                }
        );

    }
}

