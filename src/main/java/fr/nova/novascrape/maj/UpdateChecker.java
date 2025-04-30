package fr.nova.novascrape.maj;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class UpdateChecker {

    private static final String GITHUB_API = "https://api.github.com/repos/Nova-Center/nova-scrape/releases/latest";

    public static ReleaseInfo getLatestReleaseInfo() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(GITHUB_API)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String body = response.body().string();
                JSONObject json = new JSONObject(body);

                String version = json.getString("tag_name").replace("v", ""); // v1.0.0 => 1.0.0
                String jarUrl = json.getJSONArray("assets")
                        .getJSONObject(0)
                        .getString("browser_download_url");

                return new ReleaseInfo(version, jarUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
