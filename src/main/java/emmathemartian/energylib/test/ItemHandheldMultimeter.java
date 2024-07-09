package emmathemartian.energylib.test;

import emmathemartian.energylib.api.IEnergyStorage;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.world.ItemInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class ItemHandheldMultimeter extends TemplateItem {
    public ItemHandheldMultimeter(Identifier identifier) {
        super(identifier);
    }

    @Override
    public boolean useOn(ItemInstance stack, Player user, Level world, int x, int y, int z, int direction) {
        if (world.getTileEntity(x, y, z) instanceof IEnergyStorage blockEntity) {
            ((Minecraft) FabricLoader.getInstance().getGameInstance()).gui
                    .addMessage("Energy: " + blockEntity.getEnergy() + "/" + blockEntity.getMaxEnergy());
            return true;
        }

        return false;
    }
}
