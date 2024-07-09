package emmathemartian.energylib.impl;

import emmathemartian.energylib.api.IMutableEnergyStorage;
import net.minecraft.nbt.CompoundTag;

/**
 * An {@link IMutableEnergyStorage} which changes the NBT compound of something
 * directly. Note that this means there may be significant performance drawbacks.
 * @param compound The {@link net.minecraft.nbt.CompoundTag} to use.
 */
@SuppressWarnings("unused")
public record NbtEnergyHolder(CompoundTag compound) implements IMutableEnergyStorage {
    @Override
    public void setEnergy(int value) {
        compound.putInt("Energy", value);
        onChange();
    }

    @Override
    public void setMaxEnergy(int value) {
        compound.putInt("MaxEnergy", value);
        onChange();
    }

    @Override
    public int getEnergy() {
        return compound.getInt("Energy");
    }

    @Override
    public int getMaxEnergy() {
        return compound.getInt("MaxEnergy");
    }
}
