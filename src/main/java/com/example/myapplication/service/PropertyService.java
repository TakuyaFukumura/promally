package com.example.myapplication.service;

import com.example.myapplication.entity.Property;
import com.example.myapplication.entity.PropertyStatus;
import com.example.myapplication.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 物件情報のビジネスロジックを担当するサービスクラス
 */
@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    /**
     * 全ての物件を取得
     *
     * @return 全物件のリスト
     */
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    /**
     * IDで物件を検索
     *
     * @param id 物件ID
     * @return 物件情報（存在しない場合はOptional.empty()）
     */
    public Optional<Property> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    /**
     * ステータス別に物件を検索
     *
     * @param status 物件ステータス
     * @return ステータスに一致する物件のリスト
     */
    public List<Property> getPropertiesByStatus(PropertyStatus status) {
        return propertyRepository.findByStatus(status);
    }

    /**
     * 物件名で検索
     *
     * @param name 物件名
     * @return 名前に部分一致する物件のリスト
     */
    public List<Property> searchPropertiesByName(String name) {
        return propertyRepository.findByNameContaining(name);
    }

    /**
     * 物件を保存（新規登録・更新）
     *
     * @param property 保存する物件情報
     * @return 保存された物件情報
     */
    public Property saveProperty(Property property) {
        return propertyRepository.save(property);
    }

    /**
     * 物件を削除
     *
     * @param id 削除する物件のID
     */
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }

    /**
     * 検討中の物件数を取得
     *
     * @return 検討中の物件数
     */
    public long getConsideringPropertiesCount() {
        return getPropertiesByStatus(PropertyStatus.CONSIDERING).size();
    }

    /**
     * 所有中の物件数を取得
     *
     * @return 所有中の物件数
     */
    public long getOwnedPropertiesCount() {
        return getPropertiesByStatus(PropertyStatus.OWNED).size();
    }
}
