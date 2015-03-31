package de.overwatch.gungungun.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan(basePackages = {
        "de.overwatch.gungungun.job"})
public class SchedulingConfiguration {

}
