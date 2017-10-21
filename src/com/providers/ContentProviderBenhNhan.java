package com.providers;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.sql2o.data.Row;

import com.model.dao.BenhNhan;

public class ContentProviderBenhNhan implements IStructuredContentProvider {
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof List)
			return ((List<Row>)inputElement).toArray();
		return new Object[0];
	}
	public void dispose() {
	}
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
}