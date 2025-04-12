package codes.aliahmad.parcel.tracker.integration.controller;

import codes.aliahmad.parcel.tracker.base.BaseIntegrationTest;
import codes.aliahmad.parcel.tracker.dao.document.Client;
import codes.aliahmad.parcel.tracker.dto.request.ClientRequest;
import codes.aliahmad.parcel.tracker.util.MongoUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClientControllerTest extends BaseIntegrationTest
{
  private static final String AUDIT_FILE = "init/mongo/client.json";

  @Autowired
  public MongoTemplate mongoTemplate;

  @Autowired
  private MockMvc mockMvc;

  private final ObjectMapper objectMapper = new ObjectMapper();


  @BeforeEach
  public void setup()
  {
    objectMapper.registerModule(new JavaTimeModule());
    MongoUtil.deleteCollectionData(mongoTemplate, "client");
    MongoUtil.insertDocuments(AUDIT_FILE, new TypeReference<List<Client>>()
    {
    }, mongoTemplate, "client");
  }

  @Test
  public void testCreateClient() throws Exception
  {
    ClientRequest clientRequest = new ClientRequest("Alice", "Johnson", "alice.johnson1@example.com", "1-202-555-0143");
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/client")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(clientRequest)))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.firstName").value("Alice"))
            .andExpect(jsonPath("$.lastName").value("Johnson"))
            .andExpect(jsonPath("$.email").value("alice.johnson1@example.com"))
            .andExpect(jsonPath("$.phone").value("1-202-555-0143"));
  }

  @Test
  public void testGetClientByEmail() throws Exception
  {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/client/bob.smith@example.com"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.firstName").value("Bob"))
            .andExpect(jsonPath("$.lastName").value("Smith"));
  }

  @Test
  public void testListClient() throws Exception
  {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/client?pageNo=1&recordPerPage=10"))
            .andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$.totalRecords").value(3));
  }

}
