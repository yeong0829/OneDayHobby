package com.example.onedayhobby;


public class HobbyItem {

    public int icon;
    public String name;
    public String contents;
    public String longContents;

    public HobbyItem(int icon, String name, String contents, String longContents) {
        this.icon = icon;
        this.name = name;
        this.contents = contents;
        this.longContents = longContents;
    }

    public int getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }
}
