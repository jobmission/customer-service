package com.revengemission.customerservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.*;


@Controller
public class FileServiceController {

    @Value("${storage.location.public}")
    private String publicStorageLocation;

    @Value("${host.image}")
    private String hostImage;

    @PostMapping("/upload/public")
    @ResponseBody
    public Map<String, Object> handleFileUpload(@RequestPart(value = "files", required = false) List<MultipartFile> files,
                                                Principal principal) {
        Map<String, Object> result = new HashMap<>();
        Set<String> fileNames = new LinkedHashSet<>();
        if (files != null && files.size() > 0) {
            files.forEach(multipartFile -> {
                store(Paths.get(publicStorageLocation), multipartFile);
                fileNames.add(multipartFile.getOriginalFilename());
            });
            result.put("status", 1);
            result.put("files", fileNames);
            result.put("hostImage", hostImage);
        } else {
            result.put("status", 0);
        }

        return result;
    }

    public void store(Path directoryPath, MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectories(directoryPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            if (file.isEmpty()) {
                return;
            }
            if (filename.contains("..")) {
                // This is a security check
                return;
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, directoryPath.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
