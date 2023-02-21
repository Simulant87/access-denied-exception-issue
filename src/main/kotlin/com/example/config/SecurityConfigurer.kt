package com.example.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.ObjectPostProcessor
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.header.HeaderWriterFilter

@Configuration
@EnableWebSecurity
class SecurityConfigurer {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        // because of https://github.com/spring-projects/spring-security/issues/9175
        // exception logging issue is also prevent without this addition
        val postProcessorFilter =
            object : ObjectPostProcessor<HeaderWriterFilter> {
                override fun <O : HeaderWriterFilter> postProcess(filter: O): O {
                    filter.setShouldWriteHeadersEagerly(true)
                    return filter
                }
            }
        http
            .headers()
            .withObjectPostProcessor(postProcessorFilter)

        http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().authorizeHttpRequests().anyRequest().authenticated()

        http.addFilterBefore(JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }
}
