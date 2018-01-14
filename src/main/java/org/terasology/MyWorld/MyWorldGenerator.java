package org.terasology.MyWorld;

import org.terasology.core.world.generator.facetProviders.SeaLevelProvider;
import org.terasology.engine.SimpleUri;
import org.terasology.registry.In;
import org.terasology.world.generation.BaseFacetedWorldGenerator;
import org.terasology.world.generation.WorldBuilder;
import org.terasology.world.generator.RegisterWorldGenerator;
import org.terasology.world.generator.plugin.WorldGeneratorPluginLibrary;

@RegisterWorldGenerator(id = "MyWorld", displayName = "2012 in Artic")
public class MyWorldGenerator extends BaseFacetedWorldGenerator {

    @In
    private WorldGeneratorPluginLibrary worldGeneratorPluginLibrary;

    public MyWorldGenerator(SimpleUri uri) {
        super(uri);
    }

    @Override
    protected WorldBuilder createWorld() {
        return new WorldBuilder(worldGeneratorPluginLibrary)
                .addProvider(new SurfaceProvider())
                .addProvider(new LavaProvider())
                .addProvider(new Perks())
                .addProvider(new HouseProvider())
                .addProvider(new GrassProvider())
                .addProvider(new SeaLevelProvider(0))
                .addRasterizer(new LavaRasterizer())
                .addRasterizer(new HouseRasterizer())
                .addRasterizer(new GrassRasterizer())
                .addRasterizer(new MyWorldRasterizer())
                .addPlugins();


    }
}