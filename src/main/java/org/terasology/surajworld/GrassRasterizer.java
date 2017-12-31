
package org.terasology.surajworld;

import org.terasology.math.ChunkMath;
import org.terasology.math.geom.Vector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.WorldRasterizer;

public class GrassRasterizer implements WorldRasterizer {
    Block tallgrass;

    @Override
    public void initialize() {
        tallgrass = CoreRegistry.get(BlockManager.class).getBlock("Core:TallGrass3");
    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion) {
        BushFacet BushFacet = chunkRegion.getFacet(BushFacet.class);

        for(Vector3i block : BushFacet.getWorldRegion()) if(BushFacet.getWorld(block)) {
            chunk.setBlock(ChunkMath.calcBlockPos(block.add(0,1,0)), tallgrass);
        }
    }
}
