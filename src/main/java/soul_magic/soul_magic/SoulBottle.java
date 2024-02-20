package soul_magic.soul_magic;
import java.util.List;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
//TODO fix textures by adding layers and use data generation for crafting them
//TODO make the bottles explode when dropped on the dround as well
//TODO higher levels of soul bottles via nbt
//for texture, add a pixel every 12.5 fill
public class SoulBottle extends Item {
    public int tier;
    public SoulBottle(Settings settings){ 
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack soulbottle=playerEntity.getStackInHand(hand);
            int xp=playerEntity.experienceLevel;
            double ammount=xp;
            if(this.tier==1){
                ammount=xp*0.4761904762;
            }
            if(this.tier==2){
                ammount=xp*0.07843137255;
            }
            if(this.tier==3){
                ammount=xp*0.0198019802;
            }            
            if(xp>0){ 
                playerEntity.addExperienceLevels(-1);
                if(soulbottle.hasNbt()){
                    double fill=soulbottle.getNbt().getDouble("fill");
                    if(fill+ammount<100){
                        soulbottle.getNbt().putDouble("fill", fill+ammount);
                    }
                    else{
                        soulbottle.getNbt().putDouble("fill", 100);
                    }
                }
                else{
                    NbtCompound nbt=new NbtCompound();
                    nbt.putDouble("fill", ammount);
                    soulbottle.setNbt(nbt);
                }
            }
        return TypedActionResult.success(playerEntity.getStackInHand(hand)); 
    }
    
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected){
       if (stack.hasNbt()){
           double fill=stack.getNbt().getDouble("fill");
           if(fill>100){
            double damage=2*this.tier*(fill/10);
                if(damage>60*this.tier){
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
                tooltip.add(Text.translatable("item.soul_magic.soul_bottle.tooltip", ((int)itemStack.getNbt().getDouble("fill"))).formatted(Formatting.DARK_BLUE));
            }
            if (itemStack.getNbt().getDouble("fill")>100){
                tooltip.add(Text.translatable("item.soul_magic.soul_bottle.tooltip", ((int)itemStack.getNbt().getDouble("fill"))).formatted(Formatting.OBFUSCATED));
            }
        }
       else{
          tooltip.add(Text.translatable("item.soul_magic.soul_bottle.tooltip.empty" ).formatted(Formatting.DARK_BLUE));
         }
    }
    private void explode(World world, double fill, Entity entity){
        double damage=(fill/10)*this.tier;
        if(damage>30*this.tier){
            damage=30*this.tier;
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
