package com.api.apibackend.Midia.Application.DTOs;

import java.util.Date;

import lombok.Data;

@Data
public class MidiaDTO {
    private Long id_image;
    private String url;
    private Date dateCreate;
    private String category;
}
