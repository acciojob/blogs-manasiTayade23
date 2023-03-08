package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image=new Image();
        image.setId(blogId);
        image.setDescription(description);
        image.setDimensions(dimensions);

        imageRepository2.save(image);
        Blog blog=blogRepository2.findById(blogId).get();
        blog.getImageList().add(image);
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        Image image=imageRepository2.findById(id).get();
        imageRepository2.deleteById(id);

    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        int count=0;
        Image image=imageRepository2.findById(id).get();
        String dimension=image.getDimensions();
        String[] s=dimension.split("X");
        int a=Integer.parseInt(s[0]);
        int b=Integer.parseInt(s[1]);
        String[] d2=screenDimensions.split("X");
        int x=Integer.parseInt(d2[0]);
        int y=Integer.parseInt(d2[1]);

        int len1=x/a;
        int len2=y/b;

        return len1*len2;
    }
}
