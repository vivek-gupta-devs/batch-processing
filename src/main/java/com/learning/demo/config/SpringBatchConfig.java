package com.learning.demo.config;

import com.learning.demo.entity.Product;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class SpringBatchConfig {


    @Bean
    public Job jobBean(JobRepository jobRepository,JobCompletionNotification listener,Step steps
    ) {

        return new JobBuilder("job", jobRepository)
                .listener(listener)
                .start(steps)
                .build();

    }

    @Bean
    public Step steps(JobRepository jobRepository,
                      DataSourceTransactionManager dataSourceTransactionManager,
                      ItemReader<Product> reader,
                      ItemProcessor<Product,Product> processor){

        return new StepBuilder("jobStep",jobRepository)
                .chunk(5,dataSourceTransactionManager)
                .reader(reader)
//                .processor(processor)
//                .writer()
                .build();

    }


    @Bean
    public FlatFileItemReader<Product> reader(){
        return new FlatFileItemReaderBuilder<Product>()
                .name("itemReader")
                .resource(new ClassPathResource("data.csv"))
                .delimited()
                .names("productId","title","description","price","discount")
                .targetType(Product.class)
                .build();
    }

    @Bean
    public ItemProcessor<Product,Product> itemProcessor(){
        return new CustomItemProcessor();
    }


    @Bean
    public ItemWriter<Product> itemWriter(){
       return new JdbcBatchItemWriterBuilder<Product>()
               //                .sql("insert into product(product_id,title,description,discounted_price)")
               .build();
    }

}
