package soul_magic.soul_magic;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class InvisEntityRender<T extends Entity> extends EntityRenderer<T>{

    public InvisEntityRender(EntityRendererFactory.Context context){
        super(context);
    }
    @Override
    public Identifier getTexture(T entity) {
        return null;
    }
    
}
