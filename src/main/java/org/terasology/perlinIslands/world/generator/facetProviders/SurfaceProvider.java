
package org.terasology.perlinIslands.world.generator.facetProviders;

import org.terasology.math.geom.BaseVector2i;
import org.terasology.math.geom.Rect2i;
import org.terasology.math.geom.Vector2f;
import org.terasology.utilities.procedural.Noise;
import org.terasology.utilities.procedural.PerlinNoise;
import org.terasology.utilities.procedural.SubSampledNoise;
import org.terasology.world.generation.Border3D;
import org.terasology.world.generation.FacetProvider;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Produces;
import org.terasology.world.generation.facets.SurfaceHeightFacet;

@Produces(SurfaceHeightFacet.class)
public class SurfaceProvider implements FacetProvider {
    private int amplitude;
    private Noise surfaceNoise;

    public SurfaceProvider(int amplitude) {
        this.amplitude = amplitude;
    }

    @Override
    public void setSeed(long seed) {
        surfaceNoise = new SubSampledNoise(new PerlinNoise(seed), new Vector2f(0.009f, 0.009f), 1);
    }

    @Override
    public void process(GeneratingRegion region) {
        Border3D border = region.getBorderForFacet(SurfaceHeightFacet.class);
        SurfaceHeightFacet facet = new SurfaceHeightFacet(region.getRegion(), border);

        Rect2i processRegion = facet.getWorldRegion();
        for (BaseVector2i pos : processRegion.contents()) {
            facet.setWorld(pos, surfaceNoise.noise(pos.getX(), pos.getY()) * amplitude);
        }

        region.setRegionFacet(SurfaceHeightFacet.class, facet);
    }

}
