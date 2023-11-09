package KlajdiNdoci.U5W2D3.services;

import KlajdiNdoci.U5W2D3.entities.Utente;
import KlajdiNdoci.U5W2D3.exceptions.NotFoundException;
import KlajdiNdoci.U5W2D3.payloads.posts.NewPostDTO;
import KlajdiNdoci.U5W2D3.payloads.users.NewUserDTO;
import KlajdiNdoci.U5W2D3.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public Page<Utente> getUtenti(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return utenteRepository.findAll(pageable);
    }

    public Utente save(NewUserDTO body) throws IOException {
        Utente newUser = new Utente();
        newUser.setAvatar("https://ui-avatars.com/api/?name=" + body.nome() + "+" + body.cognome());
        newUser.setNome(body.nome());
        newUser.setCognome(body.cognome());
        newUser.setEmail(body.email());
        utenteRepository.save(newUser);
        return newUser;
    }

    public Utente findById(long id) throws NotFoundException{
        Utente found = null;
        for (Utente user : utenteRepository.findAll()) {
            if (user.getId() == id) {
                found = user;
            }
        }
        if (found == null) {
            throw new NotFoundException(id);
        } else {
            return found;
        }
    }

    public void findByIdAndDelete(int id)throws NotFoundException {
       Utente found = this.findById(id);
       utenteRepository.delete(found);
    }

    public Utente findByIdAndUpdate(int id, Utente body) throws NotFoundException{
        Utente found = null;

        for (Utente user : utenteRepository.findAll()) {
            if (user.getId() == id) {
                found = user;
                found.setNome(body.getNome());
                found.setCognome(body.getCognome());
                found.setAvatar("https://ui-avatars.com/api/?name=" + body.getNome() + "+" + body.getCognome());
                found.setEmail(body.getEmail());
                found.setDataDiNascita(body.getDataDiNascita());
            }
        }
        if (found == null) {
            throw new NotFoundException(id);
        } else {
            return utenteRepository.save(found);
        }
    }
}
