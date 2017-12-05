package org.terasology.desertworld;

import org.terasology.math.ChunkMath;
import org.terasology.math.geom.Vector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.WorldRasterizer;

public class CactusRasterizer implements WorldRasterizer {
    Block cactus;

    @Override
    public void initialize() {
        cactus = CoreRegistry.get(BlockManager.class).getBlock("Core:Cactus");
    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion) {
        CactusFacet cactusFacet = chunkRegion.getFacet(CactusFacet.class);

        for (Vector3i block : cactusFacet.getWorldRegion()) {
            if (cactusFacet.getWorld(block)) {
                chunk.setBlock(ChunkMath.calcBlockPos(block), cactus);
                chunk.setBlock(ChunkMath.calcBlockPos(block.add(0, 1, 0)), cactus);
                chunk.setBlock(ChunkMath.calcBlockPos(block.add(0, 1, 0)), cactus);
            }
        }
    }
}