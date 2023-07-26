package com.ejercicio.block16springcloud.Backend.Cliente.Application;

import com.ejercicio.block16springcloud.Backend.Cliente.Controller.DTO.ClienteInputDTO;
import com.ejercicio.block16springcloud.Backend.Cliente.Controller.DTO.ClienteOutputDTO;
import com.ejercicio.block16springcloud.Backend.Cliente.Domain.Cliente;
import com.ejercicio.block16springcloud.Backend.Cliente.Repository.ClienteRepository;
import com.ejercicio.block16springcloud.Backend.Exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public ClienteOutputDTO addCliente(ClienteInputDTO clienteInputDTO) {
        Cliente cliente = new Cliente(clienteInputDTO);
        clienteRepository.save(cliente);
        return cliente.clienteToClienteOutputDTO();
    }

    @Override
    public ClienteOutputDTO getClienteById(int id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + id));
        return cliente.clienteToClienteOutputDTO();
    }

    @Override
    public List<ClienteOutputDTO> getAllClientes(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        List<ClienteOutputDTO> clienteOutputDTOList = new ArrayList<>();
        return clienteRepository.findAll(pageRequest)
                .getContent()
                .stream()
                .map(Cliente::clienteToClienteOutputDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteOutputDTO updateCliente(int id, ClienteInputDTO clienteInputDTO) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();

            if (clienteInputDTO.getNombre() != null && clienteInputDTO.getApellido() != null && clienteInputDTO.getEdad() > 0 && clienteInputDTO.getEmail() != null && clienteInputDTO.getTelefono() > 0) {
                cliente.setNombre(clienteInputDTO.getNombre());
                cliente.setApellido(clienteInputDTO.getApellido());
                cliente.setEdad(clienteInputDTO.getEdad());
                cliente.setEmail(clienteInputDTO.getEmail());
                cliente.setTelefono(clienteInputDTO.getTelefono());
                clienteRepository.save(cliente);

                // Si lo deseas, puedes devolver una instancia de ClienteOutputDTO con los datos actualizados
                return new ClienteOutputDTO(cliente.getId(), cliente.getNombre(), cliente.getApellido(), cliente.getEdad(), cliente.getEmail(), cliente.getTelefono());
            } else {
                // Si los datos de entrada no son válidos, puedes lanzar una excepción o manejar el error de otra manera
                throw new IllegalArgumentException("Datos de entrada no válidos para actualizar el cliente.");
            }
        } else {
            // Si el cliente no existe, puedes lanzar una excepción específica o manejar el error de otra manera
            throw new EntityNotFoundException("Cliente no encontrado con ID: " + id);
        }
    }

    @Override
    public void deleteCliente(int id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + id));

        clienteRepository.delete(cliente);
    }

}
