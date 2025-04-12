package codes.aliahmad.parcel.tracker.dao.repository;

import codes.aliahmad.parcel.tracker.dao.document.Parcel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;

public interface ParcelRepository
{
  Parcel save(Parcel parcel);

  Optional<Parcel> findById(ObjectId id);

  List<Parcel> searchByQuery(Query query);
}
