package test.ad.util;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorUtil extends ThreadPoolExecutor {
	private static ExecutorUtil sInstance;

	private ExecutorUtil(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
	}

	public synchronized static void post(Runnable run) {
		if (sInstance == null) {
			sInstance = new ExecutorUtil(1, 3, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), new RejectedExecutionHandler() {
				@Override
				public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
					System.out.println("runnable rejectd!!!");
				}
			});
		}
		sInstance.execute(run);
	}
}
