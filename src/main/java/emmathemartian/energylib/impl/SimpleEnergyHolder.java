package emmathemartian.energylib.impl;

import emmathemartian.energylib.api.IMutableEnergyStorage;
import net.minecraft.nbt.CompoundTag;

public class SimpleEnergyHolder implements IMutableEnergyStorage {
    protected int energy, maxEnergy;

    public SimpleEnergyHolder(int energy, int maxEnergy) {
        this.energy = energy;
        this.maxEnergy = maxEnergy;
    }

    public SimpleEnergyHolder(int maxEnergy) {
        this(0, maxEnergy);
    }

    @Override
    public void setEnergy(int value) {
        this.energy = value;
        onChange();
    }

    @Override
    public void setMaxEnergy(int value) {
        this.maxEnergy = value;
        onChange();
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public int getMaxEnergy() {
        return maxEnergy;
    }

    public static SimpleEnergyHolder fromNbt(CompoundTag tag) {
        return new SimpleEnergyHolder(tag.getInt("Energy"), tag.getInt("MaxEnergy"));
    }
}
