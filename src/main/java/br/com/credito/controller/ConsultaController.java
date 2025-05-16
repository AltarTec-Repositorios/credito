package br.com.credito.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/credito")
public class ConsultaController {

    @GetMapping("/teste-doc")
    public ResponseEntity<String> getMessage() {
        return new ResponseEntity<>("Este endpoint é de teste para verificar geração da documentação -> Swagger e OpenAPI", HttpStatus.OK);
    }
}
