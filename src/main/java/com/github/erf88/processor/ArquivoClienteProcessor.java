package com.github.erf88.processor;

import com.github.erf88.model.Cliente;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("ALL")
@Configuration
public class ArquivoClienteProcessor {

    @Bean
    public ItemProcessor<Cliente, Cliente> clienteProcessor() {
        return new ClienteProcessor();
    }

}
