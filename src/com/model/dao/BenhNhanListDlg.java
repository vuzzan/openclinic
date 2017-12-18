/**

*/
package com.model.dao;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
import com.openclinic.utils.Utils;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
public class BenhNhanListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(BenhNhanListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderBenhNhan extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof BenhNhan){
				return ((BenhNhan) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderBenhNhan implements IStructuredContentProvider {
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
	private Table tableBenhNhan;
	private TableViewer tableViewerBenhNhan;
	private List<BenhNhan> listDataBenhNhan;
	private Text textSearchBenhNhan;
	private String textSearchBenhNhanString;
	public BenhNhan objBenhNhan = null;
	public int typeBenhNhanDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgBenhNhan;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public BenhNhanListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(BenhNhanDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("BenhNhan List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objBenhNhan = null;
				}
			}
		});
        
        Composite compositeInShellBenhNhan = new Composite(shell, SWT.NONE);
		compositeInShellBenhNhan.setLayout(new BorderLayout(0, 0));
		compositeInShellBenhNhan.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderBenhNhan = new Composite(compositeInShellBenhNhan, SWT.NONE);
		compositeHeaderBenhNhan.setLayoutData(BorderLayout.NORTH);
		compositeHeaderBenhNhan.setLayout(new GridLayout(5, false));

