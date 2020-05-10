/*
 * Copyright 2017 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.additionalworlds.forestworld;

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

public class ForestWorldRasterizer implements WorldRasterizer {
    private Block grass, mantleStone, stone;

    @Override
    public void initialize() {
        grass = CoreRegistry.get(BlockManager.class).getBlock("CoreAssets:Grass");
        mantleStone = CoreRegistry.get(BlockManager.class).getBlock("CoreAssets:MantleStone");
        stone = CoreRegistry.get(BlockManager.class).getBlock("CoreAssets:Stone");
    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion) {
        SurfaceHeightFacet surfaceHeightFacet = chunkRegion.getFacet(SurfaceHeightFacet.class);
        for (Vector3i position : chunkRegion.getRegion()) {
            float surfaceHeight = surfaceHeightFacet.getWorld(position.x, position.z);
            SeaLevelFacet seaLevelFacet = chunkRegion.getFacet(SeaLevelFacet.class);
            int seaLevel = seaLevelFacet.getSeaLevel();
            if (position.y < surfaceHeight) {
                if(surfaceHeight - position.y > 9) {
                    chunk.setBlock(ChunkMath.calcBlockPos(position), stone);
                } else if (position.y < seaLevel) {
                    chunk.setBlock(ChunkMath.calcBlockPos(position), mantleStone);
                } else {
                    chunk.setBlock(ChunkMath.calcBlockPos(position), grass);
                }
            }
        }
    }
}
