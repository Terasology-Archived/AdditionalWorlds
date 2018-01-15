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
package org.terasology.caveworld;

import org.terasology.world.generation.FacetProviderPlugin;
import org.terasology.world.generation.GeneratingRegion;
import org.terasology.world.generation.Produces;
import org.terasology.world.generator.plugin.RegisterPlugin;

@RegisterPlugin
@Produces(AmplitudeFacet.class)
public class AmplitudeProvider implements FacetProviderPlugin {

    private int amplitude = 50;

    public AmplitudeProvider(int amplitude) {
        this.amplitude = amplitude;
    }

    public AmplitudeProvider() {}

    @Override
    public void process(GeneratingRegion region) {
        region.setRegionFacet(AmplitudeFacet.class, new AmplitudeFacet(amplitude));
    }
}
