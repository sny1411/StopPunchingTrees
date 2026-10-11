package fr.sny1411.plugin;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.util.Config;
import fr.sny1411.plugin.config.StopPunchingTreesConfig;
import fr.sny1411.plugin.events.DamageBlockEventHandler;

import javax.annotation.Nonnull;

public class StopPunchingTrees extends JavaPlugin {
    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    private final Config<StopPunchingTreesConfig> config;

    public StopPunchingTrees(@Nonnull JavaPluginInit init) {
        super(init);
        this.config = this.withConfig(StopPunchingTreesConfig.CODEC);
    }

    @Override
    protected void setup() {
        this.initMessage();
        // Writes the default config.json on first start so server owners can edit it.
        this.config.save();
        this.registerEvents();
    }

    private void registerEvents() {
        getEntityStoreRegistry().registerSystem(new DamageBlockEventHandler(this.config));
    }

    private void initMessage() {
        var manifest = this.getManifest();
        LOGGER.atInfo().log("[%s] Plugin version %s loaded.", manifest.getName(), manifest.getVersion());
    }
}
