package com.appfacturasya;

import com.appfacturasya.domain.model.InitialCost;
import com.appfacturasya.domain.model.Operation;
import com.appfacturasya.domain.repository.InitialCostRepository;
import com.appfacturasya.domain.repository.OperationRepository;
import com.appfacturasya.domain.repository.RateTermRepository;
import com.appfacturasya.domain.repository.UserRepository;
import com.appfacturasya.domain.service.InitialCostService;
import com.appfacturasya.domain.service.OperationService;
import com.appfacturasya.exception.ResourceNotFoundException;
import com.appfacturasya.service.InitialCostServiceImpl;
import com.appfacturasya.service.OperationServiceImpl;
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
public class OperationImplIntegrationTest {
    @MockBean
    private OperationRepository operationRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private RateTermRepository rateTermRepository;


    @Autowired
    private OperationService operationService;


    @TestConfiguration
    static class OperationImplIntegrationTestConfiguration{
        @Bean
        public OperationService operationService(){
            return new OperationServiceImpl();
        }
    }

    @Test
    @DisplayName("When GetOperationById With Valid Id Then Returns Operation")
    public void whenGetOperationByIdWithValidIdThenReturnsOperation() {
        // Arrange
        Long id = 1L;
        Operation operation = new Operation();
        operation.setId(1L);
        when(operationRepository.findById(id))
                .thenReturn(Optional.of(operation));

        // Act
        Operation foundOperation = operationService.getOperationById(id);

        // Assert
        assertThat(foundOperation.getId()).isEqualTo(id);

    }

    @Test
    @DisplayName("When GetOperationById With Invalid Id Then Returns ResourceNotFoundException")
    public void whenGetOperationByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(operationRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Operation", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> {
            Operation foundOperation = operationService.getOperationById(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }
}
