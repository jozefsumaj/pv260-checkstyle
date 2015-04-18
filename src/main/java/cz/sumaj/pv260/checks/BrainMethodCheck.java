package cz.sumaj.pv260.checks;

import com.puppycrawl.tools.checkstyle.api.*;

/**
 *
 * @author Jozef Sumaj, Jakub Senko
 */
public class BrainMethodCheck extends BrainMethodBaseCheck {

    private static final int DEFAULT_MAX = 1;

    public BrainMethodCheck() 
    {
        super(DEFAULT_MAX, DEFAULT_MAX, DEFAULT_MAX, DEFAULT_MAX);
    }
        
    @Override
    public int[] getDefaultTokens()
    {
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
            TokenTypes.LOR,
        };
    }
    
    @Override
    public void visitTokenHook(DetailAST ast)
    {
        switch (ast.getType()) {
            case TokenTypes.VARIABLE_DEF:
                // process variables count
                incrementVariables();
                break;
            case TokenTypes.LITERAL_WHILE:
            case TokenTypes.LITERAL_DO:
            case TokenTypes.LITERAL_FOR:
            case TokenTypes.LITERAL_IF:
                // process nesting
                incrementNesting();
                // process cyclomatic complexity
                incrementComplexity();
                break;
            default:
                // process cyclomatic complexity
                incrementComplexity();
        }
    }
    
    @Override
    public void leaveTokenHook(DetailAST ast)
    {
        switch (ast.getType()) {
            case TokenTypes.LITERAL_WHILE:
            case TokenTypes.LITERAL_DO:
            case TokenTypes.LITERAL_FOR:
            case TokenTypes.LITERAL_IF:
                // process cyclomatic complexity
                decreaseNesting();
                break;
            default:
                break;
        }
    }
}
