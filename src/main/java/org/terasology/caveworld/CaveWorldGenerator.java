/*
 * Copyright 2018 MovingBlocks
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
package org.terasology.caveworld;

import org.terasology.core.world.generator.facetProviders.SeaLevelProvider;
import org.terasology.engine.SimpleUri;
import org.terasology.registry.In;
import org.terasology.world.generation.BaseFacetedWorldGenerator;
import org.terasology.world.generation.WorldBuilder;
import org.terasology.world.generator.RegisterWorldGenerator;
import org.terasology.world.generator.plugin.WorldGeneratorPluginLibrary;

@RegisterWorldGenerator(id = "caveWorld", displayName = "Cave World")
public class CaveWorldGenerator extends BaseFacetedWorldGenerator {

    @In
    private WorldGeneratorPluginLibrary worldGeneratorPluginLibrary;

    public CaveWorldGenerator(SimpleUri uri) {
        super(uri);
    }

    @Override
    protected WorldBuilder createWorld() {
        int seaLevel = -50;

        return new WorldBuilder(worldGeneratorPluginLibrary)
                .setSeaLevel(seaLevel)
                .addProvider(new AmplitudeProvider(0))
                .addProvider(new SurfaceProvider())
                .addProvider(new SeaLevelProvider(seaLevel))
                .addProvider(new HillsProvider())
                .addProvider(new CaveSurfaceHeightProvider())
                .addProvider(new CaveFacetProvider())
                .addProvider(new CaveToDensityProvider())
                .addProvider(new LavalakeFacetProvider())
                .addRasterizer(new CaveWorldRasterizer())
                .addRasterizer(new CaveRasterizer())
                .addRasterizer(new LavalakeRasterizer())
                .addPlugins();
    }
}