		textSearchBenhNhan = new Text(compositeHeaderBenhNhan, SWT.BORDER);
		textSearchBenhNhan.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		textSearchBenhNhan.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableBenhNhan();
				}
			}
		});
		
		Button btnNewButtonSearchBenhNhan = new Button(compositeHeaderBenhNhan, SWT.NONE);
		btnNewButtonSearchBenhNhan.setImage(SWTResourceManager.getImage(BenhNhanDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchBenhNhan.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));

		btnNewButtonSearchBenhNhan.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableBenhNhan();
			}
		});
		Button btnNewButtonExportExcelBenhNhan = new Button(compositeHeaderBenhNhan, SWT.NONE);
		btnNewButtonExportExcelBenhNhan.setText("Export Excel");
		btnNewButtonExportExcelBenhNhan.setImage(SWTResourceManager.getImage(KhamBenhListDlg.class, "/png/spreadsheet-2x.png"));
		btnNewButtonExportExcelBenhNhan.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		btnNewButtonExportExcelBenhNhan.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				exportExcelTableBenhNhan();
			}
		});
		
		
		GridData gd_btnNewButtonBenhNhan = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonBenhNhan.widthHint = 87;
		btnNewButtonSearchBenhNhan.setLayoutData(gd_btnNewButtonBenhNhan);
		btnNewButtonSearchBenhNhan.setText("Search");
        
		tableViewerBenhNhan = new TableViewer(compositeInShellBenhNhan, SWT.BORDER | SWT.FULL_SELECTION);
		tableBenhNhan = tableViewerBenhNhan.getTable();
		tableBenhNhan.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NORMAL));
		tableBenhNhan.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableBenhNhan();
                }
                if(e.keyCode==SWT.F4){
					editTableBenhNhan();
                }
				else if(e.keyCode==13){
					selectTableBenhNhan();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableBenhNhan();
				}
                else if(e.keyCode==SWT.F7){
					newItemBenhNhan();
				}
			}
		});
        tableBenhNhan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableBenhNhan();
			}
		});
        
		tableBenhNhan.setLinesVisible(true);
		tableBenhNhan.setHeaderVisible(true);
		tableBenhNhan.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnBenhNhanHO_TEN = new TableColumn(tableBenhNhan, SWT.LEFT);
		tbTableColumnBenhNhanHO_TEN.setWidth(100);
		tbTableColumnBenhNhanHO_TEN.setText("HO_TEN");

		TableColumn tbTableColumnBenhNhanNGAY_SINH = new TableColumn(tableBenhNhan, SWT.LEFT);
		tbTableColumnBenhNhanNGAY_SINH.setWidth(100);
		tbTableColumnBenhNhanNGAY_SINH.setText("NGAY_SINH");

		TableColumn tbTableColumnBenhNhanGIOI_TINH = new TableColumn(tableBenhNhan, SWT.RIGHT);
		tbTableColumnBenhNhanGIOI_TINH.setWidth(100);
		tbTableColumnBenhNhanGIOI_TINH.setText("GIOI_TINH");

		TableColumn tbTableColumnBenhNhanDIA_CHI = new TableColumn(tableBenhNhan, SWT.LEFT);
		tbTableColumnBenhNhanDIA_CHI.setWidth(100);
		tbTableColumnBenhNhanDIA_CHI.setText("DIA_CHI");

		TableColumn tbTableColumnBenhNhanMA_THE = new TableColumn(tableBenhNhan, SWT.LEFT);
		tbTableColumnBenhNhanMA_THE.setWidth(100);
		tbTableColumnBenhNhanMA_THE.setText("MA_THE");

		TableColumn tbTableColumnBenhNhanMA_DKBD = new TableColumn(tableBenhNhan, SWT.LEFT);
		tbTableColumnBenhNhanMA_DKBD.setWidth(100);
		tbTableColumnBenhNhanMA_DKBD.setText("MA_DKBD");

		TableColumn tbTableColumnBenhNhanGT_THE_TU = new TableColumn(tableBenhNhan, SWT.LEFT);
		tbTableColumnBenhNhanGT_THE_TU.setWidth(100);
		tbTableColumnBenhNhanGT_THE_TU.setText("GT_THE_TU");

		TableColumn tbTableColumnBenhNhanGT_THE_DEN = new TableColumn(tableBenhNhan, SWT.LEFT);
		tbTableColumnBenhNhanGT_THE_DEN.setWidth(100);
		tbTableColumnBenhNhanGT_THE_DEN.setText("GT_THE_DEN");

		TableColumn tbTableColumnBenhNhanNGAY_CAP = new TableColumn(tableBenhNhan, SWT.LEFT);
		tbTableColumnBenhNhanNGAY_CAP.setWidth(100);
		tbTableColumnBenhNhanNGAY_CAP.setText("NGAY_CAP");

		TableColumn tbTableColumnBenhNhanMA_QUAN_LY = new TableColumn(tableBenhNhan, SWT.LEFT);
		tbTableColumnBenhNhanMA_QUAN_LY.setWidth(100);
		tbTableColumnBenhNhanMA_QUAN_LY.setText("MA_QUAN_LY");

		TableColumn tbTableColumnBenhNhanTEN_CHA_ME = new TableColumn(tableBenhNhan, SWT.LEFT);
		tbTableColumnBenhNhanTEN_CHA_ME.setWidth(100);
		tbTableColumnBenhNhanTEN_CHA_ME.setText("TEN_CHA_ME");

		TableColumn tbTableColumnBenhNhanMA_DT_SONG = new TableColumn(tableBenhNhan, SWT.RIGHT);
		tbTableColumnBenhNhanMA_DT_SONG.setWidth(100);
		tbTableColumnBenhNhanMA_DT_SONG.setText("MA_DT_SONG");

		TableColumn tbTableColumnBenhNhanTHOIDIEM_NAMNAM = new TableColumn(tableBenhNhan, SWT.LEFT);
		tbTableColumnBenhNhanTHOIDIEM_NAMNAM.setWidth(100);
		tbTableColumnBenhNhanTHOIDIEM_NAMNAM.setText("THOIDIEM_NAMNAM");

		TableColumn tbTableColumnBenhNhanCHUOI_KIEM_TRA = new TableColumn(tableBenhNhan, SWT.LEFT);
		tbTableColumnBenhNhanCHUOI_KIEM_TRA.setWidth(100);
		tbTableColumnBenhNhanCHUOI_KIEM_TRA.setText("CHUOI_KIEM_TRA");


		TableColumn tbTableColumnBenhNhanDATE_ADD = new TableColumn(tableBenhNhan, SWT.NONE);
		tbTableColumnBenhNhanDATE_ADD.setWidth(100);
		tbTableColumnBenhNhanDATE_ADD.setText("DATE_ADD");


		TableColumn tbTableColumnBenhNhanLAST_EDIT = new TableColumn(tableBenhNhan, SWT.NONE);
		tbTableColumnBenhNhanLAST_EDIT.setWidth(100);
		tbTableColumnBenhNhanLAST_EDIT.setText("LAST_EDIT");

		TableColumn tbTableColumnBenhNhanGATE_INFO = new TableColumn(tableBenhNhan, SWT.LEFT);
		tbTableColumnBenhNhanGATE_INFO.setWidth(100);
		tbTableColumnBenhNhanGATE_INFO.setText("GATE_INFO");

		TableColumn tbTableColumnBenhNhanSTS = new TableColumn(tableBenhNhan, SWT.RIGHT);
		tbTableColumnBenhNhanSTS.setWidth(100);
		tbTableColumnBenhNhanSTS.setText("STS");

        Menu menuBenhNhan = new Menu(tableBenhNhan);
		tableBenhNhan.setMenu(menuBenhNhan);
		
		MenuItem mntmNewItemBenhNhan = new MenuItem(menuBenhNhan, SWT.NONE);
		mntmNewItemBenhNhan.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemBenhNhan();
			}
		});
		mntmNewItemBenhNhan.setImage(SWTResourceManager.getImage(BenhNhanDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemBenhNhan.setText("New");
		
		MenuItem mntmEditItemBenhNhan = new MenuItem(menuBenhNhan, SWT.NONE);
		mntmEditItemBenhNhan.setImage(SWTResourceManager.getImage(BenhNhanDlg.class, "/png/wrench-2x.png"));
		mntmEditItemBenhNhan.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTableBenhNhan();
			}
		});
		mntmEditItemBenhNhan.setText("Edit");
		
		MenuItem mntmDeleteBenhNhan = new MenuItem(menuBenhNhan, SWT.NONE);
		mntmDeleteBenhNhan.setImage(SWTResourceManager.getImage(BenhNhanDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteBenhNhan.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableBenhNhan();
			}
		});
		mntmDeleteBenhNhan.setText("Delete");
		
		MenuItem mntmExportBenhNhan = new MenuItem(menuBenhNhan, SWT.NONE);
		mntmExportBenhNhan.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				exportExcelTableBenhNhan();
			}
		});
		mntmExportBenhNhan.setImage(SWTResourceManager.getImage(BenhNhanDlg.class, "/png/spreadsheet-2x.png"));
		mntmExportBenhNhan.setText("Export Excel");
		
		tableViewerBenhNhan.setLabelProvider(new TableLabelProviderBenhNhan());
		tableViewerBenhNhan.setContentProvider(new ContentProviderBenhNhan());
		tableViewerBenhNhan.setInput(listDataBenhNhan);
        //
        //
		loadDataBenhNhan();
		//
        reloadTableBenhNhan();
	}
    public void setDataBenhNhan(String textSearchString){
		this.textSearchBenhNhanString = textSearchString;
	}
	private void loadDataBenhNhan() {
		if(textSearchBenhNhanString!=null){
			textSearchBenhNhan.setText(textSearchBenhNhanString);
		}
	}
	protected void exportExcelTableBenhNhan() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "benh_nhan")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
        if(listDataBenhNhan!=null){
            // Export to EXCEL
    		
			StringBuffer buff_benh_nhan = new StringBuffer();
			String benh_nhan_filename = "benh_nhan_"+Utils.getDatetimeCurent().replaceAll(":", "_")+".xls";
			String delimiter = "</td><td>";
			// Get header...
			// Get header...
			buff_benh_nhan.append( "<table>");
			buff_benh_nhan.append( "<tr class='background-color:#dfdfdf'><td>");

			buff_benh_nhan.append( "HO_TEN" +delimiter);
			buff_benh_nhan.append( "NGAY_SINH" +delimiter);
			buff_benh_nhan.append( "GIOI_TINH" +delimiter);
			buff_benh_nhan.append( "DIA_CHI" +delimiter);
			buff_benh_nhan.append( "MA_THE" +delimiter);
			buff_benh_nhan.append( "MA_DKBD" +delimiter);
			buff_benh_nhan.append( "GT_THE_TU" +delimiter);
			buff_benh_nhan.append( "GT_THE_DEN" +delimiter);
			buff_benh_nhan.append( "NGAY_CAP" +delimiter);
			buff_benh_nhan.append( "MA_QUAN_LY" +delimiter);
			buff_benh_nhan.append( "TEN_CHA_ME" +delimiter);
			buff_benh_nhan.append( "MA_DT_SONG" +delimiter);
			buff_benh_nhan.append( "THOIDIEM_NAMNAM" +delimiter);
			buff_benh_nhan.append( "CHUOI_KIEM_TRA" +delimiter);
			buff_benh_nhan.append( "DATE_ADD" +delimiter);
			buff_benh_nhan.append( "LAST_EDIT" +delimiter);
			buff_benh_nhan.append( "GATE_INFO" +delimiter);
			buff_benh_nhan.append( "STS");
			// End of header
			buff_benh_nhan.append( "</td></tr>");
			buff_benh_nhan.append( "\n");
			// Get data...
			for( BenhNhan obj:  listDataBenhNhan){
				buff_benh_nhan.append( "<tr><td>");
				buff_benh_nhan.append( obj.HO_TEN +delimiter);
				buff_benh_nhan.append( obj.NGAY_SINH +delimiter);
				buff_benh_nhan.append( obj.GIOI_TINH +delimiter);
				buff_benh_nhan.append( obj.DIA_CHI +delimiter);
				buff_benh_nhan.append( obj.MA_THE +delimiter);
				buff_benh_nhan.append( obj.MA_DKBD +delimiter);
				buff_benh_nhan.append( obj.GT_THE_TU +delimiter);
				buff_benh_nhan.append( obj.GT_THE_DEN +delimiter);
				buff_benh_nhan.append( obj.NGAY_CAP +delimiter);
				buff_benh_nhan.append( obj.MA_QUAN_LY +delimiter);
				buff_benh_nhan.append( obj.TEN_CHA_ME +delimiter);
				buff_benh_nhan.append( obj.MA_DT_SONG +delimiter);
				buff_benh_nhan.append( obj.THOIDIEM_NAMNAM +delimiter);
				buff_benh_nhan.append( obj.CHUOI_KIEM_TRA +delimiter);
				buff_benh_nhan.append( obj.DATE_ADD +delimiter);
				buff_benh_nhan.append( obj.LAST_EDIT +delimiter);
				buff_benh_nhan.append( obj.GATE_INFO +delimiter);
				buff_benh_nhan.append( obj.STS );
				// End of header
				buff_benh_nhan.append( "</td></tr>");
			}
			//
			buff_benh_nhan.append( "</table>");
			Writer out = null;
			try {
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(benh_nhan_filename), "UTF-8"));
				out.write('\uFEFF'); // BOM for UTF-*
			    out.write(buff_benh_nhan.toString());
			} 
			catch(Exception ee){
				ee.printStackTrace();
			}
			finally {
			    try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e);
				}
			}
			//
            return;
        }
		// End of export
	}
	protected void reloadTableBenhNhan() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "benh_nhan")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataBenhNhan!=null){
            // 
            tableViewerBenhNhan.setInput(listDataBenhNhan);
            tableViewerBenhNhan.refresh();
            //
            if(listDataBenhNhan.size()==0){
                textSearchBenhNhan.forceFocus();
            }
            else{
                tableBenhNhan.forceFocus();
                tableBenhNhan.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchBenhNhan.getText().toLowerCase().trim();
		String sql = "select * from benh_nhan WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(HO_TEN) like '%"+searchString+"%'";
        sql += " or LOWER(NGAY_SINH) like '%"+searchString+"%'";
        sql += " or LOWER(DIA_CHI) like '%"+searchString+"%'";
        sql += " or LOWER(MA_THE) like '%"+searchString+"%'";
        sql += " or LOWER(MA_DKBD) like '%"+searchString+"%'";
        sql += " or LOWER(GT_THE_TU) like '%"+searchString+"%'";
        sql += " or LOWER(GT_THE_DEN) like '%"+searchString+"%'";
        sql += " or LOWER(NGAY_CAP) like '%"+searchString+"%'";
        sql += " or LOWER(MA_QUAN_LY) like '%"+searchString+"%'";
        sql += " or LOWER(TEN_CHA_ME) like '%"+searchString+"%'";
        sql += " or LOWER(THOIDIEM_NAMNAM) like '%"+searchString+"%'";
        sql += " or LOWER(CHUOI_KIEM_TRA) like '%"+searchString+"%'";
        sql += " or LOWER(GATE_INFO) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataBenhNhan = con.createQuery(sql).executeAndFetch(BenhNhan.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerBenhNhan.setInput(listDataBenhNhan);
		tableViewerBenhNhan.refresh();
        //
        if(listDataBenhNhan.size()==0){
            textSearchBenhNhan.forceFocus();
        }
        else{
            tableBenhNhan.forceFocus();
            tableBenhNhan.setSelection(0);
        }
	}
    
    protected void selectTableBenhNhan() {
		if(tableBenhNhan.getSelectionCount()==0){
			return;
		}
		TableItem item = tableBenhNhan.getSelection()[0];
		BenhNhan obj = (BenhNhan)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typeBenhNhanDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objBenhNhan = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "benh_nhan")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgBenhNhan==TYPE_DLG_VIEW){
                return;
            }
			BenhNhanDlg dlg = new BenhNhanDlg(shell, 0);
            dlg.setBenhNhanDlgData(obj);
            dlg.open();
            //
            reloadTableBenhNhan();
    	}
	}
    protected void editTableBenhNhan() {
        if(intTypeDlgBenhNhan==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "benh_nhan")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tableBenhNhan.getSelectionCount()==0){
			return;
		}
		TableItem item = tableBenhNhan.getSelection()[0];
		BenhNhan obj = (BenhNhan)item.getData();
        logger.info(obj.toString());
        //
        //
		BenhNhanDlg dlg = new BenhNhanDlg(shell, 0);
        dlg.setBenhNhanDlgData(obj);
        dlg.open();
        //
        reloadTableBenhNhan();
	}
    protected void deleteTableBenhNhan() {
        if(intTypeDlgBenhNhan==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "benh_nhan")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tableBenhNhan.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableBenhNhan.getSelection()[0];
		BenhNhan obj = (BenhNhan)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataBenhNhan.remove(obj);
        //
		reloadTableBenhNhan();
	}

	protected void newItemBenhNhan() {
        if(intTypeDlgBenhNhan==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "benh_nhan")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		BenhNhanDlg dlg = new BenhNhanDlg(shell, 0);
        BenhNhan obj = new BenhNhan();
		dlg.setBenhNhanDlgData(obj);
		dlg.open();
        listDataBenhNhan.add(obj);
        //
		reloadTableBenhNhan();
		//
	}
}
