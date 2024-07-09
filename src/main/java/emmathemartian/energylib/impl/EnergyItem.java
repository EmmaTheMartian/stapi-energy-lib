package emmathemartian.energylib.impl;

import emmathemartian.energylib.api.IEnergyItem;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class EnergyItem extends TemplateItem implements IEnergyItem {
    private final int maxEnergy;

    public EnergyItem(Identifier identifier, int maxEnergy) {
        super(identifier);
        this.maxEnergy = maxEnergy;
    }

    @Override
    public int getMaxEnergy() {
        return maxEnergy;
    }
}
