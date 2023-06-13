package net.azeti.challenge.recipe.security.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SecurityConfigurationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }
    @Test
    @WithMockUser
    void securedEndpointsAreAccessibleWithMockUser() throws Exception {
        mockMvc.perform(get("/api/recipes/")).andExpect(status().isOk());
        mockMvc.perform(get("/api/recipes/1")).andExpect(status().isOk());
        mockMvc.perform(get("/api/recipes/users/search?username=John")).andExpect(status().isOk());
        mockMvc.perform(get("/api/recipes/search?title=pizza")).andExpect(status().isOk());
        mockMvc.perform(get("/api/recipes/recommended?temperature=5")).andExpect(status().isOk());
    }
    @Test
    void securedEndpointsAreNotAccessibleWithoutMockUser() throws Exception {
        mockMvc.perform(get("/api/recipes/")).andExpect(status().isUnauthorized());
        mockMvc.perform(get("/api/recipes/1")).andExpect(status().isUnauthorized());
        mockMvc.perform(get("/api/recipes/users/search?username=John")).andExpect(status().isUnauthorized());
        mockMvc.perform(get("/api/recipes/search?title=pizza")).andExpect(status().isUnauthorized());
        mockMvc.perform(get("/api/recipes/recommended?temperature=5")).andExpect(status().isUnauthorized());
    }
}
