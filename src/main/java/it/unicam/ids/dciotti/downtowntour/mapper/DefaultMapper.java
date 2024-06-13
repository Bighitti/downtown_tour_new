package it.unicam.ids.dciotti.downtowntour.mapper;

import java.util.*;

public interface DefaultMapper<DTO,MODEL,ENTITY> {
    DTO dto(MODEL model);

    default List<DTO> dto(List<MODEL> modelList) {
        if (modelList == null) return new ArrayList<>();
        List<DTO> dtoList = new ArrayList<>(modelList.size());
        modelList.forEach(model -> dtoList.add(dto(model)));
        return dtoList;
    }

    MODEL modelFromDto(DTO dto);

    default List<MODEL> modelFromDto(List<DTO> dtoList) {
        if (dtoList == null) return new ArrayList<>();
        List<MODEL> modelList = new ArrayList<>(dtoList.size());
        for (DTO dto : dtoList) modelList.add(modelFromDto(dto));
        return modelList;
    }

    MODEL modelFromEntity(ENTITY entity);

    default List<MODEL> modelFromEntity(List<ENTITY> entityList) {
        if (entityList == null) return new ArrayList<>();
        List<MODEL> modelList = new ArrayList<>(entityList.size());
        for (ENTITY entity : entityList) modelList.add(modelFromEntity(entity));
        return modelList;
    }

    ENTITY entity(MODEL model);

    default List<ENTITY> entity(List<MODEL> modelList) {
        if (modelList == null) return new ArrayList<>();
        List<ENTITY> entityList = new ArrayList<>(modelList.size());
        modelList.forEach(model -> entityList.add(entity(model)));
        for (MODEL model : modelList) entityList.add(entity(model));
        return entityList;
    }
}
