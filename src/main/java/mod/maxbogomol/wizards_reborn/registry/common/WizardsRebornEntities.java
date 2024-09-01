package mod.maxbogomol.wizards_reborn.registry.common;

import mod.maxbogomol.fluffy_fur.client.render.entity.CustomBoatRenderer;
import mod.maxbogomol.fluffy_fur.common.entity.CustomBoatEntity;
import mod.maxbogomol.fluffy_fur.common.entity.CustomChestBoatEntity;
import mod.maxbogomol.wizards_reborn.WizardsReborn;
import mod.maxbogomol.wizards_reborn.client.render.entity.SniffaloRenderer;
import mod.maxbogomol.wizards_reborn.client.render.entity.SpellProjectileRenderer;
import mod.maxbogomol.wizards_reborn.client.render.entity.SplitArrowRenderer;
import mod.maxbogomol.wizards_reborn.client.render.entity.ThrowedScytheRenderer;
import mod.maxbogomol.wizards_reborn.common.entity.SniffaloEntity;
import mod.maxbogomol.wizards_reborn.common.entity.SpellProjectileEntity;
import mod.maxbogomol.wizards_reborn.common.entity.SplitArrowEntity;
import mod.maxbogomol.wizards_reborn.common.entity.ThrowedScytheEntity;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WizardsRebornEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, WizardsReborn.MOD_ID);

    public static final RegistryObject<EntityType<CustomBoatEntity>> ARCANE_WOOD_BOAT = ENTITIES.register("arcane_wood_boat", () -> EntityType.Builder.<CustomBoatEntity>of((t, l) -> (new CustomBoatEntity(t, l, WizardsRebornItems.ARCANE_WOOD_BOAT)), MobCategory.MISC).sized(1.375f, 0.5625f).build(new ResourceLocation(WizardsReborn.MOD_ID, "arcane_wood_boat").toString()));
    public static final RegistryObject<EntityType<CustomChestBoatEntity>> ARCANE_WOOD_CHEST_BOAT = ENTITIES.register("arcane_wood_chest_boat", () -> EntityType.Builder.<CustomChestBoatEntity>of((t, l) -> (new CustomChestBoatEntity(t, l, WizardsRebornItems.ARCANE_WOOD_CHEST_BOAT)), MobCategory.MISC).sized(1.375f, 0.5625f).build(new ResourceLocation(WizardsReborn.MOD_ID, "arcane_wood_chest_boat").toString()));
    public static final RegistryObject<EntityType<CustomBoatEntity>> INNOCENT_WOOD_BOAT = ENTITIES.register("innocent_wood_boat", () -> EntityType.Builder.<CustomBoatEntity>of((t, l) -> (new CustomBoatEntity(t, l, WizardsRebornItems.INNOCENT_WOOD_BOAT)), MobCategory.MISC).sized(1.375f, 0.5625f).build(new ResourceLocation(WizardsReborn.MOD_ID, "innocent_wood_boat").toString()));
    public static final RegistryObject<EntityType<CustomChestBoatEntity>> INNOCENT_WOOD_CHEST_BOAT = ENTITIES.register("innocent_wood_chest_boat", () -> EntityType.Builder.<CustomChestBoatEntity>of((t, l) -> (new CustomChestBoatEntity(t, l, WizardsRebornItems.INNOCENT_WOOD_CHEST_BOAT)), MobCategory.MISC).sized(1.375f, 0.5625f).build(new ResourceLocation(WizardsReborn.MOD_ID, "innocent_wood_chest_boat").toString()));
    public static final RegistryObject<EntityType<CustomBoatEntity>> CORK_BAMBOO_RAFT = ENTITIES.register("cork_bamboo_raft", () -> EntityType.Builder.<CustomBoatEntity>of((t, l) -> (new CustomBoatEntity(t, l, WizardsRebornItems.CORK_BAMBOO_RAFT)), MobCategory.MISC).sized(1.375f, 0.5625f).build(new ResourceLocation(WizardsReborn.MOD_ID, "cork_bamboo_boat").toString()));
    public static final RegistryObject<EntityType<CustomChestBoatEntity>> CORK_BAMBOO_CHEST_RAFT = ENTITIES.register("cork_bamboo_chest_raft", () -> EntityType.Builder.<CustomChestBoatEntity>of((t, l) -> (new CustomChestBoatEntity(t, l, WizardsRebornItems.CORK_BAMBOO_CHEST_RAFT)), MobCategory.MISC).sized(1.375f, 0.5625f).build(new ResourceLocation(WizardsReborn.MOD_ID, "cork_bamboo_chest_boat").toString()));

    public static final RegistryObject<EntityType<SpellProjectileEntity>> SPELL_PROJECTILE = ENTITIES.register("spell_projectile", () -> EntityType.Builder.<SpellProjectileEntity>of(SpellProjectileEntity::new, MobCategory.MISC).sized(0.4f, 0.4f).build(new ResourceLocation(WizardsReborn.MOD_ID, "spell_projectile").toString()));
    public static final RegistryObject<EntityType<ThrowedScytheEntity>> THROWED_SCYTHE_PROJECTILE = ENTITIES.register("throwed_scythe", () -> EntityType.Builder.<ThrowedScytheEntity>of(ThrowedScytheEntity::new, MobCategory.MISC).sized(1.75f, 0.2f).build(new ResourceLocation(WizardsReborn.MOD_ID, "throwed_scythe").toString()));
    public static final RegistryObject<EntityType<SplitArrowEntity>> SPLIT_ARROW_PROJECTILE = ENTITIES.register("split_arrow", () -> EntityType.Builder.<SplitArrowEntity>of(SplitArrowEntity::new, MobCategory.MISC).sized(0.2f, 0.2f).build(new ResourceLocation(WizardsReborn.MOD_ID, "split_arrow").toString()));

    public static final RegistryObject<EntityType<SniffaloEntity>> SNIFFALO = ENTITIES.register("sniffalo", () -> EntityType.Builder.<SniffaloEntity>of(SniffaloEntity::new, MobCategory.CREATURE).sized(1.9F, 1.75F).clientTrackingRange(10).build(new ResourceLocation(WizardsReborn.MOD_ID, "sniffalo").toString()));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

    @Mod.EventBusSubscriber(modid = WizardsReborn.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void registerAttributes(EntityAttributeCreationEvent event) {
            event.put(WizardsRebornEntities.SNIFFALO.get(), SniffaloEntity.createAttributes().build());
        }
    }

    @Mod.EventBusSubscriber(modid = WizardsReborn.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientRegistryEvents {
        @SubscribeEvent
        public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            EntityRenderers.register(WizardsRebornEntities.ARCANE_WOOD_BOAT.get(), m -> new CustomBoatRenderer(m, WizardsReborn.MOD_ID, "arcane_wood", false, false));
            EntityRenderers.register(WizardsRebornEntities.ARCANE_WOOD_CHEST_BOAT.get(), m -> new CustomBoatRenderer(m, WizardsReborn.MOD_ID, "arcane_wood", true, false));
            EntityRenderers.register(WizardsRebornEntities.INNOCENT_WOOD_BOAT.get(), m -> new CustomBoatRenderer(m, WizardsReborn.MOD_ID, "arcane_wood", false, false));
            EntityRenderers.register(WizardsRebornEntities.INNOCENT_WOOD_CHEST_BOAT.get(), m -> new CustomBoatRenderer(m, WizardsReborn.MOD_ID, "arcane_wood", true, false));
            EntityRenderers.register(WizardsRebornEntities.CORK_BAMBOO_RAFT.get(), m -> new CustomBoatRenderer(m, WizardsReborn.MOD_ID, "arcane_wood", false, true));
            EntityRenderers.register(WizardsRebornEntities.CORK_BAMBOO_CHEST_RAFT.get(), m -> new CustomBoatRenderer(m, WizardsReborn.MOD_ID, "arcane_wood", true, true));
            EntityRenderers.register(WizardsRebornEntities.SPELL_PROJECTILE.get(), SpellProjectileRenderer::new);
            EntityRenderers.register(WizardsRebornEntities.THROWED_SCYTHE_PROJECTILE.get(), ThrowedScytheRenderer::new);
            EntityRenderers.register(WizardsRebornEntities.SPLIT_ARROW_PROJECTILE.get(), SplitArrowRenderer::new);
            EntityRenderers.register(WizardsRebornEntities.SNIFFALO.get(), SniffaloRenderer::new);
        }
    }
}