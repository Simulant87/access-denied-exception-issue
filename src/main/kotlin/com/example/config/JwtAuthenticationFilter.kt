package com.example.config

import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter


class JwtAuthenticationFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        // simplified, actually doing a correct token extraction and validation here
        // val authToken = request.getHeader(HttpHeaders.AUTHORIZATION)
        val authToken = "test"
        SecurityContextHolder.getContext().authentication = JwtUser(authToken)
        filterChain.doFilter(request, response)
    }

}
