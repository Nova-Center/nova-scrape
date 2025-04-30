package fr.nova.novascrape.maj;


public class ReleaseInfo {
    private final String version;
    private final String jarUrl;

    public ReleaseInfo(String version, String jarUrl) {
        this.version = version;
        this.jarUrl = jarUrl;
    }

    public String getVersion() {
        return version;
    }

    public String getJarUrl() {
        return jarUrl;
    }
}
