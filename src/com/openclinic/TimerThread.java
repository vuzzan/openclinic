package com.openclinic;

import com.openclinic.khambenh.FormKhamBenhDlg;

public class TimerThread extends Thread {

	public int timer = 1000;
	public boolean isStop = false;
	@Override
	public void run() {
		
		while(isStop == false){
			//System.out.println("TIMER START............");
			if(isStop == false){
				//FormKhamBenhDlg.doSearchBenhNhan();
			}
			
			//
			try{
				//System.out.println("TIMER WAIT............"+timer);
				Thread.sleep(timer);
				//System.out.println("TIMER WAIT............END");
			}
			catch(Exception ee){
				//
			}
		}
		//System.out.println("TIMER START. EXIST");
	}

}
