package top.lizhengxian.processor;


public class DescriptionInfo {
    public int id;
    public String threadName;
    public String className;
    public String methodName;
    public String paramName;

    public DescriptionInfo() {
    }

    public DescriptionInfo(int id, String threadType, String className, String methodName, String paramName) {
        this.id = id;
        this.threadName = threadType;
        this.className = className;
        this.methodName = methodName;
        this.paramName = paramName;
    }

    @Override
    public String toString() {
        return "DescriptionInfo{" +
                "id=" + id +
                ", threadName=" + threadName +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", paramName='" + paramName + '\'' +
                '}';
    }
}
