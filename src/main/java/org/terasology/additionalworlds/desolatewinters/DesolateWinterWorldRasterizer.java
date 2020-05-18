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
package org.terasology.additionalworlds.desolatewinters;

import org.terasology.math.ChunkMath;
import org.terasology.math.geom.Vector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.WorldRasterizer;
import org.terasology.world.generation.facets.SurfaceHeightFacet;

import java.util.Random;

public class DesolateWinterWorldRasterizer implements WorldRasterizer {

    private Block dirt;
    private Block snow;
    private Block water;
    private Block ice;
    private Block thickSnow;
    private int rand;

    @Override
    public void initialize() {
        dirt = CoreRegistry.get(BlockManager.class).getBlock("CoreAssets:Dirt");
        snow = CoreRegistry.get(BlockManager.class).getBlock("CoreAssets:Snow");
        water = CoreRegistry.get(BlockManager.class).getBlock("CoreAssets:Water");
        ice = CoreRegistry.get(BlockManager.class).getBlock("CoreAssets:Ice");
        thickSnow = CoreRegistry.get(BlockManager.class).getBlock("AdditionalWorlds:ThickSnow");
        rand = 0;
    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion) {
        SurfaceHeightFacet surfaceHeightFacet = chunkRegion.getFacet(SurfaceHeightFacet.class);
        Random random = new Random();

        for (Vector3i position : chunkRegion.getRegion()) {
            float surfaceHeight = surfaceHeightFacet.getWorld(position.x, position.z);
            rand = random.nextInt(15);


            if (position.y < surfaceHeight - 1) {
                if(surfaceHeight>=60+rand){
                    chunk.setBlock(ChunkMath.calcBlockPos(position), thickSnow);
                } else {
                    chunk.setBlock(ChunkMath.calcBlockPos(position), dirt);
                }
            }  else if (position.y < surfaceHeight) {
                chunk.setBlock(ChunkMath.calcBlockPos(position), snow);
            } else if (position.y < 0) {
                if(rand<=13) {
                    chunk.setBlock(ChunkMath.calcBlockPos(position), water);
                }else {
                    chunk.setBlock(ChunkMath.calcBlockPos(position), ice);
                }
            }
        }

    }
}
