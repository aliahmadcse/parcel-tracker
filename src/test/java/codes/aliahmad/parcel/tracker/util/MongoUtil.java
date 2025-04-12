package codes.aliahmad.parcel.tracker.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MongoUtil
{
  private static final ObjectMapper objectMapper = new ObjectMapper();

  public static <T> void insertDocuments(
          String scriptFileName,
          TypeReference<List<T>> typeReference,
          MongoTemplate mongoTemplate,
          String collectionName
  )
  {
    try
    {
      objectMapper.registerModule(new JavaTimeModule());
      InputStream inputStream = MongoUtil.class.getClassLoader().getResourceAsStream(scriptFileName);
      if (inputStream != null)
      {
        List<T> documents = objectMapper.readValue(inputStream, typeReference);
        mongoTemplate.insert(documents, collectionName);
      }
      else
      {
        log.error("Resource not found: {}", scriptFileName);
      }
    }
    catch (IOException exception)
    {
      log.error("Exception while inserting the documents: {}", exception.getMessage(), exception);
    }
  }

  public static void deleteCollectionData(MongoTemplate mongoTemplate, String collectionName)
  {
    mongoTemplate.remove(new Query(), collectionName);
  }
}
