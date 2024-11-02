package mod.maxbogomol.wizards_reborn.common.spell.sound;

import mod.maxbogomol.wizards_reborn.api.spell.SpellContext;
import mod.maxbogomol.wizards_reborn.registry.common.WizardsRebornSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;

public class PipeSoundSpell extends SecretSoundSpell {

    public PipeSoundSpell(String id, int points) {
        super(id, points);
    }

    @Override
    public SoundEvent getSound(Level level, SpellContext spellContext) {
        return WizardsRebornSounds.PIPE.get();
    }
}
