package org.terasology.MyWorld;

import org.terasology.math.ChunkMath;
import org.terasology.math.geom.Vector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.WorldRasterizer;

         public class GrassRasterizer implements WorldRasterizer {
     Block TallGrass2;

             @Override
     public void initialize() {
                TallGrass2 = CoreRegistry.get(BlockManager.class).getBlock("Core:TallGrass2");
            }

             @Override
     public void generateChunk(CoreChunk chunk, Region chunkRegion) {
                GrassFacet GrassFacet = chunkRegion.getFacet(GrassFacet.class);
                for(Vector3i block : GrassFacet.getWorldRegion()) if(GrassFacet.getWorld(block))  {
                        chunk.setBlock(ChunkMath.calcBlockPos(block.add(0,1,0)),TallGrass2);
                    }
            }
 }