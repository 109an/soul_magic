package soul_magic.soul_magic.geckolib;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import soul_magic.soul_magic.spells.spellEntity.SpellFireball;

public class SpellFireballEntityModel extends GeoModel<SpellFireball> {
    @Override
    public Identifier getModelResource(SpellFireball animatable) {
        return new Identifier("soul_magic", "geo/spell_fireball.geo.json");
    }

    @Override
    public Identifier getTextureResource(SpellFireball animatable) {
        return new Identifier("soul_magic", "textures/entity/spell_fireball.png");
    }

    @Override
    public Identifier getAnimationResource(SpellFireball animatable) {
        return new Identifier("soul_magic", "animations/spell_fireball.animation.json");
    }
}