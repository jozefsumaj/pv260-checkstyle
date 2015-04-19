package cz.sumaj.pv260.checks;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import cz.sumaj.pv260.checks.methods.BrainMethod;

/**
 * @author Jozef Sumaj, Jakub Senko
 */
public class BrainMethodCheck extends AbstractIdentityDisharmonyCheck {

    private static final int DEFAULT_MAX_LOC = 20;
    private static final int DEFAULT_MAX_CYCLO = 5;
    private static final int DEFAULT_MAX_NESTING = 3;
    private static final int DEFAULT_MAX_NOAV = 5;

    public BrainMethodCheck() {
        super(DEFAULT_MAX_LOC, DEFAULT_MAX_CYCLO, DEFAULT_MAX_NESTING,
                DEFAULT_MAX_NOAV);
    }

    @Override
    public int[] getDefaultTokens() {
        return new int[]{
            // listen to methods:
            TokenTypes.CTOR_DEF,
            TokenTypes.METHOD_DEF,
            TokenTypes.INSTANCE_INIT,
            TokenTypes.STATIC_INIT,
            // listen to variables:
            TokenTypes.VARIABLE_DEF,
            // listen to cyclomatic tokens:
            TokenTypes.LITERAL_WHILE,
            TokenTypes.LITERAL_DO,
            TokenTypes.LITERAL_FOR,
            TokenTypes.LITERAL_IF,
            TokenTypes.LITERAL_CASE,
            TokenTypes.LITERAL_CATCH,
            TokenTypes.QUESTION,
            TokenTypes.LAND,
            TokenTypes.LOR,};
    }

    @Override
    public void visitTokenHook(DetailAST ast) {
        switch (ast.getType()) {
            case TokenTypes.VARIABLE_DEF:
                method.incrementVariableCount();
                break;
            case TokenTypes.LITERAL_WHILE:
            case TokenTypes.LITERAL_DO:
            case TokenTypes.LITERAL_FOR:
            case TokenTypes.LITERAL_IF:
                method.incrementNesting(maxNestingLevel);
                method.incrementComplexity();
                break;
            default:
                method.incrementComplexity();
                break;
        }
    }

    @Override
    public void leaveTokenHook(DetailAST ast) {
        switch (ast.getType()) {
            case TokenTypes.LITERAL_WHILE:
            case TokenTypes.LITERAL_DO:
            case TokenTypes.LITERAL_FOR:
            case TokenTypes.LITERAL_IF:
                method.decreaseNesting();
                break;
            default:
                break;
        }
    }

    @Override
    public void visitMethodHook(DetailAST ast) {
        if (method == null) {
            method = new BrainMethod();
        } else {
            method.setInnerMethod(true);
        }
    }

    @Override
    public void leaveMethodHook(DetailAST ast) {
        if (method.isInnerMethod()) {
            method.setInnerMethod(false);
            return;
        }

        countLinesOfCode(ast);

        if (method.brainDisharmonyPresent(maxLinesOfCode, maxCyclomaticComplexity,
                maxNumberOfVariables)) {
            log(ast, "Brain method detected.");
        }
        
        method = null;
    }
}
