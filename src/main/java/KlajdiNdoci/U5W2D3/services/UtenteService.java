package KlajdiNdoci.U5W2D3.services;

import KlajdiNdoci.U5W2D3.config.EmailSender;
import KlajdiNdoci.U5W2D3.entities.Utente;
import KlajdiNdoci.U5W2D3.exceptions.NotFoundException;
import KlajdiNdoci.U5W2D3.payloads.users.NewUserDTO;
import KlajdiNdoci.U5W2D3.repositories.UtenteRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    EmailSender emailSender;

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
        Utente savedUser = utenteRepository.save(newUser);
        emailSender.sendRegistrationEmail(body.email());
        return savedUser;
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
    public String uploadPicture(MultipartFile file, long id) throws IOException {
        Utente utente = utenteRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        String url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        utente.setAvatar(url);
        utenteRepository.save(utente);
        return url;
    }
}
