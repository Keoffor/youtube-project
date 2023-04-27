package com.kentechies.youtube.youtubeclone.controller;

import com.kentechies.youtube.youtubeclone.dto.VideoDTO;
import com.kentechies.youtube.youtubeclone.dto.VideoUploadResponse;
import com.kentechies.youtube.youtubeclone.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class VideoController {
    private final VideoService videoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VideoUploadResponse uploadFile(@RequestParam("file") MultipartFile file){
      return videoService.uploadVideos(file);
    }


    @PostMapping(value = "/thumbnail")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadThumbnial(@RequestParam("file") MultipartFile file, @RequestParam("videoId")String videoId){
        return videoService.uploadThumbnails(file, videoId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public VideoDTO editVideoMetadata(@RequestBody VideoDTO videoDTO){
      return videoService.editVideo(videoDTO);
    }
}
