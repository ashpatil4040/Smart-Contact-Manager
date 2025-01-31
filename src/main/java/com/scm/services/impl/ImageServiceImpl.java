package com.scm.services.impl;


import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.helper.AppConstants;
import com.scm.services.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

    
    private Cloudinary cloudinary;

    public ImageServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile contactImage , String filename) {
        
        //code to upload img on server
      

        try {
            InputStream inputStream = contactImage.getInputStream(); // Get stream once
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
       

            cloudinary.uploader().upload(data, ObjectUtils.asMap(
                "public_id", filename
                , "overwrite", true
                , "resource_type","auto" ));
          
            return this.getUrlFromPublicId(filename);


        } catch (IOException e) {
            
            e.printStackTrace();
            return null;
        }

        
        //return path of img: url
       

    }

    @Override
    public String getUrlFromPublicId(String publicId) {
       
       return cloudinary.url()   
                        .transformation(
                            new Transformation<>()
                            .width(AppConstants.CONTACT_IMAGE_WIDTH)
                            .height(AppConstants.CONTACT_IMAGE_HEIGHT) 
                            .crop(AppConstants.CONTACT_IMAGE_CROP)
                        )
                        .generate(publicId);
    }
 
}
