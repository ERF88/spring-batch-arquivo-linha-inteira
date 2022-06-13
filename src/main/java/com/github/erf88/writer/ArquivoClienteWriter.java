package com.github.erf88.writer;

import com.github.erf88.model.Cliente;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ArquivoClienteWriter {

    @Value("${spring.batch.file.clientes.out}")
    private Resource resource;

    @Bean
    public MultiResourceItemWriter<Cliente> clienteWriter() {
        return new MultiResourceItemWriterBuilder<Cliente>()
                .name("clienteWriter")
                .resource(resource)
                .delegate(writer())
                .itemCountLimitPerResource(50) // mesma quantidade do chunk
                .resourceSuffixCreator(index -> "_" + index + ".txt")
                .build();
    }

    private FlatFileItemWriter<Cliente> writer() {
        return new FlatFileItemWriterBuilder<Cliente>()
                .name("clienteWriter")
                .resource(resource)
                .delimited()
                .delimiter(System.lineSeparator())
                .names(new String[] { "linha" })
                .build();
    }

}
