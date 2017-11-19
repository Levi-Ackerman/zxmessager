package top.lizhengxian.event_lib.util;


public class AssertUtil {
    public static void assertTrue(boolean expre, String log){
        if (expre){
            throw new Error(log);
        }
    }
}
