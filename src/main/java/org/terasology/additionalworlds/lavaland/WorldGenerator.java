package org.terasology.additionalworlds.lavaland;
import org.terasology.core.world.generator.facetProviders.SeaLevelProvider;
import org.terasology.engine.SimpleUri;
import org.terasology.registry.In;
import org.terasology.world.generation.BaseFacetedWorldGenerator;
import org.terasology.world.generation.WorldBuilder;
import org.terasology.world.generator.RegisterWorldGenerator;
import org.terasology.world.generator.plugin.WorldGeneratorPluginLibrary;

@RegisterWorldGenerator(id = "contronLavaLand", displayName = "Lava Land")
public class WorldGenerator extends BaseFacetedWorldGenerator {
    @In
    private WorldGeneratorPluginLibrary worldGeneratorPluginLibrary;
    
    public WorldGenerator(SimpleUri uri) {
        super(uri);
    }
    
    @Override
    protected WorldBuilder createWorld() {
        return new WorldBuilder(worldGeneratorPluginLibrary)
                .addProvider(new SurfaceProvider())
                .addProvider(new SeaLevelProvider(0))
                .addProvider(new MountainsProvider())
                .addProvider(new BushProvider())
                .addProvider(new LogProvider())
                .addProvider(new HouseProvider())
                .addProvider(new LakesProvider())
                .addRasterizer(new TerrainRasterizer())
                .addRasterizer(new BushRasterizer())
                .addRasterizer(new LogRasterizer())
                .addRasterizer(new HouseRasterizer())
                .addRasterizer(new LakesRasterizer())
                .addPlugins();
    }
}
