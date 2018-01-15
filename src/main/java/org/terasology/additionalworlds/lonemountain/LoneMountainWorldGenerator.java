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
package org.terasology.additionalworlds.lonemountain;

import org.terasology.core.world.generator.facetProviders.SeaLevelProvider;
import org.terasology.core.world.generator.facetProviders.SurfaceToDensityProvider;
import org.terasology.core.world.generator.rasterizers.TreeRasterizer;
import org.terasology.engine.SimpleUri;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.math.Region3i;
import org.terasology.math.geom.Vector2i;
import org.terasology.math.geom.Vector3f;
import org.terasology.math.geom.Vector3i;
import org.terasology.registry.In;
import org.terasology.world.WorldProvider;
import org.terasology.world.generation.BaseFacetedWorldGenerator;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.World;
import org.terasology.world.generation.WorldBuilder;
import org.terasology.world.generation.facets.SurfaceHeightFacet;
import org.terasology.world.generator.RegisterWorldGenerator;
import org.terasology.world.generator.plugin.WorldGeneratorPluginLibrary;

import java.math.RoundingMode;

@RegisterWorldGenerator(id = "loneMountain", displayName = "Lone Mountain")
public class LoneMountainWorldGenerator extends BaseFacetedWorldGenerator {
    private static final int SPAWN_OFFSET = LoneMountainTreeProvider.MAX_TREE_BARK_RADIUS;
    private static final Vector2i SPAWN_POSITION = LoneMountainTreeProvider.TREE_SURFACE_POSITION;

    @In
    private WorldGeneratorPluginLibrary worldGeneratorPluginLibrary;

    @In
    private WorldProvider worldProvider;

    public LoneMountainWorldGenerator(SimpleUri uri) {
        super(uri);
    }

    @Override
    public Vector3f getSpawnPosition(EntityRef entity) {
        World world = getWorld();

        Vector2i offsetSpawnPosition = Vector2i.one().mul(SPAWN_OFFSET).add(SPAWN_POSITION);

        Vector3i ext = new Vector3i(SPAWN_OFFSET, 1, SPAWN_OFFSET);
        Vector3i desiredPos = new Vector3i(offsetSpawnPosition.getX(), 1, offsetSpawnPosition.getY());

        Region3i spawnArea = Region3i.createFromCenterExtents(desiredPos, ext);
        Region worldRegion = world.getWorldData(spawnArea);

        SurfaceHeightFacet surfaceHeightFacet = worldRegion.getFacet(SurfaceHeightFacet.class);

        float y = surfaceHeightFacet.getWorld(offsetSpawnPosition.getX(), offsetSpawnPosition.getY());
        return new Vector3f(offsetSpawnPosition.getX(), y, offsetSpawnPosition.getY());
    }

    @Override
    protected WorldBuilder createWorld() {
        return new WorldBuilder(worldGeneratorPluginLibrary)
                .addProvider(new SeaLevelProvider(2))
                .addProvider(new GaussianSurfaceProvider())
                .addProvider(new LoneMountainTreeProvider())
                .addProvider(new SurfaceToDensityProvider())
                .addRasterizer(new LoneMountainRasterizer())
                .addRasterizer(new TreeRasterizer());
    }
}
