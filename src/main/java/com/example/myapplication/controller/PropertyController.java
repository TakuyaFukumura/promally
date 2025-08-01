package com.example.myapplication.controller;

import com.example.myapplication.entity.Property;
import com.example.myapplication.entity.PropertyStatus;
import com.example.myapplication.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

/**
 * 物件管理のWebコントローラー
 */
@Controller
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    /**
     * 物件一覧表示
     */
    @GetMapping
    public String listProperties(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String search,
            Model model) {

        if (search != null && !search.trim().isEmpty()) {
            model.addAttribute("properties", propertyService.searchPropertiesByName(search));
            model.addAttribute("searchQuery", search);
        } else if (status != null && !status.isEmpty()) {
            PropertyStatus propertyStatus = PropertyStatus.valueOf(status);
            model.addAttribute("properties", propertyService.getPropertiesByStatus(propertyStatus));
            model.addAttribute("selectedStatus", status);
        } else {
            model.addAttribute("properties", propertyService.getAllProperties());
        }

        model.addAttribute("consideringCount", propertyService.getConsideringPropertiesCount());
        model.addAttribute("ownedCount", propertyService.getOwnedPropertiesCount());
        model.addAttribute("statuses", PropertyStatus.values());

        return "properties/list";
    }

    /**
     * 物件詳細表示
     */
    @GetMapping("/{id}")
    public String viewProperty(@PathVariable Long id, Model model) {
        Optional<Property> property = propertyService.getPropertyById(id);
        if (property.isPresent()) {
            model.addAttribute("property", property.get());
            return "properties/detail";
        } else {
            return "redirect:/properties";
        }
    }

    /**
     * 新規物件登録フォーム表示
     */
    @GetMapping("/new")
    public String newPropertyForm(Model model) {
        model.addAttribute("property", new Property());
        model.addAttribute("statuses", PropertyStatus.values());
        return "properties/form";
    }

    /**
     * 物件編集フォーム表示
     */
    @GetMapping("/{id}/edit")
    public String editPropertyForm(@PathVariable Long id, Model model) {
        Optional<Property> property = propertyService.getPropertyById(id);
        if (property.isPresent()) {
            model.addAttribute("property", property.get());
            model.addAttribute("statuses", PropertyStatus.values());
            return "properties/form";
        } else {
            return "redirect:/properties";
        }
    }

    /**
     * 物件保存（新規登録・更新）
     */
    @PostMapping("/save")
    public String saveProperty(@ModelAttribute Property property, RedirectAttributes redirectAttributes) {
        try {
            propertyService.saveProperty(property);
            redirectAttributes.addFlashAttribute("successMessage", "物件情報を保存しました。");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "物件情報の保存に失敗しました。");
        }
        return "redirect:/properties";
    }

    /**
     * 物件削除
     */
    @PostMapping("/{id}/delete")
    public String deleteProperty(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            propertyService.deleteProperty(id);
            redirectAttributes.addFlashAttribute("successMessage", "物件を削除しました。");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "物件の削除に失敗しました。");
        }
        return "redirect:/properties";
    }
}
