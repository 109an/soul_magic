package soul_magic.soul_magic.geckolib;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import soul_magic.soul_magic.Items.MagicSwordItem;

public class MagicSwordItemModel extends GeoModel<MagicSwordItem> {
    @Override
    public Identifier getModelResource(MagicSwordItem animatable) {
        return new Identifier("soul_magic", "geo/magic_sword_item.geo.json");
    }

    @Override
    public Identifier getTextureResource(MagicSwordItem animatable) {
        return new Identifier("soul_magic", "textures/item/magic_sword_item.png");
    }

    @Override
    public Identifier getAnimationResource(MagicSwordItem animatable) {
        return new Identifier("soul_magic", "animations/magic_sword_item.animation.json");
    }
}