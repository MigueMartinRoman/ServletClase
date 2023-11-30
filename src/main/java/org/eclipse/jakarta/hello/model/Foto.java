package org.eclipse.jakarta.hello.model;

import lombok.Data;

@Data
public class Foto {
    private Long id;
    private String url;
    private String nombre;
    private String descripcion;
    private Boolean privada;
}
