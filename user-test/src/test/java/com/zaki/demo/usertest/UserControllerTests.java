package com.zaki.demo.usertest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaki.demo.usertest.model.User;
import com.zaki.demo.usertest.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.File;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class UserControllerTests {
    private static final String ENTITY_API_URL = "/api/users";
    private static final String PATH_PAYLOAD = "src/test/payload";

    @Autowired
    public UserService userService;

    @Mock
    private ObjectMapper mapperUtil;

    @Autowired
    private MockMvc restUserMockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void initSetup() {
    }

    @Test
    void createUser() throws Exception {
        String className = this.getClass().getSimpleName();
        String jsonReqPath = PATH_PAYLOAD + className + ".user.req.json";

        User reqUser = objectMapper.readValue(new File(jsonReqPath), User.class);

        ResponseEntity responseEntity = this.createUserUtil(reqUser);


    }

    private ResponseEntity createUserUtil(User user) throws Exception {

        String className = this.getClass().getSimpleName();
        String jsonReqPath = PATH_PAYLOAD + className + ".user.req.json";

        User reqUser = objectMapper.readValue(new File(jsonReqPath), User.class);

        MvcResult mockUser = (MvcResult) restUserMockMvc
                .perform(
                        post(ENTITY_API_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtil.convertObjectToJsonBytes(reqUser))

                ).andExpect(status().isCreated()).andDo(print());

        return objectMapper.readValue(mockUser.getRequest().getContentAsString(), ResponseEntity.class);
    }


}
