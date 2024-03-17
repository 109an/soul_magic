package soul_magic.soul_magic.geckolib;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import soul_magic.soul_magic.spells.spellEntity.SpellFireball;

public class SpellFireballEntityRender extends GeoEntityRenderer<SpellFireball> {
    public SpellFireballEntityRender(EntityRendererFactory.Context context) {
        super(context, new SpellFireballEntityModel());
    }
}