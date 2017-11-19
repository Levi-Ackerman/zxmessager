package top.lizhengxian.event_lib;


import top.lizhengxian.event_lib.anno.ThreadMode;

public class Description {
    public int id;
    public ThreadMode mThreadMode;
    public Class ownClass;
    public String methodName;
    public Class paramClass;

    public Description() {
    }

    public Description(int id, ThreadMode threadMode, Class ownClass, String methodName, Class paramClass) {
        this.id = id;
        this.mThreadMode = threadMode;
        this.ownClass = ownClass;
        this.methodName = methodName;
        this.paramClass = paramClass;
    }

    @Override
    public String toString() {
        return "Description{" +
                "id=" + id +
                ", mThreadMode=" + mThreadMode +
                ", ownClass='" + ownClass + '\'' +
                ", methodName='" + methodName + '\'' +
                ", paramClass='" + paramClass + '\'' +
                '}';
    }
}
