package mod.maxbogomol.wizards_reborn.common.item.equipment.curio;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import mod.maxbogomol.wizards_reborn.api.wissen.WissenItemType;
import mod.maxbogomol.wizards_reborn.api.wissen.WissenItemUtil;
import mod.maxbogomol.wizards_reborn.api.wissen.WissenUtil;
import mod.maxbogomol.wizards_reborn.registry.common.WizardsRebornAttributes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import java.util.UUID;

public class ArcaciteRingItem extends BaseWissenCurioItem {

    public ArcaciteRingItem(Properties properties) {
        super(properties);
    }

    @Nonnull
    @Override
    public ICurio.SoundInfo getEquipSound(SlotContext slotContext, ItemStack stack) {
        return new ICurio.SoundInfo(SoundEvents.ARMOR_EQUIP_GOLD, 1.0f, 1.0f);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> atts = LinkedHashMultimap.create();
        atts.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "bonus", 1, AttributeModifier.Operation.ADDITION));
        atts.put(WizardsRebornAttributes.WISSEN_DISCOUNT.get(), new AttributeModifier(uuid, "bonus", 1, AttributeModifier.Operation.ADDITION));
        return atts;
    }

    @Override
    public int getMaxWissen() {
        return 1000;
    }

    @Override
    public WissenItemType getWissenItemType() {
        return WissenItemType.USING;
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        WissenItemUtil.existWissen(stack);
        return stack;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (!slotContext.entity().level().isClientSide()) {
            WissenItemUtil.existWissen(stack);

            if (slotContext.entity() instanceof Player player) {
                if (slotContext.entity().getHealth() < slotContext.entity().getMaxHealth()) {
                    if (slotContext.entity().tickCount % 120 == 0) {
                        float costModifier = WissenUtil.getWissenCostModifierWithDiscount(player);
                        int cost = (int) (12 * costModifier);
                        if (cost <= 0) {
                            cost = 1;
                        }
                        if (WissenItemUtil.canRemoveWissen(stack, cost)) {
                            slotContext.entity().heal(0.5F);
                            WissenItemUtil.removeWissen(stack, cost);
                        }
                    }
                }
            }
        }
    }
}
