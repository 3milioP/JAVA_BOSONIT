package com.ejercicio.block16springcloud.Backend.Cliente.Application;

import com.ejercicio.block16springcloud.Backend.Cliente.Controller.DTO.ClienteInputDTO;
import com.ejercicio.block16springcloud.Backend.Cliente.Controller.DTO.ClienteOutputDTO;
import com.ejercicio.block16springcloud.Backend.Cliente.Domain.Cliente;

import java.util.List;

public interface ClienteService {
    ClienteOutputDTO addCliente(ClienteInputDTO clienteInputDTO);
    ClienteOutputDTO getClienteById(int id);
    List<ClienteOutputDTO> getAllClientes(int pageNumber, int pageSize);
    ClienteOutputDTO updateCliente(int id, ClienteInputDTO clienteInputDTO);
    void deleteCliente(int id);
}
