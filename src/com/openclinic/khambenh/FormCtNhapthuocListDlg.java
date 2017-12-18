package com.openclinic.khambenh;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import swing2swt.layout.BorderLayout;

import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.sql2o.Connection;

import com.DbHelper;
import com.model.dao.CtNhapthuoc;
import com.openclinic.utils.Utils;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class FormCtNhapthuocListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(FormCtNhapthuocListDlg.class.getName());
	protected Object result;
	protected Shell shlChnThucCho;

	private class TableLabelProviderCtNhapthuoc extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof CtNhapthuoc){
				//return ((CtNhapthuoc) element).getIndex(columnIndex);
				CtNhapthuoc obj = (CtNhapthuoc)element;
				if(columnIndex==0){
					return obj.TENKHO;
				}
				else if(columnIndex==1){
					return obj.TENTHUOC;
				}
				else if(columnIndex==2){
					return obj.DONVI;
				}
				else if(columnIndex==3){
					return ( obj.HANDUNG );
				}
				else if(columnIndex==4){
					return Utils.getMoneyDefault(obj.DONGIA);
				}
				else if(columnIndex==5){
					return ""+obj.SL_TONKHO;
				}
				else if(columnIndex==6){
					return ""+obj.CTID_FROM;
				}
				else if(columnIndex==7){
					return ""+obj.SL_DADUNG;
				}
				else if(columnIndex==8){
					return obj.SOLUONG+"";
				}
				else if(columnIndex==6){
				}
			}
			return "";
		}
	}
	private static class ContentProviderCtNhapthuoc implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			if(inputElement instanceof ArrayList){
				return ((ArrayList) inputElement).toArray();
			}
			return new Object[0];
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}
	private Table tableCtNhapthuoc;
	private TableViewer tableViewerCtNhapthuoc;
	private List<CtNhapthuoc> listDataCtNhapthuoc;
	public int typeCtNhapthuocDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

	public CtNhapthuoc objCtNhapthuoc = null;
	private Text textSearchCtNhapthuoc;
	private String textSearchCtNhapthuocString;
	private Text txtSoluong;
	public int iSoLuongChiDinh = 0;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public FormCtNhapthuocListDlg(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlChnThucCho.open();
		shlChnThucCho.layout();
		Display display = getParent().getDisplay();
        //
//        Monitor primary = display.getPrimaryMonitor();
//		Rectangle bounds = primary.getBounds();
//		Rectangle rect = shell.getBounds();
//		int x = bounds.x + (bounds.width - rect.width) / 2;
//		int y = bounds.y + (bounds.height - rect.height) / 2;
//		shell.setLocation(x, y);
        
		while (!shlChnThucCho.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlChnThucCho = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER | SWT.PRIMARY_MODAL);
		shlChnThucCho.setImage(SWTResourceManager.getImage(FormCtNhapthuocListDlg.class, "/png/list-2x.png"));
		shlChnThucCho.setSize(948, 382);
		shlChnThucCho.setText("Chọn thuốc cho bệnh");
		shlChnThucCho.setLayout(new BorderLayout(0, 0));
		shlChnThucCho.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objCtNhapthuoc = null;
				}
			}
		});
        
        
        Composite compositeInShellCtNhapthuoc = new Composite(shlChnThucCho, SWT.NONE);
		compositeInShellCtNhapthuoc.setLayout(new BorderLayout(0, 0));
		compositeInShellCtNhapthuoc.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderCtNhapthuoc = new Composite(compositeInShellCtNhapthuoc, SWT.NONE);
		compositeHeaderCtNhapthuoc.setLayoutData(BorderLayout.NORTH);
		compositeHeaderCtNhapthuoc.setLayout(new GridLayout(3, false));
		
		Label lblTmThucTrong = new Label(compositeHeaderCtNhapthuoc, SWT.NONE);
		lblTmThucTrong.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		lblTmThucTrong.setText("Tìm thuốc");

		textSearchCtNhapthuoc = new Text(compositeHeaderCtNhapthuoc, SWT.BORDER);
		GridData gd_textSearchCtNhapthuoc = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_textSearchCtNhapthuoc.heightHint = 35;
		gd_textSearchCtNhapthuoc.widthHint = 188;
		textSearchCtNhapthuoc.setLayoutData(gd_textSearchCtNhapthuoc);
		textSearchCtNhapthuoc.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		textSearchCtNhapthuoc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableCtNhapthuoc();
				}
			}
		});
		
		Button btnNewButtonSearchCtNhapthuoc = new Button(compositeHeaderCtNhapthuoc, SWT.NONE);
		GridData gd_btnNewButtonSearchCtNhapthuoc = new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1);
		gd_btnNewButtonSearchCtNhapthuoc.widthHint = 307;
		btnNewButtonSearchCtNhapthuoc.setLayoutData(gd_btnNewButtonSearchCtNhapthuoc);
		btnNewButtonSearchCtNhapthuoc.setImage(SWTResourceManager.getImage(FormCtNhapthuocListDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchCtNhapthuoc.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnNewButtonSearchCtNhapthuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableCtNhapthuoc();
			}
		});
		btnNewButtonSearchCtNhapthuoc.setText("Tìm thuốc trong kho");
		
		Label lblSLng = new Label(compositeHeaderCtNhapthuoc, SWT.NONE);
		lblSLng.setText("Số lượng");
		lblSLng.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		
		txtSoluong = new Text(compositeHeaderCtNhapthuoc, SWT.BORDER | SWT.RIGHT);
		txtSoluong.setText("0");
		txtSoluong.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		txtSoluong.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		txtSoluong.setFont(SWTResourceManager.getFont("Tahoma", 15, SWT.NORMAL));
		txtSoluong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					selectSoluongAndClose();
				}
			}
		});
		
		new Label(compositeHeaderCtNhapthuoc, SWT.NONE);
        
		tableViewerCtNhapthuoc = new TableViewer(compositeInShellCtNhapthuoc, SWT.BORDER | SWT.FULL_SELECTION);
		tableCtNhapthuoc = tableViewerCtNhapthuoc.getTable();
		tableCtNhapthuoc.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tableCtNhapthuoc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableCtNhapthuoc();
                }
				else if(e.keyCode==13){
					selectTableCtNhapthuoc();
				}
			}
		});
        tableCtNhapthuoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableCtNhapthuoc();
			}
		});
        
		tableCtNhapthuoc.setLinesVisible(true);
		tableCtNhapthuoc.setHeaderVisible(true);
		tableCtNhapthuoc.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnCtNhapthuocTENKHO = new TableColumn(tableCtNhapthuoc, SWT.LEFT);
		tbTableColumnCtNhapthuocTENKHO.setWidth(117);
		tbTableColumnCtNhapthuocTENKHO.setText("KHO");

		TableColumn tbTableColumnCtNhapthuocTENTHUOC = new TableColumn(tableCtNhapthuoc, SWT.LEFT);
		tbTableColumnCtNhapthuocTENTHUOC.setWidth(233);
		tbTableColumnCtNhapthuocTENTHUOC.setText("TÊN THUỐC");

		TableColumn tbTableColumnCtNhapthuocDONVI = new TableColumn(tableCtNhapthuoc, SWT.LEFT);
		tbTableColumnCtNhapthuocDONVI.setWidth(80);
		tbTableColumnCtNhapthuocDONVI.setText("ĐV TÍNH");


		TableColumn tbTableColumnCtNhapthuocHANDUNG = new TableColumn(tableCtNhapthuoc, SWT.NONE);
		tbTableColumnCtNhapthuocHANDUNG.setWidth(108);
		tbTableColumnCtNhapthuocHANDUNG.setText("HẠN DÙNG");
		
				TableColumn tbTableColumnCtNhapthuocDONGIA = new TableColumn(tableCtNhapthuoc, SWT.RIGHT);
				tbTableColumnCtNhapthuocDONGIA.setWidth(73);
				tbTableColumnCtNhapthuocDONGIA.setText("ĐƠN GIÁ");
		
				TableColumn tbTableColumnCtNhapthuocSL_TONKHO = new TableColumn(tableCtNhapthuoc, SWT.RIGHT);
				tbTableColumnCtNhapthuocSL_TONKHO.setToolTipText("Tồn kho, chỉ cấp số lượng < số này");
				tbTableColumnCtNhapthuocSL_TONKHO.setWidth(90);
				tbTableColumnCtNhapthuocSL_TONKHO.setText("TỒN KHO");
		
		TableColumn tblclmnSlSCp = new TableColumn(tableCtNhapthuoc, SWT.RIGHT);
		tblclmnSlSCp.setToolTipText("SL thuốc BS đã cho, đang đợi xuất kho");
		tblclmnSlSCp.setWidth(76);
		tblclmnSlSCp.setText("SẼ CẤP");
		
		TableColumn tblclmnSlCp = new TableColumn(tableCtNhapthuoc, SWT.RIGHT);
		tblclmnSlCp.setToolTipText("SL thuốc đã cấp cho người bệnh");
		tblclmnSlCp.setWidth(76);
		tblclmnSlCp.setText("ĐÃ CẤP");

		TableColumn tbTableColumnCtNhapthuocSOLUONG = new TableColumn(tableCtNhapthuoc, SWT.RIGHT);
		tbTableColumnCtNhapthuocSOLUONG.setToolTipText("SL nhập ban đầu");
		tbTableColumnCtNhapthuocSOLUONG.setWidth(85);
		tbTableColumnCtNhapthuocSOLUONG.setText("SL NHẬP");

		tableViewerCtNhapthuoc.setLabelProvider(new TableLabelProviderCtNhapthuoc());
		tableViewerCtNhapthuoc.setContentProvider(new ContentProviderCtNhapthuoc());
		tableViewerCtNhapthuoc.setInput(listDataCtNhapthuoc);
        //
		shlChnThucCho.setMaximized(true);
        //
		loadDataCtNhapthuoc();
		//
        reloadTableCtNhapthuoc();
	}
   
	public void setDataCtNhapthuoc(String textSearchString){
		this.textSearchCtNhapthuocString = textSearchString;
	}
	private void loadDataCtNhapthuoc() {
		if(textSearchCtNhapthuocString!=null){
			textSearchCtNhapthuoc.setText(textSearchCtNhapthuocString);
			txtSoluong.setText(""+iSoLuongChiDinh);
		}
	}
	protected void reloadTableCtNhapthuoc() {
		//if(listDataCtNhapthuoc==null){
			// Do search
			String searchString = textSearchCtNhapthuoc.getText().toLowerCase().trim();
			String sql = "select * from ct_nhapthuoc WHERE STS<> "+DbHelper.DELETE_STATUS+" and SL_TONKHO>0 ";
			if(searchString.length()>0){
	            sql += " and ";
		        sql += " LOWER(TENTHUOC) like '"+searchString+"%'";
	        }
			sql += " order by HANDUNG ASC";
			try  {
	            logger.info(sql);
				Connection con = DbHelper.getSql2o();
				listDataCtNhapthuoc = con.createQuery(sql).executeAndFetch(CtNhapthuoc.class);
		    }   
		    catch(Exception e){
		    	logger.error(e);
		    	MessageDialog.openError(shlChnThucCho, "Error", e.getMessage());
		    }
		//}
		// 
		tableViewerCtNhapthuoc.setInput(listDataCtNhapthuoc);
		tableViewerCtNhapthuoc.refresh();
        //
        if(listDataCtNhapthuoc.size()==0){
            textSearchCtNhapthuoc.forceFocus();
        }
        else{
            tableCtNhapthuoc.forceFocus();
            tableCtNhapthuoc.setSelection(0);
        }
	}
    
    
    protected void selectTableCtNhapthuoc() {
		if(tableCtNhapthuoc.getSelectionCount()==0){
			objCtNhapthuoc = null;
			return;
		}
		TableItem item = tableCtNhapthuoc.getSelection()[0];
		CtNhapthuoc obj = (CtNhapthuoc)item.getData();
        logger.info(obj.toString());
        //
		// Choosen and close dlg
		objCtNhapthuoc = obj;
		txtSoluong.forceFocus();
		txtSoluong.selectAll();
		//shell.close();
	}
    
    protected void selectSoluongAndClose() {
		if(objCtNhapthuoc==null){
			
			tableCtNhapthuoc.forceFocus();
			tableCtNhapthuoc.select(0);
			return;
		}
		iSoLuongChiDinh = Utils.getInt(txtSoluong.getText());
		if(iSoLuongChiDinh>0 && objCtNhapthuoc.SL_TONKHO <= iSoLuongChiDinh ){
			txtSoluong.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
			txtSoluong.forceFocus();
			txtSoluong.selectAll();
			return;
		}
		else{
			txtSoluong.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		}
		//
		shlChnThucCho.close();
		//
	}

}
