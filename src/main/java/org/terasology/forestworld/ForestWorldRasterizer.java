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
package org.terasology.forestworld;

import org.terasology.math.ChunkMath;
import org.terasology.math.geom.Vector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.WorldRasterizer;

import java.util.Random;

public class ForestWorldRasterizer implements WorldRasterizer {
    private Block stone, mantleStone;

    @Override
    public void initialize(){
        stone = CoreRegistry.get(BlockManager.class).getBlock("Core:Stone");
        mantleStone = CoreRegistry.get(BlockManager.class).getBlock("Core:MantleStone");
    }

    @Override
    public void generateChunk(CoreChunk chunk,Region chunkRegion) {
        Random rand = new Random();
        for (Vector3i position : chunkRegion.getRegion()) {
            int r = rand.nextInt(100)+1;
            if (position.y < -12) {
                if (r % 29 == 0 || r % 13 == 0) {
                    chunk.setBlock(ChunkMath.calcBlockPos(position), mantleStone);
                } else {
                    chunk.setBlock(ChunkMath.calcBlockPos(position), stone);
                }
            }
        }
    }
}
