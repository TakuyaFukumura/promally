package com.example.myapplication.entity;

/**
 * 物件のステータスを表す列挙型
 */
public enum PropertyStatus {
    /**
     * 検討中
     */
    CONSIDERING("検討中"),
    
    /**
     * 所有中
     */
    OWNED("所有中");

    private final String displayName;

    PropertyStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}