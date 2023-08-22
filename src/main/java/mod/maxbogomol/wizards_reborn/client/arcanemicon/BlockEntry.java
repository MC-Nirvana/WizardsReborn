package mod.maxbogomol.wizards_reborn.client.arcanemicon;

import com.mojang.blaze3d.vertex.PoseStack;
import mod.maxbogomol.wizards_reborn.client.event.ClientTickHandler;
import mod.maxbogomol.wizards_reborn.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;

public class BlockEntry {
    public ItemStack block;
    public ItemStack item;

    public BlockEntry(ItemStack block, ItemStack item) {
        this.block = block;
        this.item = item;
    }

    public BlockEntry(ItemStack block) {
        this.block = block;
        this.item = ItemStack.EMPTY;
    }

    public BlockEntry() {
        this.block = ItemStack.EMPTY;
        this.item = ItemStack.EMPTY;
    }

    public void render(GuiGraphics gui, int x, int y, int mouseX, int mouseY) {
        gui.renderItemDecorations(Minecraft.getInstance().font, block, x, y, null);

        float partialTicks = Minecraft.getInstance().getFrameTime();
        double ticks = (ClientTickHandler.ticksInGame + partialTicks) * 2;
        double ticksUp = (ClientTickHandler.ticksInGame + partialTicks) * 4;
        ticksUp = (ticksUp) % 360;

        RenderUtils.renderFloatingItemModelIntoGUI(item, x, y - 12, (float) ticks, (float) ticksUp);
    }

    public void drawTooltip(ArcanemiconGui gui, int x, int y, int mouseX, int mouseY) {
        boolean hover = mouseX >= x && mouseY >= y && mouseX <= x + 16 && mouseY <= y + 16;
        if (hover) gui.currentItem = block;

        hover = mouseX >= x && mouseY >= y - 16 && mouseX <= x + 16 && mouseY < y;
        if (hover) gui.currentItem = item;
    }
}