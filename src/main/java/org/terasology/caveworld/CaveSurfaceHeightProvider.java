/*
 * Copyright 2018 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.caveworld;

import org.terasology.math.geom.BaseVector2i;
import org.terasology.world.generation.Facet;
import org.terasology.world.generation.FacetProvider;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Produces;
import org.terasology.world.generation.Requires;
import org.terasology.world.generation.facets.SeaLevelFacet;
import org.terasology.world.generation.facets.SurfaceHeightFacet;

@Produces(SurfaceHeightFacet.class)
@Requires(@Facet(HeightFacet.class))
public class CaveSurfaceHeightProvider implements FacetProvider {

    public CaveSurfaceHeightProvider() {
    }

    @Override
    public void setSeed(long seed) {
    }

    @Override
    public void process(GeneratingRegion region) {
        SurfaceHeightFacet facet = new SurfaceHeightFacet(region.getRegion(), region.getBorderForFacet(SurfaceHeightFacet.class));
        HeightFacet heightFacet = region.getRegionFacet(HeightFacet.class);

        for (BaseVector2i pos : facet.getRelativeRegion().contents()) {
            facet.set(pos, heightFacet.get(pos));
        }

        region.setRegionFacet(SurfaceHeightFacet.class, facet);
    }
}
