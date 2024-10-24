package mod.maxbogomol.wizards_reborn.integration.common.farmersdelight;

import mod.maxbogomol.wizards_reborn.common.item.equipment.arcanewood.ArcaneWoodTools;
import mod.maxbogomol.wizards_reborn.common.item.equipment.innocentwood.InnocentWoodTools;
import mod.maxbogomol.wizards_reborn.common.network.WizardsRebornPacketHandler;
import mod.maxbogomol.wizards_reborn.common.network.item.InnocentWoodToolsPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

import java.util.Random;
import java.util.function.Consumer;

public class InnocentWoodKnifeItem extends ArcaneWoodKnifeItem {
    private static Random random = new Random();

    public InnocentWoodKnifeItem(Tier tier, float attackDamageModifier, float attackSpeedModifier, Properties properties, Item repairItem) {
        super(tier, attackDamageModifier, attackSpeedModifier, properties, repairItem);
    }

    @Override
    public ArcaneWoodTools getTools(Item repairItem) {
        return new InnocentWoodTools(repairItem);
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        if (entity != null) {
            if (!entity.level().isClientSide) {
                if (entity.getHealth() != entity.getMaxHealth() && random.nextFloat() < 0.35f + (0.15f * tools.getLifeRoots(stack))) {
                    entity.heal(1f);
                    entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), tools.getRepairSound(stack, entity.level(), entity), SoundSource.PLAYERS, 0.25f, 2f);
                    WizardsRebornPacketHandler.sendToTracking(entity.level(), entity.getOnPos(), new InnocentWoodToolsPacket((float) entity.getX(), (float) entity.getY() + (entity.getBbHeight() / 2), (float) entity.getZ()));
                }
            }
        }
        return super.damageItem(stack, amount, entity, onBroken);
    }
}
