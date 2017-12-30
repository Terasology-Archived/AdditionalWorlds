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

public class TreeRasterizer implements WorldRasterizer{
    private Block birchWood, oakWood, pineWood, leaf, stone;

    @Override
    public void initialize() {
        birchWood = CoreRegistry.get(BlockManager.class).getBlock("Core:BirchTrunk");
        oakWood = CoreRegistry.get(BlockManager.class).getBlock("Core:OakTrunk");
        pineWood = CoreRegistry.get(BlockManager.class).getBlock("Core:PineTrunk");
        leaf = CoreRegistry.get(BlockManager.class).getBlock("Core:GreenLeaf");
        stone = CoreRegistry.get(BlockManager.class).getBlock("Core:Stone");
    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion) {
        TreeFacet treeFacet = chunkRegion.getFacet(TreeFacet.class);
        Random rand = new Random();
        Block trunk;

        for (Vector3i block : treeFacet.getWorldRegion()) if(treeFacet.getWorld(block)) {
            if (rand.nextInt() * 100 % 29 == 0) trunk = birchWood;
            else if (rand.nextInt() * 100 % 15 == 0) trunk = pineWood;
            else trunk = oakWood;
            for (int i = 0; i < 5; i++) {
                chunk.setBlock(ChunkMath.calcBlockPos(block.add(0, 1, 0)), trunk);
            }
            for (int i = 0; i < 3; i++) {
                chunk.setBlock(ChunkMath.calcBlockPos(block.add(1, 0, 0)), leaf);
            }
            for (int i = 0; i < 3; i++) {
                chunk.setBlock(ChunkMath.calcBlockPos(block.add(-1, 0, 1)), leaf);
            }
            for (int i = 0; i < 3; i++) {
                chunk.setBlock(ChunkMath.calcBlockPos(block.add(-1, 0, -1)), leaf);
            }
            for (int i = 0; i < 3; i++) {
                chunk.setBlock(ChunkMath.calcBlockPos(block.add(1, 0, -1)), leaf);
            }
            for (int i = 0; i < 2; i++) {
                chunk.setBlock(ChunkMath.calcBlockPos(block.add(1, 0, 1)), leaf);
            }
            for (int i = 0; i < 3; i++) {
                chunk.setBlock(ChunkMath.calcBlockPos(block.add(-1, 0, 0)), leaf);
            }
            for (int i = 0; i < 2; i++) {
                chunk.setBlock(ChunkMath.calcBlockPos(block.add(0, 0, 1)), leaf);
            }
            for (int i = 0; i < 2; i++) {
                chunk.setBlock(ChunkMath.calcBlockPos(block.add(1, 0, 0)), leaf);
            }
            for (int i = 0; i < 2; i++) {
                chunk.setBlock(ChunkMath.calcBlockPos(block.add(0, 0, -1)), leaf);
            }
            chunk.setBlock(ChunkMath.calcBlockPos(block.add(-1, 0, -1)), leaf);
            chunk.setBlock(ChunkMath.calcBlockPos(block.add(-2, 0, 2)), leaf);
            chunk.setBlock(ChunkMath.calcBlockPos(block.add(2, 0, 2)), leaf);
            chunk.setBlock(ChunkMath.calcBlockPos(block.add(0, 1, -2)), leaf);
            chunk.setBlock(ChunkMath.calcBlockPos(block.add( 1, 0, 0)), leaf);
            chunk.setBlock(ChunkMath.calcBlockPos(block.add( 0, 0, 1)), leaf);
            for(int i = 0; i < 2; i++) {
                chunk.setBlock(ChunkMath.calcBlockPos(block.add( -1, 0, 0)), leaf);
            }
            for(int i = 0; i < 2; i++) {
                chunk.setBlock(ChunkMath.calcBlockPos(block.add( 0, 0, -1)), leaf);
            }
            for(int i = 0; i < 2; i++) {
                chunk.setBlock(ChunkMath.calcBlockPos(block.add( 1, 0, 0)), leaf);
            }
            chunk.setBlock(ChunkMath.calcBlockPos(block.add( -1, 1, 1)), leaf);
            chunk.setBlock(ChunkMath.calcBlockPos(block.add( 1, 0, 0)), leaf);
            chunk.setBlock(ChunkMath.calcBlockPos(block.add( -1, 0, 1)), leaf);
            chunk.setBlock(ChunkMath.calcBlockPos(block.add( -1, 0, -1)), leaf);
            chunk.setBlock(ChunkMath.calcBlockPos(block.add( 1, 0, -1)), leaf);
            chunk.setBlock(ChunkMath.calcBlockPos(block.add( 0, 1, 1)), leaf);
        }
    }
}
