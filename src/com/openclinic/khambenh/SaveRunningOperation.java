package com.openclinic.khambenh;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

public class SaveRunningOperation implements IRunnableWithProgress {
	public static Object objNotifyDone = new Object();
	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		monitor.beginTask("Đang xử lý....",IProgressMonitor.UNKNOWN);
		//monitor.subTask("Đang xử lý.....");
		//monitor.worked(500);
		synchronized (SaveRunningOperation.objNotifyDone) {
			objNotifyDone.wait();
		}
		monitor.done();
		if (monitor.isCanceled())
			throw new InterruptedException(
					"The long running operation was cancelled");

	}
}
