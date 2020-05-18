package org.terasology.additionalworlds.desertworld;

import org.terasology.math.ChunkMath;
import org.terasology.math.geom.Vector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.WorldRasterizer;

public class DeadBushRasterizer implements WorldRasterizer {
    private Block deadBush;

    @Override
    public void initialize() {
        deadBush = CoreRegistry.get(BlockManager.class).getBlock("CoreAssets:DeadBush");
    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion) {
        DeadBushFacet deadBushFacet = chunkRegion.getFacet(DeadBushFacet.class);

        for (Vector3i block : deadBushFacet.getWorldRegion()) {
            if (deadBushFacet.getWorld(block)
                // Don't want bushes in water, now do we?
                //Surely there's a more generic way we can make sure not to place a bush on uninhabitable blocks... -Q
                && !chunk.getBlock(ChunkMath.calcBlockPos(block.addY(-1))).getURI().toString().toLowerCase().equals("coreassets:water")) {
                block.addY(1);
                chunk.setBlock(ChunkMath.calcBlockPos(block), deadBush);
            }
        }
    }
}