package com.example.myapplication.repository;

import com.example.myapplication.entity.Property;
import com.example.myapplication.entity.PropertyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 物件情報のデータアクセスを担当するリポジトリインターフェース
 */
@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    
    /**
     * ステータス別に物件を検索
     * @param status 物件ステータス
     * @return ステータスに一致する物件のリスト
     */
    List<Property> findByStatus(PropertyStatus status);
    
    /**
     * 物件名で部分一致検索
     * @param name 物件名
     * @return 名前に部分一致する物件のリスト
     */
    List<Property> findByNameContaining(String name);
}