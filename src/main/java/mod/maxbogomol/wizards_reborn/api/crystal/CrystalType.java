package mod.maxbogomol.wizards_reborn.api.crystal;

import mod.maxbogomol.wizards_reborn.WizardsReborn;
import mod.maxbogomol.wizards_reborn.registry.common.WizardsRebornCrystals;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.awt.*;
import java.util.ArrayList;

public class CrystalType {
    public ArrayList<CrystalStat> stats = new ArrayList<CrystalStat>();
    public String id;

    public CrystalType(String id) {
        this.id = id;

        addStat(WizardsRebornCrystals.FOCUS);
        addStat(WizardsRebornCrystals.BALANCE);
        addStat(WizardsRebornCrystals.ABSORPTION);
        addStat(WizardsRebornCrystals.RESONANCE);
    }

    public Color getColor() {
        return new Color(255, 255, 255);
    }

    public ResourceLocation getMiniIcon() {
        return new ResourceLocation(WizardsReborn.MOD_ID, "textures/gui/spell/empty_mini_icon.png");
    }

    public ResourceLocation getIcon() {
        return new ResourceLocation(WizardsReborn.MOD_ID, "textures/gui/spell/empty_icon.png");
    }

    public ItemStack getFracturedCrystal() {
        return ItemStack.EMPTY;
    }

    public ItemStack getCrystal() {
        return ItemStack.EMPTY;
    }

    public void addStat(CrystalStat stat) {
        stats.add(stat);
    }

    public ArrayList<CrystalStat> getStats() {
        return stats;
    }

    public String getId() {
        return id;
    }

    public String getTranslatedName() {
        return getTranslatedName(id);
    }

    public static String getTranslatedName(String id) {
        int i = id.indexOf(":");
        String modId = id.substring(0, i);
        String crystalTypeId = id.substring(i + 1);
        return "crystal_type."  + modId + "." + crystalTypeId;
    }
}