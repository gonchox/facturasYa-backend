package com.appfacturasya;

import com.appfacturasya.domain.model.InitialCost;
import com.appfacturasya.domain.model.RateTerm;
import com.appfacturasya.domain.repository.InitialCostRepository;
import com.appfacturasya.domain.repository.OperationRepository;
import com.appfacturasya.domain.repository.RateTermRepository;
import com.appfacturasya.domain.service.InitialCostService;
import com.appfacturasya.domain.service.RateTermService;
import com.appfacturasya.exception.ResourceNotFoundException;
import com.appfacturasya.service.InitialCostServiceImpl;
import com.appfacturasya.service.RateTermServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class InitialCostImplIntegrationTest {

        @MockBean
        private InitialCostRepository initialCostRepository;
        @MockBean
        private OperationRepository operationRepository;


        @Autowired
        private InitialCostService initialCostService;


        @TestConfiguration
        static class InitialCostImplIntegrationTestConfiguration{
            @Bean
            public InitialCostService initialCostService(){
                return new InitialCostServiceImpl();
            }
        }

        @Test
        @DisplayName("When GetInitialCostById With Valid Id Then Returns InitialCost")
        public void whenGetInitialCostByIdWithValidIdThenReturnsInitialCost() {
            // Arrange
            Long id = 1L;
            InitialCost initialCost = new InitialCost();
            initialCost.setId(1L);
            when(initialCostRepository.findById(id))
                    .thenReturn(Optional.of(initialCost));

            // Act
            InitialCost foundInitialCost = initialCostService.getInitialCostById(id);

            // Assert
            assertThat(foundInitialCost.getId()).isEqualTo(id);

        }

        @Test
        @DisplayName("When GetInitialCostById With Invalid Id Then Returns ResourceNotFoundException")
        public void whenGetRateTermByIdWithInvalidIdThenReturnsResourceNotFoundException() {
            // Arrange
            Long id = 1L;
            String template = "Resource %s not found for %s with value %s";
            when(initialCostRepository.findById(id))
                    .thenReturn(Optional.empty());
            String expectedMessage = String.format(template, "InitialCost", "Id", id);

            // Act
            Throwable exception = catchThrowable(() -> {
                InitialCost foundInitialCost = initialCostService.getInitialCostById(id);
            });

            // Assert
            assertThat(exception)
                    .isInstanceOf(ResourceNotFoundException.class)
                    .hasMessage(expectedMessage);

        }
}
