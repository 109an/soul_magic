package soul_magic.soul_magic;
/*package soul_magic.arcana.spells.aoe_spells;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

import java.util.*;

import soul_magic.arcana.Aoe;
import soul_magic.arcana.ParticleShapes;
import soul_magic.arcana.Spellticker;
public class RepulsionSpell extends Spellticker
{
  World World;
  LivingEntity Caster;
  int Size;
  double CenterX;
  double CenterY;
  double CenterZ;
  public RepulsionSpell(World world, LivingEntity caster, int castime, int duration, int rate, int n, int size, double centerX, double centerY, double centerZ)
  {
    super(world, caster, castime, duration, rate, 0);
    this.Caster=caster;
    this.Size=size;
    this.World=world;
    this.CenterX=centerX;
    this.CenterY=centerY;
    this.CenterZ=centerZ;
  }
 @Override
 public void OnTick()
 {
  List<LivingEntity> inaoe=Aoe.GetLiving(World, Caster, Size, CenterX, CenterY, CenterZ);
    for(int i=inaoe.size()-1; i>=0; i--)
  {
    LivingEntity hit=inaoe.get(i);
    hit.setVelocity(-1/((CenterX-hit.getX())+1/(Size/2)), 0, -1/((CenterZ-hit.getZ())+1/(Size/2)));
 }
}
 
}*/
