package emmathemartian.energylib.api;

import emmathemartian.energylib.impl.SimpleEnergyHolder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.ItemInstance;

import java.util.function.Function;

public interface IEnergyItem {
    int getMaxEnergy();

    default IMutableEnergyStorage getEnergyStorage(ItemInstance stack) {
        CompoundTag tag = stack.getStationNbt();
        if (!tag.hasKey("Energy")) {
            SimpleEnergyHolder energy = new SimpleEnergyHolder(getMaxEnergy());
            tag.putCompoundTag("Energy", energy.save());
            return energy;
        } else {
            return SimpleEnergyHolder.fromNbt(tag.getCompoundTag("Energy"));
        }
    }

    default void saveEnergyStorage(ItemInstance stack, IEnergyStorage storage) {
        stack.getStationNbt().putCompoundTag("Energy", storage.save());
    }

    default void mutateEnergyStorage(ItemInstance stack, Function<IMutableEnergyStorage, IEnergyStorage> function) {
        saveEnergyStorage(stack, function.apply(getEnergyStorage(stack)));
    }
}
