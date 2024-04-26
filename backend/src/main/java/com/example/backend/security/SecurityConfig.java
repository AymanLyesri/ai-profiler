//package com.example.backend.security;
//
//public class SecurityConfig {
////
////    @Bean
////    public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
////        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
////        manager.createUser(User.withUsername("user")
////                .password(bCryptPasswordEncoder.encode("userPass"))
////                .roles("USER")
////                .build());
////        manager.createUser(User.withUsername("admin")
////                .password(bCryptPasswordEncoder.encode("adminPass"))
////                .roles("USER", "ADMIN")
////                .build());
////        return manager;
////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
//}
