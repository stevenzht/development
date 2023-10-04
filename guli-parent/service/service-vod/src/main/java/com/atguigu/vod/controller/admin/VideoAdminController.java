package com.atguigu.vod.controller.admin;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.atguigu.commonutils.R;
import com.atguigu.vod.Utils.AliyunVodSDKUtils;
import com.atguigu.vod.Utils.ConstantPropertiesUtil;
import com.atguigu.vod.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author steven
 * @create 2023-09-25 10:25
 */
@Api(description = "阿里云视频点播微服务")
@CrossOrigin
@RestController
@RequestMapping("/admin/void/video")
public class VideoAdminController {

    @Autowired
    private VideoService videoService;

    @PostMapping("upload")
    public R uploadVideo(
            @ApiParam(name = "file",value = "文件",required = true)
            @RequestParam("file")MultipartFile file
            ) throws Exception{
        String videoId = videoService.uploadVideo(file);
        return R.ok().message("视频上传成功").data("videoId",videoId);
    }

    @DeleteMapping("{videoId}")
    public R removeVideo(
            @ApiParam(name = "videoId",value = "云端视频id",required = true)
            @PathVariable String videoId
    ){
        videoService.removeVideo(videoId);
        return R.ok().message("视频删除成功");
    }

    @GetMapping("get-play-auth/{videoId}")
    public R getVideoPlayAuth(@PathVariable("videoId") String videoId) throws Exception {

        //获取阿里云存储相关常量
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;

        //初始化
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(accessKeyId, accessKeySecret);

        //请求
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoId);

        //响应
        GetVideoPlayAuthResponse response = client.getAcsResponse(request);

        //得到播放凭证
        String playAuth = response.getPlayAuth();

        //返回结果
        return R.ok().message("获取凭证成功").data("playAuth", playAuth);
    }
}
