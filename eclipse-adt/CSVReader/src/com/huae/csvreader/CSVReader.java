package com.huae.csvreader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CSVReader {
	public static void main(String[] args) throws Exception {
		
		String address = "https://cdn.heweather.com/china-city-list.csv";
		URL url = new URL(address);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(8000);
		conn.setReadTimeout(8000);
		conn.setDoOutput(true);
		
		InputStream inputStream = conn.getInputStream();
		BufferedReader contentReader = new BufferedReader(new InputStreamReader(inputStream));
		
		File file = new File("city_data.csv");
		if(file.exists()){
			file.delete();
		}
		file.createNewFile();
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		String temp = "";
		while((temp = contentReader.readLine()) != null){
			writer.write(temp);
			writer.write("\r\n");
		}
		
		
		String line = "";
		FileReader reader = new FileReader(new File("E:/360极速浏览器下载/china-city-list.csv"));
		BufferedReader br = new BufferedReader(reader);
		while((line = br.readLine()) != null){
			System.out.println(line);
		}
		
		if(contentReader != null){
			contentReader.close();
		}
		if(writer != null){
			writer.close();
		}
		if(br != null){
			br.close();
		}
	}
}
