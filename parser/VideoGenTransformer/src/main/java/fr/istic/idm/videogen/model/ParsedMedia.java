package fr.istic.idm.videogen.model;

import lombok.Data;

@Data
public class ParsedMedia {

    private String fileName;
    private boolean active;
    private String type;
    private int totalAlternative;
}
