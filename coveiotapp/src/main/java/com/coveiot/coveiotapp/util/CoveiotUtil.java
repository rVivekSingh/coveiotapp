package com.coveiot.coveiotapp.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

public class CoveiotUtil {

	public static String getUserId(){
		String userId= "COVE";
		Random rand = new Random();
		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
		   LocalDateTime now = LocalDateTime.now();
		
		   userId = userId+dtf.format(now)+rand.nextInt(1000);
		   System.out.println(userId);
		return userId;
	}
	
	public static Boolean checkLoginExpire(Date lastLogin, Integer loginExpire){
		String dateStart = lastLogin.toString();
		String dateStop = new Timestamp(System.currentTimeMillis()).toString();
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

		Date d1 = null;
		Date d2 = null;

		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			//in milliseconds
			long diff = d2.getTime() - d1.getTime();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			System.out.println(diffDays + " days, ");
			System.out.println(diffHours + " hours, ");
			System.out.println(diffMinutes + " minutes, ");
			System.out.println(diffSeconds + " seconds.");
			
			if(diffDays==0 && diffHours ==0 && diffMinutes<=loginExpire) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return false;
	}
	
	public static void main(String[] args) {
		//CoveiotUtil.getUserId();
		
		String dateStart = "2019-04-14 21:47:13";
		String dateStop = "2019-04-14 23:00:13";

		//Boolean b=CoveiotUtil.checkLoginExpire(dateStart, 5);
		//System.out.println(b);

	}
	}
