package org.terasology.desertworld;

import org.terasology.core.world.generator.facetProviders.SeaLevelProvider;
import org.terasology.engine.SimpleUri;
import org.terasology.registry.In;
import org.terasology.world.generation.BaseFacetedWorldGenerator;
import org.terasology.world.generation.WorldBuilder;
import org.terasology.world.generator.RegisterWorldGenerator;
import org.terasology.world.generator.plugin.WorldGeneratorPluginLibrary;

@RegisterWorldGenerator(id = "desertWorld", displayName = "Desert World")
public class DesertWorldGenerator extends BaseFacetedWorldGenerator {

    @In
    private WorldGeneratorPluginLibrary worldGeneratorPluginLibrary;

    public DesertWorldGenerator(SimpleUri uri) {
        super(uri);
    }

    @Override
    protected WorldBuilder createWorld() {
        return new WorldBuilder(worldGeneratorPluginLibrary)
                .addProvider(new SurfaceProvider())
                .addProvider(new SeaLevelProvider(0))
                //.addProvider(new LakesProvider())
                .addRasterizer(new DesertWorldRasterizer());
        //.addRasterizer(new LakesRasterizer());
    }
}