package org.terasology.lavalandgenerator;
import org.terasology.math.ChunkMath;
import org.terasology.math.geom.Vector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.WorldRasterizer;
import org.terasology.world.generation.facets.SurfaceHeightFacet;

public class TerrainRasterizer implements WorldRasterizer {
    Block ash, volcanicRock;
    
    @Override
    public void initialize() {
        ash = CoreRegistry.get(BlockManager.class).getBlock("AdditionalWorlds:Ash");
        volcanicRock = CoreRegistry.get(BlockManager.class).getBlock("AdditionalWorlds:VolcanicRock");
    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion) {
        SurfaceHeightFacet surfaceHeightFacet = chunkRegion.getFacet(SurfaceHeightFacet.class);
        for(Vector3i position : chunkRegion.getRegion()) {
            float surfaceHeight = surfaceHeightFacet.getWorld(position.x,position.z);
            if(surfaceHeight - position.y > 5) {    //Beneath Surface
                chunk.setBlock(ChunkMath.calcBlockPos(position),volcanicRock);
            }
            else if(position.y < surfaceHeight) {   //Surface
                chunk.setBlock(ChunkMath.calcBlockPos(position),ash);
            }
        }
    }

}
