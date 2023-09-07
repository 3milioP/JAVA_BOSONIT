package com.ejercicio.block17Batch.block17Batch.job.config;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import com.ejercicio.block17Batch.block17Batch.Tiempo.Tiempo;
import com.ejercicio.block17Batch.block17Batch.Tiempo.TiempoOutputDTO;
import com.ejercicio.block17Batch.block17Batch.Tiempo.repository.TiempoRepository;
import com.ejercicio.block17Batch.block17Batch.TiempoRiesgo.TiempoRiesgo;
import com.ejercicio.block17Batch.block17Batch.TiempoRiesgo.repository.TiempoRiesgoRepository;
import com.ejercicio.block17Batch.block17Batch.job.ErroresItemWriter;
import com.ejercicio.block17Batch.block17Batch.job.TiempoItemProcessor;
import com.ejercicio.block17Batch.block17Batch.job.TiempoRiesgoItemWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.core.SkipListener;


import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
class BatchConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private TiempoRepository tiempoRepository;

    @Autowired
    private TiempoRiesgoRepository tiempoRiesgoRepository;

    @Bean
    public FlatFileItemReader<TiempoOutputDTO> tiempoItemReader() {
        return new FlatFileItemReaderBuilder<TiempoOutputDTO>()
                .name("tiempoItemReader")
                .resource(new ClassPathResource("tiempo.csv"))
                .linesToSkip(1)
                .lineTokenizer(new DelimitedLineTokenizer() {{
                    setNames("localidad", "dia", "temperatura");
                }})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<TiempoOutputDTO>() {{
                    setTargetType(TiempoOutputDTO.class);
                }})
                .build();
    }

    @Bean
    public TiempoItemProcessor tiempoItemProcessor() {
        return new TiempoItemProcessor();
    }

    @Bean
    public TiempoRiesgoItemWriter tiempoRiesgoItemWriter(TiempoRiesgoRepository tiempoRiesgoRepository) {
        return new TiempoRiesgoItemWriter(tiempoRiesgoRepository);
    }

    @Bean
    public ErroresItemWriter erroresItemWriter(TiempoRepository tiempoRepository) {
        return new ErroresItemWriter(tiempoRepository);
    }

    @Bean
    public ItemProcessor<Tiempo, Tiempo> erroresItemProcessor() {
        return tiempo -> {
            // Aquí colocas la lógica para detectar los registros erróneos basados en tus condiciones
            // Por ejemplo, supongamos que la temperatura no debe ser mayor a 50 grados ni menor a -20 grados
            // Entonces, si la temperatura es incorrecta, devolvemos el objeto Tiempo, de lo contrario, devolvemos null
            if (tiempo.getTemperatura() > 50 || tiempo.getTemperatura() < -20) {
                return tiempo; // Registro erróneo, lo devolvemos
            } else {
                return null; // Registro válido, lo descartamos (no se incluirá en el procesamiento)
            }
        };
    }

    @Bean
    public ItemWriter<Tiempo> tiempoItemWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Tiempo>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO tiempo (localidad, dia, temperatura) VALUES (:localidad, :dia, :temperatura)")
                .dataSource(dataSource)
                .build();
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("tu.paquete.de.entidades"); // Reemplaza por el paquete que contiene tus entidades
        // Configuración adicional del entityManagerFactory (puedes agregar más propiedades si es necesario)
        // em.setJpaProperties(...);
        return em;
    }

    @Bean
    public Step lecturaStep() {
        return new StepBuilder("lecturaStep")
                .<Tiempo, Tiempo>chunk(10)
                .reader(tiempoItemReader())
                .writer(tiempoItemWriter())
                .build();
    }

    @Bean
    public Step procesamientoStep() {
        return new StepBuilder("procesamientoStep")
                .<Tiempo, TiempoRiesgo>chunk(10)
                .reader(tiempoItemReader())
                .processor(tiempoItemProcessor())
                .writer(tiempoRiesgoItemWriter())
                .faultTolerant()
                .skipLimit(100)
                .skip(FlatFileParseException.class)
                .listener(new TiempoSkipListener())
                .build();
    }

    @Bean
    public Step erroresStep() {
        return new StepBuilder("erroresStep")
                .<Tiempo, Tiempo>chunk(10)
                .reader(tiempoItemReader())
                .processor(erroresItemProcessor())
                .writer(erroresItemWriter())
                .build();
    }

    @Bean
    public Job procesarTiempoJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("procesarTiempoJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(lecturaStep())
                .next(procesamientoStep())
                .next(erroresStep())
                .build();
    }
}