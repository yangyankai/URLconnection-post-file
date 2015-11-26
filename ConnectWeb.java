/*
 * Copyright (c) 2015-2015 by Shanghai shootbox Information Technology Co., Ltd.
 * Create: 2015/11/13 5:7:48
 * Project: T_ShootBox_Net
 * File: test.java
 * Class: test
 * Module: app
 * Author: yangyankai
 * Version: 1.0
 */

package com.shootbox.t_shootbox_net;

import android.content.Context;
import android.os.Environment;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//import javax.net.ssl.HttpsURLConnection;

/**
 * Created by yangyankai on 2015/11/13.
 */
public class ConnectWeb
{

	private String weburl   = "http://172.16.8.19:8080/shootbox_appSrv/user/reqFileUpload.action";
	private String filePath = Environment.getExternalStorageDirectory().getPath() +
			"/imagesCache" +
			"/aaa.png";
	private String fileName = "aaa.png";

	public String uploadFile(Context context)
	{

		String str        = "";
		String end        = "\r\n";
		String twoHyphens = "--";
		String boundary   = "*****";
		try
		{
			URL url = new URL(weburl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setRequestMethod("POST");
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");
			con.setRequestProperty("Content-Type", "multipart/from-data;boundary=" + boundary);

			DataOutputStream ds = new DataOutputStream(con.getOutputStream());
			ds.writeBytes(twoHyphens + boundary + end);
			ds.writeBytes("Content-Disposition:form-data;" + "name=\"filel\";" +
								  "filename=\"" + fileName + "\"" + end);
			ds.writeBytes(end);

			FileInputStream fStream=new FileInputStream(filePath);

			int bufferSize=1024;
			byte[] buffer = new byte[bufferSize];

			int length=-1;
			while ((length = fStream.read(buffer)) != -1){
				ds.write(buffer,0,length);
			}
			ds.writeBytes(end);
			ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
			fStream.close();
			ds.flush();

			InputStream is= con.getErrorStream();
			int ch;
			StringBuffer b = new StringBuffer();
			while ((ch = is.read()) !=-1){
				b.append((char) ch);
			}
			str = b.toString().trim();

			ds.close();
		}catch (Exception e){
			e.printStackTrace();
			str="upload fail"+e.toString();
		}
		return str;
	}

}

