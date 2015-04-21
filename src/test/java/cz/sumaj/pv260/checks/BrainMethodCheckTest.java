package cz.sumaj.pv260.checks;

import com.puppycrawl.tools.checkstyle.BaseCheckTestSupport;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import java.io.File;
import java.io.IOException;
import org.junit.Test;

/**
 * @author Jozef Sumaj, Jakub Senko
 */
public class BrainMethodCheckTest extends BaseCheckTestSupport {

    /**
     * Test verifies detection of brain method disharmony in provided java test
     * class, using check-style module BrainMethodCheck.
     * 
     * Test using default configuration (50,10,3,8)
     *
     * @throws Exception
     */
    @Test
    public void testDefaultConfig() throws Exception {
        final DefaultConfiguration checkConfig
                = createCheckConfig(BrainMethodCheck.class);
        checkConfig.addAttribute("maxLinesOfCode", "50");
        checkConfig.addAttribute("maxCyclomaticComplexity", "10");
        checkConfig.addAttribute("maxNestingLevel", "3");
        checkConfig.addAttribute("maxNumberOfVariables", "8");

        // expected: line number: error message
        final String[] expected = {
            "8:5: " + "Brain method detected."};

        verify(checkConfig, getTestClassPath("BrainClass.java"), expected);
    }
    
    /**
     * Test using non-default configuration (30,5,3,3)
     *
     * @throws Exception
     */
    @Test
    public void testNonDefaultConfig() throws Exception {
        final DefaultConfiguration checkConfig
                = createCheckConfig(BrainMethodCheck.class);
        checkConfig.addAttribute("maxLinesOfCode", "25");
        checkConfig.addAttribute("maxCyclomaticComplexity", "5");
        checkConfig.addAttribute("maxNestingLevel", "3");
        checkConfig.addAttribute("maxNumberOfVariables", "3");
        
        final String[] expected = {
            "8:5: " + "Brain method detected.",
            "61:5: " + "Brain method detected."};

        verify(checkConfig, getTestClassPath("BrainClass.java"), expected);
    }

    private String getTestClassPath(String className) throws IOException {
        String filePathPrefix = "src/test/resources/cz/sumaj/pv260/checks/";
        return new File(filePathPrefix + className).getCanonicalPath();
    }
}
