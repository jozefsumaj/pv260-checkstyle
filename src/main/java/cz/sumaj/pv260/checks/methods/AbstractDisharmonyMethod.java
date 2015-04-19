package cz.sumaj.pv260.checks.methods;

/**
 * @author Jozef Sumaj, Jakub Senko
 */
public abstract class AbstractDisharmonyMethod {

    protected int currentVariablesCount;
    protected int currentNestingLevel;
    protected int currentComplexity;
    protected int currentLinesCount;
    protected boolean innerMethod;
    protected boolean maxNestingReached;

    public AbstractDisharmonyMethod() {
        currentVariablesCount = 0;
        currentNestingLevel = 0;
        currentComplexity = 0;
        currentLinesCount = 0;
        innerMethod = false;
        maxNestingReached = false;
    }

    /**
     * @return the innerMethod
     */
    public boolean isInnerMethod() {
        return innerMethod;
    }

    /**
     * @param innerMethod the innerMethod to set
     */
    public void setInnerMethod(boolean innerMethod) {
        this.innerMethod = innerMethod;
    }

    /**
     * @param linesCount the currentLinesCount to set
     */
    public void setLinesCount(int linesCount) {
        this.currentLinesCount = linesCount;
    }

    public void incrementNesting(int maxNesting) {
        if (currentNestingLevel == maxNesting) {
            maxNestingReached = true;
        }
        ++currentNestingLevel;
    }

    public void decreaseNesting() {
        --currentNestingLevel;
    }

    public void incrementVariableCount() {
        ++currentVariablesCount;
    }

    public void incrementComplexity() {
        ++currentComplexity;
    }

    public abstract boolean brainDisharmonyPresent(int maxLoc, int maxCyclo,
            int maxNoav);
}
