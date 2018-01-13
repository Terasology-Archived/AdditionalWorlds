package org.terasology.MyWorld;

import org.terasology.math.geom.BaseVector2i;
import org.terasology.utilities.procedural.Noise;
import org.terasology.utilities.procedural.SimplexNoise;
import org.terasology.world.generation.Border3D;
import org.terasology.world.generation.Facet;
import org.terasology.world.generation.FacetProvider;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Produces;
import org.terasology.world.generation.Requires;
import org.terasology.world.generation.facets.SurfaceHeightFacet;

         @Produces(GrassFacet.class)
 @Requires(@Facet(SurfaceHeightFacet.class))
 public class GrassProvider implements FacetProvider {
     private Noise noise;

             @Override
     public void setSeed(long seed) {
                noise = new SimplexNoise(seed);
            }

             @Override
     public void process(GeneratingRegion region) {
                Border3D border = region.getBorderForFacet(GrassFacet.class);
                GrassFacet facet = new GrassFacet(region.getRegion(), border);
                SurfaceHeightFacet surfaceHeightFacet = region.getRegionFacet(SurfaceHeightFacet.class);
                for (BaseVector2i position : surfaceHeightFacet.getWorldRegion().contents()) {
                        int surfaceHeight = (int) surfaceHeightFacet.getWorld(position);
                        if (facet.getWorldRegion().encompasses(position.getX(), surfaceHeight, position.getY())
                                        && noise.noise(position.getX(), position.getY()) > 0.7) {
                                facet.setWorld(position.getX(), surfaceHeight, position.getY(), true);
                            }
                    }
                region.setRegionFacet(GrassFacet.class, facet);
            }

         }