package top.lizhengxian.event_lib.zx;


import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import top.lizhengxian.event_lib.anno.ThreadMode;

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

    public static void execute(ThreadMode threadMode, Runnable runnable) {
        if (runnable == null) {
            return;
        }
        switch (threadMode) {
            case SYNC:
                runnable.run();
                break;
            case BACKGROUD:
                getInstance().mBackgroundExecutor.execute(runnable);
                break;
            case MAIN:
                getInstance().mMainHandler.post(runnable);
                break;
            case IO:
                getInstance().mIOExecutor.execute(runnable);
                break;
        }
    }
}
