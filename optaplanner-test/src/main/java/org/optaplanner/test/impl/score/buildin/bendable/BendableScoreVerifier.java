/*
 * Copyright 2016 JBoss Inc
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

package org.optaplanner.test.impl.score.buildin.bendable;

import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.score.buildin.bendable.BendableScore;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.impl.score.buildin.bendable.BendableScoreDefinition;
import org.optaplanner.test.impl.score.AbstractScoreVerifier;

/**
 * To assert the constraints (including score rules) of a {@link SolverFactory}
 * that uses a {@link BendableScore}.
 * @param <Solution_> the solution type, the class with the {@link PlanningSolution} annotation
 */
public class BendableScoreVerifier<Solution_> extends AbstractScoreVerifier<Solution_> {

    protected final int hardLevelsSize;

    /**
     * @param solverFactory never null, the {@link SolverFactory} of which you want to test the constraints.
     */
    public BendableScoreVerifier(SolverFactory<Solution_> solverFactory) {
        super(solverFactory, BendableScore.class);
        hardLevelsSize = ((BendableScoreDefinition) scoreDirectorFactory.getScoreDefinition()).getHardLevelsSize();
    }

    /**
     * Assert that the constraint (which is usually a score rule) of {@link PlanningSolution}
     * has the expected weight for that score level
     * @param constraintName never null, the name of the constraint, which is usually the name of the score rule
     * @param hardLevel {@code 0 <= hardLevel <} {@code hardLevelSize}.
     * The {@code scoreLevel} is {@code hardLevel} for hard levels and {@code softLevel + hardLevelSize} for soft levels.
     * @param expectedWeight the total weight for all matches of that 1 constraint
     * @param solution never null, the actual {@link PlanningSolution}
     */
    public void assertHard(String constraintName, int hardLevel, int expectedWeight, Solution_ solution) {
        assertHard(null, constraintName, hardLevel, expectedWeight, solution);
    }

    /**
     * Assert that the constraint (which is usually a score rule) of {@link PlanningSolution}
     * has the expected weight for that score level.
     * @param constraintPackage sometimes null.
     * When null, {@code constraintName} for the {@code scoreLevel} must be unique.
     * @param constraintName never null, the name of the constraint, which is usually the name of the score rule
     * @param hardLevel {@code 0 <= hardLevel <} {@code hardLevelSize}.
     * The {@code scoreLevel} is {@code hardLevel} for hard levels and {@code softLevel + hardLevelSize} for soft levels.
     * @param expectedWeight the total weight for all matches of that 1 constraint
     * @param solution never null, the actual {@link PlanningSolution}
     */
    public void assertHard(String constraintPackage, String constraintName,
            int hardLevel, int expectedWeight,
            Solution_ solution) {
        assertConstraintWeight(constraintPackage, constraintName,
                hardLevel, Integer.valueOf(expectedWeight), solution);
    }

    /**
     * Assert that the constraint (which is usually a score rule) of {@link PlanningSolution}
     * has the expected weight for that score level.
     * @param constraintName never null, the name of the constraint, which is usually the name of the score rule
     * @param softLevel {@code 0 <= softLevel <} {@code softLevelSize}.
     * The {@code scoreLevel} is {@code hardLevel} for hard levels and {@code softLevel + hardLevelSize} for soft levels.
     * @param expectedWeight the total weight for all matches of that 1 constraint
     * @param solution never null, the actual {@link PlanningSolution}
     */
    public void assertSoft(String constraintName, int softLevel, int expectedWeight, Solution_ solution) {
        assertSoft(null, constraintName, softLevel, expectedWeight, solution);
    }

    /**
     * Assert that the constraint (which is usually a score rule) of {@link PlanningSolution}
     * has the expected weight for that score level.
     * @param constraintPackage sometimes null.
     * When null, {@code constraintName} for the {@code scoreLevel} must be unique.
     * @param constraintName never null, the name of the constraint, which is usually the name of the score rule
     * @param softLevel {@code 0 <= softLevel <} {@code softLevelSize}.
     * The {@code scoreLevel} is {@code hardLevel} for hard levels and {@code softLevel + hardLevelSize} for soft levels.
     * @param expectedWeight the total weight for all matches of that 1 constraint
     * @param solution never null, the actual {@link PlanningSolution}
     */
    public void assertSoft(String constraintPackage, String constraintName, int softLevel, int expectedWeight,
            Solution_ solution) {
        assertConstraintWeight(constraintPackage, constraintName,
                hardLevelsSize + softLevel, Integer.valueOf(expectedWeight), solution);
    }

}
