package com.tucanoo.springbatchtest.data.importconfig.contacts;

import com.tucanoo.springbatchtest.data.entities.Contact;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchImportConfigForContacts {

    private final EntityManagerFactory entityManagerFactory;

    /**
     * Creates and returns a {@link FlatFileItemReader} bean for reading CSV records.
     * The reader uses job parameters to determine the file path at runtime.
     *
     * @param path the file path provided at runtime through job parameters.
     * @return a configured FlatFileItemReader for reading Contact entities.
     */
    @Bean
    @StepScope
    public FlatFileItemReader<Contact> reader(@Value("#{jobParameters['filePath']}") String path) {
        return new FlatFileItemReaderBuilder<Contact>()
            .name("personItemReader")
            .resource(new FileSystemResource(path))
            .linesToSkip(1)  // skip header row
            .delimited()
            .names(new String[]{"firstName", "lastName", "gender", "email", "phone", "address", "occupation", "website"})
            .fieldSetMapper(new BeanWrapperFieldSetMapper<Contact>() {{
                setTargetType(Contact.class);
            }})
            .build();
    }

    /**
     * Defines the main batch job for importing contacts.
     *
     * @param jobRepository the repository for storing job metadata.
     * @param step1 the step associated with this job.
     * @return a configured Job for importing contacts.
     */
    @Bean
    public Job importContactsJob(JobRepository jobRepository, Step step1)  {
        return new JobBuilder("importContactsJob", jobRepository)
            .start(step1)
            .build();
    }

    /**
     * Creates and returns a {@link JpaItemWriter} bean for persisting Contact entities.
     *
     * @return a configured JpaItemWriter for writing Contact entities.
     */
    @Bean
    public JpaItemWriter<Contact> writer() {
        JpaItemWriter<Contact> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

    /**
     * Defines the main batch step which includes reading, processing (if any), and writing.
     *
     * @param jobRepository the repository for storing job metadata.
     * @param transactionManager the transaction manager to handle transactional behavior.
     * @return a configured Step for reading and writing Contact entities.
     */
    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step1", jobRepository)
            .<Contact, Contact>chunk(1000, transactionManager)
            .reader(reader(null))  // null path just for type resolution
            .writer(writer())
            .build();
    }
}
