package io.github.jodlodi;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.apache.commons.lang3.tuple.Pair;

@EventBusSubscriber(modid = DragonFightConfig.MOD_ID)
public class Config {
    public static final Common CONFIG;
    public static final ForgeConfigSpec SPEC;

    static {
        final Pair<Common, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(Common::new);
        CONFIG = pair.getLeft();
        SPEC = pair.getRight();
    }

    public static boolean naturalState;
    public static boolean dragonState;
    public static boolean portalState;
    public static boolean portalOpen;
    public static boolean gatewayState;
    public static boolean spikeState;
    public static boolean eggState;

    public static class Common{
        public final ConfigValue<Boolean> finalNaturalState;
        public final ConfigValue<Boolean> finalDragonState;
        public final ConfigValue<Boolean> finalPortalState;
        public final ConfigValue<Boolean> finalPortalOpen;
        public final ConfigValue<Boolean> finalGatewayState;
        public final ConfigValue<Boolean> finalSpikeState;
        public final ConfigValue<Boolean> finalEggState;

        Common(ForgeConfigSpec.Builder builder){
            builder.comment("Should the Ender Dragon naturally spawn on world generation?" +
                    "\nIf you enter the word \"true\", the Ender Dragon will spawn on her own when the End is first generated." +
                    "\nIf you enter the word \"false\", the Ender Dragon will only appear if summoned.");
            finalNaturalState = builder.define("Should the Ender Dragon naturally spawn?", true);
            builder.comment("\nShould it be possible to summon the Ender Dragon with the use of End Crystals?" +
                    "\nIf you enter the word \"true\", End Crystals will behave just like in vanilla, allowing the player to summon the Dragon." +
                    "\nIf you enter the word \"false\", the Ender Dragon will only be summonable via console commands.");
            finalDragonState = builder.define("Should it be possible to summon the Ender Dragon?", true);
            builder.comment("\nShould the exit portal generate?" +
                    "\nIf you enter the word \"true\", an exit portal will generate." +
                    "\nIf you enter the word \"false\", no exit portal will generate at all." +
                    "\nIf you select \"false\", it will be impossible to summon the Ender Dragon" +
                    "\nand it will be impossible to leave the End without dying (in vanilla)");
            finalPortalState = builder.define("Should the exit portal generate?", true);
            builder.comment("\nIf you selected \"true\" for generating an exit, should the exit be open?" +
                    "\nIf you enter the word \"true\", an open exit portal will generate." +
                    "\nIf you enter the word \"false\", an empty exit portal will generate." +
                    "\nIf you select \"false\", it will be impossible to leave the End alive until the player summons and defeats the Ender Dragon");
            finalPortalOpen = builder.define("Should the exit portal be open?", false);
            builder.comment("\nShould a Gateway be generated on world generation?" +
                    "\nIf you enter the word \"true\", a Gateway will be generated." +
                    "\nThis will allow players to leave the central island easily." +
                    "\nIf you enter the word \"false\", there will be no pre-generated Gateways." +
                    "\nThis way the players will have to summon and kill an Ender Dragon so that a Gateway appears like it would in vanilla");
            finalGatewayState = builder.define("Should a Gateway be generated?", false);
            builder.comment("\nShould End Spikes generate on world generation? (they will still appear when you summon the dragon)" +
                    "\nIf you enter the word \"true\", End Spikes will generate." +
                    "\nIf you enter the word \"false\", there will be no pre-generated End Spikes.");
            finalSpikeState = builder.define("Should End Spikes generate on world generation?", true);
            builder.comment("\nShould a Dragon egg spawn on each and every Dragon death?" +
                    "\nIf you enter the word \"true\", a Dragon egg will spawn each time a Dragon is killed" +
                    "\nIf you enter the word \"false\", a Dragon egg will only spawn for the first time that a Dragon is killed.");
            finalEggState = builder.define("Should a Dragon egg spawn on every Dragon death?", false);
        }
    }

    public static void refresh() {
        naturalState = CONFIG.finalNaturalState.get();
        dragonState = CONFIG.finalDragonState.get();
        portalState = CONFIG.finalPortalState.get();
        portalOpen = CONFIG.finalPortalOpen.get();
        gatewayState = CONFIG.finalGatewayState.get();
        spikeState = CONFIG.finalSpikeState.get();
        eggState = CONFIG.finalEggState.get();
    }
}
