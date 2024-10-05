package mod.maxbogomol.wizards_reborn.common.spell.ray;

import mod.maxbogomol.wizards_reborn.registry.common.WizardsRebornCrystals;
import mod.maxbogomol.wizards_reborn.registry.common.WizardsRebornSpells;

import java.awt.*;

public class FrostRaySpell extends RaySpell {
    public FrostRaySpell(String id, int points) {
        super(id, points);
        addCrystalType(WizardsRebornCrystals.WATER);
    }

    @Override
    public Color getColor() {
        return WizardsRebornSpells.frostSpellColor;
    }
/*
    @Override
    public void onImpact(HitResult ray, Level level, SpellProjectileEntity projectile, Player player, Entity target) {
        super.onImpact(ray, level, projectile, player, target);

        if (player != null) {
            if (target.tickCount % 10 == 0) {
                ItemStack stack = player.getItemInHand(player.getUsedItemHand());
                if (WissenItemUtil.canRemoveWissen(stack, getWissenCostWithStat(projectile.getStats(), player))) {
                    removeWissen(stack, projectile.getStats(), player);
                    int focusLevel = CrystalUtil.getStatLevel(projectile.getStats(), WizardsRebornCrystals.FOCUS);
                    float magicModifier = ArcaneArmorItem.getPlayerMagicModifier(player);
                    float damage = (float) (1.5f + (focusLevel * 0.5)) + magicModifier;

                    target.clearFire();
                    int frost = target.getTicksFrozen() + 75;
                    if (frost > 250) frost = 250;
                    target.setTicksFrozen(frost);

                    target.hurt(new DamageSource(target.damageSources().freeze().typeHolder(), projectile, player), damage);

                    Color color = getColor();
                    float r = color.getRed() / 255f;
                    float g = color.getGreen() / 255f;
                    float b = color.getBlue() / 255f;

                    PacketHandler.sendToTracking(level, player.getOnPos(), new FrostRaySpellEffectPacket((float) target.getX(), (float) target.getY() + (target.getBbHeight() / 2), (float) target.getZ(), r, g, b));
                }
            }
        }
    }

    @Override
    public void onImpact(HitResult ray, Level level, SpellProjectileEntity projectile, Player player) {
        super.onImpact(ray, level, projectile, player);

        if (player != null) {
            if (player.isShiftKeyDown()) {
                int focusLevel = CrystalUtil.getStatLevel(projectile.getStats(), WizardsRebornCrystals.FOCUS);
                ItemStack stack = player.getItemInHand(player.getUsedItemHand());
                if (projectile.tickCount % (4 - focusLevel) == 0) {
                    if (WissenItemUtil.canRemoveWissen(stack, 1)) {
                        Vec3 vec = getBlockHitOffset(ray, projectile, 0.1f);
                        BlockPos blockPos = BlockPos.containing(vec.x(), vec.y(), vec.z());
                        BlockState blockState = level.getBlockState(blockPos);
                        BlockState blockStateIce = Blocks.FROSTED_ICE.defaultBlockState();

                        BlockEvent.EntityPlaceEvent placeEv = new BlockEvent.EntityPlaceEvent(
                                BlockSnapshot.create(level.dimension(), level, blockPos),
                                blockStateIce,
                                player
                        );

                        if (blockState == FrostedIceBlock.meltsInto() && blockStateIce.canSurvive(level, blockPos) && level.isUnobstructed(blockStateIce, blockPos, CollisionContext.empty()) && !net.minecraftforge.event.ForgeEventFactory.onBlockPlace(player, net.minecraftforge.common.util.BlockSnapshot.create(level.dimension(), level, blockPos), net.minecraft.core.Direction.UP)  && !MinecraftForge.EVENT_BUS.post(placeEv)) {
                            level.setBlockAndUpdate(blockPos, blockStateIce);
                            level.scheduleTick(blockPos, Blocks.FROSTED_ICE, Mth.nextInt(player.getRandom(), 300, 600));

                            WissenItemUtil.removeWissen(stack, 1);

                            Color color = getColor();
                            float r = color.getRed() / 255f;
                            float g = color.getGreen() / 255f;
                            float b = color.getBlue() / 255f;

                            PacketHandler.sendToTracking(level, player.getOnPos(), new FrostRaySpellEffectPacket((float) blockPos.getX() + 0.5f, (float) blockPos.getY() + 0.5f, (float) blockPos.getZ() + 0.5f, r, g, b));
                        }
                    }
                }
            }
        }
    }

    @Override
    public HitResult getHitResult(SpellProjectileEntity pProjectile, Vec3 pStartVec, Vec3 pEndVecOffset, Level pLevel, Predicate<Entity> pFilter) {
        if (pProjectile.getSender() != null) {
            if (pProjectile.getSender().isShiftKeyDown()) {
                Vec3 vec3 = pStartVec.add(pEndVecOffset);
                HitResult hitresult = pLevel.clip(new ClipContext(pStartVec, vec3, ClipContext.Block.COLLIDER, ClipContext.Fluid.WATER, pProjectile));
                if (hitresult.getType() != HitResult.Type.MISS) {
                    vec3 = hitresult.getLocation();
                }

                HitResult hitresult1 = ProjectileUtil.getEntityHitResult(pLevel, pProjectile, pStartVec, vec3, pProjectile.getBoundingBox().expandTowards(pEndVecOffset).inflate(1.0D), pFilter);
                if (hitresult1 != null) {
                    hitresult = hitresult1;
                }

                return hitresult;
            }
        }

        return getHitResultStandard(pProjectile, pStartVec, pEndVecOffset, pLevel, pFilter);
    }*/
}