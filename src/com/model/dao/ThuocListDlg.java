/**

*/
package com.model.dao;

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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
public class ThuocListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(ThuocListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderThuoc extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof Thuoc){
				return ((Thuoc) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderThuoc implements IStructuredContentProvider {
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
	private Table tableThuoc;
	private TableViewer tableViewerThuoc;
	private List<Thuoc> listDataThuoc;
	private Text textSearchThuoc;
	private String textSearchThuocString;
	public Thuoc objThuoc = null;
	public int typeThuocDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgThuoc;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ThuocListDlg(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
        //
        Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
        
		while (!shell.isDisposed()) {
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
		shell = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER | SWT.PRIMARY_MODAL);
		shell.setImage(SWTResourceManager.getImage(ThuocDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("Thuoc List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objThuoc = null;
				}
			}
		});
        
        Composite compositeInShellThuoc = new Composite(shell, SWT.NONE);
		compositeInShellThuoc.setLayout(new BorderLayout(0, 0));
		compositeInShellThuoc.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderThuoc = new Composite(compositeInShellThuoc, SWT.NONE);
		compositeHeaderThuoc.setLayoutData(BorderLayout.NORTH);
		compositeHeaderThuoc.setLayout(new GridLayout(2, false));

		textSearchThuoc = new Text(compositeHeaderThuoc, SWT.BORDER);
		textSearchThuoc.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		textSearchThuoc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableThuoc();
				}
			}
		});
		
		Button btnNewButtonSearchThuoc = new Button(compositeHeaderThuoc, SWT.NONE);
		btnNewButtonSearchThuoc.setImage(SWTResourceManager.getImage(ThuocDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchThuoc.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnNewButtonSearchThuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableThuoc();
			}
		});
		GridData gd_btnNewButtonThuoc = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonThuoc.widthHint = 87;
		btnNewButtonSearchThuoc.setLayoutData(gd_btnNewButtonThuoc);
		btnNewButtonSearchThuoc.setText("Search");
        
		tableViewerThuoc = new TableViewer(compositeInShellThuoc, SWT.BORDER | SWT.FULL_SELECTION);
		tableThuoc = tableViewerThuoc.getTable();
		tableThuoc.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tableThuoc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableThuoc();
                }
                if(e.keyCode==SWT.F4){
					editTableThuoc();
                }
				else if(e.keyCode==13){
					selectTableThuoc();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableThuoc();
				}
                else if(e.keyCode==SWT.F7){
					newItemThuoc();
				}
			}
		});
        tableThuoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableThuoc();
			}
		});
        
		tableThuoc.setLinesVisible(true);
		tableThuoc.setHeaderVisible(true);
		tableThuoc.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnThuocMA_HOAT_CHAT = new TableColumn(tableThuoc, SWT.LEFT);
		tbTableColumnThuocMA_HOAT_CHAT.setWidth(100);
		tbTableColumnThuocMA_HOAT_CHAT.setText("MA_HOAT_CHAT");

		TableColumn tbTableColumnThuocMA_AX = new TableColumn(tableThuoc, SWT.LEFT);
		tbTableColumnThuocMA_AX.setWidth(100);
		tbTableColumnThuocMA_AX.setText("MA_AX");

		TableColumn tbTableColumnThuocHOAT_CHAT = new TableColumn(tableThuoc, SWT.LEFT);
		tbTableColumnThuocHOAT_CHAT.setWidth(100);
		tbTableColumnThuocHOAT_CHAT.setText("HOAT_CHAT");

		TableColumn tbTableColumnThuocHOATCHAT_AX = new TableColumn(tableThuoc, SWT.LEFT);
		tbTableColumnThuocHOATCHAT_AX.setWidth(100);
		tbTableColumnThuocHOATCHAT_AX.setText("HOATCHAT_AX");

		TableColumn tbTableColumnThuocMA_DUONG_DUNG = new TableColumn(tableThuoc, SWT.LEFT);
		tbTableColumnThuocMA_DUONG_DUNG.setWidth(100);
		tbTableColumnThuocMA_DUONG_DUNG.setText("MA_DUONG_DUNG");

		TableColumn tbTableColumnThuocMA_DUONGDUNG_AX = new TableColumn(tableThuoc, SWT.LEFT);
		tbTableColumnThuocMA_DUONGDUNG_AX.setWidth(100);
		tbTableColumnThuocMA_DUONGDUNG_AX.setText("MA_DUONGDUNG_AX");

		TableColumn tbTableColumnThuocDUONG_DUNG = new TableColumn(tableThuoc, SWT.LEFT);
		tbTableColumnThuocDUONG_DUNG.setWidth(100);
		tbTableColumnThuocDUONG_DUNG.setText("DUONG_DUNG");

		TableColumn tbTableColumnThuocDUONGDUNG_AX = new TableColumn(tableThuoc, SWT.LEFT);
		tbTableColumnThuocDUONGDUNG_AX.setWidth(100);
		tbTableColumnThuocDUONGDUNG_AX.setText("DUONGDUNG_AX");

		TableColumn tbTableColumnThuocHAM_LUONG = new TableColumn(tableThuoc, SWT.LEFT);
		tbTableColumnThuocHAM_LUONG.setWidth(100);
		tbTableColumnThuocHAM_LUONG.setText("HAM_LUONG");

		TableColumn tbTableColumnThuocHAMLUONG_AX = new TableColumn(tableThuoc, SWT.LEFT);
		tbTableColumnThuocHAMLUONG_AX.setWidth(100);
		tbTableColumnThuocHAMLUONG_AX.setText("HAMLUONG_AX");

		TableColumn tbTableColumnThuocTEN_THUOC = new TableColumn(tableThuoc, SWT.LEFT);
		tbTableColumnThuocTEN_THUOC.setWidth(100);
		tbTableColumnThuocTEN_THUOC.setText("TEN_THUOC");

		TableColumn tbTableColumnThuocTENTHUOC_AX = new TableColumn(tableThuoc, SWT.LEFT);
		tbTableColumnThuocTENTHUOC_AX.setWidth(100);
		tbTableColumnThuocTENTHUOC_AX.setText("TENTHUOC_AX");

		TableColumn tbTableColumnThuocSO_DANG_KY = new TableColumn(tableThuoc, SWT.LEFT);
		tbTableColumnThuocSO_DANG_KY.setWidth(100);
		tbTableColumnThuocSO_DANG_KY.setText("SO_DANG_KY");

		TableColumn tbTableColumnThuocSODANGKY_AX = new TableColumn(tableThuoc, SWT.LEFT);
		tbTableColumnThuocSODANGKY_AX.setWidth(100);
		tbTableColumnThuocSODANGKY_AX.setText("SODANGKY_AX");

		TableColumn tbTableColumnThuocDONG_GOI = new TableColumn(tableThuoc, SWT.LEFT);
		tbTableColumnThuocDONG_GOI.setWidth(100);
		tbTableColumnThuocDONG_GOI.setText("DONG_GOI");

		TableColumn tbTableColumnThuocDON_VI_TINH = new TableColumn(tableThuoc, SWT.LEFT);
		tbTableColumnThuocDON_VI_TINH.setWidth(100);
		tbTableColumnThuocDON_VI_TINH.setText("DON_VI_TINH");

		TableColumn tbTableColumnThuocDON_GIA = new TableColumn(tableThuoc, SWT.RIGHT);
		tbTableColumnThuocDON_GIA.setWidth(100);
		tbTableColumnThuocDON_GIA.setText("DON_GIA");

		TableColumn tbTableColumnThuocDON_GIA_TT = new TableColumn(tableThuoc, SWT.RIGHT);
		tbTableColumnThuocDON_GIA_TT.setWidth(100);
		tbTableColumnThuocDON_GIA_TT.setText("DON_GIA_TT");

		TableColumn tbTableColumnThuocSO_LUONG = new TableColumn(tableThuoc, SWT.RIGHT);
		tbTableColumnThuocSO_LUONG.setWidth(100);
		tbTableColumnThuocSO_LUONG.setText("SO_LUONG");

		TableColumn tbTableColumnThuocMA_CSKCB = new TableColumn(tableThuoc, SWT.LEFT);
		tbTableColumnThuocMA_CSKCB.setWidth(100);
		tbTableColumnThuocMA_CSKCB.setText("MA_CSKCB");

		TableColumn tbTableColumnThuocHANG_SX = new TableColumn(tableThuoc, SWT.LEFT);
		tbTableColumnThuocHANG_SX.setWidth(100);
		tbTableColumnThuocHANG_SX.setText("HANG_SX");

		TableColumn tbTableColumnThuocNUOC_SX = new TableColumn(tableThuoc, SWT.LEFT);
		tbTableColumnThuocNUOC_SX.setWidth(100);
		tbTableColumnThuocNUOC_SX.setText("NUOC_SX");

		TableColumn tbTableColumnThuocNHA_THAU = new TableColumn(tableThuoc, SWT.LEFT);
		tbTableColumnThuocNHA_THAU.setWidth(100);
		tbTableColumnThuocNHA_THAU.setText("NHA_THAU");

		TableColumn tbTableColumnThuocQUYET_DINH = new TableColumn(tableThuoc, SWT.LEFT);
		tbTableColumnThuocQUYET_DINH.setWidth(100);
		tbTableColumnThuocQUYET_DINH.setText("QUYET_DINH");

		TableColumn tbTableColumnThuocCONG_BO = new TableColumn(tableThuoc, SWT.LEFT);
		tbTableColumnThuocCONG_BO.setWidth(100);
		tbTableColumnThuocCONG_BO.setText("CONG_BO");

		TableColumn tbTableColumnThuocMA_THUOC_BV = new TableColumn(tableThuoc, SWT.LEFT);
		tbTableColumnThuocMA_THUOC_BV.setWidth(100);
		tbTableColumnThuocMA_THUOC_BV.setText("MA_THUOC_BV");

		TableColumn tbTableColumnThuocLOAI_THUOC = new TableColumn(tableThuoc, SWT.RIGHT);
		tbTableColumnThuocLOAI_THUOC.setWidth(100);
		tbTableColumnThuocLOAI_THUOC.setText("LOAI_THUOC");

		TableColumn tbTableColumnThuocLOAI_THAU = new TableColumn(tableThuoc, SWT.RIGHT);
		tbTableColumnThuocLOAI_THAU.setWidth(100);
		tbTableColumnThuocLOAI_THAU.setText("LOAI_THAU");

		TableColumn tbTableColumnThuocNHOM_THAU = new TableColumn(tableThuoc, SWT.LEFT);
		tbTableColumnThuocNHOM_THAU.setWidth(100);
		tbTableColumnThuocNHOM_THAU.setText("NHOM_THAU");

		TableColumn tbTableColumnThuocMANHOM_9324 = new TableColumn(tableThuoc, SWT.RIGHT);
		tbTableColumnThuocMANHOM_9324.setWidth(100);
		tbTableColumnThuocMANHOM_9324.setText("MANHOM_9324");

		TableColumn tbTableColumnThuocHIEULUC = new TableColumn(tableThuoc, SWT.RIGHT);
		tbTableColumnThuocHIEULUC.setWidth(100);
		tbTableColumnThuocHIEULUC.setText("HIEULUC");

		TableColumn tbTableColumnThuocKETQUA = new TableColumn(tableThuoc, SWT.RIGHT);
		tbTableColumnThuocKETQUA.setWidth(100);
		tbTableColumnThuocKETQUA.setText("KETQUA");

		TableColumn tbTableColumnThuocTHUOC_RANK = new TableColumn(tableThuoc, SWT.RIGHT);
		tbTableColumnThuocTHUOC_RANK.setWidth(100);
		tbTableColumnThuocTHUOC_RANK.setText("THUOC_RANK");

        Menu menuThuoc = new Menu(tableThuoc);
		tableThuoc.setMenu(menuThuoc);
		
		MenuItem mntmNewItemThuoc = new MenuItem(menuThuoc, SWT.NONE);
		mntmNewItemThuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemThuoc();
			}
		});
		mntmNewItemThuoc.setImage(SWTResourceManager.getImage(ThuocDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemThuoc.setText("New");
		
		MenuItem mntmEditItemThuoc = new MenuItem(menuThuoc, SWT.NONE);
		mntmEditItemThuoc.setImage(SWTResourceManager.getImage(ThuocDlg.class, "/png/wrench-2x.png"));
		mntmEditItemThuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTableThuoc();
			}
		});
		mntmEditItemThuoc.setText("Edit");
		
		MenuItem mntmDeleteThuoc = new MenuItem(menuThuoc, SWT.NONE);
		mntmDeleteThuoc.setImage(SWTResourceManager.getImage(ThuocDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteThuoc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableThuoc();
			}
		});
		mntmDeleteThuoc.setText("Delete");

		tableViewerThuoc.setLabelProvider(new TableLabelProviderThuoc());
		tableViewerThuoc.setContentProvider(new ContentProviderThuoc());
		tableViewerThuoc.setInput(listDataThuoc);
        //
        //
		loadDataThuoc();
		//
        reloadTableThuoc();
	}
    public void setDataThuoc(String textSearchString){
		this.textSearchThuocString = textSearchString;
	}
	private void loadDataThuoc() {
		if(textSearchThuocString!=null){
			textSearchThuoc.setText(textSearchThuocString);
		}
	}
	protected void reloadTableThuoc() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "thuoc")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataThuoc!=null){
            // 
            tableViewerThuoc.setInput(listDataThuoc);
            tableViewerThuoc.refresh();
            //
            if(listDataThuoc.size()==0){
                textSearchThuoc.forceFocus();
            }
            else{
                tableThuoc.forceFocus();
                tableThuoc.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchThuoc.getText().toLowerCase().trim();
		String sql = "select * from thuoc WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(MA_HOAT_CHAT) like '%"+searchString+"%'";
        sql += " or LOWER(MA_AX) like '%"+searchString+"%'";
        sql += " or LOWER(HOAT_CHAT) like '%"+searchString+"%'";
        sql += " or LOWER(HOATCHAT_AX) like '%"+searchString+"%'";
        sql += " or LOWER(MA_DUONG_DUNG) like '%"+searchString+"%'";
        sql += " or LOWER(MA_DUONGDUNG_AX) like '%"+searchString+"%'";
        sql += " or LOWER(DUONG_DUNG) like '%"+searchString+"%'";
        sql += " or LOWER(DUONGDUNG_AX) like '%"+searchString+"%'";
        sql += " or LOWER(HAM_LUONG) like '%"+searchString+"%'";
        sql += " or LOWER(HAMLUONG_AX) like '%"+searchString+"%'";
        sql += " or LOWER(TEN_THUOC) like '%"+searchString+"%'";
        sql += " or LOWER(TENTHUOC_AX) like '%"+searchString+"%'";
        sql += " or LOWER(SO_DANG_KY) like '%"+searchString+"%'";
        sql += " or LOWER(SODANGKY_AX) like '%"+searchString+"%'";
        sql += " or LOWER(DONG_GOI) like '%"+searchString+"%'";
        sql += " or LOWER(DON_VI_TINH) like '%"+searchString+"%'";
        sql += " or LOWER(MA_CSKCB) like '%"+searchString+"%'";
        sql += " or LOWER(HANG_SX) like '%"+searchString+"%'";
        sql += " or LOWER(NUOC_SX) like '%"+searchString+"%'";
        sql += " or LOWER(NHA_THAU) like '%"+searchString+"%'";
        sql += " or LOWER(QUYET_DINH) like '%"+searchString+"%'";
        sql += " or LOWER(CONG_BO) like '%"+searchString+"%'";
        sql += " or LOWER(MA_THUOC_BV) like '%"+searchString+"%'";
        sql += " or LOWER(NHOM_THAU) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataThuoc = con.createQuery(sql).executeAndFetch(Thuoc.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerThuoc.setInput(listDataThuoc);
		tableViewerThuoc.refresh();
        //
        if(listDataThuoc.size()==0){
            textSearchThuoc.forceFocus();
        }
        else{
            tableThuoc.forceFocus();
            tableThuoc.setSelection(0);
        }
	}
    
    protected void selectTableThuoc() {
		if(tableThuoc.getSelectionCount()==0){
			return;
		}
		TableItem item = tableThuoc.getSelection()[0];
		Thuoc obj = (Thuoc)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typeThuocDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objThuoc = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "thuoc")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgThuoc==TYPE_DLG_VIEW){
                return;
            }
			ThuocDlg dlg = new ThuocDlg(shell, 0);
            dlg.setThuocDlgData(obj);
            dlg.open();
            //
            reloadTableThuoc();
    	}
	}
    protected void editTableThuoc() {
        if(intTypeDlgThuoc==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "thuoc")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tableThuoc.getSelectionCount()==0){
			return;
		}
		TableItem item = tableThuoc.getSelection()[0];
		Thuoc obj = (Thuoc)item.getData();
        logger.info(obj.toString());
        //
        //
		ThuocDlg dlg = new ThuocDlg(shell, 0);
        dlg.setThuocDlgData(obj);
        dlg.open();
        //
        reloadTableThuoc();
	}
    protected void deleteTableThuoc() {
        if(intTypeDlgThuoc==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "thuoc")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tableThuoc.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableThuoc.getSelection()[0];
		Thuoc obj = (Thuoc)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataThuoc.remove(obj);
        //
		reloadTableThuoc();
	}

	protected void newItemThuoc() {
        if(intTypeDlgThuoc==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "thuoc")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		ThuocDlg dlg = new ThuocDlg(shell, 0);
        Thuoc obj = new Thuoc();
		dlg.setThuocDlgData(obj);
		dlg.open();
        listDataThuoc.add(obj);
        //
		reloadTableThuoc();
		//
	}
}
