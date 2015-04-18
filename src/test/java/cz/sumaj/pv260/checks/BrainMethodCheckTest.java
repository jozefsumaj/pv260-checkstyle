package cz.sumaj.pv260.checks;

import com.puppycrawl.tools.checkstyle.BaseCheckTestSupport;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import java.io.File;
import org.junit.Test;

/**
 *
 * @author Jozef Sumaj, Jakub Senko
 */
public class BrainMethodCheckTest extends BaseCheckTestSupport {
    
    @Test
    public void testIt() throws Exception
    {
        final DefaultConfiguration checkConfig = 
                createCheckConfig(BrainMethodCheck.class);
        checkConfig.addAttribute("maxLinesOfCode", "1");
        // expected: line number: error message
        final String[] expected = {
            "16: " + "Brain method detected.",
        };
        verify(checkConfig,
                new File("src/test/resources/cz/sumaj/pv260/checks/BrainClass.java")
                        .getCanonicalPath(),
                expected);
    }
}
