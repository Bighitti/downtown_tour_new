package it.unicam.ids.dciotti.downtowntour.mapper;

import it.unicam.ids.dciotti.downtowntour.dto.ContentDTO;
import it.unicam.ids.dciotti.downtowntour.model.Challenge;
import it.unicam.ids.dciotti.downtowntour.model.Content;
import it.unicam.ids.dciotti.downtowntour.model.Contributor;
import it.unicam.ids.dciotti.downtowntour.model.Curator;
import org.springframework.stereotype.Service;

@Service
public class ContentMapper {
    public Content model(ContentDTO dto, Contributor contributor, Challenge challenge, Curator curator) {
        Content content = contributor.writeContent(dto.getText(), challenge);
        content.setPublication(dto.getPublication());
        content.setAuthorized(curator);//TODO: verify
        return content;
    }
}
