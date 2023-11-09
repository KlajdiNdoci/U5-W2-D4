package KlajdiNdoci.U5W2D3.utente;

import KlajdiNdoci.U5W2D3.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public Utente save(Utente body) {
        body.setAvatar("https://ui-avatars.com/api/?name=" + body.getNome() + "+" + body.getCognome());
        utenteRepository.save(body);
        return body;
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
