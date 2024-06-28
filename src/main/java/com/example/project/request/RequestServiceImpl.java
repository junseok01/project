package com.example.project.request;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class RequestServiceImpl implements RequestService {
    @Autowired
    RequestDAO requestDAO;
    @Autowired
    FileDAO fileDAO;

    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private FileEntityRepository fileEntityRepository;

    private final ModelMapper modelMapper;


    public RequestServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public void update(RequestDTO DTO) {

    }


    @Override
    public RequestDTO searchById(Long id) {
        RequestEntity requestEntity = requestDAO.searchById(id);
        RequestDTO requestDTO = modelMapper.map(requestEntity,RequestDTO.class);
        return requestDTO;
    }


    @Override
    public List<RequestDTO> showRequest() {
        List<RequestEntity> entities = requestDAO.showRequest();
        List<RequestDTO> requestDTOList = entities.stream()
                        .map(entity -> modelMapper.map(entity, RequestDTO.class))
                        .collect(Collectors.toList());
        System.out.println("리퀘스트 서비스 임플의 쇼 리퀘스트" + requestDTOList);
        return requestDTOList;
    }




    @Override
    @Transactional
    public void deleteByLoginId(String loginId) {
        requestDAO.deleteByLoginId(loginId);
    }

    @Override
    public void deleteById(Long id) {
        requestDAO.deleteById(id);
    }
    @Transactional
    @Override
    public RequestDTO saveUploadFile(String uploadDirectory, RequestEntity requestEntity, List<MultipartFile> files) throws IOException{
        //컨트롤러에서 DTO->Entity변환후 DB에 RequestDTO저장하기
        requestEntity = requestDAO.saveRequest(requestEntity);
        System.out.println("업로드 파일 위치" + uploadDirectory);
        //PDF파일을 지정된 폴더에 UUID+확장자로 이름을 설정하여 저장후 fileEntity생성
        for (MultipartFile file : files) {
            String uuid = UUID.randomUUID().toString();
            String name = file.getOriginalFilename();
            String fileExtension = name.substring(name.lastIndexOf("."));
            String fileName = uuid + fileExtension;

            System.out.println("파일이름바꾸어 저장");
            //지정된 경로에 파일저장
            Path filepath = Paths.get(uploadDirectory, fileName);
            String newFilePath = filepath.toString().replace("\\","/");
            System.out.println(filepath.toString());
            System.out.println(newFilePath);
            Files.write(filepath,file.getBytes());
            System.out.println("파일이름바꾸어 저장완료");

            //파일 엔티티 생성
            //requestEntity를 셋팅함으로 RequestEntity의 자녀라는걸 알수있다.
            FileEntity fileEntity = new FileEntity(requestEntity,name,fileName,filepath.toString());
            //생성된 엔티티 DB에 저장
            fileDAO.save(fileEntity);

        }
        RequestDTO requestDTO = modelMapper.map(requestEntity,RequestDTO.class);
        return requestDTO;
    }

    @Override
    public List<RequestDTO> requestListByState(String state) {
        List<RequestEntity> requestEntityList = requestDAO.requestListByState(state);
        List<RequestDTO> requestDTOList = requestEntityList.stream()
                .map(requestEntity -> modelMapper.map(requestEntity, RequestDTO.class))
                .collect(Collectors.toList());
        return requestDTOList;
    }

    @Override
    public void updateState(Long id) {
        requestDAO.updateState(id);
    }

    @Override
    public void updateAllState(String loginId) {
        requestDAO.updateAllState(loginId);
    }

    @Override
    public Page<RequestDTO> getRequestPage(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);


        Page<RequestEntity> requestEntities = requestDAO.getRequestPage(page,size,pageable);

        List<RequestDTO> requestDTOList = requestEntities.getContent()
                .stream()
                .map(requestEntity -> modelMapper.map(requestEntity, RequestDTO.class))
                .collect(Collectors.toList());

        return new PageImpl<>(requestDTOList, pageable, requestEntities.getTotalElements());
    }

    @Override
    public Page<RequestDTO> getRequestPageByState(int page, int size, String state) {
        Pageable pageable = PageRequest.of(page,size);
        Page<RequestEntity> requestEntitiesByState = requestDAO.getRequestPageByState(page,size,state,pageable);

        List<RequestDTO> requestDTOList = requestEntitiesByState.getContent()
                .stream()
                .map(requestEntity -> modelMapper.map(requestEntity, RequestDTO.class))
                .collect(Collectors.toList());

        return new PageImpl<>(requestDTOList, pageable, requestEntitiesByState.getTotalElements());
    }


}

