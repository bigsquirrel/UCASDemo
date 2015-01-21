package com.ivanchou.ucasdemo.util;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

/**
 * Created by ivanchou on 1/21/2015.
 */
public class HttpUtil {
    private final String TAG = this.getClass().getSimpleName();
    private Context mContext;
    private static AsyncHttpClient client = new AsyncHttpClient();

    public HttpUtil(Context context) {
        this.mContext = context;
        client.setTimeout(10 * 1000);
        client.setCookieStore(new PersistentCookieStore(mContext));
        client.addHeader("user-agent", "ucasdemo");
    }

    /** post数据交互 */
    public void post(String url, RequestParams params,
                     TextHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
    }

    /** get数据交互 */
    public void get(String url, RequestParams params,
                    AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }
    /** get数据交互 */
    public void get(String url, RequestParams params,
                    TextHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }
}
