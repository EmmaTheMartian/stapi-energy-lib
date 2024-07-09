package emmathemartian.energylib.api;

import net.minecraft.nbt.CompoundTag;

@SuppressWarnings("unused")
public interface IEnergyStorage {
    int getEnergy();

    int getMaxEnergy();

    default boolean isFull() {
        return getEnergy() >= getMaxEnergy();
    }

    /**
     * @return {@link net.minecraft.nbt.CompoundTag} representation of this storage
     */
    default CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("Energy", getEnergy());
        tag.putInt("MaxEnergy", getMaxEnergy());
        return tag;
    }
}
