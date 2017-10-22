package com.openclinic;

import com.openclinic.khambenh.FormKhamBenhDlg;

public class TimerThread extends Thread {

	public int timer = 2000;
	public boolean isStop = false;
	@Override
	public void run() {
		
		while(isStop == false){
			//System.out.println("TIMER START............");
			FormKhamBenhDlg.doSearchBenhNhan(1);
			try{
				//System.out.println("TIMER WAIT............");
				Thread.sleep(timer);
			}
			catch(Exception ee){
				
			}
		}
	}

}
