package mod.maxbogomol.wizards_reborn.client.arcanemicon;

import com.mojang.blaze3d.matrix.MatrixStack;
import mod.maxbogomol.wizards_reborn.api.monogram.Monogram;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TitledMonogramRecipesPage extends MonogramRecipesPage {
    public String title;

    public TitledMonogramRecipesPage(String textKey, Monogram monogram) {
        super(monogram);
        this.title = textKey + ".title";
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void render(ArcanemiconGui gui, MatrixStack mStack, int x, int y, int mouseX, int mouseY) {
        gui.blit(mStack, x, y, 128, 0, 128, 20);
        String title = I18n.format(this.title);
        int titleWidth = Minecraft.getInstance().fontRenderer.getStringWidth(title);
        drawText(gui, mStack, title, x + 64 - titleWidth / 2, y + 15 - Minecraft.getInstance().fontRenderer.FONT_HEIGHT);

        Minecraft.getInstance().getTextureManager().bindTexture(BACKGROUND);
        super.render(gui, mStack, x, y + 16, mouseX, mouseY);
    }
}
