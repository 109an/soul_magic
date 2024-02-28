package soul_magic.soul_magic.spells;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import soul_magic.soul_magic.util.Aoe;
import soul_magic.soul_magic.util.ParticleShapes;

public class ShockwaveSpell {
    public LivingEntity Caster;
    public World world;
    public int MaxTime=10;
    private int count=0;
    public ShockwaveSpell(World world, LivingEntity caster){
        this.world=world;
        this.Caster=caster;
    }
    public void start(){
        ServerTickEvents.END_SERVER_TICK.register(server ->tick());
        Caster.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1f, 1f);
        for (LivingEntity hit : Aoe.getLiving(world, Caster, Caster.getX(), Caster.getY(), Caster.getZ(), 10)) {
            hit.damage(hit.getDamageSources().indirectMagic(Caster, Caster), 6);
        }
    }
    public void tick(){
      if(count > 0){
            count--;
            ParticleShapes.circleShape(world, ParticleTypes.CRIT, Caster.getX(), Caster.getY(), Caster.getZ(), count, count*100, 0, false);
        }
    }
}
