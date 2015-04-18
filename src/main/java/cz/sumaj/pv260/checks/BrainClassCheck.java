package cz.sumaj.pv260.checks;

import com.puppycrawl.tools.checkstyle.api.*;

/**
 *
 * @author Jozef Sumaj, Jakub Senko
 */
public class BrainClassCheck extends Check {

    private static final int DEFAULT_MAX = 1;
    private int max = DEFAULT_MAX;

    @Override
    public int[] getDefaultTokens()
    {
        return new int[]{TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF};
    }
    
    @Override
    public void visitToken(DetailAST ast)
    {
        // find the OBJBLOCK node below the CLASS_DEF/INTERFACE_DEF
        DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);

        // count the number of direct children of the OBJBLOCK
        // that are METHOD_DEFS
        int methodDefs = objBlock.getChildCount(TokenTypes.METHOD_DEF);

        // report error if limit is reached
        if (methodDefs > this.max) {
            String message = "too many methods, only " + this.max + " are allowed";
            log(ast.getLineNo(), message);
        }
    }
    
    public void setMax(int limit)
    {
        max = limit;
    }
    
}
