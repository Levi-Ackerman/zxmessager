package top.lizhengxian.event_lib;


public class DescriptionInfo {
    public int id;
    public int threadType;
    public String className;
    public String methodName;
    public String paramName;

    public DescriptionInfo() {
    }

    public DescriptionInfo(int id,int threadType, String className, String methodName, String paramName) {
        this.id = id;
        this.threadType = threadType;
        this.className = className;
        this.methodName = methodName;
        this.paramName = paramName;
    }

    @Override
    public String toString() {
        return "DescriptionInfo{" +
                "id=" + id +
                ", threadType=" + threadType +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", paramName='" + paramName + '\'' +
                '}';
    }
}
