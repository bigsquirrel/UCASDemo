package com.ivanchou.ucasdemo.core.tasker;

import android.content.Context;
import android.nfc.Tag;
import android.os.AsyncTask;

import com.ivanchou.ucasdemo.core.model.EventModel;
import com.ivanchou.ucasdemo.core.model.TagModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by ivanchou on 3/14/15.
 */
public abstract class AllTagsParseTasker extends AsyncTask<JSONArray, Void, ArrayList<TagModel>> {

    private Context mContext;

    protected AllTagsParseTasker(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected ArrayList<TagModel> doInBackground(JSONArray... params) {
        try {
            return getModels(params[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected abstract void onPostExecute(ArrayList<TagModel> tagModels);

    private ArrayList<TagModel> getModels(JSONArray jsonArray) throws JSONException{
        ArrayList<TagModel> models = new ArrayList<TagModel>();
        for(int i = 0; i < jsonArray.length(); i++){
            TagModel model = new TagModel();
            model.parse(jsonArray.getJSONObject(i));
            models.add(model);
        }
        return models;
    }
}
