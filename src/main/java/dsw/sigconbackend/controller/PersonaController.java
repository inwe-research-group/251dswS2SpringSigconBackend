package dsw.sigconbackend.controller;

import dsw.sigconbackend.dto.NPersonasXTipoDocumento;
import dsw.sigconbackend.dto.PersonaRequest;
import dsw.sigconbackend.dto.PersonaResponse;
import dsw.sigconbackend.service.PersonaService;
import dsw.sigconbackend.utils.ErrorResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/persona")
public class PersonaController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    PersonaService personaService;
    
    @GetMapping
    public ResponseEntity<?> getPersonas(){
        List<PersonaResponse> listaPersonaResponse=null;
        try{
            listaPersonaResponse=personaService.listPersonas();
            
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaPersonaResponse.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persona not found").build());
        return ResponseEntity.ok(listaPersonaResponse);        
    }
    @PostMapping()
    public ResponseEntity<?> insertPersona(@RequestBody PersonaRequest personaRequest){
        logger.info(">insert " + personaRequest.toString());
        PersonaResponse personaResponse;
        try{
            personaResponse=personaService.insertPersona(personaRequest);
            
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(personaResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persona not insert").build());
        return ResponseEntity.ok(personaResponse);                
    }
    @PutMapping()
    public ResponseEntity<?> updatePersona(@RequestBody PersonaRequest personaRequest){
        logger.info(">update " + personaRequest.toString());
        PersonaResponse personaResponse;
        try{
            //validar que la persona exista
            personaResponse=personaService.findPersona(personaRequest.getIdPersona());
            if (personaResponse==null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persona not found").build());
            
            personaResponse=personaService.updatePersona(personaRequest);
            
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(personaResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persona not update").build());
        return ResponseEntity.ok(personaResponse);                
    }
    @DeleteMapping()
    public ResponseEntity<?> deletePersona(@RequestBody PersonaRequest personaRequest){
        logger.info(">delete " + personaRequest.toString());
        PersonaResponse personaResponse;
        try{
            //validar que la persona exista
            personaResponse=personaService.findPersona(personaRequest.getIdPersona());
            if (personaResponse==null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persona not found for delete").build());
            
            personaService.deletePersona(personaRequest.getIdPersona());
            
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(personaResponse);                
    }
    @GetMapping("/find")
    public ResponseEntity<?> findPersonaById(@RequestBody PersonaRequest personaRequest){
        logger.info(">find " + personaRequest.toString());
        PersonaResponse personaResponse;
        try{
            personaResponse=personaService.findPersona(personaRequest.getIdPersona());
            
        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(personaResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persona not found").build());
        return ResponseEntity.ok(personaResponse);                
    }
    @GetMapping("/NPersonasXTipoDocumento")
    public ResponseEntity<?> getNPersonasXTipoDocumento(){
        List<NPersonasXTipoDocumento> listaNPersonasXTipoDocumento=null;
        try{
            listaNPersonasXTipoDocumento=personaService.getNPersonasXTipoDocumento();

        }catch(Exception e){
            logger.error("Error inesperado",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(listaNPersonasXTipoDocumento.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("NPersonasXTipoDocumento not found").build());
        return ResponseEntity.ok(listaNPersonasXTipoDocumento);
    }
}
