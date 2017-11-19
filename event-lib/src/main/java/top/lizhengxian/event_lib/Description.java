package top.lizhengxian.event_lib;


import top.lizhengxian.event_lib.anno.Thread;

public class Description {
    public int id;
    public Thread threadType;
    public Class ownClass;
    public String methodName;
    public Class paramClass;

    public Description() {
    }

    public Description(int id, Thread threadType, Class ownClass, String methodName, Class paramClass) {
        this.id = id;
        this.threadType = threadType;
        this.ownClass = ownClass;
        this.methodName = methodName;
        this.paramClass = paramClass;
    }

    @Override
    public String toString() {
        return "Description{" +
                "id=" + id +
                ", threadType=" + threadType +
                ", ownClass='" + ownClass + '\'' +
                ", methodName='" + methodName + '\'' +
                ", paramClass='" + paramClass + '\'' +
                '}';
    }
}
