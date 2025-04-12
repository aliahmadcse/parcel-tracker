package codes.aliahmad.parcel.tracker.dao.repository;

import codes.aliahmad.parcel.tracker.dao.document.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ClientRepository
{
  Client save(Client client);

  Optional<Client> findByEmail(String email);

  Page<Client> findAll(Pageable pageable);
}
