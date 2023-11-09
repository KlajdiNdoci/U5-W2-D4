package KlajdiNdoci.U5W2D3.controllers;

import KlajdiNdoci.U5W2D3.entities.Utente;
import KlajdiNdoci.U5W2D3.exceptions.BadRequestException;
import KlajdiNdoci.U5W2D3.payloads.users.NewUserDTO;
import KlajdiNdoci.U5W2D3.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/utenti")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;


    @GetMapping("")
    public Page<Utente> getUser(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String orderBy){
        return utenteService.getUtenti(page, size, orderBy);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente saveUtente(@RequestBody @Validated NewUserDTO body, BindingResult validation) {
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }else {
            try{
                return utenteService.save(body);
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }

    @GetMapping("/{id}")
    public Utente findById(@PathVariable long id) {
        return utenteService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int id) {
        utenteService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    public Utente findByIdAndUpdate(@PathVariable int id, @RequestBody Utente body) {
        return utenteService.findByIdAndUpdate(id, body);
    }

    @PostMapping("/{id}/upload")
    public String upload(@RequestParam("avatar") MultipartFile body, @PathVariable long id) throws IOException {
        return utenteService.uploadPicture(body, id);
    }
}
