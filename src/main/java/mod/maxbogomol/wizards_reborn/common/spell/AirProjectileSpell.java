package mod.maxbogomol.wizards_reborn.common.spell;

import mod.maxbogomol.wizards_reborn.WizardsReborn;
import mod.maxbogomol.wizards_reborn.api.spell.Spell;

import java.awt.*;

public class AirProjectileSpell extends Spell {
    public AirProjectileSpell(String id) {
        super(id);
        addCrystalType(WizardsReborn.AIR_CRYSTAL_TYPE);
    }

    public Color getColor() {
        return new Color(230, 173, 134);
    }
}
