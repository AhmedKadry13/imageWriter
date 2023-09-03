package com.image;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class ImageWriter {

     public void writeTextOnImage(String imagePath, String text, String otputFolderPath) throws IOException {
        final BufferedImage image = ImageIO.read(new File(imagePath));
    
        Graphics g = image.getGraphics();
        g.setFont(g.getFont().deriveFont(40f));
        g.drawString(text, 200, 250);
        g.dispose();

        ImageIO.write(image, "png", new File(otputFolderPath + text.replace(' ', '-') + ".png"));

    }

    public void openOutputFolder(String path) {
        String[] bash = {"/usr/bin/nautilus", path};
        try {
            Process process = Runtime.getRuntime().exec(bash);

            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Success!");
                System.out.println(output);
                System.exit(0);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }


    public String makeDirectory(String parent, String dirName) {
        String parentPath = parent;
        if(!parent.endsWith("/")) {
            parent.concat("/");
        }
        File dir = new File(parentPath, dirName);

        if(dir.mkdirs()) {
            String path = dir.getPath() + "/";
            System.out.println(path);
            return path;
        } else {
            return parent;
        }
    }
    
}
