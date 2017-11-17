package top.lizhengxian.event_lib;


public class DescriptionInfo {
    public int id;
    public String className;
    public String methodName;
    public String paramName;
    @Override
    public String toString() {
        return "DescriptionInfo{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", paramName='" + paramName + '\'' +
                '}';
    }
}
