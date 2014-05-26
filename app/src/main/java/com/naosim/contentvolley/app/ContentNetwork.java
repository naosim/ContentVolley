package com.naosim.contentvolley.app;

import android.content.ContentResolver;
import android.net.Uri;

import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by fujitanao on 2014/05/26.
 */
public class ContentNetwork implements Network {

    private final ContentResolver contentResolver;

    public ContentNetwork(ContentResolver cr) {
        contentResolver = cr;
    }

    @Override
    public NetworkResponse performRequest(Request<?> request) throws VolleyError {
        String url = request.getUrl();
        InputStream in = null;
        byte[] data = null;
        try {
            in = contentResolver.openInputStream(Uri.parse(url));
            data = IOUtils.toByteArray(in);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } finally {
            if(in != null) try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new NetworkResponse(data);
    }
}
