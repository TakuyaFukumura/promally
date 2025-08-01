package com.example.myapplication.service

import com.example.myapplication.entity.Property
import com.example.myapplication.entity.PropertyStatus
import com.example.myapplication.repository.PropertyRepository
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Subject

@SpringBootTest
class PropertyServiceSpec extends Specification {

    PropertyRepository propertyRepository = Mock()
    
    @Subject
    PropertyService propertyService = new PropertyService(propertyRepository)

    def "getAllProperties should return all properties"() {
        given:
        def properties = [new Property(), new Property()]
        propertyRepository.findAll() >> properties

        when:
        def result = propertyService.getAllProperties()

        then:
        result == properties
    }

    def "getConsideringPropertiesCount should return count of considering properties"() {
        given:
        def consideringProperties = [new Property(), new Property()]
        propertyRepository.findByStatus(PropertyStatus.CONSIDERING) >> consideringProperties

        when:
        def result = propertyService.getConsideringPropertiesCount()

        then:
        result == 2L
    }

    def "getOwnedPropertiesCount should return count of owned properties"() {
        given:
        def ownedProperties = [new Property()]
        propertyRepository.findByStatus(PropertyStatus.OWNED) >> ownedProperties

        when:
        def result = propertyService.getOwnedPropertiesCount()

        then:
        result == 1L
    }
}
