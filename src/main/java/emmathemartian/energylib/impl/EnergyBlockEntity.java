package emmathemartian.energylib.impl;

import emmathemartian.energylib.api.IMutableEnergyStorage;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.tile.entity.TileEntity;

public abstract class EnergyBlockEntity extends TileEntity implements IMutableEnergyStorage {
    protected final IMutableEnergyStorage energy;

    public EnergyBlockEntity(int maxEnergy) {
        energy = new SimpleEnergyHolder(maxEnergy) {
            @Override
            public void onChange() {
                EnergyBlockEntity.this.setChanged();
            }
        };
    }

    @Override
    public void save(CompoundTag tag) {
        super.save(tag);
        tag.putCompoundTag("Energy", energy.save());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        energy.load(tag.getCompoundTag("Energy"));
    }

    @Override
    public final void setEnergy(int value) {
        energy.setEnergy(value);
    }

    @Override
    public final void setMaxEnergy(int value) {
        energy.setMaxEnergy(value);
    }

    @Override
    public final int getEnergy() {
        return energy.getEnergy();
    }

    @Override
    public final int getMaxEnergy() {
        return energy.getMaxEnergy();
    }
}
