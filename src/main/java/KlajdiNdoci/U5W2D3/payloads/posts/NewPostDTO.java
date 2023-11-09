package KlajdiNdoci.U5W2D3.payloads.posts;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

;import java.time.LocalDate;

public record NewPostDTO(
        @NotEmpty(message = "La categoria deve essere necessariamente inserito!")
        @Size(min = 3, max= 20, message = "La categoria deve avere tra i 3 e i 20 caratteri")
        String categoria,
        @NotEmpty(message = "Il titolo deve essere necessariamente inserito!")
        @Size(min = 1, message = "Il titolo deve avere almeno un carattere")
        String titolo,
        @NotEmpty(message = "Il contenuto deve essere necessariamente inserito!" )
        String contenuto,
        @NotEmpty(message = "La cover deve essere necessariamente inserita!")
        @Pattern(regexp = "^(http(s):\\/\\/.)[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)$", message = "L'URL della cover non è valido")
        String cover,
        @NotEmpty(message = "Il tempo di lettura deve essere necessariamente inserito!")
        @Pattern(regexp = "^[0-9]+$", message = "Inserisci un numero valido")
        String tempoDiLettura,
        @NotNull
        long utenteId
){}
