package mif.psp.lab2;


import com.google.gson.Gson;
import mif.psp.lab2.controllers.AccountRestController;
import mif.psp.lab2.model.Account;
import mif.psp.lab2.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.NestedServletException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AccountRestController.class)
public class RestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountRepository mockRepository;

    private Account jonas;
    private Account petras;

    private String generateJson(Account account) {
        Gson gson = new Gson();
        return gson.toJson(account);
    }


    @BeforeEach
    public void setup() {
        jonas = new Account();
        jonas.setName("Jonas");
        jonas.setSurname("Jonaitis");
        jonas.setPhonenumber("+37060123456");
        jonas.setAddress("Naugarduko g. 23");
        jonas.setEmail("jonas@gmail.com");
        jonas.setPassword("Slaptazodis1!");

        petras = new Account();
        petras.setName("Petras");
        petras.setSurname("Petraitis");
        petras.setPhonenumber("35061234567");
        petras.setAddress("Naugarduko g. 24");
        petras.setEmail("petras@gmail.com");
        petras.setPassword("Slaptazodis1@");

    }


    @Test
    void createAccount_successful() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/account")
                .content(generateJson(jonas))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        verify(mockRepository).save(Mockito.any(Account.class));
    }

    @Test
    void createAccount_unSuccessful_invalidEmail() {
        jonas.setEmail("jonasgmail.com");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/account")
                .content(generateJson(jonas))
                .contentType(MediaType.APPLICATION_JSON);

        Exception exception = assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(requestBuilder)
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
        });

        assertEquals(exception.getCause().getMessage(),"Invalid email address: "+jonas.getEmail());
    }

    @Test
    void createAccount_unSuccessful_invalidPhoneNumber() {
        jonas.setPhonenumber("+3706125467as78");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/account")
                .content(generateJson(jonas))
                .contentType(MediaType.APPLICATION_JSON);

        Exception exception = assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(requestBuilder)
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
        });

        assertEquals(exception.getCause().getMessage(),"Invalid phone number: "+jonas.getPhonenumber());
    }

    @Test
    void createAccount_unSuccessful_invalidPassword() {
        jonas.setPassword("1234565");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/account")
                .content(generateJson(jonas))
                .contentType(MediaType.APPLICATION_JSON);

        Exception exception = assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(requestBuilder)
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
        });

        assertEquals(exception.getCause().getMessage(),"Invalid password");
    }

    @Test
    void findById_accountExists() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/account/"+jonas.getId())
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), generateJson(jonas));
    }

    @Test
    void findById_accountDoesntExist() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/account/22")
                .accept(MediaType.APPLICATION_JSON);

        Exception exception = assertThrows(Exception.class, () -> {
            mockMvc.perform(requestBuilder)
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
        });
        assertEquals(exception.getCause().getMessage(), "ID not found: 22");
    }

    @Test
    void deleteAccount_successful() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/account/1")
                .contentType(MediaType.APPLICATION_JSON);



        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        verify(mockRepository).deleteById(Mockito.anyLong());
    }

    @Test
    void deleteById_unsuccessful() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/account/" + 5)
                .accept(MediaType.APPLICATION_JSON);


        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }
}
