package cz.sumaj.pv260.checks.methods;

/**
 * @author Jozef Sumaj, Jakub Senko
 */
public class BrainMethod extends AbstractDisharmonyMethod {

    public BrainMethod() {
        super();
    }

    /**
     * @param maxLoc maximum allowed number of lines in method
     * @param maxCyclo maximum allowed cyclo complexity
     * @param maxNoav maximum allowed number of variables
     * @return
     */
    @Override
    public boolean brainDisharmonyPresent(int maxLoc, int maxCyclo, int maxNoav) {
        boolean manyLines = currentLinesCount > maxLoc;
        boolean manyVars = currentVariablesCount > maxNoav;
        boolean complex = currentComplexity > maxCyclo;

        return (manyLines && manyVars && complex && maxNestingReached);
    }
}
