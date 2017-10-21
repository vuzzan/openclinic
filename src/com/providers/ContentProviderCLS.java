package com.providers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.ibm.icu.util.RangeValueIterator.Element;
import com.model.dao.DvChitiet;

public class ContentProviderCLS implements IStructuredContentProvider{
	public Object[] getElements(Object inputElement) {
		//System.out.println(inputElement.getClass().getName());
		if(inputElement instanceof List){
			List<DvChitiet> list = (List<DvChitiet>)inputElement;
			return list.toArray();
		}
		return new Object[0];
	}
	public void dispose() {
	}
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
}