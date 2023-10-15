package org.dimas4ek.wrapper;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class ApiClient {
    private static final OkHttpClient CLIENT = new OkHttpClient();

    public static void sendResponse(String channelId, JSONObject jsonObject) {
        RequestBody requestBody = RequestBody.create(jsonObject.toString(), Constants.MEDIA_TYPE_JSON);

        Request request = new Request.Builder()
                .url(Constants.API_URL + "/channels/" + channelId + "/messages")
                .addHeader("Authorization", "Bot " + Constants.BOT_TOKEN)
                .post(requestBody)
                .build();

        try (Response response = CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("true");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void post(JSONObject jsonObject, String url) {
        RequestBody requestBody = RequestBody.create(jsonObject.toString(), Constants.MEDIA_TYPE_JSON);

        Request request = new Request.Builder()
                .url(Constants.API_URL + url)
                .addHeader("Authorization", "Bot " + Constants.BOT_TOKEN)
                .post(requestBody)
                .build();

        try (Response response = CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                try (ResponseBody body = response.body()) {
                    if (body != null) {
                        System.out.println(new JSONObject(body).toString(4));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONArray getJsonArray(String url) {
        Request request = new Request.Builder()
                .url(Constants.API_URL + url)
                .addHeader("Authorization", "Bot " + Constants.BOT_TOKEN)
                .get()
                .build();

        try (Response response = CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()) {
                try (ResponseBody body = response.body()) {
                    if (body != null) {
                        return new JSONArray(body.string());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static JSONArray getJsonArrayParams(String url, Map<String, String> params) {
        HttpUrl.Builder httpBuilder = HttpUrl.parse(Constants.API_URL + url).newBuilder();
        if (params != null) {
            for(Map.Entry<String, String> param : params.entrySet()) {
                httpBuilder.addQueryParameter(param.getKey(), param.getValue());
            }
        }

        Request request = new Request.Builder()
                .url(httpBuilder.build().toString())
                .addHeader("Authorization", "Bot " + Constants.BOT_TOKEN)
                .get()
                .build();

        try (Response response = CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()) {
                try (ResponseBody body = response.body()) {
                    if (body != null) {
                        return new JSONArray(body.string());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static JSONObject getJsonObject(String url) {
        Request request = new Request.Builder()
                .url(Constants.API_URL + url)
                .addHeader("Authorization", "Bot " + Constants.BOT_TOKEN)
                .get()
                .build();

        try (Response response = CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()) {
                try (ResponseBody body = response.body()) {
                    if (body != null) {
                        return new JSONObject(body.string());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}