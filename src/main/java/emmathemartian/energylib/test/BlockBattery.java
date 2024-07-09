package emmathemartian.energylib.test;

import emmathemartian.energylib.EnergyLibModClient;
import emmathemartian.energylib.api.IEnergyItem;
import emmathemartian.energylib.impl.EnergyBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.entity.TileEntity;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.Identifier;

public class BlockBattery extends TemplateBlockWithEntity {
    public BlockBattery(Identifier identifier, Material material) {
        super(identifier, material);
    }

    @Override
    public boolean use(Level level, int x, int y, int z, Player player) {
        if (player.getSelectedItem().getItem() instanceof IEnergyItem energyItem) {
            energyItem.mutateEnergyStorage(player.getSelectedItem(), storage -> {
                if (player.isSneaking()) {
                    ((EnergyBlockEntity) level.getTileEntity(x, y, z)).pullEnergy(storage);
                } else {
                    ((EnergyBlockEntity) level.getTileEntity(x, y, z)).pushEnergy(storage);
                }
                return storage;
            });
            return true;
        }

        return false;
    }

    @Override
    protected TileEntity newTileEntity() {
        return new BatteryBlockEntity();
    }

    @Override
    public int getTexture(int side, int meta) {
        if (meta != 0) {
            return 4;
        }

        return switch (side) {
            case 0 -> EnergyLibModClient.textureCapacitorBottom;
            case 1 -> EnergyLibModClient.textureCapacitorTop;
            case 2, 3, 4, 5 -> EnergyLibModClient.textureCapacitorSide;
            default -> 0;
        };
    }

    public static class BatteryBlockEntity extends EnergyBlockEntity {
        public BatteryBlockEntity() {
            super(10_000);
        }
    }
}
