package com.openclinic;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TypedListener;
import org.eclipse.wb.swt.SWTResourceManager;

import com.openclinic.utils.Utils;

public class DatePicker extends org.eclipse.swt.widgets.Composite {
	/* (non-Javadoc)
	 * @see org.eclipse.swt.widgets.Control#setEnabled(boolean)
	 */
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		if(enabled==true){
			dayText.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
			monthText.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
			yearText.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		}
		else{
			dayText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
			monthText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
			yearText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		}
	}

	private Text dayText = null;
	private Text monthText = null;
	private Text yearText = null;

	protected int day = 0;
	protected int month = 0;
	protected int year = 2017;
	public boolean isNgaySinh0101 = false;

	public DatePicker(Composite parent, int style) {
		super(parent, style);
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				dayText.selectAll();
				dayText.forceFocus();
			}
		});
		setToolTipText("Bấm qua lại để chỉnh ngày tháng năm, gõ vào Năm số tuổi");
		initialize();
	}

	private void initialize() {
		GridData gridData1 = new org.eclipse.swt.layout.GridData();
		gridData1.widthHint = 20;
		GridData gridData100 = new org.eclipse.swt.layout.GridData();
		gridData100.widthHint = 47;
		
		GridData gridData = new org.eclipse.swt.layout.GridData();
		gridData.widthHint = 20;
		gridData.horizontalAlignment = org.eclipse.swt.layout.GridData.END;
		gridData.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		gridLayout.horizontalSpacing = 0;
		gridLayout.verticalSpacing = 0;
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		gridLayout.makeColumnsEqualWidth = false;
		this.setLayout(gridLayout);
		setSize(new Point(136, 28));
		//
		dayText = new Text(this, SWT.BORDER);
		dayText.setToolTipText("Ngày <= 31");
		dayText.setLayoutData(gridData1);
		dayText.setText(""+day);
		dayText.setTextLimit(2);
		dayText.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		dayText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(Character.isDigit(e.character)==false){
					if(e.keyCode==SWT.ARROW_RIGHT ){
						monthText.forceFocus();
					}
					else if(e.keyCode==SWT.ARROW_UP){
						int val = Utils.getInt( dayText.getText() );
						dayText.setText(""+(val<=1?1:(val-1)));
					}
					else if(e.keyCode==SWT.ARROW_DOWN){
						int val = Utils.getInt( dayText.getText() );
						dayText.setText(""+(val>=31?31:(val+1)));
					}
					else if(e.keyCode=='m' || e.keyCode=='M' ){
						monthText.forceFocus();
						e.doit = false;
					}
					else if(e.keyCode=='y' || e.keyCode=='Y' ){
						yearText.forceFocus();
						e.doit = false;
					}
					else{
						//e.doit = false;
					}
				}
				controlKey(e);
			}
		});
		dayText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				dayText.selectAll();
			}
			@Override
			public void focusLost(FocusEvent e) {
				try{
					int day1 = Integer.parseInt(dayText.getText());
					if(day1<0 || day1>31){
						dayText.forceFocus();
						dayText.setText(""+day);
					}
					else{
						day = day1;
					}
				}
				catch(Exception ex){
				}
			}
		});
		dayText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
			}
		});
		//
		monthText = new Text(this, SWT.BORDER);
		monthText.setToolTipText("Tháng, <=12");
		monthText.setLayoutData(gridData);
		monthText.setText(""+month);
		monthText.setTextLimit(2);
		monthText.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		monthText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(Character.isDigit(e.character)==false){
					if(e.keyCode==SWT.ARROW_LEFT){
						dayText.forceFocus();
					}
					else if(e.keyCode==SWT.ARROW_RIGHT){
						yearText.forceFocus();
					}
					else if(e.keyCode==SWT.ARROW_UP){
						int val = Utils.getInt( monthText.getText() );
						monthText.setText(""+(val<=1?1:(val-1)));
					}
					else if(e.keyCode==SWT.ARROW_DOWN){
						int val = Utils.getInt( monthText.getText() );
						monthText.setText(""+(val>=12?12:(val+1)));
					}
					else if(e.keyCode=='d' || e.keyCode=='D' ){
						dayText.forceFocus();
						e.doit = false;
					}
					else if(e.keyCode=='y' || e.keyCode=='Y' ){
						yearText.forceFocus();
						e.doit = false;
					}
					else{
						//e.doit = false;
					}
				}
				controlKey(e);

			}
		});
		monthText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				monthText.selectAll();
			}
			@Override
			public void focusLost(FocusEvent e) {
				try{
					int month1 = Integer.parseInt(monthText.getText());
					if(month1<0 || month1>12){
						monthText.forceFocus();
						monthText.setText(""+month);
					}
					else{
						month = month1;
					}
				}
				catch(Exception ex){
				}
			}
		});
		
		yearText = new Text(this, SWT.BORDER);
		yearText.setToolTipText("Bấm lên xuống để chỉnh năm, bấm số <100 tức là tuổi, gõ 35t là trẻ con 35 tháng tuổi");
		yearText.setLayoutData(gridData100);
		yearText.setText(""+year);
		yearText.setTextLimit(4);
		yearText.setFont(SWTResourceManager.getFont("Tahoma", 12, SWT.NORMAL));
		yearText.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent e) {
				if(Character.isDigit(e.character)==false){
					if(e.keyCode==SWT.ARROW_LEFT){
						monthText.forceFocus();
					}
					else if(e.keyCode==SWT.ARROW_RIGHT){
						dayText.forceFocus();
					}
					else if(e.keyCode==SWT.ARROW_UP){
						int val = Utils.getInt( yearText.getText() );
						yearText.setText(""+(val-1));
					}
					else if(e.keyCode==SWT.ARROW_DOWN){
						int val = Utils.getInt( yearText.getText() );
						yearText.setText(""+(val+1));
					}
					else if(e.keyCode=='m' || e.keyCode=='M' ){
						monthText.forceFocus();
						e.doit = false;
					}
					else if(e.keyCode=='d' || e.keyCode=='D' ){
						dayText.forceFocus();
						e.doit = false;
					}
					else if(e.keyCode=='t' || e.keyCode=='T' ){
						//Cal by month
						System.out.println(yearText.getText());
						//
						//String strMonth = yearText.getText();
						try{
							//
							int monthCount = Integer.parseInt(yearText.getText());
							Date dt = new Date();
							Calendar c = Calendar.getInstance(); 
							c.setTime(dt); 
							c.add(Calendar.MONTH, -monthCount);
							dt = c.getTime();
							//System.out.println("TTT = " + dt.toLocaleString());
							day = 1;
							month = dt.getMonth()+1;
							year = dt.getYear()+1900;
							updateDateText();
							isNgaySinh0101 = true;
							//
							//
						}
						catch(Exception ee){
							
						}
						//
						e.doit = false;
					}
					else if(e.keyCode==13){
						try{
							int year1 = Integer.parseInt(yearText.getText());
							if(year1<1000){
								if(year1<200){
									int year0 = Calendar.getInstance().get(Calendar.YEAR);
									year = year0 - year1;
									month = 1;
									day = 1;
									updateDateText();
									isNgaySinh0101 = true;
								}
							}
							else{
								month = 1;
								day = 1;
								year = year1;
								isNgaySinh0101 = true;
							}
						}
						catch(Exception ex){
						}
						yearText.setText(""+year);
						yearText.selectAll();
					}
					else{
						//e.doit = false;
					}
				}
				else{
					
				}
				controlKey(e);

			}
		});
		yearText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				yearText.selectAll();
			}
			@Override
			public void focusLost(FocusEvent e) {
				try{
					int year1 = Integer.parseInt(yearText.getText());
					if(year1<1000){
						if(year1<200){
							int year0 = Calendar.getInstance().get(Calendar.YEAR);
							year = year0 - year1;
						}
						isNgaySinh0101 = true;
					}
					else{
						year = year1;
					}
				}
				catch(Exception ex){
				}
				updateDateText();
				//System.out.println("LOST YEAR");
				getParent().notifyListeners(SWT.KeyDown, new Event());
			}
		});
		//
	}

	protected void controlKey(KeyEvent e) {
		Event ev = new Event();
	    ev.type = SWT.KeyDown;
	    ev.keyCode = e.keyCode;
		//getParent().notifyListeners(SWT.KeyDown, ev);
	}

	public int toMonth(){
		Date dt = new Date();
		Date dt2 = new Date();
		dt2.setDate(day-1);
		dt2.setMonth(month-1);
		dt2.setYear(year-1900);
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		startCalendar.setTime(dt);
		endCalendar.setTime(dt2);
		int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int difInMonths = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
		difInMonths = -difInMonths -1;
		//System.out.println("TO MONTH toMonth()= " + difInMonths);
		return difInMonths;
	}
	public String toMonthDay(){
		Date dt = new Date();
		Date dt2 = new Date();
		dt2.setDate(day-1);
		dt2.setMonth(month-1);
		dt2.setYear(year-1900);
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		startCalendar.setTime(dt2);
		endCalendar.setTime(dt);
		int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int difInMonths = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
		int difInDate = endCalendar.get(Calendar.DATE) - startCalendar.get(Calendar.DATE);
		//System.out.println("TO MONTH toMonth()= " + difInMonths);
		return "" + (diffYear<=0?"":(diffYear +" năm "))+(difInMonths<=0?"":(difInMonths+" tháng "))+(difInDate<0?"":(difInDate+" ngày"));
	}
	public String getDate(){
		return (day<10?"0"+day:day)+"/"+(month<10?"0"+month:month)+"/"+year;
	}
	public String getDateByYear(){
		return ""+year;
	}
	
	public Calendar getDate2(){
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(year, month-1, day);
		return calendar1;
	}
	
	public long getDate3(){
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(year, month-1, day);
		return calendar1.getTimeInMillis();
	}
	
	public void setColor(Color color) {
		this.dayText.setBackground(color);
		this.monthText.setBackground(color);
		this.yearText.setBackground(color);
	}
	
	public void setText(String strDate) {
		setDate(strDate);
	}
	public void setText(Date objDate) {
		setDate(Utils.getDatetime(objDate, "dd/MM/yyyy"));
	}
	public int getTuoi(){
		return toMonth()/12;
	}
	public void setDate(String strDate) {
		if(strDate==null){
			return;
		}
		//
		//
		if(strDate.trim().length() == 8 && strDate.indexOf("/")==-1){
			// 20161001 format
			int dt[] = Utils.getDateTime(strDate, "yyyyMMdd");
			day 	= dt[0]==0?1:dt[0];
			month 	= dt[1]==0?1:dt[1];
			year 	= dt[2];
		}
		else if(strDate.trim().length() == 4 ){
			// 2016 format. Only Year
			int dt[] = Utils.getDateTime(strDate, "yyyy");
			day 	= 1;
			month 	= 1;
			year 	= dt[2];
			isNgaySinh0101 = true;
		}
		else{
			int dt[] = Utils.getDateTime(strDate);
			day 	= dt[0]==0?1:dt[0];
			month 	= dt[1]==0?1:dt[1];
			year 	= dt[2];
			isNgaySinh0101 = false;
		}
		updateDateText();
	}
	
	public Point computeSize(int wHint, int hHint, boolean changed) {
		this.layout();
		return new Point(this.getSize().x, this.getSize().y);
	}

	private void updateDateText() {
		System.out.println("Update updateDateText "+day+"/"+month+"/"+year + " DiffMonth=" + toMonth());
		if(day>0 && day<=31){
			dayText.setText(""+day);
			dayText.setEditable(true);
			dayText.setEnabled(true);
		}
		else{
			dayText.setText("");
			//dayText.setEditable(false);
			//dayText.setEnabled(false);
		}
		if(month>0 && month<=12){
			monthText.setText(""+month);
			monthText.setEditable(true);
			monthText.setEnabled(true);
		}
		else{
			monthText.setText("");
			//monthText.setEditable(false);
			//monthText.setEnabled(false);
		}
		yearText.setText(""+year);
	}
	
	/**
	 * Adds the listener to the collection of listeners who will be notified
	 * when the receiver's text is modified, by sending it one of the messages
	 * defined in the <code>ModifyListener interface.
	 * 
	 * @param listener
	 *            the listener which should be notified
	 * 
	 * @exception IllegalArgumentException
	 *                <ul>
	 *                <li>ERROR_NULL_ARGUMENT - if the listener is null
	 *                </ul>
	 * @exception SWTException
	 *                <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 * 
	 * @see ModifyListener
	 * @see #removeModifyListener
	 */
	public void addModifyListener(ModifyListener listener) {
		checkWidget();
		if (listener == null)
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
		TypedListener typedListener = new TypedListener(listener);
		addListener(SWT.Modify, typedListener);
	}

	/**
	 * Removes the listener from the collection of listeners who will be
	 * notified when the receiver's text is modified.
	 * 
	 * @param listener
	 *            the listener which should no longer be notified
	 * 
	 * @exception IllegalArgumentException
	 *                <ul>
	 *                <li>ERROR_NULL_ARGUMENT - if the listener is null
	 *                </ul>
	 * @exception SWTException
	 *                <ul>
	 *                <li>ERROR_WIDGET_DISPOSED - if the receiver has been
	 *                disposed</li>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the receiver</li>
	 *                </ul>
	 * 
	 * @see ModifyListener
	 * @see #addModifyListener
	 */
	public void removeModifyListener(ModifyListener listener) {
		checkWidget();
		if (listener == null)
			SWT.error(SWT.ERROR_NULL_ARGUMENT);
		removeListener(SWT.Modify, listener);
	}
}