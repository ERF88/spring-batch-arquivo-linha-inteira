package com.github.erf88.step;

import com.github.erf88.model.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoClienteStep {

    @Bean
    public Step validaClienteStep(
            StepBuilderFactory stepBuilderFactory,
            FlatFileItemReader<Cliente> clienteReader,
            ItemProcessor<Cliente, Cliente> clienteProcessor,
            ItemWriter clienteWriter) {

        return stepBuilderFactory
                .get("validaClienteStep")
                .<Cliente, Cliente>chunk(50)
                .reader(clienteReader)
                .processor(clienteProcessor)
                .writer(clienteWriter)
                .build();
    }

}
