package com.example.config

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

class JwtUser(
    private val id: String,
    private val authorities: List<GrantedAuthority> = emptyList(),
    private var authenticated: Boolean = true,
) : Authentication {

    override fun getAuthorities() = authorities

    override fun setAuthenticated(isAuthenticated: Boolean) {
        authenticated = isAuthenticated
    }

    override fun getName() = id

    override fun getCredentials() = authorities

    override fun getPrincipal() = id

    override fun isAuthenticated() = authenticated

    override fun getDetails() = id
}
