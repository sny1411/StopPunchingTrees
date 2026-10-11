package fr.sny1411.plugin;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import fr.sny1411.plugin.events.DamageBlockEventHandler;

import javax.annotation.Nonnull;

public class StopPunchingTrees extends JavaPlugin {
    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    public StopPunchingTrees(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        this.initMessage();
        this.registerEvents();
    }

    private void registerEvents() {
        getEntityStoreRegistry().registerSystem(new DamageBlockEventHandler());
    }

    private void initMessage() {
        var manifest = this.getManifest();
        LOGGER.atInfo().log("[%s] Plugin version %s loaded.", manifest.getName(), manifest.getVersion());
    }
}
