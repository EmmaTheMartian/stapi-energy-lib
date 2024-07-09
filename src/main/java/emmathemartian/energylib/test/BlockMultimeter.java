package emmathemartian.energylib.test;

import emmathemartian.energylib.api.IEnergyItem;
import emmathemartian.energylib.api.IMutableEnergyStorage;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class BlockMultimeter extends TemplateBlock {
    public BlockMultimeter(Identifier identifier, Material material) {
        super(identifier, material);
    }

    @Override
    public boolean use(Level level, int x, int y, int z, Player player) {
        if (player.getSelectedItem().getItem() instanceof IEnergyItem energyItem) {
            IMutableEnergyStorage energy = energyItem.getEnergyStorage(player.getSelectedItem());
            ((Minecraft) FabricLoader.getInstance().getGameInstance()).gui
                    .addMessage("Energy: " + energy.getEnergy() + "/" + energy.getMaxEnergy());
            return true;
        }

        return false;
    }
}
