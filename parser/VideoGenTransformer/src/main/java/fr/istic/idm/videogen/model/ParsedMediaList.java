package fr.istic.idm.videogen.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParsedMediaList {

	private int id;
	
	private List<ParsedMedia> parsedMedias = new ArrayList<>();
}
