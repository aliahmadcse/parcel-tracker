package codes.aliahmad.parcel.tracker.dao.repository.impl;

import codes.aliahmad.parcel.tracker.dao.document.Client;
import codes.aliahmad.parcel.tracker.dao.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepository
{
  private final MongoTemplate mongoTemplate;

  @Override
  public Client save(Client client)
  {
    return mongoTemplate.save(client);
  }

  @Override
  public Optional<Client> findByEmail(String email)
  {
    Query query = new Query();
    query.addCriteria(Criteria.where("email").is(email));
    return Optional.ofNullable(mongoTemplate.findOne(query, Client.class));
  }

  @Override
  public Page<Client> findAll(Pageable pageable)
  {
    Query query = new Query().with(pageable);
    List<Client> clients = mongoTemplate.find(query, Client.class);
    long total = mongoTemplate.count(new Query(), Client.class);

    return new PageImpl<>(clients, pageable, total);
  }
}
