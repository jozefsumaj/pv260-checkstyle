package cz.sumaj.pv260.checks;

/**
 *
 * @author Jozef Sumaj, Jakub Senko
 */
public class BrainClass {
    
    public void foo() {
        while (true) {
            Runnable runnable = new Runnable() {
                public void run() {
                    while (true) {
                    }
                }
            };

            new Thread(runnable).start();
        }
    }

    public void bar() {
        if (System.currentTimeMillis() == 0) {
            if (System.currentTimeMillis() == 0 && System.currentTimeMillis() == 0) {
            }

            if (System.currentTimeMillis() == 0 || System.currentTimeMillis() == 0) {
            }
        }
    }

    public void simpleElseIf() {
        if (System.currentTimeMillis() == 0) {
        } else if (System.currentTimeMillis() == 0) {
        } else {
        }
    }

    public void stupidElseIf() {
        if (System.currentTimeMillis() == 0) {
        } else {
            if (System.currentTimeMillis() == 0) {
            } else {
                if (System.currentTimeMillis() == 0) {
                }
            }

            if (System.currentTimeMillis() == 0) {
            }
        }
    }
    
    // STATIC_INIT   
    static {
        int i = 1;
        if (System.currentTimeMillis() == 0) {
        } else if (System.currentTimeMillis() == 0) {
        } else {
        }
    }
    
    // INSTANCE_INIT   
    {
        int i = 1;
        if (System.currentTimeMillis() == 0) {
        } else if (System.currentTimeMillis() == 0) {
        } else {
        }
    }
}
