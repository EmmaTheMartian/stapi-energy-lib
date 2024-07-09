package emmathemartian.energylib;

import emmathemartian.energylib.impl.EnergyItem;
import emmathemartian.energylib.test.BlockBattery;
import emmathemartian.energylib.test.BlockCreativeGenerator;
import emmathemartian.energylib.test.BlockMultimeter;
import emmathemartian.energylib.test.ItemHandheldMultimeter;
import net.fabricmc.loader.api.FabricLoader;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.tile.Tile;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.client.texture.atlas.ExpandableAtlas;
import net.modificationstation.stationapi.api.event.block.entity.BlockEntityRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

import static emmathemartian.energylib.EnergyLibMod.NAMESPACE;

public class EnergyLibModClient {
    public static int
            textureCapacitorTop,
            textureCapacitorBottom,
            textureCapacitorSide;

    @SuppressWarnings("unused")
    @EventListener
    private static void registerTextures(TextureRegisterEvent event) {
        ExpandableAtlas atlas = Atlases.getTerrain();

        textureCapacitorTop = atlas.addTexture(NAMESPACE.id("block/capacitor_top")).index;
        textureCapacitorBottom = atlas.addTexture(NAMESPACE.id("block/capacitor_bottom")).index;
        textureCapacitorSide = atlas.addTexture(NAMESPACE.id("block/capacitor_side")).index;
    }
}
