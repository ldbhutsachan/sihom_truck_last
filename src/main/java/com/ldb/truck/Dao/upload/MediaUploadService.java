package com.ldb.truck.Dao.upload;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface MediaUploadService {

    public String uploadMedia(MultipartFile file);
    public String uploadMediaStaff(MultipartFile file);
    public String uploadMediacar(MultipartFile file);
}
