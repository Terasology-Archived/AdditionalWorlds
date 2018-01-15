/*
 * Copyright 2017 MovingBlocks
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
package org.terasology.additionalworlds.lonemountain;

import org.terasology.core.world.generator.facets.TreeFacet;
import org.terasology.core.world.generator.trees.Trees;
import org.terasology.math.TeraMath;
import org.terasology.math.geom.Vector2i;
import org.terasology.math.geom.Vector3i;
import org.terasology.world.generation.Border3D;
import org.terasology.world.generation.Facet;
import org.terasology.world.generation.FacetBorder;
import org.terasology.world.generation.FacetProvider;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Produces;
import org.terasology.world.generation.Requires;
import org.terasology.world.generation.facets.SurfaceHeightFacet;

import java.math.RoundingMode;

@Produces(TreeFacet.class)
@Requires(@Facet(value = SurfaceHeightFacet.class, border = @FacetBorder(sides = 13 + 1)))
public class LoneMountainTreeProvider implements FacetProvider {
    public static final Vector2i TREE_SURFACE_POSITION =
            new Vector2i(GaussianSurfaceProvider.CENTER, RoundingMode.FLOOR);
    public static final int MAX_TREE_BARK_RADIUS = 4;

    @Override
    public void process(GeneratingRegion region) {

        SurfaceHeightFacet surfaceFacet = region.getRegionFacet(SurfaceHeightFacet.class);

        int maxRad = 13;
        int maxHeight = 32;
        Border3D borderForTreeFacet = region.getBorderForFacet(TreeFacet.class);
        TreeFacet facet = new TreeFacet(region.getRegion(), borderForTreeFacet.extendBy(0, maxHeight, maxRad));

        if (surfaceFacet.getWorldRegion().contains(TREE_SURFACE_POSITION)) {
            Vector3i treePosition = new Vector3i(TREE_SURFACE_POSITION.x,
                    TeraMath.floorToInt(surfaceFacet.getWorld(TREE_SURFACE_POSITION)),
                    TREE_SURFACE_POSITION.y);

            if (facet.getWorldRegion().encompasses(treePosition)) {
                facet.setWorld(treePosition, Trees.pineTree());
            }
        }

        region.setRegionFacet(TreeFacet.class, facet);
    }
}
