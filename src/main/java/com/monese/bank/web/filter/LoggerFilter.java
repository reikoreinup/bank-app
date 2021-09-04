package com.monese.bank.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

@Slf4j
@Component
public class LoggerFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestUri = request.getMethod() + " " + request.getRequestURI();
        log.info("Request begins {}", requestUri);
        Instant start = Instant.now();
        try {
            filterChain.doFilter(request, response);
        } finally {
            Instant end = Instant.now();
            log.info("Request {} ended, took {} ms", requestUri, Duration.between(start, end).toMillis());
        }
    }
}


