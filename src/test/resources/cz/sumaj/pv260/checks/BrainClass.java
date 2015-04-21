package cz.sumaj.pv260.checks;

/**
 * @author Jozef Sumaj, Jakub Senko
 */
public class BrainClass {

    public void brainMethod() {

        int foo1 = 1;
        int foo2 = 2;
        boolean foo = false;

        while (foo1 < 10) {

            int foo3 = 3;

            if (foo3 > 3) {
                if (foo3 == 4) {
                    return;
                }
            }

            for (int i = 0; i < 100; i++) {
                int foo4 = 4;

                if (i > 5) {
                    int foo5 = 5;
                    int foo6 = 6;

                    if (foo6 < 10) {
                        foo = true;
                    }
                }
            }
        }
        
        try
        {
            do
            {
                foo1++;
                if(foo1 == 5)
                {
                    throw new Exception("foo1");
                }
            }
            while(foo1 < 20);
        }
        catch(Exception ex)
        {
            int foo7 = 7;
        }
        
        while(foo2 > 30)
        {
            foo2++;
        }
    }
    
    public void notSoBadBrainMethod() {

        int foo1 = 1;
        int foo2 = 2;
        boolean foo = false;

        while (foo1 < 10) {

            int foo3 = 3;

            if (foo3 > 3) {
                if (foo3 == 4) {
                    return;
                }
            }

            for (int i = 0; i < 100; i++) {
                int foo4 = 4;

                if (i > 5) {
                    int foo5 = 5;
                    int foo6 = 6;

                    if (foo6 < 10) {
                        foo = true;
                    }
                }
            }
        }
    }
}
