package mod.maxbogomol.wizards_reborn.common.network.spell;

import mod.maxbogomol.wizards_reborn.WizardsReborn;
import mod.maxbogomol.wizards_reborn.client.particle.Particles;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.Random;
import java.util.function.Supplier;

public class IrritationSpellEffectPacket {
    private final float X, Y, Z;
    private final float colorR, colorG, colorB;

    private static final Random random = new Random();

    public IrritationSpellEffectPacket(float X, float Y, float Z, float colorR, float colorG, float colorB) {
        this.X = X;
        this.Y = Y;
        this.Z = Z;

        this.colorR = colorR;
        this.colorG = colorG;
        this.colorB = colorB;
    }

    public static IrritationSpellEffectPacket decode(FriendlyByteBuf buf) {
        return new IrritationSpellEffectPacket(buf.readFloat(), buf.readFloat(), buf.readFloat(), buf.readFloat(), buf.readFloat(), buf.readFloat());
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeFloat(X);
        buf.writeFloat(Y);
        buf.writeFloat(Z);

        buf.writeFloat(colorR);
        buf.writeFloat(colorG);
        buf.writeFloat(colorB);
    }

    public static void handle(IrritationSpellEffectPacket msg, Supplier<NetworkEvent.Context> ctx) {
        if (ctx.get().getDirection().getReceptionSide().isClient()) {
            ctx.get().enqueueWork(new Runnable() {
                @Override
                public void run() {
                    Level world = WizardsReborn.proxy.getWorld();


                    for (int i = 0; i < 10; i++) {
                        if (random.nextFloat() < 0.4f) {
                            Particles.create(WizardsReborn.WISP_PARTICLE)
                                    .addVelocity(((random.nextDouble() - 0.5D) / 10), ((random.nextDouble() - 0.5D) / 10), ((random.nextDouble() - 0.5D) / 10))
                                    .setAlpha(0.3f, 0).setScale(0, 0.3f)
                                    .setColor(msg.colorR, msg.colorG, msg.colorB)
                                    .setLifetime(60)
                                    .spawn(world, msg.X, msg.Y, msg.Z);
                        }
                    }

                    for (int i = 0; i < 22; i++) {
                        if (random.nextFloat() < 0.6f) {
                            world.addParticle(ParticleTypes.ENTITY_EFFECT,
                                    msg.X + ((random.nextDouble() - 0.5D) / 5), msg.Y + ((random.nextDouble() - 0.5D) / 5), msg.Z + ((random.nextDouble() - 0.5D) / 5),
                                    msg.colorR, msg.colorG, msg.colorB);
                        }
                    }

                    ctx.get().setPacketHandled(true);
                }
            });
        }
    }
}