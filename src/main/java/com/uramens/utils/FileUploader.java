package com.uramens.utils;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploader {

    String upload(MultipartFile multipartFile) throws IOException;

    void delete(String originalFileName);

    boolean isExist(String originalFileName);

}
