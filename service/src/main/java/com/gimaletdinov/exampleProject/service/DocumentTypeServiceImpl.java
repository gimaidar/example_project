package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.exception.NoSuchObjectException;
import com.gimaletdinov.exampleProject.mapper.DocumentTypeMapper;
import com.gimaletdinov.exampleProject.dao.DocumentTypeRepository;
import com.gimaletdinov.exampleProject.model.DocumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gimaletdinov.exampleProject.dto.response.DocumentTypeListResponseDto;

import java.util.List;
import java.util.Optional;

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
     * @see DocumentTypeService#getDocumentTypeByCode(String)
     * @throws NoSuchObjectException ("Нет типа документа с code = " + id)
     * @return
     * @param code
     */
    @Override
    public DocumentType getDocumentTypeByCode(String code) {
        Optional<DocumentType> documentTypeOptional = documentTypeRepository.findByCode(code) ;
        if (documentTypeOptional.isEmpty()){
            throw new NoSuchObjectException("Нет типа документа с code = " + code);
        }
        return documentTypeOptional.get();
    }
}
