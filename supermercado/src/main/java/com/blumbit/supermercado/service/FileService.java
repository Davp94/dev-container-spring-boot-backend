package com.blumbit.supermercado.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blumbit.supermercado.dto.request.FileRequest;
import com.blumbit.supermercado.dto.response.FileDownloadResponse;
import com.blumbit.supermercado.dto.response.FileResponse;

@Service
public class FileService implements IFileService{

    @Value("${file.path}")
    private String uploadDir;

    private Path fileStorageLocation;

    @Override
    public FileResponse createFile(FileRequest fileRequest) {
        //TODO validate file relation uploadDir fileStorage
        MultipartFile file = fileRequest.getFile();
        String uniqueFileName = generateUniqueFileName(file.getOriginalFilename());
        try {
            Path targetLocation = fileStorageLocation.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return FileResponse.builder().filePath(uniqueFileName).build();
        } catch (IOException e) {
            throw e;
        }
    }

    @Override
    public File retrieveFile(FileResponse fileResponse) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retrieveFile'");
    }

    @Override
    public FileDownloadResponse fileDownload(String filePath) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fileDownload'");
    }

    private String generateUniqueFileName(String originalFileName){
        if(originalFileName == null){
            originalFileName = "file";
        }
        String timestamp=new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
        String uuid = UUID.randomUUID().toString().substring(0,8);

        return timestamp + "_" + uuid + "_" + originalFileName;
    }


}
