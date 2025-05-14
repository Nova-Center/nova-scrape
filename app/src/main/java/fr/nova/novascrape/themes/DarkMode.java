package fr.nova.novascrape.themes;

public class DarkMode {
    private static DarkMode instance;
    private boolean isDarkModeEnabled = false;

    public static DarkMode getInstance() {
        if (instance == null) {
            instance = new DarkMode();
        }
        return instance;
    }

    public boolean isDarkModeEnabled() {
        return isDarkModeEnabled;
    }

    public void setDarkModeEnabled(boolean darkModeEnabled) {
        isDarkModeEnabled = darkModeEnabled;
    }
}
