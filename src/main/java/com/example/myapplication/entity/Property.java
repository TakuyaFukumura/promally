package com.example.myapplication.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 物件情報を表すエンティティクラス
 */
@Entity
@Table(name = "property")
@Data
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 物件名
     */
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * 住所
     */
    @Column(nullable = false, length = 255)
    private String address;

    /**
     * 賃料（円）
     */
    @Column(precision = 10, scale = 0)
    private BigDecimal rent;

    /**
     * 面積（平方メートル）
     */
    @Column(precision = 6, scale = 2)
    private BigDecimal area;

    /**
     * 築年数
     */
    private Integer buildingAge;

    /**
     * 間取り
     */
    @Column(length = 20)
    private String layout;

    /**
     * 物件ステータス
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PropertyStatus status;

    /**
     * 説明・備考
     */
    @Column(length = 1000)
    private String description;

    /**
     * 登録日時
     */
    @Column(nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}