package org.terasology.desertworld;

import org.terasology.world.generation.Border3D;
import org.terasology.world.generation.Facet;
import org.terasology.world.generation.FacetBorder;
import org.terasology.world.generation.FacetProviderPlugin;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Produces;
import org.terasology.world.generation.Requires;
import org.terasology.world.generation.facets.SurfaceHeightFacet;
import org.terasology.world.generator.plugin.RegisterPlugin;

@RegisterPlugin
@Requires(@Facet(value = SurfaceHeightFacet.class, border = @FacetBorder(sides = 5, bottom = 2, top = 2)))
@Produces(LakeFacet.class)
public class LakesProvider implements FacetProviderPlugin {


    @Override
    public void setSeed(long seed) {
    }

    @Override
    public void process(GeneratingRegion region) {
        Border3D border = region.getBorderForFacet(CactusFacet.class);
        LakeFacet facet = new LakeFacet(region.getRegion(), border);

        region.setRegionFacet(LakeFacet.class, facet);
    }
}
