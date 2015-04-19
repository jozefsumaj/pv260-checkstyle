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
     * @throws Exception
     */
    @Test
    public void testBrainMethodCheck() throws Exception {
        final DefaultConfiguration checkConfig
                = createCheckConfig(BrainMethodCheck.class);
        checkConfig.addAttribute("maxLinesOfCode", "1");

        // expected: line number: error message
        final String[] expected = {
            "9:5: " + "Brain method detected.",};

        verify(checkConfig, getTestClassPath("BrainClass.java"), expected);
    }

    private String getTestClassPath(String className) throws IOException {
        String filePathPrefix = "src/test/resources/cz/sumaj/pv260/checks/";
        return new File(filePathPrefix + className).getCanonicalPath();
    }
}
