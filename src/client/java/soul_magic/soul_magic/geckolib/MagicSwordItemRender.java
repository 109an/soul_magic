package soul_magic.soul_magic.geckolib;

import software.bernie.geckolib.renderer.GeoItemRenderer;
import soul_magic.soul_magic.Items.MagicSwordItem;

public class MagicSwordItemRender extends GeoItemRenderer<MagicSwordItem> {
    public MagicSwordItemRender() {
        super(new MagicSwordItemModel());
    }
}