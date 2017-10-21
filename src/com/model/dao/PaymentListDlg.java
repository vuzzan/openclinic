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
public class PaymentListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(PaymentListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderPayment extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof Payment){
				return ((Payment) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderPayment implements IStructuredContentProvider {
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
	private Table tablePayment;
	private TableViewer tableViewerPayment;
	private List<Payment> listDataPayment;
	private Text textSearchPayment;
	private String textSearchPaymentString;
	public Payment objPayment = null;
	public int typePaymentDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgPayment;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public PaymentListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(PaymentDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("Payment List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objPayment = null;
				}
			}
		});
        
        Composite compositeInShellPayment = new Composite(shell, SWT.NONE);
		compositeInShellPayment.setLayout(new BorderLayout(0, 0));
		compositeInShellPayment.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderPayment = new Composite(compositeInShellPayment, SWT.NONE);
		compositeHeaderPayment.setLayoutData(BorderLayout.NORTH);
		compositeHeaderPayment.setLayout(new GridLayout(2, false));

		textSearchPayment = new Text(compositeHeaderPayment, SWT.BORDER);
		textSearchPayment.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		textSearchPayment.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTablePayment();
				}
			}
		});
		
		Button btnNewButtonSearchPayment = new Button(compositeHeaderPayment, SWT.NONE);
		btnNewButtonSearchPayment.setImage(SWTResourceManager.getImage(PaymentDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchPayment.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnNewButtonSearchPayment.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTablePayment();
			}
		});
		GridData gd_btnNewButtonPayment = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonPayment.widthHint = 87;
		btnNewButtonSearchPayment.setLayoutData(gd_btnNewButtonPayment);
		btnNewButtonSearchPayment.setText("Search");
        
		tableViewerPayment = new TableViewer(compositeInShellPayment, SWT.BORDER | SWT.FULL_SELECTION);
		tablePayment = tableViewerPayment.getTable();
		tablePayment.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tablePayment.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTablePayment();
                }
                if(e.keyCode==SWT.F4){
					editTablePayment();
                }
				else if(e.keyCode==13){
					selectTablePayment();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTablePayment();
				}
                else if(e.keyCode==SWT.F7){
					newItemPayment();
				}
			}
		});
        tablePayment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTablePayment();
			}
		});
        
		tablePayment.setLinesVisible(true);
		tablePayment.setHeaderVisible(true);
		tablePayment.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnPaymentHOADON = new TableColumn(tablePayment, SWT.LEFT);
		tbTableColumnPaymentHOADON.setWidth(100);
		tbTableColumnPaymentHOADON.setText("HOADON");

		TableColumn tbTableColumnPaymentTONGCONG = new TableColumn(tablePayment, SWT.RIGHT);
		tbTableColumnPaymentTONGCONG.setWidth(100);
		tbTableColumnPaymentTONGCONG.setText("TONGCONG");

		TableColumn tbTableColumnPaymentV_ID = new TableColumn(tablePayment, SWT.RIGHT);
		tbTableColumnPaymentV_ID.setWidth(100);
		tbTableColumnPaymentV_ID.setText("V_ID");


		TableColumn tbTableColumnPaymentNGAY_GIO = new TableColumn(tablePayment, SWT.NONE);
		tbTableColumnPaymentNGAY_GIO.setWidth(100);
		tbTableColumnPaymentNGAY_GIO.setText("NGAY_GIO");

		TableColumn tbTableColumnPaymentSTS = new TableColumn(tablePayment, SWT.RIGHT);
		tbTableColumnPaymentSTS.setWidth(100);
		tbTableColumnPaymentSTS.setText("STS");

        Menu menuPayment = new Menu(tablePayment);
		tablePayment.setMenu(menuPayment);
		
		MenuItem mntmNewItemPayment = new MenuItem(menuPayment, SWT.NONE);
		mntmNewItemPayment.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemPayment();
			}
		});
		mntmNewItemPayment.setImage(SWTResourceManager.getImage(PaymentDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemPayment.setText("New");
		
		MenuItem mntmEditItemPayment = new MenuItem(menuPayment, SWT.NONE);
		mntmEditItemPayment.setImage(SWTResourceManager.getImage(PaymentDlg.class, "/png/wrench-2x.png"));
		mntmEditItemPayment.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTablePayment();
			}
		});
		mntmEditItemPayment.setText("Edit");
		
		MenuItem mntmDeletePayment = new MenuItem(menuPayment, SWT.NONE);
		mntmDeletePayment.setImage(SWTResourceManager.getImage(PaymentDlg.class, "/png/circle-x-2x.png"));
		mntmDeletePayment.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTablePayment();
			}
		});
		mntmDeletePayment.setText("Delete");

		tableViewerPayment.setLabelProvider(new TableLabelProviderPayment());
		tableViewerPayment.setContentProvider(new ContentProviderPayment());
		tableViewerPayment.setInput(listDataPayment);
        //
        //
		loadDataPayment();
		//
        reloadTablePayment();
	}
    public void setDataPayment(String textSearchString){
		this.textSearchPaymentString = textSearchString;
	}
	private void loadDataPayment() {
		if(textSearchPaymentString!=null){
			textSearchPayment.setText(textSearchPaymentString);
		}
	}
	protected void reloadTablePayment() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "payment")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataPayment!=null){
            // 
            tableViewerPayment.setInput(listDataPayment);
            tableViewerPayment.refresh();
            //
            if(listDataPayment.size()==0){
                textSearchPayment.forceFocus();
            }
            else{
                tablePayment.forceFocus();
                tablePayment.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchPayment.getText().toLowerCase().trim();
		String sql = "select * from payment WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(HOADON) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataPayment = con.createQuery(sql).executeAndFetch(Payment.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerPayment.setInput(listDataPayment);
		tableViewerPayment.refresh();
        //
        if(listDataPayment.size()==0){
            textSearchPayment.forceFocus();
        }
        else{
            tablePayment.forceFocus();
            tablePayment.setSelection(0);
        }
	}
    
    protected void selectTablePayment() {
		if(tablePayment.getSelectionCount()==0){
			return;
		}
		TableItem item = tablePayment.getSelection()[0];
		Payment obj = (Payment)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typePaymentDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objPayment = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "payment")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgPayment==TYPE_DLG_VIEW){
                return;
            }
			PaymentDlg dlg = new PaymentDlg(shell, 0);
            dlg.setPaymentDlgData(obj);
            dlg.open();
            //
            reloadTablePayment();
    	}
	}
    protected void editTablePayment() {
        if(intTypeDlgPayment==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "payment")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tablePayment.getSelectionCount()==0){
			return;
		}
		TableItem item = tablePayment.getSelection()[0];
		Payment obj = (Payment)item.getData();
        logger.info(obj.toString());
        //
        //
		PaymentDlg dlg = new PaymentDlg(shell, 0);
        dlg.setPaymentDlgData(obj);
        dlg.open();
        //
        reloadTablePayment();
	}
    protected void deleteTablePayment() {
        if(intTypeDlgPayment==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "payment")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tablePayment.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tablePayment.getSelection()[0];
		Payment obj = (Payment)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataPayment.remove(obj);
        //
		reloadTablePayment();
	}

	protected void newItemPayment() {
        if(intTypeDlgPayment==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "payment")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		PaymentDlg dlg = new PaymentDlg(shell, 0);
        Payment obj = new Payment();
		dlg.setPaymentDlgData(obj);
		dlg.open();
        listDataPayment.add(obj);
        //
		reloadTablePayment();
		//
	}
}
