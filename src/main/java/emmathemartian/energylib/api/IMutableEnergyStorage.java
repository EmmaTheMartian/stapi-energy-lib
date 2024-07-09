package emmathemartian.energylib.api;

import net.minecraft.nbt.CompoundTag;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface IMutableEnergyStorage extends IEnergyStorage {
    void setEnergy(int value);

    void setMaxEnergy(int value);

    /**
     * Add a given amount of energy. If the current energy exceeds the maximum
     * then the current energy is set to the max energy.
     * @param amount The amount of energy to add.
     * @return The amount of overflow, if any.
     */
    default int addEnergy(int amount) {
        if (isFull() || getEnergy() == Integer.MAX_VALUE) {
            return amount;
        }

        int overflow = 0;
        setEnergy(NumberUtil.addWithMax(getEnergy(), amount));
        if (getEnergy() > getMaxEnergy()) {
            overflow = getEnergy() - getMaxEnergy();
            setEnergy(getMaxEnergy());
        }

        return overflow;
    }

    /**
     * Remove a given amount of energy. If the current energy is less than zero
     * after removing energy then the current energy is set to zero.
     * @param amount The amount of energy to remove.
     * @return The amount of underflow, if any. This will always be positive.
     */
    default int removeEnergy(int amount) {
        if (getEnergy() <= 0) {
            return amount;
        }

        int underflow = 0;
        setEnergy(getEnergy() - amount);
        if (getEnergy() < 0) {
            underflow = Math.abs(getEnergy());
            setEnergy(0);
        }

        return underflow;
    }

    /**
     * Send energy to another {@link IMutableEnergyStorage}.
     * @param amount The maximum amount of energy to send.
     * @param other The destination to send energy to.
     */
    default void pushEnergy(int amount, IMutableEnergyStorage other) {
        // If the amount to send is more than what is available, send less.
        if (amount > getEnergy()) {
            amount = getEnergy();
        }

        int otherOverflow = other.addEnergy(amount);
        removeEnergy(amount);

        // If there was an overflow, add that back here.
        if (otherOverflow > 0) {
            addEnergy(otherOverflow);
        }
    }

    /**
     * Pull energy from another {@link IMutableEnergyStorage}.
     * @param amount The maximum amount of energy to pull.
     * @param other The source to pull energy from.
     */
    default void pullEnergy(int amount, IMutableEnergyStorage other) {
        other.pushEnergy(amount, this);
    }

    /**
     * Send as much energy as possible to another {@link IMutableEnergyStorage}.
     * @param other The destination to send energy to.
     */
    default void pushEnergy(IMutableEnergyStorage other) {
        pushEnergy(other.getMaxEnergy(), other);
    }

    /**
     * Pull as much energy as possible from another {@link IMutableEnergyStorage}.
     * @param other The destination to send energy to.
     */
    default void pullEnergy(IMutableEnergyStorage other) {
        other.pushEnergy(this);
    }

    /**
     * Invoked when the energy or max energy change.
     * For example, this can be used to mark an entity as changed.
     */
    default void onChange() { }

    /**
     * Update this storage with the values in the provided tag
     * @param tag The {@link CompoundTag} to load from
     */
    default void load(CompoundTag tag) {
        setEnergy(tag.getInt("Energy"));
        setMaxEnergy(tag.getInt("MaxEnergy"));
    }
}
