package org.terasology.MyWorld;

import java.util.Map.Entry;

import org.terasology.math.ChunkMath;
import org.terasology.math.Region3i;
import org.terasology.math.geom.BaseVector3i;
import org.terasology.math.geom.Vector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.WorldRasterizer;

public class    HouseRasterizer implements WorldRasterizer {
    private Block brick;

    @Override
    public void initialize() {
        brick = CoreRegistry.get(BlockManager.class).getBlock("Core:Brick");
    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion) {
        HouseFacet houseFacet = chunkRegion.getFacet(HouseFacet.class);

        for (Entry<BaseVector3i, House> entry : houseFacet.getWorldEntries().entrySet()) {
            // there should be a house here
            // create a couple 3d regions to help iterate through the cube shape, inside and out
            Vector3i centerHousePosition = new Vector3i(entry.getKey());
            int extent = entry.getValue().getExtent();
            centerHousePosition.add(0, extent, 0);
            Region3i walls = Region3i.createFromCenterExtents(centerHousePosition, extent);
            Region3i inside = Region3i.createFromCenterExtents(centerHousePosition, extent - 1);

            // loop through each of the positions in the cube, ignoring the is
            for (Vector3i newBlockPosition : walls) {
                if (chunkRegion.getRegion().encompasses(newBlockPosition)
                        && !inside.encompasses(newBlockPosition)) {
                    chunk.setBlock(ChunkMath.calcBlockPos(newBlockPosition), brick);
                }
            }
        }
    }
}