package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.controller.handler.exceptions.NoSuchObjectException;
import com.gimaletdinov.exampleProject.dao.DocumentTypeRepository;
import com.gimaletdinov.exampleProject.dto.response.DocumentTypeListResponseDto;
import com.gimaletdinov.exampleProject.model.DocumentType;
import com.gimaletdinov.exampleProject.model.mapper.DocumentTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс реализация интерфейса DocumentTypeService. Реализация методов получения данных с БД и преобразования данных в формат ответа
 */
@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;

    private final DocumentTypeMapper documentTypeMapper;

    @Autowired
    public DocumentTypeServiceImpl(DocumentTypeRepository documentTypeRepository, DocumentTypeMapper documentTypeMapper) {
        this.documentTypeRepository = documentTypeRepository;
        this.documentTypeMapper = documentTypeMapper;
    }

    /**
     * @see DocumentTypeService#getAllDocumentTypes()
     * @return
     */
    @Override
    public List<DocumentTypeListResponseDto> getAllDocumentTypes() {
        List<DocumentType> documentTypeList=documentTypeRepository.findAll();

        return documentTypeMapper.toResponseDtoList(documentTypeList);
    }

    /**
     * @see DocumentTypeService#getDocumentTypeById(int)
     * @throws NoSuchObjectException ("Нет типа документа с code = " + id)
     * @return
     */
    @Override
    public DocumentType getDocumentTypeById(int id) {
        DocumentType documentType = documentTypeRepository.getById(id);
        if (documentType == null){
            throw new NoSuchObjectException("Нет типа документа с code = " + id);
        }
        return documentType;
    }
}
