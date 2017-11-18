package top.lizhengxian.event_lib.zx;


import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import top.lizhengxian.event_lib.anno.Thread;

public class ZxExecutor {
    private static class Holder {
        private static final ZxExecutor INSTANCE = new ZxExecutor();
    }

    private ZxExecutor() {
        mMainHandler = new Handler(Looper.getMainLooper());
        mBackgroundExecutor = Executors.newSingleThreadExecutor();
        mIOExecutor = AsyncTask.THREAD_POOL_EXECUTOR;
    }

    private static ZxExecutor getInstance() {
        return Holder.INSTANCE;
    }

    private Handler mMainHandler;
    private Executor mBackgroundExecutor;
    private Executor mIOExecutor;

    public static void execute(int threadType, Runnable runnable) {
        if (runnable == null) {
            return;
        }
        switch (threadType) {
            case Thread.SYNC:
                runnable.run();
                break;
            case Thread.BACKGROUD:
                getInstance().mBackgroundExecutor.execute(runnable);
                break;
            case Thread.MAIN:
                getInstance().mMainHandler.post(runnable);
                break;
            case Thread.IO:
                getInstance().mIOExecutor.execute(runnable);
                break;
        }
    }
}
