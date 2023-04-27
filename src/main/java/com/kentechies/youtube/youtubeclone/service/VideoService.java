package com.kentechies.youtube.youtubeclone.service;

import com.kentechies.youtube.youtubeclone.dto.VideoDTO;
import com.kentechies.youtube.youtubeclone.dto.VideoUploadResponse;
import com.kentechies.youtube.youtubeclone.model.Video;
import com.kentechies.youtube.youtubeclone.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final S3Service s3Service;
    private final VideoRepository videoRepository;

    public VideoUploadResponse uploadVideos(MultipartFile multipartFile){
       String videourl = s3Service.uploadFile(multipartFile);
       var video = new Video();
       video.setVideourl(videourl);
       var savedVideo = videoRepository.save(video);
       return new VideoUploadResponse(savedVideo.getId(), savedVideo.getVideourl());
    }

    public VideoDTO editVideo(VideoDTO videoDTO) {
        //find the video by id
       Video videoSaved = getVideoById(videoDTO.getId());

        // map the videoDTo to video
          videoSaved.setVideourl(videoDTO.getVideourl());
          videoSaved.setDescription(videoDTO.getDescription());
          videoSaved.setTags(videoDTO.getTags());
          videoSaved.setStatus(videoDTO.getVideoStatus());
          videoSaved.setThumbnailurl(videoDTO.getThumbnailurl());
          videoSaved.setTitle(videoDTO.getTitle());
        //save the video
        videoRepository.save(videoSaved);
        return videoDTO;
    }

    public String uploadThumbnails(MultipartFile file, String videoId) {
      var savedVideo = getVideoById(videoId);
      String thumbnailUrl = s3Service.uploadFile(file);
      savedVideo.setThumbnailurl(thumbnailUrl);
      videoRepository.save(savedVideo);
      return thumbnailUrl;
    }
    Video getVideoById(String id){
       return videoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("cannot find video by "+ id));
    }
}
