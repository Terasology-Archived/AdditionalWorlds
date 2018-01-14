package org.terasology.MyWorld;

import org.terasology.math.geom.Rect2i;
import org.terasology.math.TeraMath;
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

@Produces(HouseFacet.class)
@Requires(@Facet(value = SurfaceHeightFacet.class, border = @FacetBorder(sides = 4)))
public class HouseProvider implements FacetProvider {

    private Noise noise;

    @Override
    public void setSeed(long seed) {
        noise = new WhiteNoise(seed);
    }

    @Override
    public void process(GeneratingRegion region) {

        Border3D border = region.getBorderForFacet(HouseFacet.class).extendBy(0, 8, 4);
        HouseFacet facet = new HouseFacet(region.getRegion(), border);
        SurfaceHeightFacet surfaceHeightFacet = region.getRegionFacet(SurfaceHeightFacet.class);

        Rect2i worldRegion = surfaceHeightFacet.getWorldRegion();

        for (int wz = worldRegion.minY(); wz <= worldRegion.maxY(); wz++) {
            for (int wx = worldRegion.minX(); wx <= worldRegion.maxX(); wx++) {
                int surfaceHeight = TeraMath.floorToInt(surfaceHeightFacet.getWorld(wx, wz));

                // check if height is within this region
                if (surfaceHeight >= facet.getWorldRegion().minY() &&
                        surfaceHeight <= facet.getWorldRegion().maxY()) {

                    // TODO: check for overlap
                    if (noise.noise(wx, wz) > 0.998) {
                        facet.setWorld(wx, surfaceHeight, wz, new House());
                    }
                }
            }
        }

        region.setRegionFacet(HouseFacet.class, facet);
    }
}