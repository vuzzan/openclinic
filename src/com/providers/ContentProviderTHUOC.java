package com.providers;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.model.dao.DvChitiet;
import com.model.dao.ThuocChitiet;

public class ContentProviderTHUOC implements IStructuredContentProvider {
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof List){
			List<ThuocChitiet> list = (List<ThuocChitiet>)inputElement;
			return list.toArray();
		}
		return new Object[0];
	}
	public void dispose() {
	}
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
}