package com.appfacturasya;

import com.appfacturasya.domain.model.RateTerm;
import com.appfacturasya.domain.repository.RateTermRepository;
import com.appfacturasya.domain.service.RateTermService;
import com.appfacturasya.exception.ResourceNotFoundException;
import com.appfacturasya.service.RateTermServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class RateTermImplIntegrationTest {
    @MockBean
    private RateTermRepository rateTermRepository;


    @Autowired
    private RateTermService rateTermService;


    @TestConfiguration
    static class RateTermImplIntegrationTestConfiguration{
        @Bean
        public RateTermService rateTermService(){
            return new RateTermServiceImpl();
        }
    }

    @Test
    @DisplayName("When GetRateNameById With Valid Id Then Returns RateTerm")
    public void whenGetRateTermByIdWithValidIdThenReturnsRateTerm() {
        // Arrange
        Long id = 1L;
        RateTerm rateTerm = new RateTerm();
        rateTerm.setId(1L);
        when(rateTermRepository.findById(id))
                .thenReturn(Optional.of(rateTerm));

        // Act
        RateTerm foundRateTerm = rateTermService.getRateTermById(id);

        // Assert
        assertThat(foundRateTerm.getId()).isEqualTo(id);

    }

    @Test
    @DisplayName("When GetRateTermById With Invalid Id Then Returns ResourceNotFoundException")
    public void whenGetRateTermByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(rateTermRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "RateTerm", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> {
            RateTerm foundRateTerm = rateTermService.getRateTermById(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }
}
