package top.lizhengxian.event_lib.window;


public class WindowUtil {
    private static int sBaseId = 0;
    synchronized static String genStackTag(){
        return String.valueOf(sBaseId++);
    }
}
