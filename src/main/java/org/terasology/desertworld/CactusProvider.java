package org.terasology.desertworld;

import org.terasology.math.geom.BaseVector2i;
import org.terasology.utilities.procedural.Noise;
import org.terasology.utilities.procedural.WhiteNoise;
import org.terasology.world.generation.Border3D;
import org.terasology.world.generation.Facet;
import org.terasology.world.generation.FacetBorder;
import org.terasology.world.generation.FacetProvider;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Produces;
import org.terasology.world.generation.Requires;
import org.terasology.world.generation.facets.SurfaceHeightFacet;

@Produces(CactusFacet.class)
@Requires(@Facet(value = SurfaceHeightFacet.class, border = @FacetBorder(top = 3, bottom = 3)))
public class CactusProvider implements FacetProvider {

    private Noise noise;

    @Override
    public void setSeed(long seed) {
        noise = new WhiteNoise(seed);
    }

    @Override
    public void process(GeneratingRegion region) {

        //Don't forget you sometimes have to extend the borders.
        //extendBy(top, bottom, sides) is the method used for this.

        Border3D border = region.getBorderForFacet(CactusFacet.class);
        CactusFacet facet = new CactusFacet(region.getRegion(), border);
        SurfaceHeightFacet surfaceHeightFacet = region.getRegionFacet(SurfaceHeightFacet.class);

         for (BaseVector2i position : surfaceHeightFacet.getWorldRegion().contents()) {
            int surfaceHeight = (int) surfaceHeightFacet.getWorld(position);

            if (facet.getWorldRegion().encompasses(position.getX(), surfaceHeight, position.getY())
                && noise.noise(position.getX(), position.getY()) > 0.995) {
                facet.setWorld(position.getX(), surfaceHeight, position.getY(), true);
            }
        }

        region.setRegionFacet(CactusFacet.class, facet);
    }

}