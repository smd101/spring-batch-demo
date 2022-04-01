package com.example.demo



import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableBatchProcessing
class SandboxJob(
    val jobBuilderFactory: JobBuilderFactory,
    val stepBuilderFactory: StepBuilderFactory,
    val sandboxTasklet1: SandboxTasklet1,
    val sandboxTasklet2: SandboxTasklet2) {

  @Bean
  fun sandboxStep1(): Step = stepBuilderFactory.get("sandbox-step1").tasklet(sandboxTasklet1).build()

  @Bean
  fun sandboxStep2(): Step = stepBuilderFactory.get("sandbox-step2").tasklet(sandboxTasklet2).build()

  @Bean
  fun sandbox(): Job? = jobBuilderFactory.get("sandbox-job")
      .start(sandboxStep1())
      .next(sandboxStep2())
      .build()

}