package soul_magic.soul_magic;

import java.util.List;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import soul_magic.soul_magic.ParticleShapes;
//TODO fix textures
//TODO make the bottles explode when dropped on the dround as well
//TODO higher levels of soul bottles
public class SoulBottle extends Item {
    public SoulBottle(Settings settings){ 
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if(playerEntity.getStackInHand(hand).hasNbt()){ 
         //somthing that drains the player's soul by taking xp
        }
        return TypedActionResult.success(playerEntity.getStackInHand(hand)); 
    }
    
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected){
       if (stack.hasNbt()){
           double fill=stack.getNbt().getDouble("fill");
           if(fill>100){
            double damage=2*(fill/10);
                if(damage>60){
                    damage=60;
                }
                if(stack.getNbt().contains("tickslefttoexplode")){
                   int tickslefttoexplode=stack.getNbt().getInt("tickslefttoexplode");
                   tickslefttoexplode-=1;
                   stack.getNbt().putInt("tickslefttoexplode", tickslefttoexplode);
                }
                else{
                    stack.getNbt().putInt("tickslefttoexplode", 100);
                }
                if(stack.getNbt().getInt("tickslefttoexplode")==0){
                    explode(world, fill, entity);
                    entity.damage(entity.getDamageSources().magic(), ((int)damage));
                    stack.decrement(1);
                }
            }
        }
    }
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        if(itemStack.hasNbt()){
            if (itemStack.getNbt().getDouble("fill")<=100){
                tooltip.add(Text.translatable("item.soul_magic.soul_bottle.tooltip", itemStack.getNbt().getDouble("fill")).formatted(Formatting.DARK_BLUE));
            }
            if (itemStack.getNbt().getDouble("fill")>100){
                tooltip.add(Text.translatable("item.soul_magic.soul_bottle.tooltip", itemStack.getNbt().getDouble("fill")).formatted(Formatting.OBFUSCATED));
            }
        }
       else{
          tooltip.add(Text.translatable("empty" ).formatted(Formatting.DARK_BLUE));
         }
    }
    private void explode(World world, double fill, Entity entity){
        double damage=fill/10;
        if(damage>30){
            damage=30;
        }
        double size=fill/20;
        if(size>20){
            size=20;
        }
        Box box=new Box(entity.getX()-size, entity.getY()-size, entity.getZ()-size, entity.getX()+size, entity.getY()+size, entity.getZ()+size);
        List<Entity> entitys=world.getOtherEntities(entity, box);
        for (int i=entitys.size()-1; i>=0; i--){
            Entity hit=entitys.get(i);
            if(hit instanceof LivingEntity){
                hit.damage(hit.getDamageSources().magic(), (int)(damage));
            }
        }
        ParticleShapes.RandomSphereShape(world, ParticleTypes.SCULK_SOUL, entity.getX(), entity.getY(), entity.getZ(), ((int)size), ((int)(size*100)), 0, true);
        ParticleShapes.RandomSphereShape(world, ParticleTypes.SOUL, entity.getX(), entity.getY(), entity.getZ(), 0.3, 500, 0.5, true);
        world.playSound(entity.getX(), entity.getY(), entity.getY(), SoundEvents.BLOCK_SOUL_SAND_BREAK, SoundCategory.BLOCKS, 5, 1, true);
    }
}
