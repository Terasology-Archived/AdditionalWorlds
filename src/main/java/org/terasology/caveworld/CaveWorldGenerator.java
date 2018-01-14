package org.terasology.caveworld;

import org.terasology.core.world.generator.facetProviders.FlatSurfaceHeightProvider;
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
                .addProvider(new FlatSurfaceHeightProvider(0))
                .addProvider(new SeaLevelProvider(seaLevel))
                .addProvider(new HillsProvider())
                .addRasterizer(new CaveWorldRasterizer())
                .addPlugins();
    }
}
