package com.github.erf88.processor;

import com.github.erf88.model.Cliente;
import org.springframework.batch.item.ItemProcessor;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class ClienteProcessor implements ItemProcessor<Cliente, Cliente> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final LocalDate dataProcessamento = LocalDate.now();

    @Override
    public Cliente process(Cliente cliente) {
        String linha = cliente.getLinha();

        if(linha.contains("INATIVO")) {
            return null;
        }

        cliente.setLinha(formataLinha(linha));
        return cliente;
    }

    private String formataLinha(String linha) {

        String[] colunas = linha.split(";");

        colunas[1] = removeAcentos(colunas[1]);
        colunas[2] = removeAcentos(colunas[2]);

        return Arrays.toString(colunas)
                .concat(";")
                .concat(dataProcessamento.format(formatter))
                .replace("[", "")
                .replace("]", "")
                .replace(", ", ";");
    }

    public static String removeAcentos(final String str) {
        String strSemAcentos = Normalizer.normalize(str, Normalizer.Form.NFD);
        return strSemAcentos.replaceAll("[^\\p{ASCII}]", "");
    }

}