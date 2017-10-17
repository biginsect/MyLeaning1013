package com.example.lipeng_ds3.techniquenews.tool;

import android.accounts.NetworkErrorException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.IllegalFormatCodePointException;

/**
 * Created by lipeng-ds3 on 2017/10/17.
 */

public class NetworkUtil {
    public static String post(String url, String content){
        HttpURLConnection connection = null;
        try{
            URL mURL = new URL(url);
            connection = (HttpURLConnection)mURL.openConnection();

            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(10*1000);
            connection.setDoOutput(true);//允许向服务器输出内容

            String data = content;
            OutputStream out = connection.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();

            int code = connection.getResponseCode();
            if (code == 200){
                InputStream stream = connection.getInputStream();
                String response = getStringFromInputStream(stream);
                return response;
            }else throw new NetworkErrorException("response status is " + code);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (connection != null){
                //关闭连接
                connection.disconnect();
            }
        }
        return null;
    }

    public static String get(String url){
        HttpURLConnection connection = null;
        try{
            URL mURL = new URL(url);
            connection = (HttpURLConnection) mURL.openConnection();

            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(10000);

            int code = connection.getResponseCode();
            if (code == 200){
                InputStream is = connection.getInputStream();
                String response = getStringFromInputStream(is);
                return response;
            }else
                throw new NetworkErrorException("response status is " + code);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (connection != null)
                connection.disconnect();
        }

        return null;
    }

    private static String getStringFromInputStream(InputStream inputStream)throws IOException{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inputStream.read(buffer)) != -1){
            outputStream.write(buffer, 0, len);
        }

        inputStream.close();
        String state = outputStream.toString();
        outputStream.close();
        return state;
    }
}
