package br.com.carv.sales.sales.controller;


import br.com.carv.sales.sales.entities.Client;
import br.com.carv.sales.sales.repositories.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testMessage() {
        return "Test Controller Client";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findClientById/{id}")
    public ResponseEntity<Client> findClientById(@PathVariable Integer id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveClient")
    public ResponseEntity<Client> saveClient(@RequestBody @Valid Client client) {
        Client clientToSave = clientRepository.save(client);
        return ResponseEntity.ok(clientToSave);
    }

    @RequestMapping(method= RequestMethod.DELETE, value = "/deleteClientById/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable Integer id) {
        Optional<Client> client = clientRepository.findById(id);
        if(client.isPresent()) {
            clientRepository.delete(client.get());
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/testUpdateClientById/{id}")
    public ResponseEntity<Client> testUpdateClientById(@PathVariable Integer id){
        Optional<Client> client = clientRepository.findById(id);
        if(client.isPresent()) {
            clientRepository.save(client.get());
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateClientById/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Integer id, @RequestBody @Valid Client client) {
       Optional<Client> clientToUpdate = clientRepository.findById(id);
       if(clientToUpdate.isPresent()) {
           clientToUpdate.get().setNameClient(client.getName());
           clientRepository.save(client);
           return ResponseEntity.noContent().build();
       } else {
           return ResponseEntity.notFound().build();
       }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findAllClients")
    public List<Client> findAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients;
    }

   @RequestMapping(method = RequestMethod.GET, value = "/findClient")
    public List<Client> findClient(@RequestBody Client value) {
        List<Client> clients = clientRepository.findAll();
        List<Client> clientResult = new ArrayList<Client>();

        if(!clients.isEmpty()) {
            for (Client client : clients) {
                if(client.getName().contains(value.getName())) {
                    clientResult.add(client);
                }
            }
            return clientResult;
        }
        return (List<Client>) ResponseEntity.notFound().build();

    }





}
