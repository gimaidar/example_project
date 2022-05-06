package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Organization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.util.List;

import static com.gimaletdinov.exampleProject.helper.OrganizationTestHelper.TEST_ORG_INN;
import static com.gimaletdinov.exampleProject.helper.OrganizationTestHelper.TEST_ORG_IS_ACTIVE;
import static com.gimaletdinov.exampleProject.helper.OrganizationTestHelper.TEST_ORG_NAME;
import static com.gimaletdinov.exampleProject.helper.OrganizationTestHelper.getOrganizationForUpdate;
import static com.gimaletdinov.exampleProject.helper.OrganizationTestHelper.getPopulateOrganization;
import static org.assertj.core.api.Assertions.assertThat;

//@SpringJUnitConfig
//@EnableAutoConfiguration
//@SpringBootTest(classes = OrganizationRepository.class)
//@EntityScan("com/gimaletdinov/exampleProject/model")
//@TestPropertySource("classpath:application-test.properties")
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class OrganizationRepositoryTest {

    private Organization testOrganizationInBD;

    @Autowired
    private OrganizationRepository organizationRepository;

    @BeforeEach
    void saveTestOrganizationInBD() {
        testOrganizationInBD = organizationRepository.save(getPopulateOrganization());
    }

    @Test
    @Transactional
    @DisplayName("Тест: findAll(organizationSpecification()) (Найти организации по предикату)")
    void findAllOrganizationsByPredicat() {
        List<Organization> organizationsFromBD = organizationRepository.findAll(organizationSpecification());
        assertThat(organizationsFromBD).isNotEmpty();
        assertThat(organizationsFromBD).hasSize(1);
        assertThat(organizationsFromBD.get(0)).isEqualTo(testOrganizationInBD);
    }

    @Test
    @Transactional
    @DisplayName("Тест: findById (Найти организацию по id)")
    void findById() {
        Organization organizationFromBD = organizationRepository.findById(testOrganizationInBD.getId()).get();
        assertThat(organizationFromBD).isNotNull();
        assertThat(organizationFromBD).isEqualTo(testOrganizationInBD);
    }

    @Test
    @Transactional
    @DisplayName("Тест: updateOrganization (Обновить данные организации)")
    void updateOrganization() {
        Organization organizationForUpdate = getOrganizationForUpdate(testOrganizationInBD);

        Organization updatedOrganization = organizationRepository.save(organizationForUpdate);

        assertThat(updatedOrganization).isNotNull();
        assertThat(updatedOrganization).isEqualTo(organizationForUpdate);
    }

    @Test
    @Transactional
    @DisplayName("Тест: save (Сохранить новую организацию)")
    void saveOrganization() {
        Organization organizationForSave = getPopulateOrganization();
        Organization savedOrganization = organizationRepository.save(organizationForSave);

        assertThat(savedOrganization).isNotNull();
        assertThat(savedOrganization).isEqualTo(organizationForSave);
    }

    private Specification<Organization> organizationSpecification() {
        return (organizationRoot, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(organizationRoot.get("name"), TEST_ORG_NAME),
                criteriaBuilder.equal(organizationRoot.get("inn"), TEST_ORG_INN),
                criteriaBuilder.equal(organizationRoot.get("isActive"), TEST_ORG_IS_ACTIVE));
    }
}
