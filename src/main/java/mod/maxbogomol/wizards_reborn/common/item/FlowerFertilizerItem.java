package mod.maxbogomol.wizards_reborn.common.item;

import mod.maxbogomol.wizards_reborn.common.network.item.FlowerFertilizerPacket;
import mod.maxbogomol.wizards_reborn.common.network.WizardsRebornPacketHandler;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.Vec3;

public class FlowerFertilizerItem extends BoneMealItem {

    public FlowerFertilizerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        InteractionResult result1 = super.useOn(context);
        InteractionResult result2 = super.useOn(context);
        if (result1 != InteractionResult.PASS || result2 != InteractionResult.PASS) {
            if (!context.getLevel().isClientSide()) {
                Vec3 pos = context.getClickedPos().getCenter();
                WizardsRebornPacketHandler.sendToTracking(context.getLevel(), context.getClickedPos(), new FlowerFertilizerPacket(pos.add(0, -0.4f, 0)));
            }
            return InteractionResult.sidedSuccess(context.getLevel().isClientSide);
        }

        return InteractionResult.PASS;
    }
}