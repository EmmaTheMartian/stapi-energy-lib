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
import net.modificationstation.stationapi.api.event.block.entity.BlockEntityRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class EnergyLibMod {
    @Entrypoint.Namespace
    public static final Namespace NAMESPACE = Null.get();

    public static boolean registerTestContent = FabricLoader.getInstance().isDevelopmentEnvironment();

    public static Item itemBattery, itemMultimeter;
    public static Tile blockCapacitor, blockCreativeGenerator, blockMultimeter;

    @SuppressWarnings("unused")
    @EventListener
    private static void registerItems(ItemRegistryEvent event) {
        itemMultimeter = new ItemHandheldMultimeter(NAMESPACE.id("handheld_multimeter")).setTranslationKey(NAMESPACE, "handheld_multimeter").stacksTo(1);

        if (!registerTestContent) {
            return;
        }

        itemBattery = new EnergyItem(NAMESPACE.id("battery"), 1_000).setTranslationKey(NAMESPACE, "battery").stacksTo(1);
    }

    @SuppressWarnings("unused")
    @EventListener
    private static void registerBlocks(BlockRegistryEvent event) {
        if (!registerTestContent) {
            return;
        }

        blockCapacitor = new BlockBattery(NAMESPACE.id("capacitor"), Material.METAL).setTranslationKey(NAMESPACE, "capacitor");
        blockCreativeGenerator = new BlockCreativeGenerator(NAMESPACE.id("creative_generator"), Material.METAL).setTranslationKey(NAMESPACE, "creative_generator");
        blockMultimeter = new BlockMultimeter(NAMESPACE.id("multimeter"), Material.METAL).setTranslationKey(NAMESPACE, "multimeter");
    }

    @SuppressWarnings("unused")
    @EventListener
    private static void registerBlockEntities(BlockEntityRegisterEvent event) {
        event.register(BlockBattery.BatteryBlockEntity.class, "energylib:battery_block_entity");
        event.register(BlockCreativeGenerator.CreativeGeneratorBlockEntity.class, "energylib:creative_generator_block_entity");
    }
}
