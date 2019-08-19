package util;

import java.text.MessageFormat;

public class Log {
    private boolean DEBUG = false;
    private boolean ERROR = true;


    public Log() {
    }

    public void setDEBUG(boolean DEBUG) {
        this.DEBUG = DEBUG;
    }
    public boolean getDEBUG(){
        return DEBUG;
    }


    public void debug(String s, Object... obj) {
        if (DEBUG) {
            System.out.println(MessageFormat.format(s, obj));
        }
    }

    public void info(String s, Object... obj) {
            System.out.println(MessageFormat.format(s, obj));
    }


    public void error(String s, Object... obj) {
        if (ERROR) {
            System.err.println(MessageFormat.format(s, obj));
        }
    }

}
