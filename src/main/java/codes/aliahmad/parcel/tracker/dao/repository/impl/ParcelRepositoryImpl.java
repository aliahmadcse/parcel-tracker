package codes.aliahmad.parcel.tracker.dao.repository.impl;

import codes.aliahmad.parcel.tracker.dao.document.Parcel;
import codes.aliahmad.parcel.tracker.dao.repository.ParcelRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ParcelRepositoryImpl implements ParcelRepository
{
  private final MongoTemplate mongoTemplate;


  @Override
  public Parcel save(Parcel parcel)
  {
    return mongoTemplate.save(parcel);
  }

  @Override
  public Optional<Parcel> findById(ObjectId id)
  {
    return Optional.ofNullable(mongoTemplate.findById(id, Parcel.class));
  }

  @Override
  public List<Parcel> searchByQuery(Query query)
  {
    return mongoTemplate.find(query, Parcel.class);
  }


}
