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
public class KhamBenhListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(KhamBenhListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderKhamBenh extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof KhamBenh){
				return ((KhamBenh) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderKhamBenh implements IStructuredContentProvider {
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
	private Table tableKhamBenh;
	private TableViewer tableViewerKhamBenh;
	private List<KhamBenh> listDataKhamBenh;
	private Text textSearchKhamBenh;
	private String textSearchKhamBenhString;
	public KhamBenh objKhamBenh = null;
	public int typeKhamBenhDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgKhamBenh;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public KhamBenhListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(KhamBenhDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("KhamBenh List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objKhamBenh = null;
				}
			}
		});
        
        Composite compositeInShellKhamBenh = new Composite(shell, SWT.NONE);
		compositeInShellKhamBenh.setLayout(new BorderLayout(0, 0));
		compositeInShellKhamBenh.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderKhamBenh = new Composite(compositeInShellKhamBenh, SWT.NONE);
		compositeHeaderKhamBenh.setLayoutData(BorderLayout.NORTH);
		compositeHeaderKhamBenh.setLayout(new GridLayout(2, false));

		textSearchKhamBenh = new Text(compositeHeaderKhamBenh, SWT.BORDER);
		textSearchKhamBenh.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		textSearchKhamBenh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableKhamBenh();
				}
			}
		});
		
		Button btnNewButtonSearchKhamBenh = new Button(compositeHeaderKhamBenh, SWT.NONE);
		btnNewButtonSearchKhamBenh.setImage(SWTResourceManager.getImage(KhamBenhDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchKhamBenh.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnNewButtonSearchKhamBenh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableKhamBenh();
			}
		});
		GridData gd_btnNewButtonKhamBenh = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonKhamBenh.widthHint = 87;
		btnNewButtonSearchKhamBenh.setLayoutData(gd_btnNewButtonKhamBenh);
		btnNewButtonSearchKhamBenh.setText("Search");
        
		tableViewerKhamBenh = new TableViewer(compositeInShellKhamBenh, SWT.BORDER | SWT.FULL_SELECTION);
		tableKhamBenh = tableViewerKhamBenh.getTable();
		tableKhamBenh.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tableKhamBenh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableKhamBenh();
                }
                if(e.keyCode==SWT.F4){
					editTableKhamBenh();
                }
				else if(e.keyCode==13){
					selectTableKhamBenh();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableKhamBenh();
				}
                else if(e.keyCode==SWT.F7){
					newItemKhamBenh();
				}
			}
		});
        tableKhamBenh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableKhamBenh();
			}
		});
        
		tableKhamBenh.setLinesVisible(true);
		tableKhamBenh.setHeaderVisible(true);
		tableKhamBenh.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnKhamBenhSTT = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhSTT.setWidth(100);
		tbTableColumnKhamBenhSTT.setText("STT");

		TableColumn tbTableColumnKhamBenhBN_ID = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhBN_ID.setWidth(100);
		tbTableColumnKhamBenhBN_ID.setText("BN_ID");

		TableColumn tbTableColumnKhamBenhTEN_BENH_NHAN = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhTEN_BENH_NHAN.setWidth(100);
		tbTableColumnKhamBenhTEN_BENH_NHAN.setText("TEN_BENH_NHAN");

		TableColumn tbTableColumnKhamBenhTEN_BENH = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhTEN_BENH.setWidth(100);
		tbTableColumnKhamBenhTEN_BENH.setText("TEN_BENH");

		TableColumn tbTableColumnKhamBenhMA_BENH = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhMA_BENH.setWidth(100);
		tbTableColumnKhamBenhMA_BENH.setText("MA_BENH");

		TableColumn tbTableColumnKhamBenhMA_BENHKHAC = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhMA_BENHKHAC.setWidth(100);
		tbTableColumnKhamBenhMA_BENHKHAC.setText("MA_BENHKHAC");

		TableColumn tbTableColumnKhamBenhMA_LYDO_VVIEN = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhMA_LYDO_VVIEN.setWidth(100);
		tbTableColumnKhamBenhMA_LYDO_VVIEN.setText("MA_LYDO_VVIEN");

		TableColumn tbTableColumnKhamBenhMA_NOI_CHUYEN = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhMA_NOI_CHUYEN.setWidth(100);
		tbTableColumnKhamBenhMA_NOI_CHUYEN.setText("MA_NOI_CHUYEN");

		TableColumn tbTableColumnKhamBenhMA_TAI_NAN = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhMA_TAI_NAN.setWidth(100);
		tbTableColumnKhamBenhMA_TAI_NAN.setText("MA_TAI_NAN");

		TableColumn tbTableColumnKhamBenhNGAY_VAO = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhNGAY_VAO.setWidth(100);
		tbTableColumnKhamBenhNGAY_VAO.setText("NGAY_VAO");

		TableColumn tbTableColumnKhamBenhNGAY_RA = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhNGAY_RA.setWidth(100);
		tbTableColumnKhamBenhNGAY_RA.setText("NGAY_RA");

		TableColumn tbTableColumnKhamBenhSO_NGAY_DTRI = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhSO_NGAY_DTRI.setWidth(100);
		tbTableColumnKhamBenhSO_NGAY_DTRI.setText("SO_NGAY_DTRI");

		TableColumn tbTableColumnKhamBenhKET_QUA_DTRI = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhKET_QUA_DTRI.setWidth(100);
		tbTableColumnKhamBenhKET_QUA_DTRI.setText("KET_QUA_DTRI");

		TableColumn tbTableColumnKhamBenhTINH_TRANG_RV = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhTINH_TRANG_RV.setWidth(100);
		tbTableColumnKhamBenhTINH_TRANG_RV.setText("TINH_TRANG_RV");

		TableColumn tbTableColumnKhamBenhNGAY_TTOAN = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhNGAY_TTOAN.setWidth(100);
		tbTableColumnKhamBenhNGAY_TTOAN.setText("NGAY_TTOAN");

		TableColumn tbTableColumnKhamBenhMUC_HUONG = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhMUC_HUONG.setWidth(100);
		tbTableColumnKhamBenhMUC_HUONG.setText("MUC_HUONG");

		TableColumn tbTableColumnKhamBenhT_THUOC = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhT_THUOC.setWidth(100);
		tbTableColumnKhamBenhT_THUOC.setText("T_THUOC");

		TableColumn tbTableColumnKhamBenhT_VTYT = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhT_VTYT.setWidth(100);
		tbTableColumnKhamBenhT_VTYT.setText("T_VTYT");

		TableColumn tbTableColumnKhamBenhT_TONGCHI = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhT_TONGCHI.setWidth(100);
		tbTableColumnKhamBenhT_TONGCHI.setText("T_TONGCHI");

		TableColumn tbTableColumnKhamBenhT_BNTT = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhT_BNTT.setWidth(100);
		tbTableColumnKhamBenhT_BNTT.setText("T_BNTT");

		TableColumn tbTableColumnKhamBenhT_BHTT = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhT_BHTT.setWidth(100);
		tbTableColumnKhamBenhT_BHTT.setText("T_BHTT");

		TableColumn tbTableColumnKhamBenhT_NGUONKHAC = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhT_NGUONKHAC.setWidth(100);
		tbTableColumnKhamBenhT_NGUONKHAC.setText("T_NGUONKHAC");

		TableColumn tbTableColumnKhamBenhT_NGOAIDS = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhT_NGOAIDS.setWidth(100);
		tbTableColumnKhamBenhT_NGOAIDS.setText("T_NGOAIDS");

		TableColumn tbTableColumnKhamBenhNAM_QT = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhNAM_QT.setWidth(100);
		tbTableColumnKhamBenhNAM_QT.setText("NAM_QT");

		TableColumn tbTableColumnKhamBenhTHANG_QT = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhTHANG_QT.setWidth(100);
		tbTableColumnKhamBenhTHANG_QT.setText("THANG_QT");

		TableColumn tbTableColumnKhamBenhMA_LOAI_KCB = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhMA_LOAI_KCB.setWidth(100);
		tbTableColumnKhamBenhMA_LOAI_KCB.setText("MA_LOAI_KCB");

		TableColumn tbTableColumnKhamBenhMA_KHOA = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhMA_KHOA.setWidth(100);
		tbTableColumnKhamBenhMA_KHOA.setText("MA_KHOA");

		TableColumn tbTableColumnKhamBenhMA_CSKCB = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhMA_CSKCB.setWidth(100);
		tbTableColumnKhamBenhMA_CSKCB.setText("MA_CSKCB");

		TableColumn tbTableColumnKhamBenhMA_KHUVUC = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhMA_KHUVUC.setWidth(100);
		tbTableColumnKhamBenhMA_KHUVUC.setText("MA_KHUVUC");

		TableColumn tbTableColumnKhamBenhMA_PTTT_QT = new TableColumn(tableKhamBenh, SWT.LEFT);
		tbTableColumnKhamBenhMA_PTTT_QT.setWidth(100);
		tbTableColumnKhamBenhMA_PTTT_QT.setText("MA_PTTT_QT");


		TableColumn tbTableColumnKhamBenhCAN_NANG = new TableColumn(tableKhamBenh, SWT.NONE);
		tbTableColumnKhamBenhCAN_NANG.setWidth(100);
		tbTableColumnKhamBenhCAN_NANG.setText("CAN_NANG");


		TableColumn tbTableColumnKhamBenhKB_DATE = new TableColumn(tableKhamBenh, SWT.NONE);
		tbTableColumnKhamBenhKB_DATE.setWidth(100);
		tbTableColumnKhamBenhKB_DATE.setText("KB_DATE");

		TableColumn tbTableColumnKhamBenhKIEU_TT = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhKIEU_TT.setWidth(100);
		tbTableColumnKhamBenhKIEU_TT.setText("KIEU_TT");

		TableColumn tbTableColumnKhamBenhSTS = new TableColumn(tableKhamBenh, SWT.RIGHT);
		tbTableColumnKhamBenhSTS.setWidth(100);
		tbTableColumnKhamBenhSTS.setText("STS");

        Menu menuKhamBenh = new Menu(tableKhamBenh);
		tableKhamBenh.setMenu(menuKhamBenh);
		
		MenuItem mntmNewItemKhamBenh = new MenuItem(menuKhamBenh, SWT.NONE);
		mntmNewItemKhamBenh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemKhamBenh();
			}
		});
		mntmNewItemKhamBenh.setImage(SWTResourceManager.getImage(KhamBenhDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemKhamBenh.setText("New");
		
		MenuItem mntmEditItemKhamBenh = new MenuItem(menuKhamBenh, SWT.NONE);
		mntmEditItemKhamBenh.setImage(SWTResourceManager.getImage(KhamBenhDlg.class, "/png/wrench-2x.png"));
		mntmEditItemKhamBenh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTableKhamBenh();
			}
		});
		mntmEditItemKhamBenh.setText("Edit");
		
		MenuItem mntmDeleteKhamBenh = new MenuItem(menuKhamBenh, SWT.NONE);
		mntmDeleteKhamBenh.setImage(SWTResourceManager.getImage(KhamBenhDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteKhamBenh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableKhamBenh();
			}
		});
		mntmDeleteKhamBenh.setText("Delete");

		tableViewerKhamBenh.setLabelProvider(new TableLabelProviderKhamBenh());
		tableViewerKhamBenh.setContentProvider(new ContentProviderKhamBenh());
		tableViewerKhamBenh.setInput(listDataKhamBenh);
        //
        //
		loadDataKhamBenh();
		//
        reloadTableKhamBenh();
	}
    public void setDataKhamBenh(String textSearchString){
		this.textSearchKhamBenhString = textSearchString;
	}
	private void loadDataKhamBenh() {
		if(textSearchKhamBenhString!=null){
			textSearchKhamBenh.setText(textSearchKhamBenhString);
		}
	}
	protected void reloadTableKhamBenh() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "kham_benh")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataKhamBenh!=null){
            // 
            tableViewerKhamBenh.setInput(listDataKhamBenh);
            tableViewerKhamBenh.refresh();
            //
            if(listDataKhamBenh.size()==0){
                textSearchKhamBenh.forceFocus();
            }
            else{
                tableKhamBenh.forceFocus();
                tableKhamBenh.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchKhamBenh.getText().toLowerCase().trim();
		String sql = "select * from kham_benh WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(TEN_BENH_NHAN) like '%"+searchString+"%'";
        sql += " or LOWER(TEN_BENH) like '%"+searchString+"%'";
        sql += " or LOWER(MA_BENH) like '%"+searchString+"%'";
        sql += " or LOWER(MA_BENHKHAC) like '%"+searchString+"%'";
        sql += " or LOWER(MA_NOI_CHUYEN) like '%"+searchString+"%'";
        sql += " or LOWER(NGAY_VAO) like '%"+searchString+"%'";
        sql += " or LOWER(NGAY_RA) like '%"+searchString+"%'";
        sql += " or LOWER(NGAY_TTOAN) like '%"+searchString+"%'";
        sql += " or LOWER(MA_KHOA) like '%"+searchString+"%'";
        sql += " or LOWER(MA_CSKCB) like '%"+searchString+"%'";
        sql += " or LOWER(MA_KHUVUC) like '%"+searchString+"%'";
        sql += " or LOWER(MA_PTTT_QT) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataKhamBenh = con.createQuery(sql).executeAndFetch(KhamBenh.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerKhamBenh.setInput(listDataKhamBenh);
		tableViewerKhamBenh.refresh();
        //
        if(listDataKhamBenh.size()==0){
            textSearchKhamBenh.forceFocus();
        }
        else{
            tableKhamBenh.forceFocus();
            tableKhamBenh.setSelection(0);
        }
	}
    
    protected void selectTableKhamBenh() {
		if(tableKhamBenh.getSelectionCount()==0){
			return;
		}
		TableItem item = tableKhamBenh.getSelection()[0];
		KhamBenh obj = (KhamBenh)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typeKhamBenhDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objKhamBenh = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "kham_benh")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgKhamBenh==TYPE_DLG_VIEW){
                return;
            }
			KhamBenhDlg dlg = new KhamBenhDlg(shell, 0);
            dlg.setKhamBenhDlgData(obj);
            dlg.open();
            //
            reloadTableKhamBenh();
    	}
	}
    protected void editTableKhamBenh() {
        if(intTypeDlgKhamBenh==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "kham_benh")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tableKhamBenh.getSelectionCount()==0){
			return;
		}
		TableItem item = tableKhamBenh.getSelection()[0];
		KhamBenh obj = (KhamBenh)item.getData();
        logger.info(obj.toString());
        //
        //
		KhamBenhDlg dlg = new KhamBenhDlg(shell, 0);
        dlg.setKhamBenhDlgData(obj);
        dlg.open();
        //
        reloadTableKhamBenh();
	}
    protected void deleteTableKhamBenh() {
        if(intTypeDlgKhamBenh==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "kham_benh")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tableKhamBenh.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableKhamBenh.getSelection()[0];
		KhamBenh obj = (KhamBenh)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataKhamBenh.remove(obj);
        //
		reloadTableKhamBenh();
	}

	protected void newItemKhamBenh() {
        if(intTypeDlgKhamBenh==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "kham_benh")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		KhamBenhDlg dlg = new KhamBenhDlg(shell, 0);
        KhamBenh obj = new KhamBenh();
		dlg.setKhamBenhDlgData(obj);
		dlg.open();
        listDataKhamBenh.add(obj);
        //
		reloadTableKhamBenh();
		//
	}
}
