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
public class ConfigurationListDlg extends Dialog {
	static Logger logger = LogManager.getLogger(ConfigurationListDlg.class.getName());
	protected Object result;
	protected Shell shell;

	private class TableLabelProviderConfiguration extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(element instanceof Configuration){
				return ((Configuration) element).getIndex(columnIndex);
			}
			return "";
		}
	}
	private static class ContentProviderConfiguration implements IStructuredContentProvider {
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
	private Table tableConfiguration;
	private TableViewer tableViewerConfiguration;
	private List<Configuration> listDataConfiguration;
	private Text textSearchConfiguration;
	private String textSearchConfigurationString;
	public Configuration objConfiguration = null;
	public int typeConfigurationDlg = TYPE_DLG_CHOOSEN; 
	public static final int TYPE_DLG_MANAGER = 0;
	public static final int TYPE_DLG_CHOOSEN = 1;

    public int intTypeDlgConfiguration;
	public static final int TYPE_DLG_VIEW = 1;
	public static final int TYPE_DLG_EDIT = 2;
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ConfigurationListDlg(Shell parent, int style) {
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
		shell.setImage(SWTResourceManager.getImage(ConfigurationDlg.class, "/png/list-2x.png"));
		shell.setSize(610, 340);
		shell.setText("Configuration List View");
		shell.setLayout(new BorderLayout(0, 0));
		shell.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.ESC){
					objConfiguration = null;
				}
			}
		});
        
        Composite compositeInShellConfiguration = new Composite(shell, SWT.NONE);
		compositeInShellConfiguration.setLayout(new BorderLayout(0, 0));
		compositeInShellConfiguration.setLayoutData(BorderLayout.CENTER);
        
		Composite compositeHeaderConfiguration = new Composite(compositeInShellConfiguration, SWT.NONE);
		compositeHeaderConfiguration.setLayoutData(BorderLayout.NORTH);
		compositeHeaderConfiguration.setLayout(new GridLayout(2, false));

		textSearchConfiguration = new Text(compositeHeaderConfiguration, SWT.BORDER);
		textSearchConfiguration.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		textSearchConfiguration.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==13){
					reloadTableConfiguration();
				}
			}
		});
		
		Button btnNewButtonSearchConfiguration = new Button(compositeHeaderConfiguration, SWT.NONE);
		btnNewButtonSearchConfiguration.setImage(SWTResourceManager.getImage(ConfigurationDlg.class, "/png/media-play-2x.png"));
		btnNewButtonSearchConfiguration.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));

		btnNewButtonSearchConfiguration.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				reloadTableConfiguration();
			}
		});
		GridData gd_btnNewButtonConfiguration = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButtonConfiguration.widthHint = 87;
		btnNewButtonSearchConfiguration.setLayoutData(gd_btnNewButtonConfiguration);
		btnNewButtonSearchConfiguration.setText("Search");
        
		tableViewerConfiguration = new TableViewer(compositeInShellConfiguration, SWT.BORDER | SWT.FULL_SELECTION);
		tableConfiguration = tableViewerConfiguration.getTable();
		tableConfiguration.setFont(SWTResourceManager.getFont("Tahoma", 11, SWT.NORMAL));
		tableConfiguration.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode==SWT.F5){
					reloadTableConfiguration();
                }
                if(e.keyCode==SWT.F4){
					editTableConfiguration();
                }
				else if(e.keyCode==13){
					selectTableConfiguration();
				}
                else if(e.keyCode==SWT.DEL){
					deleteTableConfiguration();
				}
                else if(e.keyCode==SWT.F7){
					newItemConfiguration();
				}
			}
		});
        tableConfiguration.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectTableConfiguration();
			}
		});
        
		tableConfiguration.setLinesVisible(true);
		tableConfiguration.setHeaderVisible(true);
		tableConfiguration.setLayoutData(BorderLayout.CENTER);

		TableColumn tbTableColumnConfigurationCONF_NAME = new TableColumn(tableConfiguration, SWT.LEFT);
		tbTableColumnConfigurationCONF_NAME.setWidth(100);
		tbTableColumnConfigurationCONF_NAME.setText("CONF_NAME");

		TableColumn tbTableColumnConfigurationCONF_VALUE = new TableColumn(tableConfiguration, SWT.LEFT);
		tbTableColumnConfigurationCONF_VALUE.setWidth(100);
		tbTableColumnConfigurationCONF_VALUE.setText("CONF_VALUE");

		TableColumn tbTableColumnConfigurationSTS = new TableColumn(tableConfiguration, SWT.RIGHT);
		tbTableColumnConfigurationSTS.setWidth(100);
		tbTableColumnConfigurationSTS.setText("STS");

        Menu menuConfiguration = new Menu(tableConfiguration);
		tableConfiguration.setMenu(menuConfiguration);
		
		MenuItem mntmNewItemConfiguration = new MenuItem(menuConfiguration, SWT.NONE);
		mntmNewItemConfiguration.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newItemConfiguration();
			}
		});
		mntmNewItemConfiguration.setImage(SWTResourceManager.getImage(ConfigurationDlg.class, "/png/arrow-circle-top-2x.png"));
		mntmNewItemConfiguration.setText("New");
		
		MenuItem mntmEditItemConfiguration = new MenuItem(menuConfiguration, SWT.NONE);
		mntmEditItemConfiguration.setImage(SWTResourceManager.getImage(ConfigurationDlg.class, "/png/wrench-2x.png"));
		mntmEditItemConfiguration.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editTableConfiguration();
			}
		});
		mntmEditItemConfiguration.setText("Edit");
		
		MenuItem mntmDeleteConfiguration = new MenuItem(menuConfiguration, SWT.NONE);
		mntmDeleteConfiguration.setImage(SWTResourceManager.getImage(ConfigurationDlg.class, "/png/circle-x-2x.png"));
		mntmDeleteConfiguration.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deleteTableConfiguration();
			}
		});
		mntmDeleteConfiguration.setText("Delete");

		tableViewerConfiguration.setLabelProvider(new TableLabelProviderConfiguration());
		tableViewerConfiguration.setContentProvider(new ContentProviderConfiguration());
		tableViewerConfiguration.setInput(listDataConfiguration);
        //
        //
		loadDataConfiguration();
		//
        reloadTableConfiguration();
	}
    public void setDataConfiguration(String textSearchString){
		this.textSearchConfigurationString = textSearchString;
	}
	private void loadDataConfiguration() {
		if(textSearchConfigurationString!=null){
			textSearchConfiguration.setText(textSearchConfigurationString);
		}
	}
	protected void reloadTableConfiguration() {
        if(DbHelper.checkPhanQuyen(DbHelper.READ, "configuration")==false){
			logger.info("DON'T HAVE READ RIGHT");
			return;
		}
    
        if(listDataConfiguration!=null){
            // 
            tableViewerConfiguration.setInput(listDataConfiguration);
            tableViewerConfiguration.refresh();
            //
            if(listDataConfiguration.size()==0){
                textSearchConfiguration.forceFocus();
            }
            else{
                tableConfiguration.forceFocus();
                tableConfiguration.setSelection(0);
            }
            return;
        }
		// Do search in the first time
		String searchString = textSearchConfiguration.getText().toLowerCase().trim();
		String sql = "select * from configuration WHERE STS<> "+DbHelper.DELETE_STATUS+" ";
		if(searchString.length()>0){
            sql += " and ( 0 ";
        sql += " or LOWER(CONF_NAME) like '%"+searchString+"%'";
        sql += " or LOWER(CONF_VALUE) like '%"+searchString+"%'";
            sql += " )";
        }
		try  {
            logger.info(sql);
			Connection con = DbHelper.getSql2o();
			listDataConfiguration = con.createQuery(sql).executeAndFetch(Configuration.class);
	    }   
	    catch(Exception e){
	    	logger.error(e);
	    	MessageDialog.openError(shell, "Error", e.getMessage());
	    }
		// 
		tableViewerConfiguration.setInput(listDataConfiguration);
		tableViewerConfiguration.refresh();
        //
        if(listDataConfiguration.size()==0){
            textSearchConfiguration.forceFocus();
        }
        else{
            tableConfiguration.forceFocus();
            tableConfiguration.setSelection(0);
        }
	}
    
    protected void selectTableConfiguration() {
		if(tableConfiguration.getSelectionCount()==0){
			return;
		}
		TableItem item = tableConfiguration.getSelection()[0];
		Configuration obj = (Configuration)item.getData();
        logger.info(obj.toString());
        //
        //
    	if(typeConfigurationDlg==TYPE_DLG_CHOOSEN){
    		// Choosen and close dlg
    		objConfiguration = obj;
    		shell.close();
    	}
    	else{
            if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "configuration")==false){
                logger.info("DON'T HAVE UPDATE RIGHT");
                return;
            }
            if(intTypeDlgConfiguration==TYPE_DLG_VIEW){
                return;
            }
			ConfigurationDlg dlg = new ConfigurationDlg(shell, 0);
            dlg.setConfigurationDlgData(obj);
            dlg.open();
            //
            reloadTableConfiguration();
    	}
	}
    protected void editTableConfiguration() {
        if(intTypeDlgConfiguration==TYPE_DLG_VIEW){
            return;
        }
        if(DbHelper.checkPhanQuyen(DbHelper.UPDATE, "configuration")==false){
            logger.info("DON'T HAVE UPDATE RIGHT");
            return;
        }
    	if(tableConfiguration.getSelectionCount()==0){
			return;
		}
		TableItem item = tableConfiguration.getSelection()[0];
		Configuration obj = (Configuration)item.getData();
        logger.info(obj.toString());
        //
        //
		ConfigurationDlg dlg = new ConfigurationDlg(shell, 0);
        dlg.setConfigurationDlgData(obj);
        dlg.open();
        //
        reloadTableConfiguration();
	}
    protected void deleteTableConfiguration() {
        if(intTypeDlgConfiguration==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.DELETE, "configuration")==false){
            logger.info("DON'T HAVE DELETE RIGHT");
            return;
        }
		if(tableConfiguration.getSelectionCount()==0){
			return;
		}
        if(MessageDialog.openConfirm(shell, "Confirm?", "Delete ?")==false){
			return;
		}
		
		TableItem item = tableConfiguration.getSelection()[0];
		Configuration obj = (Configuration)item.getData();
        logger.info(obj.toString());
        //
		obj.delete();
        listDataConfiguration.remove(obj);
        //
		reloadTableConfiguration();
	}

	protected void newItemConfiguration() {
        if(intTypeDlgConfiguration==TYPE_DLG_VIEW){
            return;
        }
        //
        if(DbHelper.checkPhanQuyen(DbHelper.INSERT, "configuration")==false){
            logger.info("DON'T HAVE INSERT RIGHT");
            return;
        }
		//
		ConfigurationDlg dlg = new ConfigurationDlg(shell, 0);
        Configuration obj = new Configuration();
		dlg.setConfigurationDlgData(obj);
		dlg.open();
        listDataConfiguration.add(obj);
        //
		reloadTableConfiguration();
		//
	}
}
