/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.supercar;

import org.docksidestage.bizfw.basic.screw.SpecialScrewManufacturer;
import org.docksidestage.bizfw.basic.screw.SpecialScrewManufacturer.ScrewSpec;
import org.docksidestage.bizfw.basic.screw.SpecialScrewManufacturer.SpecialScrew;

/**
 * The manufacturer(製造業者) of supercar steering wheel(車のハンドル).
 * @author jflute
 */
public class SupercarSteeringWheelManufacturer {

    private final SupercarSteeringWheelComponentDB componentDB = createSupercarSteeringWheelComponentDB();

    protected SupercarSteeringWheelComponentDB createSupercarSteeringWheelComponentDB() {
        return new SupercarSteeringWheelComponentDB();
    }

    public SteeringWheel makeSteeringWheel(Integer steeringWheelId) throws Exception {
        if (steeringWheelId == 3) {
            throw new Exception("This steering wheel is unavailable");
        }

        String specText = componentDB.findClincherSpecText(steeringWheelId);

        ScrewSpec screwSpec = new ScrewSpec(specText);

        SpecialScrewManufacturer manufacturer = createSpecialScrewManufacturer();
        SpecialScrew screw = manufacturer.makeSpecialScrew(screwSpec);

        return new SteeringWheel(screw);
    }

    protected SpecialScrewManufacturer createSpecialScrewManufacturer() {
        return new SpecialScrewManufacturer();
    }

    public static class SteeringWheel {

        public SteeringWheel(SpecialScrew screw) {
            // dummy
        }
    }
}
