package fr.sny1411.plugin.events;

import com.hypixel.hytale.component.Archetype;
import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.EntityEventSystem;
import com.hypixel.hytale.server.core.asset.type.blocktype.config.BlockBreakingDropType;
import com.hypixel.hytale.server.core.asset.type.blocktype.config.BlockGathering;
import com.hypixel.hytale.server.core.asset.type.blocktype.config.BlockType;
import com.hypixel.hytale.server.core.event.events.ecs.DamageBlockEvent;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.universe.world.SoundUtil;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.Config;
import fr.sny1411.plugin.config.StopPunchingTreesConfig;
import org.joml.Vector3i;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DamageBlockEventHandler extends EntityEventSystem<EntityStore, DamageBlockEvent> {
    private final Config<StopPunchingTreesConfig> config;

    public DamageBlockEventHandler(@Nonnull Config<StopPunchingTreesConfig> config) {
        super(DamageBlockEvent.class);
        this.config = config;
    }

    @Override
    public void handle(
            int index,
            @Nonnull ArchetypeChunk<EntityStore> archetypeChunk,
            @Nonnull Store<EntityStore> store,
            @Nonnull CommandBuffer<EntityStore> commandBuffer,
            @Nonnull DamageBlockEvent event
    ) {
        if (!this.requiresTool(event.getBlockType()) || isHoldingTool(event.getItemInHand())) {
            return;
        }

        event.setCancelled(true);
        playUnbreakableBlockSound(archetypeChunk.getReferenceTo(index), event.getTargetBlock(), commandBuffer);
    }

    private boolean requiresTool(@Nonnull BlockType blockType) {
        BlockGathering gathering = blockType.getGathering();
        BlockBreakingDropType breaking = gathering != null ? gathering.getBreaking() : null;
        String gatherType = breaking != null ? breaking.getGatherType() : null;

        return gatherType != null && this.config.get().getToolRequiredGatherTypes().contains(gatherType);
    }

    private static boolean isHoldingTool(@Nullable ItemStack itemInHand) {
        return itemInHand != null && !itemInHand.isEmpty() && itemInHand.getItem().getTool() != null;
    }

    // Same feedback as the game gives when a block cannot be broken with the held item.
    private static void playUnbreakableBlockSound(
            @Nonnull Ref<EntityStore> ref,
            @Nonnull Vector3i block,
            @Nonnull CommandBuffer<EntityStore> commandBuffer
    ) {
        World world = commandBuffer.getExternalData().getWorld();
        int soundEventIndex = world.getGameplayConfig().getGatheringConfig().getUnbreakableBlockConfig().getSoundEventIndex();
        if (soundEventIndex != 0) {
            SoundUtil.playSoundEvent3d(ref, soundEventIndex, block.x() + 0.5, block.y() + 0.5, block.z() + 0.5, commandBuffer);
        }
    }

    @Nullable
    @Override
    public Query<EntityStore> getQuery() {
        return Archetype.empty();
    }
}
