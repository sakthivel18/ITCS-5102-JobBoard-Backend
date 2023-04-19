package com.uncc.jobboard.config


import com.uncc.jobboard.user.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class ApplicationConfig(private val repository: UserRepository) {
    @Bean
    fun userDetailsService(): UserDetailsService {
        return UserDetailsService { username: String? ->
            repository?.findByEmail(username)
                    ?.orElseThrow { UsernameNotFoundException("User not found") }
        }
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsService())
        authProvider.setPasswordEncoder(passwordEncoder())
        return authProvider
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager {
        return config.authenticationManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun configure(): WebMvcConfigurer? {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(reg: CorsRegistry) {
                reg.addMapping("/**")
                    .allowedOriginPatterns("*")
                    .allowedHeaders("*")
                    .allowedMethods("*")
                    .allowCredentials(true)
                    .maxAge(3600L)
                    .exposedHeaders("*")
            }
        }
    }
}