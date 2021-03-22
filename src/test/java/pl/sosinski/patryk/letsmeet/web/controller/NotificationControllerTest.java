package pl.sosinski.patryk.letsmeet.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.sosinski.patryk.letsmeet.web.controller.ControllerConstants.NOTIFICATIONS_URI;

@SpringBootTest
@AutoConfigureMockMvc
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenUriNotifications_whenGet_thenStatusIsOk() throws Exception {
        //Given

        //When
        mockMvc.perform(get(NOTIFICATIONS_URI))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //Then
        //NOTE:Assert is made by andExpect() method
    }
}