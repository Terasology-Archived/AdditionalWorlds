
package org.terasology.perlinIslands.world.generator.rasterizers;

import org.terasology.math.ChunkMath;
import org.terasology.math.geom.Vector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.WorldRasterizer;
import org.terasology.world.generation.facets.SeaLevelFacet;
import org.terasology.world.generation.facets.SurfaceHeightFacet;

public class PerlinIslandsRasterizer implements WorldRasterizer {
    private Block grass, dirt, stone, water, sand;

    @Override
    public void initialize() {
        grass = CoreRegistry.get(BlockManager.class).getBlock("Core:Grass");
        dirt = CoreRegistry.get(BlockManager.class).getBlock("Core:Dirt");
        stone = CoreRegistry.get(BlockManager.class).getBlock("Core:Stone");
        water = CoreRegistry.get(BlockManager.class).getBlock("Core:Water");
        sand = CoreRegistry.get(BlockManager.class).getBlock("Core:Sand");
    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion) {
        SurfaceHeightFacet surfaceHeightFacet = chunkRegion.getFacet(SurfaceHeightFacet.class);
        SeaLevelFacet seaLevelFacet = chunkRegion.getFacet(SeaLevelFacet.class);

        for (Vector3i pos : chunkRegion.getRegion()) {
            Block setBlock = null;
            float surfaceHeight = surfaceHeightFacet.getWorld(pos.x, pos.z);
            float seaLevel = seaLevelFacet.getSeaLevel();

            if (pos.y < surfaceHeight - ((Math.random() * 2) + 2)) {
                setBlock = stone;
            } else if (pos.y < surfaceHeight - 1) {
                setBlock = dirt;
            } else if (pos.y < surfaceHeight) {
                int shFloor = (int) Math.floor(surfaceHeight);
                int slFloor = (int) Math.floor(seaLevel);

                if (shFloor < slFloor - 1) {
                    setBlock = dirt;
                } else if (shFloor < slFloor) {
                    setBlock = sand;
                } else {
                    setBlock = grass;
                }
            } else if (pos.y < seaLevel) {
                setBlock = water;
            }

            if (setBlock != null) {
                chunk.setBlock(ChunkMath.calcBlockPos(pos), setBlock);
            }
        }
    }

}
