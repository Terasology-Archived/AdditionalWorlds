package org.terasology.additionalworlds.endworld;

import org.terasology.math.ChunkMath;
import org.terasology.math.geom.Vector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.WorldRasterizer;
import org.terasology.world.generation.facets.SurfaceHeightFacet;

public class EndWorldRasterizer implements WorldRasterizer {

    private Block dirt;
    private Block grass;
    private Block air;

    @Override
    public void initialize() {
        dirt = CoreRegistry.get(BlockManager.class).getBlock("Core:Dirt");
        grass = CoreRegistry.get(BlockManager.class).getBlock("Core:Gravel");
        air = CoreRegistry.get(BlockManager.class).getBlock(BlockManager.AIR_ID);
    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion) {
    	//get surfaceHeightFacet from the game
        SurfaceHeightFacet surfaceHeightFacet = chunkRegion.getFacet(SurfaceHeightFacet.class);
        for (Vector3i position : chunkRegion.getRegion()) {
        	//get surface y value in a certain location
            float surfaceHeight = surfaceHeightFacet.getWorld(position.x, position.z);
            if ((position.y < surfaceHeight - 1) && (position.y > 0)) {
                chunk.setBlock(ChunkMath.calcBlockPos(position), dirt);
            } else if ((position.y < surfaceHeight) && (position.y > 0)) {
                chunk.setBlock(ChunkMath.calcBlockPos(position), grass);
            } else if (position.y <0) {
            	chunk.setBlock(ChunkMath.calcBlockPos(position), air);
            }
            //standing block
            if ((position.y > -1) && (position.y < 1) && (position.x > -10) && (position.x < 10) && (position.z > -10) && (position.z < 10)) {
            	chunk.setBlock(ChunkMath.calcBlockPos(position), dirt);
            }
        }
    }
}
