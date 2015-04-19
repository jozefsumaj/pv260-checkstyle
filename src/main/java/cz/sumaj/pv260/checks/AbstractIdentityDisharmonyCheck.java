package cz.sumaj.pv260.checks;

import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import cz.sumaj.pv260.checks.methods.AbstractDisharmonyMethod;

/**
 * @author Jozef Sumaj, Jakub Senko
 */
public abstract class AbstractIdentityDisharmonyCheck extends Check {

    protected int maxLinesOfCode;
    protected int maxCyclomaticComplexity;
    protected int maxNestingLevel;
    protected int maxNumberOfVariables;
    protected AbstractDisharmonyMethod method;

    /**
     * @param maxLinesOfCode the maxLinesOfCode to set
     */
    public void setMaxLinesOfCode(int maxLinesOfCode) {
        this.maxLinesOfCode = maxLinesOfCode;
    }

    /**
     * @param maxCyclomaticComplexity the maxCyclomaticComplexity to set
     */
    public void setMaxCyclomaticComplexity(int maxCyclomaticComplexity) {
        this.maxCyclomaticComplexity = maxCyclomaticComplexity;
    }

    /**
     * @param maxNestingLevel the maxNestingLevel to set
     */
    public void setMaxNestingLevel(int maxNestingLevel) {
        this.maxNestingLevel = maxNestingLevel;
    }

    /**
     * @param maxNumberOfVariables the maxNumberOfVariables to set
     */
    public void setMaxNumberOfVariables(int maxNumberOfVariables) {
        this.maxNumberOfVariables = maxNumberOfVariables;
    }

    public AbstractIdentityDisharmonyCheck(int maxLinesOfCode, int maxCyclomaticComplexity,
            int maxNestingLevel, int maxNumberOfVariables) {
        this.maxLinesOfCode = maxLinesOfCode;
        this.maxCyclomaticComplexity = maxCyclomaticComplexity;
        this.maxNestingLevel = maxNestingLevel;
        this.maxNumberOfVariables = maxNumberOfVariables;
    }

    @Override
    public void visitToken(DetailAST ast) {
        switch (ast.getType()) {
            case TokenTypes.CTOR_DEF:
            case TokenTypes.METHOD_DEF:
            case TokenTypes.INSTANCE_INIT:
            case TokenTypes.STATIC_INIT:
                visitMethodHook(ast);
                break;
            default:
                visitTokenHook(ast);
                break;
        }
    }

    @Override
    public void leaveToken(DetailAST ast) {
        switch (ast.getType()) {
            case TokenTypes.CTOR_DEF:
            case TokenTypes.METHOD_DEF:
            case TokenTypes.INSTANCE_INIT:
            case TokenTypes.STATIC_INIT:
                leaveMethodHook(ast);
                break;
            default:
                leaveTokenHook(ast);
                break;
        }
    }

    protected abstract void visitTokenHook(DetailAST ast);

    protected abstract void leaveTokenHook(DetailAST ast);

    protected abstract void visitMethodHook(DetailAST ast);

    protected abstract void leaveMethodHook(DetailAST ast);

    protected void countLinesOfCode(DetailAST ast) {
        DetailAST methodStart = ast.findFirstToken(TokenTypes.SLIST);
        DetailAST methodEnd = methodStart.findFirstToken(TokenTypes.RCURLY);

        int startLineNumber = methodStart.getLineNo();
        int endLineNumber = methodEnd.getLineNo();

        method.setLinesCount(endLineNumber - startLineNumber + 1);
    }
}
