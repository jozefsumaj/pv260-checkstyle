package cz.sumaj.pv260.checks;

import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * TO DO: create BrainMethod class to hold information about the brain method
 * @author Jozef
 */
public class BrainMethodBaseCheck extends Check{

    private int maxLinesOfCode;
    private int maxCyclomaticComplexity;
    private int maxNestingLevel;
    private int maxNumberOfVariables;
    
    private int currentVariables;
    private int currentNesting;
    private int currentComplexity;
    
    private boolean innerMethod;
    private boolean insideMethod;
    private boolean maxNestingExceed;
    
    public BrainMethodBaseCheck(int maxLinesOfCode, int maxCyclomaticComplexity,
            int maxNestingLevel, int maxNumberOfVariables)
    {
        this.maxLinesOfCode = maxLinesOfCode;
        this.maxCyclomaticComplexity = maxCyclomaticComplexity;
        this.maxNestingLevel = maxNestingLevel;
        this.maxNumberOfVariables = maxNumberOfVariables;
        
        this.currentComplexity = 0;
        this.currentNesting = 0;
        this.currentVariables = 0;
        
        this.innerMethod = false;
        this.insideMethod = false;
        this.maxNestingExceed = false;
    }
    
    // setters
    public void setMaxLinesOfCode(int limit)
    {        
        this.maxLinesOfCode = limit;
    }
    
    public void setMaxCyclomaticComplexity(int limit)
    {
        this.maxCyclomaticComplexity = limit;
    }
    
    public void setMaxNestingLevel(int limit)
    {
        this.maxNestingLevel = limit;
    }
    
    public void setMaxNumberOfVariables(int limit)
    {
        this.maxNumberOfVariables = limit;
    }
    
    @Override
    public int[] getDefaultTokens() {
        return new int[] {
            TokenTypes.CTOR_DEF,
            TokenTypes.METHOD_DEF,
            TokenTypes.INSTANCE_INIT,
            TokenTypes.STATIC_INIT,
        };
    }
    
    @Override
    public void visitToken(DetailAST ast)
    {
        switch (ast.getType()) {
            case TokenTypes.CTOR_DEF:
            case TokenTypes.METHOD_DEF:
            case TokenTypes.INSTANCE_INIT:
            case TokenTypes.STATIC_INIT:
                // check if method is inner
                if(insideMethod) innerMethod = true;
                // set inside method switch
                insideMethod = true;
                break;
            default:
                // process start of some other token
                if(insideMethod) visitTokenHook(ast);
        }
    }
    
    @Override
    public void leaveToken(DetailAST ast)
    {
        switch (ast.getType()) {
            case TokenTypes.CTOR_DEF:
            case TokenTypes.METHOD_DEF:
            case TokenTypes.INSTANCE_INIT:
            case TokenTypes.STATIC_INIT:
                // process end of the method -- except inner methods
                if(innerMethod)
                {
                    innerMethod = false;
                    break;
                }
                result(ast);
                reset();
                break;
            default:
                // process end of some other token
                if(insideMethod) leaveTokenHook(ast);
        }
    }
    
    protected void visitTokenHook(DetailAST ast)
    {
        
    }
    
    protected void leaveTokenHook(DetailAST ast)
    {

    }
    
    protected void incrementNesting()
    {
        if (currentNesting == maxNestingLevel)
        {
            maxNestingExceed = true;
        }
        ++currentNesting;
    }
    
    protected void decreaseNesting()
    {
        --currentNesting;
    }
    
    protected void incrementComplexity()
    {
        ++currentComplexity;
    }
    
    protected void incrementVariables()
    {
        ++currentVariables;
    }
    
    private boolean processLineCount(DetailAST ast) 
    {
        DetailAST methodStart = ast.findFirstToken(TokenTypes.SLIST);
        DetailAST methodEnd = methodStart.findFirstToken(TokenTypes.RCURLY);
        
        int startLineNumber = methodStart.getLineNo();
        int endLineNumber =  methodEnd.getLineNo();
        
        return (endLineNumber - startLineNumber + 1) > maxLinesOfCode;
    }

    private void result(DetailAST ast)
    {
        boolean complex = currentComplexity > maxCyclomaticComplexity;
        boolean manyVars = currentVariables > maxNumberOfVariables;
        boolean manyLines = processLineCount(ast);
                
        if(complex && maxNestingExceed && manyVars && manyLines)
        {
            log(ast, "Brain method detected.");
        }
    }

    private void reset() 
    {
        currentComplexity = 0;
        currentNesting = 0;
        currentVariables = 0;
        maxNestingExceed = false;
        insideMethod = false;
    }
}
