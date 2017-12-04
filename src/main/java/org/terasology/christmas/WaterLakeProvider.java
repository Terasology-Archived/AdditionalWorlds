import org.terasology.math.TeraMath;
import org.terasology.math.geom.BaseVector2i;
import org.terasology.math.geom.Rect2i;
import org.terasology.math.geom.Vector2f;
import org.terasology.utilities.procedural.BrownianNoise;
import org.terasology.utilities.procedural.Noise;
import org.terasology.utilities.procedural.PerlinNoise;
import org.terasology.utilities.procedural.SubSampledNoise;
import org.terasology.world.generation.FacetProviderPlugin;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.facets.SurfaceHeightFacet;

public class WaterLakeProvider implements FacetProviderPlugin {

    private Noise lakeNoise;

    @Override
    public void setSeed(long seed) {
        lakeNoise = new SubSampledNoise(new BrownianNoise(new PerlinNoise(seed + 3), 4), new Vector2f(0.001f, 0.001f), 1);
    }

    @Override
    public void process(GeneratingRegion region) {
        SurfaceHeightFacet facet = region.getRegionFacet(SurfaceHeightFacet.class);
        float depth = 40;
        Rect2i processRegion = facet.getWorldRegion();
        for (BaseVector2i position : processRegion.contents()) {
            float additiveLakeDepth = lakeNoise.noise(position.x(), position.y()) * depth;
            additiveLakeDepth = TeraMath.clamp(additiveLakeDepth, -depth, 0);
            facet.setWorld(position, facet.getWorld(position) + additiveLakeDepth);
        }
    }
}
