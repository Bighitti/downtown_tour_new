package it.unicam.ids.dciotti.downtowntour.mapper;

import it.unicam.ids.dciotti.downtowntour.dto.ContentDTO;
import it.unicam.ids.dciotti.downtowntour.dto.CuratorDTO;
import it.unicam.ids.dciotti.downtowntour.entity.ContentEntity;
import it.unicam.ids.dciotti.downtowntour.model.Challenge;
import it.unicam.ids.dciotti.downtowntour.model.Content;
import it.unicam.ids.dciotti.downtowntour.model.Contributor;
import it.unicam.ids.dciotti.downtowntour.model.Curator;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class ContentMapper implements DefaultMapper<ContentDTO, Content, ContentEntity> {
    public final ContributorMapper contributorMapper;

    @Autowired
    public ContentMapper(ContributorMapper contributorMapper) {
        this.contributorMapper = contributorMapper;
    }

    public Content model(ContentDTO dto, Contributor contributor, Challenge challenge, Curator curator) {
        Content content = contributor.writeContent(dto.getText(), challenge);
        content.setPublication(dto.getPublication());
        content.setAuthorized(curator);//TODO: verify
        return content;
    }

    public ContentDTO dto(ContentEntity entity) {
        ContentDTO dto = new ContentDTO();
        dto.setId(entity.getId());
        dto.setText(entity.getText());
        dto.setPublication(new Date(entity.getPublication().getTime()));
        dto.setContributorId(entity.getContributor().getId());
        try {
            dto.setAuthorizedById(entity.getAuthorizedBy().getId());
        } catch (NullPointerException ignore) {}
        return dto;
    }

    @Override
    public ContentDTO dto(Content content) {
        throw (RuntimeException) new RuntimeException().initCause(new ExecutionControl.NotImplementedException(""));
    }

    @Override
    public Content modelFromDto(ContentDTO contentDTO) {
        throw (RuntimeException) new RuntimeException().initCause(new ExecutionControl.NotImplementedException(""));
    }

    @Override
    public Content modelFromEntity(ContentEntity contentEntity) {
        throw (RuntimeException) new RuntimeException().initCause(new ExecutionControl.NotImplementedException(""));
    }

    @Override
    public ContentEntity entity(Content content) {
        throw (RuntimeException) new RuntimeException().initCause(new ExecutionControl.NotImplementedException(""));
    }

    public ContentEntity entity(ContentDTO contentDTO) {
        ContentEntity contentEntity = new ContentEntity();
        contentEntity.setId(contentDTO.getId());
        contentEntity.setText(contentDTO.getText());
        contentEntity.setPublication(new Timestamp(contentDTO.getPublication().getTime()));
        return contentEntity;
    }
}
