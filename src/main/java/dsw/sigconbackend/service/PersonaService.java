package dsw.sigconbackend.service;

import dsw.sigconbackend.dto.PersonaResponse;
import dsw.sigconbackend.repository.PersonaRepository;
import dsw.sigconbackend.repository.TipoDocumentoRepository;
import dsw.sigconbackend.repository.UbigeoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {
    @Autowired
    PersonaRepository personaRepository;
    @Autowired
    UbigeoRepository ubigeoRepository;
    @Autowired
    TipoDocumentoRepository tipoDocumentoRepository;
    
    public List<PersonaResponse> listPersonas(){
        return PersonaResponse.fromEntities(personaRepository.findAll());
    }
    
}
