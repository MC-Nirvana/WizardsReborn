package mod.maxbogomol.wizards_reborn.common.integration.crafttweaker;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotations.ZenRegister;
import com.blamejared.crafttweaker.api.item.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.api.managers.IRecipeManager;
import com.blamejared.crafttweaker.api.recipes.IRecipeHandler;
import com.blamejared.crafttweaker.api.util.StringUtils;
import com.blamejared.crafttweaker.impl.actions.recipes.ActionAddRecipe;
import com.blamejared.crafttweaker.impl.item.MCItemStackMutable;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import mod.maxbogomol.wizards_reborn.WizardsReborn;
import mod.maxbogomol.wizards_reborn.common.data.recipes.ArcanumDustTransmutationRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import org.openzen.zencode.java.ZenCodeType;

import java.util.StringJoiner;

@Document("mods/WizardsReborn/ArcanumDustTransmutation")
@ZenRegister
@IRecipeHandler.For(ArcanumDustTransmutationRecipe.class)
@ZenCodeType.Name("mods.wizards_reborn.ArcanumDustTransmutation")
public class ArcanumDustTransmutationRecipeManager implements IRecipeManager, IRecipeHandler<ArcanumDustTransmutationRecipe> {

    @ZenCodeType.Method
    public void addRecipe(String name, IIngredient input, IItemStack output, boolean place_block) {
        name = fixRecipeName(name);
        ResourceLocation resourceLocation = new ResourceLocation("crafttweaker", name);

        CraftTweakerAPI.apply(new ActionAddRecipe(this,
                new ArcanumDustTransmutationRecipe(resourceLocation, input.asVanillaIngredient(), output.getImmutableInternal(), place_block), ""));
    }

    @Override
    public IRecipeType<ArcanumDustTransmutationRecipe> getRecipeType() {
        return WizardsReborn.ARCANUM_DUST_TRANSMUTATION_RECIPE;
    }

    @Override
    public String dumpToCommandString(IRecipeManager manager, ArcanumDustTransmutationRecipe recipe) {
        StringJoiner s = new StringJoiner(", ", manager.getCommandString() + ".addRecipe(", ");");

        s.add(StringUtils.quoteAndEscape(recipe.getId()));
        s.add(IIngredient.fromIngredient(recipe.getIngredientRecipe()).getCommandString());
        s.add(new MCItemStackMutable(recipe.getRecipeOutput()).getCommandString());
        s.add(String.valueOf(recipe.getRecipePlaceBlock()));
        return s.toString();
    }
}