package mod.maxbogomol.wizards_reborn.common.spell;

import mod.maxbogomol.wizards_reborn.WizardsReborn;
import mod.maxbogomol.wizards_reborn.common.entity.SpellProjectileEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

import java.awt.*;

public class FrostProjectileSpell extends ProjectileSpell {
    public FrostProjectileSpell(String id) {
        super(id);
        addCrystalType(WizardsReborn.WATER_CRYSTAL_TYPE);
    }

    @Override
    public Color getColor() {
        return new Color(221, 243, 254);
    }

    @Override
    public void onImpact(HitResult ray, Level world, SpellProjectileEntity projectile, Player player, Entity target) {
        super.onImpact(ray, world, projectile, player, target);

        target.hurt(new DamageSource(projectile.damageSources().freeze().typeHolder(), projectile, player), 5.0f);
        target.clearFire();
        int frost = target.getTicksFrozen() + 75;
        if (frost > 250) {
            frost = 250;
        }
        target.setTicksFrozen(frost);
    }
}