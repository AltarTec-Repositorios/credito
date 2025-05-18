package br.com.credito.controller;

import br.com.credito.dto.CreditoDTO;
import br.com.credito.exception.EntidadeNaoEncontradaException;
import br.com.credito.service.CreditoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsultaControllerTest {

    @Mock
    private CreditoService creditoService;

    @InjectMocks
    private ConsultaController consultaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    @DisplayName("getCreditosByNfse")
    class GetCreditosByNfse {

        @Test
        @DisplayName("Returns list of CreditoDTO when found")
        void returnsListOfCreditoDTOWhenFound() {
            String numeroNfse = "12345";
            List<CreditoDTO> mockCreditos = List.of(new CreditoDTO());
            when(creditoService.getCreditosByNfse(numeroNfse)).thenReturn(mockCreditos);

            List<CreditoDTO> result = consultaController.getCreditosByNfse(numeroNfse);

            assertNotNull(result);
            assertEquals(1, result.size());
            verify(creditoService, times(1)).getCreditosByNfse(numeroNfse);
        }

        @Test
        @DisplayName("Throws EntidadeNaoEncontradaException when no credits found")
        void throwsEntidadeNaoEncontradaExceptionWhenNoCreditsFound() {
            String numeroNfse = "12345";
            when(creditoService.getCreditosByNfse(numeroNfse)).thenReturn(Collections.emptyList());

            EntidadeNaoEncontradaException exception = assertThrows(
                    EntidadeNaoEncontradaException.class,
                    () -> consultaController.getCreditosByNfse(numeroNfse)
            );

            assertEquals("Nenhum crédito encontrado para o número da NFS-e: " + numeroNfse, exception.getMessage());
            verify(creditoService, times(1)).getCreditosByNfse(numeroNfse);
        }
    }
}