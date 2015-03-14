package com.ivanchou.ucasdemo.util;

import android.content.Context;
import android.util.Log;

import com.ivanchou.ucasdemo.app.Config;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

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

    public void singUp() {


    }

    public void logIn() {

    }

    public void getLatestEvents(int page, final JsonHttpResponseHandler responseHandler) {

        RequestParams params = new RequestParams();
        params.put("page", String.valueOf(page));
        get(LATEST_EVENTS, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                responseHandler.onSuccess(statusCode, headers, response);
                super.onSuccess(statusCode, headers, response);
            }

//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                responseHandler.onSuccess(statusCode, headers, response);
//                super.onSuccess(statusCode, headers, response);
//            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

    public void getAllTags(final JsonHttpResponseHandler responseHandler) {
        get(TAGS, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                responseHandler.onSuccess(statusCode, headers, response);
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }



    /** 注册 **/
    private static final String SIGN_UP = Config.URL.COMMON + "authorize/newregister.html";

    private static final String SCHOOLS = Config.URL.COMMON + "public/shools.json";

    private static final String ACADEMIES = Config.URL.COMMON + "public/academies.json";

    private static final String MAJORS = Config.URL.COMMON + "public/majors.json";

    private static final String TAGS = Config.URL.COMMON + "public/tags.json";

    /** 活动列表 **/
    private static final String LATEST_EVENTS = Config.URL.COMMON + "activities/list.json";

    /** 活动详情 **/
    private static final String EVENT_DETAILS = Config.URL.COMMON + "activities/show.json";

    /** 登陆 **/
    private static final String LOG_IN = Config.URL.COMMON + "authorize/login.json";

    /** 登出 **/
    private static final String LOG_OUT = Config.URL.COMMON + "authorize/logout.html";

    /** 创建活动 **/
    private static final String CREATE_EVENT = Config.URL.COMMON + "activities/create.html";

    /** 活动参与者 **/
    private static final String JOINER = Config.URL.COMMON + "join/show.json";

    /** 用户发布的所有活动 **/
    private static final String USER_EVENTS = Config.URL.COMMON + "activities/createhistory.json";

    /** 用户对活动的操作 **/
    private static final String USER_EVENT_STATE = Config.URL.COMMON + "join/changestate.html";

    /** 用户参与的所有活动 **/
    private static final String USER_JOIN_EVENTS = Config.URL.COMMON + "join/joinhistory.json";

    /** 查看个人信息 **/
    private static final String USER_SELFINFO = Config.URL.COMMON + "authorize/lookselfinfo.json";

    /** 修改补全个人信息 **/
    private static final String CHANGE_SELFINFO = Config.URL.COMMON + "authorize/changeselfinfo.html";

    /** 修改活动信息 **/
    private static final String CHANGE_EVENTINFO = Config.URL.COMMON + "activities/changeinfo.json";

    /** 删除活动 **/
    private static final String DEL_EVENT = Config.URL.COMMON + "activities/delete.html";
}
