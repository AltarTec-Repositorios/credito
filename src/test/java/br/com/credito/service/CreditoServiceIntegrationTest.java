package br.com.credito.service;

import br.com.credito.dto.CreditoDTO;
import br.com.credito.repository.CreditoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreditoServiceIntegrationTest {

    @Autowired
    private CreditoService creditoService;

    @Autowired
    private CreditoRepository creditoRepository;

    @Test
    @Sql(scripts = "/insert-creditos.sql") // Script para inserir dados de teste
    void returnsListOfCreditoDTOWhenCreditsExistInDatabase() {
        String numeroNfse = "101011";

        List<CreditoDTO> result = creditoService.getCreditosByNfse(numeroNfse);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(numeroNfse, result.get(0).getNumeroNfse());
    }

    @Test
    void returnsEmptyListWhenNoCreditsExistInDatabase() {
        String numeroNfse = "99999";

        List<CreditoDTO> result = creditoService.getCreditosByNfse(numeroNfse);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}