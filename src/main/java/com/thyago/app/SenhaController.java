package com.thyago.app;

import java.security.SecureRandom;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SenhaController {

    private static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String MAIUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMEROS = "0123456789";
    private static final String SIMBOLOS = "!@#$%&*?-_+=";
    private final SecureRandom random = new SecureRandom();

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tamanho", 12);
        model.addAttribute("maiusculas", true);
        model.addAttribute("numeros", true);
        return "index";
    }

    @PostMapping("/gerar")
    public String gerar(@RequestParam(defaultValue = "12") int tamanho,
                        @RequestParam(defaultValue = "false") boolean maiusculas,
                        @RequestParam(defaultValue = "false") boolean numeros,
                        @RequestParam(defaultValue = "false") boolean simbolos,
                        Model model) {
        tamanho = Math.max(4, Math.min(64, tamanho));
        StringBuilder alfabeto = new StringBuilder(MINUSCULAS);
        if (maiusculas) alfabeto.append(MAIUSCULAS);
        if (numeros) alfabeto.append(NUMEROS);
        if (simbolos) alfabeto.append(SIMBOLOS);

        StringBuilder senha = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            senha.append(alfabeto.charAt(random.nextInt(alfabeto.length())));
        }
        model.addAttribute("senha", senha.toString());
        model.addAttribute("tamanho", tamanho);
        model.addAttribute("maiusculas", maiusculas);
        model.addAttribute("numeros", numeros);
        model.addAttribute("simbolos", simbolos);
        return "index";
    }
}
