package emmathemartian.energylib.test;

import emmathemartian.energylib.api.IEnergyItem;
import emmathemartian.energylib.impl.EnergyBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.entity.TileEntity;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.Identifier;

public class BlockCreativeGenerator extends TemplateBlockWithEntity {
    public BlockCreativeGenerator(Identifier identifier, Material material) {
        super(identifier, material);
    }

    @Override
    public boolean use(Level level, int x, int y, int z, Player player) {
        if (player.getSelectedItem().getItem() instanceof IEnergyItem energyItem) {
            energyItem.mutateEnergyStorage(player.getSelectedItem(), storage -> {
                ((EnergyBlockEntity) level.getTileEntity(x, y, z)).pushEnergy(storage);
                return storage;
            });
            return true;
        }

        return false;
    }

    @Override
    protected TileEntity newTileEntity() {
        return new CreativeGeneratorBlockEntity();
    }

    public static class CreativeGeneratorBlockEntity extends EnergyBlockEntity {
        public CreativeGeneratorBlockEntity() {
            super(Integer.MAX_VALUE);
        }

        @Override
        public void tick() {
            if (!this.energy.isFull())  {
                this.energy.addEnergy(100_000_000);
            }
        }
    }
}
