package org.terasology.additionalworlds.desertworld;

import org.terasology.math.Region3i;
import org.terasology.world.generation.Border3D;
import org.terasology.world.generation.facets.base.BaseBooleanFieldFacet3D;

public class LakeFacet extends BaseBooleanFieldFacet3D {
    public LakeFacet(Region3i targetRegion, Border3D border) {
        super(targetRegion, border);
    }
}
