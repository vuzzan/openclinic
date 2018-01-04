package com.openclinic;

import com.openclinic.khambenh.FormKhamBenhDlg;

public class TimerThread extends Thread {

	public int timer = 1000;
	public boolean isStop = false;
	public int counter = 1;
	@Override
	public void run() {
		
		while(isStop == false){
			//System.out.println("TIMER START............");
			if(isStop == false && counter%10==0){
				LoginDlg.checkVersion();
				//FormKhamBenhDlg.doSearchBenhNhan();
			}
			counter++;
			if(counter==100000){
				counter=0;
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
