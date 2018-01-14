package org.terasology.MyWorld;

import org.terasology.math.Region3i;
import org.terasology.world.generation.Border3D;
import org.terasology.world.generation.facets.base.SparseObjectFacet3D;


public class HouseFacet extends SparseObjectFacet3D<House> {

    public HouseFacet(Region3i targetRegion, Border3D border) {
        super(targetRegion, border);
    }
}