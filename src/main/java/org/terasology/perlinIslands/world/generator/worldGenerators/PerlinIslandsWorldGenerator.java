
package org.terasology.perlinIslands.world.generator.worldGenerators;

import org.terasology.core.world.generator.facetProviders.SeaLevelProvider;
import org.terasology.engine.SimpleUri;
import org.terasology.perlinIslands.world.generator.facetProviders.SurfaceProvider;
import org.terasology.perlinIslands.world.generator.rasterizers.PerlinIslandsRasterizer;
import org.terasology.registry.In;
import org.terasology.world.generation.BaseFacetedWorldGenerator;
import org.terasology.world.generation.WorldBuilder;
import org.terasology.world.generator.RegisterWorldGenerator;
import org.terasology.world.generator.plugin.WorldGeneratorPluginLibrary;

@RegisterWorldGenerator(id = "perlinIslands", displayName = "Perlin Islands")
public class PerlinIslandsWorldGenerator extends BaseFacetedWorldGenerator {
    @In
    private WorldGeneratorPluginLibrary worldGeneratorPLuginLibrary;

    public PerlinIslandsWorldGenerator(SimpleUri uri) {
        super(uri);
    }

    @Override
    protected WorldBuilder createWorld() {
        return new WorldBuilder(worldGeneratorPLuginLibrary)
                .addProvider(new SurfaceProvider(50))
                .addProvider(new SeaLevelProvider(0))
                .addRasterizer(new PerlinIslandsRasterizer());
    }

}
