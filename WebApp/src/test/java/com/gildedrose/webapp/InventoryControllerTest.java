package com.gildedrose.webapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void healthEndpointReturnsUp() throws Exception {
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }

    @Test
    void updateEndpointReturnsUpdatedInventory() throws Exception {
        String payload = """
                [
                  {"name":"Aged Brie","sellIn":2,"quality":0},
                  {"name":"Sulfuras, Hand of Ragnaros","sellIn":0,"quality":80}
                ]
                """;

        mockMvc.perform(post("/api/items/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].quality").value(1))
                .andExpect(jsonPath("$[1].quality").value(80));
    }

    @Test
    void demoEndpointReturnsInventory() throws Exception {
        mockMvc.perform(get("/api/items/demo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Aged Brie"));
    }

    @Test
    void invalidInventoryRequestIsRejected() throws Exception {
        String payload = """
                [
                  {"name":"","sellIn":-1,"quality":-1}
                ]
                """;

        mockMvc.perform(post("/api/items/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest());
    }
}
