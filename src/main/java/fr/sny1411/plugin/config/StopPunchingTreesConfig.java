package fr.sny1411.plugin.config;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Set;

public class StopPunchingTreesConfig {
    public static final BuilderCodec<StopPunchingTreesConfig> CODEC = BuilderCodec.builder(StopPunchingTreesConfig.class, StopPunchingTreesConfig::new)
            .append(
                    new KeyedCodec<>("ToolRequiredGatherTypes", Codec.STRING_ARRAY),
                    (config, gatherTypes) -> config.toolRequiredGatherTypes = Set.copyOf(Arrays.asList(gatherTypes)),
                    config -> config.toolRequiredGatherTypes.toArray(String[]::new)
            )
            .documentation("Gather types of the blocks that cannot be broken without a tool, e.g. Woods, Rocks or OreIron.")
            .add()
            .build();

    private Set<String> toolRequiredGatherTypes = Set.of("Woods");

    private StopPunchingTreesConfig() {
    }

    @Nonnull
    public Set<String> getToolRequiredGatherTypes() {
        return toolRequiredGatherTypes;
    }
}
