package com.example.ejercicio.block11uploaddownloadfiles.fIchero.controller;

import com.example.ejercicio.block11uploaddownloadfiles.fIchero.domain.Fichero;
import com.example.ejercicio.block11uploaddownloadfiles.fIchero.domain.Ruta;
import com.example.ejercicio.block11uploaddownloadfiles.fIchero.repository.FicheroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;


@RestController
public class DownloadUploadController {
    @Autowired
    private FicheroRepository ficheroRepository;

    @Autowired
    Ruta ruta;

    @GetMapping("/downloadById/{id}")
    public ResponseEntity<Resource> downloadFileById(@PathVariable Integer id) throws FileNotFoundException, MalformedURLException {
        // Buscar el fichero por ID en la base de datos
        Optional<Fichero> ficheroOptional = ficheroRepository.findById(id);
        if (ficheroOptional.isPresent()) {
            Fichero fichero = ficheroOptional.get();

            // Obtener la ruta del fichero en disco

            String route = ruta.getRuta() + "/" + fichero.getNombre();

            // Crear el objeto Resource para la descarga del fichero
            Resource resource = new UrlResource("file:" + route);

            // Verificar si el archivo existe
            if (resource.exists()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fichero.getNombre() + "\"")
                        .body(resource);
            } else {
                throw new FileNotFoundException("El archivo no existe o la ruta es incorrecta: " + ruta);
            }
        } else {
            // Manejar el caso cuando no se encuentra el fichero por ID
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/downloadByName/{nombre}")
    public ResponseEntity<Resource> downloadFileByName(@PathVariable String nombre) throws FileNotFoundException, MalformedURLException {
        // Buscar el fichero por nombre en la base de datos
        Optional<Fichero> ficheroOptional = ficheroRepository.findByNombre(nombre);
        if (ficheroOptional.isPresent()) {
            Fichero fichero = ficheroOptional.get();

            // Obtener la ruta del fichero en disco
            String route = ruta.getRuta() + "/" + fichero.getNombre();

            // Crear el objeto Resource para la descarga del fichero
            Resource resource = new UrlResource("file:" + route);

            // Verificar si el archivo existe
            if (resource.exists()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fichero.getNombre() + "\"")
                        .body(resource);
            } else {
                throw new FileNotFoundException("El archivo no existe o la ruta es incorrecta: " + ruta);
            }
        } else {
            // Manejar el caso cuando no se encuentra el fichero por nombre
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/setpath")
    public void setUploadDirectory(@RequestParam("path") String directorio) {
        ruta.setRuta(directorio);
    }

    @PostMapping("/upload/{tipo}")
    public Fichero uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String tipo) {
        // Verificar la extensión del archivo
        String extension = getFileExtension(file.getOriginalFilename());
        if (!extension.equalsIgnoreCase(tipo)) {
            // Manejar el error en caso de extensión inválida
        }

        // Obtener el directorio donde se deben guardar los ficheros
        String directorio = ruta.getRuta();

        // Guardar el archivo en disco
        String ruta = directorio + "/" + file.getOriginalFilename();
        try {
            file.transferTo(new File(ruta));

            // Crear la entidad Fichero y guardarla en la base de datos
            Fichero fichero = new Fichero();
            fichero.setNombre(file.getOriginalFilename());
            fichero.setFechaSubida(LocalDateTime.now());
            fichero.setCategoria(tipo);
            ficheroRepository.save(fichero);

            return fichero;
        } catch (IOException e) {
        }

        return null;
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }
}